package com.enirvana.rulesapp;

import java.util.Calendar;
import java.util.Date;

public class CalculatorUtil {
	
	public static Double getMonthlyTaxCost() {
		
		return null;
	}
	
	public static void main(String[] str) {
		System.out.println(monthStart(new Date()));
		System.out.println(monthEnd());
	}
	
	public static Date monthStart(Date currDate) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(currDate);
		calendar.set(Calendar.DAY_OF_MONTH,calendar.getActualMinimum(Calendar.DAY_OF_MONTH));
		
		return calendar.getTime();
	}
	
	public static Date monthEnd() {
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.DAY_OF_MONTH,calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
		
		return calendar.getTime();
	}

}
