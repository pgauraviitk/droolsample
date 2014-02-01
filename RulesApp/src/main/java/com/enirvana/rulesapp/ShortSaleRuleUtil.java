package com.enirvana.rulesapp;

import java.math.BigDecimal;
import java.util.Date;

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
