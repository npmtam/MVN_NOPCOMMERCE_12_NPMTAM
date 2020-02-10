package com.nopcommerce.account;

import static org.testng.Assert.assertTrue;
import static org.testng.Assert.assertEquals;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;


import commons.AbstractPage;

public class Topic_02_Apply_Abstract_Page {
	WebDriver driver;
	Select select;
	JavascriptExecutor jsExecutor;
	WebDriverWait waitExplicit;
	String email;
	String password;
	
	public AbstractPage abstractPage;

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.chrome.driver", ".\\libaries\\chromedriver.exe");
		driver = new ChromeDriver();
		abstractPage = new AbstractPage(driver);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		email = "tamnguyen_" + abstractPage.randomNumber() + "@gmail.com";
		password = "123123";
	}

	@Test
	public void TC_01_Register() throws InterruptedException {
		abstractPage.openURL("https://demo.nopcommerce.com/login");
		
		abstractPage.clickToElement("//a[@class='ico-register']");
		assertTrue(abstractPage.isElementDisplayed("//div[@class='page registration-page']"));
		
		abstractPage.clickToElement("//input[@id='gender-male']");
		abstractPage.sendKeyToElement("//input[@id='FirstName']", "Danny");
		abstractPage.sendKeyToElement("//input[@id='LastName']", "Cee");
		
		abstractPage.selectItemInDropdown("//select[@name='DateOfBirthDay']", "10");
		abstractPage.selectItemInDropdown("//select[@name='DateOfBirthMonth']", "October");
		abstractPage.selectItemInDropdown("//select[@name='DateOfBirthYear']", "1993");
		
		abstractPage.sendKeyToElement("//input[@id='Email']", email);
		abstractPage.sendKeyToElement("//input[@id='Company']", "Step Sister");
		abstractPage.sendKeyToElement("//input[@id='Password']", password);
		abstractPage.sendKeyToElement("//input[@id='ConfirmPassword']", password);
		
		abstractPage.clickToElement("//input[@id='register-button']");
		assertEquals(abstractPage.getTextElement("//div[@class='result']"), "Your registration completed");
	}

	@Test
	public void TC_02_Login() {
		abstractPage.clickToElement("//input[@name='register-continue']");
		
		abstractPage.sendKeyToElement("//input[@class='email']", email);
		abstractPage.sendKeyToElement("//input[@class='password']", password);
		abstractPage.clickToElement("//input[@class='button-1 login-button']");
		
		abstractPage.isElementDisplayed("//a[@class='ico-account']");
		
	}

}
