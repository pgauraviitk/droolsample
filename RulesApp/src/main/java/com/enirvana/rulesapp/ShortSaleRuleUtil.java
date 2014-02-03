package com.enirvana.rulesapp;

import java.math.BigDecimal;
import java.util.Date;

import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;

public class ShortSaleRuleUtil {
		
    public static RuleResult getNPVShortSale() {  
    	RuleResult result = new RuleResult();
    	RuleRequest request = new RuleRequest();
    	request.setFireRule(true);
    	request.setPrincipalBalance(new BigDecimal(100,CalculatorUtil.mc));
    	request.setAnnualTaxRate(new BigDecimal(24,CalculatorUtil.mc));
    	request.setAnnualInsuranceRate(new BigDecimal(16,CalculatorUtil.mc));
    	request.setAsOfDate(new Date());
    	request.setLiquidationDateShortSale(new Date());
    	
    	request.setMonthlyServicingFee(new BigDecimal(100,CalculatorUtil.mc));
    	request.setMonthly3PAMFee(new BigDecimal(100,CalculatorUtil.mc));
    	request.setMonthlyInspectionFee(new BigDecimal(200,CalculatorUtil.mc));
    	request.setMonthlyHOA(new BigDecimal(200,CalculatorUtil.mc));
    	request.setMonthlyOther(new BigDecimal(200,CalculatorUtil.mc));    	    			
		
    	KieServices ks = KieServices.Factory.get();    	
        KieContainer kContainer = ks.getKieClasspathContainer();             
        KieSession kSession = kContainer.newKieSession("ksession-dtables"); 
        
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
