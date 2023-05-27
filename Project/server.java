import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;


public class server{
    public static void main(String[] args) {

        //data base manager method 
        databasemanage.DataBaseManagement();

        ExecutorService pool = Executors.newCachedThreadPool();
        //Executors.newFixedThreadPool(10);
        
        try (ServerSocket welcomingSocket = new ServerSocket(5757)) {

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

            //System.out.print("done.\nClosing server ... ");
        } catch (IOException ex) {
            System.err.println(ex);
        }
        finally{
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
            byte[] buffer = new byte[2048];

            //sends starting menu for client
            out.write(menu.ShowHomeMenu().getBytes());

            int read = in.read(buffer);
            String clientMenuChoice = new String(buffer, 0, read).trim();
            
            if(!clientMenuChoice.equals("3")) {
                // Process the client's menu choice
                handleMenuChoice(clientMenuChoice, out);
            }

            else if(clientMenuChoice.equals("3")){
                String exitMessage = "Exiting the app...";
                out.write(exitMessage.getBytes());
            }

            System.out.println("Closing client "+ clientID +" connection...");
            connectionSocket.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void handleMenuChoice(String clientMenuChoice, OutputStream out) throws IOException {

        switch (clientMenuChoice) {
            case "1":
                // Call the SigninMenu method or perform appropriate actions
                String signUpResponse = "Sign Up option selected.";
                out.write(signUpResponse.getBytes());
                break;
            case "2":
                // Call the SignUpMenu method or perform appropriate actions
                String signInResponse = "Sign In option selected.";
                out.write(signInResponse.getBytes());
                break;
            default:
                // Invalid menu choice, handle accordingly
                String errorMessage = "Invalid menu choice. Please try again.";
                out.write(errorMessage.getBytes());
                break;
        }
    }

}
