package pageUIs;

public class OrderPageUI {
    public static final String ADD_TO_CART_BUTTON_IN_CATEGORIES = "//a[contains(text(), '%s')]/ancestor::div[@class='product-item']//input[@value='Add to cart']";
    public static final String QUANTITY_TEXTBOX = "//input[@class='qty-input']";

    public static final String TOTAL_PRICE_LABEL = "//span[@class='product-subtotal']";

    //Cart Page checkout
    public static final String GIFT_WRAPPING_DROPDOWN = "//select[@id='checkout_attribute_1']";
    public static final String ESTIMATE_SHIPPING_DROPDOWN_LISTS = "//select[@class='%s-input']";
    public static final String ZIP_CODE_TEXTBOX = "//input[@class='zip-input']";
    public static final String AGREE_WITH_TERMOFUSE_CHECKBOX = "//input[@id='termsofservice']";
    public static final String TOTAL_PRICE_CHECKOUT_LABEL = "//span[@class='value-summary']/strong";
    public static final String CHECKOUT_BUTTON = "//button[@id='checkout']";

    //Checkout screen
    public static final String COMPANY_TEXTBOX = "//input[@id='BillingNewAddress_Company']";
    public static final String COUNTRY_DROPDOWNLIST = "//select[@id='BillingNewAddress_CountryId']";
    public static final String CHECKOUT_TEXTBOXES = "//input[@name='BillingNewAddress.%s']";
    public static final String CONTINUE_BUTTON = "//input[@value='Continue']";
}
