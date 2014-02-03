package com.enirvana.rulesapp;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

public class JSONUtil {
	
	public static void parseJson(String jsonString,Map<String,Object> parsedMap){				
		JSONTokener jsonTokener = new JSONTokener(jsonString);						
		JSONObject root = (JSONObject) jsonTokener.nextValue();	
		
		@SuppressWarnings("unchecked")
		Iterator<Object> iterator = root.keys();
		while(iterator.hasNext()){
			String key = (String) iterator.next();
			Object obj = root.get(key);							
			if(obj instanceof JSONObject){
				Map<String,Object> objMap = new HashMap<>();																
				parseJsonObject((JSONObject)obj, objMap);
				
				parsedMap.put(key, objMap);
			}else if(obj instanceof JSONArray){				
				JSONArray jsonArray = (JSONArray) obj;
				int length = jsonArray.length();
				
				List<Map<String, Object>> mapList = new ArrayList<>(length);
				for(int i=0;i<length;i++){
					Map<String,Object> objMap = new HashMap<>();
					mapList.add(objMap);					
				}	
				parseJsonArray(jsonArray, mapList);
				
				parsedMap.put(key, mapList);
			}else if(obj instanceof String){
				parsedMap.put(key, obj);
			}			
		}
	}
	
	public static void parseJsonObject(JSONObject jsonObject,Map<String,Object> parsedMap){						
		@SuppressWarnings("unchecked")
		Iterator<Object> iterator = jsonObject.keys();
		while(iterator.hasNext()){
			String key = (String) iterator.next();
			Object obj = jsonObject.get(key);							
			if(obj instanceof JSONObject){
				Map<String,Object> objMap = new HashMap<>();															
				parseJsonObject((JSONObject)obj,objMap);	
				
				parsedMap.put(key, objMap);	
			}else if(obj instanceof JSONArray){	
				JSONArray jsonArray = (JSONArray) obj;
				int length = jsonArray.length();
				
				List<Map<String, Object>> mapList = new ArrayList<>(length);
				for(int i=0;i<length;i++){
					Map<String,Object> objMap = new HashMap<>();
					mapList.add(objMap);					
				}	
				parseJsonArray(jsonArray, mapList);
				
				parsedMap.put(key, mapList);
			}else if(obj instanceof String){
				parsedMap.put(key, obj);
			}			
		}
	}
	
	public static void parseJsonArray(JSONArray jsonArray,List<Map<String, Object>> mapList){						
		int length = jsonArray.length();
		for(int k=0;k<length;k++){
			Object obj = jsonArray.get(k);							
			if(obj instanceof JSONObject){
				parseJsonObject((JSONObject)obj,mapList.get(k));				
			}else if(obj instanceof JSONArray){	
				JSONArray jsonArray1 = (JSONArray) obj;			
				int length1 = jsonArray1.length();
				List<Map<String, Object>> mapList1 = new ArrayList<>(length1);
				for(int i=0;i<length1;i++){
					Map<String,Object> objMap = new HashMap<>();
					mapList1.add(objMap);					
				}	
				parseJsonArray(jsonArray1,mapList1);				
			}else if(obj instanceof String){
				mapList.get(k).put((String)obj, obj);
			}			
		}
	}	
	
	@SuppressWarnings("unchecked")
	public static void printMap(Map<String,Object> parsedMap,int count,boolean last){
		printTabs(count);
		System.out.println("{");
		int loopCount = count+1;
		Iterator<String> iterator = parsedMap.keySet().iterator();
		int l = parsedMap.keySet().size();
		int k = 0;
		while(iterator.hasNext()){
			String key = iterator.next();			
			Object val = parsedMap.get(key);
			
			if(val instanceof String){
				printTabs(loopCount);
				if(k==l-1){
					System.out.println("\""+key+"\" : \""+val+"\"");
				}else{
					System.out.println("\""+key+"\" : \""+val+"\",");
				}
			}else if(val instanceof List){
				printTabs(loopCount);
				System.out.println("\""+key+"\" : ");
				printTabs(loopCount);
				System.out.println("[");
				int inCount = loopCount+1;
				List<Map<String, Object>> mapList = (List<Map<String,Object>>) val;
				int length = mapList.size();
				int i=0;
				for (Iterator<Map<String, Object>> iterator2 = mapList
						.iterator(); iterator2.hasNext();) {
					Map<String,Object> map = iterator2.next();
					printMap(map,inCount,i==length-1);
					i++;
				}
				printTabs(loopCount);
				if(k==l-1){
					System.out.println("]");
				}else{
					System.out.println("],");
				}				
			}
			k++;
		}
		printTabs(count);
		if(last){
			System.out.println("}");
		}else{
			System.out.println("},");
		}
	}
	
	public static void printTabs(int number){
		for(int i=0;i<number;i++){
			System.out.print("\t");
		}
	}
	
	public static Map<String,Object> parseJsonFile(String filePath){
		File jsonFile = new File(filePath);
		StringBuilder builder = new StringBuilder("");
		BufferedReader bufferedReader = null;
		try {
			bufferedReader = new BufferedReader(new FileReader(jsonFile));
			String line;
			while((line=bufferedReader.readLine())!=null){
				builder.append(line);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			try {
				if(bufferedReader!=null)
					bufferedReader.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		Map<String,Object> parsedMap = new HashMap<>();		
		parseJson(builder.toString(), parsedMap);		
		
		return parsedMap;
	}
	
	public static void main(String[] args){			
		Map<String,Object> parsedMap = parseJsonFile("C:\\Users\\admin\\Downloads\\prashant\\reprojectkickedoff\\2014-01-24 NPVRequest.json");		
		printMap(parsedMap,1,true);		
	}
}
