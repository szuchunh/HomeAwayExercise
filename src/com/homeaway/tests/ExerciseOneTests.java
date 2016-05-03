package com.homeaway.tests;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.homeaway.pageobjects.AccountPage;
import com.homeaway.pageobjects.AddToCartConfirmationPage;
import com.homeaway.pageobjects.CheckoutPage;
import com.homeaway.pageobjects.HomePage;
import com.homeaway.pageobjects.Iphone4SPage;
import com.homeaway.pageobjects.IphoneProductListPage;

import org.junit.Assert;

public class ExerciseOneTests {
	  private WebDriver driver;
	  private String baseUrl;
	  private String iphonesPageUrl;
	  private String username = "szuchunh";
	  private String password = "testpassword123!";
	  private double iphoneCost = 270;
	  private String cartMessage = "Oops, there is nothing in your cart.";

	  @Before
	  public void setUp() throws Exception {
	    driver = new FirefoxDriver();
	    baseUrl = "http://store.demoqa.com/";
	    iphonesPageUrl = "http://store.demoqa.com/products-page/product-category/iphones/";
	    driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
	  }
	  
	  @Test
	  public void OrderTest() throws InterruptedException {
		  //Navigate to Iphone products page
		  driver.get(iphonesPageUrl);
		  
		  //Add Iphone to cart
		  IphoneProductListPage.iphone4SLink(driver).click();
		  Iphone4SPage.addToCartButton(driver).click();		  
		  AddToCartConfirmationPage.goToCheckoutButton(driver).click();
		  
		  //Go to checkout page
		  CheckoutPage.continueButton(driver).click();
		  
		  // Login using pre-existing account
		  CheckoutPage.usernameField(driver).sendKeys(username);
		  CheckoutPage.passwordField(driver).sendKeys(password);
		  CheckoutPage.loginButton(driver).click();
		  
		  //Need to wait for login page to finish loading
		  Thread.sleep(5000);
		  
		  //Save shipping cost
		  String shippingCostString = CheckoutPage.shippingPrice(driver).getText();
		  shippingCostString = shippingCostString.replace("$", "");
		  Double shippingCost = Double.valueOf(shippingCostString);
		  
		  //Purchase
		  CheckoutPage.purchaseButton(driver).click();
		  
		  //Assert Total Price is correct		  
		  String totalCostStringBlock = CheckoutPage.totalPrice(driver).getText();
		  String stringArray[] = totalCostStringBlock.split(":");
		  String totalCostString = stringArray[2];
		  totalCostString = totalCostString.replace("$", "");
		  Double actualTotalCost = Double.valueOf(totalCostString);
		  Double expectedTotalCost = iphoneCost + shippingCost;
		  Assert.assertEquals(expectedTotalCost, actualTotalCost);
		  
	  }
	  
	  @Test	  
	  public void accountDetailTest() throws InterruptedException {
		  //Navigate to home page
		  driver.get(baseUrl);
		  
		  //Login to My Account
		  HomePage.myAccountButton(driver).click();
		  AccountPage.usernameField(driver).sendKeys(username);
		  AccountPage.passwordField(driver).sendKeys(password);
		  AccountPage.loginButton(driver).click();		  
		  AccountPage.detailsLink(driver).click();
		  
		  //Input new value to city field and save
		  Random r = new Random();
		  int randomNum = r.nextInt(100) + 1;
		  String newFirstName = "First" + String.valueOf(randomNum);
		  AccountPage.firstNameField(driver).sendKeys(Keys.chord(Keys.CONTROL, "a"));
		  AccountPage.firstNameField(driver).sendKeys(newFirstName);
		  AccountPage.lastNameField(driver).click();
		  Thread.sleep(3000);
		  AccountPage.submitButton(driver).click();
		  AccountPage.logoutLink(driver).click();
		  
		  //Back to Homepage
		  driver.get(baseUrl);
		  
		  //Login to My Account
		  HomePage.myAccountButton(driver).click();
		  AccountPage.usernameField(driver).sendKeys(username);
		  AccountPage.passwordField(driver).sendKeys(password);
		  AccountPage.loginButton(driver).click();		  
		  AccountPage.detailsLink(driver).click();
		  
		  //Assert first name is updated
		  Assert.assertEquals(newFirstName, AccountPage.firstNameField(driver).getAttribute("value"));
		  
	  }
	  
	  @Test
	  public void EmptyCartTest() throws InterruptedException {
		  //Navigate to Iphone products page
		  driver.get(iphonesPageUrl);
		  
		  //Add Iphone to cart
		  IphoneProductListPage.iphone4SLink(driver).click();
		  Iphone4SPage.addToCartButton(driver).click();		  
		  AddToCartConfirmationPage.goToCheckoutButton(driver).click();
		  
		  //Remove item
		  CheckoutPage.quantityField(driver).sendKeys(Keys.chord(Keys.CONTROL, "a"));
		  CheckoutPage.quantityField(driver).sendKeys("0");
		  CheckoutPage.updateButton(driver).click();
		  
		  //Assert empty cart message is displayed
		  Assert.assertTrue(CheckoutPage.bodyText(driver).getText().contains(cartMessage));
		  
	  }

	  @After
	  public void tearDown() throws Exception {
	    driver.quit();
	  }
}
