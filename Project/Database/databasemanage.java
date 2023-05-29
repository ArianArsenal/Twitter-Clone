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


            // statement.execute("INSERT INTO USERS VALUES ('Arian','Pass','EMAIL.COM')");
            // statement.execute("INSERT INTO USERS VALUES ('Arian2','Pass2','EMAIL.COM2')");
            
            //Execute a query to get all the table info and store them in resultset pointer for later use
            // resultSet = statement.executeQuery("SELECT * FROM USERS");

            // //Process the query result
            // while (resultSet.next()) {
            //     // Retrieve data from the result set
            //     String username = resultSet.getString("username");
            //     String password = resultSet.getString("password");

            //     // Do something with the retrieved data
            //     System.out.println(username + " " + password);
            // }

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
