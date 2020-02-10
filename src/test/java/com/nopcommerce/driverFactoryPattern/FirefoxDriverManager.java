package com.nopcommerce.driverFactoryPattern;

import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

public class FirefoxDriverManager extends DriverManager {

	@Override
	public void createDriver() {
		String rootFolder = System.getProperty("user.dir");
		System.setProperty("webdriver.gecko.driver", rootFolder + "\\resources\\geckodriver.exe");
		System.setProperty(FirefoxDriver.SystemProperty.DRIVER_USE_MARIONETTE, "true");
		System.setProperty(FirefoxDriver.SystemProperty.BROWSER_LOGFILE, rootFolder + "\\FirefoxLogs.txt");
		driver = new FirefoxDriver();
		
	}

}
