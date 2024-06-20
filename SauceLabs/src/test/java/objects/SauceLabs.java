package objects;

import java.util.ArrayList;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

public class SauceLabs {
	
WebDriver driver;
	
	public SauceLabs(WebDriver driver) {
		this.driver = driver;
	}
	By userNameText = By.xpath("//input[@id='user-name']");
	By passwordText = By.xpath("//input[@id='password']");
	By loginButton = By.xpath("//input[@id='login-button']");
	By cartLink = By.xpath("//a[@class='shopping_cart_link']");
	By checkoutButton = By.xpath("//button[@id='checkout']");
	By continueButton = By.xpath("//input[@id='continue']");
	By finishButton = By.xpath("//Button[@id='finish']");
	By completeOrderMessage = By.xpath("//h2[@class='complete-header']");
	By menuButton = By.xpath("//button[@id='react-burger-menu-btn']");
	By logoutButton = By.xpath("//a[@id='logout_sidebar_link']");


	//Login to the website with correct credentials
	public void login() {
		try {
			driver.findElement(userNameText).sendKeys("standard_user");
			driver.findElement(passwordText).sendKeys("secret_sauce");
			driver.findElement(loginButton).click();
			Assert.assertTrue(driver.findElement(cartLink).isDisplayed());
			Thread.sleep(5000);
		} 	
		catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	//Checkout 3 items
	public void checkoutItems() throws Exception {
		ArrayList<String> addedItems =new ArrayList<String>();
		ArrayList<String> verifyItems = new ArrayList<String>();
		for(int i=1;i<=3;i++) {
			driver.findElement(By.xpath("//div[@class='inventory_list']/div["+i+"]//button[contains(text(),'Add to cart')]")).click();
			addedItems.add(driver.findElement(By.xpath("//div[@class='inventory_list']/div["+i+"]//div[@class='inventory_item_name ']")).getText());
			Thread.sleep(500);
		}
		driver.findElement(cartLink).click();
		Thread.sleep(500);
		for(int i=1;i<=3;i++) {
			verifyItems.add(driver.findElement(By.xpath("//div[@class='cart_list']//div[@class='cart_item']["+i+"]//div[@class='inventory_item_name']")).getText());
			Thread.sleep(500);
		}
		Assert.assertTrue(addedItems.equals(verifyItems));
		driver.findElement(checkoutButton).click();
		
		//Provide checkout information
		driver.findElement(By.xpath("//input[@id='first-name']")).sendKeys("Nidhi");
		driver.findElement(By.xpath("//input[@id='last-name']")).sendKeys("Kaushal");
		driver.findElement(By.xpath("//input[@id='postal-code']")).sendKeys("841101");
		driver.findElement(continueButton).click();
		driver.findElement(finishButton).click();
		
		Assert.assertTrue(driver.findElement(completeOrderMessage).isDisplayed());
	}
	
	//Logout from the application
	public void logout() throws Exception {
		driver.findElement(menuButton).click();
		Thread.sleep(1000);
		driver.findElement(logoutButton).click();
		Thread.sleep(1000);
		Assert.assertTrue(driver.findElement(loginButton).isDisplayed());;
	}
}
