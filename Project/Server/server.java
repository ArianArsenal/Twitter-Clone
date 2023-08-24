package Server;



import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

import Common.connection;
import Common.tools;
import Common.Menus.menu;
import Common.Menus.timelineMenus;
import Common.models.User;
import Database.databasemanage;
import Server.Logs.logs;


public class server{
    public static void main(String[] args) {

        System.out.print("\033[H\033[2J");
        System.out.flush();

        //data base manager method 
        databasemanage.DataBaseManagement();

        ExecutorService pool = Executors.newCachedThreadPool();
        //Executors.newFixedThreadPool(4);  == fixed number of clients
        
        try (ServerSocket welcomingSocket = new ServerSocket(configs.getPort())) {

            System.out.print("==========================\nServer created.\nShould wait for a client ... \n " + configs.getIp() + ":" + configs.getPort() + "\n==========================\n");

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

            System.out.print("ERROR : Too Many Servers requests \nClosing server ... \n");
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
    public void run(){

        try {
            
            OutputStream out = connectionSocket.getOutputStream();
            InputStream in = connectionSocket.getInputStream();
            
            ObjectOutputStream objectOut = new ObjectOutputStream(out);
            ObjectInputStream objectIn = new ObjectInputStream(in);

            while (true) {

                ///@2

                String option = connection.ServerRecieve(in);
                System.out.println(option);

                switch (option) {

                    case "login":
                        
                        String datas = connection.ServerRecieve(in);

                        if(datas.equals("return")){ ///@1
                            //continue;
                            break;
                        }

                        String[] token = datas.split(",");

                        String username = token[0];
                        String password = token[1];

                        String condition = menu.ServerLoginMenu(out, in,username,password);

                        //sends the result to the client
                        connection.ServerSend(out, condition);
                        
                        if(!condition.equals("success")){

                            String condition3 = "";

                            do {

                            String data = connection.ServerRecieve(in);

                            String[] tokens = data.split(",");
                            datas = null;

                            username = tokens[0];
                            password = tokens[1];

                            condition3 = menu.ServerLoginMenu(out, in,username,password);

                            connection.ServerSend(out, condition3);

                            
                            } while (!condition3.equals("success"));

                        }
                        
                        
                        //gets the user object from database

                        User tstuser = tools.getUser(username);

                        //sends the SignIn Log
                        logs.LoginLog(username);

                        //Enters TimeLine
                        timelineMenus.ServerTimeLine(out, in ,objectIn,objectOut,tstuser);

                        //break;
                    
                    case "signup":

                        ///@ Receive the combined string
                        String data = connection.ServerRecieve(in);

                        if(data.equals("return")){ ///@1
                            continue;
                        }

                        // Tokenize the string using the delimiter to extract individual data pieces
                        String[] tokens = data.split(",");

                        // Extract the data pieces from the tokens array
                        String firstname = tokens[0];
                        String lastname = tokens[1];
                        String username1 = tokens[2];
                        String password1 = tokens[3];
                        String passwordconfirm = tokens[4];
                        String email = tokens[5];
                        String number = tokens[6];
                        String date = tokens[7];
                        String countryoption = tokens[8];
                        
                        //!TEST
                        // System.out.println(firstname);
                        // System.out.println(lastname);
                        // System.out.println(username1);
                        // System.out.println(password1);
                        // System.out.println(passwordconfirm);
                        // System.out.println(email);
                        // System.out.println(number);
                        // System.out.println(date);
                        // System.out.println(countryoption);

                        // for checking conditions
                        String condition1 = menu.ServerSignUpMenu(out, in,firstname,lastname,username1,email,number,password1,passwordconfirm,countryoption,date);

                        ///@ send to client the result
                        connection.ServerSend(out, condition1);

                        
                        if(!condition1.equals("success")){

                            String condition2 = "";

                            do {

                                String data1 = connection.ServerRecieve(in);

                                String[] tokens1 = data1.split(",");
                                data1 = null;

                                // Extract the data pieces from the tokens array
                                firstname = tokens1[0];
                                lastname = tokens1[1];
                                username1 = tokens1[2];
                                password1 = tokens1[3];
                                passwordconfirm = tokens1[4];
                                email = tokens1[5];
                                number = tokens1[6];
                                date = tokens1[7];
                                countryoption = tokens1[8];


                                condition2 = menu.ServerSignUpMenu(out, in,firstname,lastname,username1,email,number,password1,passwordconfirm,countryoption,date);

                                connection.ServerSend(out, condition2);

                                
                            } while (!condition2.equals("success"));

                        }


                        //gets birthdate and tokenize it
                        String tokens1 = date;
                        String[] birthdate = tokens1.split("/"); // 0 = year  1 = month  2 = day

                        //creates user
                        System.out.println("user created");
                        User tst = new User(firstname, lastname, username1, password1, email, number,countryoption ,Integer.parseInt(birthdate[0]) , Integer.parseInt(birthdate[1]),Integer.parseInt(birthdate[2]), null);

                
                        //sends and add user to database
                        System.out.println("inserted into database");
                        tools.InsertUser(tst);
                    
                        //sends the SignUp Log
                        logs.SignUpLog(username1);

                        //enter timeline
                        timelineMenus.ServerTimeLine(out, in,objectIn,objectOut, tst);

                        break;
            
                    case "exit":
                    
                        // Close the connection and exit the loop
                        System.out.println("Closing client " + clientID + " connection...");

                        break;
                        
                    
                    default:

                        System.out.println("Unkown Option - ERROR 404");
                        break;
                }
            
            }
        }
        
        catch (IOException e) {
            // e.printStackTrace();

            System.out.println("Client Closed");
            
        }
        
        finally{

            try {
                connectionSocket.close();

            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

}
