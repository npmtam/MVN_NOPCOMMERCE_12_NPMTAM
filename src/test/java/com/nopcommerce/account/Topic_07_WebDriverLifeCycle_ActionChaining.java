package com.nopcommerce.account;

import static org.testng.Assert.assertTrue;

import java.util.concurrent.TimeUnit;

import pageObjects.*;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import commons.AbstractPage;
import commons.PageGeneratorManager;
/*import pageObjects.FooterShoppingCartPO;
import pageObjects.MyAccountPO;
import pageObjects.WishListPO;
import pageObjects.HomePageObject;
import pageObjects.LoginPageObject;
import pageObjects.RegisterPageObject;*/

public class Topic_07_WebDriverLifeCycle_ActionChaining {
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
	private MyAccountPO headerMyAccountPage;
	private WishListPO headerWishListPage;
	private FooterShoppingCartPO footerShoppingCartPage;
	
	
//	@Parameters("browser")
	@BeforeClass
	public void beforeClass() {	
		System.setProperty("webdriver.chrome.driver", ".\\libaries\\chromedriver.exe");
		driver = new ChromeDriver();
		abstractPage = new AbstractPage(driver);
		driver.get("https://demo.nopcommerce.com/");
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
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
	
	@Test
	public void TC_03_ActionChaining() {
		headerMyAccountPage.openMultiplePagesHeader("My account");
		abstractPage.sleepInSecond(1);
		
		footerShoppingCartPage.openMultiplePagesFooter("Shopping cart");
		abstractPage.sleepInSecond(1);
	}
}
