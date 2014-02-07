package com.enirvana.rulesapp;

import org.slf4j.LoggerFactory;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {
	private static final org.slf4j.Logger logger = LoggerFactory.getLogger(DateUtil.class);
	private static final long milli_sec_in_day = 24*60*60*1000;
	
	private static DateFormat MMddyyyyFormat = new SimpleDateFormat("MM/dd/yyyy");
    private static DateFormat yyyyMMddFormat = new SimpleDateFormat("yyyy-MM-dd");

    public static Date getParsedDate(Date dateStr) {
    	try {
			return MMddyyyyFormat.parse(MMddyyyyFormat.format(dateStr));
		} catch (ParseException e) {
			logger.error(e.getMessage());
		}
    	
    	return null;	
	}
    public static Date getParsedDate(String dateStr) {
    	try {
			return MMddyyyyFormat.parse(dateStr);
		} catch (ParseException e) {
			logger.error(e.getMessage());
		}
    	
    	return null;	
	}
    
	public static Double noOfMonth(Date toDate, Date fromDate) {
		Double diff = Double.MIN_VALUE;
		try {
			fromDate = MMddyyyyFormat.parse(MMddyyyyFormat.format(fromDate));
			toDate = MMddyyyyFormat.parse(MMddyyyyFormat.format(toDate));
			long diffLong = toDate.getTime() - fromDate.getTime();
			Long daysDiff = Long.valueOf(diffLong / (milli_sec_in_day)/30);
			diff = daysDiff.doubleValue();			
		} catch (ParseException e) {
			logger.error(e.getMessage());
		}
		
		return diff;
	}
	
	public static void main(String[] str) {
		String fromDate = "01/24/2014";
		String toDate = "02/28/2014";
		
		try {
			System.out.println(noOfMonth(MMddyyyyFormat.parse(fromDate), MMddyyyyFormat.parse(toDate)));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
