package com.nopcommerce.frontend;

import static org.junit.Assert.assertTrue;

import java.util.concurrent.TimeUnit;

import org.testng.annotations.AfterTest;
import org.testng.annotations.Parameters;
import pageObjects.SortPO;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import commons.AbstractPage;
import commons.AbstractTest;
import commons.PageGeneratorManager;
/*import pageObjects.HomePageObject;
import pageObjects.LoginPageObject;
import pageObjects.SearchPO;
import pageObjects.SortPO;*/

public class FE_05_Sort_Display extends AbstractTest {
	WebDriver driver;
	private AbstractPage abstractPage;
	private SortPO sortPage;

	@Parameters("browser")
	@BeforeTest
	public void beforeTest(String browserName) {
		driver = getBrowserDriver(browserName);
		abstractPage = new AbstractPage(driver);

		log.info("Precondition - Select Notebooks from Computers menu");
		abstractPage.openDynamicSubMenu("Computers", "Notebooks ");
	}

	@Test
	public void TC_01_SortByNameAToZ() {
		log.info("Sort - TC01 - Step 01: Select sort by: Name: A to Z");
		sortPage = PageGeneratorManager.getSortPage(driver);
		sortPage.selectSortByDropdown("Name: A to Z");

		log.info("Sort - TC01 - Step 02: Verify the products are ordered by name A to Z");
		assertTrue(sortPage.checkProductSortNameAToZ());
	}

	@Test
	public void TC_02_SortByNameZToA() {
		log.info("Sort - TC02 - Step 01: Select sort by: Name: Z to A");
		sortPage.selectSortByDropdown("Name: Z to A");

		log.info("Sort - TC02 - Step 02: Verify the products are ordered by name Z to A");
		assertTrue(sortPage.checkProductSortNameZToA());
	}

	@Test
	public void TC_03_SortByPriceLowToHigh() {
		log.info("Sort - TC03 - Step 01: Select sort by: Price: Low to High");
		sortPage.selectSortByDropdown("Price: Low to High");

		log.info("Sort - TC03 - Step 02: Verify the products are ordered by Price Low to High");
		assertTrue(sortPage.checkProductSortPriceLowToHigh());
	}

	@Test
	public void TC_04_SortByPriceHighToLow() {
		log.info("Sort - TC04 - Step 01: Select sort by: Price: High To Low");
		sortPage.selectSortByDropdown("Price: High to Low");

		log.info("Sort - TC04 - Step 02: Verify the products are ordered by Price High to Low");
		assertTrue(sortPage.checkProductSortPriceHighToLow());
	}
	
	@Test
	public void TC_05_Display3ProductsPerPage() {
		log.info("Sort - TC05 - Step 01: Select display per page dropdown list");
		sortPage.selectDisplayProducts("3");
		
		log.info("Sort - TC05 - Step 02: Verify 3 products displayed on page");
		sortPage.checkNumberProductsDisplay(3);
	}
	
	@Test
	public void TC_06_Display6ProductsPerPage() {
		log.info("Sort - TC06 - Step 01: Select display per page dropdown list");
		sortPage.selectDisplayProducts("6");
		
		log.info("Sort - TC06 - Step 02: Verify 6 products displayed on page");
		sortPage.checkNumberProductsDisplay(6);
	}
	
	@Test
	public void TC_07_Display9ProductsPerPage() {
		log.info("Sort - TC07 - Step 01: Select display per page dropdown list");
		sortPage.selectDisplayProducts("9");
		
		log.info("Sort - TC07 - Step 02: Verify 9 products displayed on page");
		sortPage.checkNumberProductsDisplay(9);
	}

	@AfterTest(alwaysRun = true)
	public void afterClass() {
		closeBrowserAndDriver(driver);
	}
}
