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
    	Map<String, Object> variables = new HashMap<String, Object>();
    	variables.put("PrincipalBalance", 100);
    	variables.put("AnnualTaxRate", 5);
    	kSession.insert(variables);
        kSession.fireAllRules();
		
        return variables;
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
