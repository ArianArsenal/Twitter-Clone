package Common.models;


import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextArea;

import Common.tools;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

import java.nio.file.Path;


public class TweetComponent extends AnchorPane {

    private Label username;
    private Label userId;

    private JFXTextArea tweetBody;
    private ImageView tweetImage;
    private Label date;
    private Label likeAmount;
    private Label likes;
    private Label reTweetAmount;
    private Label retweets;
    private Label quotes;
    private Label quoteCNT;
    private JFXButton likeButton;
    private JFXButton retweetButton;
    private JFXButton quoteButton;
    private JFXButton replyButton;

    private Circle pfpC;
    private int tweetId;
    private Tweet tweet;
    private Label twitterForWindows;
    private Rectangle rectangle;
    private ImageView whiteLike = new ImageView(new Image(Path.of("D:\\Programs\\FirstScene\\src\\assests\\7.png").toUri().toString()));
    private ImageView reTweet = new ImageView(new Image(Path.of("D:\\Programs\\FirstScene\\src\\assests\\9.png").toUri().toString()));
    private ImageView reply = new ImageView(new Image(Path.of("D:\\Programs\\FirstScene\\src\\assests\\8.png").toUri().toString()));
    private ImageView redHeart = new ImageView(new Image(Path.of("D:\\Programs\\FirstScene\\src\\assests\\liked (2).png").toUri().toString()));
    private ImageView quote = new ImageView(new Image(Path.of("D:\\Programs\\FirstScene\\src\\assests\\10.png").toUri().toString()));
    private ImageView retweeted = new ImageView(new Image(Path.of("src\\main\\resources\\Pic\\reTweeted.png").toUri().toString()));


    public TweetComponent(Tweet tweet) {

        this.tweet = tweet;
        this.userId = new Label("@" + tweet.getUsername());
        this.username = new Label(tweet.getFirstname());
        this.tweetBody = new JFXTextArea(tweet.getText());


        //! check
        this.tweetImage = new ImageView(tweet.getTweetImage());

        
        String tweetTime = tools.CalculateTimePassed(tweet.getTweetTime(),tweet.getTweetDate());
        this.date = new Label(tweetTime);


        this.likeAmount = new Label(""+ tweet.getLikeCount());
        this.likes = new Label("Likes");
        this.reTweetAmount = new Label(""+ tweet.getRetweetCount());
        this.retweets = new Label(" Retweets");
        this.quotes = new Label("Quotes");
        this.quoteCNT = new Label(""+ tweet.getQuoteCount());


        this.likeButton = new JFXButton();
        this.retweetButton = new JFXButton();
        this.quoteButton = new JFXButton();
        this.replyButton = new JFXButton();

        this.pfpC = new Circle(19);

        //this.tweetId = tweet.getTweetID();

        this.twitterForWindows = new Label("Twitter For Windows");
        this.getChildren().addAll(username, userId, pfpC, tweetBody, tweetImage, date, likeAmount, likes, reTweetAmount, retweets, quotes, quoteCNT, likeButton, retweetButton, quoteButton,replyButton, twitterForWindows);
        setConfig();
        setLoc();
        //setAction();

    }

    private void setConfig() {

        this.setPadding(new Insets(10, 10, 10, 10));;
        this.setPrefSize(1920, 300);
        this.setStyle("-fx-border-color: #14193d ;-fx-border-radius: 30;-fx-background-radius: 30 ; -fx-background-color: #171616");
        username.setFont(Font.font("System Bold", FontWeight.BOLD, 18));
        username.setTextFill(Paint.valueOf("#fafafa"));
        userId.setTextFill(Paint.valueOf("#53545c"));
        userId.setFont(Font.font("System Bold", FontWeight.BOLD, 10));
        pfpC.setStyle("-fx-stroke-width: 1; -fx-stroke: #575555");

        //! check
        pfpC.setFill(new ImagePattern(tweet.getProfilePic()));


        tweetBody.setPrefSize(250.0, 230.0);
        tweetBody.setMaxWidth(250.0);
        tweetBody.setMaxHeight(190.0);
        tweetBody.setMinWidth(250.0);
        tweetBody.setMinHeight(190.0);
        tweetBody.setEditable(false);
        tweetBody.setStyle("-fx-text-fill: white;-fx-background-color: #171616");
        tweetBody.setFont(Font.font("System", FontWeight.BLACK, 12));
        tweetImage.setFitHeight(230.0);
        tweetImage.setFitWidth(250.0);
        rectangle = new Rectangle(250, 230);
        rectangle.setArcWidth(30.0);
        rectangle.setArcHeight(30.0);
        tweetImage.setClip(rectangle);
        date.setFont(Font.font("system", FontWeight.BLACK, 9));
        date.setTextFill(Paint.valueOf("#53545c"));
        likeAmount.setFont(Font.font("system", FontWeight.BLACK, 9));
        likeAmount.setTextFill(Paint.valueOf("#fafafa"));
        likes.setFont(Font.font("system", FontWeight.BLACK, 9));
        likes.setTextFill(Paint.valueOf("#53545c"));
        reTweetAmount.setFont(Font.font("system", FontWeight.BLACK, 9));
        reTweetAmount.setTextFill(Paint.valueOf("#fafafa"));
        retweets.setFont(Font.font("system", FontWeight.BLACK, 9));
        retweets.setTextFill(Paint.valueOf("#53545c"));
        quotes.setFont(Font.font("system", FontWeight.BLACK, 9));
        quotes.setTextFill(Paint.valueOf("#53545c"));
        quoteCNT.setFont(Font.font("system", FontWeight.BLACK, 9));
        quoteCNT.setTextFill(Paint.valueOf("#fafafa"));
        likeButton.setMaxHeight(15.0);
        likeButton.setMinHeight(15.0);
        likeButton.setMaxWidth(15.0);
        likeButton.setMinWidth(15.0);
        likeButton.setGraphic(whiteLike);
        redHeart.setFitHeight(15.0);
        redHeart.setFitWidth(15.0);
        whiteLike.setFitHeight(15.0);
        whiteLike.setFitWidth(15.0);
        //likeButton.setRipplerFill(Paint.valueOf("#010101"));
        retweetButton.setGraphic(reTweet);
        retweetButton.setMaxHeight(15.0);
        retweetButton.setMinHeight(15.0);
        retweetButton.setMaxWidth(15.0);
        retweetButton.setMinWidth(15.0);
        reTweet.setFitHeight(15.0);
        reTweet.setFitWidth(15.0);
        //retweetButton.setRipplerFill(Paint.valueOf("#010101"));
        quote.setFitHeight(15.0);
        quote.setFitWidth(15.0);
        quoteButton.setMaxHeight(15.0);
        quoteButton.setMinHeight(15.0);
        quoteButton.setMaxWidth(15.0);
        quoteButton.setMinWidth(15.0);
        quoteButton.setGraphic(quote);
        replyButton.setMaxHeight(15.0);
        replyButton.setMinHeight(15.0);
        replyButton.setMaxWidth(15.0);
        replyButton.setMinWidth(15.0);
        replyButton.setGraphic(reply);
        reply.setFitHeight(15.0);
        reply.setFitWidth(15.0);
        //quoteButton.setRipplerFill(Paint.valueOf("#010101"));
        twitterForWindows.setTextFill(Paint.valueOf("#106bcc"));
    }

    private void setLoc() {

        AnchorPane.setLeftAnchor(username, 65.0);
        AnchorPane.setTopAnchor(username, 30.0);
        AnchorPane.setLeftAnchor(userId, 65.0);
        AnchorPane.setTopAnchor(userId, 54.0);
        AnchorPane.setLeftAnchor(pfpC, 25.0);
        AnchorPane.setTopAnchor(pfpC, 32.0);
        AnchorPane.setLeftAnchor(tweetBody, 65.0);
        AnchorPane.setTopAnchor(tweetBody, 80.0);
        AnchorPane.setLeftAnchor(tweetImage, 315.0);
        AnchorPane.setTopAnchor(tweetImage, 40.0);
        AnchorPane.setLeftAnchor(date, 45.0);
        AnchorPane.setTopAnchor(date, 285.0);
        AnchorPane.setLeftAnchor(likeAmount, 555.0);
        AnchorPane.setTopAnchor(likeAmount, 285.0);
        AnchorPane.setLeftAnchor(likes, 565.0);
        AnchorPane.setTopAnchor(likes, 285.0);
        AnchorPane.setLeftAnchor(reTweetAmount, 495.0);
        AnchorPane.setTopAnchor(reTweetAmount, 285.0);
        AnchorPane.setLeftAnchor(quotes, 455.0);
        AnchorPane.setTopAnchor(quotes, 285.0);
        AnchorPane.setTopAnchor(quoteCNT,285.0);
        AnchorPane.setLeftAnchor(quoteCNT,443.0);
        AnchorPane.setLeftAnchor(retweets, 505.0);
        AnchorPane.setTopAnchor(retweets, 285.0);
        AnchorPane.setLeftAnchor(likeButton, 405.0);
        AnchorPane.setTopAnchor(likeButton, 283.0);
        AnchorPane.setLeftAnchor(retweetButton, 375.0);
        AnchorPane.setTopAnchor(retweetButton, 283.0);
        AnchorPane.setLeftAnchor(quoteButton, 345.0);
        AnchorPane.setTopAnchor(quoteButton, 283.0);
        AnchorPane.setLeftAnchor(replyButton, 315.0);
        AnchorPane.setTopAnchor(replyButton, 283.0);
        AnchorPane.setLeftAnchor(twitterForWindows, 135.0);
        AnchorPane.setTopAnchor(twitterForWindows, 283.0);

    }

    // public void setAction(){

    //     //TODO like methods
        
    //     likeButton.setOnAction(event -> {

    //         if (tweet.isLiked()) {
    //             tweet.unlike();
    //             likeAmount.setText(String.valueOf(Integer.parseInt(likeAmount.getText()) - 1));
    //             likeButton.setGraphic(whiteLike);
    //         } else {
    //             tweet.like();
    //             likeAmount.setText(String.valueOf(Integer.parseInt(likeAmount.getText()) + 1));
    //             likeButton.setGraphic(redHeart);
    //         }
    //     });
    // }

//     public void setAction() {
//     likeButton.setOnAction((EventHandler<ActionEvent>) new EventHandler<ActionEvent>() {
//         @Override
//         public void handle(ActionEvent event) {
//             if (tweet.isLiked()) {
//                 tweet.unlike();
//                 likeAmount.setText(String.valueOf(Integer.parseInt(likeAmount.getText()) - 1));
//                 likeButton.setGraphic(whiteLike);
//             } else {
//                 tweet.like();
//                 likeAmount.setText(String.valueOf(Integer.parseInt(likeAmount.getText()) + 1));
//                 likeButton.setGraphic(redHeart);
//             }
//         }
//     });
// }

}