package com.example.firstscene;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.nio.file.Path;
import java.util.Date;
import java.util.ResourceBundle;

public class TimelineController implements Initializable {
    @FXML
    private ScrollPane scrollPane = new ScrollPane();
    private VBox tweetContent;
    @FXML
    private JFXButton homeBTN;
    @FXML
    private JFXButton exploreBTN;
    @FXML
    private JFXButton directMessageBTN;
    @FXML
    private JFXButton pollButton;
    @FXML
    private JFXButton profileBTN;
    @FXML
    private JFXButton tweetBTN;
    @FXML
    private JFXButton exitBTN;


    @FXML
    public void homeClicked(ActionEvent actionEvent){
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
    @FXML
    public void tweet(ActionEvent actionEvent){
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("tweet.fxml"));
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
    @FXML
    public void profile(ActionEvent actionEvent){
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("profile.fxml"));
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
    @FXML
    public void pollClicked(ActionEvent actionEvent){
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("addPoll.fxml"));
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

    @FXML
    public void exitClicked(ActionEvent actionEvent){
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


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        tweetContent = new VBox();
        scrollPane.setContent(tweetContent);
        tweetContent.setSpacing(5);
        tweetContent.setStyle("-fx-background-color:#14193d");
        ImageView imageViewHome = new ImageView(new Image(Path.of("D:\\Programs\\FirstScene\\src\\assests\\1.png").toUri().toString()));
        homeBTN.setGraphic(imageViewHome);
        ImageView imageViewExplore = new ImageView(new Image(Path.of("D:\\Programs\\FirstScene\\src\\assests\\3.png").toUri().toString()));
        exploreBTN.setGraphic(imageViewExplore);
        ImageView imageViewDM = new ImageView(new Image(Path.of("D:\\Programs\\FirstScene\\src\\assests\\4.png").toUri().toString()));
        directMessageBTN.setGraphic(imageViewDM);
        ImageView imageViewProfile = new ImageView(new Image(Path.of("D:\\Programs\\FirstScene\\src\\assests\\5.png").toUri().toString()));
        profileBTN.setGraphic(imageViewProfile);
        ImageView imageViewExit = new ImageView(new Image(Path.of("D:\\Programs\\FirstScene\\src\\assests\\6.png").toUri().toString()));
        exitBTN.setGraphic(imageViewExit);
        ImageView imageViewTweet = new ImageView(new Image(Path.of("D:\\Programs\\FirstScene\\src\\assests\\2.png").toUri().toString()));
        tweetBTN.setGraphic(imageViewTweet);
        ImageView imageViewPoll = new ImageView(new Image(Path.of("D:\\Programs\\FirstScene\\src\\assests\\11.png").toUri().toString()));
        pollButton.setGraphic(imageViewPoll);
        User user = new User("Bardia","bardia112",null);

        for (Tweet tweet : HelloApplication.tweets) {
            TweetComponent tweetComponent = new TweetComponent(tweet);
            tweetContent.getChildren().addAll(tweetComponent);
        }
        Poll poll = new Poll(user,new Date(),"The best race?","Arians","Blacks","German","French");
        PollComponent pollComponent = new PollComponent(poll);
        tweetContent.getChildren().addAll(pollComponent);
    }
}
