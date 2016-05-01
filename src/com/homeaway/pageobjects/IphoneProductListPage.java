package com.homeaway.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class IphoneProductListPage {
	private static WebElement element = null;
	 
    public static WebElement iphone4SLink(WebDriver driver){
 
         element = driver.findElement(By.linkText("Apple iPhone 4S 16GB SIM-Free – Black"));
 
         return element;
 
    }
}
