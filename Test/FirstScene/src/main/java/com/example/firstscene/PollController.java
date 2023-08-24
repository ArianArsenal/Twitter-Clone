package com.example.firstscene;

import com.jfoenix.controls.JFXButton;
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
import java.util.Date;

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
    public void submitClicked() {
        User user = new User("User","username!!",null);

        if (questionTextField.getText().isEmpty() || option1TextField.getText().isEmpty() || option2TextField.getText().isEmpty() ||
                option3TextField.getText().isEmpty() || option4TextField.getText().isEmpty()){
            label.setText("Please fill all the fields");
            label.setTextFill(Color.RED);
        }
        else {
            label.setText("Successful!");
            label.setTextFill(Color.GREEN);
            Poll poll = new Poll(user,new Date(),questionTextField.getText(),option1TextField.getText(),option2TextField.getText(),
                    option3TextField.getText(),option4TextField.getText());
        }
    }
    @FXML
    public void exit(ActionEvent actionEvent){
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("TimeLine.fxml"));
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
        imageView.setImage(new Image(Path.of("D:\\Programs\\FirstScene\\src\\assests\\580b57fcd9996e24bc43c53e.png").toUri().toString()));

    }
}
