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
    VendingMachineDao testDao = new VendingMachineDaoFileImpl("VendingMachineTestFile.txt");

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
        Item snickersClone = new Item("Pizza");
        snickersClone.setPrice(new BigDecimal("3.60"));
        snickersClone.setInventory(12);

        //ACT
        Item retrievedItem = testDao.getItem("Pizza");

        //ASSERT
        assertNotNull(retrievedItem, "Item should not be null");
        assertEquals(retrievedItem, snickersClone,"The item retrieved should be pizzas");
    }

    @Test
    public void testRemoveItemFromInventory() throws VendingMachinePersistenceException {
        //ARRANGE
        String itemName = "Google Candy";

        //ACT
        //get the inventory before we remove one
        int inventoryBefore = testDao.getItemInventory(itemName);

        //remove one item
        testDao.removeItemFromInventory(itemName);

        //get the inventory after we have removed one
        int inventoryAfter = testDao.getItemInventory(itemName);

        assertTrue(inventoryAfter < inventoryBefore, "The inventory after should be less than before");
        assertEquals(inventoryAfter, inventoryBefore - 1, "The inventory after should be one less than it was"
                + "before");
    }

    @Test
    public void testGetItemInventory() throws VendingMachinePersistenceException {
        //ARRANGE
        String itemName = "Fickers";

        //ACT
        int retrievedInventory = testDao.getItemInventory(itemName);

        //ASSERT
        assertEquals(retrievedInventory,0,"There are 0 items of snickers left.");
    }

    @Test
    public void testGetItemNamesInStockWithPrices() throws VendingMachinePersistenceException {

        //ACT
        Map<String,BigDecimal> itemsInStock = testDao.getItemNamesInStockWithPrices();

        //ASSERT
        //there are 0 snickers left, so it should not be included.
        assertFalse(itemsInStock.containsKey("Fickers"));
        //There are 7 items in total, only snickers  is out of stock, so there should be 6 items
        assertEquals(itemsInStock.size(),3,"The menu list should contain 3 items.");
        assertTrue(itemsInStock.containsKey("Pizza") &&
                itemsInStock.containsKey("Kitkat") &&
                itemsInStock.containsKey("Google Candy"));
    }

}