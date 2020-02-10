package pageObjects;

import org.openqa.selenium.WebDriver;

import commons.AbstractPage;
import commons.PageGeneratorManager;
import pageUIs.AbstractPageUI;
import pageUIs.LoginPageUI;

public class LoginPageObject extends AbstractPage {
//	public AbstractPage abstracPage;
 WebDriver driver;

	public LoginPageObject(WebDriver driver) {
		super(driver);
		this.driver = driver;
	}
	
	public void inputToEmailTextBox(String emailAddress) {
		waitToElementVisible(LoginPageUI.EMAIL_TEXTBOX);
		sendKeyToElement(LoginPageUI.EMAIL_TEXTBOX, emailAddress);
	}

	public void inputToPasswordButton(String password) {
		waitToElementVisible(LoginPageUI.PASSWORD_TEXTBOX);
		sendKeyToElement(LoginPageUI.PASSWORD_TEXTBOX, password);
	}
	
	public LoginPageObject clickToLoginButton() {
		waitToElementVisible(LoginPageUI.LOGIN_BUTTON);
		clickToElement(LoginPageUI.LOGIN_BUTTON);
		return PageGeneratorManager.getLoginPage(driver);
	}
	
	public boolean isEmailErrorMsgEquals(String textValue) {
		waitToElementVisible(LoginPageUI.EMAIL_ERROR_MSG);
		return isElementEquals(LoginPageUI.EMAIL_ERROR_MSG, textValue);
	}
	
	public boolean isErrorMessageContains(String textContains) {
		waitToElementVisible(LoginPageUI.ERROR_MSG);
		return isElementContainsText(LoginPageUI.ERROR_MSG, textContains);
	}
	
	public boolean isHeaderLinksDisplayed(String pageName) {
		waitToElementVisible(AbstractPageUI.HEADER_LINKS, pageName);
		return isElementDisplayed(AbstractPageUI.HEADER_LINKS, pageName);
	}
}
