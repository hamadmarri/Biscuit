package com.biscuit.models.services;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateService {

	public static transient SimpleDateFormat dateFormat = new SimpleDateFormat("MMM. dd, yyyy");


	public static boolean isSet(Date d) {
		return (d.compareTo(new Date(0)) > 0);
	}


	public static String getDateAsString(Date d) {
		if (DateService.isSet(d)) {
			return DateService.dateFormat.format(d);
		}
		return "Not set yet";
	}

}
