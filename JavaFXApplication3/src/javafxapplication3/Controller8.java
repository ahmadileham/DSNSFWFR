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

public class Controller8 {

    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    private TextField adminUsername;

    @FXML
    private TextField adminPassword;

    @FXML
    private Label warningLabel;

    public void registerAdmin(ActionEvent event) throws IOException {
        String a = adminUsername.getText();
        String b = adminPassword.getText();
        boolean alreadyExists = false;
        if (a.equals("") || b.equals("")) {
            warningLabel.setText("DO NOT LEAVE TEXTBOX EMPTY");
        } else {
            try {
                Connection connection = DriverManager.getConnection(DSNSFWFR.url, DSNSFWFR.username, DSNSFWFR.password);
                Statement myStmt = connection.createStatement();
                Statement myStmt2 = connection.createStatement();
                ResultSet myRs = myStmt2.executeQuery("SELECT * FROM admin");

                while (myRs.next()) {
                    if(a.equals(myRs.getString("username"))){
                        warningLabel.setText("Username already exists in database!");
                        alreadyExists = true;
                        break;
                    }
                }

                if(!alreadyExists) {
                    warningLabel.setText("");
                    myStmt.executeUpdate("insert into admin(username, password) values('" + a + "','" + b + "')");
                }

                myRs.close();
                myStmt.close();
                myStmt2.close();

            } catch (SQLException e) {
                throw new IllegalStateException("Cannot connect the database!", e);
            }

        }
    }

    public void adminLoginScene(javafx.event.ActionEvent event) throws IOException {


        root = FXMLLoader.load(getClass().getResource("makkau6.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();



    }
}
