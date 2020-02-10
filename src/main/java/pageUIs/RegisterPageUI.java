package pageUIs;

public class RegisterPageUI {
	public static final String ERROR_MESSAGE_LABELS(String field) {
		return "//span[@id='"+field+"-error']";
	}
	
	public static final String ERROR_MESSAGEE_LABELS_CLASS(String field) {
		return "//span[@class='"+field+"-error']";
	}
	
	public static final String MALE_CHECKBOX = "//input[@id='gender-male']";
	public static final String FEMALE_CHECKBOX = "//input[@id='gender-female']";
	public static final String FIRSTNAME_TEXTBOX = "//input[@id='FirstName']";
	public static final String LASTNAME_TEXTBOX = "//input[@id='LastName']";
	public static final String DAY_DROPDOWN = "//select[@name='DateOfBirthDay']";
	public static final String MONTH_DROPDOWN = "//select[@name='DateOfBirthMonth']";
	public static final String YEAR_DROPDOWN = "//select[@name='DateOfBirthYear']";
	public static final String EMAIL_TEXTBOX = "//input[@id='Email']";
	public static final String COMPANY_TEXTBOX = "//input[@id='Company']";
	public static final String PASSWORD_TEXTBOX = "//input[@id='Password']";
	public static final String CONFIRM_PASSWORD_TEXTBOX = "//input[@id='ConfirmPassword']";
	public static final String REGISTER_BUTTON = "//input[@id='register-button']";
	public static final String RESULT_LABEL = "//div[@class='result']";
	public static final String CONTINUE_BUTTON = "//input[@name='register-continue']";
	
	public static final String ERROR_MESSAGE_LABEL = "//div[@class='message-error validation-summary-errors']";
}
