package com.nopcommerce.account;


import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class classTest {
	WebDriver driver;
	Select select;
	JavascriptExecutor jsExecutor;
	WebDriverWait waitExplicit;
	String email;

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.chrome.driver", ".\\libaries\\chromedriver.exe");
		driver = new ChromeDriver();
		jsExecutor = (JavascriptExecutor) driver;
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		email = "jond_wick_" + randomNumber() + "@hotmail.com";
		driver.get("https://demo.nopcommerce.com/");
	}

	@Test
	public void TC_01_Register() throws InterruptedException {
//		  Click to Register
		driver.findElement(By.xpath("//a[@class='ico-register' and text()='Register']")).click();
		Thread.sleep(1000);	
//		  Verify Register Page displayed
		Assert.assertTrue(driver.findElement(By.xpath("//div[@class='page registration-page']")).isDisplayed());
		Thread.sleep(1000);	
//		  Click to gender radio button
		driver.findElement(By.xpath("//input[@id='gender-female']")).click();
		Thread.sleep(1000);	
//		  input to lastname textbox
		driver.findElement(By.xpath("//input[@id='FirstName']")).sendKeys("Jond");
//		  input to
		driver.findElement(By.xpath("//input[@id='LastName']")).sendKeys("Wisk");
		Thread.sleep(1000);	
//		  Selecr Date of birth Dropdown
		select = new Select(driver.findElement(By.xpath("//select[@name='DateOfBirthDay']")));
		select.selectByVisibleText("10");
		Thread.sleep(1000);
		select = new Select(driver.findElement(By.xpath("//select[@name='DateOfBirthMonth']")));
		select.selectByVisibleText("October");
		Thread.sleep(1000);
		select = new Select(driver.findElement(By.xpath("//select[@name='DateOfBirthYear']")));
		select.selectByVisibleText("1999");
		Thread.sleep(1000);	
//		  input to email textbox
		driver.findElement(By.xpath("//input[@id='Email']")).sendKeys(email);
		Thread.sleep(1000);	
//		  input to company name
		driver.findElement(By.xpath("//input[@id='Company']")).sendKeys("Ninja Assassin");
		Thread.sleep(1000);	
//		  input to passwork textbox
		driver.findElement(By.xpath("//input[@id='Password']")).sendKeys("123123");
		Thread.sleep(1000);	
//		  input to Confirm_passwork textbox
		driver.findElement(By.xpath("//input[@id='ConfirmPassword']")).sendKeys("123123");
//		  Click to Register BTn
		Thread.sleep(1000);	
		driver.findElement(By.xpath("//input[@id='register-button']")).click();
		Thread.sleep(1000);	
//		  verify register success
		Assert.assertTrue(driver.findElement(By.xpath("//div[@class='result' and text()='Your registration completed']"))
				.isDisplayed());

//		  Click to logout page
		driver.findElement(By.xpath("//a[@class='ico-logout' and text()='Log out']")).click();

//		  Verify navigate to home page success
		Assert.assertEquals(driver.getCurrentUrl(), "https://demo.nopcommerce.com/");

	}

	@Test
	public void TC_02_Login() {
		// Click to Login page
		driver.findElement(By.xpath("//a[@class='ico-login' and text()='Log in']")).click();

//		  verify login page ddc hiển thị
		Assert.assertTrue(driver.findElement(By.xpath("//div[@class='page login-page']")).isDisplayed());

//		  input to email textbox
		driver.findElement(By.xpath("//input[@id='Email']")).sendKeys(email);

//		  input to passwork textbox
		driver.findElement(By.xpath("//input[@id='Password']")).sendKeys("123123");

//		  Click login btn
		driver.findElement(By.cssSelector(".login-button")).click();
//		  Verify login link displayed
		Assert.assertTrue(driver.findElement(By.xpath("//a[@class='ico-account' and text()='My account']")).isDisplayed());

	}

	@AfterClass
	public void afterClass() {
	}

	public int randomNumber() {
		Random random = new Random();
		return random.nextInt(999999);
	}

}
