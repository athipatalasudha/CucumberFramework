package com.salesforce.test.basetest;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.Method;
import java.time.Duration;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.logging.LogManager;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import org.testng.log4testng.Logger;

import com.salesforce.test.utility.CommonUtilities;
import com.salesforce.test.utility.Constants;
import com.salesforce.test.utility.GenerateReports;

import io.github.bonigarcia.wdm.WebDriverManager;
import com.salesforce.test.pages.base.BasePage;
public class BaseTest1 extends CommonUtilities
{
	public static WebDriver driver = null;
	public static WebDriverWait wait = null;
	public static GenerateReports report=null;
	//public static Logger logger=LogManager.getLogger(BaseTest1.class);
	
	@BeforeTest
	public static void setupBeforeTest()
	{
		report=GenerateReports.getInstance();
		report.startExtentReport();
	}
	
	@Parameters({"browsername"})
	@Test
	@BeforeMethod
	public static void LaunchSFDC(String browsername,Method m ) {
		System.out.println("before method execution started");
		report.startSingleTestReport(m.getName());
		setDriver(browsername);
		report.logTestinfo("Driver Setup Successfully");
		CommonUtilities CU = new CommonUtilities();
		Properties applicationPropertiesFile = CU.loadFile("data");
		String url = CU.getApplicationProperty("myurl", applicationPropertiesFile);
		driver.get(url);
		driver.manage().window().maximize();
		waitUntilPageLoads();
		report.logTestinfo("Salesforce page launched succesfully");
	}
	@AfterMethod
	public static void tearDown(Method m) {
		//logger.info("after method execution is started");
		//captureSS(m.getName());
		closeBrowser();
	}
	@AfterTest
	public static void teardownAfterTest()
	{
		report.endReport();
	}
	public static void setDriver(String browser) {

		switch (browser) {
		case "firefox":
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
			break;
		case "chrome":
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
			break;
		default:
			break;

		}

	}
	public static WebDriver getDriverInstance()
	{
		return driver;
	}
	public static void gotoURL(String url)
	{
		driver.get(url);
	}
	public static void closeBrowser()
	{
		driver.close();
	}
	public static void closeAllBrowser()
	{
		driver.quit();
	}
	public static void waitUntilPageLoads() {
		//driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(30));
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}
	
	
	public static String captureSS(String ssname)
	{
		try
		{
			Date d=new Date();
			String Timestamp=d.toString().replace(":", "-").replace(" ", "-");
			TakesScreenshot ts=(TakesScreenshot)driver;
			File source=ts.getScreenshotAs(OutputType.FILE);
			var destFilePath = "C:\\Users\\athip\\eclipse-workspace\\eclipsenewAugbatch\\SalesForceAutomationPOM\\src\\test\\java\\"+ssname+ "_"+Timestamp+".png";
			FileHandler.copy(source, new File(destFilePath));
			System.out.println("\n Screenshot taken");
			return destFilePath;
		}catch(Exception e)
		{
			System.out.println("\n Error in capturing ss"+e.getMessage());
			return null;
		}
	}

}
