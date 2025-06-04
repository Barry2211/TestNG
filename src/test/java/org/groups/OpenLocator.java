package org.groups;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class OpenLocator {

	public static WebElement id(WebDriver driver,String locator) {
		return driver.findElement(By.id(locator));
	}
	
	public static WebElement classname(WebDriver driver,String locator) {
		return driver.findElement(By.className(locator));
	}
	
	public static WebElement name(WebDriver driver,String locator) {
		return driver.findElement(By.name(locator));
	}
	
	public static WebElement xPath(WebDriver driver,String locator) {
		return driver.findElement(By.xpath(locator));
	}
}
