package com.zwift.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Events extends BasePage {
	@FindBy (xpath = "//button[text()='Filter events']")
	public WebElement filterEventsButton;
	
	
	@FindBy (id = "events-header")
	public WebElement eventsH1;
	
	public Events (WebDriver driver, String url) {
		BasePage.driver = driver;
		driver.get(url);
		PageFactory.initElements(driver, this);
	}
	
	public FilterEventsControl clickFilterEvents() {
		try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		filterEventsButton.click();
		return new FilterEventsControl(driver);
	}
	
	public boolean isEventsVisible() {
		return eventsH1.isDisplayed();
	}
}
