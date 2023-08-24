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
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Path;
import java.util.Date;
import java.util.ResourceBundle;

public class TweetController implements Initializable {
    @FXML
    private JFXButton fileChooserButton;
    @FXML
    private ImageView imageView;
    @FXML
    private JFXButton exitButton;
    @FXML
    private JFXButton submitButton;
    @FXML
    private TextArea textArea = new TextArea();
    @FXML
    private Label label;
    private File selectedFile;

    @FXML
    public void exit() {

    }


    @FXML
    private void onFileChooserButtonClick(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        label.setText("");
        this.selectedFile = fileChooser.showOpenDialog(null);
    }

    @FXML
    public void submitClicked(ActionEvent actionEvent) throws InterruptedException {
        User user = new User("Akbar", "Akbar123", null);
        Date date = new Date();
        if (textArea.getText() == null || textArea.getText().isEmpty()) {
            label.setText("Text cannot be empty");
            label.setTextFill(Color.RED);
        } else {
            if (selectedFile != null) {
                if (selectedFile.getName().endsWith(".mp4")|| selectedFile.getName().endsWith(".png") || selectedFile.getName().endsWith(".jpg") || selectedFile.getName().endsWith(".jpeg")) {
                    // do something with the selected file
                    String path = selectedFile.getPath();
                    Tweet tweet = new Tweet(HelloApplication.user, date, 12, 22, 1, textArea.getText(), new Image(Path.of(path).toUri().toString()));
                    HelloApplication.tweets.add(tweet);
                } else {
                    label.setText("File format not correct!");
                    label.setTextFill(Color.RED);
                }
            } else {
                Tweet tweet = new Tweet(HelloApplication.user, date, 12, 22, 1, textArea.getText(), null);
                HelloApplication.tweets.add(tweet);
            }
            label.setText("Successful!");
            label.setTextFill(Color.GREEN);
            //Thread.sleep(2500); // delays the execution for 2.5 seconds
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
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        imageView.setImage(new Image(Path.of("D:\\Programs\\FirstScene\\src\\assests\\twitter-icon-download-18.png").toUri().toString()));
    }
}
