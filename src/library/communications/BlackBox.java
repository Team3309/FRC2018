package library.communications;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

public class BlackBox {

	private static final String logPath = "/media/sda1/Logs/";
	private static PrintWriter pw;

	public static void initLog(String title, String... headers) {
		DateFormat simpleFormat = new SimpleDateFormat("yyyy/MM/dd");
		LocalDateTime time = LocalDateTime.now();
		int hour = time.getHour();
		int min = time.getMinute();
		int sec = time.getSecond();
		String timeString = hour + "H " + min + "M " + sec + "S";
		File file = new File(logPath + title + " " + LocalDate.now() + " " + timeString + ".csv");
		try {
			if (!file.getParentFile().exists()) {
				file.getParentFile().mkdirs();
			}
			file.createNewFile();
			pw = new PrintWriter(file);
			String headersString = "Time,";
			for (int i = 0; i < headers.length; i++) {
				headersString += headers[i] + ",";
			}
			pw.println("Team 3309");
			pw.println(simpleFormat.format(new Date()));
			pw.println(headersString);
			pw.flush();
		} catch (IOException e) {
		//	e.printStackTrace();
		}

	}

	public static void writeLog(String... data) {
		if (pw != null) {
			String input = "";
			for (int i = 0; i < data.length; i++) {
				input += data[i] + ",";
			}
			DateFormat simpleFormat = new SimpleDateFormat("HH:MM:ss");
			pw.println(simpleFormat.format(new Date()) + "," + input);
			pw.flush();
		}
	}

}
