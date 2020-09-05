package com.qa.api.gorest.util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;


import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ExcelUtil {
	public static String sheetPath = "C:/Users/SimTaj/workspace/worspceselenium/"
			+ "RestAssuredFramework2020/src/main/java/com/qa/api/gorest/testdata/testdata.xlsx";
	public static Workbook book;
	public static Sheet sheet;
	
	public static Object[][] getTestData(String sheetName){
		
		try {
			FileInputStream file = new FileInputStream(sheetPath);
		book =	WorkbookFactory.create(file);
		sheet = book.getSheet(sheetName);
		} catch (FileNotFoundException e) {
			
			e.printStackTrace();
		} catch (InvalidFormatException e) {
			
			e.printStackTrace();
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		
		
		
		int rows = sheet.getLastRowNum();
		int columns = sheet.getRow(0).getLastCellNum();
		Object data[][] = new Object[rows][columns];
		for(int i = 0;i<rows;i++){
			
			for(int j = 0;j <columns;j++){
				
				 data[i][j]= sheet.getRow(i+1).getCell(j).toString();
				
			}
		}
		
		return data;
		
		
		
		
		
		
	}
	
	
	

}
