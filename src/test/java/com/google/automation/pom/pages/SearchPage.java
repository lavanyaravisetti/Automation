package com.google.automation.pom.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;

public class SearchPage {

	WebDriver driver;

	By txtSearchTextBox = By.name("q");
	By searchButton = By.name("btnK");

	public SearchPage(WebDriver driver) throws Exception {
		this.driver = driver;
	}

	public void search(String searchText) {
		driver.findElement(txtSearchTextBox).sendKeys(searchText);
		driver.findElement(txtSearchTextBox).sendKeys(Keys.ENTER);

	}

}
