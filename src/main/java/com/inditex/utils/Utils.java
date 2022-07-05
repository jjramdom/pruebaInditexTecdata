package com.inditex.utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Utils {
	
	private Utils() {
	    throw new IllegalStateException("Utils class");
	 }
	/**
	 * @param value
	 * @return
	 */
	public static LocalDateTime formatDateTime(Object value) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern(Constants.DATE_TIME_FORMAT); 
		return LocalDateTime.parse(value.toString(), formatter);
	}


}
