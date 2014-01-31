package com.enirvana.rulesapp;

import org.apache.poi.ss.formula.functions.FinanceLib;
import org.apache.poi.ss.formula.functions.Irr;

public class POITest {

	public static void main(String[] str) {
		double[] cashFlows = {-127.61,-127.61,58448.43};
		double rate = Double.valueOf(15D)/Double.valueOf(1200D);
		Double npv = FinanceLib.npv(rate, cashFlows);
		
		System.out.println(npv-127.61);
		
		double[] income = {200,-10000};
		double irrrate = Double.valueOf(0.01);
		double irr = Irr.irr(income,irrrate);
		System.out.println(irr*12);
	}
}
