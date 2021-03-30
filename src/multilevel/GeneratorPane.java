package multilevel;


import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;

import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class GeneratorPane extends BorderPane {


    TextField pField;
    TextField nField;

    Label status;

    Button start;

    public GeneratorPane() {
        init();
    }

    private void init() {
        HBox hBox = new HBox(5);
        hBox.setAlignment(Pos.CENTER);
        this.setTop(hBox);
        this.setStyle("-fx-background-color: lightgray;");

        Label pText = new Label("P послідовність");
        Label nText = new Label("N послідовність");

        nField = new TextField();
        nField.setPrefColumnCount(3);

        pField = new TextField();
        pField.setPrefColumnCount(3);

        status = new Label("Start generating...");

        start = new Button("Start");
        start.setOnAction(e -> {
            try {
                onClick();
            } catch (NullPointerException ex) {
                System.out.println("File not chosen");
            }
        });
        start.setPrefWidth(100.0);
        this.setCenter(start);
        this.setBottom(status);

        hBox.getChildren().addAll(new VBox(pText, pField), new VBox(nText, nField));

    }

    private void onClick() {
        int p, n;
        try {
            p = Integer.parseInt(pField.getText());
            n = Integer.parseInt(nField.getText());
        } catch (NumberFormatException nfe) {
            System.out.println("Something went wrong");
            return;
        }
        String phase = "";

        for (int i = 0; i < n; i++) {
            phase += "1";
        }
        System.out.printf("p=%s, n=%s", p, n);

        //DirectoryChooser dirChooser = new DirectoryChooser();
        //dirChooser.setTitle("Select a folder");
        //String selectedDirPath = dirChooser.showDialog(getScene().getWindow()).getAbsolutePath() + "/" + p + "pow" + n + ".txt";

        Thread t = new Thread(() -> {
            Dispatcher.keyOut(p, n);
            this.start.setDisable(false);
        });
        this.start.setDisable(true);
        t.start();
    }

    public void setStatus(String s) {
        Platform.runLater(() -> status.setText(s));
    }


    /*private void keyWriter(String selectedDirPath, int p, int n, String phase) {
        File outputFile = new File(selectedDirPath);
        try (PrintWriter writer = new PrintWriter(outputFile)) {
            writer.write("L = " + (int)(Math.pow(p,n) - 1) + "\t" + "Keys quantity = " + Generator.getKeys(p, n, phase).size());
            writer.println();
            String keys ="";
            Iterator iter = Generator.getKeys(p, n, phase).iterator();
            while (iter.hasNext()) {
                keys = (String) iter.next();
                writer.write(keys);
                writer.println();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }*/

}
