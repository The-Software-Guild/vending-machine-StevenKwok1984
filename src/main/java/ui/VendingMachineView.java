package src.main.java.ui;

import java.math.BigDecimal;
import java.util.Map;

public class VendingMachineView {

    private UserIO io;

    // for loading user io
    public VendingMachineView (UserIO io) {
        this.io = io;
    }

    // display the manu banner
    public void displayMenuBanner() {
        io.print("=== Menu ===");
    }

    // for obtaining the amount of the money
    public BigDecimal getMoney() {
        return io.readBigDecimal("Enter the amount of money in dollars before selection");
    }

    // for showing all items in stock with price
    public void displayMenu(Map<String, BigDecimal> itemsInStockWithPrices) {
        itemsInStockWithPrices.entrySet().forEach(entry ->{
            System.out.println(entry.getKey() + ": $" + entry.getValue());
        });
    }

    // ask user to select items
    public String getItemSelection() {
        return io.readString("Select an item from the menu above or 'exit' to quit");
    }

    // for providing changes
    public void displayEnjoyBanner(String name) {
        io.print("Here is your change.");
        io.print("Enjoy your " + name + "!");
    }

    // display changes per coin
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

    // for displaying error message with title
    public void displayErrorMessage (String errorMsg) {
        io.print("=== Error ===");
        io.print(errorMsg);
    }

    public void displaySelectAnotherMsg() {
        io.print("Please select something else.");
    }


}
