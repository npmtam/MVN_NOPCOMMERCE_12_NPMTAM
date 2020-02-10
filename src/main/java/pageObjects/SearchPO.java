package pageObjects;

import org.openqa.selenium.WebDriver;

import commons.AbstractPage;
import pageUIs.SearchPageUI;

public class SearchPO extends AbstractPage{
	WebDriver driver;
	
	public SearchPO(WebDriver driver) {
		super(driver);
		this.driver = driver;
	}
	
	public void clickToSearchButton() {
		waitToElementVisible(SearchPageUI.SEARCH_BUTTON);
		clickToElement(SearchPageUI.SEARCH_BUTTON);
	}
	
	public void inputToSearchTextbox(String textValue) {
		waitToElementVisible(SearchPageUI.SEARCH_TEXTBOX);
		sendKeyToElement(SearchPageUI.SEARCH_TEXTBOX, textValue);
	}
	
	public boolean isErrorMessageEquals(String classValue, String expectedValue) {
		String errorMsg = getTextElement(SearchPageUI.ERROR_MESSAGE, classValue);
		return errorMsg.equals(expectedValue);
	}
	
	public boolean isSpecificProductDisplayed(String titleContains) {
		waitToElementVisible(SearchPageUI.PRODUCT_TITLE_IN_RESULT, titleContains);
		return isElementDisplayed(SearchPageUI.PRODUCT_TITLE_IN_RESULT, titleContains);
	}
	
	public int countProductInSearchResult() {
		waitToElementVisible(SearchPageUI.PRODUCT_TITLE_TO_COUNT);
		int numberOfProducts = countElements(SearchPageUI.PRODUCT_TITLE_TO_COUNT);
		return numberOfProducts;
	}
	
	public void checkToAdvanceSearch() {
		waitToElementVisible(SearchPageUI.ADVANCE_SEARCH_CHECBOX);
		checkToCheckBox(SearchPageUI.ADVANCE_SEARCH_CHECBOX);
	}
	
	public void selectDropdown(String textValue, String itemSelect) {
		waitToElementVisible(SearchPageUI.DYNAMIC_DROPDOWNLIST, textValue);
		selectItemInDropdown(SearchPageUI.DYNAMIC_DROPDOWNLIST, itemSelect, textValue);
	}
	
	public void uncheckAutoSearchSubCategories() {
		waitToElementVisible(SearchPageUI.SEARCH_SUB_CATEGORIES_CHECKBOX);
		unCheckToCheckBox(SearchPageUI.SEARCH_SUB_CATEGORIES_CHECKBOX);
	}
	
	public void checkToAutoSearchSubCategories() {
		waitToElementVisible(SearchPageUI.SEARCH_SUB_CATEGORIES_CHECKBOX);
		checkToCheckBox(SearchPageUI.SEARCH_SUB_CATEGORIES_CHECKBOX);
	}
	
	public void inputToPriceRangeTextboxes(String className, String priceValue) {
		waitToElementVisible(SearchPageUI.PRICE_RANGE_TEXTBOXES, className);
		sendKeyToElement(SearchPageUI.PRICE_RANGE_TEXTBOXES, priceValue, className);
	}

}
