package pageUIs;

public class ProductDetailsUI {
	public static final String ADD_YOUR_REVIEW_LINK = "//a[text()='Add your review']";
	public static final String PRODUCT_REVIEW_TITLE = "//h1[contains(text(), 'Product review')]";
	
	public static final String REVIEW_TITLE = "//input[@class='review-title']";
	public static final String REVIEW_TEXTAREA = "//textarea[@class='review-text']";
	public static final String SUBMIT_REVIEW_BUTTON = "//input[@value='Submit review']";
	public static final String RESULT_LABEL = "//div[@class='result']";
	
	public static final String ADD_TO_WISHLIST_BUTTON = "//input[@id='add-to-wishlist-button-4']";
	public static final String SUCCESS_MESSAGE = "//div[@class='bar-notification success']/p[@class='content']";
	public static final String LINK_IN_SUCCESS_MSG = "//a[text()='%s']";
	public static final String WISHLIST_URL_SHARING = "//a[@class='share-link']";
	public static final String ADD_TO_CARD_CHECKBOX = "//input[@name='addtocart']";
	public static final String REMOVE_CHECKBOX = "//input[@name='removefromcart']";
	public static final String ADD_TO_CARD_BUTTON = "//input[@name='addtocartbutton']";
	public static final String UPDATE_WISHLIST_BUTTON = "//input[@name='updatecart']";
	public static final String EMPTY_WISHLIST_MSG = "//div[@class='page-body']/div[@class='no-data']";
	
	public static final String PRODUCT_TITLE_WISHLIST = "//a[@class='product-name']";
	
	public static final String ADD_TO_COMPARE_BUTTON = "//input[@value='Add to compare list']";
	public static final String COMPARE_LINK_IN_SUCCESS_MSG = "//a[text()='product comparison']";
	public static final String PRICE_IN_COMPARE_LIST = "//tr[@class='product-price']/td[%s]";
	public static final String CLEAR_LIST_BUTTON = "//a[@class='clear-list']";
}
