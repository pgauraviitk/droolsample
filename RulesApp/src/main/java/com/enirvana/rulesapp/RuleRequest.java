package com.enirvana.rulesapp;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class RuleRequest implements Serializable {

	private static final Logger logger = LoggerFactory.getLogger(RuleRequest.class);
	public boolean fireRule;

	public static Map<String, String> 	strAttr = new HashMap<String, String>();
	public static Map<String, Double> 	doubleAttr = new HashMap<String, Double>();
	public static Map<String, Date> 	dateAttr = new HashMap<String, Date>();
	public static Map<String, Double> 	hpiAttr = new HashMap<String, Double>();

	public static String getStrAttr(String attName) {
		return strAttr.get(attName);
	}

	public static void addStrAttr(String key, String value) {
		strAttr.put(key, value);
	}
	
	public static Double getDoubleAttr(String attName) {
		return doubleAttr.get(attName);
	}

	public static void addDoubleAttr(String key, Double value) {
		doubleAttr.put(key, value);
	}

	public static Date getDateAttr(String attName) {
		return dateAttr.get(attName);
	}

	public static void addDateAttr(String key, Date value) {
		dateAttr.put(key, value);
	}

	public boolean isFireRule() {
		return fireRule;
	}

	public void setFireRule(boolean fireRule) {
		this.fireRule = fireRule;
	}


	public static Double hpiValue(Date date) {
		return hpiAttr.get(getMoYr(date));
	}

	public static void addHPIAttr(String key, Double value) {
		hpiAttr.put(key, value);
	}
	
	private static String getMoYr(Date date) {
//		logger.info("Date entered is "+date);
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		String result = (calendar.get(Calendar.MONTH)+1)+"/"+calendar.get(Calendar.YEAR);
//		logger.info("MoYr is "+result);
		return result;
	}
}
