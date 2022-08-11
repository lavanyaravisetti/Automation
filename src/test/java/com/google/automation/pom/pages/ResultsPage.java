package com.google.automation.pom.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ResultsPage {
	WebDriver driver;
	By test = By.id("test");

	public ResultsPage(WebDriver driver) throws Exception {
		this.driver = driver;
	}

	public boolean verifyResultsPage(String string) {
		String str = driver.getPageSource();
		if (str.contains(string))
			return true;
		else
			return false;
	}

}
