package com.nopcommerce.driverFactoryPattern;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;

public class ChromeHeadlessDriverManager extends DriverManager {

	@Override
	public void createDriver() {
		ChromeOptions options = new ChromeOptions();
		options.addArguments("headless");
		options.addArguments("--incognito");
		options.addArguments("--disable-infobars");
		options.addArguments("windows-size=1600x900");
		driver = new ChromeDriver(options);
	} 	
	
}
