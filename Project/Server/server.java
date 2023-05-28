package Server;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

import Common.*;
import Database.databasemanage;

public class server{
    public static void main(String[] args) {

        System.out.print("\033[H\033[2J");
        System.out.flush();

        //data base manager method 
        databasemanage.DataBaseManagement();

        ExecutorService pool = Executors.newCachedThreadPool();
        //Executors.newFixedThreadPool(4);  == fixed number of clients
        
        try (ServerSocket welcomingSocket = new ServerSocket(configs.getPort())) {

            System.out.print("==========================\nServer created.\nShould wait for a client ... \n==========================\n");

            int clientID = 1;
            while (true) {

                Socket connectionSocket = welcomingSocket.accept();
                System.out.println("client "+ clientID +" accepted!");

                // Check the current thread pool size
                ThreadPoolExecutor executor = (ThreadPoolExecutor) pool;
                int poolSize = executor.getPoolSize();

                // If the pool is too small, increase the maximum pool size
                if (poolSize == executor.getMaximumPoolSize()) {
                    executor.setCorePoolSize(poolSize + 1);
                    executor.setMaximumPoolSize(poolSize + 1);
                }

                pool.execute(new ClientHandler(connectionSocket,clientID));

                clientID++;
            }

        } catch (IOException ex) {
            System.err.println(ex);
        }
        finally{

            System.out.print("ERROR : Too Many Clients\nClosing server ... \n");
            pool.shutdown();
        }

        System.out.println("Server Shut Down");
    }

}

class ClientHandler implements Runnable {

    private Socket connectionSocket;
    private int clientID;

    public ClientHandler(Socket connectionSocket, int clientID) {
        this.connectionSocket = connectionSocket;
        this.clientID = clientID;
    }

    @Override
    public void run() {
        try {
            OutputStream out = connectionSocket.getOutputStream();
            InputStream in = connectionSocket.getInputStream();
            
            //sends starting menu for client
            connection.ServerSend(out, menu.ShowHomeMenu());
            //gets menu choice from client
            String clientMenuChoice = connection.ServerRecieve(in);
            
            if(!clientMenuChoice.equals("3")) {
                // Process the client's menu choice
                handleMenuChoice(clientMenuChoice, out,in);
            }

            else if(clientMenuChoice.equals("3")){
                String exitMessage = "Exiting the app...";
                connection.ServerSend(out, exitMessage);
            }

            System.out.println("Closing client "+ clientID +" connection...");
            connectionSocket.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void handleMenuChoice(String clientMenuChoice, OutputStream out,InputStream in) throws IOException {

        switch (clientMenuChoice) {
            case "1":
                // Call the SigninMenu method or perform appropriate actions
                String signUpResponse = "Sign Up option selected.";
                connection.ServerSend(out, signUpResponse);

                menu.ServerSignUpMenu(out, in);
                //TODO

                break;
            case "2":
                // Call the SignUpMenu method or perform appropriate actions
                String signInResponse = "Sign In option selected.";
                connection.ServerSend(out, signInResponse);

                menu.ServerSigninMenu(out, in);
                //TODO

                break;
            default:
                // Invalid menu choice, handle accordingly
                String errorMessage = "Invalid menu choice. Please try again.";
                connection.ServerSend(out, errorMessage);
                break;
        }
    }

}
