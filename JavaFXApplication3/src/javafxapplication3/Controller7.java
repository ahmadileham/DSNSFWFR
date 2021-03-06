package javafxapplication3;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.*;

public class Controller7 {
    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    private TextArea messageArea;

    public void initialize(){
        try  {
            Connection connection = DriverManager.getConnection(DSNSFWFR.url, DSNSFWFR.username, DSNSFWFR.password);
            Statement myStmt2 = connection.createStatement();
            ResultSet myRs2 = myStmt2.executeQuery("SELECT * FROM not_approve");
            ConfessionPageJavaFX.notApprove.clear();
            while (myRs2.next()) {
                ConfessionPageJavaFX.notApprove.enqueue(new Confession(myRs2.getInt("confessionID"),myRs2.getString("confession"),myRs2.getString("date_post"),myRs2.getInt("reply_ID")));// to fetch data from database
            }
        } catch (SQLException e) {
            throw new IllegalStateException("Cannot connect the database!", e);
        }
        if(!ConfessionPageJavaFX.notApprove.isEmpty()) {
            messageArea.setText(ConfessionPageJavaFX.notApprove.peek().toString());
        } else {
            messageArea.setText("No new posts..");
        }
    }

    public void adminLoginScene(javafx.event.ActionEvent event) throws IOException {


        root = FXMLLoader.load(getClass().getResource("mainAdminPage.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();



    }

    @FXML
    protected void approveMessage() {


        if(!ConfessionPageJavaFX.notApprove.isEmpty()){
            Confession a = ConfessionPageJavaFX.notApprove.dequeue();
            ConfessionPageJavaFX.notApproveTemp.enqueue(a);
            try  {
                Connection connection = DriverManager.getConnection(DSNSFWFR.url, DSNSFWFR.username, DSNSFWFR.password);
                Statement myStmt = connection.createStatement();
                Statement myStmt2 = connection.createStatement();
                Statement myStmt3 = connection.createStatement();
                int ID = 0;
                ResultSet myRs = myStmt3.executeQuery("SELECT * FROM approve ORDER BY confessionID DESC LIMIT 1");

                while(myRs.next()) {
                    ID = myRs.getInt("confessionID") + 1;
                }

                myStmt.executeUpdate("insert into approve(confessionID, confession, date_post, reply_ID) values(" + ID + ",'" + a.getConfession() + "','" + a.getDate() + "',"+a.getReply_ID()+")");
                myStmt2.executeUpdate("DELETE FROM not_approve WHERE confessionID = "+a.getID());

                myStmt.close();
                myStmt2.close();
                if (!ConfessionPageJavaFX.notApprove.isEmpty()){
                    messageArea.setText(ConfessionPageJavaFX.notApprove.peek().toString());
                }else {
                    messageArea.setText("No new posts..");
                }

            } catch (SQLException e) {
                throw new IllegalStateException("Cannot connect the database!", e);
            }
        }else {
            messageArea.setText("No new posts..");
            return;
        }
    }

    @FXML
    protected void rejectMessage() {

        if(!ConfessionPageJavaFX.notApprove.isEmpty()){
            Confession a = ConfessionPageJavaFX.notApprove.dequeue();
            ConfessionPageJavaFX.notApproveTemp.enqueue(a);
            try  {
                Connection connection = DriverManager.getConnection(DSNSFWFR.url, DSNSFWFR.username, DSNSFWFR.password);
                Statement myStmt2 = connection.createStatement();

                myStmt2.executeUpdate("DELETE FROM not_approve WHERE confessionID = "+a.getID());

                myStmt2.close();
                if (!ConfessionPageJavaFX.notApprove.isEmpty()){
                    messageArea.setText(ConfessionPageJavaFX.notApprove.peek().toString());
                }else {
                    messageArea.setText("No new posts..");
                }

            } catch (SQLException e) {
                throw new IllegalStateException("Cannot connect the database!", e);
            }
        }else {
            messageArea.setText("No new posts..");
            return;
        }
    }
}
