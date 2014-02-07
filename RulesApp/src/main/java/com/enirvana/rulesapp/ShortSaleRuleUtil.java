package com.enirvana.rulesapp;

import com.enirvana.rulesapp.model.Field;
import com.enirvana.rulesapp.util.ExcelParser;
import com.enirvana.rulesapp.util.JSONUtil;
import org.kie.api.KieBaseConfiguration;
import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;

import static com.enirvana.rulesapp.DateUtil.getParsedDate;

public class ShortSaleRuleUtil {

	private static final Logger logger = LoggerFactory
			.getLogger(ShortSaleRuleUtil.class);

	private static KieServices ks;
	private static KieContainer kContainer;
	private static KieSession kSession;
	private static Class newClass;
	static {
		try {
			// newClass = FieldGeneratorUtil.createClass();
			ks = KieServices.Factory.get();
			// KieBaseConfiguration config =
			// ks.newKieBaseConfiguration(null,newClass.getClassLoader());
			KieBaseConfiguration config = ks.newKieBaseConfiguration();
			kContainer = ks.getKieClasspathContainer();
			kSession = kContainer.newKieSession("ksession-dtables");
			logger.info("kie session loaded");
		} catch (Exception e) {
			logger.error("Error while loading kie session -->>>" + e);
		}
	}

	public static RuleResult getNPVShortSale() {
		RuleResult result = new RuleResult();
		RuleRequest request = new RuleRequest();
		request.setFireRule(true);

		kSession.insert(request);
		kSession.setGlobal("req", request);
		kSession.setGlobal("result", result);

		kSession.fireAllRules();
		return result;
	}
	
	Double currDecShortSale(String Workout, Double CurrentOffer, 
			Double ReconciledAsIsValue, Double HPIChangetoLiquidationShortSale) { 
			 if(Workout=="")
			  return CurrentOffer;
			 else
			  return ReconciledAsIsValue*HPIChangetoLiquidationShortSale; 
			}
	
	Double[] getValue(Double MonthstoLiquidationShortSale,
			Double MonthstoCFKPaidShortSale,
			Double CashForKeysAmount, Double LiquidationCostsShortSale,
			Double CurrentDecisionShortSale, Double MonthlyCosts) {

		Double[] cashFlowArr = new Double[MonthstoLiquidationShortSale
				.intValue()];

		for (int i = 0; i <= MonthstoLiquidationShortSale.intValue(); i++) {
			if (MonthstoLiquidationShortSale == MonthstoCFKPaidShortSale)
				if (i == MonthstoCFKPaidShortSale)
					cashFlowArr[i] = (-MonthlyCosts - CashForKeysAmount
							- LiquidationCostsShortSale + CurrentDecisionShortSale);
				else if (i < MonthstoCFKPaidShortSale)
					cashFlowArr[i] = (-MonthlyCosts);
				else
					cashFlowArr[i] = 0D;
			else if (MonthstoCFKPaidShortSale < MonthstoLiquidationShortSale)
				if (i == MonthstoCFKPaidShortSale)
					cashFlowArr[i] = (-MonthlyCosts - CashForKeysAmount);
				else if (i == MonthstoLiquidationShortSale)
					cashFlowArr[i] = (-MonthlyCosts - LiquidationCostsShortSale + CurrentDecisionShortSale);
				else if (i < MonthstoLiquidationShortSale)
					cashFlowArr[i] = (-MonthlyCosts);
				else
					cashFlowArr[i] = 0D;
		}

		return cashFlowArr;
	}
	
	Double[] getShortSaleArr(Double MonthstoLiquidationShortSale,
			Double MonthstoCFKPaidShortSale,
			Double CashForKeysAmount, Double LiquidationCostsShortSale,
			Double CurrentDecisionShortSale, Double MonthlyCosts) {

		Double[] cashFlowArr = new Double[6];

		cashFlowArr[1] = MonthstoLiquidationShortSale;
		cashFlowArr[2] = MonthstoCFKPaidShortSale;
		cashFlowArr[3] = CashForKeysAmount;
		cashFlowArr[4] = LiquidationCostsShortSale;
		cashFlowArr[5] = CurrentDecisionShortSale;
		cashFlowArr[6] = MonthlyCosts;
		
		return cashFlowArr;
	}

    /*
    * for ShortSale pass ShortSale as program value.(as per data excel)
    * */
    public static void parseJsonAndPopulateMap(String program) throws Exception {
        //first parse the excel file for data fields and types
        ExcelParser excelParser = new ExcelParser();
        InputStream fis = null;
        try {
            fis = ExcelParser.class.getResourceAsStream("/sample/NPV Model Inputs 1.28.2014.xlsx");
            excelParser.parse(fis);
            System.out.println("done!!");
        } finally {
            try {
                if(fis!=null)
                    fis.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        Map<String,List<Field>> programFieldMap = excelParser.getProgramFieldMap();

        List<Field> fields = programFieldMap.get(program);

        Map<String,Object> parsedMap = null;
        InputStream fis1 = null;
        try {
            fis1 = ExcelParser.class.getResourceAsStream("/sample/Drool_NPVRequest.json");
            parsedMap = JSONUtil.parseJsonStream(fis1);
            System.out.println("done!!");
        } finally {
            try {
                if(fis1!=null)
                    fis1.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

        for(Field field : fields){
            String type = "";
            String arrayName = "";
            String formatKey = field.getFormat();
            if(formatKey.contains(":")){
                String[] keyArr = formatKey.split(":");
                formatKey = keyArr[0];
                if(keyArr.length >1)
                    arrayName = keyArr[1];
                if(keyArr.length >2)    {
                    type = keyArr[1];
                    arrayName = keyArr[2];
                }
            }

            String fieldName = field.getName();

            String key = fieldName;
            if(!"".equalsIgnoreCase(arrayName)){
                key = arrayName;
            }
            if(!parsedMap.containsKey(key)){
                throw new Exception(fieldName+" and "+key+" Required Field Missing from JSON.");
            }

            Object fieldValue = parsedMap.get(key);
            switch(formatKey){
                case "String":
                    RuleRequest.addStrAttr(fieldName,fieldValue.toString());
                    break;
                case "Double":
                case "Money":
                case "Number":
                    try {
                        RuleRequest.addDoubleAttr(fieldName, Double.parseDouble(fieldValue.toString()));
                    } catch (NumberFormatException e) {
                        throw new Exception(fieldName+" amount not properly formatted");
                    }
                    break;
                case "Date":
                    try {
                        RuleRequest.addDateAttr(fieldName, dateFormat.parse(fieldValue.toString()));
                    } catch (ParseException e) {
                        throw new Exception(fieldName+" date not properly formatted");
                    }
                    break;
                case "ArrayDate":
                    // ArrayDates will always have workout and date keys in their maps.
                    List<Object> objList = (List<Object>) fieldValue;
                    for(Object obj : objList){
                        Map<String,Object> dateMap = (Map<String,Object>) obj;
                        if(program.equals(dateMap.get("Workout"))){
                            try {
                                RuleRequest.addDateAttr(fieldName, dateFormat.parse(dateMap.get("Date").toString()));
                            } catch (ParseException e) {
                                throw new Exception(fieldName+" date not properly formatted");
                            }
                            break;
                        }
                    }
                    break;
                case "ArrayIncentivesDate":
                    List<Object> incentiveList = (List<Object>) fieldValue;
                    for(Object obj : incentiveList){
                        Map<String,Object> dateMap = (Map<String,Object>) obj;
                        //new format
                        if(program.equals(dateMap.get("Workout")) && type.equals(dateMap.get("Type"))){
                            try {
                                RuleRequest.addDateAttr(fieldName, dateFormat.parse(dateMap.get(fieldName).toString()));
                            } catch (ParseException e) {
                                throw new Exception(fieldName+" date not properly formatted");
                            }
                            break;
                        }
                    }
                    break;
                case "ArrayIncentivesAmount":
                    List<Object> incentiveList1 = (List<Object>) fieldValue;
                    for(Object obj : incentiveList1){
                        Map<String,Object> dateMap = (Map<String,Object>) obj;
                        if(program.equals(dateMap.get("Workout")) && type.equals(dateMap.get("Type"))){
                            try {
                                RuleRequest.addDoubleAttr(fieldName, Double.parseDouble(dateMap.get(fieldName).toString()));
                            } catch (Exception e) {
                                e.printStackTrace();
                                throw new Exception(fieldName+" amount not properly formatted");
                            }
                            break;
                        }
                    }
                    break;
                case "ArrayHPI":
                    // ArrayDates will always have MoYr and Value keys in their maps.
                    List<Object> hpiList = (List<Object>) fieldValue;
                    for(Object obj : hpiList){
                        Map<String,Object> hpiMap = (Map<String,Object>) obj;
                        try {
                            RuleRequest.addHPIAttr(hpiMap.get("MoYr").toString(), Double.parseDouble(hpiMap.get("Value").toString()));
                        } catch (NumberFormatException e) {
                            throw new Exception(fieldName+" amount not properly formatted");
                        }
                    }
                    break;
            }
        }
    }

	public static final void main(String[] args) {
		try {
			// load up the knowledge base
//			RuleRequest.addDoubleAttr("principalBalance", 100D);
//			RuleRequest.addDoubleAttr("annualTaxRate", 24D);
//			RuleRequest.addDoubleAttr("annualInsuranceRate", 100D);
//			RuleRequest.addDoubleAttr("monthlyServicingFee", 100D);
//			RuleRequest.addDoubleAttr("monthly3PAMFee", 100D);
//			RuleRequest.addDoubleAttr("monthlyInspectionFee", 100D);
//			RuleRequest.addDoubleAttr("monthlyHOA", 100D);
//			RuleRequest.addDoubleAttr("monthlyOther", 100D);
//			RuleRequest.addDoubleAttr("CurrentOffer", 100D);
//			RuleRequest.addDoubleAttr("ReconciledAsIsValue", 200D);
//			RuleRequest.addDoubleAttr("ClosingCostOther", 200D);
//			RuleRequest.addDoubleAttr("Commission", 200D);
//			RuleRequest.addDoubleAttr("CorpAndEscrowAdvance", 200D);
//			RuleRequest.addDoubleAttr("JuniorLienSettlement", 200D);
//			RuleRequest.addDoubleAttr("OtherNonRecurring", 200D);
//			RuleRequest.addDoubleAttr("CashForKeysAmount", 200D);
//
//			RuleRequest.addDateAttr("liquidationDateShortSale", getParsedDate("12/01/2014"));
//			RuleRequest.addDateAttr("asOfDate", getParsedDate("2/01/2014"));
//			RuleRequest.addDateAttr("CFKPaidDate", getParsedDate("8/10/2014"));
//
//			RuleRequest.addHPIAttr("11/2014", 200.00);
//			RuleRequest.addHPIAttr("12/2014", 100.054);
//			RuleRequest.addHPIAttr("1/2014", 100.00);
//			RuleRequest.addHPIAttr("2/2014", 100.009);
//
//			RuleRequest.addStrAttr("Workout", "ShortSaleee");

            parseJsonAndPopulateMap("ShortSale");

			logger.info("Short sale NPV is " + getNPVShortSale());
		} catch (Throwable t) {
			t.printStackTrace();
		}
	}
}
