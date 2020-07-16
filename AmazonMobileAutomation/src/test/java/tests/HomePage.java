package tests;

import org.openqa.selenium.By;
import org.testng.annotations.Test;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import io.appium.java_client.PerformsTouchActions;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;


public class HomePage {
    
	AppiumDriver<AndroidElement> appiumDriver;
	PerformsTouchActions performtouchActions;
	
	@Test
	public void searchData()
	{
	 appiumDriver.findElement(By.id("com.amazon.mShop.android.shopping:id/rs_search_src_text")).click();
	 AndroidElement searchText = ((AndroidDriver<AndroidElement>)appiumDriver).findElementByAndroidUIAutomator("new UiSelector().text(\"Search\")");
	 searchText.sendKeys("65 inch tv");
	 int x = searchText.getLocation().getX();
	 int y = searchText.getLocation().getY();
	 appiumDriver.findElement(MobileBy.id("com.amazon.mShop.android.shopping:id/iss_search_dropdown_item_text")).click();

	}
}
