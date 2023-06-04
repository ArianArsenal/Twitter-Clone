package Common.Menus;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Scanner;

import Common.connection;
import Common.models.TimeLine;

public class timelineMenus {
    
    //client side
    public static void ClientTimeLine(OutputStream out , InputStream in) throws IOException{

        System.out.print("\033[H\033[2J");
        System.out.flush();

        Scanner sc = new Scanner(System.in);

        //gets the TimeLine menu and show it
        String serverRespone = connection.ClientRecieve(in);
        System.out.println(serverRespone);

        //send the user's timeline choice
        String clientTimeLineChoice = sc.nextLine();
        connection.ClientSend(out,clientTimeLineChoice);

        switch (clientTimeLineChoice) {
            case "1":
                //Home Shows tweets of timeline
                break;
            case "2":
                //Shows Search Bar for searching name, lastname, username
                break;
            case "3":
                //Shows the current user profile
                ProfileMenuClient(out,in);
                break;
            case "4":
                //Direct message 
                break;
            case "5":
                //Setting 
                break;
            case "6":
                //Exit 
                System.exit(0);
                break;
            default:
                connection.ServerSend(out, "Incorret Input");
                break;
        }

        sc.close();
        return;

    }

    //server side
    public static void ServerTimeLine(OutputStream out , InputStream in,String username) throws IOException{

        connection.ServerSend(out, "===============\n1-Home\n2-Search\n3-Profile\n4-Message\n5-Setting\n6-Logout\n===============\n");

        //gets the timeline choice from the user
        String TimeLineChoice = connection.ServerRecieve(in);

        switch (TimeLineChoice) {
            case "1":
                //TODO the main timeline menu
                ///@ Create a new Timeline obj ( it has a show method so click on show on it)
                HomeMenuServer(out , in , username);

                break;
            case "2":

                break;
            case "3":
                //goes in the server side of Profile menu
                ProfileMenuServer(out,in);
                break;
            case "4":

                break;
            case "5":

                break;
            case "6":
                
                return;
            default:
                connection.ServerSend(out, "Incorret Input");
                break;
        }

    }

    public static void ProfileMenuClient(OutputStream out , InputStream in) throws IOException{

        Scanner sc = new Scanner(System.in);

        System.out.println(connection.ClientRecieve(in));
        
        String profileoption = sc.nextLine();
        connection.ClientSend(out,profileoption);

        switch (profileoption) {

            case "1":
                
                break;
            case "2":
                
                break;
            case "3":
                
                break;
            case "4":
                
                return;

            default:
                break;
        }

        sc.close();
    }

    public static void ProfileMenuServer(OutputStream out , InputStream in) throws IOException{
        //shows the options

        connection.ServerSend(out,"===============\n1-Edit Bio\n2-Change Avatar\n3-Change Header\n4-Back\n===============\n");

        String profileoption = connection.ServerRecieve(in);
        switch (profileoption) {

            case "1":
                
                break;
            case "2":

                break;

            case "3":

                break;
            case "4":

                return;

            default:
                break;
        }

    }

    public static void HomeMenuServer(OutputStream out , InputStream in, String username){

        //makes a timeline object 
        TimeLine userTimeLine = new TimeLine(username);

        
        

    }

    public static void HomeMenuClient(){

    }


}
