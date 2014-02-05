package com.enirvana.rulesapp;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

@SuppressWarnings("unused")
public class JSONUtil {
	
	public static void parseJson(String jsonString,Map<String,Object> parsedMap){				
		JSONTokener jsonTokener = new JSONTokener(jsonString);						
		JSONObject root = (JSONObject) jsonTokener.nextValue();	

		Iterator iterator = root.keys();
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
				
				List<Object> mapList = new ArrayList<>(length);	
				parseJsonArray(jsonArray, mapList);
				
				parsedMap.put(key, mapList);
			}else if(obj instanceof String){
				parsedMap.put(key, obj);
			}			
		}
	}
	
	public static void parseJsonObject(JSONObject jsonObject,Map<String,Object> parsedMap){
		Iterator iterator = jsonObject.keys();
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
				
				List<Object> mapList = new ArrayList<>(length);	
				parseJsonArray(jsonArray, mapList);
				
				parsedMap.put(key, mapList);
			}else if(obj instanceof String){
				parsedMap.put(key, obj);
			}			
		}
	}
	
	public static void parseJsonArray(JSONArray jsonArray,List<Object> mapList){						
		int length = jsonArray.length();
		for(int k=0;k<length;k++){
			Object obj = jsonArray.get(k);							
			if(obj instanceof JSONObject){
				Map<String,Object> objMap = new HashMap<>();
				mapList.add(objMap);
				parseJsonObject((JSONObject)obj,objMap);				
			}else if(obj instanceof JSONArray){	
				JSONArray jsonArray1 = (JSONArray) obj;			
				int length1 = jsonArray1.length();
				List<Object> mapList1 = new ArrayList<>(length1);						
				mapList.add(mapList1);
				parseJsonArray(jsonArray1,mapList1);
			}else if(obj instanceof String){
				mapList.add(obj);
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
			
			if(val instanceof Map){
				Map<String,Object> map = (Map<String,Object>) val;
				printMap(map,loopCount,k==l-1);					
			}else if(val instanceof String){
				printTabs(loopCount);
				if(k==l-1){
					System.out.println("\""+key+"\" : \""+val+"\"");
				}else{
					System.out.println("\""+key+"\" : \""+val+"\",");
				}
			}else if(val instanceof List){
				printList(key, (List<Object>) val, loopCount, k == l - 1);
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

    @SuppressWarnings("unchecked")
	public static void printList(String key,List<Object> list,int count,boolean last){
		if(key!=null){
			printTabs(count);
			System.out.println("\""+key+"\" : ");
		}
		printTabs(count);
		System.out.println("[");
		int inCount = count+1;
		int length = list.size();
		int i=0;
        for (Object obj : list) {
            if (obj instanceof Map) {
                Map<String, Object> map = (Map<String, Object>) obj;
                printMap(map, inCount, i == length - 1);
            } else if (obj instanceof List) {
                printList(null, (List<Object>) obj, inCount, i == length - 1);
            } else if (obj instanceof String) {
                printTabs(inCount);
                if (i == length - 1) {
                    System.out.println("\"" + obj + "\"");
                } else {
                    System.out.println("\"" + obj + "\",");
                }
            }
            i++;
        }
		printTabs(count);
		if(last){
			System.out.println("]");
		}else{
			System.out.println("],");
		}
	}
	
	public static void printTabs(int number){
		for(int i=0;i<number;i++){
			System.out.print("\t");
		}
	}
	
	public static Map<String,Object> parseJsonStream(InputStream is){
		StringBuilder builder = new StringBuilder("");
		BufferedReader bufferedReader = null;
		try {
			bufferedReader = new BufferedReader(new InputStreamReader(is));
			String line;
			while((line=bufferedReader.readLine())!=null){
				builder.append(line);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
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

	public static Map<String,Object> parseJsonFile(String filePath){
		File jsonFile = new File(filePath);		
		Map<String, Object> parsedMap = null;
		FileInputStream fis = null;
		try {
			fis = new FileInputStream(jsonFile);
			parsedMap = parseJsonStream(fis);
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
		return parsedMap;
	}
	
	public static void main(String[] args){	
		Map<String,Object> parsedMap = parseJsonStream(JSONUtil.class.getResourceAsStream("/sample/2014-01-24 NPVRequest.json"));		
		printMap(parsedMap,1,true);	
		System.out.println();
		System.out.println();
		System.out.println();
		System.out.println();
		System.out.println();
		System.out.println();
		Map<String,Object> parsedMap1 = parseJsonStream(JSONUtil.class.getResourceAsStream("/sample/Pre-Run Response.json"));		
		printMap(parsedMap1,1,true);
		System.out.println();
		System.out.println();
		System.out.println();
		System.out.println();
		System.out.println();
		System.out.println();
		Map<String,Object> parsedMap2 = parseJsonStream(JSONUtil.class.getResourceAsStream("/sample/Pre-Run Response1.json"));		
		printMap(parsedMap2,1,true);
	}
}
