import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Scanner;

public class client {
    public static void main(String[] args) {

        try (Socket client = new Socket("127.0.0.1", 5757)) {

            System.out.println("Connected to server.");

            OutputStream out = client.getOutputStream();
            InputStream in = client.getInputStream();
            byte[] buffer = new byte[2048];

            Scanner scan = new Scanner(System.in);

            // Receives the Starting menu from the server and shows it to the client
            int read = in.read(buffer);
                
            String menuResponse = new String(buffer, 0, read).trim();
            System.out.println(menuResponse);

            // Read user's menu choice from the console
            String clientMenuChoice = scan.nextLine();

            // Send the user's menu choice to the server
            out.write(clientMenuChoice.getBytes());

            //Read the server's response
            read = in.read(buffer);
                
            String serverResponse = new String(buffer, 0, read).trim();

            // Display the server's response
            System.out.println("Server response:\n" + serverResponse);

            //TODO handle the switch case for entering different menus

            switch (clientMenuChoice) {
                case "1":
                    
                    break;
                case "2":

                    break;
                case "3":

                    break;
                default:
                
                    break;
            }


               
            scan.close();
            if(clientMenuChoice.equals("3")){
                
                // Sleep for 3000 with exceptions
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                // Close client
                System.out.print("Closing client ... ");
                client.close();
            }
    
        } catch (IOException ex) {
            System.err.println(ex);
            System.out.println("\n==========================\nServer is Currently not up\n==========================\n");
        }
    }
}
