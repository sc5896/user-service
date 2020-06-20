package com.nagp.orderservice.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

/**
 * This is a utility class to perform date operations. Contains one method to
 * parse a date string.
 * 
 * @author santoshkumar02
 *
 */
public class DateUtil {
	/**
	 * Returns a date obejct parsed from given date string and format. If no
	 * time zone is passed then default UTC is considered.
	 * 
	 * @param date
	 *            - Date string
	 * @param format
	 *            - format of the date string
	 * @param tz
	 *            - timezone of the date
	 * @return - Date object with given time details.
	 * @throws ParseException
	 */
	public static Date getDate(String date, String format, TimeZone tz) throws ParseException {
		if (tz == null) {
			tz = TimeZone.getTimeZone("UTC");
		}
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		sdf.setTimeZone(tz);
		return sdf.parse(date);
	}
}
