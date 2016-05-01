package com.homeaway.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class CheckoutPage {
	private static WebElement element = null;
	 
    public static WebElement continueButton(WebDriver driver){
 
         element = driver.findElement(By.className("step2"));
 
         return element;
 
    }
    
    public static WebElement usernameField(WebDriver driver){
    	 
        element = driver.findElement(By.id("log"));

        return element;

    }
    
    public static WebElement passwordField(WebDriver driver){
   	 
        element = driver.findElement(By.id("pwd"));

        return element;

    }
    
    public static WebElement loginButton(WebDriver driver){
      	 
        element = driver.findElement(By.id("login"));

        return element;

   }
    
    public static WebElement purchaseButton(WebDriver driver){
     	 
        element = driver.findElement(By.className("make_purchase"));

        return element;

   }
    
    public static WebElement shippingPrice(WebDriver driver){
    	 
        element = driver.findElement(By.className("checkout-shipping"));

        return element;

   }
    
    public static WebElement totalPrice(WebDriver driver){
   	 
        element = driver.findElement(By.xpath("//*[@id='post-30']/div/div[2]/p[3]"));

        return element;

   }
    
    public static WebElement emailField(WebDriver driver){
      	 
        element = driver.findElement(By.id("wpsc_checkout_form_9"));

        return element;

   }
}
