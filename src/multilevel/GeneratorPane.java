package multilevel;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;

import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;

public class GeneratorPane extends BorderPane {

    Stage stage;
    TextField pField;
    TextField nField;

    public GeneratorPane(Stage stage){
        this.stage = stage;
        init();
    }

    private void init(){
        HBox hBox = new HBox(5);
        hBox.setAlignment(Pos.CENTER);
        this.setTop(hBox);
        this.setStyle("-fx-background-color: darkgrey;");

        Label pText = new Label("P послідовність");
        Label nText = new Label("N послідовність");
        Label boxText = new Label("Select");

        nField = new TextField();
        nField.setPrefColumnCount(3);

        pField = new TextField();
        pField.setPrefColumnCount(3);

        Button genButton = new Button("Start");
        genButton.setOnAction(e->{
            try {
                onClick();
            }catch (NullPointerException ex){
                System.out.println("File not chosen");
            }
        });
        genButton.setPrefWidth(100.0);
        this.setCenter(genButton);

        ObservableList<String> chosenEl = FXCollections.observableArrayList("*","**"); // CHANGE ON NEED!!!
        ComboBox box = new ComboBox(chosenEl);

        hBox.getChildren().addAll(new VBox(pText,pField), new VBox(nText,nField),new VBox(boxText,box));

    }

    private void onClick() {
        int p,n;
        try {
            p = Integer.parseInt(pField.getText());
            n = Integer.parseInt(nField.getText());
        }catch (NumberFormatException nfe){
            System.out.println("Something went wrong");
            return;
        }
        String phase = "";

        for (int i = 0; i < n; i++) {
            phase+="1";
        }
        System.out.println("Phase - " + phase +" n - "+n+" p - "+p);


        DirectoryChooser dirChooser = new DirectoryChooser();
        dirChooser.setTitle("Select a folder");
        String selectedDirPath = dirChooser.showDialog(stage).getAbsolutePath() + "/" + p + "pow" + n + ".txt";
        keyWriter(selectedDirPath, p, n, phase);


    }
    private void keyWriter(String selectedDirPath, int p, int n, String phase) {
        File outputFile = new File(selectedDirPath);
        try (PrintWriter writer = new PrintWriter(outputFile)) {
            writer.write("L = " + (int)(Math.pow(p,n) - 1) + "\t" + "Keys quantity = " + Generator.getKeysMap(p, n, phase).keySet().size());
            writer.println();
            String keys ="";
            Iterator iter = Generator.getKeysMap(p, n, phase).keySet().iterator();
            while (iter.hasNext()) {
                keys = (String) iter.next();
                writer.write(keys);
                writer.println();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
