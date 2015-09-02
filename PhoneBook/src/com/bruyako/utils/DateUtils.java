package com.bruyako.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtils {
	
	public static Date fromStringToDate(String dateString) throws ParseException{
		SimpleDateFormat formatterSimple = new SimpleDateFormat("yyyy-MM-dd");
        Date date = formatterSimple.parse(dateString);
        return date;
	}
	
}
