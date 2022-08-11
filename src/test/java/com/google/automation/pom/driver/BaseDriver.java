package com.google.automation.pom.driver;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import com.google.automation.utils.PropertiesReaderUtility;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseDriver {

	public WebDriver driver;

	String chrome = "chrome";
	String firefox = "firefox";
	String ie = "ie";

	protected String user_dir = System.getProperty("user.dir");
	PropertiesReaderUtility prop = new PropertiesReaderUtility(user_dir + "/selenium.properties");

	public String getProperty(String configItemName) {
		return prop.getProperty(configItemName);
	}

	public void startSession() {
		if (getProperty("remoteWebdriver").equalsIgnoreCase("false")) {
			if (getProperty("browser").equalsIgnoreCase(firefox)) {
				WebDriverManager.firefoxdriver().setup();

				driver = new FirefoxDriver();
			} else if (getProperty("browser").equals(chrome)) {
				WebDriverManager.chromedriver().setup();
				driver = new ChromeDriver();

			} else if (getProperty("browser").equals(ie)) {
				WebDriverManager.iedriver().setup();

				DesiredCapabilities ieCapabilities = DesiredCapabilities.internetExplorer();
				ieCapabilities.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS,
						true);
				driver = new InternetExplorerDriver(ieCapabilities);
			}
		} else {
			URL gridUrl = null;
			try {
				gridUrl = new URL(getProperty("gridHubURL"));
			} catch (MalformedURLException e) {
			}
			if (getProperty("browser").equalsIgnoreCase(firefox)) {
				WebDriverManager.firefoxdriver().setup();
				FirefoxProfile fp = new FirefoxProfile();
				// set something on the profile...
				DesiredCapabilities dc = DesiredCapabilities.firefox();
				dc.setCapability(FirefoxDriver.PROFILE, fp);
				dc.setCapability("marionette", true);
				driver = new RemoteWebDriver(gridUrl, dc);
			} else if (getProperty("browser").equals(chrome)) {
				WebDriverManager.chromedriver().setup();
				ChromeOptions options = new ChromeOptions();
				// set some options
				DesiredCapabilities dc = DesiredCapabilities.chrome();
				dc.setCapability(ChromeOptions.CAPABILITY, options);
				driver = new RemoteWebDriver(gridUrl, dc);

			} else if (getProperty("browser").equals(ie)) {
				WebDriverManager.iedriver().setup();
				DesiredCapabilities ieCapabilities = DesiredCapabilities.internetExplorer();
				ieCapabilities.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS,
						true);
				driver = new InternetExplorerDriver(ieCapabilities);
			}

		}
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.get(getProperty("baseurl"));
		driver.manage().window().maximize();
	}

	public void stopSession() {
		driver.quit();
	}
}
