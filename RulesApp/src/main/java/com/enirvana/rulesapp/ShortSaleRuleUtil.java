package com.enirvana.rulesapp;

import java.util.ArrayList;
import java.util.Date;
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
	
    public static RuleResult getNPVShortSale() {  
    	RuleResult result = new RuleResult();
    	RuleRequest request = new RuleRequest();
    	request.setFireRule(true);
    	request.setPrincipalBalance(100D);
    	request.setAnnualTaxRate(24D);
    	request.setAnnualInsuranceRate(16D);
    	request.setAsOfDate(new Date());
    	request.setLiquidationDateShortSale(new Date());
    	
    	request.setMonthlyServicingFee(100D);
    	request.setMonthly3PAMFee(100D);
    	request.setMonthlyInspectionFee(200D);
    	request.setMonthlyHOA(200D);
    	request.setMonthlyOther(200D);
    	
//    	Double rr = req.monthlyServicingFee+req.monthly3PAMFee
//    			    +req.monthlyInspectionFee+req.monthlyHOA
//    			    +req.monthlyOther
//    			    +result.monthlyTaxCost+ result.monthlyInsuranceCost;
    	
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
