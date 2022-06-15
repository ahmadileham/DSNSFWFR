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
import org.w3c.dom.Text;

import java.io.IOException;
import java.sql.*;

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

    private String[] choices = {"Keyword","Confession ID","Date","Date & Time","mak ilham"};

    public void initialize(){
        choicesBox.getItems().addAll(choices);
    }

    public void mainScene(javafx.event.ActionEvent event) throws IOException {
        try  {
            Connection connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/confession_page_dsnsfwfr", "root", "root");
            Statement myStmt = connection.createStatement();
            ResultSet myRs = myStmt.executeQuery("SELECT * FROM not_approve");

            while (myRs.next()) {
                ConfessionPageJavaFX.confessions.push(new Confession(myRs.getInt("confessionID"),myRs.getString("confession"),myRs.getString("date_post")));// to fetch data from database
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


    public void searchFunction(ActionEvent event) {
        String a = choicesBox.getValue();
        String choice = "";
        ConfessionPageJavaFX.confessionsSearch.clear();
        ConfessionPageJavaFX.confessionsSearchTemp.clear();

        try  {
            Connection connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/confession_page_dsnsfwfr", "root", "root");
            Statement myStmt = connection.createStatement();
            if(a.equals("Keyword")){
                choice = "SELECT * FROM not_approve WHERE confession LIKE '%"+searchText.getText()+"%'";
            } else if(a.equals("Confession ID")){
                choice = "SELECT * FROM not_approve WHERE confessionID = "+searchText.getText();
            } else if(a.equals("Date")){
                choice = "test";
            } else if(a.equals("Date & Time")){
                choice = "test";
            }

            ResultSet myRs = myStmt.executeQuery(choice);


            while (myRs.next()) {
                ConfessionPageJavaFX.confessionsSearch.push(new Confession(myRs.getInt("confessionID"),myRs.getString("confession"),myRs.getString("date_post")));// to fetch data from database
            }

            searchPrint.setText(ConfessionPageJavaFX.confessionsSearch.peek().toString());

        } catch (
                SQLException e) {
            throw new IllegalStateException("Cannot connect the database!", e);
        }
    }

    @FXML
    protected void onNextButtonClick() {

        ConfessionPageJavaFX.confessionsSearchTemp.push(ConfessionPageJavaFX.confessionsSearch.pop());
        searchPrint.setText(ConfessionPageJavaFX.confessionsSearch.peek().toString());
    }

    @FXML
    protected void onBackButtonClick() {

        searchPrint.setText(ConfessionPageJavaFX.confessionsSearchTemp.peek().toString());
        ConfessionPageJavaFX.confessionsSearch.push(ConfessionPageJavaFX.confessionsSearchTemp.pop());

    }
}
