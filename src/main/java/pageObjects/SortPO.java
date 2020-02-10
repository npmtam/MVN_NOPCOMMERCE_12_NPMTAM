package pageObjects;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import commons.AbstractPage;
import pageUIs.SortPageUI;

public class SortPO extends AbstractPage {
	WebDriver driver;
	List<WebElement> elements;

	public SortPO(WebDriver driver) {
		super(driver);
		this.driver = driver;
	}

	public void selectSortByDropdown(String itemSelect) {
		selectItemInDropdown(SortPageUI.SORTBY_DROPDOWNLIST, itemSelect);
	}

	public boolean checkProductSortNameAToZ() {
		return checkElementsSorted(SortPageUI.LIST_TITLE_PRODUCTS);
	}
	
	public boolean checkProductSortNameZToA() {
		return checkElementsSortedReverse(SortPageUI.LIST_TITLE_PRODUCTS);
	}
	
	public boolean checkProductSortPriceLowToHigh() {
		return checkElementsSorted(SortPageUI.LIST_PRODUCTS_PRICE);
	}
	
	public boolean checkProductSortPriceHighToLow() {
		return checkElementsSortedReverse(SortPageUI.LIST_PRODUCTS_PRICE);
	}
	
	public void selectDisplayProducts(String numProducts) {
		selectItemInDropdown(SortPageUI.DISPLAY_DROPDOWNLIST, numProducts);
	}
	
	public boolean checkNumberProductsDisplay(int expectedNumber) {
		 int actualNumber = countElements(SortPageUI.LIST_PRODUCTS_TITLE);
		 return actualNumber<=expectedNumber;
	}

}

