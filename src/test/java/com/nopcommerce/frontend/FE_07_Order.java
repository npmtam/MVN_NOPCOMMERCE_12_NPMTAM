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
    String email, password, productName1, productName2;

    @Parameters("browser")
    @BeforeTest
    public void beforeTest(String browserName) {
        driver = getBrowserDriver(browserName);
        abstractPage = new AbstractPage(driver);

        email = "tamqada@gmail.com";
        password = "123123";
        productName1 = "Apple MacBook Pro 13-inch";
        productName2 = "Lenovo IdeaCentre 600 All-in-One PC";

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

    @Test
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

    public void TC_02_EditProductInShoppingCart() {
//        log.info("Order - TC02 - Step 01: Access Shopping cart");
//        homePage = PageGeneratorManager.getHomePage(driver);
//        homePage.clickToHeaderShoppingCart();
    }

    @Test
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
        orderPage.clickToAddToCartButtonFromCategories(productName2);

        log.info("Order - TC04 - Step 04: Verify the product has been added to Cart");
        wishListPage = PageGeneratorManager.getWishListPage(driver);
        wishListPage.isSuccessMessageContains("The product has been added to your ");

        log.info("Order - TC04 - Step 05: Access Shopping Cart page");
        wishListPage.clickToAccessLinkFromSuccessMsg("shopping cart");

        log.info("Order - TC04 - Step 06: Input the quantity is 5");
        orderPage = PageGeneratorManager.getOrderPage(driver);
        orderPage.inputToQuantityTextbox("5");

        log.info("Order - TC04 - Step 07: Update shopping cart");
        wishListPage = PageGeneratorManager.getWishListPage(driver);
        wishListPage.clickToUpdateWishListButton();

        log.info("Order - TC04 - Step 08: Verify total amount");
        orderPage = PageGeneratorManager.getOrderPage(driver);
        verifyTrue(orderPage.isTotalPriceEquals("$2,500.00"));
    }

    @AfterTest(alwaysRun = true)
    public void afterClass() {
        closeBrowserAndDriver(driver);
    }
}
