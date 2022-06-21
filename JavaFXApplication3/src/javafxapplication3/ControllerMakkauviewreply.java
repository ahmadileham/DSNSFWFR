package javafxapplication3;
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
import java.util.EmptyStackException;

public class ControllerMakkauviewreply {

    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    private Button backButton;

    @FXML
    private Button homeButton;

    @FXML
    private Button nextButton;

    @FXML
    private TextArea original;

    @FXML
    private TextArea replies;

    public void initialize(){
        original.setText(ConfessionPageJavaFX.confessions.peek().toString());

        Confession originalConfession = ConfessionPageJavaFX.confessions.peek();

        int originalID = originalConfession.getID();

        try{
            Connection connection = DriverManager.getConnection(DSNSFWFR.url, DSNSFWFR.username, DSNSFWFR.password);
            Statement myStmt = connection.createStatement();
            ResultSet myRs = myStmt.executeQuery("SELECT * FROM approve WHERE reply_ID = " + originalID);

            while(myRs.next()){
                ConfessionPageJavaFX.viewReplies.push(new Confession(myRs.getInt("confessionID"),myRs.getString("confession"),myRs.getString("date_post"),myRs.getInt("reply_ID")));
            }

        }catch(SQLException e){
            System.out.println("cant connect database");
        }

        try{
            replies.setText(ConfessionPageJavaFX.viewReplies.peek().toString());
        }catch(EmptyStackException e){
            replies.setText("No replies to this post!");
        }
    }

    @FXML
    protected void onNextButtonClick() {

        try{
            ConfessionPageJavaFX.viewRepliesTemp.push(ConfessionPageJavaFX.viewReplies.pop());
            replies.setText(ConfessionPageJavaFX.viewReplies.peek().toString());
        }catch(EmptyStackException e){
            replies.setText("No posts....");
        }
    }

    @FXML
    protected void onBackButtonClick() {

        try{
            replies.setText(ConfessionPageJavaFX.viewRepliesTemp.peek().toString());
            ConfessionPageJavaFX.viewReplies.push(ConfessionPageJavaFX.viewRepliesTemp.pop());
        }catch(EmptyStackException e){
            replies.setText("No post....");
        }

    }

    @FXML
    public void mainScene(javafx.event.ActionEvent event) throws IOException {
        ConfessionPageJavaFX.viewReplies.clear();
        ConfessionPageJavaFX.viewRepliesTemp.clear();
        try  {
            Connection connection = DriverManager.getConnection(DSNSFWFR.url, DSNSFWFR.username, DSNSFWFR.password);
            Statement myStmt = connection.createStatement();
            ResultSet myRs = myStmt.executeQuery("SELECT * FROM approve");
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
}
