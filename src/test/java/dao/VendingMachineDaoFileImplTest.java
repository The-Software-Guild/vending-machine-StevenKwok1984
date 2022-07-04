package src.test.java.dao;


import java.math.BigDecimal;
import java.util.Map;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import src.main.java.dao.VendingMachineDao;
import src.main.java.dao.VendingMachineDaoFileImpl;
import src.main.java.dao.VendingMachinePersistenceException;
import src.main.java.dto.Item;

class VendingMachineDaoFileImplTest {
    VendingMachineDao testDao = new VendingMachineDaoFileImpl("src/test/java/dao/VendingMachineTestFile.txt");

    public VendingMachineDaoFileImplTest() {
    }
    @BeforeAll
    public static void setUpClass() {
    }
    @AfterAll
    public static void tearDownClass() {
    }
    @BeforeEach
    public void setUp() {
    }
    @AfterEach
    public void tearDown() {
    }

    @Test
    public void testGetItem() throws VendingMachinePersistenceException {
        //ARRANGE
        Item snickersClone = new Item("Starferry Pie");
        snickersClone.setCost(new BigDecimal("2.50"));
        snickersClone.setInventory(0);

        //ACT
        Item retrievedItem = testDao.getItem("Starferry Pie");

        //ASSERT
        assertNotNull(retrievedItem, "Item should not be null");
        assertEquals(retrievedItem, snickersClone,"The item retrieved should be starferry pie");
    }

    @Test
    public void testRemoveOneItemFromInventory() throws VendingMachinePersistenceException {
        //ARRANGE
        String itemName = "McCoys";

        //ACT
        //get the inventory before we remove one
        int inventoryBefore = testDao.getItemInventory(itemName);

        //remove one item
        testDao.removeOneItemFromInventory(itemName);

        //get the inventory after we have removed one
        int inventoryAfter = testDao.getItemInventory(itemName);

        assertTrue(inventoryAfter < inventoryBefore, "The inventory after should be less than before");
        assertEquals(inventoryAfter, inventoryBefore - 1, "The inventory after should be one less than it was"
                + "before");
    }

    @Test
    public void testGetItemInventory() throws VendingMachinePersistenceException {
        //ARRANGE
        String itemName = "Snickers";

        //ACT
        int retrievedInventory = testDao.getItemInventory(itemName);

        //ASSERT
        assertEquals(retrievedInventory,0,"There are 0 items of snickers left.");
    }

    @Test
    public void testGetMapOfItemNamesInStockWithPrices() throws VendingMachinePersistenceException {

        //ACT
        Map<String,BigDecimal> itemsInStock = testDao.getMapOfItemNamesInStockWithPrices();

        //ASSERT
        //there are 0 snickers left, so it should not be included.
        assertFalse(itemsInStock.containsKey("Snickers"));
        assertFalse(itemsInStock.containsKey("Starferry Pie"));
        //There are 6 items in total, only snickers and Starferry Pie is out of stock, so there should be 4 items
        assertEquals(itemsInStock.size(),6,"The menu list should contain 4 items.");
        assertTrue(itemsInStock.containsKey("Cadbury Heroes") &&
                itemsInStock.containsKey("Kitkat") &&
                itemsInStock.containsKey("Mini Eggs") &&
                itemsInStock.containsKey("Cheese Snack"));
    }

}