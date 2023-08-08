package view;

import model.Sim;

import java.util.Map;
import java.util.Scanner;

public class CLI {
    private Scanner scanner;

    public CLI(){
        scanner = new Scanner(System.in);

    }

    public String getInput(String prompt) {
        System.out.print(prompt + ": ");
        return scanner.nextLine();
    }

    public void displayMessage(String message) {
        System.out.println(message);
    }

    public void displaySIMDetails(Sim sim) {
        System.out.println("SIM Card Details:");
        System.out.print(sim.toString());
        System.out.println("\n------------------");
    }
}
