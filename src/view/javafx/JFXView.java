package view.javafx;
import controller.SimController;
import javafx.application.Application;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.CheckBoxTableCell;
import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import model.Sim;

import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;
import javafx.util.Callback;

import javax.swing.text.Element;
import javax.swing.text.html.ImageView;


public class JFXView{
    private SimController controller;
    final private Scene scene;
    final int gap = 6;
    public JFXView(SimController controller){
        this.controller = controller;
//        VBox root = new VBox(6 * gap);
        Pane paneRoot = new StackPane();
        paneRoot.setPadding(new Insets(gap,gap,gap,gap));
        Text titleText = new Text("SIM Database");
        titleText.setFont(Font.font("Tahoma", FontWeight.BOLD, 20));
//        ImageView logo = new ImageView((Element) new Image(getClass().getResourceAsStream("/graphics/icon.png")));
        paneRoot.getChildren().addAll(titleText);
//
//        Button actButton = new Button("Press here");
//        actButton.setOnAction((event) -> {
//            Alert alert = new Alert(Alert.AlertType.INFORMATION);
//            alert.setHeaderText("Test");
//            alert.setContentText("Hello world");
//            ButtonType exit = new ButtonType("Exit");
//            alert.getButtonTypes().setAll(exit);
//            Optional result = alert.showAndWait();
//            if(result.get() == exit){
//                System.exit(0);
//            }
//        });

        TableView tableView = new TableView();
        tableView.setPadding(new Insets(0, (gap*4),gap,0));

        TableColumn<Sim, String> column1 = new TableColumn<>("SIM Name");
        column1.setCellValueFactory(new PropertyValueFactory<>("simName"));
        TableColumn<Sim, String> column2 = new TableColumn<>("SIM Country");
        column2.setCellValueFactory(new PropertyValueFactory<>("simCountry"));
//        TableColumn<Sim, Boolean> column3 = new TableColumn<>("SIM Roaming");
//        column3.setCellValueFactory(new PropertyValueFactory<>(("isRoaming")));
//        column3.setCellFactory(CheckBoxTableCell.forTableColumn(column3));
//        TableColumn<Sim, Boolean> column4 = new TableColumn<>("SIM Status");
//        column4.setCellValueFactory(new PropertyValueFactory<>("isActive"));
//        column4.setCellFactory(CheckBoxTableCell.forTableColumn(column4));
        TableColumn<Sim, String> column5 = new TableColumn<>("SIM Credit");
        column5.setCellValueFactory(new PropertyValueFactory<>("simCredit"));
        TableColumn<Sim, LocalDateTime> column6 = new TableColumn<>("Last Updated");
        column6.setCellValueFactory(new PropertyValueFactory<>("lastUpdated"));
        // Create the Date Time formatter
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM-dd-yyyy @ HH:mm");
        column6.setCellFactory(new Callback<>() { // Customize the Last Updated Cell Value
            @Override
            public TableCell<Sim, LocalDateTime> call(TableColumn<Sim, LocalDateTime> simLocalDateTimeTableColumn) {
                return new TableCell<>() {
                    @Override
                    protected void updateItem(LocalDateTime item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty || item == null) {
                            setText(null);
                        } else {
                            setText(formatter.format(item));
                        }
                    }
                };
            }
        });

        tableView.getColumns().addAll(column1,column2, column5, column6);

        tableView.getItems().add(new Sim("447518497796","Personal SIM",  "United Kingdom", "O2", "Micro", "0000",12.32, false, "Micro",true, "", LocalDateTime.now()));
        tableView.getItems().add(new Sim("639157871608","Childhood SIM",  "Philippines", "Globe", "Standard", "0000", 00.00, true, "Standard", false, "Deactivated in 2015", LocalDateTime.now().minusDays(3).minusHours(6)));

        TableView.TableViewSelectionModel<Sim> selectionModel = tableView.getSelectionModel();
        selectionModel.setSelectionMode(SelectionMode.SINGLE);
        selectionModel.setSelectionMode(SelectionMode.MULTIPLE);
        // Create an Observation List
        ObservableList<Sim> selectedItems = selectionModel.getSelectedItems();
        // Print out Selected/Highlighted
        selectedItems.addListener((ListChangeListener<Sim>) change -> System.out.println("Selection changed: " + change.getList()));

        VBox vbox = new VBox(paneRoot,tableView);
        this.scene = new Scene(vbox,
            (4*gap) * gap,
            (3*gap)* gap
       );
    }

    public Scene getScene() {
        return scene;
    }
}
