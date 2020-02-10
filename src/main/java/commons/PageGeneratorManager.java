package commons;

import pageObjects.*;
import org.openqa.selenium.WebDriver;

//import pageObjects.FooterShoppingCartPO;
//import pageObjects.MyAccountPO;
//import pageObjects.WishListPO;
//import pageObjects.HomePageObject;
//import pageObjects.LoginPageObject;
//import pageObjects.ProductDetailsPO;
//import pageObjects.RegisterPageObject;
//import pageObjects.SearchPO;
//import pageObjects.SortPO;

public class PageGeneratorManager {

	public static HomePageObject getHomePage(WebDriver driver) {
		return new HomePageObject(driver);
	}

	public static RegisterPageObject getRegisterPage(WebDriver driver) {
		return new RegisterPageObject(driver);
	}

	public static LoginPageObject getLoginPage(WebDriver driver) {
		return new LoginPageObject(driver);
	}

	public static MyAccountPO getHeaderMyAccountPage(WebDriver driver) {
		return new MyAccountPO(driver);
	}

	public static FooterShoppingCartPO getFooterShoppingCartPage(WebDriver driver) {
		return new FooterShoppingCartPO(driver);
	}
	
	public static ProductDetailsPO getProductDetailsPage(WebDriver driver) {
		return new ProductDetailsPO(driver);
	}
	
	public static SearchPO getSearchPage(WebDriver driver) {
		return new SearchPO(driver);
	}
	
	public static SortPO getSortPage(WebDriver driver) {
		return new SortPO(driver);
	}
	
	public static WishListPO getWishListPage(WebDriver driver) {
		return new WishListPO(driver);
	}
}
