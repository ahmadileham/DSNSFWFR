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
import java.util.concurrent.TimeUnit;

import javafx.scene.image.Image;
/**
 * @author DSNSFWFR ( ILHAM , BO , CAPANG , SHAFIQ)
 **/
public class ConfessionPageJavaFX extends Application {

    public static Stack<Confession> confessionsSearch = new Stack<>();
    public static Stack<Confession> confessionsSearchTemp = new Stack<>();
    public static Stack<Confession> confessions = new Stack<>();
    public static Stack<Confession> confessionsTemp = new Stack<>();
    public static Stack<Confession> viewReplies = new Stack<>();
    public static Stack<Confession> viewRepliesTemp = new Stack<>();
    public static Queue<Confession> notApprove = new Queue<>(); // these for not approve
    public static Queue<Confession> notApproveTemp = new Queue<>();
    public static Queue<Confession> pending = new Queue<>();
    public static Timer time = new Timer(); // Instantiate Timer Object
    public static CustomTask a = new CustomTask();


    @Override
    public void start(Stage stage) throws Exception {
        try  {
            Connection connection = DriverManager.getConnection(DSNSFWFR.url, DSNSFWFR.username, DSNSFWFR.password);
            Statement myStmt = connection.createStatement();
            Statement myStmt2 = connection.createStatement();
            ResultSet myRs = myStmt.executeQuery("SELECT * FROM approve");

            while (myRs.next()) {
                confessions.push(new Confession(myRs.getInt("confessionID"),myRs.getString("confession"),myRs.getString("date_post"),myRs.getInt("reply_ID")));// to fetch data from database
            }
        } catch (SQLException e) {
            throw new IllegalStateException("Cannot connect the database!", e);
        }


        FXMLLoader fxmlLoader = new FXMLLoader(ConfessionPageJavaFX.class.getResource("mainPage.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        Image icon = new Image("javafxapplication3\\shafiq.png");
        stage.getIcons().add(icon);
        stage.setTitle("DSNSFWFR CONFESSION PAGE");
        stage.setScene(scene);
        stage.show();



    }

    public static void main(String[] args) throws InterruptedException {
        time.schedule(a, 0, TimeUnit.SECONDS.toMillis(1));
        launch(args);
    }
    
    

}
