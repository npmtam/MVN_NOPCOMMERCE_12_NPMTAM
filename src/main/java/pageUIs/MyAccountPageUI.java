package pageUIs;

public class MyAccountPageUI {
	public static final String MENU_LINKS = "//div[@class='listbox']//a[contains(@href, '%s')]";
	public static final String MY_ACCOUNT_PAGE_TITLE = "//div[@class='page-title']/h1";
	
	public static final String GENDER_RADIOBUTTON = "//input[@id='gender-%s']";
	public static final String CUSTOMER_TEXTBOXS = "//input[@id='%s']";
	public static final String BIRTHDATE_DROPDOWNLIST = "//select[@name='DateOfBirth%s']";
	public static final String SAVE_BUTTON = "//input[@value='Save']";
	public static final String ERROR_MESSAGE = "//div[contains(@class, 'summary-errors')]";
	
	public static final String ADD_NEW_BUTTON = "//input[@value='Add new']";
	public static final String ADDRESS_TEXTBOXES = "//input[@name='Address.%s']";
	public static final String COUNTRY_DROPDOWN_LIST = "//select[@name='Address.CountryId']";
	
	public static final String NEW_ADDRESS_INFOS = "//li[@class='%s']";
	public static final String CHANGE_PASSWORD_TEXTBOXES = "//input[@id='%s']";
	public static final String CHANGE_PASSWORD_BUTTON = "//input[@value='Change password']";
	public static final String CHANGE_PASSWORD_RESULT_LABEL = "//div[@class='result']";
}
