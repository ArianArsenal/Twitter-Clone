package Common.models;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import Common.models.PhotoModels.Photo;
import Common.models.TweetModels.MessageTweet;
import Common.models.TweetModels.PhotoTweet;
import Common.models.TweetModels.Tweet;

public class TimeLine {
    
    private String username;
    private ArrayList<Tweet> followTweets;

    public TimeLine(String username) {
        this.username = username;
        this.followTweets = new ArrayList<>();
    }
    
    public void retrieveFollowings(String username) {

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        ArrayList<String> followingUsernames = new ArrayList<>();
        
        try {

            // Load the MySQL JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");
    
            // Connect to the MySQL database
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/ap_project", "root", "ari82moh");
    
            // Prepare the SQL statement with a parameter
            String query = "SELECT following_id FROM follow WHERE username = ?";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, username); // Set the parameter value
    

            //!TEST
            PreparedStatement tst2;
            Tweet tst = new MessageTweet("this is a test message tweet", "M", 1);
            String query2 = "INSERT INTO tweet (tweetId, username , text , likeCount , retweetCount , replyCount , tweetDate ,tweetTime, tweetType, isFavStar) VALUES (?,?,?,?,?,?,?,?,?,?)";
            
            tst2 = connection.prepareStatement(query2);

            tst2.setInt(1, tst.hashCode());
            tst2.setString(2, tst.getUsername());
            tst2.setString(3, tst.getText());
            tst2.setInt(4, tst.getLikeCount());
            tst2.setInt(5, tst.getRetweetCount());
            tst2.setInt(6, tst.getReplyCount());

            tst2.setString(7, tst.getTweetDate());
            tst2.setString(8, tst.getTweetTime());

            tst2.setInt(9, tst.getTweetType());
            tst2.setBoolean(10, tst.getIsFavStar());
            
            tst2.executeUpdate();


            PreparedStatement tst3;
            Tweet tweet3 = new MessageTweet("this is a FAV tweet", "Arian", 1);
            String query3 = "INSERT INTO tweet (tweetId, username , text , likeCount , retweetCount , replyCount , tweetDate ,tweetTime, tweetType, isFavStar) VALUES (?,?,?,?,?,?,?,?,?,?)";
            
            tst3 = connection.prepareStatement(query3);

            for (int i = 0; i < 11; i++) {
                tweet3.like();
            }

            tst3.setInt(1, tweet3.hashCode());
            tst3.setString(2, tweet3.getUsername());
            tst3.setString(3, tweet3.getText());
            tst3.setInt(4, tweet3.getLikeCount());
            tst3.setInt(5, tweet3.getRetweetCount());
            tst3.setInt(6, tweet3.getReplyCount());

            tst3.setString(7, tweet3.getTweetDate());
            tst3.setString(8, tweet3.getTweetTime());

            tst3.setInt(9,tweet3.getTweetType());
            tst3.setBoolean(10, tweet3.getIsFavStar());
            
            tst3.executeUpdate();

            //!TEST


            // Execute the query
            resultSet = preparedStatement.executeQuery();
    
            // Retrieve the following usernames
            while (resultSet.next()) {
                String followingUsername = resultSet.getString("following_id");
                followingUsernames.add(followingUsername);
            }
    
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
    
        // Use the followingUsernames list to retrieve the tweets from the tweet table
        for (String followingUsername : followingUsernames) {
            // Assuming you have a method to retrieve tweets for a specific username
            ArrayList<Tweet> tweets = retrieveTweetsByUsername(followingUsername);
            // Add the tweets to the timeline
            followTweets.addAll(tweets);

        }
    
        // Sort the timeline based on timestamp or any other criteria
        sortTimeline(followTweets);


    }

    public ArrayList<Tweet> retrieveTweetsByUsername(String followingUsername) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        ArrayList<Tweet> tweets = new ArrayList<>();
    
        try {
            // Load the MySQL JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");
    
            // Connect to the MySQL database
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/ap_project", "root", "ari82moh");
    

            // Prepare the SQL statement with a parameter
            String query = "SELECT * FROM tweet WHERE username = ?";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, followingUsername); // Set the parameter value
    
            // Execute the query
            resultSet = preparedStatement.executeQuery();
    
            // Retrieve the tweets
            while (resultSet.next()) {
                // Retrieve the tweet details from the result set

                int tweetid = resultSet.getInt("tweetid");
                String tweetusername = resultSet.getString("username");
                String text = resultSet.getString("text");

                int likeCount = resultSet.getInt("likeCount");
                int retweetCount = resultSet.getInt("retweetCount");
                int replyCount = resultSet.getInt("replyCount");

                String tweetDate = resultSet.getString("tweetDate");
                
                boolean isFavStar = resultSet.getBoolean("isFavStar");
                int tweetType = resultSet.getInt("tweetType"); 
                String tweetTime = resultSet.getString("tweetTime"); 
                
                Tweet tweet;
            
                if (tweetType == 1) {
                    // Create a MessageTweet object
                    tweet = new MessageTweet(text, likeCount, retweetCount, replyCount, tweetid, isFavStar, tweetusername, tweetDate, tweetTime);

                } 
                else if (tweetType == 2) {
                    // Retrieve the photo details from the result set
                    // Assuming you have the necessary columns in the photo table
                    // and a method to retrieve photo details based on tweetid

                    //NOT COMPLETE YET
                    ArrayList<Photo> photos = retrievePhotosByTweetId(tweetid);
            
                    // Create a PhotoTweet object
                    tweet = new PhotoTweet(text,likeCount,retweetCount,replyCount,tweetid,isFavStar,tweetDate,tweetTime,tweetusername,photos);


                } else {
                    // Handle unknown tweet types or any other logic you need
                    continue; // Skip this iteration of the loop
                }
            
                tweets.add(tweet);
            }

            resultSet.close();
            preparedStatement.close();

            String query0 = "SELECT * FROM tweet WHERE isFavStar = 1 AND username != ?";
            preparedStatement = connection.prepareStatement(query0);
            preparedStatement.setString(1, followingUsername);

            // Execute the query
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                // Retrieve the tweet details from the result set

                int tweetid = resultSet.getInt("tweetid");
                String tweetusername = resultSet.getString("username");
                String text = resultSet.getString("text");

                int likeCount = resultSet.getInt("likeCount");
                int retweetCount = resultSet.getInt("retweetCount");
                int replyCount = resultSet.getInt("replyCount");

                String tweetDate = resultSet.getString("tweetDate");
                
                boolean isFavStar = resultSet.getBoolean("isFavStar");
                int tweetType = resultSet.getInt("tweetType"); 
                String tweetTime = resultSet.getString("tweetTime"); 
                
                Tweet tweet;
            
                if (tweetType == 1) {
                    // Create a MessageTweet object
                    tweet = new MessageTweet(text, likeCount, retweetCount, replyCount, tweetid, isFavStar, tweetusername, tweetDate, tweetTime);

                } 
                else if (tweetType == 2) {
                    // Retrieve the photo details from the result set
                    // Assuming you have the necessary columns in the photo table
                    // and a method to retrieve photo details based on tweetid

                    //NOT COMPLETE YET
                    ArrayList<Photo> photos = retrievePhotosByTweetId(tweetid);
            
                    // Create a PhotoTweet object
                    tweet = new PhotoTweet(text,likeCount,retweetCount,replyCount,tweetid,isFavStar,tweetDate,tweetTime,tweetusername,photos);


                } else {
                    // Handle unknown tweet types or any other logic you need
                    continue; // Skip this iteration of the loop
                }
            
                tweets.add(tweet);
            }
            
    
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
    
        

        return tweets;
    }

    private ArrayList<Photo> retrievePhotosByTweetId(int tweetid) {


        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        
    
        try {
            // Load the MySQL JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");
    
            // Connect to the MySQL database
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/ap_project", "root", "ari82moh");
    
            // Prepare the SQL statement with a parameter
            String query = "SELECT * FROM photo WHERE tweetId = ?";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1,tweetid); // Set the parameter value
    
            // Execute the query
            resultSet = preparedStatement.executeQuery();
    
            // Retrieve the photos
            ArrayList<Photo> photos = new ArrayList<>();
            while (resultSet.next()) {

                // Retrieve the photo details from the result set
                // ...retrieve the necessary photo information from the result set...
                // Create a Photo object
                //Photo photo = new Photo();
                //photos.add(photo);
            
            }
            
            return photos;
    
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


        return null;
    }

    public ArrayList<Tweet> getFollowTweets() {
        return followTweets;
    }

    private void sortTimeline(ArrayList<Tweet> followTweets) {
    
        // Sort the tweets based on their timestamp (tweetDate and tweetTime)
        Collections.sort(followTweets, new Comparator<Tweet>() {
            @Override
            public int compare(Tweet tweet1, Tweet tweet2) {
                LocalDateTime dateTime1 = LocalDateTime.parse(tweet1.getTweetDate() + " " + tweet1.getTweetTime(), DateTimeFormatter.ofPattern("yyyy-M-d H:m:s"));
                LocalDateTime dateTime2 = LocalDateTime.parse(tweet2.getTweetDate() + " " + tweet2.getTweetTime(), DateTimeFormatter.ofPattern("yyyy-M-d H:m:s"));

                return dateTime2.compareTo(dateTime1); // Sort in descending order (newest first)
            }
        });
    }

    public String showTimeline() {
        StringBuilder timelineBuilder = new StringBuilder();
        
        // Iterate through the tweets in the timeline and generate the display
        for (Tweet tweet : followTweets) {
            timelineBuilder.append(tweet.getUsername()).append(": ").append(tweet.getText()).append("\n");
        }
        
        return timelineBuilder.toString();
    }

}
