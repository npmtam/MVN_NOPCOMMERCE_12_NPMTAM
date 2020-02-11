package pageObjects;

import org.openqa.selenium.WebDriver;

import commons.AbstractPage;
import commons.PageGeneratorManager;
import pageUIs.HomePageUI;

public class HomePageObject extends AbstractPage {
	WebDriver driver;

	public HomePageObject(WebDriver driver) {
		super(driver);
		this.driver = driver;
	}

	public RegisterPageObject clickToRegisterLink() {
		waitToElementVisible(HomePageUI.HEADER_LINKS, "Register");
		clickToElement(HomePageUI.HEADER_LINKS, "Register");
		return PageGeneratorManager.getRegisterPage(driver);
	}

	public HomePageObject clickToSignOutButton() {
		waitToElementVisible(HomePageUI.HEADER_LINKS, "Log out");
		clickToElement(HomePageUI.HEADER_LINKS, "Log out");
		return PageGeneratorManager.getHomePage(driver);
	}

	public LoginPageObject clickToLoginLink() {
		waitToElementVisible(HomePageUI.HEADER_LINKS, "Log in");
		clickToElement(HomePageUI.HEADER_LINKS, "Log in");
		return PageGeneratorManager.getLoginPage(driver);
	}
	
	public MyAccountPO clickToHeaderMyAccountPage() {
		waitToElementVisible(HomePageUI.HEADER_LINKS, "My account");
		clickToElement(HomePageUI.HEADER_LINKS, "My account");
		return PageGeneratorManager.getHeaderMyAccountPage(driver);
	}

	public boolean isMyAccountLinkDisplayed() {
		return isElementDisplayed(HomePageUI.HEADER_LINKS, "My account");
	}

	public boolean isLoginLinkDisplayed() {
		return isElementDisplayed(HomePageUI.HEADER_LINKS, "Log in");
	}

	public MyAccountPO clickToMyAccountHeader() {
		waitToElementVisible(HomePageUI.HEADER_LINKS, "My account");
		clickToElement(HomePageUI.HEADER_LINKS, "My account");
		return PageGeneratorManager.getHeaderMyAccountPage(driver);
	}
	
	public void clickToProductTitle(String productTitle) {
		waitToElementVisible(HomePageUI.PRODUCT_TITLE, productTitle);
		clickToElement(HomePageUI.PRODUCT_TITLE, productTitle);
	}
	
	public void clickToHeaderWishList() {
		waitToElementVisible(HomePageUI.HEADER_WISHLIST_LINK);
		clickToElement(HomePageUI.HEADER_WISHLIST_LINK);
	}
	
	public void clickToLogoToBackHome() {
		waitToElementClickable(HomePageUI.LOGO_LINK);
		clickToElement(HomePageUI.LOGO_LINK);
	}

	public void clickToHeaderShoppingCart(){
		waitToElementVisible(HomePageUI.HEADER_SHOPPING_CART_LINK);
		clickToElement(HomePageUI.HEADER_SHOPPING_CART_LINK);
	}

}
