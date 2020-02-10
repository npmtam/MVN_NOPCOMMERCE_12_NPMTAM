package com.nopcommerce.frontend;

import org.testng.annotations.AfterTest;
import org.testng.annotations.Parameters;
import pageObjects.HomePageObject;
import pageObjects.LoginPageObject;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import static org.testng.Assert.assertTrue;

import java.util.concurrent.TimeUnit;

import commons.AbstractPage;
import commons.AbstractTest;
import commons.PageGeneratorManager;
/*import pageObjects.HomePageObject;
import pageObjects.LoginPageObject;*/

public class FE_02_Login extends AbstractTest{
	WebDriver driver;
	private HomePageObject homePage;
	private AbstractPage abstractPage;
	private LoginPageObject loginPage;
	private FE_01_Register registerTestcasess;
	public String email;

	@Parameters("browser")
	@BeforeClass
	public void beforeClass(String browserName) {
		driver = getBrowserDriver(browserName);
		abstractPage = new AbstractPage(driver);
		registerTestcasess = new FE_01_Register();
		email = "tamqada@gmail.com";
	}
	
	@Test
	public void TC_01_LoginWithEmptyData() {
		log.info("Login - TC01 - Step 01: Click to Login link on Header");
		homePage = PageGeneratorManager.getHomePage(driver);
		homePage.clickToLoginLink();
		
		log.info("Login - TC01 - Step 02: Click to login button");
		loginPage = PageGeneratorManager.getLoginPage(driver);
		abstractPage.sleepInSecond(1);
		loginPage.clickToLoginButton();
		log.info("Login - TC01 - Step 03: Verify error message");
		verifyTrue(loginPage.isEmailErrorMsgEquals("Please enter your email"));
	}
	
	@Test
	public void TC_02_LoginWithInvalidEmail() {
		log.info("Login - TC02 - Step 01: Fill invalid email");
		loginPage.inputToEmailTextBox("123");
		
		log.info("Login - TC02 - Step 02: Click to login button");
		loginPage.clickToLoginButton();
		
		log.info("Login - TC02 - Step 03: Verify error message");
		verifyTrue(loginPage.isEmailErrorMsgEquals("Wrong email"));	
	}
	
	@Test
	public void TC_03_RegisterWithUnRegisteredEmail() {
		log.info("Login - TC03 - Step 01: Fill unregisted email");
		loginPage.inputToEmailTextBox("tamqada@hotmail.com");
		
		log.info("Login - TC03 - Step 02: Click to login button");
		loginPage.clickToLoginButton();
		abstractPage.sleepInSecond(1);
		
		log.info("Login - TC03 - Step 03: Verify error message");
		verifyTrue(loginPage.isErrorMessageContains("No customer account found"));
	}
	
	@Test
	public void TC_04_LoginWithEmptyPassword() {
		log.info("Login - TC04 - Step 01: Input email");
		loginPage.inputToEmailTextBox(email);
		
		log.info("Login - TC04 - Step 02: Click to login button");
		loginPage.clickToLoginButton();
		abstractPage.sleepInSecond(1);
		
		log.info("Login - TC04 - Step 03: Verify error message");
		verifyTrue(loginPage.isErrorMessageContains("The credentials provided are incorrect"));
	}
	
	@Test
	public void TC_05_LoginWithIncorrectPassword() {
		log.info("Login - TC05 - Step 01: Fill info");
		loginPage.inputToEmailTextBox(email);
		loginPage.inputToPasswordButton("123");
		
		log.info("Login - TC05 - Step 02: Click to login button");
		loginPage.clickToLoginButton();
		
		log.info("Login - TC05 - Step 03: Verify error message");
		assertTrue(loginPage.isErrorMessageContains("The credentials provided are incorrect"));
	}
	
	@Test
	public void TC_06_LoginWithValidInfo() {
		log.info("Login - TC06 - Step 01: Fill info");
		abstractPage.sleepInSecond(1);
		loginPage.inputToEmailTextBox(email);
		loginPage.inputToPasswordButton("123123");
		
		log.info("Login - TC05 - Step 02: Click to login button");
		loginPage.clickToLoginButton();
		homePage = PageGeneratorManager.getHomePage(driver);
		abstractPage.sleepInSecond(1);
		
		log.info("Login - TC05 - Step 01: Verify login success");
		verifyTrue(loginPage.isHeaderLinksDisplayed("My account"));
	}

	@AfterTest(alwaysRun = true)
	public void afterClass() {
		closeBrowserAndDriver(driver);
	}
}
