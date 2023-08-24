package Controllers;

import javafx.event.ActionEvent;

// package com.example.firstscene;

import com.jfoenix.controls.JFXButton;

import Common.IO;
import Common.connection;
import Common.models.Tweet;
import Common.models.TweetComponent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.nio.file.Path;
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
    private JFXButton profileBTN;
    @FXML
    private JFXButton tweetBTN;
    @FXML
    private JFXButton exitBTN;

    @FXML
    public void homeClicked(ActionEvent actionEvent) throws IOException{

        sendButtonClickedToServer("Home");

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("meow.fxml"));
        Parent root = null;
        try {
            root = fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Stage stage = (Stage) ((javafx.scene.Node) actionEvent.getSource()).getScene().getWindow();
        
        Scene scene = null;
        if(root != null) {
            scene = new Scene(root,800,600);
        }
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    public void tweet(ActionEvent actionEvent) throws IOException{

        sendButtonClickedToServer("Tweet");

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("tweet.fxml"));
        Parent root = null;
        try {
            root = fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Stage stage = (Stage) ((javafx.scene.Node) actionEvent.getSource()).getScene().getWindow();
        Scene scene = null;
        if(root != null) {
            scene = new Scene(root,800,600);
        }
        stage.setScene(scene);
        stage.show();
    }


    @FXML
    public void pollClicked(ActionEvent actionEvent) throws IOException{

        sendButtonClickedToServer("Poll");

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("addPoll.fxml"));
        Parent root = null;
        try {
            root = fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Stage stage = (Stage) ((javafx.scene.Node) actionEvent.getSource()).getScene().getWindow();
        Scene scene = null;
        if(root != null) {
            scene = new Scene(root,800,600);
        }
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    public void exitClicked(ActionEvent actionEvent) throws IOException{

        sendButtonClickedToServer("Exit");

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("hello-view.fxml"));
        Parent root = null;
        try {
            root = fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        Stage stage = (Stage) ((javafx.scene.Node) actionEvent.getSource()).getScene().getWindow();
        Scene scene = null;
        if(root != null) {
            scene = new Scene(root,800,600);
        }
        stage.setScene(scene);
        stage.show();
    }

    private void sendButtonClickedToServer(String buttonClicked)throws IOException{

        //IO.out.writeObject(buttonClicked);
        connection.ClientSend(IO.out, buttonClicked);

    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle){

        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        tweetContent = new VBox();
        scrollPane.setContent(tweetContent);
        tweetContent.setSpacing(5);
        tweetContent.setStyle("-fx-background-color:#14193d");
        ImageView imageViewHome = new ImageView(new Image(Path.of("D:\\Programs\\FirstScene\\src\\assests\\Home.png").toUri().toString()));
        homeBTN.setGraphic(imageViewHome);
        ImageView imageViewExplore = new ImageView(new Image(Path.of("D:\\Programs\\FirstScene\\src\\assests\\SearchandExplore.png").toUri().toString()));
        exploreBTN.setGraphic(imageViewExplore);
        ImageView imageViewDM = new ImageView(new Image(Path.of("D:\\Programs\\FirstScene\\src\\assests\\directmessage.png").toUri().toString()));
        directMessageBTN.setGraphic(imageViewDM);
        ImageView imageViewProfile = new ImageView(new Image(Path.of("D:\\Programs\\FirstScene\\src\\assests\\Twitter-new-2017-avatar-001.png").toUri().toString()));
        profileBTN.setGraphic(imageViewProfile);
        ImageView imageViewExit = new ImageView(new Image(Path.of("D:\\Programs\\FirstScene\\src\\assests\\Exit (1).png").toUri().toString()));
        exitBTN.setGraphic(imageViewExit);
        ImageView imageViewTweet = new ImageView(new Image(Path.of("D:\\Programs\\FirstScene\\src\\assests\\Tweet.png").toUri().toString()));
        tweetBTN.setGraphic(imageViewTweet);

        try {


            //!Test here

            while (true) {

                String data = connection.ClientRecieve(IO.in);
                data = null;

                if (data == null) {
                    break; // Exit the loop if there is no more data
                }
                
                // Split the data string to extract individual values
                String[] parts = data.split("\\$");
                //String[] parts = data.split(",");
                
                // Extract the values from the split parts
                String text = parts[0];
                int likeCount = Integer.parseInt(parts[1]);
                int retweetCount = Integer.parseInt(parts[2]);
                int replyCount = Integer.parseInt(parts[3]);
                int quoteCount = Integer.parseInt(parts[4]);
                int tweetId = Integer.parseInt(parts[5]);
                boolean isFavStar = Boolean.parseBoolean(parts[6]);
                String username = parts[7];
                String firstname = parts[8];
                String tweetDate = parts[9];
                String tweetTime = parts[10];
                String tweetImage = parts[11];
                String profilePic = parts[12];
    
                // Create a new Tweet object using the extracted values
                Tweet tweet = new Tweet(text, likeCount, retweetCount, replyCount, quoteCount, tweetId, isFavStar, username, firstname, tweetDate, tweetTime, tweetImage, profilePic);
                
                // Add the tweet to your UI or data structure
                TweetComponent tweetComponent = new TweetComponent(tweet);
                tweetContent.getChildren().add(tweetComponent);

            }

            
            // ArrayList<Tweet> Retreivedtweets = (ArrayList<Tweet>) connection.ClientReceiveObject(IO.objectIn);
            //ArrayList<Tweet> retrievedTweets = tools.castObject(connection.ClientReceiveObject(IO.objectIn));

            // for (Tweet tst : retrievedTweets) {
            
            //     TweetComponent tweetComponent = new TweetComponent(tst);
            //     tweetContent.getChildren().addAll(tweetComponent);

            // }
            
        }

        catch (Exception e) {
            System.out.println("Error in retreiving tweets");
        }
        
    }


}
