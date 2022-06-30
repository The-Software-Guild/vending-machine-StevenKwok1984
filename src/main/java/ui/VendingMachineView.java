package src.main.java.ui;

import java.math.BigDecimal;
import java.util.Map;

public class VendingMachineView {

    private UserIO io;

    public VendingMachineView (UserIO io) {
        this.io = io;
    }

    public BigDecimal getMoney() {
        return io.readBigDecimal("Enter the amount money in dollars before making selection");
    }

    public void displayMenuBanner() {
        io.print("=== Menu ===");
    }

    public void displayMenu(Map<String, BigDecimal> itemsInStockWithPrices) {
        itemsInStockWithPrices.entrySet().forEach(entry ->{
            System.out.println(entry.getKey() + ": $" + entry.getValue());
        });
    }

    public String getItemSelection() {
        return io.readString("Select an item from the menu above or 'exit' to quit");
    }

    public void displayEnjoyBanner(String name) {
        io.print("Here is your change.");
        io.print("Enjoy your " + name + "!");
    }

    public void displayInsufficientFunds(BigDecimal money) {
        io.print("Insufficient funds, you only have input $" + money);
    }

    public void displayItemOutOfStock(String name) {
        io.print("Error, " + name + " is out of stock.");
    }

    public void displayChangeDuePerCoin(Map<BigDecimal, BigDecimal> changeDuePerCoin) {
        changeDuePerCoin.entrySet().forEach(entry -> {
            System.out.println(entry.getKey() + "c : " + entry.getValue());
        });
    }

    public void displayExitBanner() {
        io.print("See you!");
    }

    public void displayUnknownCommandBanner() {
        io.print("Unknown Command!!!");
    }

    public void displayErrorMessage (String errorMsg) {
        io.print("=== Error ===");
        io.print(errorMsg);
    }

    public void displayPleaseTryAgainMsg() {
        io.print("Please select something else.");
    }













}
