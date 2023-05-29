package Server.Logs;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalTime;

public class logs {

    public static void SignUpLog(String username) { //logs when the user is created
        // Get the current system time (hour and minute)
        LocalTime currentTime = LocalTime.now();

        String logMessage = "User Created: " + username + " | Create Time: " + currentTime;
        writeLog(logMessage);
    }

    public static void SignInLog(String username) {  //logs when the user is signed in
        // Get the current system time (hour and minute)
        LocalTime currentTime = LocalTime.now();

        String logMessage = "User Signed In: " + username + " | Sign-In Time: " + currentTime;
        writeLog(logMessage);
    }

    private static void writeLog(String logMessage) {
        try {
            String filePath = "C:/Users/Arian/OneDrive/Desktop/Progs/Java/AP_Project/Project/Server/Logs/UsersLogs.txt";
            FileWriter fileWriter = new FileWriter(filePath, true); // append to the file
            fileWriter.write(logMessage + "\n");
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
