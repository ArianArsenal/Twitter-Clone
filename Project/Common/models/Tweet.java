package Common.models;


import java.io.Serializable;
import java.nio.file.Path;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Objects;

import javafx.scene.image.Image;


public class Tweet implements Serializable {

    
    private String username;
    private String text;
    private int likeCount;  //if >= 10  == favstar
    private int retweetCount;
    private int replyCount;
    private int quoteCount;

    //Tweet Date
    private int year;
    private int month;
    private int day;

    //Tweet Time
    private int hour;
    private int minute;
    private int second;

    private boolean isFavStar;
    //private int tweetType;

    private String tweetImage;
    private String firstname;
    private String profilePic;

    //private ArrayList<Tweet> replies;
    //private ArrayList<Tweet> quotes;
    //private ArrayList<Tweet> retweets;



    //for creating a new tweet
    public Tweet(String text,String username,String firstname ,String tweetImage,String profilePic) {
        
        this.text = text;
        this.username = username;

        this.likeCount = 0 ;
        this.retweetCount = 0 ;
        this.replyCount = 0 ;
        this.quoteCount = 0;
        this.isFavStar = false;
        // this.tweetType = 1; //1 = message tweet , 2 = photo tweet

        LocalDate currentDate = LocalDate.now();
        LocalTime currentTime = LocalTime.now();

        //tweet date
        this.year = currentDate.getYear();
        this.month = currentDate.getMonthValue();
        this.day = currentDate.getDayOfMonth();
        
        //tweet time
        this.hour = currentTime.getHour();
        this.minute = currentTime.getMinute();
        this.second = currentTime.getSecond();

        this.tweetImage = tweetImage;
        this.firstname = firstname;
        this.profilePic = profilePic;
        
    }

    //Another Constructor for when we want to fetch the tweets from DataBase
    //for fetching tweets from database
    //todo add image to the constructor and get the image from database

    public Tweet(String text,int likeCount,int retweetCount,int replyCount,int quoteCount,int tweetId,boolean isFavStar,String username,String firstname,String tweetDate,String tweetTime,String tweetImage ,String profilePic){

        this.text = text;
        this.username = username;
        this.firstname = firstname;

        this.likeCount = likeCount;
        this.retweetCount = retweetCount;
        this.replyCount = replyCount;
        this.quoteCount = quoteCount;

        this.isFavStar = isFavStar;


        String[] date = tweetDate.split("-"); //0 = year , 1 = month , 2 = day

        this.year = Integer.parseInt(date[0]);
        this.month = Integer.parseInt(date[1]);
        this.day = Integer.parseInt(date[2]);

        String[] time = tweetTime.split(":"); //0 = hour , 1 = minute , 2 = second

        this.hour = Integer.parseInt(time[0]);
        this.minute = Integer.parseInt(time[1]);
        this.second = Integer.parseInt(time[2]);

        this.tweetImage = tweetImage;
        this.profilePic = profilePic;

    }

    //Converts a tweet's ID into to hashCode
    @Override
    public int hashCode() {
        return Objects.hash(username,text,year,month,day,hour,minute,second);
    }

    
    public void FavStar() {
        
        if (this.likeCount >= 10){
            this.isFavStar = true; 
        }
    }

    //when called on a tweet object , it will increament the likes
    public void like() {
        likeCount++;
        FavStar(); // Call the method to update isFavStar
    }
    
    

    
    //Getters
    public String getUsername() {
        return username;
    }
    public String getFirstname() {
        return firstname;
    }



    public Image getProfilePic() {

        Image pic = new Image(Path.of(this.profilePic).toUri().toString());

        return pic;
    }
    public Image getTweetImage() {

        Image image = new Image(Path.of(this.tweetImage).toUri().toString());

        return image;
    }
    
    public String getTweetImageString() {
        return tweetImage;
    }

    public String getProfilePicString() {
        return profilePic;
    }



    

    public String getText() {
        return text;
    }
    public int getLikeCount() {
        return likeCount;
    }
    public int getRetweetCount() {
        return retweetCount;
    }
    public int getReplyCount() {
        return replyCount;
    }
    public int getQuoteCount() {
        return quoteCount;
    }


    public boolean getIsFavStar() {

        if (this.likeCount >= 10){
            this.isFavStar = true; 
        }

        return isFavStar;
    }
    
    
    public String getTweetDate() {
        return year + "-" + month + "-" + day;
    }
    public String getTweetTime() {
        return hour + ":" + minute + ":" + second;
    }
    
}
