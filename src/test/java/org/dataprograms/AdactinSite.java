package org.dataprograms;

import org.baseclass.DriverUtils;
import org.baseclass.MethodUtils;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class AdactinSite extends DriverUtils{
	
	@BeforeSuite
	private void lunch() {
		driverInit(MethodUtils.EDGE);
	}
	
	@BeforeTest
	private void siteLaunch(){
		windowOp(MethodUtils.max);
		urlInit("https://adactinhotelapp.com/");
		driverWait(10);
		
	}
	
	
	@Test
	private void Program() {
		locate(MethodUtils.id, "username").sendKeys("abcde");
		locate(MethodUtils.id, "password").sendKeys("password");
		locate(MethodUtils.id, "login").click();
		//ewfewoifjewfewf

	}
	
	@DataProvider(name = "searchSpecs")
	public ProductModels[] searchSpecsData() {
		return new ProductModels[] {
				new ProductModels("Reconnect", "Cable"),
				new ProductModels("LG", "Washing Machine"),
				new ProductModels("fire boltt", "Smart Watch"),
				new ProductModels(brand, model)
		};

	}
	

}
