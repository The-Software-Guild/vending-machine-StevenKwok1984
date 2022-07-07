package src.main.java.dao;

import src.main.java.dto.Item;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public interface VendingMachineDao {


    int getItemInventory(String name) throws VendingMachinePersistenceException;


    // reduce number of items after purchasing
    void reduceOneItemFromInventory(String name) throws VendingMachinePersistenceException;

    //Returns item or null if there is no item associated with the given item name
    Item getItem(String name) throws VendingMachinePersistenceException;

    Map<String,BigDecimal> getMapOfItemNamesInStockWithPrices() throws VendingMachinePersistenceException;

    List<Item> getAllItems() throws VendingMachinePersistenceException;

    Map<BigDecimal, BigDecimal> getChangePerCoin(Item item, BigDecimal money);
}
