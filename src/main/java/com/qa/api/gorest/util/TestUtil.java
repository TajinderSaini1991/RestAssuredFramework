package com.qa.api.gorest.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class TestUtil {
	/**
	 * This method is used to convert POJO Object to JSON String
	 * @param obj
	 * @return 
	 */
	public static String getSerializedData(Object obj){
		ObjectMapper mapper = new ObjectMapper();
		String jsonString = null;
		try {
		jsonString =mapper.writeValueAsString(obj);
		System.out.println("JSON PAYLOAD IS ===>"+jsonString);
		} catch (JsonProcessingException e) {
			
			e.printStackTrace();
		}
		return jsonString;
	}

}
