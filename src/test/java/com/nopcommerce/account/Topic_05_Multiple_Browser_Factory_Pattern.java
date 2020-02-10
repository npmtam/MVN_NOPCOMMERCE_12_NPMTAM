package com.nopcommerce.account;

import static org.testng.Assert.assertTrue;

import pageObjects.HomePageObject;
import pageObjects.LoginPageObject;
import pageObjects.RegisterPageObject;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import commons.AbstractPage;
import commons.AbstractTest;
import commons.PageGeneratorManager;

public class Topic_05_Multiple_Browser_Factory_Pattern extends AbstractTest {
	private WebDriver driver;
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
		
		System.out.println(driver.toString());
		abstractPage = new AbstractPage(driver);
		driver.get("https://demo.nopcommerce.com/");
		email = "tamnguyen_" + abstractPage.randomNumber() + "@gmail.com";
		password = "123123";
		
	}

	@Test
	public void TC_01_Register() throws InterruptedException {
		homePage = PageGeneratorManager.getHomePage(driver);
		homePage.clickToRegisterLink();
		
		registerPage = PageGeneratorManager.getRegisterPage(driver);
		abstractPage.sleepInSecond(1);
		registerPage.selectMaleGenderCheckBox();
		registerPage.inputToFirstNameTextBox("Tam");
		registerPage.inputToLastNameTextBox("Nguyen");
		registerPage.selectDateInDropDown("10");
		registerPage.selectMonthInDropDown("October");
		registerPage.selectYearInDropDown("1993");
		registerPage.inputToEmailTextBox(email);
		registerPage.inputToCompanyTextBox("Step Sister");
		registerPage.inputToPasswordTextBox(password);
		registerPage.inputToConfirmPasswordTextBox(password);
		
		registerPage.clickToRegisterButton();
		boolean registerSuccess = registerPage.isResultMatched("Your registration completed");
		abstractPage.sleepInSecond(1);
		registerPage.clickToContinueButton();
		assertTrue(registerSuccess);
	}

	@Test
	public void TC_02_Login() {
		abstractPage.sleepInSecond(2);
		homePage = PageGeneratorManager.getHomePage(driver);
		homePage.clickToSignOutButton();
		abstractPage.sleepInSecond(2);
		homePage.clickToLoginLink();
		abstractPage.sleepInSecond(1);
		loginPage = PageGeneratorManager.getLoginPage(driver);
		loginPage.inputToEmailTextBox(email);
		loginPage.inputToPasswordButton(password);
		
		loginPage.clickToLoginButton();
		
		homePage = PageGeneratorManager.getHomePage(driver);
		assertTrue(homePage.isMyAccountLinkDisplayed());
	}

}
