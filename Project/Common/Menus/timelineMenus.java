package Common.Menus;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Scanner;

import Common.connection;

public class timelineMenus {
    
    //client side
    public static void TimeLine(OutputStream out , InputStream in) throws IOException{

        System.out.print("\033[H\033[2J");
        System.out.flush();

        Scanner sc = new Scanner(System.in);

        // //!TEST
        // System.out.println("Hello World");

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
    public static void TimeLineManagement(OutputStream out , InputStream in) throws IOException{

        connection.ServerSend(out, "===============\n1-Home\n2-Search\n3-Profile\n4-Message\n5-Setting\n===============\n");

        //gets the timeline choice from the user
        String TimeLineChoice = connection.ServerRecieve(in);

        switch (TimeLineChoice) {
            case "1":
                //TODO 

                break;
            case "2":

                break;
            case "3":
                //goes in the server side of Profile menu
                
                break;
            case "4":

                break;
            case "5":

                break;
            default:
                connection.ServerSend(out, "Incorret Input");
                break;
        }




    }


}
