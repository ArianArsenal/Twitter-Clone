package Common.Menus;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import Common.tools;





public class menu {

    public static String ServerLoginMenu(OutputStream out , InputStream in,String username,String password) throws IOException{    //user enters its username and password to be checked at the database

        //check if username exist
        boolean usernamecheck = tools.ExistID(username);

        //check if password for that username is the same
        boolean passwordExists = tools.ExistPassword(username,password);

        if(usernamecheck == true && passwordExists == true){

            return "success";
        }

        else if (usernamecheck == true && passwordExists == false ){

            return "wrong-pass";
        }

        else {
            return "not-found";
        }

    }

    /*
     * 1)gets the inputs from user
     * 2)check the inputs conditions (Password , Regex , Existing in DB)
     * 3)make new user object
     * 4)store the user object in Database
     */

    public static String ServerSignUpMenu(OutputStream out , InputStream in,String firstname , String lastname , String username , String email, String number , String password,String passwordconfirm , String countryoption , String date) throws IOException {    //user creates account and regex and password should be checked

        //check if username exist
        boolean usernameExists = tools.ExistID(username);


        //check password format
        boolean passwordCheck = tools.passwordChecker(password);

        //check if password is the same as confirm password
        boolean passwordConfirmCheck = password.equals(passwordconfirm);

        //check if email is valid and new
        boolean emailcheck = ( tools.isValidEmail(email) && !tools.ExistEmail(email) );

        //check if number is new
        boolean numberCheck = tools.ExistPhonenumber(number);

        if(usernameExists == true){
            return "username-already-exists";
        }

        else if(passwordCheck == false){
            return "password-not-valid";
        }
        
        else if(passwordConfirmCheck == false){
            return "password-not-match";
        }

        else if(emailcheck == false){
            return "email-not-valid";
        }
        
        else if (numberCheck == true){
            return "number-already-exists";
        }
        //both email and number can't be empty at the same time ( but one of them can be empty)
        else if(number.isEmpty() && email.isEmpty()){
            return "enter-email-or-number";
        }
        else {
            return "success";
        }

    }

}

