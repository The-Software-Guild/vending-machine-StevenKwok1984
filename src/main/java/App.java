package src.main.java;

import src.main.java.controller.VendingMachineController;

public class App {
    public static void main(String[] args) {
        VendingMachineController controller = new VendingMachineController();
        controller.run();
    }
}
