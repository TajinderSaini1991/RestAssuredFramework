package com.qa.api.gorest.tests;

import java.util.HashMap;
import java.util.Map;

import org.testng.annotations.Test;

import com.qa.api.gorest.restclient.RestClient;

import io.restassured.response.Response;

public class GetUserTest {
	
	String baseURI = "https://gorest.co.in/";
    String basePath = "public-api/users";
    String token = "da399a92bb0f67a9dd0560269b853e216a9efea38a7ba45f20e0f11ec157d1f1";
	
	
	@Test
	public void getAllusersApiTest(){
		Map<String,String> authTokenMap = new HashMap<>();
		authTokenMap.put("Authorization", "Bearer "+token);
		Response response = RestClient.doGet(baseURI, basePath, authTokenMap, "JSON", null, true);
		System.out.println(response.getContentType());
		System.out.println(response.getStatusCode());
		System.out.println(response.prettyPrint());
	}
	
	@Test
	public void getUserWithQueryParams(){
		Map<String,String> authTokenMap = new HashMap<>();
		authTokenMap.put("Authorization", "Bearer "+token);
		
		Map<String,String> map = new HashMap<>();
		map.put("name", "abc");
		map.put("gender", "Female");
		
		RestClient.doGet(baseURI, basePath, authTokenMap, "JSON", map, true);
		
	}
	

}
