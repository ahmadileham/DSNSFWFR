package javafxapplication3;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.*;

public class Controller6 {
    private Stage stage;
    private Scene scene;
    private Parent root;

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
}
