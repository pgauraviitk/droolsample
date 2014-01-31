package com.enirvana.rulesapp;

import java.io.Serializable;

public class RuleRequest implements Serializable{
	public boolean fireRule;
	public Double principalBalance;
	public Double interestRate;
	public Double annualInsuranceRate;
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
	public Double getInterestRate() {
		return interestRate;
	}
	public void setInterestRate(Double interestRate) {
		this.interestRate = interestRate;
	}
	public Double getAnnualInsuranceRate() {
		return annualInsuranceRate;
	}
	public void setAnnualInsuranceRate(Double annualInsuranceRate) {
		this.annualInsuranceRate = annualInsuranceRate;
	}
	
	
}
