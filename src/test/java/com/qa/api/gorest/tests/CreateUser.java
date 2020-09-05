package com.qa.api.gorest.tests;

import java.util.HashMap;
import java.util.Map;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.api.gorest.restclient.RestClient;
import com.qa.api.gorest.util.ExcelUtil;
import com.qa.qpi.gorest.Pojo.User;

import io.restassured.response.Response;



public class CreateUser {
	String baseURI = "https://gorest.co.in/";
    String basePath = "public-api/users";
    String token = "da399a92bb0f67a9dd0560269b853e216a9efea38a7ba45f20e0f11ec157d1f1";
	@Test
	public void createUserWithPojo(){
		
		Map<String,String> authTokenMap = new HashMap<>();
		authTokenMap.put("Authorization", "Bearer "+token);
	    
	    User user = new User("Priya","priya12@gmail.com","Female","Active");
	
	    
	   Response response = RestClient.doPost(baseURI, basePath, authTokenMap, "JSON", null, true, user);
	   System.out.println(response.prettyPrint());
	   System.out.println(response.getStatusCode());
		
		
		
			
	}
	
	@DataProvider
	public Object[][] getUserData(){
		return ExcelUtil.getTestData("Sheet1");
		
	}
	@Test(dataProvider = "getUserData")
	public void createMultipleUsersWithExcel(String name,String email,String gender,String status){
		Map<String,String> authTokenMap = new HashMap<>();
		authTokenMap.put("Authorization", "Bearer "+token);
	    
		User user = new User(name,email,gender,status);
	Response response =	RestClient.doPost(baseURI,basePath,authTokenMap,"JSON",null,true, user);
	System.out.println(response.prettyPrint());
	
		
		
		
		
	}
	
	
}
