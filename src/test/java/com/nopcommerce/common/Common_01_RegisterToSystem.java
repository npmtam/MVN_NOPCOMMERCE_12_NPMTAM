package com.nopcommerce.common;

import pageObjects.HomePageObject;
import pageObjects.RegisterPageObject;
import org.testng.annotations.BeforeTest;

import commons.AbstractPage;
import commons.AbstractTest;

public class Common_01_RegisterToSystem extends AbstractTest {
	public static String EMAIL, PASSWORD;
	public AbstractPage abstractPage;
	private HomePageObject homePage;
	private RegisterPageObject registerPage;
	
	@BeforeTest
	public void beforeTest() {
		EMAIL = "automation@gmai.com";
		PASSWORD = "123123";
		log.info("Precondition - Register to system");
		abstractPage.sleepInSecond(1);
		registerPage.inputToFirstNameTextBox("John");
		registerPage.inputToLastNameTextBox("Wick");
		registerPage.inputToEmailTextBox(EMAIL);
		registerPage.inputToPasswordTextBox(PASSWORD);
		registerPage.inputToConfirmPasswordTextBox(PASSWORD);
		log.info("Register - TC06 - Step 02: Click to register button");
		registerPage.clickToRegisterButton();

		log.info("Register - TC06 - Step 03: Verify success message");
		verifyTrue(registerPage.isResultMatched("Your registration completed"));
	}
}
