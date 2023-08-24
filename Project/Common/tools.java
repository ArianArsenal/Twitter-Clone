package Common;

import java.util.ArrayList;
import java.util.Locale;
import java.util.regex.*;

import Common.models.Poll;
import Common.models.Tweet;
import Common.models.User;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;



public class tools {

    /**
     * Function to check if the password has all the conditions
     * @param str is a password that wants to be checked
     * @return  boolean as if the given password has the conditions or not
     */
    public static boolean passwordChecker(String str){
        boolean upperCaseFlag = false;
        boolean lowerCaseFlag = false;
        char ch;
        if (str.length() < 8)
            return false;
        for (int i = 0; i < str.length(); i++) {
            ch = str.charAt(i);
            if (Character.isLowerCase(ch))
                lowerCaseFlag = true;
            if (Character.isUpperCase(ch))
                upperCaseFlag = true;
        }
        return (lowerCaseFlag && upperCaseFlag);
    }

    /**
    * Function to check if the email is valid
    * @param email to check
    * @return returns booleans true or false
    */
    public static boolean isValidEmail(String email){
        String regex = "^[A-Za-z0-9+_.-]+@(.+)$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    /**
     * Function to check if the username is already in the database
     * @param username to check
     * @return returns booleans true or false
     */

     public static boolean ExistID(String userid) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
    
        try {
            // Load the MySQL JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");
    
            // Connect to the MySQL database
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/ap_project", "root", "ari82moh");
    
            // Prepare the SQL statement with a parameter
            String query = "SELECT * FROM USERS WHERE username = ?";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, userid); // Set the parameter value
    
            // Execute the query
            resultSet = preparedStatement.executeQuery();
    
            // Check if the result set has any rows
            if (resultSet.next()) {
                // User ID exists
                return true;
            } else {
                // User ID does not exist
                return false;
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
    
        return false;
    }
    
    /**
     * Function to check if the email is already in the database
     * @param email to check
     * @return returns boolean true or false
     * 
     */
     public static boolean ExistEmail(String email){

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
    
        try {
            // Load the MySQL JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");
    
            // Connect to the MySQL database
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/ap_project", "root", "ari82moh");
    
            // Prepare the SQL statement with a parameter
            String query = "SELECT * FROM USERS WHERE email = ?";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, email); // Set the parameter value
    
            // Execute the query
            resultSet = preparedStatement.executeQuery();
    
            // Check if the result set has any rows
            if (resultSet.next()) {
                // User email exists
                return true;
            } else {
                // User email does not exist
                return false;
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
    
        return false;
    }

    public static boolean ExistPhonenumber(String phonenumber){

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
    
        try {
            // Load the MySQL JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");
    
            // Connect to the MySQL database
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/ap_project", "root", "ari82moh");
    
            // Prepare the SQL statement with a parameter
            String query = "SELECT * FROM USERS WHERE phonenumber = ?";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, phonenumber); // Set the parameter value
    
            // Execute the query
            resultSet = preparedStatement.executeQuery();
    
            // Check if the result set has any rows
            if (resultSet.next()) {
                // User phonenumber exists
                return true;
            } else {
                // User phonenumber does not exits
                return false;
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


        return false;
    }

    public static void InsertUser(User user) {

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
    
        try {
            // Load the MySQL JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");
    
            // Connect to the MySQL database
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/ap_project", "root", "ari82moh");
    
            // Prepare the SQL statement with a parameter
            String query = "INSERT INTO USERS VALUES (?,?,?,?,?,?,?,?,?,?)";

            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, user.getFirstname()); // Set the parameter value
            preparedStatement.setString(2, user.getLastname()); // Set the parameter value
            preparedStatement.setString(3, user.getUsername()); // Set the parameter value
            preparedStatement.setString(4, user.getPhonenumber()); // Set the parameter value
            preparedStatement.setString(5, user.getEmail()); // Set the parameter value
            preparedStatement.setString(6, user.getPassword()); // Set the parameter value
            preparedStatement.setString(7, user.getDatejoined()); // Set the parameter value
            preparedStatement.setString(8, user.getCountry()); // Set the parameter value
            preparedStatement.setString(9, user.getBirthdate()); // Set the parameter value
            preparedStatement.setString(10, user.getProfilePicString()); // Set the parameter value
    
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

    public static boolean ExistPassword(String username,String password){

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
    
        try {
            // Load the MySQL JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");
    
            // Connect to the MySQL database
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/ap_project", "root", "ari82moh");
    
            // Prepare the SQL statement with a parameter
            String query = "SELECT password FROM USERS WHERE username = ?";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, username); // Set the parameter value
    
            // Execute the query
            resultSet = preparedStatement.executeQuery();
    
            // Check if the result set has any rows
            if (resultSet.next()) {
                // User password exists
                String storedPassword = resultSet.getString("password");
                if(password.equals(storedPassword)){
                    return true;
                }
                else return false;
                
            } else {
                // User password does not exits
                return false;
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


        return false;
    }

     
    //shows current time ( maybe changed later)
    public static void CurrentTime() {
        LocalDateTime currentTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        String formattedTime = currentTime.format(formatter);
        System.out.println("Current Time: " + formattedTime);
    }

    public static String CalculateTimePassed(String tweetTime, String tweetDate) {
        // Tokenize
        String[] time = tweetTime.split(":"); // 0:hour 1:minute
        String[] date = tweetDate.split("-"); // 0:year 1:month 2:day

        int tweetHour = Integer.parseInt(time[0]);
        int tweetMinute = Integer.parseInt(time[1]);

        // Get current time
        LocalDateTime currentTime = LocalDateTime.now();

        // Create LocalDateTime object for the tweet time
        LocalDateTime tweetDateTime = LocalDateTime.of(
            Integer.parseInt(date[0]), // year
            Integer.parseInt(date[1]), // month
            Integer.parseInt(date[2]), // day
            tweetHour,
            tweetMinute
        );

        // Calculate the difference in minutes between the tweet time and current time
        long minutesPassed = java.time.Duration.between(tweetDateTime, currentTime).toMinutes();

        if (minutesPassed < 60) {
            // Less than 1 hour ago
            return minutesPassed + "m ago";

        } else if (minutesPassed < 1440) {
            // Between 1 hour and 24 hours ago
            long hoursPassed = minutesPassed / 60;
            return hoursPassed + "h ago";

        } else {
            // More than 24 hours ago
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d MMMM yyyy", Locale.ENGLISH);
            String formattedDate = tweetDateTime.format(formatter);

            return formattedDate;
        }

    }

    public static void InsertPoll(Poll poll){

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
    
        try {
            // Load the MySQL JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");
    
            // Connect to the MySQL database
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/ap_project", "root", "ari82moh");
    
            // Prepare the SQL statement with a parameter
            String query = "INSERT INTO Polls VALUES (?,?,?,?,?,?,?,?,?,?,?)";

            preparedStatement = connection.prepareStatement(query);

            preparedStatement.setString(1, poll.getUsername()); // Set the parameter value
            preparedStatement.setString(2, poll.getQuestion()); // Set the parameter value
            preparedStatement.setString(3, poll.getOption1()); // Set the parameter value
            preparedStatement.setString(4, poll.getOption2()); // Set the parameter value
            preparedStatement.setString(5, poll.getOption3()); // Set the parameter value
            preparedStatement.setString(6, poll.getOption4()); // Set the parameter value
            preparedStatement.setString(7, poll.getDateCreated()); // Set the parameter value
            preparedStatement.setInt(8, poll.getOption1Votes()); // Set the parameter value
            preparedStatement.setInt(9, poll.getOption2Votes()); // Set the parameter value
            preparedStatement.setInt(10, poll.getOption3Votes()); // Set the parameter value
            preparedStatement.setInt(11, poll.getOption4Votes()); // Set the parameter value

    
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
    
    @SuppressWarnings("unchecked")
    public static <T> T castObject(Object obj) {
        return (T) obj;
    }

    public static User getUser(String usersearch) {

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        User user = null;

        try {
            // Load the MySQL JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Connect to the MySQL database
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/ap_project", "root", "ari82moh");

            // Prepare the SQL statement with a parameter
            String query = "SELECT * FROM USERS WHERE username = ?";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, usersearch); // Set the parameter value

            // Execute the query and retrieve the result set
            resultSet = preparedStatement.executeQuery();

            // Check if the result set has any rows
            if (resultSet.next()) {
                // Extract the user data from the result set
                String firstname = resultSet.getString("name");
                String lastname = resultSet.getString("lastname");
                String username = resultSet.getString("username");
                String phonenumber = resultSet.getString("phonenumber");
                String email = resultSet.getString("email");
                String password = resultSet.getString("password");
                String country = resultSet.getString("country");
                String profileImage = resultSet.getString("profileImage");
                String birthdate = resultSet.getString("birthdate");

                String[] token = birthdate.split("-");

                int year = Integer.parseInt(token[0]);
                int month = Integer.parseInt(token[1]);
                int day = Integer.parseInt(token[2]);

                // Create a new User object with the retrieved data
                user = new User(firstname, lastname, username, password, email, phonenumber, country, year, month, day, profileImage);

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

        return user;
    }

    public static void main(String[] args) {
        CurrentTime();
    }

    public static void InsertTweet(Tweet newTweet) {

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
    
        try {
            // Load the MySQL JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");
    
            // Connect to the MySQL database
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/ap_project", "root", "ari82moh");
    
            // Prepare the SQL statement with a parameter
            String query2 = "INSERT INTO tweet (tweetId, username , firstname , text , likeCount , retweetCount , replyCount , quoteCount , isFavStar , tweetDate , tweetTime, tweetImage , profilePic ) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?)";


            preparedStatement = connection.prepareStatement(query2);

            preparedStatement.setInt(1, newTweet.hashCode());
            preparedStatement.setString(2, newTweet.getUsername());
            preparedStatement.setString(3, newTweet.getFirstname());
            preparedStatement.setString(4, newTweet.getText());

            preparedStatement.setInt(5, newTweet.getLikeCount());
            preparedStatement.setInt(6, newTweet.getRetweetCount());
            preparedStatement.setInt(7, newTweet.getReplyCount());
            preparedStatement.setInt(8, newTweet.getQuoteCount());
            preparedStatement.setBoolean(9, newTweet.getIsFavStar());

            preparedStatement.setString(10, newTweet.getTweetDate());
            preparedStatement.setString(11, newTweet.getTweetTime());

            preparedStatement.setString(12, newTweet.getTweetImageString());
            preparedStatement.setString(13, newTweet.getProfilePicString());

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

    public static String FollowCount(String username){

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        PreparedStatement preparedStatement1 = null;
        ResultSet resultSet1 = null;
        

        int Followercount = 0;
        int Followingcount = 0;

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
                //String followingUsername = resultSet.getString("following_id");
                Followingcount++;
            }

            // preparedStatement = null;
            // resultSet = null;

            String query1 = "SELECT username FROM follow WHERE following_id = ?";
            preparedStatement1 = connection.prepareStatement(query1);
            preparedStatement1.setString(1, username); // Set the parameter value
            // Execute the query
            resultSet1 = preparedStatement1.executeQuery();
    
            
            // Retrieve the followers usernames
            while (resultSet1.next()) {
                //String followingUsername = resultSet.getString("following_id");
                Followercount++;
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

        return Followercount + "$" + Followingcount;

    }

    public static ArrayList<User> searchUser(String key) {

        ArrayList<User> searchResults = new ArrayList<>();

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            // Load the MySQL JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Connect to the MySQL database
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/ap_project", "root", "ari82moh");

            // Prepare the SQL statement with parameters
            String query = "SELECT * FROM USERS WHERE username LIKE ? OR firstname LIKE ? OR lastname LIKE ?";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, "%" + key + "%"); // Search for username containing the key
            preparedStatement.setString(2, "%" + key + "%"); // Search for firstname containing the key
            preparedStatement.setString(3, "%" + key + "%"); // Search for lastname containing the key

            // Execute the query
            resultSet = preparedStatement.executeQuery();

            // Process the result set
            while (resultSet.next()) {

                String username = resultSet.getString("username");
                String firstname = resultSet.getString("firstname");
                String profileImage = resultSet.getString("profileImage");

                // Create a User object and add it to the searchResults list
                
                User user = new User(firstname,username,profileImage);

                searchResults.add(user);
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

        return searchResults;
    }

    public static String Block(){

        return "success"; 

    }

    public static String follow(String username, String guestuser) {

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            // Load the MySQL JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Connect to the MySQL database
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/ap_project", "root", "ari82moh");

            // Check if the user is already being followed
            String checkQuery = "SELECT * FROM follow WHERE username = ? AND following_id = ?";
            preparedStatement = connection.prepareStatement(checkQuery);
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, guestuser);
            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                // User is already being followed
                return "duplicate";
            }

            // Prepare the SQL statement with a parameter
            String insertQuery = "INSERT INTO follow (username, following_id) VALUES (?, ?)";
            preparedStatement = connection.prepareStatement(insertQuery);
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, guestuser);

            // Execute the query
            preparedStatement.execute();

            return "success";

        } catch (Exception e) {
            e.printStackTrace();
            return "error";
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

