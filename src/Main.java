import controller.SimController;
import model.Sim;
import view.CLI;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {

    public static void main(String[] args) {
        //Test
//        Sim sim = new Sim("447518497796","Personal SIM",  000, "United Kingdom", "O2", "Micro", 12.32, true, true, "", LocalDateTime.now());
//        System.out.println(sim);

        // Real Implementation
        SimController controller = new SimController();
        CLI cli = new CLI();
        controller.addSIM(new Sim("447518497796","Personal SIM",  "United Kingdom", "O2", "Micro", "0000",12.32, true, "Micro",true, "", LocalDateTime.now()));
        controller.addSIM(new Sim("639157871608","Personal SIM",  "Philippines", "Globe", "Standard", "0000", 00.00, true, "Standard", false, "Deactivated in 2015", LocalDateTime.now()));
        controller.deactivateSIM("639157871608");
        System.out.println("===================================");

        List<Sim> matching1 = controller.getSIMCardsByCarrier("Globe");
        if(!matching1.isEmpty()){
            for(Sim sim : matching1){
                System.out.println("Match Found: ");
                cli.displaySIMDetails(sim);
            }
        } else {
            System.out.println("No Carrier Name listed in database");
        }
        System.out.println("===================================");
        List<Sim> matching2 = controller.searchSIMByNumber("16");
        if(!matching2.isEmpty()){
            System.out.println("Match(es) Found! ");
            for(Sim sim : matching2){
                cli.displaySIMDetails(sim);
            }
        } else {
            System.out.println("No Number found in Database");
        }
        System.out.println("\n===================================");
        controller.saveToFile();
        System.out.println("\n===================================");
        controller.loadFromFile();
        System.out.println("Database Includes:");
        System.out.println(controller.getAllSIMs());
        System.out.println("\n===================================");

        //
    }
}