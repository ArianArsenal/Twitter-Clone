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


    

    public Tweet(String text, int likeCount, int retweetCount, int replyCount, int tweetid, boolean isFavStar, String username) {
        this.text = text;
        this.likeCount = likeCount;
        this.retweetCount = retweetCount;
        this.replyCount = replyCount;
        this.isFavStar = isFavStar;

        LocalDate currentDate = LocalDate.now();
        LocalTime currentTime = LocalTime.now();

        this.year = currentDate.getYear();
        this.month = currentDate.getMonthValue();
        this.day = currentDate.getDayOfMonth();
        this.hour = currentTime.getHour();
        this.minute = currentTime.getMinute();
        this.second = currentTime.getSecond();
        this.username = username;

    }

    public String gettext() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Tweet(String text) {
        this.text = text;
    }
    //Converts a tweet's ID into to hashCode
    @Override
    public int hashCode() {
        return Objects.hash(username,text,year,month,day,hour,minute,second);
    }

    
    public void FavStar() {
        if (this.likeCount >= 10){
            this.isFavStar = true; //creepy
        }
        
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setLikeCount(int likeCount) {
        this.likeCount = likeCount;
    }

    public void setRetweetCount(int retweetCount) {
        this.retweetCount = retweetCount;
    }

    public void setReplyCount(int replyCount) {
        this.replyCount = replyCount;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public void setHour(int hour) {
        this.hour = hour;
    }

    public void setMinute(int minute) {
        this.minute = minute;
    }

    public void setSecond(int second) {
        this.second = second;
    }

    public void setFavStar(boolean isFavStar) {
        this.isFavStar = isFavStar;
    }

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

    public boolean isFavStar() {
        return isFavStar;
    }
    

    
    
    
}
