package com.UITests.UtilityClasses;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAdjusters;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class DateUtils {
	//takes a date and converts it into a new format. Takes 3 parameters: Original Date (e.g. 11/22/2021), Original Format (e.g. MM/dd/yyyy), New Format (e.g. MM/dd/yy)
	//Example: Original Date = "08:20 PM", Original Format = "hh:mm a" (the "a" signifies AM/PM), New Format = "HH:mm" (convert to military time)
	//Example: Original Date = "2022-02-01 23:32:09.797", Original Format = "yyyy-MM-dd HH:mm:ss.SSS", New Format = "yyyy-MM-dd HH:mm"
	//Example: Original Date = "20:54", Original Format = "HH:mm", New Format = "HH"
	//Example: Original Date = "20:54", Original Format = "HH:mm", New Format = "mm"
	//Example: Original Date = "2022-01-18T20:59:18", Original Format = "yyyy-MM-dd'T'HH:mm:ss", New Format = "HH:mm"
	//Example: Original Date = "2022-01-18T20:59:18", Original Format = "yyyy-MM-dd'T'HH:mm:ss", New Format = "MMM d yyyy"
	
	//Warning: if you don't specify a time, this might error. We might need to create a new function
	//reformatDate for strings with no time
	public static String reformat(String originalFormat, String newFormat, String originalDate){
		LocalDateTime oldDate = LocalDateTime.parse(originalDate, 
				DateTimeFormatter.ofPattern(originalFormat));		
		String newDateStr = oldDate.format(DateTimeFormatter.ofPattern(newFormat));
		return newDateStr;
    }
	
	public static String reformatTime(String originalFormat, String newFormat, String originalTime){
		LocalTime oldTime = LocalTime.parse(originalTime,
				DateTimeFormatter.ofPattern(originalFormat));
  	    String newDateStr = oldTime.format(DateTimeFormatter.ofPattern(newFormat));
		return newDateStr;
	}
	
	//takes in 2 String params: DateFormat (e.g. MM/dd/yyyy) and DaysIncrement (e.g. 60). Returns a date back. These values are stored in variable "params" as a Map. Used in Save as Draft - Data Management - 2
	//Example: DateFormat = "yyyy-MM-dd", DaysIncrement=0
	//Example: DateFormat = "yyyy-MM-dd HH:mm", DaysIncrement=14
	//Example: DateFormat = "dd", DaysIncrement = "0"
	//Example: DateFormat = "M", DaysIncrement = "-1"
	//Example: DateFormat = "yyyy-MM-dd HH:mm:ss.SSS", DaysIncrement = 0
	public static String getDate(String dateFormat, int daysIncrement) {
		LocalDateTime futureDate = LocalDateTime.now().plusDays((long)daysIncrement);
		String reformattedDateStr = futureDate.format(DateTimeFormatter.ofPattern(dateFormat));
		
		return reformattedDateStr;	
	}
	
	public static String getCurrentDatetime() {
		return LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
	}
	
	//"Get Day of Month" takes in 2 String params DateString (e.g. 11/12/2021) and Date Format (e.g. MM/dd/yyyy) and returns back a day number. 
	public static String getDayOfMonth(String dateStr, String dateFormat) throws ParseException {
		SimpleDateFormat formatter = new SimpleDateFormat(dateStr);
        Date date = formatter.parse(dateStr);
        
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return String.valueOf(cal.get(Calendar.DAY_OF_MONTH));
	}

	//Action "Get Day Suffix" takes in a String parameter called "Day Number" which is a number from 1-31. and returns the suffix for that number
	public static String getDaySuffix(int dayInt) {		        
		if (dayInt >= 11 && dayInt <= 13) {
			return "th";
		}

		switch (dayInt % 10) {
		case 1:
			return "st";
		case 2:
			return "nd";
		case 3:
			return "rd";
		default:
			return "th";
		}
	}

	//Action "Get Month Name" takes in 2 String params Date String (e.g. 11/12/2021) and Date Format (e.g. MM/dd/yyyy) and returns back a month name.
	public static String getMonthName(String dateStr, String dateFormat) throws ParseException {
		SimpleDateFormat formatter = new SimpleDateFormat(dateStr);
		Date date = formatter.parse(dateStr);

		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		return cal.getDisplayName(Calendar.MONTH, Calendar.LONG, Locale.getDefault());
	}
	
	//Action "Get Year" takes in 2 String params Date String (e.g. 11/12/2021) and Date Format (e.g. MM/dd/yyyy) and returns back a year.
	public static String getYear(String dateStr, String dateFormat) throws ParseException {
		SimpleDateFormat formatter = new SimpleDateFormat(dateStr);
        Date date = formatter.parse(dateStr);

        Calendar cal = Calendar.getInstance();
        cal.setTime(date);      
        return String.valueOf(cal.get(Calendar.YEAR));
	}
	
	public static String getFutureYear(int yearsForward) {
		LocalDateTime futureDate = LocalDateTime.now().plusYears((long)yearsForward);
		String futureYear = futureDate.format(DateTimeFormatter.ofPattern("yyyy"));
		return futureYear;
	}
	
	//"Get Day of Nth Week" takes in 4 parameters: Year (e.g. 2022), Month (e.g. 03), Day of Week 
	//(e.g. SUNDAY), and Nth Week (e.g. 2). Returns back a number representing the day of the Nth 
	//week of the month of that year. (e.g. the 2nd Sunday of March in 2022) would return back 13
	public static String GetDayOfNthWeek(int nthWeek, String dayOfWeek, String month, String year) {
		LocalDate d = LocalDate.of(Integer.parseInt(year), Integer.parseInt(month), 1); //the 1 is arbitrary
        DayOfWeek dayAsEnum = DayOfWeek.valueOf(dayOfWeek.toUpperCase());
        LocalDate d2 = d.with(TemporalAdjusters.dayOfWeekInMonth(nthWeek, dayAsEnum));

        return String.valueOf(d2.getDayOfMonth());
	}
	
	//Pad single digit with 0 (e.g. turn 3 into 03)
	public static String padSingleDigitWithZero(int num) {
		if (num <= 9)
			return "0" + num;
		else
			return String.valueOf(num);
	}
	
	//Get Future Time by Minutes: 2 parameters: Future By Minutes (e.g. 5) and Time Format (e.g. hh:mm a) returns back a time such as 04:20 PM
	public static String getFutureTimeByMinutes(int minutesForward, String timeFormat) {
		LocalDateTime futureTime = LocalDateTime.now().plusMinutes((long)minutesForward);
		String futureTimeStr = futureTime.format(DateTimeFormatter.ofPattern(timeFormat));
		return futureTimeStr;
	}

	//Compare Two Dates Takes in 3 parameters: Date1 (e.g. 2022-02-01 23:32:09.797), Date2 (2022-02-01 23:32), and tolerance_seconds (e.g. 120)
	public static boolean compareTwoDates(String dateStr1, String dateStr2, int tolerance_seconds) {
		
		dateStr1 = dateStr1.replaceFirst(" ", "T"); //replace space with T to make it parseable
		dateStr2 = dateStr2.replaceFirst(" ", "T"); //replace space with T to make it parseable

		LocalDateTime parsedDate1 = LocalDateTime.parse(dateStr1);
		LocalDateTime parsedDate2 = LocalDateTime.parse(dateStr2);


		long delta = ChronoUnit.SECONDS.between(parsedDate1, parsedDate2);
		return (delta <= tolerance_seconds);		
	}
	
	public static int getSecondsBetween(String dateStr1, String dateStr2) {
		dateStr1 = dateStr1.replaceFirst(" ", "T"); //replace space with T to make it parseable
		dateStr2 = dateStr2.replaceFirst(" ", "T"); //replace space with T to make it parseable

		LocalDateTime parsedDate1 = LocalDateTime.parse(dateStr1);
		LocalDateTime parsedDate2 = LocalDateTime.parse(dateStr2);


		long delta = ChronoUnit.SECONDS.between(parsedDate1, parsedDate2);
		return (int)delta;		
	}
	
	public static String reformatDateForFile(String date) {
		//date is of the format "YYYY-MM-DD HH:MM"
        String[] dateComponents = date.split("-"); //YYYY, MM, DD HH:MM
        String[] dayTime = dateComponents[2].split(" "); //DD, HH:MM
        String[] time = dayTime[1].split(":"); //HH, MM
        String formattedDate = dateComponents[0] + dateComponents[1] + dayTime[0] + time[0] + time[1]; //YYYYMMDDHHMM
        return formattedDate;
	}
	
	public static String getTimeInMillis() {
		Date date = new Date();
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		return String.valueOf(cal.getTimeInMillis());
	}

}
