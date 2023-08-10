import controller.SimController;
import javafx.application.Application;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import model.Sim;
import view.CLI;
import view.javafx.JFXView;

import java.time.LocalDateTime;
import java.util.List;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main extends Application {

    public static void main(String[] args) {
        // Real Implementation
        SimController controllerCLI = new SimController();
        CLI cli = new CLI();
        controllerCLI.addSIM(new Sim("447518497796","Personal SIM",  "United Kingdom", "O2", "Micro", "0000",12.32, true, "Micro",true, "", LocalDateTime.now()));
        controllerCLI.addSIM(new Sim("639157871608","Personal SIM",  "Philippines", "Globe", "Standard", "0000", 00.00, true, "Standard", false, "Deactivated in 2015", LocalDateTime.now()));
        controllerCLI.deactivateSIM("639157871608");
        System.out.println("===================================");

        List<Sim> matching1 = controllerCLI.getSIMCardsByCarrier("Globe");
        if(!matching1.isEmpty()){
            for(Sim sim : matching1){
                System.out.println("Match Found: ");
                cli.displaySIMDetails(sim);
            }
        } else {
            System.out.println("No Carrier Name listed in database");
        }
        System.out.println("===================================");
        List<Sim> matching2 = controllerCLI.searchSIMByNumber("16");
        if(!matching2.isEmpty()){
            System.out.println("Match(es) Found! ");
            for(Sim sim : matching2){
                cli.displaySIMDetails(sim);
            }
        } else {
            System.out.println("No Number found in Database");
        }
        System.out.println("\n===================================");
        controllerCLI.saveToFile();
        System.out.println("\n===================================");
        controllerCLI.loadFromFile();
        System.out.println("Database Includes:");
        System.out.println(controllerCLI.getAllSIMs());
        System.out.println("\n===================================");
        // GUI Test
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        SimController guiController = new SimController();
        JFXView gui = new JFXView(guiController);

        primaryStage.setTitle("SIM Database");
        primaryStage.setScene(gui.getScene());
        // Load icon image from the resource folder
        Image iconImage = new Image(getClass().getResourceAsStream("/graphics/duo-sim-cards-64.png"));
        // Set the application icon
        primaryStage.getIcons().add(iconImage);
        // Set the program name for macOS
        System.setProperty("apple.awt.application.name", "SIM Database");
        System.out.println(System.getProperties());
        // Set the program name for Windows
        primaryStage.setTitle("SIM Database");
        primaryStage.getIcons().add(new Image("/resources/graphics/duo-sim-cards-64.png"));
        primaryStage.setMinWidth(450);
        primaryStage.setMinHeight(420);
        primaryStage.show();
    }
}