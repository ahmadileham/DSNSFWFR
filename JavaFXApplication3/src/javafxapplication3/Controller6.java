package javafxapplication3;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

import javax.swing.plaf.nimbus.State;
import java.io.IOException;
import java.sql.*;

public class Controller6 {
    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    private TextArea messageArea;

    public void initialize(){
        messageArea.setText(ConfessionPageJavaFX.confessions.peek().toString());
    }

    public void adminLoginScene(javafx.event.ActionEvent event) throws IOException {


        root = FXMLLoader.load(getClass().getResource("makkau5.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();



    }

    @FXML
    protected void approveMessage() {

        Confession a = ConfessionPageJavaFX.notApprove.dequeue();

        ConfessionPageJavaFX.notApproveTemp.enqueue(a);

        messageArea.setText(ConfessionPageJavaFX.notApprove.peek().toString());
        try  {
            Connection connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/confession_page_dsnsfwfr", "root", "root");
            Statement myStmt = connection.createStatement();
            Statement myStmt2 = connection.createStatement();

            myStmt.executeUpdate("insert into approve(confessionID, confession, date_post, reply_ID) values(" + a.getID() + ",'" + a.getConfession() + "','" + a.getDate() + "',"+a.getReply_ID()+")");
            myStmt2.executeUpdate("DELETE FROM not_approve WHERE confessionID = "+a.getID());

            myStmt.close();
            myStmt2.close();

        } catch (SQLException e) {
            throw new IllegalStateException("Cannot connect the database!", e);
        }
    }

    @FXML
    protected void rejectMessage() {

        Confession a = ConfessionPageJavaFX.notApprove.dequeue();

        ConfessionPageJavaFX.notApproveTemp.enqueue(a);

        messageArea.setText(ConfessionPageJavaFX.notApprove.peek().toString());

        try  {
            Connection connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/confession_page_dsnsfwfr", "root", "root");
            Statement myStmt = connection.createStatement();

            myStmt.executeUpdate("DELETE FROM not_approve WHERE confessionID = "+a.getID());

            myStmt.close();

        } catch (SQLException e) {
            throw new IllegalStateException("Cannot connect the database!", e);
        }

    }
}
