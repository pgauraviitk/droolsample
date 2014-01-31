package com.enirvana.rulesapp;

import java.io.Serializable;
import java.util.Date;

public class RuleRequest implements Serializable{
	public boolean fireRule;
	public Double principalBalance;
	public Double annualTaxRate;
	public Double annualInsuranceRate;
	public Double monthlyServicingFee;
	public Double monthly3PAMFee;
	public Double monthlyInspectionFee;
	public Double monthlyHOA;
	public Double monthlyOther;
	public Date asOfDate;
	public Date liquidationDateShortSale;
	public boolean isFireRule() {
		return fireRule;
	}
	public void setFireRule(boolean fireRule) {
		this.fireRule = fireRule;
	}
	public Double getPrincipalBalance() {
		return principalBalance;
	}
	public void setPrincipalBalance(Double principalBalance) {
		this.principalBalance = principalBalance;
	}
	
	protected Double getAnnualTaxRate() {
		return annualTaxRate;
	}
	protected void setAnnualTaxRate(Double annualTaxRate) {
		this.annualTaxRate = annualTaxRate;
	}
	public Double getAnnualInsuranceRate() {
		return annualInsuranceRate;
	}
	public void setAnnualInsuranceRate(Double annualInsuranceRate) {
		this.annualInsuranceRate = annualInsuranceRate;
	}
	public Double getMonthlyServicingFee() {
		return monthlyServicingFee;
	}
	public void setMonthlyServicingFee(Double monthlyServicingFee) {
		this.monthlyServicingFee = monthlyServicingFee;
	}
	public Double getMonthly3PAMFee() {
		return monthly3PAMFee;
	}
	public void setMonthly3PAMFee(Double monthly3pamFee) {
		monthly3PAMFee = monthly3pamFee;
	}
	public Double getMonthlyInspectionFee() {
		return monthlyInspectionFee;
	}
	public void setMonthlyInspectionFee(Double monthlyInspectionFee) {
		this.monthlyInspectionFee = monthlyInspectionFee;
	}
	public Double getMonthlyHOA() {
		return monthlyHOA;
	}
	public void setMonthlyHOA(Double monthlyHOA) {
		this.monthlyHOA = monthlyHOA;
	}
	public Double getMonthlyOther() {
		return monthlyOther;
	}
	public void setMonthlyOther(Double monthlyOther) {
		this.monthlyOther = monthlyOther;
	}
	public Date getAsOfDate() {
		return asOfDate;
	}
	public void setAsOfDate(Date asOfDate) {
		this.asOfDate = asOfDate;
	}
	public Date getLiquidationDateShortSale() {
		return liquidationDateShortSale;
	}
	public void setLiquidationDateShortSale(Date liquidationDateShortSale) {
		this.liquidationDateShortSale = liquidationDateShortSale;
	}
	
}
