package multilevel;

import javafx.application.Application;

import javafx.scene.Scene;
import javafx.scene.control.*;

import javafx.stage.Stage;

import java.util.function.Predicate;


public class Main extends Application {

    static GeneratorPane gp;

    @Override
    public void start(Stage stage) throws Exception {
        gp = new GeneratorPane();
        Scene scene = new Scene(gp, 300, 275);
        stage.setScene(scene);
        stage.show();
    }

    @Override
    public void stop() throws Exception {
        super.stop();
        System.exit(0);
    }

    public static void main(String[] args) {

        launch(args);

    }

}
