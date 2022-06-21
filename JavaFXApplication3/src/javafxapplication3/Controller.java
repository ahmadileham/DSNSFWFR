package javafxapplication3;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.*;
import javafx.scene.*;
import java.util.Locale;
import javax.speech.Central;
import javax.speech.synthesis.Synthesizer;
import javax.speech.synthesis.SynthesizerModeDesc;
import java.io.IOException;
import java.sql.*;
import java.util.EmptyStackException;
/**
 * @author DSNSFWFR ( ILHAM , BO , CAPANG , SHAFIQ)
 **/
public class Controller {

    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    private Label empty;

    @FXML
    private TextField confessionTextBox;
    @FXML
    private TextArea confessionPrint;


    @FXML
    private TextField messageReply;

    public Controller() {
    }

    public void initialize(){
        confessionPrint.setText(ConfessionPageJavaFX.confessions.peek().toString());
    }

    @FXML
    protected void onNextButtonClick() {

        try{
            ConfessionPageJavaFX.confessionsTemp.push(ConfessionPageJavaFX.confessions.pop());
            confessionPrint.setText(ConfessionPageJavaFX.confessions.peek().toString());
        }catch(EmptyStackException e){
            confessionPrint.setText("End of posts!");
        }
    }

    @FXML
    protected void onBackButtonClick() {

        try{
            confessionPrint.setText(ConfessionPageJavaFX.confessionsTemp.peek().toString());
            ConfessionPageJavaFX.confessions.push(ConfessionPageJavaFX.confessionsTemp.pop());
        }catch(EmptyStackException e){
            confessionPrint.setText("No post");
        }

    }

    @FXML
    protected void readScene() {

        try {
            // Set property as Kevin Dictionary
            System.setProperty(
                    "freetts.voices",
                    "com.sun.speech.freetts.en.us"
                            + ".cmu_us_kal.KevinVoiceDirectory");

            // Register Engine
            Central.registerEngineCentral(
                    "com.sun.speech.freetts"
                            + ".jsapi.FreeTTSEngineCentral");

            // Create a Synthesizer
            Synthesizer synthesizer
                    = Central.createSynthesizer(
                    new SynthesizerModeDesc(Locale.US));

            // Allocate synthesizer
            synthesizer.allocate();

            // Resume Synthesizer
            synthesizer.resume();

            // Speaks the given text
            // until the queue is empty.
            synthesizer.speakPlainText(
                    ConfessionPageJavaFX.confessions.peek().getConfession(), null);
            synthesizer.waitEngineState(
                    Synthesizer.QUEUE_EMPTY);

        }

        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void replyScene(javafx.event.ActionEvent event) throws IOException {


        root = FXMLLoader.load(getClass().getResource("makkau2.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();



    }

    public void adminLoginScene(javafx.event.ActionEvent event) throws IOException {


        root = FXMLLoader.load(getClass().getResource("makkau5.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();



    }

    public void submitScene(javafx.event.ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("makkau3.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void searchScene(javafx.event.ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("makkau4.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void viewRepliesScene(javafx.event.ActionEvent event) throws IOException{
        root = FXMLLoader.load(getClass().getResource("makkauviewreply.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void originalPostScene(javafx.event.ActionEvent event) throws IOException{
        root = FXMLLoader.load(getClass().getResource("makkauoriginal.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }


}
