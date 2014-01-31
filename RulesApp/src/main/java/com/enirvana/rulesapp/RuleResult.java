package com.enirvana.rulesapp;

import java.util.Date;

public class RuleResult {
	public Double monthlyTaxCost;
	public Double monthlyInsuranceCost;
	public Double monthlyCosts;
	public Date firstDayCurrentMonth;
	public Date firstDayLiquidationMonthShortSale;
	
	public Double getMonthlyTaxCost() {
		return monthlyTaxCost;
	}
	public void setMonthlyTaxCost(Double monthlyTaxCost) {
		this.monthlyTaxCost = monthlyTaxCost;
	}
	public Double getMonthlyInsuranceCost() {
		return monthlyInsuranceCost;
	}
	public void setMonthlyInsuranceCost(Double monthlyInsuranceCost) {
		this.monthlyInsuranceCost = monthlyInsuranceCost;
	}
	public Double getMonthlyCosts() {
		return monthlyCosts;
	}
	public void setMonthlyCosts(Double monthlyCosts) {
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
	
	@Override
	public String toString() {
		return "RuleResult [monthlyTaxCost=" + monthlyTaxCost
				+ ", monthlyInsuranceCost=" + monthlyInsuranceCost
				+ ", monthlyCosts=" + monthlyCosts + ", firstDayCurrentMonth="
				+ firstDayCurrentMonth + ", firstDayLiquidationMonthShortSale="
				+ firstDayLiquidationMonthShortSale + "]";
	}
	
}
