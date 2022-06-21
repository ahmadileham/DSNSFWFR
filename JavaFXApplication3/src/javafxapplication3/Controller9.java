package javafxapplication3;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.*;
import java.util.EmptyStackException;

public class Controller9 {

    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    private TextArea displayArea;

    public void initialize(){
        try  {
            Connection connection = DriverManager.getConnection(DSNSFWFR.url, DSNSFWFR.username, DSNSFWFR.password);
            Statement myStmt = connection.createStatement();
            Statement myStmt2 = connection.createStatement();
            ResultSet myRs = myStmt.executeQuery("SELECT * FROM approve");

            ConfessionPageJavaFX.confessions.clear();
            ConfessionPageJavaFX.confessionsTemp.clear();

            while (myRs.next()) {
                ConfessionPageJavaFX.confessions.push(new Confession(myRs.getInt("confessionID"),myRs.getString("confession"),myRs.getString("date_post"),myRs.getInt("reply_ID")));// to fetch data from database
            }
        } catch (SQLException e) {
            throw new IllegalStateException("Cannot connect the database!", e);
        }
        displayArea.setText(ConfessionPageJavaFX.confessions.peek().toString());
    }
    @FXML

    protected void onNextButtonClick() {
        try{
            ConfessionPageJavaFX.confessionsTemp.push(ConfessionPageJavaFX.confessions.pop());
            displayArea.setText(ConfessionPageJavaFX.confessions.peek().toString());
        }catch(EmptyStackException e){
            displayArea.setText("No posts...");
        }
    }

    @FXML
    protected void onBackButtonClick() {

        try{
            displayArea.setText(ConfessionPageJavaFX.confessionsTemp.peek().toString());
            ConfessionPageJavaFX.confessions.push(ConfessionPageJavaFX.confessionsTemp.pop());
        }catch(EmptyStackException e){
            displayArea.setText("No posts....");
        }

    }

    public void adminLoginScene(javafx.event.ActionEvent event) throws IOException {

        root = FXMLLoader.load(getClass().getResource("makkau6.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }

    public void deleteButton(ActionEvent event) throws IOException {

        Confession a = ConfessionPageJavaFX.confessions.pop();
        deleteThread(a);
        try  {
            Connection connection = DriverManager.getConnection(DSNSFWFR.url, DSNSFWFR.username, DSNSFWFR.password);
            Statement myStmt = connection.createStatement();
            Statement myStmt2 = connection.createStatement();
            ResultSet myRs = myStmt.executeQuery("SELECT * FROM approve");
            ConfessionPageJavaFX.confessions.clear();
            ConfessionPageJavaFX.confessionsTemp.clear();
            while (myRs.next()) {
                ConfessionPageJavaFX.confessions.push(new Confession(myRs.getInt("confessionID"),myRs.getString("confession"),myRs.getString("date_post"),myRs.getInt("reply_ID")));// to fetch data from database
            }
        } catch (SQLException e) {
            throw new IllegalStateException("Cannot connect the database!", e);
        }
        displayArea.setText(ConfessionPageJavaFX.confessions.peek().toString());

    }
// method for batch removal
    public void deleteThread(Confession a){
        try  {
            Connection connection = DriverManager.getConnection(DSNSFWFR.url, DSNSFWFR.username, DSNSFWFR.password);
            Statement myStmt = connection.createStatement();
            Statement myStmt2 = connection.createStatement();
            ResultSet myRs = myStmt.executeQuery("SELECT * FROM approve");
            while (myRs.next()) {
                if (myRs.getInt("reply_ID")==a.getID()){
                    deleteThread(new Confession(myRs.getInt("confessionID"),myRs.getString("confession"),myRs.getString("date_post"),myRs.getInt("reply_ID")));
                }
            }
            myStmt2.executeUpdate("DELETE from approve where confessionID = "+a.getID());
        } catch (SQLException e) {
            throw new IllegalStateException("Cannot connect the database!", e);
        }


        return;
    }
}
