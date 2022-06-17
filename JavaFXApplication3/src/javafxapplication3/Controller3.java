package javafxapplication3;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.*;

public class Controller3 {

    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    private Label empty;

    @FXML
    private TextField confessionTextBox;

    public void uploadConfession(ActionEvent event) throws IOException {
        Confession a = new Confession(confessionTextBox.getText());
        int ID = 0;
        if(a.getConfession().equals("")){
            //empty.setText("DO NOT LEAVE TEXTBOX EMPTY");
        } else{
            try  {
                Connection connection = DriverManager.getConnection(DSNSFWFR.url, DSNSFWFR.username, DSNSFWFR.password);
                Statement myStmt = connection.createStatement();
                Statement myStmt2 = connection.createStatement();
                Statement myStmt3 = connection.createStatement();
                ResultSet myRs = myStmt2.executeQuery("SELECT * FROM not_approve ORDER BY confessionID DESC LIMIT 1");

                while(myRs.next()) {
                    ID = myRs.getInt("confessionID") + 1;
                }

                myStmt.executeUpdate("insert into not_approve(confessionID, confession, date_post) values("+ID+",'"+a.getConfession()+"','"+a.getDate()+"')");


                myRs = myStmt3.executeQuery("SELECT * FROM not_approve");

//                while (myRs.next()) {
//                    ConfessionPageJavaFX.notApprove.enqueue(new Confession(myRs.getInt("confessionID"),myRs.getString("confession"),myRs.getString("date_post"), myRs.getInt("reply_ID")));// to fetch data from database
//                }

                myRs.close();
                myStmt.close();
                myStmt2.close();
                myStmt3.close();

            } catch (SQLException e) {
                throw new IllegalStateException("Cannot connect the database!", e);
            }

            root = FXMLLoader.load(getClass().getResource("makkau.fxml"));
            stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }

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
}
