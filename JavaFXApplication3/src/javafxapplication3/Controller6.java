package javafxapplication3;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Controller6 {

    private Stage stage;
    private Scene scene;
    private Parent root;


    public void loginScene(javafx.event.ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("loginAdmin.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void approveScene(javafx.event.ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("approvePage.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void approveNLPScene(javafx.event.ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("vacationmodePage.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void registerAdminScene(javafx.event.ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("registerAdminPage.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void deleteScene(javafx.event.ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("deletePage.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
