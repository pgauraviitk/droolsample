package com.enirvana.rulesapp;

import java.math.BigDecimal;
import java.util.Date;

public class RuleResult {
	public BigDecimal monthlyTaxCost;
	public BigDecimal monthlyInsuranceCost;
	public BigDecimal monthlyCosts;
	public Date firstDayCurrentMonth;
	public Date firstDayLiquidationMonthShortSale;
	public int dummy;
	
	public BigDecimal getMonthlyTaxCost() {
		return monthlyTaxCost;
	}
	public void setMonthlyTaxCost(BigDecimal monthlyTaxCost) {
		this.monthlyTaxCost = monthlyTaxCost;
	}
	public BigDecimal getMonthlyInsuranceCost() {
		return monthlyInsuranceCost;
	}
	public void setMonthlyInsuranceCost(BigDecimal monthlyInsuranceCost) {
		this.monthlyInsuranceCost = monthlyInsuranceCost;
	}
	public BigDecimal getMonthlyCosts() {
		return monthlyCosts;
	}
	public void setMonthlyCosts(BigDecimal monthlyCosts) {
		this.monthlyCosts = monthlyCosts;
	}
	
	public Date getFirstDayCurrentMonth() {
		return firstDayCurrentMonth;
	}
	public void setFirstDayCurrentMonth(Date firstDayCurrentMonth) {
		this.firstDayCurrentMonth = firstDayCurrentMonth;
	}
	public Date getFirstDayLiquidationMonthShortSale() {
		return firstDayLiquidationMonthShortSale;
	}
	public void setFirstDayLiquidationMonthShortSale(
			Date firstDayLiquidationMonthShortSale) {
		this.firstDayLiquidationMonthShortSale = firstDayLiquidationMonthShortSale;
	}	
	public int getDummy() {
		return dummy;
	}
	public void setDummy(int dummy) {
		this.dummy = dummy;
	}
	
	@Override
	public String toString() {
		return "RuleResult [monthlyTaxCost=" + monthlyTaxCost.doubleValue()
				+ ", monthlyInsuranceCost=" + monthlyInsuranceCost.doubleValue()
				+ ", monthlyCosts=" + monthlyCosts.doubleValue() + ", firstDayCurrentMonth="
				+ firstDayCurrentMonth + ", firstDayLiquidationMonthShortSale="
				+ firstDayLiquidationMonthShortSale + ", dummy=" + dummy + "]";
	}
	
}
