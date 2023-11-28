package com.selenium.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class LoginPage {

	WebDriver driver;
	public LoginPage(WebDriver driver) {
		this.driver=driver;
	}
	
	public void enterEmail(String email) {
		driver.findElement(By.id("input-email")).sendKeys(email);
	}
	public void enterPassword(String password) {
		driver.findElement(By.id("input-password")).sendKeys(password);
	}
	public void clickOnLoginButton() {
		driver.findElement(By.xpath("//input[@value='Login']")).click();
	}
	
	public void verifyLoginErrorWarningMessage() {
		String loginErrorText = driver.findElement(By.xpath("//div[text()='Warning: No match for E-Mail Address and/or Password.']")).getText();
		System.out.println("Login Error Warning : "+loginErrorText);
		Assert.assertTrue(loginErrorText.contains("No match"), "Login Error message is not getting displayed");
	}

}