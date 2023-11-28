package com.selenium.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class AccountPage {

	WebDriver driver;
	public AccountPage(WebDriver driver) {
		this.driver=driver;
	}
	
	public void verifyEditYourAccountInformationText() {
		String expectedText = "Edit your account information";
		String actualText = driver.findElement(By.linkText("Edit your account information")).getText();
		System.out.println("Account Page is verified : "+actualText);
		Assert.assertEquals(expectedText, actualText, "Edit account link is not prosent on thr page");
	}
}
