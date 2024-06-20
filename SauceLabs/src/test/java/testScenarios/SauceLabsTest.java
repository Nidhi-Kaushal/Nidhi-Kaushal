package testScenarios;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import objects.SauceLabs;

public class SauceLabsTest {
	
	public static WebDriver driver;
	SauceLabs obj;

	
	@SuppressWarnings("deprecation")
	@BeforeTest
	public void setUp() {
		System.setProperty("webdriver.http.factory", "jdk-http-client");

		System.setProperty("webdriver.chrome.driver", "C:\\Users\\HP\\Downloads\\chromedriver-win64\\chromedriver-win64\\chromedriver.exe");
		driver= new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://www.saucedemo.com/");
		driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
		obj = new SauceLabs(driver);

	}
	@Test(priority=1)
	public void login() {
		obj.login();
	}
	
	@Test(priority=2)
	public void checkoutItems() throws Exception {
		obj.checkoutItems();
	}
	
	@Test(priority=3)
	public void logout() throws Exception {
		obj.logout();
	}
	
	@AfterTest
	public void tearDown() {
		driver.quit();
	}
}



