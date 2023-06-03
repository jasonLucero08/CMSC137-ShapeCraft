package ChatFeatureWithGui.copy;

import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) {
        ClientStart window1 = new ClientStart("Window 1");
        window1.show();

    }

    public static void main(String[] args) {
        launch(args);
    }
}

