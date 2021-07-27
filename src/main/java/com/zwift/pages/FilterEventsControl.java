package com.zwift.pages;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.LoadableComponent;
import org.openqa.selenium.support.ui.WebDriverWait;

public class FilterEventsControl extends BasePage {

	@FindBy (xpath = "//button[text()='Cycling']")
	WebElement cyclingButton;
	
	@FindBy (xpath = "//button[text()='Close']")
	public WebElement closeButton;
	
	@FindBy (className =  "tab-listing")
	List<WebElement> events;
	
	public FilterEventsControl(WebDriver driver){
		this.driver = driver;
		PageFactory.initElements(driver, this);
		new WebDriverWait(driver, 2)
			.until(ExpectedConditions.elementToBeClickable(cyclingButton));
	}
	
	public int countEvents() {
		return events.size();
	}
	
	public void clickCyclingButton() {
		cyclingButton.click();
	}

	public void clickCLoseButton() {
		closeButton.click();
	}
}
