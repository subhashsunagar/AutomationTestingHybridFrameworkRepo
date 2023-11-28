package com.selenium.scenarios;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.selenium.base.Base;
import com.selenium.pages.HomePage;
import com.selenium.pages.SearchPage;

public class SearchTest extends Base {

	public WebDriver driver;
	HomePage homePage;
	public SearchTest() {
		super();
	}
	
	@BeforeMethod
	public void startUp(){
		
		driver = initializeBrowserAndLaunchApplicationURL(prop.getProperty("browserName"));
		homePage = new HomePage(driver);
	}
	
	@AfterMethod
	public void quitBrowser() {
		driver.quit();
	}	
	
	@Test(priority=1)
	public void verifySearchWithValidProduct() throws Exception {
		
		//HomePage homePage = new HomePage(driver);
		homePage.enterProductIntoSearchBoxField(prop.getProperty("productName"));
		homePage.clickOnSearchButton();
		
		SearchPage searchPage = new SearchPage(driver);
		searchPage.verifyDisplayStatusOfValidProduct();
	}
	
	@Test(priority=2)
	public void verifySearchWithInvalidProduct() throws Exception{
		//HomePage homePage = new HomePage(driver);
		homePage.enterProductIntoSearchBoxField(prop.getProperty("invalidProduct"));
		homePage.clickOnSearchButton();
		
		SearchPage searchPage = new SearchPage(driver);
		searchPage.verifyDisplayStatusOfInvalidProduct();				
	}	
	
	@Test(priority=3, dependsOnMethods = {"verifySearchWithInvalidProduct"})
	public void verifySearchWithoutAnyProduct() throws Exception{
		//HomePage homePage = new HomePage(driver);
		homePage.clickOnSearchButton();
		
		SearchPage searchPage = new SearchPage(driver);
		searchPage.verifyDisplayStatusOfInvalidProduct();		
	}
}













