package src.test.java.dao;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.*;
import src.main.java.dao.VendingMachineDaoFileImpl;
import src.main.java.dao.VendingMachinePersistenceException;
import src.main.java.dto.Item;
import src.main.java.dao.VendingMachineDao;

import java.math.BigDecimal;


public class VendingMachineAuditDaoFileImplTest {

    VendingMachineDao testDao = new VendingMachineDaoFileImpl("VendingMachineTestFile.txt");


    public void VendingMachineDaoFileImplTest() {
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
        Item snickersClone = new Item("Snickers");
        snickersClone.setPrice(new BigDecimal("2.10"));
        snickersClone.setInventory(0);

        //ACT
        Item retrievedItem = testDao.getItem("Snickers");

        //ASSERT
        assertNotNull(retrievedItem, "Item should not be null");
        assertEquals(retrievedItem, snickersClone,"The item retrieved should be snickers");

    }
}