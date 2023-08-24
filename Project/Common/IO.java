package Common;


import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;

import Server.configs;


public class IO {

    public static OutputStream out;
    public static InputStream in;
    public static Socket socket;

    public static ObjectOutputStream objectOut;
    public static ObjectInputStream objectIn;
    
    public static void start() throws IOException {

        socket = new Socket("127.0.0.1", configs.getPort());
        out = socket.getOutputStream();
        in = socket.getInputStream();


        //always cast when getting objects
        objectOut = new ObjectOutputStream(out);
        objectIn = new ObjectInputStream(in);

    }
}
