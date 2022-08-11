package com.google.automation.utils;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class WebDriverWaitUtils {

	public WebElement presenceOfElementLocated(WebDriver driver, By by) {
		WebElement myDynamicElement = (new WebDriverWait(driver, 10))
				.until(ExpectedConditions.presenceOfElementLocated(by));
		return myDynamicElement;
	}
	
	public WebElement elementToBeClickable(WebDriver driver, By by) {
		WebElement myDynamicElement = (new WebDriverWait(driver, 10))
				.until(ExpectedConditions.elementToBeClickable(by));
		return myDynamicElement;
	}

}
