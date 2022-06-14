/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMain.java to edit this template
 */
package javafxapplication3;

//import java.awt.Image;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.*;
import java.util.*;
/**
 * @author DSNSFWFR ( ILHAM , BO , CAPANG , SHAFIQ)
 **/
public class JavaFXApplication3 extends Application {

    public static Stack<Confession> confessions = new Stack<>();
    public static Stack<Confession> confessionsTemp = new Stack<>();
    @Override
    public void start(Stage stage) throws Exception {
        try  {
            Connection connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/confession_page_dsnsfwfr", "root", "18102002");
            Statement myStmt = connection.createStatement();
            ResultSet myRs = myStmt.executeQuery("SELECT * FROM not_approve");

            while (myRs.next()) {
                confessions.push(new Confession(myRs.getString("confession"),myRs.getDate("date_post")));
            }
        } catch (
                SQLException e) {
            throw new IllegalStateException("Cannot connect the database!", e);
        }
//        confessions.push("i have to confess... i am gay");
//        confessions.push("happy birthday ilham");
//        confessions.push("help me");
//        confessions.push("i love capang");
//        confessions.push("i hate black people");
//        confessions.push("i hate chinese people");
//        confessions.push("i hate ilham people");
//        confessions.push("i hate BO people");
        FXMLLoader fxmlLoader = new FXMLLoader(JavaFXApplication3.class.getResource("makkau.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("HELLO MAK ILHAM!");
        stage.setScene(scene);
        stage.show();
        
    }

    // boo babid aadsdsdadsada
    public static void main(String[] args) {
        launch(args);
    }
    
}
