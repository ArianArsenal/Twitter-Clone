package Database;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class databasemanage {

    public static void DataBaseManagement(){

        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;

        try {

            // Load the MySQL JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Connect to the MySQL database
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/ap_project", "root", "ari82moh");

            // Create a statement
            statement = connection.createStatement();

            
            //Execute a query to create table / if is already created ,it skips this part without error

            //statement.execute("DROP TABLE USERS");

            String sql = "CREATE TABLE IF NOT EXISTS USERS (" +
                "name VARCHAR(255) NOT NULL, " +
                "lastname VARCHAR(255) NOT NULL, " +
                "username VARCHAR(255) NOT NULL UNIQUE, " +
                "phonenumber VARCHAR(255) NOT NULL UNIQUE, " +
                "email VARCHAR(255) NOT NULL UNIQUE, " +
                "password TEXT NOT NULL, " +
                "datejoined DATE NOT NULL, " +
                "country VARCHAR(255) NOT NULL, " +
                "birthdate DATE NOT NULL, " +
                "PRIMARY KEY (username, phonenumber, email)" +")";
            statement.execute(sql);

            String sql2 = "CREATE TABLE IF NOT EXISTS bio(" +
            "username VARCHAR(255) NOT NULL, " +
            "bioText VARCHAR(161), " +  
            "location VARCHAR(255), " + 
            "website VARCHAR(255), " +    
            "PRIMARY KEY (username)" +
            ")";

            statement.execute(sql2);

            String sql3 = "CREATE TABLE IF NOT EXISTS follow(" +
            "username VARCHAR(255) NOT NULL, " +
            "following_id VARCHAR(255) NOT NULL"+ 
            ")";
            statement.execute(sql3);


            String sql4 = "CREATE TABLE IF NOT EXISTS tweet (" +
            "tweetId INT PRIMARY KEY AUTO_INCREMENT, " +
            "username VARCHAR(255) NOT NULL, " +
            "text VARCHAR(255), " +
            "likeCount INT, " +
            "retweetCount INT, " +
            "replyCount INT, " +
            "tweetDate VARCHAR(50), " +
            "tweetTime VARCHAR(50), " +
            "tweetType INT, " +
            "isFavStar BOOLEAN" +
            ")";
            statement.execute(sql4);


            String sql5 = "CREATE TABLE IF NOT EXISTS photo (" +
            "photoId INT AUTO_INCREMENT PRIMARY KEY," +
            "tweetId INT," +
            "filePath VARCHAR(255)" +
            ")";
            statement.execute(sql5);



            

        } catch (ClassNotFoundException | SQLException e) {

            e.printStackTrace();

        } finally {

            // Close the resources
            try {
                if (resultSet != null)
                    resultSet.close();
                if (statement != null)
                    statement.close();
                if (connection != null)
                    connection.close();

            } 
            catch (SQLException e) {
                e.printStackTrace();
            }

        }


    }
}
