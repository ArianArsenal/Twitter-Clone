package com.example.firstscene;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;

public class FirstSceneLoader extends VBox {

    @FXML
    Button loginBTN;
    @FXML
    Button signUpBTN;
    @FXML
    Button backBTN;
    @FXML
    ImageView imageView;

    @FXML
    AnchorPane anchorPane;

    @FXML
    VBox vBox = new VBox();

    @FXML
    public void exit(){
        System.exit(0);
    }

    @FXML
    public void login(ActionEvent event){
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("scene2.fxml"));
        Parent root = null;
        try {
            root = fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = null;
        if(root != null) {
            scene = new Scene(root,800,600);
        }
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    public void signUp(ActionEvent actionEvent){
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("signUp.fxml"));
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
