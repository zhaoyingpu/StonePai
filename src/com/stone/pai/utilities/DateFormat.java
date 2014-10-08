package com.stone.pai.utilities;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateFormat {
	public static String toString(Date str) {
		SimpleDateFormat lastYearFormat = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat thisYearFormat = new SimpleDateFormat("M‘¬d»’");
		SimpleDateFormat todayFormat = new SimpleDateFormat("h:m");
		Date today = new Date();
		if (str.getYear() < today.getYear())
			return lastYearFormat.format(str);
		else if (str.getMonth() < today.getMonth() || str.getDate() < today.getDate())
			return thisYearFormat.format(str);
		else
			return todayFormat.format(str);
	}
}
