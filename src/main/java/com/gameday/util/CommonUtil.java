package com.gameday.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class CommonUtil {
	
	public static String formatDate(Date date) {
		
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss a");
		
		return simpleDateFormat.format(date);
	}
}
