package src.test.java.dao;

import org.junit.jupiter.api.*;
import src.main.java.dao.VendingMachineAuditDao;
import src.main.java.dao.VendingMachineAuditDaoFileImpl;
import src.main.java.dao.VendingMachinePersistenceException;


class VendingMachineAuditDaoFileImplTest {
    String testAuditFile = "testAuditFile.txt";
    VendingMachineAuditDao testAuditDao = new VendingMachineAuditDaoFileImpl(testAuditFile);


    public VendingMachineAuditDaoFileImplTest() {
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
    public void testWriteAuditEntry() throws VendingMachinePersistenceException {
        //ARRANGE
        String entry = "One Snickers removed.";

        //ACT
        testAuditDao.writeAuditEntry(entry);

    }

}