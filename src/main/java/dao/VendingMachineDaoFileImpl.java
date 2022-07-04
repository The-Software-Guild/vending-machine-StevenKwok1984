package src.main.java.dao;


import src.main.java.dto.Change;
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
    private final String VENDING_MACHINE_FILE;

    public VendingMachineDaoFileImpl() {
        VENDING_MACHINE_FILE = "VendingMachine.txt";
    }
    public VendingMachineDaoFileImpl(String testFile) {
        VENDING_MACHINE_FILE = testFile;
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


    @Override
    public Item getItem(String name) throws VendingMachinePersistenceException {
        loadMachine();
        return items.get(name);
    }




    @Override
    public Map<String,BigDecimal> getMapOfItemNamesInStockWithPrices() throws VendingMachinePersistenceException{
        loadMachine();

        Map<String, BigDecimal> itemsInStockWithCosts = items.entrySet()
                .stream()
                .filter(map -> map.getValue().getInventory() > 0)
                .collect(Collectors.toMap(map -> map.getKey(), map -> map.getValue().getCost()));

        return itemsInStockWithCosts;

    }

    @Override
    public  List<Item> getAllItems() throws VendingMachinePersistenceException {
        loadMachine();
        return new ArrayList(items.values());
    }

    @Override
    public Map<BigDecimal, BigDecimal> getChangePerCoin(Item item, BigDecimal money) {
        BigDecimal itemCost = item.getCost();
        Map<BigDecimal, BigDecimal> changeDuePerCoin = Change.changeDuePerCoin(itemCost, money);
        return changeDuePerCoin;
    }

    private String marshallItem (Item anItem) {
        String itemAsText = anItem.getName() + DELIMITER;
        itemAsText += anItem.getCost() + DELIMITER;
        itemAsText += anItem.getInventory();
        return itemAsText;
    }

    private Item unmarshallItem (String itemAsText){
        //split the string into an array of strings at the delimiter
        String [] itemTokens = itemAsText.split("::");
        String name = itemTokens[0];
        Item itemFromFile = new Item(name);
        BigDecimal bigDecimal = new BigDecimal(itemTokens[1]);
        itemFromFile.setCost(bigDecimal);
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
