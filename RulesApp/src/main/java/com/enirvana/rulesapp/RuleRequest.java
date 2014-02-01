package com.enirvana.rulesapp;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class RuleRequest implements Serializable{
	public boolean fireRule;
	public BigDecimal principalBalance;
	public BigDecimal annualTaxRate;
	public BigDecimal annualInsuranceRate;
	public BigDecimal monthlyServicingFee;
	public BigDecimal monthly3PAMFee;
	public BigDecimal monthlyInspectionFee;
	public BigDecimal monthlyHOA;
	public BigDecimal monthlyOther;
	public Date asOfDate;
	public Date liquidationDateShortSale;
	
	public boolean isFireRule() {
		return fireRule;
	}
	public void setFireRule(boolean fireRule) {
		this.fireRule = fireRule;
	}
	public BigDecimal getPrincipalBalance() {
		return principalBalance;
	}
	public void setPrincipalBalance(BigDecimal principalBalance) {
		this.principalBalance = principalBalance;
	}
	
	protected BigDecimal getAnnualTaxRate() {
		return annualTaxRate;
	}
	protected void setAnnualTaxRate(BigDecimal annualTaxRate) {
		this.annualTaxRate = annualTaxRate;
	}
	public BigDecimal getAnnualInsuranceRate() {
		return annualInsuranceRate;
	}
	public void setAnnualInsuranceRate(BigDecimal annualInsuranceRate) {
		this.annualInsuranceRate = annualInsuranceRate;
	}
	public BigDecimal getMonthlyServicingFee() {
		return monthlyServicingFee;
	}
	public void setMonthlyServicingFee(BigDecimal monthlyServicingFee) {
		this.monthlyServicingFee = monthlyServicingFee;
	}
	public BigDecimal getMonthly3PAMFee() {
		return monthly3PAMFee;
	}
	public void setMonthly3PAMFee(BigDecimal monthly3pamFee) {
		monthly3PAMFee = monthly3pamFee;
	}
	public BigDecimal getMonthlyInspectionFee() {
		return monthlyInspectionFee;
	}
	public void setMonthlyInspectionFee(BigDecimal monthlyInspectionFee) {
		this.monthlyInspectionFee = monthlyInspectionFee;
	}
	public BigDecimal getMonthlyHOA() {
		return monthlyHOA;
	}
	public void setMonthlyHOA(BigDecimal monthlyHOA) {
		this.monthlyHOA = monthlyHOA;
	}
	public BigDecimal getMonthlyOther() {
		return monthlyOther;
	}
	public void setMonthlyOther(BigDecimal monthlyOther) {
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
