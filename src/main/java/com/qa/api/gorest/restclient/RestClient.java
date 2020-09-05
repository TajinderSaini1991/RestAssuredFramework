package com.qa.api.gorest.restclient;

import java.io.File;
import java.util.Map;

import com.qa.api.gorest.util.TestUtil;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class RestClient {

	public static Response doGet(String baseURI,String basePath,Map<String,String> token,
			   String contentType ,Map<String,String> paramsMap,boolean log){
		if(setBaseUri(baseURI)){
			RequestSpecification request = createRequest(token,contentType,paramsMap,log);
			return getResponse("GET",basePath,request);	
		}
		return null;
		
	}
	public static Response doPost(String baseURI,String basePath,Map<String,String> token,
			   String contentType ,Map<String,String> paramsMap,boolean log,Object obj){
		if(setBaseUri(baseURI)){
			RequestSpecification request = createRequest(token,contentType,paramsMap,log);
			addRequestPayload(request,obj);
			
			return getResponse("POST",basePath,request);	
		}
		return null;
		
	}
	private static void addRequestPayload(RequestSpecification request,Object obj){
		if(obj instanceof Map){
			request.formParams((Map<String, String>) obj);
		}
		
		
		String jsonPayload = TestUtil.getSerializedData(obj);
		System.out.println(jsonPayload);
		 request.body(jsonPayload);
	}
	
	private static boolean setBaseUri(String baseURI){
		// proper error handling and exception handling
		if(baseURI == null || baseURI.isEmpty()){
			return false;
		}
		try{
		RestAssured.baseURI = baseURI;
		return true;
		}
		catch(Exception e){
			System.out.println("please pass correct URI--its null/empty");
			return false;
		}
	}
	
	private static RequestSpecification createRequest (Map<String,String> token,String contentType
			,Map<String,String> paramsMap,boolean log){
		RequestSpecification request;
		if(log){
	request=	RestAssured.given().log().all();
		}
		else{
		request =	RestAssured.given();
		}
		
		if (token.size()>0){
			request.headers(token);
		}
		if (!(paramsMap == null)){
			request.queryParams(paramsMap);
		}
		if(!(contentType ==null)){
		if (contentType.equalsIgnoreCase("JSON")){
			request.contentType(ContentType.JSON);
		}
		
		else if(contentType.equalsIgnoreCase("xml")){
			request.contentType(ContentType.XML);
		}
		else if(contentType.equalsIgnoreCase("text")){
			request.contentType(ContentType.TEXT);
		}
		else if(contentType.equalsIgnoreCase("multipart")){
			request.multiPart("image", new File("C:/Users/SimTaj/Documents/dessert.jpg"));
		}
		}
		return request;
	}
	
	private static Response getResponse(String httpMethod,String basePath,RequestSpecification request){
		return executeAPI(httpMethod,basePath,request);
	}
	
	
	
	private static Response executeAPI(String httpMethod,String basePath,RequestSpecification request){
		Response response = null;
		switch (httpMethod) {
		case "GET":
		response = 	request.get(basePath);
			break;
		case "POST":
			response = request.post(basePath);
			break;
		case "PUT":
			response = request.put(basePath);
			break;
		case "DELETE":
			response = request.delete(basePath);
			break;

		default:
			System.out.println("Please pass the correct http method");
			break;
		}
		return response;
	}
	
	
	
	
}
