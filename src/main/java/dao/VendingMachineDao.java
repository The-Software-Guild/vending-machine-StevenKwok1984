package src.main.java.dao;

import src.main.java.dto.Item;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public interface VendingMachineDao {


    int getItemInventory(String name) throws VendingMachinePersistenceException;


    void removeItemFromInventory(String name) throws VendingMachinePersistenceException;

    //Returns item or null if there is no item associated with the given item name
    Item getItem(String name) throws VendingMachinePersistenceException;


    Map<String,BigDecimal> getItemNamesInStockWithPrices() throws VendingMachinePersistenceException;

    List<Item> getAllItems() throws VendingMachinePersistenceException;
}
