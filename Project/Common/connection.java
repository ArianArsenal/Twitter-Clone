package Common;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;


public class connection{

    //*Client side

    public static void ClientSend(OutputStream out, String input) throws IOException {
        out.write(input.getBytes());
    }

    public static String ClientRecieve(InputStream in) throws IOException {
        byte[] buffer = new byte[2048];
        int read = in.read(buffer);
        return new String(buffer, 0, read).trim();
    }

    //*Server side

    public static void ServerSend(OutputStream out, String input) throws IOException {
        out.write(input.getBytes());
    }

    public static String ServerRecieve(InputStream in) throws IOException {
        byte[] buffer = new byte[2048];
        int read = in.read(buffer);
        return new String(buffer, 0, read).trim();
    }
    
}

//getting input ways
/* 1)
 *  int read = in.read(buffer);
 *  String menuResponse = new String(buffer, 0, read).trim();
 * 
 * 2)
 * String menuResponse = connection.ClientRecieve(in);
 *
 * 
 */

//sending output ways
/*
 * 1)
 * out.write(clientMenuChoice.getBytes());
 * 
 * 2)
 * connection.ClientSend(out, clientMenuChoice);
 * 
 */

/*
 * In Both ways the 
 * OutputStream out = client.getOutputStream();
 * InputStream in = client.getInputStream();
 * are neccasary to be written at first
 * 
 * 
 * but declaring the 
 * byte[] buffer = new byte[2048];
 * is only for way 1)
 * in the way 2) these are implemented within the function and can be used anywhere
 * 
 */