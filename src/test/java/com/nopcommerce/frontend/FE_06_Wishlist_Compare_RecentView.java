package com.nopcommerce.frontend;

import static org.junit.Assert.assertTrue;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import commons.AbstractPage;
import commons.AbstractTest;
import commons.PageGeneratorManager;
import pageObjects.HomePageObject;
import pageObjects.LoginPageObject;
import pageObjects.SearchPO;
import pageObjects.WishListPO;

public class FE_06_Wishlist_Compare_RecentView extends AbstractTest {
	private WebDriver driver;
	private AbstractPage abstractPage;
	private HomePageObject homePage;
	private LoginPageObject loginPage;
	private WishListPO wishListPage;
	private SearchPO searchPage;
	private FE_01_Register registerTestCases;
	String email, productName1, productName2, productName3, productName4, productName5, password;

	@Parameters("browser")
	@BeforeTest
	public void beforeTest(String browserName) {
		System.out.println(browserName);
		driver = getBrowserDriver(browserName);
		
		abstractPage = new AbstractPage(driver);
		searchPage = new SearchPO(driver);
		
		email = "tamqada@gmail.com";
		password = "123123";
		productName1 = "Apple MacBook Pro 13-inch";
		productName2 = "HTC One M8 Android L 5.0 Lollipop";
		productName3 = "HTC One Mini Blue";
		productName4 = "Nokia Lumia 1020";
		productName5 = "Beats Pill 2.0 Wireless Speaker";

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
		
		wishListPage = PageGeneratorManager.getWishListPage(driver);
		verifyTrue(wishListPage.isWishListButtonDisplayed());
	}
	
	@Test
	public void TC_01_AddToWishList() {
		log.info("WishList - TC01 - Step 01: Click to add to wish list button");
		wishListPage.clickAddToWishListButton();
		
		log.info("WishList - TC01 - Step 02: Verify success message");
		wishListPage.isSuccessMessageContains("The product has been added to your ");
		
		log.info("WishList - TC01 - Step 03: Access wishlist page");
		wishListPage.clickToAccessLinkFromSuccessMsg("wishlist");
		
		log.info("WishList - TC01 - Step 04: Verify product added to wish list");
		verifyTrue(wishListPage.isProductAddedToWishList(productName1));
		
		log.info("WishList - TC01 - Step 05: Click to Your wishlist URL for sharing");
		abstractPage.sleepInSecond(1);
		wishListPage.clickToURLSharing();
		
		log.info("WishList - TC01 - Step 06: Verify product present in Wishlist URL for sharing");
		verifyTrue(wishListPage.isProductAddedToWishList(productName1));
	}
	
	@Test
	public void TC_02_AddProductToCartFromWishListPage() {
		log.info("WishList - TC02 - Step 01: Access wishlist page");
		homePage = PageGeneratorManager.getHomePage(driver);
//		abstractPage.sleepInSecond(1);
//		homePage.clickToLogoToBackHome();
//		abstractPage.sleepInSecond(1);
		homePage.clickToHeaderWishList();
		verifyTrue(abstractPage.isSubPageTitleEquals("Wishlist"));
		
		log.info("WishList - TC02 - Step 02: Add product to cart");
		wishListPage = PageGeneratorManager.getWishListPage(driver);
		abstractPage.sleepInSecond(1);
		wishListPage.clickToAddToCartCheckbox();
		verifyTrue(wishListPage.isAddToCardCheckboxSelected());
		wishListPage.clickToAddToCardButton();
		
		log.info("WishList - TC02 - Step 03: Verify the product was added to Cart");
		verifyTrue(abstractPage.isSubPageTitleEquals("Shopping cart"));
		verifyTrue(wishListPage.isProductAddedToWishList(productName1));
		
		log.info("WishList - TC02 - Step 04: Verify the product was removed from Wishlist");
		homePage = PageGeneratorManager.getHomePage(driver);
		homePage.clickToHeaderWishList();
		
		wishListPage = PageGeneratorManager.getWishListPage(driver);
		verifyTrue(wishListPage.isWishListEmpty());
	}
	
	@Test
	public void TC_03_RemoveProductInWishlist() {
		log.info("WishList - TC03  - Step 01: Add product to wishlist");
		homePage = PageGeneratorManager.getHomePage(driver);
		homePage.clickToLogoToBackHome();
		abstractPage.sleepInSecond(1);
		homePage.clickToProductTitle(productName1);
		
		wishListPage = PageGeneratorManager.getWishListPage(driver);
		wishListPage.clickAddToWishListButton();
		
		wishListPage.isSuccessMessageContains("The product has been added to your ");
		
		log.info("WishList - TC03 - Step 02: Access wishlist page");
		wishListPage.clickToAccessLinkFromSuccessMsg("wishlist");
		
		log.info("WishList - TC03 - Step 03: Select checkbox remove");
		abstractPage.sleepInSecond(1);
		wishListPage.clickToRemoveCheckbox();
		
		log.info("WishList - TC03 - Step 04: Click button UPDATE WISHLIST");
		wishListPage.clickToUpdateWishListButton();
		
		log.info("WishList - TC03 - Step 05: Verify message: The wishlist is empty!");
		wishListPage.isErrorMessageEquals("The wishlist is empty!");
		
		log.info("WishList - TC03 - Step 06: Verify the product is not present in WishList");
		verifyTrue(wishListPage.isProductsPresentEquals(0));
	}
	
	@Test
	public void TC_04_AddProductToCompare() {
		homePage = PageGeneratorManager.getHomePage(driver);
		log.info("WishList - TC04 - Step 01: Access Home page");
		abstractPage.sleepInSecond(1);
		homePage.clickToLogoToBackHome();
		abstractPage.sleepInSecond(1);
		
		log.info("WishList - TC04 - Step 02: Select a product");
		homePage.clickToProductTitle(productName2);
		
		log.info("WishList - TC04 - Step 03: Click to add to compare list button");
		wishListPage = PageGeneratorManager.getWishListPage(driver);
		wishListPage.clickToAddToCompareButton();
		
		log.info("WishList - TC04 - Step 04: Verify success message appears");
		abstractPage.sleepInSecond(1);
		verifyTrue(wishListPage.isSuccessMessageContains("The product has been added to your product comparison"));
		
		log.info("WishList - TC04 - Step 05: Back to Home page");
		homePage = PageGeneratorManager.getHomePage(driver);
		homePage.clickToLogoToBackHome();
		abstractPage.sleepInSecond(1);
		
		log.info("WishList - TC04 - Step 06: Select another product");
		homePage.clickToProductTitle(productName1);

		log.info("WishList - TC04 - Step 07: Click to add to compare list button");
		wishListPage = PageGeneratorManager.getWishListPage(driver);
		wishListPage.clickToAddToCompareButton();
		abstractPage.sleepInSecond(1);
		
		log.info("WishList - TC04 - Step 08: Access product comparison list");
		wishListPage.clickToAccessLinkFromSuccessMsg("product comparison");
		abstractPage.sleepInSecond(1);
		verifyTrue(wishListPage.isCurrentURLContains("compareproducts"));
		
		log.info("WishList - TC04 - Step 09: Verify product info in Comparison list - price");
		verifyTrue(wishListPage.isPriceOfProductEquals(productName1, "$1,800.00"));
		verifyTrue(wishListPage.isPriceOfProductEquals(productName2, "$245.00"));
		
		log.info("WishList - TC04 - Step 10: Click 'CLEAR LIST' button");
		wishListPage.clickToClearListButton();
		
		log.info("WishList - TC04 - Step 11: Verify error message appears");
		verifyTrue(wishListPage.isErrorMessageEquals("You have no items to compare."));
		
		log.info("WishList - TC04 - Step 12: Verify no product in list");
		wishListPage.isProductsPresentEquals(0);
	}
	
	@Test
	public void TC_05_RecentlyViewedProducts() {
		log.info("WishList - TC05 - Step 01: Access Home page");
		homePage = PageGeneratorManager.getHomePage(driver);
		abstractPage.sleepInSecond(1);
		homePage.clickToLogoToBackHome();
		abstractPage.sleepInSecond(1);
		
		log.info("WishList - TC05 - Step 02: View product details 2");
		homePage.clickToProductTitle(productName2);
		
		log.info("WishList - TC05 - Step 03: View product details 2");
		homePage.clickToProductTitle(productName3);
		
		log.info("WishList - TC05 - Step 04: View product details 3");
		homePage.clickToProductTitle(productName4);
		
		log.info("WishList - TC05 - Step 05: View product details 4");
		homePage.clickToProductTitle(productName5);
		
		log.info("WishList - TC05 - Step 06: Access 'Recently viewed products' section");
		homePage.openMultiplePagesFooter("Recently viewed products");
		abstractPage.sleepInSecond(1);
		
		log.info("WishList - TC05 - Step 07: Verify only 3 recent viewed products appears");
		wishListPage = PageGeneratorManager.getWishListPage(driver);
		verifyTrue(wishListPage.isProductsPresentEquals(3));
		searchPage = PageGeneratorManager.getSearchPage(driver);
		verifyTrue(searchPage.isSpecificProductDisplayed(productName5));
		verifyTrue(searchPage.isSpecificProductDisplayed(productName4));
		verifyTrue(searchPage.isSpecificProductDisplayed(productName3));

	}
	
	@AfterTest(alwaysRun = true)
	public void afterClass() {
		closeBrowserAndDriver(driver);
	}
	
}
