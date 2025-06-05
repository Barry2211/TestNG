package org.adactin;

import java.util.concurrent.TimeUnit;

import org.baseclass.DriverUtils;
import org.baseclass.MethodUtils;
import org.baseclass.ProductModels;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class AdactinLogin extends DriverUtils{


	@BeforeSuite
	private void lunch() {
		driverInit(MethodUtils.EDGE);
	}
	
	@Parameters("url")
	@BeforeTest
	private void siteLaunch(String url){
		windowOp(MethodUtils.max);
		urlInit(url);
		driverWait(10);
		
	}
	
	
	@Test(dataProvider="searchSpecs")
	private void Program(ProductModels model) throws InterruptedException {
		locate(MethodUtils.id, "username").sendKeys(model.brand);
		locate(MethodUtils.id, "password").sendKeys(model.model);
		TimeUnit.SECONDS.sleep(2);
		locate(MethodUtils.id, "login").click();

	}
	
	@DataProvider(name = "searchSpecs")
	public ProductModels[] searchSpecsData() {
		return new ProductModels[] {
				new ProductModels("", "Cable"),
				new ProductModels("qwerty@", " "),
				new ProductModels("LifeHacker11", "VDTYZV"),
		};

	}
}
