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

    public void selectGiftWrappingDropdown(int index){
        waitToElementVisible(OrderPageUI.GIFT_WRAPPING_DROPDOWN);
        selectItemInDropdownByIndex(OrderPageUI.GIFT_WRAPPING_DROPDOWN, index);
    }

    public void selectEstimateShippingDropdownLists(String dropdownName, String valueItem){
        waitToElementVisible(OrderPageUI.ESTIMATE_SHIPPING_DROPDOWN_LISTS, dropdownName);
        selectItemInDropdown(OrderPageUI.ESTIMATE_SHIPPING_DROPDOWN_LISTS, valueItem, dropdownName);
    }

    public void inputToZipCodeTextbox(String textValue){
        waitToElementVisible(OrderPageUI.ZIP_CODE_TEXTBOX);
        sendKeyToElement(OrderPageUI.ZIP_CODE_TEXTBOX, textValue);
    }

    public void clickToAgreeTermOfUse(){
        waitToElementClickable(OrderPageUI.AGREE_WITH_TERMOFUSE_CHECKBOX);
        clickToElement(OrderPageUI.AGREE_WITH_TERMOFUSE_CHECKBOX);
    }

    public String getTotalPrice(){
        waitToElementVisible(OrderPageUI.TOTAL_PRICE_CHECKOUT_LABEL);
        return getTextElement(OrderPageUI.TOTAL_PRICE_CHECKOUT_LABEL);
    }

    public void clickToCheckOut(){
        waitToElementClickable(OrderPageUI.CHECKOUT_BUTTON);
        clickToElement(OrderPageUI.CHECKOUT_BUTTON);
    }

    public void inputToTextboxesInCheckout(String fieldValue, String textValue){
        waitToElementVisible(OrderPageUI.CHECKOUT_TEXTBOXES, fieldValue);
        sendKeyToElement(OrderPageUI.CHECKOUT_TEXTBOXES, textValue, fieldValue);
    }

    public void selectCountryDropdownList(String countryValue){
        waitToElementClickable(OrderPageUI.COUNTRY_DROPDOWNLIST);
        selectItemInDropdown(OrderPageUI.COUNTRY_DROPDOWNLIST, countryValue);
    }

    public void clickToContinueButton(){
        waitToElementClickable(OrderPageUI.CONTINUE_BUTTON);
        clickToElement(OrderPageUI.CONTINUE_BUTTON);
    }

}
