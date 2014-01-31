package com.enirvana.rulesapp;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;

public class ShortSaleRuleUtil {
	
    private static KieServices ks = KieServices.Factory.get();
    private static KieContainer kContainer = ks.getKieClasspathContainer();
    private static KieSession kSession = kContainer.newKieSession("ksession-dtables");
	
    public static Map<String, Object> getNPVShortSale() {     	
    	HashMap<String, Double> variables = new HashMap<String, Double>();
    	variables.put("PrincipalBalance", 100D);
    	variables.put("AnnualTaxRate", 5D);
    	
    	

    	HashMap<String, Object> result = new HashMap<String, Object>();
    	RuleRequest request = new RuleRequest();
    	request.setFireRule(true);
    	request.setPrincipalBalance(100D);
    	request.setInterestRate(24D);
    	request.setAnnualInsuranceRate(16D);
    	
    	kSession.insert(request);
    	kSession.setGlobal("req",request);
    	kSession.setGlobal("result",result);
        kSession.fireAllRules();
		
        return result;
	}
	public static final void main(String[] args) {
        try {
            // load up the knowledge base   
            System.out.println(getNPVShortSale());
        } catch (Throwable t) {
            t.printStackTrace();
        }
    }
}
