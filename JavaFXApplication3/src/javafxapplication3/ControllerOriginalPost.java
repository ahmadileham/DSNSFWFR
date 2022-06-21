package javafxapplication3;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.*;

public class ControllerOriginalPost {

    private Stage stage;
    private Scene scene;
    private Parent root;


    @FXML
    private Button homeButton;

    @FXML
    private TextArea originalPost;

    public void initialize(){
        try{
            Confession replyPost = ConfessionPageJavaFX.confessions.peek();
            int replyPostID = replyPost.getReply_ID();
            Connection connection = DriverManager.getConnection(DSNSFWFR.url, DSNSFWFR.username, DSNSFWFR.password);
            Statement myStmt = connection.createStatement();
            ResultSet myRs = myStmt.executeQuery("SELECT * FROM approve WHERE confessionID = " + replyPostID);
            myRs.next();
            Confession toPost = new Confession(myRs.getInt("confessionID"),myRs.getString("confession"),myRs.getString("date_post"),myRs.getInt("reply_ID"));

            if(toPost.equals(null)||toPost.getID()==0){
                originalPost.setText("No posts...");
            }

            else{
                originalPost.setText(toPost.toString());
            }
        }catch(SQLException e){
            System.out.println(e);
            originalPost.setText("ada problem kat daatbase dop");
        }
    }

    @FXML
    void mainScene(ActionEvent event) throws IOException {

        try  {
            Connection connection = DriverManager.getConnection(DSNSFWFR.url, DSNSFWFR.username, DSNSFWFR.password);
            Statement myStmt = connection.createStatement();
            ResultSet myRs = myStmt.executeQuery("SELECT * FROM approve");
            ConfessionPageJavaFX.confessions.clear();
            ConfessionPageJavaFX.confessionsTemp.clear();
            while (myRs.next()) {
                ConfessionPageJavaFX.confessions.push(new Confession(myRs.getInt("confessionID"),myRs.getString("confession"),myRs.getString("date_post"), myRs.getInt("reply_ID")));// to fetch data from database
            }
            myStmt.close();
            myRs.close();

        } catch (
                SQLException e) {
            throw new IllegalStateException("Cannot connect the database!", e);
        }

        root = FXMLLoader.load(getClass().getResource("mainPage.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
