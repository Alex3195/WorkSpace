package uz.alex.workspace.util;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Date;

public class DateUtil {
    public static Date stringToDate(String dateTimeString) {
        Date date = null;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        try {
            // Parse the date string to LocalDateTime
            LocalDateTime localDateTime = LocalDateTime.parse(dateTimeString, formatter);
            // Convert LocalDateTime to Date
            date = Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
        } catch (DateTimeParseException e) {
            System.err.println("Invalid date-time format: " + e.getMessage());
        }
        return date;
    }
    public static String dateToString(Date date) {
        // Convert Date to LocalDateTime
        LocalDateTime localDateTime = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();

        // Define the date format
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");

        // Format LocalDateTime to String
        return localDateTime.format(formatter);
    }
}
