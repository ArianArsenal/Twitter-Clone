package Client;
import Common.*;
import Common.Menus.menu;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Scanner;

public class client {
    public static void main(String[] args) {

        
        System.out.print("\033[H\033[2J");
        System.out.flush();

        try (Socket client = new Socket("127.0.0.1", 5757)) {

            System.out.println("Connected to server.");

            OutputStream out = client.getOutputStream();
            InputStream in = client.getInputStream();
        
            Scanner scan = new Scanner(System.in);

            boolean exit = false;

            while (!exit) {

                // Receives the Starting menu from the server and shows it to the client
                String menuResponse = connection.ClientRecieve(in);
                System.out.println(menuResponse);

                // Read user's menu choice from the console
                String clientMenuChoice = scan.nextLine();

                // Send the user's menu choice to the server
                connection.ClientSend(out, clientMenuChoice);

                // Read the server's response and display it
                String serverResponse = connection.ClientRecieve(in);
                System.out.println("Server response:\n" + serverResponse);

                switch (clientMenuChoice) 
                {
                    case "1":
                    // Client-side Sign up menu
                    menu.ClientSignUpMenu(out, in);
                    break;
                    case "2":
                    // Client-side sign in menu
                    menu.ClientSigninMenu(out, in);
                    break;
                    case "3":
                    // Exit the loop and close the client
                    exit = true;
                    break;
                    default:
                    // Invalid menu choice, display error message
                    System.out.println("Invalid menu choice. Please try again.");
                    break;
                }
            }

            // Close client
            scan.close();
            System.out.print("Closing client ... ");
            client.close();
                
            
        }
        catch (IOException ex) {
            System.err.println(ex);
            System.out.println("\n==========================\nServer is Currently not up\n==========================\n");
        }
            
    
        
    }

}