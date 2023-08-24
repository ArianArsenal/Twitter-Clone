package Controllers;



import com.jfoenix.controls.JFXButton;

import Common.IO;
import Common.connection;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;
import java.nio.file.Path;


public class PollController extends Application {

    @FXML
    ImageView imageView;
    @FXML
    private TextField questionTextField;
    @FXML
    private TextField option1TextField;
    @FXML
    private TextField option2TextField;
    @FXML
    private TextField option3TextField;
    @FXML
    private TextField option4TextField;
    @FXML
    JFXButton submitButton;
    @FXML
    Label label;
    @FXML
    JFXButton exitButton;

    @FXML
    public void submitClicked() throws IOException{


        if (questionTextField.getText().isEmpty() || option1TextField.getText().isEmpty() || option2TextField.getText().isEmpty() || option3TextField.getText().isEmpty() || option4TextField.getText().isEmpty()){
            
            label.setText("Please fill all the fields");
            label.setTextFill(Color.RED);
        }
        else {

            String datas = questionTextField.getText() + "," + option1TextField.getText() + "," + option2TextField.getText() + "," + option3TextField.getText() + "," + option4TextField.getText();

            connection.ClientSend(IO.out,datas);

            label.setText("Successful!");
            label.setTextFill(Color.GREEN);

            try {
                Thread.sleep(2500);

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            
            //todo
            ///@return to home page
    
        }


    }
    @FXML
    public void exit(ActionEvent actionEvent) throws IOException{

        connection.ClientSend(IO.out,"return");

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("meow.fxml"));
        Parent root = null;
        try {
            root = fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        Scene scene = null;
        if(root != null) {
            scene = new Scene(root,800,600);
        }
        stage.setScene(scene);
        stage.show();
    }


    @Override
    public void start(Stage stage) throws Exception {
        imageView.setImage(new Image(Path.of("D:\\Programs\\FirstScene\\src\\assests\\twitter-icon-download-18.png").toUri().toString()));

    }
}
