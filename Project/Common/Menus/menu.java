package Common.Menus;
import java.util.List;
import java.util.Scanner;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import Common.connection;
import Common.tools;
import Common.models.User;
import Server.Logs.logs;



public class menu {

    public static String ShowHomeMenu(){ //Menu that shows the login-sign in - exist options
        return "===============\n1-Sign-Up\n2-Sign-In\n3-Exit\n===============\n";
    }

    public static void ClientSigninMenu(OutputStream out , InputStream in) throws IOException {    //Client-side sign in menu

        System.out.print("\033[H\033[2J");
        System.out.flush();

        Scanner sc = new Scanner(System.in);

        String serverResponse = "";

        boolean validUsername = false;
        while (!validUsername){

            serverResponse = connection.ClientRecieve(in);
            System.out.println(serverResponse);
            
            //Sends username
            connection.ClientSend(out, sc.nextLine());

            serverResponse = connection.ClientRecieve(in);
            
            if(serverResponse.equals("OK")){
                validUsername = true; // ok valid username provided, exit the loop
                
            }

            if (serverResponse.equals("This Username does not exist")) {
                System.out.println(serverResponse);
                validUsername = false; 
            }
        }

        boolean validPassword = false;
        while (!validPassword){

            serverResponse = connection.ClientRecieve(in);
            System.out.println(serverResponse);
            
            //Sends password
            connection.ClientSend(out, sc.nextLine());

            serverResponse = connection.ClientRecieve(in);
            
            if(serverResponse.equals("OK")){
                validPassword = true; // ok valid password provided, exit the loop
                
            }

            if (serverResponse.equals("Incorrect Password")) {
                System.out.println(serverResponse);
                validPassword = false; 
            }
        }

        // //sends the SignIn Log
        // logs.SignInLog(username);

        //! TEST
        System.out.println("Ok");

        //Enters TimeLine 
        timelineMenus.ClientTimeLine(out, in);

        sc.close();
    }

    public static void ClientSignUpMenu(OutputStream out , InputStream in) throws IOException {    //Client-side sign up menu

        System.out.print("\033[H\033[2J");
        System.out.flush();

        Scanner sc = new Scanner(System.in);

        String serverResponse = connection.ClientRecieve(in);
        System.out.println(serverResponse);

        //Sends name
        connection.ClientSend(out, sc.nextLine());

        serverResponse = connection.ClientRecieve(in);
        System.out.println(serverResponse);
        
        //Sends lastname
        connection.ClientSend(out, sc.nextLine());

        boolean validUsername = false;
        while (!validUsername){

            serverResponse = connection.ClientRecieve(in);
            System.out.println(serverResponse);
            
            //Sends username
            connection.ClientSend(out, sc.nextLine());

            serverResponse = connection.ClientRecieve(in);
            
            if(serverResponse.equals("OK")){
                validUsername = true; // Unique username provided, exit the loop
            }

            if (serverResponse.equals("This username is already taken. Please enter another username.")) {
                System.out.println(serverResponse);
                validUsername = false; 
            }
        }

        boolean validPass = false;
        while (!validPass){

            serverResponse = connection.ClientRecieve(in);
            System.out.println(serverResponse);
            
            //Sends Password
            connection.ClientSend(out, sc.nextLine());

            serverResponse = connection.ClientRecieve(in);
            
            if(serverResponse.equals("OK")){
                validPass = true; // ok password provided, exit the loop
            }

            if (serverResponse.equals("This Password does not meet the Password policy requirements. Please enter another Password.")) {
                System.out.println(serverResponse);
                validPass = false; 
            }
        }

        boolean validConfirmPass = false;
        while (!validConfirmPass){

            serverResponse = connection.ClientRecieve(in);
            System.out.println(serverResponse);
            
            //Sends Confirm Password
            connection.ClientSend(out, sc.nextLine());

            serverResponse = connection.ClientRecieve(in);
            
            if(serverResponse.equals("OK")){
                validConfirmPass = true; // ok Confirm password provided, exit the loop
            }

            if (serverResponse.equals("This Password is not the same as your password. Please re-enter your Password.")) {
                System.out.println(serverResponse);
                validConfirmPass = false; 
            }
        }

        int EmailOrPhonenumber = 0 ;

        boolean validEmail = false;
        while (!validEmail){

            serverResponse = connection.ClientRecieve(in);
            System.out.println(serverResponse);
            
            //Sends email
            connection.ClientSend(out, sc.nextLine());

            serverResponse = connection.ClientRecieve(in);
            
            if(serverResponse.equals("OK")){
                validEmail = true; // ok valid email provided, exit the loop
                EmailOrPhonenumber++ ;
            }

            if (serverResponse.equals("Your Email is not valid")) {
                System.out.println(serverResponse);
                validEmail = false; 
            }
        }


        boolean validPhonenumber = false;
        while (!validPhonenumber){

            serverResponse = connection.ClientRecieve(in);
            System.out.println(serverResponse);
            
            //Sends Phonenumber
            connection.ClientSend(out, sc.nextLine());

            serverResponse = connection.ClientRecieve(in);
            
            if(serverResponse.equals("OK")){
                validPhonenumber = true; // ok valid phonenumber provided, exit the loop
                EmailOrPhonenumber++ ;
            }

            if (serverResponse.equals("Your Phonenumber is already taken")) {
                System.out.println(serverResponse);
                validPhonenumber = false; 
            }
        }

        //!EmailOrPhonenumber should be 1 at least or can be 2

        if(EmailOrPhonenumber == 0 ){
            //!ERROR
            System.out.println("error");
        }

        //!TEST
        System.out.println("test done");

        //gets the country list from the server
        serverResponse = connection.ClientRecieve(in);
        System.out.println(serverResponse);

        //send the country
        connection.ClientSend(out,sc.nextLine());
        
        
        serverResponse = connection.ClientRecieve(in);
        System.out.println(serverResponse);

        //Sends birthdate to server
        connection.ClientSend(out, sc.nextLine());

        ///@ DONE the user gets created and added to Database

        //!TEST
        serverResponse = connection.ClientRecieve(in);
        System.out.println(serverResponse);

        //Enters TimeLine 
        timelineMenus.ClientTimeLine(out, in);

        sc.close();
    }

    public static void ServerSigninMenu(OutputStream out , InputStream in) throws IOException{    //user enters its username and password to be checked at the database

        boolean usernameExists = false;
        String username = "";

        do {
            connection.ServerSend(out, "Enter Your Username: ");

            //gets username
            username = connection.ServerRecieve(in);
    
            usernameExists = tools.ExistID(username);
            if (!usernameExists) {
                // Username already exists, inform the client and prompt for a new username
                connection.ServerSend(out, "This Username does not exist");
            }
            else {
                connection.ServerSend(out, "OK");
            }

        } while (!usernameExists);

        boolean passwordExists = false;
        String password = "";

        do {
            connection.ServerSend(out, "Enter Your Password: ");

            //gets password
            password = connection.ServerRecieve(in);
    
            passwordExists = tools.ExistPassword(username,password);
            if (!passwordExists) {
                // password doesnt exist
                connection.ServerSend(out, "Incorrect Password");
            }
            else {
                connection.ServerSend(out, "OK");
            }

        } while (!passwordExists);

        //sends the SignIn Log
        logs.SignInLog(username);

        //Enters TimeLine
        timelineMenus.ServerTimeLine(out, in ,username);

    }


    /*
     * 1)gets the inputs from user
     * 2)check the inputs conditions (Password , Regex , Existing in DB)
     * 3)make new user object
     * 4)store the user object in Database
     */

    public static void ServerSignUpMenu(OutputStream out , InputStream in) throws IOException {    //user creates account and regex and password should be checked

        connection.ServerSend(out, "Enter Your First Name : ");

        //gets name
        String name = connection.ServerRecieve(in);

        connection.ServerSend(out,"Enter Your Last Name : ");

        //gets lastname
        String lastname = connection.ServerRecieve(in);


        boolean usernameExists = false;
        String username = "";

        do {
            connection.ServerSend(out, "Enter Your Username: ");

            //gets username
            username = connection.ServerRecieve(in);
    
            usernameExists = tools.ExistID(username);
            if (usernameExists) {
                // Username already exists, inform the client and prompt for a new username
                //System.out.println("Username already taken");
                connection.ServerSend(out, "This username is already taken. Please enter another username.");
            }
            else {
                connection.ServerSend(out, "OK");
            }

        } while (usernameExists);

        boolean passwordCheck = false;
        String password = "";

        do {
            connection.ServerSend(out, "Enter Your Password: (8 characters and at least 1 uppercase letter) ");

            //gets username
            password = connection.ServerRecieve(in);
    
            passwordCheck = tools.passwordChecker(password);
            if (!passwordCheck) {
                
                connection.ServerSend(out, "This Password does not meet the Password policy requirements. Please enter another Password.");
            }
            else {
                connection.ServerSend(out, "OK");
            }

        } while (!passwordCheck);

        boolean ConfirmpasswordCheck = false;
        String Confirmpassword = "";

        do {
            connection.ServerSend(out, "Confrim your Passwod: ");

            //gets confirm password
            Confirmpassword = connection.ServerRecieve(in);
    
            ConfirmpasswordCheck = Confirmpassword.equals(password);
            if (!ConfirmpasswordCheck) {
                
                connection.ServerSend(out, "This Password is not the same as your password. Please re-enter your Password.");
            }
            else {
                connection.ServerSend(out, "OK");
            }

        } while (!ConfirmpasswordCheck);

        boolean emailcheck = false;
        String email = "";

        do {
            connection.ServerSend(out, "Enter Your Email: ");

            //gets email
            email = connection.ServerRecieve(in);
    
            emailcheck = ( tools.isValidEmail(email) && !tools.ExistEmail(email) );
            if (!emailcheck) {
                
                connection.ServerSend(out, "Your Email is not valid");
            }
            else {
                connection.ServerSend(out, "OK");
            }

        } while (!emailcheck);

        boolean phonenumberCheck = false;
        String phonenumber = "";

        do {
            connection.ServerSend(out, "Enter Your Phonenumber: ");

            //gets phonenumber
            phonenumber = connection.ServerRecieve(in);
    
            phonenumberCheck = tools.ExistPhonenumber(phonenumber);
            if (phonenumberCheck) {
                
                connection.ServerSend(out, "Your Phonenumber is already taken");
            }
            else {
                connection.ServerSend(out, "OK");
            }

        } while (phonenumberCheck);

        // Get country list
        List<String> countryList = tools.CountryList();

        // Display country list to the user
        StringBuilder countryOptions = new StringBuilder("Select Your Country:\n");
        for (int i = 0; i < countryList.size(); i++) {
            countryOptions.append(i + 1).append(". ").append(countryList.get(i)).append("\n");
        }
        connection.ServerSend(out, countryOptions.toString());

        // Get user's country selection
        int countryIndex = Integer.parseInt(connection.ServerRecieve(in));

        // Validate and set the user's country
        String country = "";

        if (countryIndex >= 1 && countryIndex <= countryList.size()) {
            country = countryList.get(countryIndex - 1);
        }


        //sends birthdate text
        connection.ServerSend(out, "Enter Your Birthdate (YY/MM/DD) : ");

        //gets birthdate and tokenize it
        String tokens = connection.ServerRecieve(in);
        String[] birthdate = tokens.split("/"); // 0 = year 1 = month 2 =day

        User tst = new User(name, lastname, username, password, email, phonenumber,country ,Integer.parseInt(birthdate[0]) , Integer.parseInt(birthdate[1]),Integer.parseInt(birthdate[2]));

        //sends and add user to database

        tools.InsertUser(tst);

        //sends the SignUp Log
        logs.SignUpLog(username);

        //!TEST
        connection.ServerSend(out,name +" "+ lastname + " " + username + " " +
        password + " " + Confirmpassword + " " + email + " " + phonenumber);

        
        //enter timeline
        timelineMenus.ServerTimeLine(out, in, username);
        
        
    }

}

