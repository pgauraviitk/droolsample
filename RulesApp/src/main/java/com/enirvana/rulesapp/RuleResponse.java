package com.enirvana.rulesapp;

import java.util.HashMap;
import java.util.Map;

public class RuleResponse {

	Map<String, Object> variables;
	private Double monthlyTaxCost;

	public Double getMonthlyTaxCost() {
		return monthlyTaxCost;
	}

	public void setMonthlyTaxCost(Double monthlyTaxCost) {
		this.monthlyTaxCost = monthlyTaxCost;
	}

	public Map<String, Object> getVariables() {
		return variables;
	}

	public void setVariables(Map<String, Object> variables) {
		this.variables = variables;
	}

}
