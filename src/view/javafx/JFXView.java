package view.javafx;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;


public class JFXView extends Application{

    @Override
    public void start(Stage primStage) {
        Pane root = new StackPane();
        Label label = new Label("Hello world");
        Button actButton = new Button("Press here");
        actButton.setOnAction((event) -> label.setVisible(!label.isVisible()));
        root.getChildren().addAll(label,actButton);

        primStage.setTitle("Application Name Here");
        primStage.setScene(new Scene(root,1080,1080));
        primStage.show();

    }
}
