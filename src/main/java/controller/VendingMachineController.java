package src.main.java.controller;

import src.main.java.ui.UserIO;
import src.main.java.ui.UserIOConsoleImpl;

import java.util.Scanner;


public class VendingMachineController {
    private UserIO io = new UserIOConsoleImpl();

    public void run() {
        boolean keepGoing = true;
        String menuSelection = "";
        io.print("List of Products");
        io.print("product 1 \n Product 2");

        Scanner sc = new Scanner(System.in);
        while (keepGoing) {
            System.out.println("Please enter ");
            int answer = sc.nextInt();
            if (answer < 1) {
                keepGoing = false;
                io.print("Bye");
            } else {
                io.print("Keep Going");
            }
        }
    }
}
