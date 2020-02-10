package pageUIs;

public class SearchPageUI {
	public static final String SEARCH_TEXTBOX = "//input[@class='search-text']";
	public static final String SEARCH_BUTTON = "//input[contains(@class, 'search-button')]";
	public static final String ERROR_MESSAGE = "//div[@class='search-results']//div[@class='%s']";
	public static final String PRODUCT_TITLE_TO_COUNT = "//h2[@class='product-title']";
	public static final String PRODUCT_TITLE_IN_RESULT = "//h2[@class='product-title']/a[contains(text(), '%s')]";
	public static final String ADVANCE_SEARCH_CHECBOX = "//input[@id='adv']";
	public static final String DYNAMIC_DROPDOWNLIST = "//label[contains(text(), '%s')]/following-sibling::select";
	public static final String SEARCH_SUB_CATEGORIES_CHECKBOX = "//input[@id='isc']";
	public static final String PRICE_RANGE_TEXTBOXES = "//input[@class='%s']";
	
}
