import java.util.*;
import java.util.regex.*;
public class Regex {
    public static void main(String[] args) {
        ArrayList<String> emails = new ArrayList<>();

        //valid types
        emails.add("user@domain.com");
        emails.add("user@domain.co.in");
        emails.add("user1@domain.com");
        emails.add("user.name@domain.com");
        emails.add("user_name@domain.co.in");
        emails.add("user-name@domain.co.in");
        emails.add("user@domaincom");
        emails.add("kirekhar@aut.ac.ir");

        //invalid types
        emails.add("@yahoo.com");
        String regex = "^[A-Za-z0-9+_.-]+@(.+)$";
        Pattern pattern = Pattern.compile(regex);
        for (String email : emails) {
            Matcher matcher = pattern.matcher(email);
            System.out.println(email + " : " + matcher.matches());
        }

    }
}