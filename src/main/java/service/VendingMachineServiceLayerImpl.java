package src.main.java.service;

import src.main.java.dao.VendingMachineAuditDao;
import src.main.java.dao.VendingMachineDao;
import src.main.java.dao.VendingMachinePersistenceException;
import src.main.java.dto.Change;
import src.main.java.dto.Item;

import java.math.BigDecimal;
import java.util.Map;

public class VendingMachineServiceLayerImpl implements VendingMachineServiceLayer{

    private VendingMachineAuditDao auditDao;
    private VendingMachineDao dao;

    public VendingMachineServiceLayerImpl(VendingMachineAuditDao auditDao, VendingMachineDao dao){
        this.auditDao = auditDao;
        this.dao = dao;
    }

    @Override
    public void checkIfEnoughMoney(Item item, BigDecimal inputMoney) throws  InsufficientFundsException {
        if (item.getPrice().compareTo(inputMoney) == 1) {
            throw new InsufficientFundsException(
            "Error: insufficient funds, you have only input " + inputMoney);
        }
    }

    @Override
    public Map<String, BigDecimal> getItemsInStockWithPrices() throws VendingMachinePersistenceException{
        Map<String, BigDecimal> itemsInStockWithPrices = dao.getMapOfItemNamesInStockWithPrices();
        return itemsInStockWithPrices;
    }

    @Override
    public Map<BigDecimal, BigDecimal> getChangePerCoin(Item item, BigDecimal money){
        BigDecimal itemPrice = item.getPrice();
        Map<BigDecimal, BigDecimal> changeDuePerCoin = Change.changeDuePerCoin(itemPrice, money);
        return changeDuePerCoin;
    }

    @Override
    public Item getItem(String name, BigDecimal inputMoney) throws
                                                            InsufficientFundsException,
                                                            NoItemInventoryException,
                                                            VendingMachinePersistenceException {
        Item wantedItem = dao.getItem(name);

        if (wantedItem == null) {
            throw new NoItemInventoryException(
                    "Error: there are no " + name + "'s in the vending machine.");
        }

        checkIfEnoughMoney(wantedItem, inputMoney);

        removeOneItemFromInventory(name);
        return wantedItem;
    }

    private void removeOneItemFromInventory(String name) throws
                                                        NoItemInventoryException,
                                                        VendingMachinePersistenceException {
        if (dao.getItemInventory(name) >0) {
            dao.removeAnItemFromInventory(name);
            auditDao.writeAuditEntry(" One " + name + " removed.");
        } else {
            throw new NoItemInventoryException("Error: " + name + " is out of stock.");
        }
    }

}
