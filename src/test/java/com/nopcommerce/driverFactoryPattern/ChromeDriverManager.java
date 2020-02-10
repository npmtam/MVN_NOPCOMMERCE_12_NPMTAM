package com.nopcommerce.driverFactoryPattern;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;

public class ChromeDriverManager extends DriverManager {

	@Override
	public void createDriver() {
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--incognito");	
		options.addArguments("--start-maximized");	
		options.addArguments("--disable-infobars");	
		driver = new ChromeDriver(options);
	}
	
}
