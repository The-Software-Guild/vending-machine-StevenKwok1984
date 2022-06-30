package src.main.java.dao;


import src.main.java.dto.Item;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;

public class VendingMachineDaoFileImpl implements VendingMachineDao {
    private Map <String, Item> items = new HashMap<>();
    public static final String DELIMITER = "::";
    private String VENDING_MACHINE_FILE;


    public VendingMachineDaoFileImpl(String file) {
        VENDING_MACHINE_FILE = file;
    }

    @Override
    public int getItemInventory(String name) throws VendingMachinePersistenceException {
        loadMachine();
        return items.get(name).getInventory();
    }

    @Override
    public void removeOneItemFromInventory(String name) throws VendingMachinePersistenceException {
        loadMachine();
        int prevInventory = items.get(name).getInventory();
        items.get(name).setInventory(prevInventory-1);
        writeMachine();
    }

    //Returns item or null if there is no item associated with the given item name
    @Override
    public Item getItem(String name) throws VendingMachinePersistenceException {
        loadMachine();
        return items.get(name);
    }


    @Override
    public Map<String,BigDecimal> getMapOfItemNamesInStockWithPrices() throws VendingMachinePersistenceException{
        loadMachine();
        //Return a list of the items names where the item inventory
        //is greater than 0, i.e. get the keys where the inventory>0

        Map<String, BigDecimal> itemsInStockWithCosts = items.entrySet()
                .stream()
                .filter(map -> map.getValue().getInventory() > 0)
                .collect(Collectors.toMap(map -> map.getKey(), map -> map.getValue().getPrice()));

        return itemsInStockWithCosts;

    }


    //Marshall: process of transforming memory representation of an object to a data format
    //suit for permanent storage
    private String marshallItem (Item anItem) {
        String itemAsText = anItem.getName() + DELIMITER;
        itemAsText += anItem.getPrice() + DELIMITER;
        itemAsText += anItem.getInventory();
        return itemAsText;
    }


    //Unmarshall: process of transforming the memory representation of an object
    private Item unmarshallItem (String itemAsText){
        //split the string into an array of strings at the delimiter
        String [] itemTokens = itemAsText.split("::");
        String name = itemTokens[0];
        Item itemFromFile = new Item(name);
        BigDecimal bigDecimal = new BigDecimal(itemTokens[1]);
        itemFromFile.setPrice(bigDecimal);
        itemFromFile.setInventory(Integer.parseInt(itemTokens[2]));
        return itemFromFile;
    }


    private void loadMachine() throws VendingMachinePersistenceException {
        Scanner scanner;

        try {
            // Create Scanner for reading the file
            scanner = new Scanner(
                    new BufferedReader(
                            new FileReader(VENDING_MACHINE_FILE)));
        } catch (FileNotFoundException e) {
            throw new VendingMachinePersistenceException(
                    "-_- Could not load item data into memory.", e);
        }
        String currentLine;
        Item currentItem;

        while (scanner.hasNextLine()) {
            currentLine = scanner.nextLine();
            currentItem = unmarshallItem(currentLine);
            items.put(currentItem.getName(), currentItem);
        }
        scanner.close();
    }


    @Override
    public  List<Item> getAllItems() throws VendingMachinePersistenceException {
        loadMachine();
        return new ArrayList(items.values());
    }


    private void writeMachine() throws VendingMachinePersistenceException {
        PrintWriter out;

        try {
            out = new PrintWriter(new FileWriter(VENDING_MACHINE_FILE));
        } catch (IOException e) {
            throw new VendingMachinePersistenceException("Could not save student data.", e);
        }
        String itemAsText;
        List <Item> itemList = this.getAllItems();
        for (Item currentItem : itemList) {
            itemAsText = marshallItem(currentItem);
            out.println(itemAsText);
            out.flush();
        }
        out.close();
    }
}
