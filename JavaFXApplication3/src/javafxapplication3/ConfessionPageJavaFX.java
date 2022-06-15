/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMain.java to edit this template
 */
package javafxapplication3;

//import java.awt.Image;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.sql.*;
import java.util.*;
/**
 * @author DSNSFWFR ( ILHAM , BO , CAPANG , SHAFIQ)
 **/
public class ConfessionPageJavaFX extends Application {

    public static Stack<Confession> confessions = new Stack<>();
    public static Stack<Confession> confessionsTemp = new Stack<>();
    @Override
    public void start(Stage stage) throws Exception {
        try  {
            Connection connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/confession_page_dsnsfwfr", "root", "root");
            Statement myStmt = connection.createStatement();
            ResultSet myRs = myStmt.executeQuery("SELECT * FROM not_approve");

            while (myRs.next()) {
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
        FXMLLoader fxmlLoader = new FXMLLoader(ConfessionPageJavaFX.class.getResource("makkau.fxml"));
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
