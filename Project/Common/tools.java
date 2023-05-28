package Common;
import java.util.regex.*;

import com.mysql.cj.protocol.Resultset;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

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

    //TODO a method to check if the userid already exist or not

    public static boolean ExistID(String userid){

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

            //TODO Get all the usernames column from the database USER table and check 
            //TODO 1) if its empty the result set returns null --- > the function returns false == meaning the id doesnt exist so we can sign up
            //TODO 2) if its not empty the result set returns sth --- > the function returns true == meaning the id exist so we cant sign up and ERROR

            //Execute a query to get all the table info and store them in resultset pointer for later use
            resultSet = statement.executeQuery("SELECT * FROM USERS WHERE username = "+ userid + "");

            if((resultSet.getFetchSize() == 0)){
                System.out.println("Its Fine");
                return false;
            }
            else{
                System.out.println("Already exists");
                return true;
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    
        return false;
    }

}
