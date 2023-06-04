package Common.models.TweetModels;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Objects;


public abstract class Tweet {

    private String username;
    private String text;
    private int likeCount;  //if >= 10  == favstar
    private int retweetCount;
    private int replyCount;

    //Tweet Date
    private int year;
    private int month;
    private int day;

    //Tweet Time
    private int hour;
    private int minute;
    private int second;

    private boolean isFavStar = false;

    //in tweet we only need to give the text to the constructor
    
    public Tweet(String text,String username) {
        this.text = text;
        this.username = username;

        this.likeCount = 0 ;
        this.retweetCount = 0 ;
        this.replyCount = 0 ;
        this.isFavStar = false;

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
        
    }

    //TODO: Another Constructor for when we want to fetch the tweets from DataBase

    //int tweetid
    public Tweet(String text,int likeCount,int retweetCount,int replyCount,Boolean isFavStar,String username){

        this.text = text;
        this.username = username;

        this.likeCount = likeCount;
        this.retweetCount = retweetCount;
        this.replyCount = replyCount;
        this.isFavStar = isFavStar;

        // LocalDate currentDate = LocalDate.now();
        // LocalTime currentTime = LocalTime.now();

        //TODO:tweet date
        // this.year = currentDate.getYear();
        // this.month = currentDate.getMonthValue();
        // this.day = currentDate.getDayOfMonth();
        
        //TODO:tweet time
        // this.hour = currentTime.getHour();
        // this.minute = currentTime.getMinute();
        // this.second = currentTime.getSecond();
        
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
    
    public boolean isFavStar() {
        return isFavStar;
    }

    
    //Getters
    public String getUsername() {
        return username;
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

    public int getYear() {
        return year;
    }
    public int getMonth() {
        return month;
    }
    public int getDay() {
        return day;
    }

    public int getHour() {
        return hour;
    }
    public int getMinute() {
        return minute;
    }
    public int getSecond() {
        return second;
    }


    //TODO 


}
