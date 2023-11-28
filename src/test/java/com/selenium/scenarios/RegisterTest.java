package com.selenium.scenarios;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.selenium.base.Base;
import com.selenium.pages.HomePage;
import com.selenium.pages.RegisterPage;
import com.selenium.utils.Utilities;

public class RegisterTest extends Base {

	public WebDriver driver;
	RegisterPage registerPage;
	
	public RegisterTest() {
		super();
	}

	@BeforeMethod
	public void startUp() throws Exception {
		driver = initializeBrowserAndLaunchApplicationURL(prop.getProperty("browserName"));
		HomePage homePage = new HomePage(driver);
		homePage.selectMyAccount();
		homePage.selectRegister();
		registerPage = new RegisterPage(driver);
	}

	@AfterMethod
	public void quitBrowser() {
		driver.quit();
	}

	@Test(priority = 1)
	public void registerAccountWithValidFields() throws Throwable {

		//RegisterPage registerPage = new RegisterPage(driver);
		registerPage.enterFirstName("Subhash");
		registerPage.enterLastName("Sunagar");
		registerPage.entereEmail("Subhash" + Utilities.randomNumber() + "@gamil.com");
		registerPage.enterTelephone("1234567890");
		registerPage.enterPassword(prop.getProperty("validPassword"));
		registerPage.enterConfirmPassword(prop.getProperty("validPassword"));

		registerPage.clickOnPrivacyPolicyAgreement();
		registerPage.clickOnContinueButtonToRegister();

		registerPage.verifyAccountCreatedSuccessfullyMessage();
		
		// TakeScreenshot
		Utilities.takeScreenshot(driver,"AccountCreated");
	}

	@Test(priority = 2)
	public void registerAccountWithValidFieldsAndSubscribeAsYes() {

		//RegisterPage registerPage = new RegisterPage(driver);
		registerPage.enterFirstName("Subhash");
		registerPage.enterLastName("Sunagar");
		registerPage.entereEmail("Subhash" + Utilities.randomNumber() + "@gamil.com");
		registerPage.enterTelephone("1234567890");
		registerPage.enterPassword(prop.getProperty("validPassword"));
		registerPage.enterConfirmPassword(prop.getProperty("validPassword"));
		registerPage.selectNewsLetterSubscription();
		registerPage.clickOnPrivacyPolicyAgreement();
		registerPage.clickOnContinueButtonToRegister();

		registerPage.verifyAccountCreatedSuccessfullyMessage();
	}

	@Test(priority = 3)
	public void registerAccountWithoutAgreeingToPrivacyPolicy() throws Throwable {

		//RegisterPage registerPage = new RegisterPage(driver);
		registerPage.enterFirstName("Subhash");
		registerPage.enterLastName("Sunagar");
		registerPage.entereEmail("Subhash" + Utilities.randomNumber() + "@gamil.com");
		registerPage.enterTelephone("1234567890");
		registerPage.enterPassword(prop.getProperty("validPassword"));
		registerPage.enterConfirmPassword(prop.getProperty("validPassword"));
		registerPage.selectNewsLetterSubscription();
		// Here we are not agreeing the Privacy Policy
		registerPage.clickOnContinueButtonToRegister();

		registerPage.verifyYouMustAgreeToPrivacyPolicyWarningMessage();

		// TakeScreenshot
		Utilities.takeScreenshot(driver,"WithoutPrivacyPolicy");
	}

	@Test(priority = 4)
	public void registerAccountWithoutEnteringAnyFields() throws Throwable {
		//RegisterPage registerPage = new RegisterPage(driver);
		registerPage.clickOnContinueButtonToRegister();

		registerPage.verifyYouMustAgreeToPrivacyPolicyWarningMessage();
		registerPage.verifyFirstNameErrorMessage();
		registerPage.verifyLastNameErrorMessage();
		registerPage.verifyEmailFieldErrorMessage();
		registerPage.verifyTelephoneFieldErrroMessage();
		registerPage.verifyPasswordFieldErrroMessage();

		// TakeScreenshot
		Utilities.takeScreenshot(driver,"RegisterErrorValidation");
	}
}
