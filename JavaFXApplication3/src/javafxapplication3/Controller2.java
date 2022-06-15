package javafxapplication3;

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.*;
import javafx.scene.*;

import java.io.IOException;
import java.sql.*;


public class Controller2 {

    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    private TextArea toReply;

    @FXML
    private TextField messageReply;

    public void initialize(){
        toReply.setText(ConfessionPageJavaFX.confessions.peek().toString());
    }

    public void mainScene(javafx.event.ActionEvent event) throws IOException {
        try  {
            Connection connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/confession_page_dsnsfwfr", "root", "18102002");
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

    public void uploadConfession(ActionEvent event) throws IOException {
        int b = ConfessionPageJavaFX.confessions.peek().getID();
        Confession a = new Confession(messageReply.getText());
        int ID = 0;
        if (a.getConfession().equals("")) {
//            empty.setText("DO NOT LEAVE TEXTBOX EMPTY");
        } else {
            try {
                Connection connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/confession_page_dsnsfwfr", "root", "root");
                Statement myStmt = connection.createStatement();
                Statement myStmt2 = connection.createStatement();
                Statement myStmt3 = connection.createStatement();
                ResultSet myRs = myStmt2.executeQuery("SELECT * FROM not_approve ORDER BY confessionID DESC LIMIT 1");

                while (myRs.next()) {
                    ID = myRs.getInt("confessionID") + 1;
                }


                myStmt.executeUpdate("insert into not_approve(confessionID, confession, date_post, reply_ID) values(" + ID + ",'" + a.getConfession() + "','" + a.getDate() + "',"+b+")");


                myRs = myStmt3.executeQuery("SELECT * FROM not_approve");

                while (myRs.next()) {
                    ConfessionPageJavaFX.confessions.push(new Confession(myRs.getInt("confessionID"), myRs.getString("confession"), myRs.getString("date_post")));// to fetch data from database
                }

                myRs.close();
                myStmt.close();
                myStmt2.close();
                myStmt3.close();

            } catch (SQLException e) {
                throw new IllegalStateException("Cannot connect the database!", e);
            }

            root = FXMLLoader.load(getClass().getResource("makkau.fxml"));
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }
    }
}


