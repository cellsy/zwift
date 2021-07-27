package com.zwift.tests;


import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;

import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.zwift.pages.Events;
import com.zwift.pages.FilterEventsControl;
import com.zwift.pages.Home;

import io.github.bonigarcia.wdm.WebDriverManager;

public class UiAutomationTest {
	WebDriver driver = null;

	@BeforeTest
	public void setup() {
		String chromeVersion = System.getProperty("chromeVersion");
				
		if (chromeVersion == null || chromeVersion == "")
		{
			assertEquals(false, true, "Did not provide a chrome version in the testng.xml file");
		}
		driver = null;
		
		/**
		 * WebDriverManager is a way to not store every version
		 * of the chromedriver in the project
		 * 
		 * On my machine, I'm using "81.0.4044.138"
		 */
		
		WebDriverManager.chromedriver().version(chromeVersion).setup();
		ChromeOptions options = new ChromeOptions();
		
		/**
		 * these should speed up chrome during testing
		 */
		options.addArguments("start-maximized");
		options.addArguments("enable-automation");
		options.addArguments("--no-sandbox");
		options.addArguments("--disable-infobars");
		options.addArguments("--disable-dev-shm-usage");
		options.addArguments("--disable-browser-side-navigation");
		options.addArguments("--disable-gpu");
		driver = new ChromeDriver(options);
	}

	@Test
	public void pageLoadedTest() {
		Home home = new Home(driver);
		assertNotNull(home);
		assertEquals(true, home.isNavigationVisible());
	}
	
	@Test
	public void eventFilterTest() {
		Home home = new Home(driver);
		home.clickNavigationButton();
		Events events = home.clickEventsLink();
		
		assertEquals(events.isEventsVisible(), true);
		
		FilterEventsControl filter = events.clickFilterEvents();
		int numEventsBefore = filter.countEvents();
		
		filter.clickCyclingButton();
		filter.clickCLoseButton();
		int numEventsAfter = filter.countEvents();
		
		System.out.println(numEventsBefore + " - " + numEventsAfter);
	}

	// cleanup, shut down and clean up
	@AfterTest
	public void tearDown() {
		driver.close();
		driver.quit();
	}
}
