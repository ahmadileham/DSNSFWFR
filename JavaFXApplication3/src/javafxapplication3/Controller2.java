package javafxapplication3;

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.TextField;
import javafx.stage.*;
import javafx.scene.*;

import java.io.IOException;
import java.sql.*;
import java.util.EmptyStackException;
import javafx.scene.control.Alert;


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
            Connection connection = DriverManager.getConnection(DSNSFWFR.url, DSNSFWFR.username, DSNSFWFR.password);
            Statement myStmt = connection.createStatement();
            ResultSet myRs = myStmt.executeQuery("SELECT * FROM not_approve");

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

    public void uploadConfession(ActionEvent event) throws IOException {
        int b = ConfessionPageJavaFX.confessions.peek().getID();
        Confession a = new Confession(messageReply.getText());
        a.setReply_ID(b);
        int ID = 0;
        if (a.getConfession().equals("")) {
//            empty.setText("DO NOT LEAVE TEXTBOX EMPTY");
        } else {
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
}


