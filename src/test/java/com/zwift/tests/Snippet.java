package com.zwift.tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Snippet {
	public void waitForElement(WebDriver driver, final String webElementId) {
		int timeOut = 90;
		WebDriverWait wait = new WebDriverWait(driver, timeOut);
		wait.until(new ExpectedCondition<Boolean>() {
			public Boolean apply(WebDriver driver) {
				if (driver.findElement(By.id(webElementId)) != null) {
					return Boolean.TRUE;
				}
				return null;
			}
		});
	}
}
