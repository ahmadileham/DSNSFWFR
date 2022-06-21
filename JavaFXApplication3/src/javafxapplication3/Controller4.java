package javafxapplication3;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.*;
import java.util.EmptyStackException;

import javafx.scene.image.Image;
import javafx.scene.layout.Background;

public class Controller4 {

    private Stage stage;
    private Scene scene;
    private Parent root;


    @FXML
    private ChoiceBox<String>  choicesBox;

    @FXML
    private TextField searchText;

    @FXML
    private TextArea searchPrint;

    private String[] choices = {"Keyword","Confession ID","Date","Date & Time","?"};

    public void initialize(){
        choicesBox.getItems().addAll(choices);
    }

    public void mainScene(javafx.event.ActionEvent event) throws IOException {
        try  {
            Connection connection = DriverManager.getConnection(DSNSFWFR.url, DSNSFWFR.username, DSNSFWFR.password);
            Statement myStmt = connection.createStatement();
            ResultSet myRs = myStmt.executeQuery("SELECT * FROM approve");
            ConfessionPageJavaFX.confessions.clear();
            while (myRs.next()) {
                ConfessionPageJavaFX.confessions.push(new Confession(myRs.getInt("confessionID"),myRs.getString("confession"),myRs.getString("date_post"), myRs.getInt("reply_ID")));// to fetch data from database
            }
            myStmt.close();
            myRs.close();

        } catch (
                SQLException e) {
            throw new IllegalStateException("Cannot connect the database!", e);
        }
        root = FXMLLoader.load(getClass().getResource("makkau.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }


    public void searchFunction(ActionEvent event) throws IOException {
        String a = choicesBox.getValue();
        String choice = "";
        ConfessionPageJavaFX.confessionsSearch.clear();
        ConfessionPageJavaFX.confessionsSearchTemp.clear();

        try  {
            Connection connection = DriverManager.getConnection(DSNSFWFR.url, DSNSFWFR.username, DSNSFWFR.password);
            Statement myStmt = connection.createStatement();
            if(a.equals("Keyword")){
                choice = "SELECT * FROM approve WHERE confession LIKE '%"+searchText.getText()+"%'";
            } else if(a.equals("Confession ID")){
                choice = "SELECT * FROM approve WHERE confessionID = "+searchText.getText();
            } else if(a.equals("Date")){
                choice = "SELECT * FROM approve WHERE date_post LIKE '%"+searchText.getText()+"%'";
            } else if(a.equals("Date & Time")){
                choice = "SELECT * FROM approve WHERE date_post LIKE '%"+searchText.getText()+"%'";
            } else if(a.equals("?")){
                root = FXMLLoader.load(getClass().getResource("makkaubabi.fxml"));
                stage = (Stage)((Node)event.getSource()).getScene().getWindow();
                scene = new Scene(root);
                stage.setScene(scene);
                stage.show();
                return;
            }

            ResultSet myRs = myStmt.executeQuery(choice);


            while (myRs.next()) {
                ConfessionPageJavaFX.confessionsSearch.push(new Confession(myRs.getInt("confessionID"),myRs.getString("confession"),myRs.getString("date_post"), myRs.getInt("reply_ID")));// to fetch data from database
            }

            try{
                searchPrint.setText(ConfessionPageJavaFX.confessionsSearch.peek().toString());
            }catch(EmptyStackException e){
                searchPrint.setText("There's nothing here...");
            }

        } catch (
                SQLException e) {
            throw new IllegalStateException("Cannot connect the database!", e);
        }
    }

    @FXML
    protected void onNextButtonClick() {

        try{
            ConfessionPageJavaFX.confessionsSearchTemp.push(ConfessionPageJavaFX.confessionsSearch.pop());
            searchPrint.setText(ConfessionPageJavaFX.confessionsSearch.peek().toString());
        }catch(EmptyStackException e){
            searchPrint.setText("There's nothing here...");
        }
    }

    @FXML
    protected void onBackButtonClick() {

        try{
            searchPrint.setText(ConfessionPageJavaFX.confessionsSearchTemp.peek().toString());
            ConfessionPageJavaFX.confessionsSearch.push(ConfessionPageJavaFX.confessionsSearchTemp.pop());
        }catch(EmptyStackException e){
            searchPrint.setText("There's nothing here...");
        }

    }
}
