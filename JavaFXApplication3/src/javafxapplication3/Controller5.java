package javafxapplication3;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.*;

public class Controller5 {
    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    private TextField usernameField;

    @FXML
    private PasswordField passField;

    @FXML
    private Label warningLabel;

    public void adminScene(ActionEvent event) throws IOException {
        String a = usernameField.getText();
        String b = passField.getText();

        int ID = 0;
        if (a.equals("") || b.equals("")) {
            warningLabel.setText("mak ilam");
        } else {
            try {
                Connection connection = DriverManager.getConnection(DSNSFWFR.url, DSNSFWFR.username, DSNSFWFR.password);
                Statement myStmt = connection.createStatement();
                Statement myStmt2 = connection.createStatement();
                Statement myStmt3 = connection.createStatement();
                ResultSet myRs = myStmt2.executeQuery("SELECT * FROM admin");


                while (myRs.next()) {
                    if(myRs.getString("username").equals(a)){
                        if(myRs.getString("password").equals(b)){
                            root = FXMLLoader.load(getClass().getResource("makkau6.fxml"));
                            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                            scene = new Scene(root);
                            stage.setScene(scene);
                            stage.show();
                        } else {
                            warningLabel.setText("Wrong password!");
                        }
                    } else {
                        warningLabel.setText("Wrong username!");
                    }
                }

                myRs.close();
                myStmt.close();
                myStmt2.close();
                myStmt3.close();

            } catch (SQLException e) {
                throw new IllegalStateException("Cannot connect the database!", e);
            }


        }
    }

    public void mainScene(javafx.event.ActionEvent event) throws IOException {
        try  {
            Connection connection = DriverManager.getConnection(DSNSFWFR.url, DSNSFWFR.username, DSNSFWFR.password);
            Statement myStmt = connection.createStatement();
            ResultSet myRs = myStmt.executeQuery("SELECT * FROM approve");
            ConfessionPageJavaFX.confessions.clear();
            while (myRs.next()) {
                ConfessionPageJavaFX.confessions.push(new Confession(myRs.getInt("confessionID"),myRs.getString("confession"),myRs.getString("date_post"),myRs.getInt("reply_ID")));// to fetch data from database
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
