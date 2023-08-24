package com.example.firstscene;

import com.jfoenix.controls.JFXButton;
import javafx.geometry.Orientation;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.control.TextArea;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Circle;

public class TweetComponent2 extends VBox {
    private Tweet tweet;
    private HBox firstHBox;
    private Circle profilePictureCircle;
    private VBox nameAndIDVbox;
    private Label nameLabel;
    private Label idLabel;
    private TextArea tweetArea;
    private AnchorPane imageAnchorPane;
    private ImageView tweetPicture;
    private HBox timeStampHBox;
    private Label timeLabel;
    private Label dateLabel;
    private Label twitterForWindows;
    private Separator separator1;
    private HBox showingStatsHBox;
    private HBox retweetHBox;
    private HBox likeHBox;
    private HBox quoteHBox;
    private Label likeCnt;
    private Label likes;
    private Label reTweetCnt;
    private Label retweets;
    private Label quoteCnt;
    private Label quotes;
    private Separator separator2;
    private HBox buttonsHBox;
    private JFXButton likeButton;
    private JFXButton retweetButton;
    private JFXButton quoteButton;
    private JFXButton replyButton;

    public TweetComponent2(Tweet tweet) {
        this.tweet = tweet;
        this.idLabel = new Label("@" + tweet.getUser().getId());
        this.nameLabel = new Label(tweet.getUser().getName());
        this.nameAndIDVbox = new VBox(idLabel, nameLabel);
        this.profilePictureCircle = new Circle(12);
        this.firstHBox = new HBox(profilePictureCircle, nameAndIDVbox);
        this.tweetArea = new TextArea(tweet.getText());
        this.tweetPicture = new ImageView(tweet.getImage());
        this.imageAnchorPane = new AnchorPane(tweetPicture);
        this.timeLabel = new Label(tweet.getDate().toString());
        this.dateLabel = new Label(Long.toString(tweet.getDate().getTime()));
        this.twitterForWindows = new Label("Twitter for windows");
        this.timeStampHBox = new HBox(timeLabel, dateLabel, twitterForWindows);
        this.separator1 = new Separator(Orientation.VERTICAL);
        this.retweets = new Label("Retweets ");
        this.reTweetCnt = new Label(String.valueOf(tweet.getRetweets()));
        this.quotes = new Label("Quotes ");
        this.quoteCnt = new Label(String.valueOf(tweet.getQuotes()));
        this.likes = new Label("Likes ");
        this.likeCnt = new Label(String.valueOf(tweet.getLikes()));
        this.retweetHBox = new HBox(retweets, reTweetCnt);
        this.quoteHBox = new HBox(quotes, quoteCnt);
        this.likeHBox = new HBox(likes, likeCnt);
        this.showingStatsHBox = new HBox(retweetHBox, quoteHBox, likeHBox);
        this.separator2 = new Separator(Orientation.VERTICAL);
        this.likeButton = new JFXButton();
        this.retweetButton = new JFXButton();
        this.quoteButton = new JFXButton();
        this.replyButton = new JFXButton();
        this.buttonsHBox = new HBox(likeButton, retweetButton, quoteButton, replyButton);
        this.getChildren().addAll(firstHBox, tweetArea, imageAnchorPane,timeStampHBox,separator1,showingStatsHBox,separator2,buttonsHBox);
        setConfig();


    }

    private void setConfig() {
        this.setPrefSize(597, 330);
        this.setStyle("-fx-border-color: #4da4e2 ;-fx-border-radius: 30;-fx-background-radius: 30 ; -fx-background-color: #010101");



    }

}
