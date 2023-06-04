package Common.models;

import java.util.ArrayList;
import java.util.List;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

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
    
    //TODO :
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
        //sortTimeline();
    }

    //TODO: 
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
                String text = resultSet.getString("text");
                int likeCount = resultSet.getInt("likeCount");
                int retweetCount = resultSet.getInt("retweetCount");
                int replyCount = resultSet.getInt("replyCount");
                int tweetid = resultSet.getInt("tweetid");
                boolean isFavStar = resultSet.getBoolean("isFavStar");
                int tweetType = resultSet.getInt("tweetType");
            
                Tweet tweet;
            
                if (tweetType == 1) {
                    // Create a MessageTweet object
                    tweet = new MessageTweet(text, likeCount, retweetCount, replyCount, tweetid, isFavStar, username);

                } 
                else if (tweetType == 2) {
                    // Retrieve the photo details from the result set
                    // Assuming you have the necessary columns in the photo table
                    // and a method to retrieve photo details based on tweetid


                    //NOT COMPLETE YET
                    ArrayList<Photo> photos = retrievePhotosByTweetId(tweetid);
            
                    // Create a PhotoTweet object
                    tweet = new PhotoTweet(text, likeCount, retweetCount, replyCount, tweetid, isFavStar, username, photos);
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
    
    // private void sortTimeline() {
    //     // Implement the logic to sort the timeline based on timestamp or any other criteria
    // }
    
    public String showTimeline() {
        StringBuilder timelineBuilder = new StringBuilder();
        
        // Iterate through the tweets in the timeline and generate the display
        for (Tweet tweet : followTweets) {
            timelineBuilder.append(tweet.getUsername()).append(": ").append(tweet.getText()).append("\n");
        }
        
        return timelineBuilder.toString();
    }

}
