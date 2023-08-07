import model.Sim;

import java.time.LocalDateTime;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) {
        //Test
        Sim sim = new Sim("Personal SIM", "447518497796", 000, "United Kingdom", "O2", "Micro", 31.23, false, true, "", LocalDateTime.now());
        System.out.println(sim);
        System.out.println(sim.getSimName());

        // Real Implementation
        // model
        // View
        // Controller (model, view)
        //view.run()
    }
}