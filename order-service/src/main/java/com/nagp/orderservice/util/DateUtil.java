package com.nagp.orderservice.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

public class DateUtil {
	public static Date getDate(String date, String format, TimeZone tz) throws ParseException {
		if(tz==null) {
			tz = TimeZone.getTimeZone("UTC");
		}
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		sdf.setTimeZone(tz);
		return sdf.parse(date);
	}
}
