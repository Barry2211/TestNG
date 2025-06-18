package org.windows;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import org.baseclass.DriverUtils;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.annotations.Test;

public class Windows extends DriverUtils{

	Set<String> landingPages =  new LinkedHashSet<>();
	List<String> windowList = new ArrayList<>();
	@Test
	public void handling() {
		driver = new EdgeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		driver.get("https://adactinhotelapp.com/");
		String homePage = driver.getWindowHandle();
		windowList.add(homePage);
		javascript(newWindow);
		landingPages =  driver.getWindowHandles();
		for(String page:landingPages) {
			if(!windowList.contains(page)) {
				windowList.add(page);
			}
		}
		javascript(newWindow);
		landingPages =  driver.getWindowHandles();
		for(String page:landingPages) {
			if(!windowList.contains(page)) {
				windowList.add(page);
			}
		}
		driver.switchTo().window(windowList.get(1));
		driver.get("https://www.facebook.com/");
		driver.switchTo().window(windowList.get(2));
		driver.get("https://www.amazon.com/");
	}
}
