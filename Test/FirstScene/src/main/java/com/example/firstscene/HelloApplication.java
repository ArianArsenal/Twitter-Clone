package com.example.firstscene;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

public class HelloApplication extends Application {
    public static ArrayList<User> users = new ArrayList<>();
    Date date = new Date();
    public static ArrayList<Tweet>tweets = new ArrayList<>();
    public static User user = new User("Bardia", "Bardia112",null);
    @Override
    public void start(Stage stage) throws IOException {

        User ali = new User("Ali","ali123",new Image("C:/Users/Lenovo/Desktop/FirstScene/src/assests/twitter-icon-download-18.png"));
        ali.setBio("Ali 22 shire es esi, longe pare pare HDMI");
        users.add(ali);
        users.add(user);

        Tweet tweet1 = new Tweet(ali,date,3,2,1,"I love Twitter",new Image("D:/images/1280px-Sunflower_from_Silesia2.jpg"));
        Tweet tweet2 = new Tweet(ali,date,3,6,1,"Meow Meow I hate being sad!!!!",null);
        Tweet tweet3 = new Tweet(ali,date,3,2,1,"گفته بودم اگه برگردی دوباره\n" +
                "غم می ره از دل و تاریکی می میره\n" +
                "بعد از اون بی تو نشستن ها یه روزی\n" +
                "دستای سردمو دست تو می گیره\n" +
                "اومدی اما دیدم دست تو سرده\n" +
                "گفتی اون روزها دیگه بر نمی گرده\n" +
                "اومدی اما دیدم دست تو سرده\n" +
                "گفتی اون روزها دیگه بر نمی گرده\n" +
                "گفته بودم اگه برگردی می بینی\n" +
                "نقش غم ها رو تو آیینه چشمام\n" +
                "می دونی این جا تو این خونه غمگین\n" +
                "رنگ بی رنگی گرفته بی تو دنیام\n" +
                "اومدی اما دیدم دست تو سرده\n" +
                "گفتی اون روزها دیگه بر نمی گرده\n" +
                "اومدی اما دیدم دست تو سرده\n" +
                "گفتی اون روزها دیگه بر نمی گرده\n",new Image("D:/images/Cat03.jpg"));
        Tweet tweet4 = new Tweet(ali,date,3,6,1,"Meow Meow!",new Image("D:/images/Cat03.jpg"));
        Tweet tweet5 = new Tweet(ali,date,3,2,1,"I hate pasta and pizza!!!!",null);
        Tweet tweet6 = new Tweet(ali,date,3,6,1,"Meow Meow!",null);
        tweets.add(tweet1);
        tweets.add(tweet2);
        tweets.add(tweet3);
        tweets.add(tweet4);
        tweets.add(tweet5);
        tweets.add(tweet6);
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("profile.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 800, 600);
        stage.setTitle("Twitter");
        stage.getIcons().addAll(new Image("C:/Users/Lenovo/Desktop/FirstScene/src/assests/twitter-icon-download-18.png"));
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}