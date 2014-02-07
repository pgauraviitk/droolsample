package com.enirvana.rulesapp;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class RuleResult {
	public static Map<String, Double> 	doubleAttr = new HashMap<String, Double>();
	public static Map<String, Date> 	dateAttr = new HashMap<String, Date>();
	public static double[] cashArr;
	
	public static double[] getCashArr() {
		return cashArr;
	}

	public static void setCashArr(double[] cashArr) {
		RuleResult.cashArr = cashArr;
	}

	public static Double getDoubleAttrRes(String attName) {
		return doubleAttr.get(attName);
	}

	public static void addDoubleAttrRes(String key, Double value) {
		doubleAttr.put(key, value);
	}

	public static Date getDateAttrRes(String attName) {
		return dateAttr.get(attName);
	}

	public static void addDateAttrRes(String key, Date value) {
		dateAttr.put(key, value);
	}
	
	public String getCashArrStr() {
		StringBuilder sb = new StringBuilder("{");
		for(int i = 0; i < cashArr.length; i++) {
			sb.append(cashArr[i]);
			if(i != cashArr.length-1)
				sb.append(", ");
		}
		
		sb.append("}");
		
		return sb.toString();
	}

	@Override
	public String toString() {
		return "RuleResult [" + doubleAttr +" " + dateAttr + getCashArrStr() + "]";
	}
	
}
