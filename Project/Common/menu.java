package Common;
import java.util.Scanner;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;


public class menu {

    public static void ClientSigninMenu(OutputStream out , InputStream in) throws IOException {    //Client-side sign in menu

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

        serverResponse = connection.ClientRecieve(in);
        System.out.println(serverResponse);

        //Sends username
        connection.ClientSend(out, sc.nextLine());



        //!TEST
        serverResponse = connection.ClientRecieve(in);
        System.out.println(serverResponse);

        //TODO --->>

        sc.close();
    }

    public static void ServerSigninMenu(OutputStream out , InputStream in) throws IOException{    //user enters its username and password to be checked at the database

    }


    /*
     * 1)gets the inputs from user
     * 2)check the inputs conditions (Password , Regex , Existing in DB)
     * 3)make new user object
     * 4)store the user object in Database
     */

    public static void ServerSignUpMenu(OutputStream out , InputStream in) throws IOException {    //user creates account and regex and password should be checked

        String cmd = "Enter Your First Name : ";
        connection.ServerSend(out, cmd);

        //gets name
        String name = connection.ServerRecieve(in);

        connection.ServerSend(out,"Enter Your Last Name : ");

        //gets lastname
        String lastname = connection.ServerRecieve(in);

        connection.ServerSend(out,"Enter Your Username : ");

        //gets username 
        String username = connection.ServerRecieve(in);

        if(tools.ExistID(username) == true){

            //! ERROR
            //in server cmd
            System.out.println("Already Exist");

            connection.ServerSend(out,"This Username is already taken");

        }
        //in server cmd
        else System.out.println("Fine");

        ///@ Check if the user already exist



        //!TEST
        connection.ServerSend(out,name +" "+ lastname);

        //TODO --->>

        
        
    }

    public static String ShowHomeMenu(){ //Menu that shows the login-sign in - exist options
        return "===============\n1-Sign-Up\n2-Sign-In\n3-Exit\n===============\n";
    }

}
