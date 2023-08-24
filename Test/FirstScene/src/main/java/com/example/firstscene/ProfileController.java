package com.example.firstscene;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Path;
import java.util.ResourceBundle;

public class ProfileController implements Initializable {
    @FXML
    private Label usernameLabel;
    @FXML
    private Label nameLabel;
    @FXML
    private Label followerCountLabel;
    @FXML
    private Label followingCountLabel;
    @FXML
    private JFXButton editHeaderButton;
    @FXML
    private JFXButton editAvatarButton;
    @FXML
    private JFXButton editBioButton;
    @FXML
    private Circle avatarCircle;
    @FXML
    private ImageView header;
    @FXML
    private Label statusLabel;
    @FXML
    private JFXButton submitButton;
    @FXML
    private JFXButton exit;
    @FXML
    private TextArea bioTextArea;
    @FXML
    private Label bioLabel;
    private Rectangle rectangle;


    private File headerFile;
    private File avatarFile;

    //handle the user


    public void setAvatar() {
        FileChooser fileChooser = new FileChooser();
        this.avatarFile = fileChooser.showOpenDialog(null);
        if (avatarFile != null){
        if (avatarFile.getName().endsWith(".png") || avatarFile.getName().endsWith(".jpg") || avatarFile.getName().endsWith(".jpeg")) {
            HelloApplication.user.setProfileImage(new Image(Path.of(avatarFile.getPath()).toUri().toString()));
            statusLabel.setText("Success!");
            statusLabel.setTextFill(Color.GREEN);
        }
        else{
            statusLabel.setText("Wrong Format!");
            statusLabel.setTextFill(Color.RED);
        }}
    }

    @FXML
    public void editHeader(){

        FileChooser fileChooser = new FileChooser();
        this.headerFile = fileChooser.showOpenDialog(null);
        if (headerFile!= null){
        if (headerFile.getName().endsWith(".png") || headerFile.getName().endsWith(".jpg") || headerFile.getName().endsWith(".jpeg")) {
            HelloApplication.user.setHeader(new Image(Path.of(headerFile.getPath()).toUri().toString()));
            statusLabel.setText("Success!");
            statusLabel.setTextFill(Color.GREEN);
        }
        else{
            statusLabel.setText("Wrong Format!");
            statusLabel.setTextFill(Color.RED);
        }}
    }

    @FXML
    public void submit(ActionEvent actionEvent){

        HelloApplication.user.setBio(bioTextArea.getText());
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("profile.fxml"));
        Parent root = null;
        try {
            root = fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        Scene scene = null;
        if (root != null) {
            scene = new Scene(root, 663, 676);
        }
        stage.setScene(scene);
        stage.show();
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
        if (root != null) {
            scene = new Scene(root, 800, 600);
        }
        stage.setScene(scene);
        stage.show();
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        bioLabel.setText(HelloApplication.user.getBio());
        avatarCircle.setFill(new ImagePattern(HelloApplication.user.getProfileImage()));
        header.setImage(HelloApplication.user.getHeader());
        header.setPreserveRatio(false);
        header.setFitHeight(200);
        header.setFitWidth(800);
        avatarCircle.setStyle("-fx-stroke-width: 1; -fx-stroke: #575555");
        header.setImage(HelloApplication.user.getHeader());
        rectangle = new Rectangle(655,184);
        header.setClip(rectangle);

    }
}
