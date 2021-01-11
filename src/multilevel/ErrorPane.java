package multilevel;

import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class ErrorPane{
    public static void setErrorScene(Stage stage, String errorName) {
        Label errorLabel = new Label(errorName);

        StackPane errorLayout = new StackPane();
        errorLayout.getChildren().add(errorLabel);

        Scene errorScene = new Scene(errorLayout, 230, 100);

        Stage newWindow = new Stage();
        newWindow.setTitle("Error");
        newWindow.setScene(errorScene);

        newWindow.setX(stage.getX() + 200);
        newWindow.setY(stage.getY() + 100);

        newWindow.show();
    }

}
