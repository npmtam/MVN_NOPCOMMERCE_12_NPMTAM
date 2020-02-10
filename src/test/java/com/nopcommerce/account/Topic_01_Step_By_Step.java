package com.nopcommerce.account;

import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.testng.Assert.assertTrue;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_01_Step_By_Step {
	WebDriver driver;
	Select select;
	JavascriptExecutor jsExecutor;
	WebDriverWait waitExplicit;

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.chrome.driver", ".\\libaries\\chromedriver.exe");
		driver = new ChromeDriver();
		jsExecutor = (JavascriptExecutor) driver;
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}

	@Test
	public void TC_01_Register() throws InterruptedException {
		driver.get("https://demo.nopcommerce.com/login");

		// Click register
		driver.findElement(By.xpath("//a[@class='ico-register']")).click();
		// Verify register page displayed
		assertTrue(driver.findElement(By.xpath("//div[@class='page registration-page']")).isDisplayed());

		// Fill info to register
		driver.findElement(By.xpath("//input[@id='gender-male']")).click(); // Select gender
		driver.findElement(By.xpath("//input[@id='FirstName']")).sendKeys("Danny"); // Fill Firstname
		driver.findElement(By.xpath("//input[@id='LastName']")).sendKeys("Cee"); // Fill Lastname
		//D.O.B
		select = new Select(driver.findElement(By.xpath("//select[@name='DateOfBirthDay']")));
		select.selectByVisibleText("10"); 
		
		select = new Select(driver.findElement(By.xpath("//select[@name='DateOfBirthMonth']")));
		select.selectByVisibleText("October"); 
		
		select = new Select(driver.findElement(By.xpath("//select[@name='DateOfBirthYear']")));
		select.selectByVisibleText("1990");

//		// DOB
//		selectItemInDropdown("//select[@name='DateOfBirthDay']", "//select[@name='DateOfBirthDay']/option", "10");
//		Thread.sleep(1000);
//		selectItemInDropdown("//select[@name='DateOfBirthMonth']", "//select[@name='DateOfBirthMonth']/option",
//				"October");
//		Thread.sleep(1000);
//		selectItemInDropdown("//select[@name='DateOfBirthYear']", "//select[@name='DateOfBirthYear']/option", "1993");

		driver.findElement(By.xpath("//input[@id='Email']")).sendKeys("tamnguyen_" + randomNumber() + "@gmail.com"); // email
		driver.findElement(By.xpath("//input[@id='Company']")).sendKeys("Step Sister"); // Company
		driver.findElement(By.xpath("//input[@id='Password']")).sendKeys("123123"); // password
		driver.findElement(By.xpath("//input[@id='ConfirmPassword']")).sendKeys("123123"); // ConfirmPassword

		// Click register
		driver.findElement(By.xpath("//input[@id='register-button']")).click();
	}

	@Test
	public void TC_02_Login() {

	}

	// METHODS
	public int randomNumber() {
		Random random = new Random();
		return random.nextInt(999);
	}

	public void selectItemInDropdown(String parentLocator, String allItemsLocator, String expectedItem)
			throws InterruptedException {
		WebElement parentDropdown = driver.findElement(By.xpath(parentLocator));
		jsExecutor.executeScript("arguments[0].scrollIntoView(true)", parentDropdown);
		jsExecutor.executeScript("arguments[0].click();", parentDropdown);
//			parentDropdown.click();
		Thread.sleep(1000);

		// wait for elements
		waitExplicit.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(allItemsLocator)));

		// Define list to store all listed items
		List<WebElement> allItems = driver.findElements(By.xpath(allItemsLocator));

		// Through each item to find the item
		// For each
		for (WebElement item : allItems) {
			if (item.getText().equals(expectedItem)) {
				jsExecutor.executeScript("arguments[0].scrollIntoView(true)", item);
				Thread.sleep(1000);
				item.click();
				Thread.sleep(1000);
				break;
			}
		}
	}
}
