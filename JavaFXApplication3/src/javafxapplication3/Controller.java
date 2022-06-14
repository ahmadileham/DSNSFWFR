package javafxapplication3;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.stage.*;
import javafx.scene.*;

import java.awt.event.ActionEvent;
import java.io.IOException;
import java.util.*;
public class Controller {

    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    private Label welcomeText;
    @FXML
    private TextArea confessionPrint;

    public Controller() {
    }


    @FXML
    protected void onNextButtonClick() {

        confessionPrint.setText(JavaFXApplication3.confessions.peek().toString());
        JavaFXApplication3.confessionsTemp.push(JavaFXApplication3.confessions.pop());
    }

    @FXML
    protected void onBackButtonClick() {

        confessionPrint.setText(JavaFXApplication3.confessionsTemp.peek().toString());
        JavaFXApplication3.confessions.push(JavaFXApplication3.confessionsTemp.pop());

    }

    @FXML


    public void switchScene(javafx.event.ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("makkau2.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
