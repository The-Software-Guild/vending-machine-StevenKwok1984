package src.test.java.service;


import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.fail;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import src.main.java.dao.*;
import src.main.java.dto.Item;
import src.main.java.service.InsufficientFundsException;
import src.main.java.service.NoItemInventoryException;
import src.main.java.service.VendingMachineServiceLayer;
import src.main.java.service.VendingMachineServiceLayerImpl;

class VendingMachineServiceLayerImplTest {

    // initialisation
    VendingMachineDao testDao = new VendingMachineDaoFileImpl("VendingMachineTestFile.txt");
    VendingMachineAuditDao testAuditDao = new VendingMachineAuditDaoFileImpl();
    VendingMachineServiceLayer testService = new VendingMachineServiceLayerImpl(testAuditDao,testDao);

    // constructors
    public VendingMachineServiceLayerImplTest() {

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
    public void testCheckIfEnoughMoney() {
        // ARRANGE
        Item pizzaClone = new Item("Pizza");
        pizzaClone.setPrice(new BigDecimal("3.60"));
        pizzaClone.setInventory(12);

        BigDecimal enoughMoney = new BigDecimal("10.00");
        BigDecimal notEnoughMoney = new BigDecimal("0.50");

        // ACT - enough
        try{
            testService.checkIfEnoughMoney(pizzaClone, enoughMoney);
        } catch (InsufficientFundsException e) {
            fail("There is sufficient funds, exception should not have been thrown.");
        }

        // ACT not enough
        try{
            testService.checkIfEnoughMoney(pizzaClone, notEnoughMoney);
            fail("There insufficient funds, exception should have been thrown");
        } catch (Exception e) {

        }

    }

    @Test
    public void testGetChangePerCoin() {
        //ARRANGE
        Item pizzaClone = new Item("Pizza");
        pizzaClone.setPrice(new BigDecimal("3.60"));
        pizzaClone.setInventory(12);

        BigDecimal money = new BigDecimal("4.25");

        // Change should be $0.90: 25c: 3, 10c: 1, 5c:1
        Map<BigDecimal, BigDecimal> expectedChangePerCoin = new HashMap<>();
        expectedChangePerCoin.put(new BigDecimal("0.25"), new BigDecimal("2"));
        expectedChangePerCoin.put(new BigDecimal("0.10"), new BigDecimal("1"));
        expectedChangePerCoin.put(new BigDecimal("0.05"), new BigDecimal("1"));

        //ACT
        Map<BigDecimal, BigDecimal> changePerCoin = testService.getChangePerCoin(pizzaClone, money);

        //ASSERT
        assertEquals(changePerCoin.size(), 3, "There should be three types of coins");
    }

    @Test
    public void testGetItem() throws InsufficientFundsException, VendingMachinePersistenceException, NoItemInventoryException {
        //ARRANGE

        BigDecimal money = new BigDecimal("4.00");

        Item fickersClone = new Item("Fickers");
        fickersClone.setPrice(new BigDecimal("2.10"));
        fickersClone.setInventory(0);

        Item pizzaClone = new Item("Pizza");
        pizzaClone.setPrice(new BigDecimal("3.60"));
        pizzaClone.setInventory(12);

        Item itemWanted = null;
        //ACT
        try {
            itemWanted = testService.getItem("Fickers", money);
            fail("The item wanted is out of stock.");
        }catch (NoItemInventoryException e) {
        }
        try {
            itemWanted = testService.getItem("Pizza", money);
        }catch (NoItemInventoryException e) {
            if (testDao.getItemInventory("Pizza")>0){
                fail("The item wanted is in stock.");
            }

            //ASSERT
            assertNotNull(itemWanted, "change should not be null");
            assertEquals(itemWanted, pizzaClone,"The item retrieved should be snickers");
        }
    }

    @Test
    public void testRemoveItemFromInventory() throws VendingMachinePersistenceException {
        //ARRANGE
        String name = "Fickers";

        //There are no snickers left
        try{
            //ACT
            testService.removeOneItemFromInventory(name);
            //ASSERT
            fail("There are no fickers left, exception should be thrown");
        } catch (NoItemInventoryException e) {
        }

        String kitkat = "Kitkat";
        try{
            //ACT
            testService.removeOneItemFromInventory(kitkat);
        } catch (NoItemInventoryException e) {
            if (testDao.getItemInventory(kitkat)>0){
                fail("Kitkats are in stock, exception should not be thrown");
            }
        }
    }




}