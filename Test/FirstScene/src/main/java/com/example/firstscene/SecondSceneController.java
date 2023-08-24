package com.example.firstscene;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;

public class SecondSceneController {
    @FXML
    AnchorPane anchorPane;
    @FXML
    ImageView imageView;

    @FXML
    Label label;

    @FXML
    VBox vBox;

    @FXML
    TextField usernameTF;
    @FXML
    PasswordField passwordTF;

    @FXML
    Button loginBTN;

    @FXML
    public void buttonClicked(){
        String tempUser = usernameTF.getText();
        String tempPass = passwordTF.getText();
        if (tempPass.equals("admin") && tempUser.equals("admin")){
            label.setText("Success!");
            label.setTextFill(Color.GREEN);
        }
        else {
            label.setText("Wrong username or password");
            label.setTextFill(Color.RED);
        }
    }
    @FXML
    public void exit(ActionEvent actionEvent){
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("hello-view.fxml"));
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
}
