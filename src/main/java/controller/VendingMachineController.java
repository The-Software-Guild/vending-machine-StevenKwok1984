package src.main.java.controller;

import src.main.java.dao.VendingMachinePersistenceException;
import src.main.java.dto.Item;
import src.main.java.service.InsufficientFundsException;
import src.main.java.service.NoItemInventoryException;
import src.main.java.service.VendingMachineServiceLayer;
import src.main.java.ui.UserIO;
import src.main.java.ui.UserIOConsoleImpl;
import src.main.java.ui.VendingMachineView;

import java.math.BigDecimal;
import java.util.Map;

public class VendingMachineController {
    private UserIO io = new UserIOConsoleImpl();
    private VendingMachineView view;
    private VendingMachineServiceLayer service;

    public VendingMachineController(VendingMachineView view, VendingMachineServiceLayer service) {
        this.view = view;
        this.service = service;
    }

    public void run() {
        boolean keepGoing = true;
        String itemSelection = "";
        BigDecimal inputMoney;
        view.displayMenuBanner();
        try {
            getMenu();
        } catch (VendingMachinePersistenceException e) {
            view.displayErrorMessage(e.getMessage());
        }
        inputMoney = getMoney();
        while (keepGoing) {
            try {
                //Display the menu and get a selection
                itemSelection = getItemSelection();

                //If the user selects Exit, the program is exited
                if (itemSelection.equalsIgnoreCase("Exit")) {
                    keepGoing = false;
                    break;
                }
                getItem(itemSelection, inputMoney);
                keepGoing = false;
                break;

            } catch (InsufficientFundsException | NoItemInventoryException | VendingMachinePersistenceException e) {
                view.displayErrorMessage(e.getMessage());
                view.displayPleaseTryAgainMsg();
            }
        }
        exitMessage();

    }


    private void getMenu() throws VendingMachinePersistenceException {
        Map<String, BigDecimal> itemsInStockWithPrices = service.getItemsInStockWithPrices();
        view.displayMenu(itemsInStockWithPrices);
    }

    private BigDecimal getMoney() {
        return view.getMoney();
    }

    private String getItemSelection() {
        return view.getItemSelection();
    }

    private void exitMessage() {
        view.displayExitBanner();
    }

    private void getItem(String name, BigDecimal money) throws InsufficientFundsException, NoItemInventoryException, VendingMachinePersistenceException {
        Item wantedItem = service.getItem(name, money);
        Map<BigDecimal, BigDecimal> changeDuePerCoin = service.getChangePerCoin(wantedItem, money);
        view.displayChangeDuePerCoin(changeDuePerCoin);
        view.displayEnjoyBanner(name);
    }



}
