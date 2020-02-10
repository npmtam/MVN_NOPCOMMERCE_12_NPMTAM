package pageObjects;

import org.openqa.selenium.WebDriver;

import commons.AbstractPage;
import commons.PageGeneratorManager;
import pageUIs.RegisterPageUI;

public class RegisterPageObject extends AbstractPage{
	WebDriver driver;

	public RegisterPageObject(WebDriver driver) {
		super(driver);
		this.driver = driver;
	}

	public void inputToFirstNameTextBox(String firstName) {
		waitToElementVisible(RegisterPageUI.FIRSTNAME_TEXTBOX);
		sendKeyToElement(RegisterPageUI.FIRSTNAME_TEXTBOX, firstName);
	}

	public void inputToLastNameTextBox(String lastName) {
		waitToElementVisible(RegisterPageUI.LASTNAME_TEXTBOX);
		sendKeyToElement(RegisterPageUI.LASTNAME_TEXTBOX, lastName);
	}

	public void selectMaleGenderCheckBox() {
		waitToElementVisible(RegisterPageUI.MALE_CHECKBOX);
		clickToElement(RegisterPageUI.MALE_CHECKBOX);
	}
	public void selectFemaleGenderCheckBox() {
		waitToElementVisible(RegisterPageUI.FEMALE_CHECKBOX);
		clickToElement(RegisterPageUI.FEMALE_CHECKBOX);
	}

	public void selectDateInDropDown(String valueItem) {
		waitToElementVisible(RegisterPageUI.DAY_DROPDOWN);
		selectItemInDropdown(RegisterPageUI.DAY_DROPDOWN, valueItem);
	}

	public void selectMonthInDropDown(String valueItem) {
		waitToElementVisible(RegisterPageUI.MONTH_DROPDOWN);
		selectItemInDropdown(RegisterPageUI.MONTH_DROPDOWN, valueItem);
	}

	public void selectYearInDropDown(String valueItem) {
		waitToElementVisible(RegisterPageUI.YEAR_DROPDOWN);
		selectItemInDropdown(RegisterPageUI.YEAR_DROPDOWN, valueItem);
	}

	public void inputToEmailTextBox(String emailValue) {
		waitToElementVisible(RegisterPageUI.EMAIL_TEXTBOX);
		sendKeyToElement(RegisterPageUI.EMAIL_TEXTBOX, emailValue);
	}

	public void inputToCompanyTextBox(String companyName) {
		waitToElementVisible(RegisterPageUI.COMPANY_TEXTBOX);
		sendKeyToElement(RegisterPageUI.COMPANY_TEXTBOX, companyName);
	}

	public void inputToPasswordTextBox(String password) {
		waitToElementVisible(RegisterPageUI.PASSWORD_TEXTBOX);
		sendKeyToElement(RegisterPageUI.PASSWORD_TEXTBOX, password);
	}

	public void inputToConfirmPasswordTextBox(String confirmPassword) {
		waitToElementVisible(RegisterPageUI.CONFIRM_PASSWORD_TEXTBOX);
		sendKeyToElement(RegisterPageUI.CONFIRM_PASSWORD_TEXTBOX, confirmPassword);
	}

	public void clickToRegisterButton() {
		waitToElementVisible(RegisterPageUI.REGISTER_BUTTON);
		clickToElement(RegisterPageUI.REGISTER_BUTTON);
	}

	public HomePageObject clickToContinueButton() {
		waitToElementVisible(RegisterPageUI.CONTINUE_BUTTON);
		clickToElement(RegisterPageUI.CONTINUE_BUTTON);
		return PageGeneratorManager.getHomePage(driver);
		
	}
	
	public boolean isResultMatched(String expectedResult) {
		waitToElementVisible(RegisterPageUI.RESULT_LABEL);
		return isElementEquals(RegisterPageUI.RESULT_LABEL, expectedResult);		 
	}
	
	public boolean isErrorMessageDisplayed(String fieldValue) {
		return isElementDisplayed(RegisterPageUI.ERROR_MESSAGE_LABELS(fieldValue));
	}
	
	public boolean isErrorMessageContains(String fieldValue, String expectedText) {
		String errorMsg = getTextElement(RegisterPageUI.ERROR_MESSAGEE_LABELS_CLASS(fieldValue));
		boolean errorMsgEqual = errorMsg.contains(expectedText);
		return errorMsgEqual;
	}
	
	
	public boolean isErrorMessageOnTopDisplayed() {
		return isElementDisplayed(RegisterPageUI.ERROR_MESSAGE_LABEL);
	}
	
}
