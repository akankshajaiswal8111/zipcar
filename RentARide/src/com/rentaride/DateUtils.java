package com.rentaride;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.TimeZone;

public class DateUtils {
	
	public ArrayList<String> getDateAndTimeComponents(String dateStr)
	{
		ArrayList<String> arrDt =new ArrayList<>();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date dtDateTime;
		try {
			dtDateTime = dateFormat.parse(dateStr);
			
			DateFormat date = new SimpleDateFormat("dd MMM YYYY");
			DateFormat time = new SimpleDateFormat("hh:mm a");
			
			arrDt.add(date.format(dtDateTime).toString());
			arrDt.add(time.format(dtDateTime).toString());
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		return arrDt;
	}
	
	public Date getDateComponent(String dateStr)
	{
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd MMM yyyy");
		Date dt = null;
		try {
			dt = dateFormat.parse(dateStr);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return dt;
	}
	
	public ArrayList<LocalDateTime> getDateTime(String frmDtStr, String toDtStr)
	{
		ArrayList<LocalDateTime> arrDtTime = new ArrayList<LocalDateTime>();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.S");
		LocalDateTime dateTime1= LocalDateTime.parse(frmDtStr, formatter);
		LocalDateTime dateTime2= LocalDateTime.parse(toDtStr, formatter);
		arrDtTime.add(dateTime1);
		arrDtTime.add(dateTime2);
		return arrDtTime;
	}

	
	public ArrayList<LocalDateTime> getLocalDateTime(String dtStr)
	{
		ArrayList<LocalDateTime> arrDtTime = new ArrayList<LocalDateTime>();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMM yyyy hh:mm a");
		LocalDateTime valDateTime= LocalDateTime.parse(dtStr, formatter);
		LocalDateTime todayDateTime = LocalDateTime.parse(formatter.format(LocalDateTime.now()).toString(),formatter);
		arrDtTime.add(valDateTime);
		arrDtTime.add(todayDateTime);
		return arrDtTime;
	}
}
