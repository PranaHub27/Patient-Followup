package databasecon;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class AddDaysToDate {
	public static String addDaysToDate(int days) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Calendar cal = Calendar.getInstance();
		System.out.println("Current Date: " + sdf.format(cal.getTime()));

		cal.add(Calendar.DAY_OF_MONTH, days);
		String newDate = sdf.format(cal.getTime());
		System.out.println("Date after Addition: " + newDate);
		return newDate;
	}

	public static void main(String[] args) {
		String medicationDuration = "4";
		AddDaysToDate.addDaysToDate(Integer.parseInt(medicationDuration));
		addDaysToDate(7);
	}

}
