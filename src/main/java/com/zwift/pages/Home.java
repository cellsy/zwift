package com.zwift.pages;

import java.time.Duration;
import java.util.NoSuchElementException;
import java.util.function.Function;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * https://www.swtestacademy.com/pagegenerator-loadablecomponent-page-object-model-selenium/
 * 
 * @author cellsworth
 *
 */
public class Home {
	WebDriver driver;

	@FindBy(xpath = "//header[@data-testid='primaryNav']")
	public WebElement primaryNav;

	@FindBy(xpath = "//*[@aria-label='Open side navigation']")
	public WebElement navigationButton;

	@FindBy(linkText = "Events")
	public WebElement eventsLink;

	private String url = "http://zwift.com";

	public Home(WebDriver driver) {
		this.driver = driver;
		driver.get(url);
		acceptCookies();
		PageFactory.initElements(driver, this);
	}

	private void acceptCookies() {
		int count = 0;
		while (count++ < 10) {
			try {
				Thread.sleep(100);
				driver.findElement(By.id("truste-consent-button")).click();
				break;
			} catch (Exception e) {
				System.out.println("Didn't find after " + count + " tries.");
			}
		}
	}

	public void clickNavigationButton() {
		navigationButton.click();
		new WebDriverWait(driver, 5).until(ExpectedConditions.elementToBeClickable(eventsLink));
	}

	public Events clickEventsLink() {
		String _url = eventsLink.getText();
		return new Events(driver, url + "/" + _url);
	}

	public boolean isNavigationVisible() {
		return navigationButton.isDisplayed();
	}
}