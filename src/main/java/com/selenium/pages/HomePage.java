package com.selenium.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HomePage {

	WebDriver driver;
	public HomePage(WebDriver driver) {
		this.driver = driver;
	}
	
	public void selectMyAccount() {
		driver.findElement(By.xpath("//span[text()='My Account']")).click();
	}
	
	/*
	 * public void selectLogin() { driver.findElement(By.linkText("Login")).click();
	 * }
	 */
	
	public LoginPage selectLogin() {
		driver.findElement(By.linkText("Login")).click();
		return new LoginPage(driver);
	}
	 
	
	public void selectRegister() {
		driver.findElement(By.linkText("Register")).click();
	}

	public void enterProductIntoSearchBoxField(String productName) {
		driver.findElement(By.name("search")).sendKeys(productName);
		
	}

	public void clickOnSearchButton() {
		driver.findElement(By.xpath("//div[@id='search']/descendant::button")).click();
		
	}
	
}
