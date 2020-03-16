package View;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class SelectionWindow extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("SelectionWindow.fxml"));

        primaryStage.setTitle("My Toy Language");
        primaryStage.setScene(new Scene(root, 700, 600));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
