package javafxapplication3;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.*;
import javafx.scene.*;

import java.io.IOException;
import java.sql.*;

public class Controller {

    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    private TextField confessionTextBox;
    @FXML
    private TextArea confessionPrint;

    public Controller() {
    }


    @FXML
    protected void onNextButtonClick() {

        confessionPrint.setText(ConfessionPageJavaFX.confessions.peek().toString());
        ConfessionPageJavaFX.confessionsTemp.push(ConfessionPageJavaFX.confessions.pop());
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

    public void submitScene(javafx.event.ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("makkau3.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void mainScene(javafx.event.ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("makkau.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void uploadConfession(ActionEvent event) {
        Confession a = new Confession(confessionTextBox.getText());
        System.out.println(confessionTextBox.getText());
        System.out.println(a.toString());
        try  {
            Connection connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/confession_page_dsnsfwfr", "root", "root");
            Statement myStmt = connection.createStatement();
            myStmt.executeUpdate("insert into not_approve(confessionID, confession, date_post) values("+a.getID()+",'"+a.getConfession()+"','"+a.getDate()+"')");


        } catch (
                SQLException e) {
            throw new IllegalStateException("Cannot connect the database!", e);
        }
    }
}
