package com.nopcommerce.frontend;

import java.util.concurrent.TimeUnit;

import org.testng.annotations.AfterTest;
import org.testng.annotations.Parameters;
import pageObjects.HomePageObject;
import pageObjects.LoginPageObject;
import pageObjects.SearchPO;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import commons.AbstractPage;
import commons.AbstractTest;
import commons.PageGeneratorManager;

public class FE_04_Search extends AbstractTest {
	WebDriver driver;
	private HomePageObject homePage;
	private AbstractPage abstractPage;
	private LoginPageObject loginPage;
	private SearchPO searchPage;
	
	String email, password;

	@Parameters("browser")
	@BeforeClass
	public void beforeClass(String browserName) {
		driver = getBrowserDriver(browserName);
		abstractPage = new AbstractPage(driver);

		email = "tam@gmail.com";
		password = "123123";
		
		log.info("Search - Pre-condition01: Login to account");
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
		
		log.info("Search - Pre-condition02: Access search page at Footer");
		abstractPage.openMultiplePagesFooter("Search");
		searchPage = PageGeneratorManager.getSearchPage(driver);
		log.info("Search - Pre-condition03: Verify access search page successfully");
		verifyTrue(abstractPage.isCurrentURLContains("search"));
	}

	@Test
	public void TC_01_SearchEmptyData() {
		abstractPage.sleepInSecond(1);
		log.info("Search - TC01 - Step 01: Click to search button");
		searchPage.clickToSearchButton();
		log.info("Search - TC01 - Step 02: Verify error message");
		verifyTrue(searchPage.isErrorMessageEquals("warning", "Search term minimum length is 3 characters"));
	}
	
	@Test
	public void TC_02_SearchWithDataNotExist() {
		abstractPage.sleepInSecond(1);
		log.info("Search - TC02 - Step 01: Search with data not exist");
		searchPage.inputToSearchTextbox("Macbook Pro 2050");
		
		log.info("Search - TC02 - Step 02: Click search button");
		searchPage.clickToSearchButton();
		
		log.info("Search - TC02 - Step 03: Verify error message");
		verifyTrue(searchPage.isErrorMessageEquals("no-result", "No products were found that matched your criteria."));
	}
	
	@Test
	public void TC_03_SearchProductName() {
		abstractPage.sleepInSecond(1);
		log.info("Search - TC03 - Step 01: Search product name");
		searchPage.inputToSearchTextbox("Lenovo");
		
		log.info("Search - TC03 - Step 02: Click search button");
		searchPage.clickToSearchButton();
		
		log.info("Search - TC03 - Verify 2 products displayed");
		verifyEquals(searchPage.countProductInSearchResult(), 2);
		verifyTrue(searchPage.isSearchResultDisplayedProduct("IdeaCentre 600 All-in-One PC"));
		verifyTrue(searchPage.isSearchResultDisplayedProduct("Thinkpad X1 Carbon"));
	}
	
	@Test
	public void TC_04_SearchProductNameSpecific() {
		abstractPage.sleepInSecond(1);
		log.info("Search - TC 04 - Step 01: Search with specific product name");
		searchPage.inputToSearchTextbox("Thinkpad X1 Carbon");
		
		log.info("Search - TC04 - Step 02: Click search button");
		searchPage.clickToSearchButton();
		
		log.info("Search - TC04 - Step 03: Verify 1 product displayed");
		verifyEquals(searchPage.countProductInSearchResult(), 1);
		verifyTrue(searchPage.isSearchResultDisplayedProduct("Thinkpad X1 Carbon"));
	}
	
	@Test
	public void TC_05_AdvanceSearchParentCategories() {
		abstractPage.sleepInSecond(1);
		log.info("Search - TC05 - Step 01: Search parent categories");
		searchPage.inputToSearchTextbox("Apple Macbook Pro");
		
		log.info("Search - TC05 - Step 02: Check advance search option");
		searchPage.checkToAdvanceSearch();
		
		log.info("Search - TC05 - Step 03: Select category");
		searchPage.selectDropdown("Category", "Computers");
		
		log.info("Search - TC05 - Step 04: Uncheck checkbox auto search sub categories");
		searchPage.uncheckAutoSearchSubCategories();
		
		log.info("Search - TC05 - Step 05: Click to search button");
		searchPage.clickToSearchButton();
		
		log.info("Search - TC05 - Step 06: Verify error message");
		verifyTrue(searchPage.isErrorMessageEquals("no-result", "No products were found that matched your criteria."));
	}
	
	@Test
	public void TC_06_AdvanceSearchSubCategories() {
		abstractPage.sleepInSecond(1);
		log.info("Search - TC06 - Step 01: Search keyword");
		searchPage.inputToSearchTextbox("Apple Macbook Pro");
		
		log.info("Search - TC06 - Step 02: Check advance search option");
		searchPage.checkToAdvanceSearch();
		
		log.info("Search - TC06 - Step 03: Select category");
		searchPage.selectDropdown("Category", "Computers");
		
		log.info("Search - TC06 - Step 04: Check auto search sub categories checkbox");
		searchPage.checkToAutoSearchSubCategories();
		
		log.info("Search - TC06 - Step 05: Click save button");
		searchPage.clickToSearchButton();
		
		log.info("Search - TC06 - Step 05: Verify the result");
		verifyEquals(searchPage.countProductInSearchResult(), 1);
		verifyTrue(searchPage.isSearchResultDisplayedProduct("Apple MacBook Pro 13-inch"));
	}
	
	@Test
	public void TC_07_AdvanceSearchIncorrectManufacturer() {
		abstractPage.sleepInSecond(1);
		log.info("Search - TC07 - Step 01: Search keyword");
		searchPage.inputToSearchTextbox("Apple Macbook Pro");
		
		log.info("Search - TC07 - Step 02: Check advance search option");
		searchPage.checkToAdvanceSearch();
		
		log.info("Search - TC07 - Step 03: Select category");
		searchPage.selectDropdown("Category", "Computers");
		
		log.info("Search - TC07 - Step 04: Check auto search sub categories checkbox");
		searchPage.checkToAutoSearchSubCategories();
		
		log.info("Search - TC07 - Step 05: Select Manufacturer");
		searchPage.selectDropdown("Manufacturer", "HP");
		
		log.info("Search - TC07 - Step 06: Click search button");
		searchPage.clickToSearchButton();
		
		log.info("Search - TC07 - Step 07: Verify error message");
		verifyTrue(searchPage.isErrorMessageEquals("no-result", "No products were found that matched your criteria."));
	}
	
	@Test
	public void TC_08_AdvanceSearchCorrectManufacturer() {
		abstractPage.sleepInSecond(1);
		log.info("Search - TC08 - Step 01: Search keyword");
		searchPage.inputToSearchTextbox("Apple Macbook Pro");
		
		log.info("Search - TC08 - Step 02: Check advance search option");
		searchPage.checkToAdvanceSearch();
		
		log.info("Search - TC08 - Step 03: Select category");
		searchPage.selectDropdown("Category", "Computers");
		
		log.info("Search - TC08 - Step 04: Check auto search sub categories checkbox");
		searchPage.checkToAutoSearchSubCategories();
		
		log.info("Search - TC08 - Step 05: Select Manufacturer");
		searchPage.selectDropdown("Manufacturer", "Apple");
		
		log.info("Search - TC08 - Step 06: Click search button");
		searchPage.clickToSearchButton();
		
		log.info("Search - TC08 - Step 07: Verify search result");
		abstractPage.sleepInSecond(1);
		verifyEquals(searchPage.countProductInSearchResult(), 1);
		verifyTrue(searchPage.isSearchResultDisplayedProduct("Apple MacBook Pro 13-inch"));
	}
	
	@Test
	public void TC_09_AdvanceSearchDuringPriceRange() {
		abstractPage.sleepInSecond(1);
		log.info("Search - TC09 - Step 01: Search keyword");
		searchPage.inputToSearchTextbox("Apple Macbook Pro");
		
		log.info("Search - TC09 - Step 02: Check advance search option");
		searchPage.checkToAdvanceSearch();
		
		log.info("Search - TC09 - Step 03: Select category");
		searchPage.selectDropdown("Category", "Computers");
		
		log.info("Search - TC09 - Step 04: Check auto search sub categories checkbox");
		searchPage.checkToAutoSearchSubCategories();
		
		log.info("Search - TC09 - Step 05: Select Manufacturer");
		searchPage.selectDropdown("Manufacturer", "Apple");
		
		log.info("Search - TC09 - Step 06: Input price range");
		searchPage.inputToPriceRangeTextboxes("price-from", "1000");
		searchPage.inputToPriceRangeTextboxes("price-to", "2000");
		
		log.info("Search - TC09 - Step 07: Click search button");
		searchPage.clickToSearchButton();
		
		log.info("Search - TC09 - Step 08: Verify search result");
		verifyEquals(searchPage.countProductInSearchResult(), 1);
		verifyTrue(searchPage.isSearchResultDisplayedProduct("Apple MacBook Pro 13-inch"));
	}
	
	@Test
	public void TC_10_AdvanceSearchPriceRangeLowerProductPrice() {
		abstractPage.sleepInSecond(1);
		log.info("Search - TC10 - Step 01: Search keyword");
		searchPage.inputToSearchTextbox("Apple Macbook Pro");
		
		log.info("Search - TC10 - Step 02: Check advance search option");
		searchPage.checkToAdvanceSearch();
		
		log.info("Search - TC10 - Step 03: Select category");
		searchPage.selectDropdown("Category", "Computers");
		
		log.info("Search - TC10 - Step 04: Check auto search sub categories checkbox");
		searchPage.checkToAutoSearchSubCategories();
		
		log.info("Search - TC10 - Step 05: Select Manufacturer");
		searchPage.selectDropdown("Manufacturer", "Apple");
		
		log.info("Search - TC10 - Step 06: Input price range");
		searchPage.inputToPriceRangeTextboxes("price-from", "1000");
		searchPage.inputToPriceRangeTextboxes("price-to", "1700");
		
		log.info("Search - TC10 - Step 07: Click search button");
		searchPage.clickToSearchButton();
		
		log.info("Search - TC10 - Step 08: Verify error message");
		verifyTrue(searchPage.isErrorMessageEquals("no-result", "No products were found that matched your criteria."));
	}
	
	@Test
	public void TC_11_AdvanceSearchPriceRangeLargerProductPrice() {
		abstractPage.sleepInSecond(1);
		log.info("Search - TC11 - Step 01: Search keyword");
		searchPage.inputToSearchTextbox("Apple Macbook Pro");
		
		log.info("Search - TC11 - Step 02: Check advance search option");
		searchPage.checkToAdvanceSearch();
		
		log.info("Search - TC11 - Step 03: Select category");
		searchPage.selectDropdown("Category", "Computers");
		
		log.info("Search - TC11 - Step 04: Check auto search sub categories checkbox");
		searchPage.checkToAutoSearchSubCategories();
		
		log.info("Search - TC11 - Step 05: Select Manufacturer");
		searchPage.selectDropdown("Manufacturer", "Apple");
		
		log.info("Search - TC11 - Step 06: Input price range");
		searchPage.inputToPriceRangeTextboxes("price-from", "1900");
		searchPage.inputToPriceRangeTextboxes("price-to", "5000");
		
		log.info("Search - TC11 - Step 07: Click search button");
		searchPage.clickToSearchButton();
		
		log.info("Search - TC11 - Step 08: Verify error message");
		verifyTrue(searchPage.isErrorMessageEquals("no-result", "No products were found that matched your criteria."));
	}

	@AfterTest(alwaysRun = true)
	public void afterClass() {
		closeBrowserAndDriver(driver);
	}
}
