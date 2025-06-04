package org.groups;

import java.time.Duration;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

import org.baseclass.DriverUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class Program1 extends DriverUtils{

	public static WebDriver driver;
	public static Scanner scan = new Scanner(System.in);
	static String product = "iPhone";
	String productName;
	
	@BeforeSuite
	private void lunch() {
		driver = new EdgeDriver();
	}
	
	@Parameters({"url"})
	@BeforeTest
	private void siteLaunch(String url){
		driver.manage().window().maximize();
		
		driver.get(url);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
	}
	
	@Test(groups = {"signIn"},priority = 1)
	private void login() {
		JavascriptExecutor js = (JavascriptExecutor) driver;
        boolean pop= (Boolean) js.executeScript("return !!window.alert;");
        System.out.println(pop);
        if(pop) {
        	driver.findElement(By.id("btn_location_close_icon")).click();
        }
		OpenLocator.xPath(driver, "//button[@id=\"btn_sign_in\"]").click();
		OpenLocator.id(driver, "phoneNumber").sendKeys("6382074263",Keys.ENTER);
		System.out.println("Enter Otp: ");
		String otp = scan.next();
		List<WebElement> otpFields = driver.findElements(By.xpath("//input[@type='number']"));
		for(int i = 0 ; i < otpFields.size() ; i++) {
			WebElement field = otpFields.get(i);
			field.sendKeys(otp.charAt(i)+"");
		}
		Actions verify = new Actions(driver);
		verify.click(OpenLocator.xPath(driver, "//div[text()='Verify']")).perform();
		
	}
	
	@Test(groups = {"pop"}, priority = 3)
	private void popClose() {
		JavascriptExecutor js = (JavascriptExecutor) driver;
        boolean pop= (Boolean) js.executeScript("return !!window.alert;");
        
        System.out.println(pop);
        if(pop) {
        	driver.findElement(By.id("btn_location_close_icon")).click();
        }
	}
	
	@Test(groups = {"home"},priority = 4)
	private void search() {
		System.out.println("search");
		WebElement searchBar = driver.findElement(By.id("autocomplete-0-input"));
		searchBar.sendKeys(product);
		searchBar.submit();
	}
	
	@Test(groups = {"search"},dependsOnGroups = {"home"},priority = 5)
	private void productSelect() {
		List<WebElement> products = driver.findElements(By.xpath("//a[@class=' plp-card-wrapper plp_product_list ']"));
		System.out.println(products.size());
		if(products.size()!=0) {
			productName = products.get(0).getText();
			products.get(0).click();
		}
	}
	
	@Test(groups = {"productImages"},dependsOnGroups = {"search"})
	private void productImages() throws InterruptedException {
		WebElement nextBtn = OpenLocator.xPath(driver, "//div[@class='center swiper-thumb-button-next jm-btn secondary small full-width jm-mt-s']");
		for(int i = 0 ; i < 5 ; i++) {
			TimeUnit.SECONDS.sleep(2);
			nextBtn.click();
		}
	}
	
	@Test(groups = {"productBuy"},dependsOnGroups = {"signIn"},priority = 6)
	private void productBuy() {
		WebElement nextBtn = OpenLocator.xPath(driver, "//button[text()='Buy Now']");
		nextBtn.click();
	}
	
	@Test(groups = {"productAddToCart"},priority = 6)
	private void productAddToCart(){
		WebElement nextBtn = OpenLocator.xPath(driver, "(//button[contains(text(),'Add to Cart')])[1]");
		nextBtn.click();
		OpenLocator.xPath(driver, "//a[@id='view_cart_link']").click();
	}
	
	@Test(groups = {"productCart"},dependsOnGroups = {"productAddToCart"},priority = 6)
	private void Cart() throws InterruptedException{
		WebElement product = OpenLocator.xPath(driver, "//div[@class='j-text product-name cursor-pointer j-text-body-xs ng-star-inserted']");
		String prodName = product.getText();
		if(productName.contains(prodName)) {
			System.out.println("Valid product is added");
		}
		else {
			System.out.println("Different product is added");
		}
		TimeUnit.SECONDS.sleep(2);
	}
	
	@Test(groups = {"placeOrder"},dependsOnGroups = {"productCart","signIn"},priority = 7)
	private void placeOrder() {
		OpenLocator.xPath(driver, "//button[@name='placeorder']").click();
	}
	
}
