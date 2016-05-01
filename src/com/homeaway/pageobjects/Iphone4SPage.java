package com.homeaway.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class Iphone4SPage {
	private static WebElement element = null;
	 
    public static WebElement addToCartButton(WebDriver driver){
 
    	element = driver.findElement(By.name("Buy"));
 
    	return element;
 
    }
}
