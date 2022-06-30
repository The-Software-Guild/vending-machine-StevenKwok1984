package src.main.java.dao;

import src.main.java.dto.Item;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public interface VendingMachineDao {
    int getItemInventory(String name) throws VendingMachinePersistenceException;

    void removeAnItemFromInventory(String name) throws VendingMachinePersistenceException;

    // Return item or null
    Item getItem(String name) throws VendingMachinePersistenceException;

    Map<String, BigDecimal> getMapOfItemNamesInStockWithPrices() throws VendingMachinePersistenceException;

    List<Item> getAllItems() throws VendingMachinePersistenceException;
}
