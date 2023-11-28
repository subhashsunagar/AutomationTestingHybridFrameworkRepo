package com.selenium.listeners;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.selenium.utils.ExtentReporter;
import com.selenium.utils.Utilities;

public class MyListeners implements ITestListener {
	ExtentReports extentReport;
	ExtentTest extentTest;
	WebDriver driver;
	String destScreenshotPath;
	
	@Override
	public void onStart(ITestContext context) {
		extentReport = ExtentReporter.generateExtentReport();

	}

	
	@Override
	public void onTestStart(ITestResult result) {
		extentTest = extentReport.createTest(result.getName());
		extentTest.log(Status.INFO, result.getName() + " - started executing");
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		extentTest.log(Status.PASS, result.getName() + " - got successfully executed");
	}

	@Override
	public void onTestFailure(ITestResult result) {
		try {
			driver = (WebDriver) result.getTestClass().getRealClass().getDeclaredField("driver")
					.get(result.getInstance());
		} catch (IllegalArgumentException | IllegalAccessException | NoSuchFieldException | SecurityException e) {
			e.printStackTrace();
		}
		
		// take screenshot when test failed
		/*
		TakesScreenshot screenshot = (TakesScreenshot) driver;
		File srcScreenshot = screenshot.getScreenshotAs(OutputType.FILE);
		String destScreenshotPath = System.getProperty("user.dir")+"\\screenshots\\"+result.getName()+".png";
		try {
			FileUtils.copyFile(srcScreenshot, new File(destScreenshotPath));
			//FileHandler.copy(srcScreenshot, new File(destScreenshotPath));
		} catch (IOException e) {
			e.printStackTrace();
		}
		*/
		
		try {
			//Calling the screenshot method from Utilities Class
			destScreenshotPath = Utilities.takeScreenshot(driver, result.getName());
		} catch (Throwable e) {
			
			e.printStackTrace();
		}
		extentTest.addScreenCaptureFromPath(destScreenshotPath);
		
		extentTest.log(Status.INFO, result.getThrowable());
		extentTest.log(Status.FAIL, result.getName() + " - got failed");

	}

	@Override
	public void onTestSkipped(ITestResult result) {
		extentTest.log(Status.INFO, result.getThrowable());
		extentTest.log(Status.SKIP, result.getName() + " - got skipped");
		
		System.out.println(result.getName() + " - got skipped");
		System.out.println(result.getThrowable());
	}

	@Override
	public void onFinish(ITestContext context) {
		System.out.println("Execution of Project Tests Finished");
		extentTest.log(Status.INFO, "Execution of Project Tests Finished");
		extentReport.flush();
		
		
		File path = new File(System.getProperty("user.dir")+"\\test-output\\ExtentReports\\extentReport.html");
		try {
			Desktop.getDesktop().browse(path.toURI());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
