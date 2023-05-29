package Server.Logs;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class logs {

    private static final DateTimeFormatter TIME_FORMATTER = DateTimeFormatter.ofPattern("HH:mm");

    public static void SignUpLog(String username) {
        LocalTime currentTime = LocalTime.now();
        LocalDate currentDate = LocalDate.now();

        String logMessage = "User Created: " + username + " | Create Time: " + formatTime(currentTime) + " " + currentDate;
        writeLog(logMessage);
    }

    public static void SignInLog(String username) {
        LocalTime currentTime = LocalTime.now();
        LocalDate currentDate = LocalDate.now();

        String logMessage = "User Signed In: " + username + " | Sign-In Time: " + formatTime(currentTime) + " " + currentDate;
        writeLog(logMessage);
    }

    private static String formatTime(LocalTime time) {
        return time.format(TIME_FORMATTER);
    }

    private static void writeLog(String logMessage) {
        try {
            String filePath = "C:/Users/Arian/OneDrive/Desktop/Progs/Java/AP_Project/Project/Server/Logs/UsersLogs.txt";
            FileWriter fileWriter = new FileWriter(filePath, true);
            fileWriter.write(logMessage + "\n");
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
