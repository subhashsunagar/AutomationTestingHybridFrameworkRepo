package com.selenium.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Random;

import org.apache.commons.io.FileUtils;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;

public class Utilities {

	
	public static String generateRandomEmailAddress() {
		Random rnd = new Random();
	    int number = rnd.nextInt(99999);
	    String emailId= "Subhash"+number+"@gamil.com";
	    System.out.println(emailId); 
	    return emailId;
	 
	}
	
	public static int randomNumber() {
		Random rnd = new Random();
	    int randomNumber = rnd.nextInt(99999);
	    return randomNumber;
	}
	
	public static String takeScreenshot(WebDriver driver, String screenshotName) throws Throwable {
		TakesScreenshot screenshot = (TakesScreenshot) driver;
		File src = screenshot.getScreenshotAs(OutputType.FILE); 
		String dstScreenshotPath = "D:\\Java-Selenium Course\\Automation\\SeleniumCaseStudyAutomationProject\\screenshots\\"+screenshotName+"Screenshot.png";
		FileUtils.copyFile(src,new File(dstScreenshotPath));
		System.out.println("Screenshot has been taken");
		return dstScreenshotPath;
	}
	
	//Read data from ExcelFIle
	@Test
	public static Object[][] readTestDataFromExcel(String sheetName) {

		XSSFWorkbook wb = null;
		//String excelPath = "D:\\Java-Selenium Course\\Automation\\SeleniumCaseStudyAutomationProject\\src\\main\\java\\com\\selenium\\testData\\ExcelFile.xlsx";
		File excelPath = new File(System.getProperty("user.dir")+"\\src\\main\\java\\com\\selenium\\testData\\ExcelFile.xlsx");
		try {
			FileInputStream fis = new FileInputStream(excelPath);
			wb = new XSSFWorkbook(fis);
		} catch (Throwable e) {
			e.printStackTrace();
		}
		
		XSSFSheet sheet = wb.getSheet(sheetName);
		
		int rowCount = sheet.getLastRowNum();
		int colCount = sheet.getRow(0).getLastCellNum();
		
		Object[][] data = new Object[rowCount][colCount]; 
		
		for(int i=0; i<rowCount; i++) {
			XSSFRow row = sheet.getRow(i+1);
			for(int j=0; j<colCount; j++) {
				XSSFCell cell = row.getCell(j);
				CellType cellType = cell.getCellType();
				
				switch(cellType) {
				case STRING :
					data[i][j] = cell.getStringCellValue();
					System.out.println(data[i][j]);
					break;
				case NUMERIC:
					data[i][j] = (int)cell.getNumericCellValue();
					System.out.println(data[i][j]);
					break;
				case BOOLEAN:
					data[i][j] = cell.getBooleanCellValue();
					break;
				}
			}
		}
		return data;
		
		
	}
}
















