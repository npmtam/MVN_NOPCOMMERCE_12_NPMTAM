package com.nopcommerce.frontend;

import commons.AbstractPage;
import commons.AbstractTest;
import commons.PageGeneratorManager;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pageObjects.HomePageObject;
import pageObjects.LoginPageObject;
import pageObjects.OrderPO;
import pageObjects.WishListPO;

public class FE_07_Order extends AbstractTest {
    private WebDriver driver;
    private AbstractPage abstractPage;
    private HomePageObject homePage;
    private LoginPageObject loginPage;
    private WishListPO wishListPage;
    private OrderPO orderPage;
    String email, password, productName1, productName2, totalPrice, country, address1, address2, zipCode, phoneNumber, faxNumber;

    @Parameters("browser")
    @BeforeTest
    public void beforeTest(String browserName) {
        driver = getBrowserDriver(browserName);
        abstractPage = new AbstractPage(driver);

        email = "tamqada@gmail.com";
        password = "123123";
        productName1 = "Apple MacBook Pro 13-inch";
        productName2 = "Lenovo IdeaCentre 600 All-in-One PC";
        country = "Viet Nam";
        address1 = "271 Nguyen Van Linh";
        address2 = "02 Quang Trung";
        zipCode = "550000";
        phoneNumber = "356822833";
        faxNumber = "45555643232";

        log.info("Precondition: Login");
        homePage = PageGeneratorManager.getHomePage(driver);
        homePage.clickToLoginLink();
        abstractPage.sleepInSecond(1);
        loginPage = PageGeneratorManager.getLoginPage(driver);
        loginPage.inputToEmailTextBox(email);
        loginPage.inputToPasswordButton(password);
        loginPage.clickToLoginButton();
        verifyTrue(loginPage.isHeaderLinksDisplayed("My account"));
    }

//    @Test
    public void TC_01_AddProductToCart() {
        log.info("Order - TC01 -  Step 01: Open product details of " + productName1);
        homePage = PageGeneratorManager.getHomePage(driver);
        abstractPage.sleepInSecond(1);
        homePage.clickToProductTitle(productName1);

        log.info("Order - TC01 - Step 02: Verify open product details successfully");
        wishListPage = PageGeneratorManager.getWishListPage(driver);
        verifyTrue(wishListPage.isWishListButtonDisplayed());

        log.info("Order - TC01 - Step 03: Press Add to cart button");
        orderPage = PageGeneratorManager.getOrderPage(driver);
        orderPage.clickToAddToCartButton();

        log.info("Order - TC01 - Step 04: Verify success message");
        wishListPage = PageGeneratorManager.getWishListPage(driver);
        wishListPage.isSuccessMessageContains("The product has been added to your ");

        log.info("Order - TC01 - Step 05: Access shopping cart page");
        wishListPage.clickToAccessLinkFromSuccessMsg("shopping cart");

        log.info("Order - TC01 - Step 06: Verify the product has been added to Cart page");
        abstractPage.sleepInSecond(1);
        verifyTrue(wishListPage.isProductPresented(productName1));
    }

//    public void TC_02_EditProductInShoppingCart() {
//        log.info("Order - TC02 - Step 01: Access Shopping cart");
//        homePage = PageGeneratorManager.getHomePage(driver);
//        homePage.clickToHeaderShoppingCart();
//    }

//    @Test
    public void TC_03_RemoveProductFromCart() {
        log.info("Order - TC03 - Step 01: Access Shopping Cart");
        homePage = PageGeneratorManager.getHomePage(driver);
        homePage.clickToHeaderShoppingCart();

        log.info("Order - TC03 - Step 02: Select checkbox Remove");
        wishListPage = PageGeneratorManager.getWishListPage(driver);
        wishListPage.clickToRemoveCheckbox();

        log.info("Order - TC03 - Step 03: Click button Update shopping cart");
        wishListPage.clickToUpdateWishListButton();

        log.info("Order - TC03 - Step 04: Verify success message appears");
        verifyTrue(wishListPage.isErrorMessageEquals("Your Shopping Cart is empty!"));

        log.info("Order - TC03 - Step 05: Verify no product present");
        verifyTrue(wishListPage.isProductsPresentEquals(0));
    }

    @Test
    public void TC_04_UpdateShoppingCart(){
        log.info("Order - TC04 - Step 01: Access Home page");
        homePage = PageGeneratorManager.getHomePage(driver);
        homePage.clickToLogoToBackHome();

        log.info("Order - TC04 - Step 02: Select Desktop from menu Computer");
        abstractPage.openDynamicSubMenu("Computers", "Desktops ");

        log.info("Order - TC04 - Step 03: Click to Add to cart button from product " + productName2);
        orderPage = PageGeneratorManager.getOrderPage(driver);
        abstractPage.sleepInSecond(1);
        orderPage.clickToAddToCartButtonFromCategories(productName2);

        log.info("Order - TC04 - Step 04: Verify the product has been added to Cart");
        wishListPage = PageGeneratorManager.getWishListPage(driver);
        wishListPage.isSuccessMessageContains("The product has been added to your ");

        log.info("Order - TC04 - Step 05: Access Shopping Cart page");
        wishListPage.clickToAccessLinkFromSuccessMsg("shopping cart");

        log.info("Order - TC04 - Step 06: Input the quantity is 5");
        orderPage = PageGeneratorManager.getOrderPage(driver);
        abstractPage.sleepInSecond(1);
        orderPage.inputToQuantityTextbox("5");

        log.info("Order - TC04 - Step 07: Update shopping cart");
        wishListPage = PageGeneratorManager.getWishListPage(driver);
        wishListPage.clickToUpdateWishListButton();

        log.info("Order - TC04 - Step 08: Verify total amount");
        orderPage = PageGeneratorManager.getOrderPage(driver);
        verifyTrue(orderPage.isTotalPriceEquals("$2,500.00"));

        log.info("Order - TC04 - Step 09: Remove the product from Cart");
        wishListPage = PageGeneratorManager.getWishListPage(driver);
        abstractPage.sleepInSecond(1);
        wishListPage.clickToRemoveCheckbox();
        wishListPage.clickToUpdateWishListButton();
        verifyTrue(wishListPage.isProductsPresentEquals(0));
    }

    @Test
    public void TC_05_CheckOut_Oder(){
        log.info("Order - TC05 - Step 01: Access Home page");
        homePage = PageGeneratorManager.getHomePage(driver);
        homePage.clickToLogoToBackHome();

        log.info("Order - TC05 - Step 02: Open product details of " + productName1);
        abstractPage.sleepInSecond(1);
        homePage.clickToProductTitle(productName1);

        log.info("Order - TC05 - Step 03: Add product " + productName1 + " to Cart");
        orderPage = PageGeneratorManager.getOrderPage(driver);
        abstractPage.sleepInSecond(1);
        orderPage.clickToAddToCartButton();

        log.info("Order - TC05 - Step 04: Verify success message");
        wishListPage = PageGeneratorManager.getWishListPage(driver);
        verifyTrue(wishListPage.isSuccessMessageContains("The product has been added to your "));

        log.info("Order - TC05 - Step 05: Access Shopping cart");
        abstractPage.sleepInSecond(1);
        wishListPage.clickToAccessLinkFromSuccessMsg("shopping cart");

        log.info("Order - TC06 - Step 06: Select Gift wrapping dropdown list");
        orderPage = PageGeneratorManager.getOrderPage(driver);
        orderPage.selectGiftWrappingDropdown(1);

        log.info("Order - TC05 - Step 07: Select country from dropdown list");
        orderPage.selectEstimateShippingDropdownLists("country", "Viet Nam");

        log.info("Order - TC05 - Step 08: Select state from drop down list");
        orderPage.selectEstimateShippingDropdownLists("state", "Other (Non US)");

        log.info("Order - TC05 - Step 09: Input zip/postal code");
        orderPage.inputToZipCodeTextbox("550000");

        log.info("Order - TC05 - Step 10: Click on checkbox to agree with the ToU");
        orderPage.clickToAgreeTermOfUse();

        log.info("Order - TC05 - Step 11: Get total price and checkout");
        totalPrice = orderPage.getTotalPrice();
        System.out.println(totalPrice);
        orderPage.clickToCheckOut();
        verifyTrue(abstractPage.isSubPageTitleEquals("Checkout"));

        log.info("Order - TC05 - Step 12: Input to company textbox");
        orderPage.inputToTextboxesInCheckout("Company", "Foody Corp");

        log.info("Order - TC05 - Step 13: Select country drop down list");
        orderPage.selectCountryDropdownList(country);

        log.info("Order - TC05 - Step 14: Input city");
        orderPage.inputToTextboxesInCheckout("City", "Da Nang");

        log.info("Order - TC05 - Step 15: Input address 1");
        orderPage.inputToTextboxesInCheckout("Address1", address1);

        log.info("Order - TC05 - Step 16: Input address 2");
        orderPage.inputToTextboxesInCheckout("Address2", address2);

        log.info("Order - TC05 - Step 17: Input zip code");
        orderPage.inputToTextboxesInCheckout("ZipPostalCode", zipCode);

        log.info("Order - TC05 - Step 18: Input phone number");
        orderPage.inputToTextboxesInCheckout("PhoneNumber", phoneNumber);

        log.info("Order - TC05 - Step 19: Input fax number");
        orderPage.inputToTextboxesInCheckout("FaxNumber", faxNumber);

        log.info("Order - TC05 - Step 20: Click Continue button");
        orderPage.clickToContinueButton();

        abstractPage.sleepInSecond(20);
    }

    @AfterTest(alwaysRun = true)
    public void afterClass() {
        closeBrowserAndDriver(driver);
    }
}

