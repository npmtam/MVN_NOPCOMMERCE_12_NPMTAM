package com.nopcommerce.frontend;

import static org.junit.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

import java.util.concurrent.TimeUnit;

import org.testng.annotations.AfterTest;
import org.testng.annotations.Parameters;
import pageObjects.HomePageObject;
import pageObjects.LoginPageObject;
import pageObjects.MyAccountPO;
import pageObjects.ProductDetailsPO;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import commons.AbstractPage;
import commons.AbstractTest;
import commons.PageGeneratorManager;
/*import pageObjects.MyAccountPO;
import pageObjects.HomePageObject;
import pageObjects.LoginPageObject;
import pageObjects.ProductDetailsPO;*/

public class FE_03_MyAccount extends AbstractTest {
    WebDriver driver;
    private HomePageObject homePage;
    private AbstractPage abstractPage;
    private LoginPageObject loginPage;
    private MyAccountPO myAccountPage;
    private ProductDetailsPO productPage;
    String email;
    String emailUpdate;
    String password;
    String passwordUpdate;
    String newAddressEmail;
    String newAddressCity;
    String newAddressZipCode;
    String newAddressVeirifyCityAndZipCode;


    @Parameters("browser")
    @BeforeClass
    public void beforeClass(String browserName) {
        driver = getBrowserDriver(browserName);
        abstractPage = new AbstractPage(driver);

        email = "automation-testing@gmail.com";
        emailUpdate = "tamqada@gmail.com";
        newAddressEmail = "automationfc.vn@gmail.com";
        newAddressCity = "Da Nang";
        newAddressZipCode = "550000";
        newAddressVeirifyCityAndZipCode = newAddressCity + ", " + newAddressZipCode;
        passwordUpdate = "automation";
        password = "098765";


        log.info("Pre-condition - Step 01: Login to account");
        homePage = PageGeneratorManager.getHomePage(driver);
        homePage.clickToLoginLink();
        loginPage = PageGeneratorManager.getLoginPage(driver);
        abstractPage.sleepInSecond(1);
        abstractPage.sleepInSecond(1);
        loginPage.inputToEmailTextBox(email);
        loginPage.inputToPasswordButton(password);
        loginPage.clickToLoginButton();
        homePage = PageGeneratorManager.getHomePage(driver);
        abstractPage.sleepInSecond(1);
        verifyTrue(loginPage.isHeaderLinksDisplayed("My account"));
    }

    @Test
    public void TC_01_UpdateCustomerInfo() {
        log.info("My Account - TC01 - Step 01: Access my account link from header");
        homePage = PageGeneratorManager.getHomePage(driver);
        homePage.openMultiplePagesHeader("My account");
        myAccountPage = PageGeneratorManager.getHeaderMyAccountPage(driver);

        log.info("My Account - TC01 - Step 02: Verify access my account successfuly");
        verifyTrue(myAccountPage.isCurrentURLContains("customer/info"));

        log.info("My Account - TC01 - Step 03: Fill info");
        abstractPage.sleepInSecond(1);
        myAccountPage.clickToGenderRadioButton("female");
        myAccountPage.inputToCustomerInfoTextboxs("FirstName", "Automation");
        myAccountPage.inputToCustomerInfoTextboxs("LastName", "FC");
        myAccountPage.selectBirdtDateDropDownList("Day", "1");
        myAccountPage.selectBirdtDateDropDownList("Month", "January");
        myAccountPage.selectBirdtDateDropDownList("Year", "1999");
//		myAccountPage.inputToCustomerInfoTextboxs("Email", emailUpdate);
        myAccountPage.inputToCustomerInfoTextboxs("Company", "Automation FC");

        log.info("My Account - TC01 - Step 04: Click to save button");
        myAccountPage.clickToSaveButton();

        log.info("My Account - TC01 - Step 05: Verify update info successful");
        verifyFalse(myAccountPage.isErrorMessagePresentInDOM());
    }

    @Test
    public void TC_02_UpdateAddress() {
        log.info("My Account - TC02 - Step 01: Access my account");
        abstractPage.sleepInSecond(1);
        myAccountPage.clickToMyAccountLinks("addresses");
        abstractPage.sleepInSecond(1);

        log.info("My Account - TC02 - Step 01: Verify access my account section success");
        verifyTrue(myAccountPage.isCurrentURLContains("customer/addresses"));

        log.info("My Account - TC02 - Step 03: Click to add new address");
        myAccountPage.clickToAddNewButton();
        abstractPage.sleepInSecond(1);

        log.info("My Account - TC02 - Step 02: Fill info for new address");
        myAccountPage.inputToAddressTextboxes("FirstName", "Automation");
        myAccountPage.inputToAddressTextboxes("LastName", "FC");
        myAccountPage.inputToAddressTextboxes("Email", newAddressEmail);
        myAccountPage.inputToAddressTextboxes("Company", "Automation FC");
        myAccountPage.selectCountryDropdownList("Viet Nam");
        myAccountPage.inputToAddressTextboxes("City", newAddressCity);
        myAccountPage.inputToAddressTextboxes("Address1", "123/04 Le Lai");
        myAccountPage.inputToAddressTextboxes("Address2", "234/05 Hai Phong");
        myAccountPage.inputToAddressTextboxes("ZipPostalCode", newAddressZipCode);
        myAccountPage.inputToAddressTextboxes("PhoneNumber", "0123456789");
        myAccountPage.inputToAddressTextboxes("FaxNumber", "0987654321");

        log.info("My Account - TC02 - Step 03: Click to save");
        myAccountPage.clickToSaveButton();

        log.info("My Account - TC02 - Step 04: Verify add new address successful");
        abstractPage.sleepInSecond(1);
        verifyTrue(myAccountPage.isNewAddressAddedEquals("email", newAddressEmail));
        verifyTrue(myAccountPage.isNewAddressCityAndZipEquals("city-state-zip", newAddressVeirifyCityAndZipCode));
    }

    @Test
    public void TC_03_ChangePassword() {
        log.info("My Account - TC03 - Step 01: Access change password menu");
        myAccountPage.clickToMyAccountLinks("changepassword");
        abstractPage.sleepInSecond(1);

        log.info("My Account - TC03 - Step 02: Fill password");
        myAccountPage.inputToChangePasswordTextboxes("OldPassword", password);
        myAccountPage.inputToChangePasswordTextboxes("NewPassword", passwordUpdate);
        myAccountPage.inputToChangePasswordTextboxes("ConfirmNewPassword", passwordUpdate);

        log.info("My Account - TC03 - Step 03: Click to change password");
        myAccountPage.clickToChangePasswordButton();

        log.info("My Account - TC03 - Step 04: Verify success message");
        verifyTrue(myAccountPage.isChangePasswordResultEquals("Password was changed"));

        log.info("My Account - TC03 - Step 05: Log out");
        abstractPage.sleepInSecond(1);
        myAccountPage.openMultiplePagesHeader("Log out");
        abstractPage.sleepInSecond(1);

        log.info("My Account - TC03 - Step 06: Login with incorrect password");
        myAccountPage.openMultiplePagesHeader("Log in");
        loginPage = PageGeneratorManager.getLoginPage(driver);
        loginPage.inputToEmailTextBox(email);
        loginPage.inputToPasswordButton("1111111");
        loginPage.clickToLoginButton();

        log.info("My Account - TC03 - Step 07: Verify error message");
        verifyTrue(loginPage.isErrorMessageContains("The credentials provided are incorrect"));

        log.info("My Account - TC03 - Step 08: Login with new password");
        loginPage.inputToEmailTextBox(email);
        loginPage.inputToPasswordButton(passwordUpdate);
        loginPage.clickToLoginButton();
        homePage = PageGeneratorManager.getHomePage(driver);
        abstractPage.sleepInSecond(1);

        log.info("My Account - TC03 - Step 09: Verify login successful");
        verifyTrue(loginPage.isHeaderLinksDisplayed("My account"));
    }

    @Test
    public void TC_04_MyProductReview() {
        log.info("My Account - TC04 - Step 01: Access product details page");
        homePage.clickToProductTitle("Apple MacBook");
        productPage = PageGeneratorManager.getProductDetailsPage(driver);
        log.info("My Account - TC04 - Step 02: Add new review");
        productPage.clickToAddReviewLink();
        assertTrue(productPage.isProductReviewTitleDisplayed());
        productPage.inputToReviewTitleTextbox("Nice one");
        productPage.inputToReviewTextTextArea("Nice one with MacOS. beatiful and very slim");
        productPage.clickToSubmitReviewButton();

        log.info("My Account - TC04 - Step 03: Verify new review has been added");
        verifyTrue(productPage.isAddReviewResultContains("successfully added"));

        log.info("My Account - TC04 - Step 04: Access my account");
        homePage.openMultiplePagesHeader("My account");
        myAccountPage = PageGeneratorManager.getHeaderMyAccountPage(driver);

        log.info("My Account - TC04 - Step 05: Verify new review in my account section");
        myAccountPage.clickToMyAccountLinks("productreviews");
        verifyTrue(myAccountPage.isPageTitleContains("My product reviews"));
    }

    @AfterTest(alwaysRun = true)
    public void afterClass() {
        closeBrowserAndDriver(driver);
    }
}
