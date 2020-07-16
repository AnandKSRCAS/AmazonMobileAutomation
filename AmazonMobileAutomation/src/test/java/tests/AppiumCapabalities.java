package tests;

import java.io.IOException;
import java.net.URL;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.remote.DesiredCapabilities;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.remote.AndroidMobileCapabilityType;
import io.appium.java_client.remote.AutomationName;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.remote.MobilePlatform;
import utils.ReadConfigProperty;


/*
 * Author
 * Aravindh
 * Created on 11-07=2020
 */
public class AppiumCapabalities {   
	static AppiumDriver<AndroidElement> appiumDriver;
	
	 static Properties property = null;

	public static DesiredCapabilities setup() throws IOException
	{   
		property = ReadConfigProperty.readConfigProperty("GlobalSettings.properties");
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
			appiumDriver = new AndroidDriver<AndroidElement>(new URL(property.getProperty("AppiumURL")), dc);
			appiumDriver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
			
		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());
		}
		return dc;
		
				
}  
	
	
}

