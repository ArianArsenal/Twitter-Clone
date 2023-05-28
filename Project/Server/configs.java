package Server;
import java.net.InetAddress;
import java.net.UnknownHostException;

public class configs {

    private static int port = 5757;
    private static String ip;

    static {
        try {
            InetAddress inetAddress = InetAddress.getLocalHost();
            ip = inetAddress.getHostAddress();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
    }

    public static int getPort() {
        return port;
    }

    public static String getIp() {
        return ip;
    }

}
