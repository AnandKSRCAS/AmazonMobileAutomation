package tests;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.remote.AndroidMobileCapabilityType;
import io.appium.java_client.remote.AutomationName;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.remote.MobilePlatform;

public class AppiumCapabalities {   
	AppiumDriver<AndroidElement> appiumDriver;
	@BeforeTest
	public void setup() throws IOException
	{   
	    Properties property = readPropertiesFile("GlobalSettings.properties");
	    String udid = property.getProperty("UDID");
	    String deviceName = property.getProperty("deviceName");
	    String appPackage =  property.getProperty("appPackage");
	    String appActivity = property.getProperty("appActivity");
		DesiredCapabilities dc = new DesiredCapabilities();
		dc.setCapability(MobileCapabilityType.PLATFORM_NAME, MobilePlatform.ANDROID);
		dc.setCapability(MobileCapabilityType.DEVICE_NAME, deviceName);
		dc.setCapability(MobileCapabilityType.UDID, udid);
		dc.setCapability(MobileCapabilityType.AUTOMATION_NAME, AutomationName.ANDROID_UIAUTOMATOR2);
		dc.setCapability(MobileCapabilityType.FULL_RESET , false);
		dc.setCapability(MobileCapabilityType.NO_RESET , true);
		dc.setCapability(MobileCapabilityType.NEW_COMMAND_TIMEOUT, 20000);
		dc.setCapability(MobileCapabilityType.APP, property.getProperty("AndroidPath"));
		dc.setCapability(AndroidMobileCapabilityType.APP_PACKAGE,appPackage);
		dc.setCapability(AndroidMobileCapabilityType.APP_ACTIVITY,appActivity);
				dc.setCapability(AndroidMobileCapabilityType.AUTO_GRANT_PERMISSIONS , true);	
		try
		{
			//Initializing appiumDriverFactory object
			appiumDriver = new AndroidDriver<AndroidElement>(new URL("http://127.0.0.1:4723/wd/hub"), dc);
			appiumDriver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());
		}
		
				
}  
	
	@Test
	public void samplerun()
	{
		System.out.println("I have started running");
	}
	@Test
	public void searchData()
	{
		AndroidElement skipSignIn = appiumDriver.findElement(By.id("com.amazon.mShop.android.shopping:id/skip_sign_in_button"));
		if(skipSignIn.isDisplayed())
		{
			skipSignIn.click();
		}
		else
		{
			System.out.println("no in signpage");
		}
	  AndroidElement enterValue =	appiumDriver.findElement(By.id("com.amazon.mShop.android.shopping:id/rs_search_src_text"));
	 enterValue.sendKeys("65 inch tv");
	 appiumDriver.manage().timeouts().implicitlyWait(05, TimeUnit.SECONDS);
	 AndroidElement dataEntered = appiumDriver.findElement(By.xpath("//[contains(text(), '64 inch tv')]"));
	 dataEntered.click();
	 
	}
	
	@AfterSuite
	public void tearDown () {
	    if (appiumDriver != null) {
	    	appiumDriver.quit();

	    }
	
	}
	public static Properties readPropertiesFile(String fileName) throws IOException {
	      FileInputStream fis = null;
	      Properties prop = null;
	      try {
	         fis = new FileInputStream(fileName);
	         prop = new Properties();
	         prop.load(fis);
	      } catch(FileNotFoundException fnfe) {
	         fnfe.printStackTrace();
	      } catch(IOException ioe) {
	         ioe.printStackTrace();
	      } finally {
	         fis.close();
	      }
	      return prop;
	   }
	
	
}

