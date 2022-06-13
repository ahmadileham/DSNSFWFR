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
import java.util.*;
/**
 *
 * @author DSNSFWFR
 */
public class JavaFXApplication3 extends Application {

    public static Stack<String> confessions = new Stack<>();
    public static Stack<String> confessionsTemp = new Stack<>();
    @Override
    public void start(Stage stage) throws Exception {
        confessions.push("i have to confess... i am gay");
        confessions.push("happy birthday ilham");
        confessions.push("help me");
        confessions.push("i love capang");
        confessions.push("i hate black people");
        FXMLLoader fxmlLoader = new FXMLLoader(JavaFXApplication3.class.getResource("makkau.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("HELLO MAK ILHAM!");
        stage.setScene(scene);
        stage.show();
        
    }

    // boo babid aadsdsdadsada
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
