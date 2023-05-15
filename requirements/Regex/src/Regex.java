import java.util.*;
import java.util.regex.*;

public class Regex {

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

    public static void main(String[] args) {

        String test = "salam@gmail.com";

        if(isValidEmail(test)){
            System.out.println("valid");
        }
        else System.out.println("invalid");

    }
}