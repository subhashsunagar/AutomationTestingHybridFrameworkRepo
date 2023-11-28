package com.selenium.scenarios;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.selenium.base.Base;
import com.selenium.pages.AccountPage;
import com.selenium.pages.HomePage;
import com.selenium.pages.LoginPage;
import com.selenium.utils.Utilities;

public class LoginTest extends Base {

	LoginPage loginPage;
	public LoginTest() {
		super();
	}
	
	public WebDriver driver;

	@BeforeMethod
	public void startUp() {

		driver = initializeBrowserAndLaunchApplicationURL(prop.getProperty("browserName"));
		HomePage homePage = new HomePage(driver);
		homePage.selectMyAccount();
		loginPage = homePage.selectLogin();
	}

	@DataProvider(name = "supplyingData")
	public Object[][] supplyData() {
		Object[][] data = Utilities.readTestDataFromExcel("LoginData");// u need the Java code to read data from excel
		return data;
	}

	@Test(priority = 1, dataProvider = "supplyingData")
	public void loginWithValidCredentials(String email, String password) {
		// LoginPage loginPage = new LoginPage(driver);
		loginPage.enterEmail(email);
		loginPage.enterPassword(password);
		loginPage.clickOnLoginButton();
		AccountPage accountPage = new AccountPage(driver);
		accountPage.verifyEditYourAccountInformationText();
	}

	@Test(priority = 2)
	public void loginWithValidUsernameAndWrongPassword() {
		// LoginPage loginPage = new LoginPage(driver);
		loginPage.enterEmail(prop.getProperty("validEmail"));
		loginPage.enterPassword("123SASAS12");
		loginPage.clickOnLoginButton();
		loginPage.verifyLoginErrorWarningMessage();
	}

	@Test(priority = 3)
	public void loginWithInvalidUsernameAndCorrectPassword() {
		// LoginPage loginPage = new LoginPage(driver);
		loginPage.enterEmail("111@gmail.com");
		loginPage.enterPassword(prop.getProperty("validPassword"));
		loginPage.clickOnLoginButton();
		loginPage.verifyLoginErrorWarningMessage();
	}

	@Test(priority = 4)
	public void loginWithNoCredentials() throws InterruptedException {
		// LoginPage loginPage = new LoginPage(driver);
		Thread.sleep(2000);
		loginPage.clickOnLoginButton();
		loginPage.verifyLoginErrorWarningMessage();
	}

	@AfterMethod
	public void quitBrowser() {
		driver.quit();
	}
}
