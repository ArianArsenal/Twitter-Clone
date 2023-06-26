package Common.models.TweetModels;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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

    private boolean isFavStar;
    private int tweetType;


    //for creating a new tweet
    public Tweet(String text,String username,int tweetType) {
        this.text = text;
        this.username = username;

        this.likeCount = 0 ;
        this.retweetCount = 0 ;
        this.replyCount = 0 ;
        this.isFavStar = false;
        this.tweetType = 1; //1 = message tweet , 2 = photo tweet

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

    //Another Constructor for when we want to fetch the tweets from DataBase
    //for fetching tweets from database
    public Tweet(String text,int likeCount,int retweetCount,int replyCount,boolean isFavStar,String username,String tweetDate,String tweetTime){

        this.text = text;
        this.username = username;

        this.likeCount = likeCount;
        this.retweetCount = retweetCount;
        this.replyCount = replyCount;
        this.isFavStar = isFavStar;


        String[] date = tweetDate.split("-"); //0 = year , 1 = month , 2 = day

        this.year = Integer.parseInt(date[0]);
        this.month = Integer.parseInt(date[1]);
        this.day = Integer.parseInt(date[2]);

        String[] time = tweetTime.split(":"); //0 = hour , 1 = minute , 2 = second

        this.hour = Integer.parseInt(time[0]);
        this.minute = Integer.parseInt(time[1]);
        this.second = Integer.parseInt(time[2]);

        
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
    public boolean getIsFavStar() {

        if (this.likeCount >= 10){
            this.isFavStar = true; 
        }

        return isFavStar;
    }
    public int getTweetType() {
        return tweetType;
    }
    
    public String getTweetDate() {
        return year + "-" + month + "-" + day;
    }
    public String getTweetTime() {
        return hour + ":" + minute + ":" + second;
    }

    //todo add tweets
    public static void AddTweet(String username,int tweetTYPE){

        if(tweetTYPE == 1){
            Tweet tweet = new MessageTweet(username, username, tweetTYPE);
        }
        else{
            Tweet tweet = new PhotoTweet("text", username, tweetTYPE, null);
        }

        

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
    
        try {
            // Load the MySQL JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");
    
            // Connect to the MySQL database
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/ap_project", "root", "ari82moh");
    
            // Prepare the SQL statement with a parameter
            String query = "INSERT INTO TWEETS VALUES (?,?,?)";

            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, username); // Set the parameter value
            preparedStatement.setString(2, "tweet"); // Set the parameter value
            preparedStatement.setString(3, "date"); // Set the parameter value

    
            // Execute the query
            preparedStatement.execute();
    
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // Close the resources
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }



}
