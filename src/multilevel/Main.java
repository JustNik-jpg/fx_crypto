package multilevel;

import javafx.application.Application;

import javafx.scene.Scene;
import javafx.scene.control.*;

import javafx.stage.Stage;



public class Main extends Application {

    @Override
    public void start(Stage stage) throws Exception{
        TabPane tabPane = new TabPane();

        Tab tabKeyGen = new Tab("Generator");
        Tab tabUpload = new Tab("Upload");
        tabPane.getTabs().addAll(tabKeyGen,tabUpload);

        tabKeyGen.setClosable(false);
        tabUpload.setClosable(false);

        tabKeyGen.setContent(new GeneratorPane(stage));
        tabUpload.setContent(new UploadPane());

        stage.setTitle("CryptoFX version 0.1 alpha (c)");
        Scene scene = new Scene(tabPane, 300, 275);
        //scene.getStylesheets().add("multilevel/style.css");
        stage.setScene(scene);
        stage.show();
    }


    public static void main(String[] args) {
        //Generator.getKeysMap(13,3,"111");


        System.out.println();
        launch(args);

    }
}
