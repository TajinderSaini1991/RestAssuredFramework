package com.qa.api.gorest.tests;

import java.util.HashMap;
import java.util.Map;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.qa.api.gorest.restclient.RestClient;
import com.qa.api.gorest.util.tokenUtil;

import io.restassured.response.Response;

public class ImgurGetAPI {
	Map<Object,Object> map;
	//String tokenid;
	String username;
	
	@BeforeMethod
	public void setUp(){
	map=tokenUtil.getAccessToken();
	//tokenid =	map.get("access_token").toString();
	username = map.get("account_username").toString();
		
		
	}
	
	
	@Test
	public void getAccountBlockStatusTest(){
		Map<String,String> accessToken = tokenUtil.getAuthToken();
		for (Map.Entry<String, String> entry : accessToken.entrySet()) {
		    System.out.println("MAP:"+entry.getKey() + ":" + entry.getValue().toString());
		}
	Response response =	RestClient.doGet("https://api.imgur.com", "/account/v1/"+username+"/block", accessToken, null, null, true);
		  
		System.out.println(response.prettyPrint());
	}
	
	@Test
	public void getAccountImageTest(){
		Map<String,String> accessToken = tokenUtil.getAuthToken();
		Response response = RestClient.doGet("https://api.imgur.com", "/3/account/me/images", accessToken, null, null, true);
		System.out.println(response.prettyPrint());
		
		
	}
		
	@Test
	public void uploadImageTest(){
		
	Map<String,String> clientIdMap=	tokenUtil.getAuthToken();//tokenUtil.getClientID();
	
	Map<String,String> formBodyMap = new HashMap<String, String>();
	formBodyMap.put("title", "my first image");
	formBodyMap.put("description", "this image is for pratice");
	Response response = RestClient.doPost("https://api.imgur.com", "/3/upload", clientIdMap, "multipart", null, true, formBodyMap);
	System.out.println(response.prettyPrint());
	}
		
	
	
	

}
