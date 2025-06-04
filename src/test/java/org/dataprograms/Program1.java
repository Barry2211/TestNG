package org.dataprograms;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class Program1 {

	public static WebDriver driver;
	
	@Parameters({"url"})
	@Test
	public void meth1(String url) {
		driver = new EdgeDriver();
		driver.get(url);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		JavascriptExecutor js = (JavascriptExecutor) driver;
        boolean pop= (Boolean) js.executeScript("return !!window.alert;");
        if(pop) {
        	driver.findElement(By.id("btn_location_close_icon")).click();
        }
	}
	
	@Test(dataProvider="search",priority=1)
	public void search(String product) {
		WebElement searchBar = driver.findElement(By.id("autocomplete-0-input"));
		searchBar.sendKeys(Keys.chord(Keys.CONTROL,"a"),product);
		searchBar.submit();
		List<WebElement> elements = driver.findElements(By.xpath("//div[@class=\"plp-card-details-name line-clamp jm-body-xs jm-fc-primary-grey-80\"]"));
		
		for(WebElement element:elements) {
			String text=element.getText();
			if(text.contains(product)) {
				System.out.println(text);
			}
			else {
				System.out.print("Invalid Item is displayed : ");
				System.out.print(text);
			}
		}
		System.out.print("");
	}
	
	@Test(dataProvider="searchSpecs",priority=2)
	public void searchSpecs(ProductModels product) {
		WebElement searchBar = driver.findElement(By.id("autocomplete-0-input"));
		searchBar.sendKeys(Keys.chord(Keys.CONTROL,"a"),product.brand);
		searchBar.submit();
		List<WebElement> elements = driver.findElements(By.xpath("//div[@class=\"plp-card-details-name line-clamp jm-body-xs jm-fc-primary-grey-80\"]"));
		
		for(WebElement element:elements) {
			String text=element.getText();
			if(text.contains(product.model)) {
				System.out.println(text);
			}
			else {
				System.out.print("Invalid Item is displayed : ");
				System.out.print(text);
			}
		}
		System.out.print("");
	}
	
	@AfterSuite
	private void end() {
		driver.quit();
	}
	
	@DataProvider(name = "search")
	public String[] searchData() {
		return new String[] {"iPhone","Samsung","Redmi"};

	}
	
	@DataProvider(name = "searchSpecs")
	public ProductModels[] searchSpecsData() {
		return new ProductModels[] {
				new ProductModels("Reconnect", "Cable"),
				new ProductModels("LG", "Washing Machine"),
				new ProductModels("fire boltt", "Smart Watch"),
		};

	}
}


class ProductModels{
	public String brand;
	public String model;
	public ProductModels(String brand,String model) {
		this.brand=brand;
		this.model=model;
	}
}
