package com.salesforce.test.stepdefinitions;

import java.io.File;
import java.time.Duration;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;

import com.salesforce.test.pages.home.HomePage;
import com.salesforce.test.pages.login.LoginPage;
import com.salesforce.test.pages.login.LogoutPage;
import com.salesforce.test.utility.CommonUtilities;
import com.salesforce.test.basetest.*;
import com.salesforce.test.pages.base.*;
import com.salesforce.test.pages.forgotpassword.ForgotPasswordpage;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.*;
import io.github.bonigarcia.wdm.WebDriverManager;

public class LoginStepDefiniton extends BaseTest1{
	WebDriver driver;
	LoginPage lp;
	HomePage hp;
	LogoutPage lgp;
	ForgotPasswordpage fp;
	CommonUtilities CU = new CommonUtilities();
	Properties applicationPropertiesFile = CU.loadFile("data");
	String usrname = CU.getApplicationProperty("myusername", applicationPropertiesFile);
	String pwd=CU.getApplicationProperty("mypswd", applicationPropertiesFile);
	String invalidusrname = CU.getApplicationProperty("invalidusername", applicationPropertiesFile);
	String invalidpwd=CU.getApplicationProperty("invalidpswd", applicationPropertiesFile);
	String url = CU.getApplicationProperty("myurl", applicationPropertiesFile);
	String email = CU.getApplicationProperty("myemail", applicationPropertiesFile);
	
	@Before(order=0)
	public void setUp() {
		WebDriverManager.chromedriver().setup();
		driver=new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(30,TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.get(url);
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30,TimeUnit.SECONDS);
		//lp=new LoginPage(driver);
	}

	@When("User enters {string} in username field")
		public void user_enters_in_username_field(String string) {
		    lp.enterUserName(string);
		}

	@When("User enters {string} in password field")
	public void user_enters_in_password_field(String string) {
	   lp.enterPassword(string);
	}
	@When("User enters no password in password field")
	public void user_enters_no_password_in_password_field() {
		 lp.clearPassword();
	}


	@When("clicks on login button")
	public void clicks_on_login_button() {
	    lp.clickLoginButton();
	    System.out.println("login button clicked");
	    driver.manage().timeouts().implicitlyWait(40,TimeUnit.SECONDS);
	    
	}

	@Then("error message {string} is displayed")
	public void error_message_is_displayed(String string) {
	   String exp=string;
	   String act=lp.getErrorMsg();
	   Assert.assertEquals(act, exp);
	}
	
	@Given("User on {string}")
	public void user_on(String page) {
		driver.manage().timeouts().implicitlyWait(40,TimeUnit.SECONDS);
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(30));
		if(page.equalsIgnoreCase("loginpage"))
	    	lp=new LoginPage(driver);
	    else if(page.equalsIgnoreCase("homepage"))
	    	hp=new HomePage(driver);
	    else if(page.equalsIgnoreCase("logoutpage"))
	    	lgp=new LogoutPage(driver);
	    else if(page.equalsIgnoreCase("forgotpasswordpage"))
	    	fp=new ForgotPasswordpage(driver);
		driver.manage().timeouts().implicitlyWait(40,TimeUnit.SECONDS);
	}
	
	@Then("HomePage is displayed")
	public void home_page_is_displayed() {
		String exp="Home Page ~ Salesforce - Developer Edition";
		driver.manage().timeouts().implicitlyWait(40,TimeUnit.SECONDS);
		String act=hp.getTitle();
		Assert.assertEquals(act, exp);
	}
	
	@When("User clicks on RememberMe checkbox")
	public void user_clicks_on_remember_me_checkbox() {
		lp.checkRememberMe();
	}

	@Then("User logsout of the Salesforce")
	public void user_logsout_of_the_salesforce() {
	   hp.logout();
	}

	@Then("Username is displayed in the username field")
	public void username_is_displayed_in_the_username_field() {
		String exp="august22-qa@abc.com";
		driver.manage().timeouts().implicitlyWait(40,TimeUnit.SECONDS);
		String act=lgp.getTextUsername();
		Assert.assertEquals(act, exp);
	}
	

	@When("User clicks ForgotPassword link")
	public void user_clicks_forgot_password_link() {
		lp.forgotPassword();
		driver.manage().timeouts().implicitlyWait(40,TimeUnit.SECONDS);
	}

	@When("User clicks Continue button")
	public void user_clicks_continue_button() {
		fp.clickContinue();
		driver.manage().timeouts().implicitlyWait(40,TimeUnit.SECONDS);
	}

	@Then("Password reset message is displayed")
	public void password_reset_message_is_displayed() {
		boolean res=fp.verifyText();
		Assert.assertTrue(res);
	}

	@When("User enters {string} in email field")
	public void user_enters_in_email_field(String string) {
		fp.enterUserName(string);
	}
	
	@After
	public void tearDown() {
		driver.quit();
	}


}
