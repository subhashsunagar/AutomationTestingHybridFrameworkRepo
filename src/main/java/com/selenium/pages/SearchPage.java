package com.selenium.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

public class SearchPage {

	WebDriver driver;
	
	
	public SearchPage(WebDriver driver) {
		this.driver=driver;
	}

	public void verifyDisplayStatusOfValidProduct() {
		WebElement hpLaptopProd = driver.findElement(By.linkText("HP LP3065"));
		System.out.println(hpLaptopProd.getText());
		Assert.assertTrue(hpLaptopProd.isDisplayed(), "HP Product is not displayed");
	}
	
	public void verifyDisplayStatusOfInvalidProduct() {
		String noProductMessage = driver.findElement(By.xpath("//div[@id='content']/descendant::p[text()='There is no product that matches the search criteria.']")).getText();
		System.out.println(noProductMessage);
		//Assert.assertTrue(noProductMessage.contains("There is no product"), "'No product that matches the search criteria' message is not displayed");
		Assert.assertTrue(noProductMessage.contains("abcd"), "'No product that matches the search criteria' message is not displayed");
	}
	
}
