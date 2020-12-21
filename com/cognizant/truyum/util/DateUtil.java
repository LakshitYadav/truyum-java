package com.cognizant.truyum.util;

import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class DateUtil {
	public static Date convertToDate(String date) 	//static method to convert the input String in ‘dd/MM/yyyy’ format into java.util.Date type
	{
		Date date1=null;
		try {
			date1=new SimpleDateFormat("dd/MM/yyyy").parse(date);
			return date1;
		} catch (ParseException e) {
			return date1;
		}
	}
}
