package pageObjects;

import com.sun.org.apache.xpath.internal.operations.Or;
import commons.AbstractPage;
import org.openqa.selenium.WebDriver;
import pageUIs.OrderPageUI;
import pageUIs.ProductDetailsUI;

public class OrderPO extends AbstractPage {
    WebDriver driver;
    public OrderPO(WebDriver driver) {
        super(driver);
        this.driver = driver;

    }

    public void clickToAddToCartButton(){
        waitToElementVisible(ProductDetailsUI.ADD_TO_CART_BUTTON_PRODUCT_DETAILS);
        clickToElement(ProductDetailsUI.ADD_TO_CART_BUTTON_PRODUCT_DETAILS);
    }

    public void clickToAddToCartButtonFromCategories(String productName){
        waitToElementVisible(OrderPageUI.ADD_TO_CART_BUTTON_IN_CATEGORIES, productName);
        clickToElement(OrderPageUI.ADD_TO_CART_BUTTON_IN_CATEGORIES, productName);
    }

    public void inputToQuantityTextbox(String value){
        waitToElementVisible(OrderPageUI.QUANTITY_TEXTBOX);
        sendKeyToElement(OrderPageUI.QUANTITY_TEXTBOX, value);
    }

    public boolean isTotalPriceEquals(String expectedPrice){
        waitToElementVisible(OrderPageUI.TOTAL_PRICE_LABEL);
        String actualPrice = getTextElement(OrderPageUI.TOTAL_PRICE_LABEL);
        return actualPrice.equals(expectedPrice);
    }
}
