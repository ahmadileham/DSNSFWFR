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
import javafx.scene.control.Alert;

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
        if(a.getConfession().equals("")){
            //empty.setText("DO NOT LEAVE TEXTBOX EMPTY");
        } else{
            String newConfessionDate = a.getDate();
            String newConfessionContent = a.getConfession();
            
            String[] newConfessionDateSplit = newConfessionDate.split(" ");
            String newConfessionTime = newConfessionDateSplit[1];
            String[] newConfessionTimeSplit = newConfessionTime.split(":");
            String newConfessionMinute = newConfessionTimeSplit[1];
            
            for(int i=0;i<ConfessionPageJavaFX.pending.getSize();i++){
                Confession oldConfession = ConfessionPageJavaFX.pending.getElement(i);
                String oldConfessionContent = oldConfession.getConfession();
                String oldConfessionDate = oldConfession.getDate();
                
                String[] oldConfessionDateSplit = oldConfessionDate.split(" ");
                String oldConfessionTime = oldConfessionDateSplit[1];
                String[] oldConfessionTimeSplit = oldConfessionTime.split(":");
                String oldConfessionMinute = oldConfessionTimeSplit[1];
                
                int oldMinute = Integer.parseInt(oldConfessionMinute);
                int newMinute = Integer.parseInt(newConfessionMinute);
                
                int minuteDifference = Math.abs(oldMinute-newMinute);
                double similarity = StringSimilarity.similarity(oldConfessionContent, newConfessionContent);
                
                
                if(minuteDifference<=3&&similarity>0){
                    Alert spamAlert = new Alert(Alert.AlertType.WARNING);
                    spamAlert.setTitle("SPAM CONTENT ALERT");
                    spamAlert.setContentText("JANGAN SPAM LA BABI");
                    spamAlert.showAndWait();
                    root = FXMLLoader.load(getClass().getResource("makkau.fxml"));
                    stage = (Stage)((Node)event.getSource()).getScene().getWindow();
                    scene = new Scene(root);
                    stage.setScene(scene);
                    stage.show();
                    return;
                }
            }
            
                    ConfessionPageJavaFX.pending.enqueue(a);
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
