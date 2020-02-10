package pageObjects;

import org.openqa.selenium.WebDriver;

import commons.AbstractPage;
import pageUIs.ProductDetailsUI;

public class ProductDetailsPO extends AbstractPage{
	WebDriver driver;

	public ProductDetailsPO(WebDriver driver) {
		super(driver);
		this.driver = driver;
	}
	
	public void clickToAddReviewLink() {
		waitToElementVisible(ProductDetailsUI.ADD_YOUR_REVIEW_LINK);
		clickToElement(ProductDetailsUI.ADD_YOUR_REVIEW_LINK);
	}
	
	public boolean isProductReviewTitleDisplayed() {
		waitToElementVisible(ProductDetailsUI.PRODUCT_REVIEW_TITLE);
		return isElementDisplayed(ProductDetailsUI.PRODUCT_REVIEW_TITLE);
	}
	
	public void inputToReviewTitleTextbox(String textValue) {
		waitToElementVisible(ProductDetailsUI.REVIEW_TITLE);
		sendKeyToElement(ProductDetailsUI.REVIEW_TITLE, textValue);
	}
	
	public void inputToReviewTextTextArea(String textValue) {
		waitToElementVisible(ProductDetailsUI.REVIEW_TEXTAREA);
		sendKeyToElement(ProductDetailsUI.REVIEW_TEXTAREA, textValue);
	}
	
	public void clickToSubmitReviewButton() {
		waitToElementVisible(ProductDetailsUI.SUBMIT_REVIEW_BUTTON);
		clickToElement(ProductDetailsUI.SUBMIT_REVIEW_BUTTON);
	}
	
	public boolean isAddReviewResultContains(String expectedResult) {
		waitToElementVisible(ProductDetailsUI.RESULT_LABEL);
		String actualText = getTextElement(ProductDetailsUI.RESULT_LABEL);
		return actualText.contains(expectedResult);
	}
}
