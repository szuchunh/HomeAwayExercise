package com.homeaway.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class AccountPage {
	private static WebElement element = null;
	 
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
    
    public static WebElement detailsLink(WebDriver driver){
    	 
        element = driver.findElement(By.linkText("Your Details"));

        return element;

   }
    
    public static WebElement firstNameField(WebDriver driver){
   	 
        element = driver.findElement(By.id("wpsc_checkout_form_2"));

        return element;

   }
    
    public static WebElement lastNameField(WebDriver driver){
      	 
        element = driver.findElement(By.id("wpsc_checkout_form_3"));

        return element;

   }
    
    public static WebElement submitButton(WebDriver driver){
      	 
        element = driver.findElement(By.name("submit"));

        return element;

   }
    
    public static WebElement logoutLink(WebDriver driver){
     	 
        element = driver.findElement(By.linkText("Log out"));

        return element;

   }
    
}
