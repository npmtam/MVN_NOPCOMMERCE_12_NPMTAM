package pageObjects;

import org.openqa.selenium.WebDriver;

import commons.AbstractPage;
import pageUIs.MyAccountPageUI;

public class MyAccountPO extends AbstractPage {
	WebDriver driver;

	public MyAccountPO(WebDriver driver) {
		super(driver);
		this.driver = driver;
	}

	public boolean isCurrentURLContains(String expectedvalue) {
		String currentURL = getCurrentPageURL();
		return currentURL.contains(expectedvalue);
	}

	public void clickToGenderRadioButton(String gender) {
		clickToElement(MyAccountPageUI.GENDER_RADIOBUTTON, gender);
	}

	public void inputToCustomerInfoTextboxs(String fieldID, String textValue) {
		waitToElementVisible(MyAccountPageUI.CUSTOMER_TEXTBOXS, fieldID);
		sendKeyToElement(MyAccountPageUI.CUSTOMER_TEXTBOXS, textValue, fieldID);
	}

	public void selectBirdtDateDropDownList(String valueField, String valueItem) {
		waitToElementVisible(MyAccountPageUI.BIRTHDATE_DROPDOWNLIST, valueField);
		selectItemInDropdown(MyAccountPageUI.BIRTHDATE_DROPDOWNLIST, valueItem, valueField);
	}

	public void clickToSaveButton() {
		waitToElementVisible(MyAccountPageUI.SAVE_BUTTON);
		clickToElement(MyAccountPageUI.SAVE_BUTTON);
	}

	public void clickToAddNewButton() {
		waitToElementVisible(MyAccountPageUI.ADD_NEW_BUTTON);
		clickToElement(MyAccountPageUI.ADD_NEW_BUTTON);
	}

	public void inputToAddressTextboxes(String textboxName, String textValue) {
		waitToElementVisible(MyAccountPageUI.ADDRESS_TEXTBOXES, textboxName);
		sendKeyToElement(MyAccountPageUI.ADDRESS_TEXTBOXES, textValue, textboxName);
	}

	public void selectCountryDropdownList(String countryValue) {
		waitToElementVisible(MyAccountPageUI.COUNTRY_DROPDOWN_LIST);
		selectItemInDropdown(MyAccountPageUI.COUNTRY_DROPDOWN_LIST, countryValue);
	}

	public boolean isErrorMessagePresentInDOM() {
		return isElementPresentInDOM(MyAccountPageUI.ERROR_MESSAGE);
	}

	public boolean isNewAddressAddedEquals(String infoValue, String expectedResult) {
		waitToElementVisible(MyAccountPageUI.NEW_ADDRESS_INFOS, infoValue);
		String actualText = getTextElement(MyAccountPageUI.NEW_ADDRESS_INFOS, infoValue);
		String[] expectedText = actualText.split("\\s");
		String emailAddress = expectedText[1];
		return emailAddress.equals(expectedResult);
	}

	public boolean isNewAddressCityAndZipEquals(String infoValue, String expectedResult) {
		waitToElementVisible(MyAccountPageUI.NEW_ADDRESS_INFOS, infoValue);
		String actualText = getTextElement(MyAccountPageUI.NEW_ADDRESS_INFOS, infoValue);
		return actualText.equals(expectedResult);
	}

	public void clickToMyAccountLinks(String fieldValue) {
		waitToElementVisible(MyAccountPageUI.MENU_LINKS, fieldValue);
		clickToElement(MyAccountPageUI.MENU_LINKS, fieldValue);
	}

	public void inputToChangePasswordTextboxes(String fieldValue, String textValue) {
		waitToElementVisible(MyAccountPageUI.CHANGE_PASSWORD_TEXTBOXES, fieldValue);
		sendKeyToElement(MyAccountPageUI.CHANGE_PASSWORD_TEXTBOXES, textValue, fieldValue);
	}

	public void clickToChangePasswordButton() {
		waitToElementVisible(MyAccountPageUI.CHANGE_PASSWORD_BUTTON);
		clickToElement(MyAccountPageUI.CHANGE_PASSWORD_BUTTON);
	}

	public boolean isChangePasswordResultEquals(String expectedResult) {
		waitToElementVisible(MyAccountPageUI.CHANGE_PASSWORD_RESULT_LABEL);
		String actualText = getTextElement(MyAccountPageUI.CHANGE_PASSWORD_RESULT_LABEL);
		return actualText.equals(expectedResult);
	}

	public boolean isPageTitleContains(String expectedText) {
		waitToElementVisible(MyAccountPageUI.MY_ACCOUNT_PAGE_TITLE);
		String actualText = getTextElement(MyAccountPageUI.MY_ACCOUNT_PAGE_TITLE);
		return actualText.contains(expectedText);
	}

}
