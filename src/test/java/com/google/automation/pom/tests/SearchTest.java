package com.google.automation.pom.tests;

import static org.testng.Assert.assertTrue;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.google.automation.pom.driver.BaseDriver;
import com.google.automation.pom.pages.ResultsPage;
import com.google.automation.pom.pages.SearchPage;

public class SearchTest extends BaseDriver {

	@BeforeMethod(enabled = true)
	public void startWebdriverSession() {
		startSession();
	}

	@AfterMethod(enabled = true)
	public void stopWebdriverSession() {
		stopSession();
	}

	@Test(priority = 1, description = "Googele Search test")
	public void googleSearchTest() throws Exception {
		SearchPage sp = new SearchPage(driver);
		sp.search("Selenium");
		ResultsPage rp = new ResultsPage(driver);
		assertTrue(rp.verifyResultsPage("https://www.selenium.dev"));

	}

}
