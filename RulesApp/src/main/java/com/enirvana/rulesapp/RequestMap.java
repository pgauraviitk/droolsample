package com.enirvana.rulesapp;

import java.util.HashMap;

public class RequestMap {
	private HashMap<String, Double> requestParams;

	public double val(Object key) {
//		return requestParams.get(key);
		return 100D;
	}
	public double getP() {
//		return requestParams.get(key);
		return 100D;
	}
	
	public HashMap<String, Double> getRequestParams() {
		return requestParams;
	}

	public void setRequestParams(HashMap<String, Double> requestParams) {
		this.requestParams = requestParams;
	}

}
