package com.homeaway.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class HomePage {
	private static WebElement element = null;
	 
    public static WebElement myAccountButton(WebDriver driver){
 
    	element = driver.findElement(By.className("account_icon"));
 
    	return element;
 
    }
}
