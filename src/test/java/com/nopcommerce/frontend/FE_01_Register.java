package com.nopcommerce.frontend;

import static org.testng.Assert.assertTrue;
import static org.testng.Assert.assertEquals;

import org.testng.annotations.AfterTest;
import pageObjects.HomePageObject;
import pageObjects.LoginPageObject;
import pageObjects.RegisterPageObject;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import commons.AbstractPage;
import commons.AbstractTest;
import commons.PageGeneratorManager;
/*import pageObjects.HomePageObject;
import pageObjects.LoginPageObject;
import pageObjects.RegisterPageObject;*/

public class FE_01_Register extends AbstractTest {
	WebDriver driver;
	Select select;
	JavascriptExecutor jsExecutor;
	WebDriverWait waitExplicit;
	String email;
	String password;

	public AbstractPage abstractPage;
	private HomePageObject homePage;
	private RegisterPageObject registerPage;
	private LoginPageObject loginPage;

	@Parameters("browser")
	@BeforeClass
	public void beforeClass(String browserName) {
		driver = getBrowserDriver(browserName);
		abstractPage = new AbstractPage(driver);

		email = "tamnguyen_" + abstractPage.randomNumber() + "@gmail.com";
		password = "123123";
	}

	@Test
	public void TC_01_RegisterWithEmptyData() {
		log.info("Register - TC01 - Step 01: Click to register link on Header");
		homePage = PageGeneratorManager.getHomePage(driver);
		homePage.clickToRegisterLink();
		abstractPage.sleepInSecond(1);
		
		log.info("Register - TC01 - Step 02: Click to register button");
		registerPage = PageGeneratorManager.getRegisterPage(driver);
		registerPage.clickToRegisterButton();
		abstractPage.sleepInSecond(1);

		log.info("Register - TC01 - Step 03: Verify error messages");
		verifyTrue(registerPage.isErrorMessageDisplayed("FirstName"));
		verifyTrue(registerPage.isErrorMessageDisplayed("LastName"));
		verifyTrue(registerPage.isErrorMessageDisplayed("Email"));
		verifyTrue(registerPage.isErrorMessageDisplayed("Password"));
		verifyTrue(registerPage.isErrorMessageDisplayed("ConfirmPassword"));
	}

	@Test
	public void TC_02_RegisterWithInvalidEmail() {
		log.info("Register - TC02 - Step 01: Fill info");
		abstractPage.sleepInSecond(1);
		registerPage.inputToFirstNameTextBox("John");
		registerPage.inputToLastNameTextBox("Wick");
		registerPage.inputToEmailTextBox("123@123");
		registerPage.inputToPasswordTextBox(password);
		registerPage.inputToConfirmPasswordTextBox(password);
		log.info("Register - TC02 - Step 02: Click to register button");
		registerPage.clickToRegisterButton();
		abstractPage.sleepInSecond(1);
		
		log.info("Register - TC02 - Step 03: Verify error message");
		verifyTrue(registerPage.isErrorMessageContains("field-validation", "Wrong email"));
	}

	@Test
	public void TC_03_RegisterWithExistEmail() {
		log.info("Register - TC03 - Step 01: Fill info");
		abstractPage.sleepInSecond(1);
		registerPage.inputToFirstNameTextBox("John");
		registerPage.inputToLastNameTextBox("Wick");
		registerPage.inputToEmailTextBox("tamqada@gmail.com");
		registerPage.inputToPasswordTextBox(password);
		registerPage.inputToConfirmPasswordTextBox(password);
		log.info("Register - TC03 - Step 02: Click to register button");
		registerPage.clickToRegisterButton();

		log.info("Register - TC03 - Step 03: Verify error message");
		verifyTrue(registerPage.isErrorMessageOnTopDisplayed());
	}

	@Test
	public void TC_04_PasswordLesser6Characters() {
		log.info("Register - TC04 - Step 01: Fill info");
		abstractPage.sleepInSecond(1);
		registerPage.inputToFirstNameTextBox("John");
		registerPage.inputToLastNameTextBox("Wick");
		registerPage.inputToEmailTextBox("tamqada@gmail.com");
		registerPage.inputToPasswordTextBox("123");
		registerPage.inputToConfirmPasswordTextBox("123");
		abstractPage.sleepInSecond(1);
		
		log.info("Register - TC04 - Step 02: Verify error message");
		verifyTrue(registerPage.isErrorMessageContains("field-validation", "must have at least 6 characters"));
	}

	@Test
	public void TC_05_ConfirmPasswordNotMatched() {
		log.info("Register - TC05 - Step 01: Fill info");
		abstractPage.sleepInSecond(1);
		registerPage.inputToFirstNameTextBox("John");
		registerPage.inputToLastNameTextBox("Wick");
		registerPage.inputToEmailTextBox("tamqada@gmail.com");
		registerPage.inputToPasswordTextBox(password);
		registerPage.inputToConfirmPasswordTextBox("123");
		
		log.info("Register - TC05 - Step 02: Verify error message");
		verifyTrue(registerPage.isErrorMessageDisplayed("ConfirmPassword"));
	}

	@Test
	public void TC_06_RegisterWithValidInfo() {
		log.info("Register - TC06 - Step 01: Fill info");
		abstractPage.sleepInSecond(1);
		registerPage.inputToFirstNameTextBox("John");
		registerPage.inputToLastNameTextBox("Wick");
		registerPage.inputToEmailTextBox(email);
		registerPage.inputToPasswordTextBox(password);
		registerPage.inputToConfirmPasswordTextBox(password);
		log.info("Register - TC06 - Step 02: Click to register button");
		registerPage.clickToRegisterButton();

		log.info("Register - TC06 - Step 03: Verify success message");
		verifyTrue(registerPage.isResultMatched("Your registration completed"));
	}

	@Test
	public void TC_07_LogOUt() {
		log.info("Register - TC07 - Step 01: Log out");
		homePage.clickToSignOutButton();
		homePage = PageGeneratorManager.getHomePage(driver);
		abstractPage.sleepInSecond(2);
		verifyTrue(homePage.isLoginLinkDisplayed());
	}

	@AfterTest(alwaysRun = true)
	public void afterClass() {
		closeBrowserAndDriver(driver);
	}
}
