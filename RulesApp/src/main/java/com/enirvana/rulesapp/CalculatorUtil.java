package com.enirvana.rulesapp;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.Calendar;
import java.util.Date;

public class CalculatorUtil {
	
	protected static MathContext mc = new MathContext(2,RoundingMode.HALF_UP);
	
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
	
	public static BigDecimal divide(BigDecimal a,int b){
		return a.divide(new BigDecimal(b), mc);
	}
	
	public static BigDecimal divide(BigDecimal a,double b){
		return a.divide(new BigDecimal(b), mc);
	}
	
	public static BigDecimal divide(BigDecimal a,BigDecimal b){
		return a.divide(b, mc);
	}
	
	public static BigDecimal multiply(BigDecimal a,int b){
		return a.multiply(new BigDecimal(b), mc);
	}
	
	public static BigDecimal multiply(BigDecimal a,double b){
		return a.multiply(new BigDecimal(b), mc);
	}
	
	public static BigDecimal multiply(BigDecimal a,BigDecimal b){
		return a.multiply(b, mc);
	}
	
	public static BigDecimal multiply(BigDecimal a,BigDecimal... b){
		BigDecimal temp = BigDecimal.ONE;
		for(int i=0;i<b.length;i++){
			temp = temp.multiply(b[i], mc);
		}
		return a.multiply(temp, mc);
	}
	
	public static BigDecimal add(BigDecimal a,int b){
		return a.add(new BigDecimal(b), mc);
	}
	
	public static BigDecimal add(BigDecimal a,double b){
		return a.add(new BigDecimal(b), mc);
	}
	
	public static BigDecimal add(BigDecimal a,BigDecimal b){
		return a.add(b, mc);
	}
	
	public static BigDecimal add(BigDecimal a,BigDecimal... b){
		BigDecimal temp = BigDecimal.ZERO;
		for(int i=0;i<b.length;i++){
			temp = temp.add(b[i], mc);
		}
		return a.add(temp, mc);
	}

}
