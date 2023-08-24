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
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.nio.file.Path;
import java.util.ResourceBundle;

public class SearchController implements Initializable {
    @FXML
    ImageView imageView;
    @FXML
    ListView<ProfileComponent> profileComponentListView;
    @FXML
    JFXButton backButton;
    @FXML
    TextField textField;
    @FXML
    JFXButton submitButton;
    @FXML
    Label label;

//    @FXML
//    public void submit(){
//        for ( : ) {
//
//        }
//    }


    @FXML
    public void exit(ActionEvent actionEvent) {
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

    public void submit() {
        profileComponentListView.getItems().removeAll();
        System.out.println(profileComponentListView.getItems().size());
        if (!textField.getText().isEmpty()) {
            String temp = textField.getText();
            for (User user : HelloApplication.users) {
                if (user.getId().contains(temp)) {
                    ProfileComponent profileComponent = new ProfileComponent(user.getProfileImage(), user.getId(), user.getName(), user.getBio());
                    profileComponentListView.getItems().addAll(profileComponent);
                }
            }
        }
        else {
            label.setText("Search could not be null");
            label.setTextFill(Color.RED);
        }
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        imageView.setImage(new Image(Path.of("D:\\Programs\\FirstScene\\src\\assests\\twitter-icon-download-18.png").toUri().toString()));
    }
}
