package Common;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.regex.*;

import Common.models.User;

// import com.mysql.cj.protocol.Resultset;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
//import java.sql.Statement;

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

    

    public static List<String> CountryList() {
        
        List<String> countryList = new ArrayList<>();
    
        String[] countryCodes = Locale.getISOCountries();
    
        for (String countryCode : countryCodes) {
            Locale.Builder localeBuilder = new Locale.Builder();
            Locale locale = localeBuilder.setRegion(countryCode).build();
            String countryName = locale.getDisplayCountry();
            countryList.add(countryName);
        }
    
        return countryList;
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
            String query = "INSERT INTO USERS VALUES (?,?,?,?,?,?,?,?,?)";

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

    //search user 


}
