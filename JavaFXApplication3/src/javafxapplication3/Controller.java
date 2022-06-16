package javafxapplication3;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.*;
import javafx.scene.*;

import java.io.IOException;
import java.sql.*;
/**
 * @author DSNSFWFR ( ILHAM , BO , CAPANG , SHAFIQ)
 **/
public class Controller {

    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    private Label empty;

    @FXML
    private TextField confessionTextBox;
    @FXML
    private TextArea confessionPrint;


    @FXML
    private TextField messageReply;

    public Controller() {
    }

    public void initialize(){
        confessionPrint.setText(ConfessionPageJavaFX.confessions.peek().toString());
    }

    @FXML
    protected void onNextButtonClick() {

        ConfessionPageJavaFX.confessionsTemp.push(ConfessionPageJavaFX.confessions.pop());
        confessionPrint.setText(ConfessionPageJavaFX.confessions.peek().toString());
    }

    @FXML
    protected void onBackButtonClick() {

        confessionPrint.setText(ConfessionPageJavaFX.confessionsTemp.peek().toString());
        ConfessionPageJavaFX.confessions.push(ConfessionPageJavaFX.confessionsTemp.pop());

    }

    @FXML


    public void replyScene(javafx.event.ActionEvent event) throws IOException {


        root = FXMLLoader.load(getClass().getResource("makkau2.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();



    }

    public void adminLoginScene(javafx.event.ActionEvent event) throws IOException {


        root = FXMLLoader.load(getClass().getResource("makkau5.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();



    }

    public void submitScene(javafx.event.ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("makkau3.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void searchScene(javafx.event.ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("makkau4.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }


}
