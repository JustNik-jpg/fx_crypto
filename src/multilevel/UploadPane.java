package multilevel;

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static multilevel.Generator.generatePeriod;


public class UploadPane extends VBox implements EventHandler {
    TextField pField;
    TextField nField;
    TextField keyField;
    TextField phaseField;
    Label status;

    public UploadPane() {
        setSpacing(10);
        setStyle("-fx-background-color: darkgrey;");

        setAlignment(Pos.TOP_CENTER);
        GridPane textFields = new GridPane();
        textFields.setAlignment(Pos.CENTER);

        status = new Label("Ready for work");

        pField = new TextField();
        pField.setPromptText("Основа P");
        pField.setPrefColumnCount(12);

        nField = new TextField();
        nField.setPromptText("Степінь N");
        nField.setPrefColumnCount(12);

        keyField = new TextField();
        keyField.setPromptText("Номер ключа");
        keyField.setPrefColumnCount(12);

        phaseField = new TextField();
        phaseField.setPromptText("Фаза");
        phaseField.setPrefColumnCount(12);

        textFields.add(pField, 1, 1);
        textFields.add(nField, 1, 2);
        textFields.add(keyField, 2, 1);
        textFields.add(phaseField, 2, 2);

        Button generate = new Button("Сгенерувати");
        generate.setOnAction(this);

        getChildren().addAll(textFields, generate, status);


    }

    @Override
    public void handle(Event event) {

        int p;
        int n;
        int keyNumber;
        String phase = phaseField.getText();

        try {
            keyNumber = Integer.parseInt(keyField.getText());
            p = Integer.parseInt(pField.getText());
            n = Integer.parseInt(nField.getText());
        } catch (NumberFormatException nfe) {
            status.setText("Wrong Input");
            return;
        }
        if (phase.length() != n) {
            status.setText("Invalid phase");
            return;
        }

        String line;
        if (keyNumber > 0) {
            try {
                line = Files.readAllLines(Paths.get("C://Users/JustNik/Desktop/"+p+"pow"+n+".txt")).get(keyNumber);
            } catch (IOException e) {
                status.setText("Invalid file path");
                return;
            } catch (IndexOutOfBoundsException ioobe) {
                status.setText("Invalid key number");
                return;
            }
        } else {
            status.setText("Wrong Key number");
            return;
        }


        String s = generatePeriod(p, n, line, phase);
        System.out.println(s);
        System.out.println(s.length());


        status.setText("Period generated.");
    }
}
