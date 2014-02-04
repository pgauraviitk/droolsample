package com.enirvana.rulesapp;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelParser {
	private Map<String,List<Field>> programFieldMap = new HashMap<>();
	private List<String> programList = new ArrayList<>(); 
	private List<Field> fieldList = new ArrayList<Field>();
	
	public void parse(String fileName){
		File file = new File(fileName);		
		FileInputStream fis = null;
		try {
			fis = new FileInputStream(file);
			parse(fis);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} finally {
			try {
				if(fis!=null)
					fis.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void parse(InputStream fis){				
		try {
			XSSFWorkbook wb = new XSSFWorkbook(fis);
			XSSFSheet ws = wb.getSheet("Data");
			Map<Integer,String> programMap = new HashMap<>();
			
			int rowNum = ws.getLastRowNum() + 1;
	        int colNum = ws.getRow(0).getLastCellNum();

	        for(int i = 0; i <rowNum; i++){
	        	Field field = new Field();	        	
	            XSSFRow row = ws.getRow(i);
                for (int j = 0; j < colNum; j++){
                	XSSFCell cell = row.getCell(j);
                	String value = cell==null ? "" : cell.toString();
                	
                	// collect program names in j=0;
                	if(i==0) {
                		switch(j) {
	                		case 4:
	                		case 5:
	                		case 6:
	                		case 7:
	                		case 8:
	                		case 9:
	                		case 10:
	                		case 11:
	                		case 12:
	                			programMap.put(j, value);
	                			break;
                		}
                		continue;
                	}
                	
                	
                	switch(j) {
                		case 1:
                			field.setName(value);
                			break;
                		case 2:
                			field.setSource(value);
                			break;
                		case 3:
                			field.setCategory(value);
                			break;
                		case 4:
                		case 5:
                		case 6:
                		case 7:
                		case 8:
                		case 9:
                		case 10:
                		case 11:
                		case 12:
                			String program = programMap.get(j);
                			List<Field> reqFieldList = programFieldMap.get(program);
                			if(reqFieldList==null){
                				reqFieldList = new ArrayList<>();
                				programFieldMap.put(program, reqFieldList);
                			}
                			if("X".equals(value) && field.getName()!=null && !"".equals(field.getName())){
                				reqFieldList.add(field);
                			}                			                			
                			break;
                	}               	               	                                   	
                }
                if(field.getName()!=null && !"".equals(field.getName())){
                	fieldList.add(field); 
                }
	        }
	        
	        programList = new ArrayList<String>(programMap.values());
		} catch (IOException e) {
			e.printStackTrace();
		}		        
	}
		
	public Map<String, List<Field>> getProgramFieldMap() {
		return programFieldMap;
	}

	public List<String> getProgramList() {
		return programList;
	}

	public List<Field> getFieldList() {
		return fieldList;
	}

	public static void main(String[] args){
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
	}
}
