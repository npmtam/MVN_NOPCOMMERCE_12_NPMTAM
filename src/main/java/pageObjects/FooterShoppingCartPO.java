package pageObjects;

import org.openqa.selenium.WebDriver;

import commons.AbstractPage;

public class FooterShoppingCartPO extends AbstractPage {
WebDriver driver;

	public FooterShoppingCartPO(WebDriver driver) {
		super(driver);
		this.driver = driver;
	}

}
