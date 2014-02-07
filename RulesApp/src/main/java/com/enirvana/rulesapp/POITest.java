package com.enirvana.rulesapp;

import org.jquantlib.cashflow.CashFlows;
import org.jquantlib.cashflow.Leg;
import org.jquantlib.cashflow.SimpleCashFlow;
import org.jquantlib.daycounters.SimpleDayCounter;
import org.jquantlib.termstructures.Compounding;
import org.jquantlib.termstructures.InterestRate;
import org.jquantlib.time.DateParser;

import java.util.Collections;

public class POITest {

	public static void main(String[] str) {
//		double[] cashFlows = {-127.61,-127.61,58448.43};
		double rate = Double.valueOf(15D)/Double.valueOf(1200D);
//		Double npv = FinanceLib.npv(rate, cashFlows);
//
//		System.out.println(npv-127.61);
//
//		double[] income = {200,-10000};
//		double irrrate = Double.valueOf(0.01);
//		double irr = Irr.irr(income,irrrate);
//		System.out.println(irr*12);


        Leg leg = new Leg();
//        leg.add(new SimpleCashFlow(-127.61, DateParser.parse("2013/10/01","yyyy/MM/dd")));
        leg.add(new SimpleCashFlow(-127.61, DateParser.parse("2013/11/01", "yyyy/MM/dd")));
        leg.add(new SimpleCashFlow(-127.61, DateParser.parse("2013/12/01", "yyyy/MM/dd")));
        leg.add(new SimpleCashFlow(58448.43, DateParser.parse("2014/01/01", "yyyy/MM/dd")));

        InterestRate interestRate = new InterestRate(rate, new SimpleDayCounter());

        CashFlows cashFlows = CashFlows.getInstance();
        double npv = cashFlows.npv(leg,interestRate);

        System.out.println(npv);
        System.out.println(npv-127.61);

        Collections.shuffle(leg);

        npv = cashFlows.npv(leg,interestRate, DateParser.parse("2013/08/27", "yyyy/MM/dd"));

        System.out.println(npv);
        System.out.println(npv-127.61);

        double marketPrice = 75000.0;
        double irr = cashFlows.irr(leg,marketPrice,new SimpleDayCounter(), Compounding.Simple);

        System.out.println(irr);
        System.out.println(irr*12);
	}
}
