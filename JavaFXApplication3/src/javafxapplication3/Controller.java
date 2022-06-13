package javafxapplication3;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import java.util.*;
public class Controller {
    @FXML
    private Label welcomeText;
    @FXML
    private TextArea confessionPrint;


    @FXML
    protected void onNextButtonClick() {

        confessionPrint.setText(JavaFXApplication3.confessions.peek());
        JavaFXApplication3.confessionsTemp.push(JavaFXApplication3.confessions.pop());
    }

    @FXML
    protected void onBackButtonClick() {

        confessionPrint.setText(JavaFXApplication3.confessionsTemp.peek());
        JavaFXApplication3.confessions.push(JavaFXApplication3.confessionsTemp.pop());

    }
}
