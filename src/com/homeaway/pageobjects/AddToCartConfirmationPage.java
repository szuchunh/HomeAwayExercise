package com.homeaway.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class AddToCartConfirmationPage {
	private static WebElement element = null;
	 
    public static WebElement goToCheckoutButton(WebDriver driver){
 
		  element = driver.findElement(By.linkText("Go to Checkout"));
 
		  return element;
 
    }
}
