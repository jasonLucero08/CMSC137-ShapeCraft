package ChatFeatureWithGui;

import javax.swing.JOptionPane;

import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) {
    	String input = JOptionPane.showInputDialog(null, "Enter your username for the group chat:");
        ClientStart window1 = new ClientStart(input);
        window1.show();

    }

    public static void main(String[] args) {
        launch(args);
    }
}

