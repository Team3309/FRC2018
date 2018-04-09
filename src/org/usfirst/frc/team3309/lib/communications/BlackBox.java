package org.usfirst.frc.team3309.lib.communications;

import edu.wpi.first.wpilibj.DriverStation;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

public class BlackBox {

    // path writes to USB connected to roboRio

    // /media/sda1/Logs
    private static final String logPath = "/home/lvuser/Logs/";
    private static PrintWriter pw;

    public static void initLog(String title, String... headers) {
        DateFormat simpleFormat = new SimpleDateFormat("yyyy/MM/dd");
        LocalDateTime time = LocalDateTime.now();
        int hour = time.getHour();
        int min = time.getMinute();
        int sec = time.getSecond();
        String timeString = hour + "H " + min + "M " + sec + "S";
        File file = new File(logPath + "3309_"
                + String.valueOf(DriverStation.getInstance().getAlliance()) + "_"
                + title + "_" + LocalDate.now() + "_" + timeString + ".csv");
        try {
            if (!file.getParentFile().exists()) {
                file.getParentFile().mkdirs();
            }
            file.createNewFile();
            pw = new PrintWriter(file);
            StringBuilder headersString = new StringBuilder("Time,");
            for (String header : headers) {
                headersString.append(header).append(",");
            }
            pw.println("Team 3309");
            pw.println(simpleFormat.format(new Date()));
            pw.println(headersString);
            pw.flush();
        } catch (IOException e) {
            // e.printStackTrace();
        }

    }

    public static void writeLog(String... data) {
        if (pw != null) {
            StringBuilder input = new StringBuilder();
            for (String val : data) {
                input.append(val).append(",");
            }
            DateFormat simpleFormat = new SimpleDateFormat("HH:MM:ss");
            pw.println(simpleFormat.format(new Date()) + "," + input);
            pw.flush();
        }
    }

}
