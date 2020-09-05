package com.qa.api.gorest.util;

import java.util.HashMap;
import java.util.Map;

import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;


public class tokenUtil {
	public static Map<Object,Object> appTokenMap;
	public static Map<String,String> tokenMap = new HashMap<String,String>();
	public static String ClientId = "8dfab99dfb3b507";
	
	public static Map<Object, Object> getAccessToken(){
	Map<String,String> maps = new HashMap<String,String>();
	maps.put("refresh_token","8366944794e098f5f3a5de22669ba5f5bc05fdc3");
	maps.put("client_id","8dfab99dfb3b507");
	maps.put("client_secret", "2417a284bc69eaa1c9605626c432b9c40cd03379");
	maps.put("grant_type", "refresh_token");
	
	JsonPath jsontoken = given()
	 .formParams(maps)
	 .when()
	   .post("https://api.imgur.com/oauth2/token")
	 .then()
	    .extract().jsonPath();
	
	
	
	
	appTokenMap = jsontoken.getMap("");
	System.out.println("aaptoken: "+appTokenMap.toString());
	return appTokenMap;
	}
	
	
	public static Map<String, String> getAuthToken(){
	String authToken =	getAccessToken().get("access_token").toString();
	System.out.println("auth token is "+authToken);
	
	tokenMap.put("Authorization", "Bearer "+authToken);
	
	return tokenMap;
	}
	public static Map<String, String> getClientID(){
		System.out.println("client id is ...>"+ClientId);
		tokenMap.put("Authorization", "Client-ID "+ClientId);
		System.out.println("client id is ...>"+ClientId);
		return tokenMap;
		
	}
	
	
	
		

}
