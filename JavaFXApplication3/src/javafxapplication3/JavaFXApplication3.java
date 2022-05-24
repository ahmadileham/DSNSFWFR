/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMain.java to edit this template
 */
package javafxapplication3;

//import java.awt.Image;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

/**
 *
 * @author Ilham
 */
public class JavaFXApplication3 extends Application {
    
    @Override
    public void start(Stage stage)  {
        Group root = new Group();
        Scene scene = new Scene(root,Color.BLUEVIOLET);
        
        //Image icon = new Image("C:\\Users\\Ilham\\Desktop\\boco\\DSCF5079.jpg");
        
        //stage.getIcons().add(icon);
        stage.setTitle("MAK KAU");
        
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
