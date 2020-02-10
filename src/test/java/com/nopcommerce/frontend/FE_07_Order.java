package com.nopcommerce.frontend;

import commons.AbstractPage;
import commons.AbstractTest;
import commons.PageGeneratorManager;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pageObjects.HomePageObject;
import pageObjects.LoginPageObject;

public class FE_07_Order extends AbstractTest {
    private WebDriver driver;
    private AbstractPage abstractPage;
    private HomePageObject homePage;
    private LoginPageObject loginPage;
    String email, password, productName1;

    @Parameters("browser")
    @BeforeTest
    public void beforeTest(String browserName){
        driver = getBrowserDriver(browserName);
        abstractPage = new AbstractPage(driver);

        email = "tamqada@gmail.com";
        password = "123123";
        productName1 = "Build your own computer";

        log.info("Precondition: Login");
        homePage = PageGeneratorManager.getHomePage(driver);
        homePage.clickToLoginLink();
        abstractPage.sleepInSecond(1);
        loginPage = PageGeneratorManager.getLoginPage(driver);
        loginPage.inputToEmailTextBox(email);
        loginPage.inputToPasswordButton(password);
        loginPage.clickToLoginButton();
        verifyTrue(loginPage.isHeaderLinksDisplayed("My account"));

        log.info("Pre-condition: Open product details");
        homePage = PageGeneratorManager.getHomePage(driver);
        abstractPage.sleepInSecond(1);
        homePage.clickToProductTitle(productName1);
    }

    @Test
    public void TC_01_AddProductToCart(){
        log.info("Order - TC01 -  Step 01: ");
    }

}
