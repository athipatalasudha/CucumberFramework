package com.salesforce.test.runner;
import org.testng.annotations.AfterTest;
import com.salesforce.test.stepdefinitions.LoginStepDefiniton;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(plugin = { "pretty", "html:target/cucumber-reports/cucumber.html",
"json:target/cucumber-reports/cucumber.json" }, 
features = {"src/test/java/com/salesforce/test/features/login.feature" }, 
glue = { "com.salesforce.test.stepdefinitions" })

public class Runner extends AbstractTestNGCucumberTests {

}