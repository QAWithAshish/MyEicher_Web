package tests;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import browserFactory.DriverInit;
import constants.FileConstant;

public class SuiteSetup {
	
	
    @BeforeMethod
    public void launchBrowser(){
        DriverInit.getDriver("chrome").get(FileConstant.getEnvironmentKey("URL"));

    }
    @AfterMethod
    public void tearDownBrowser(){
        DriverInit.tearDown();

    }

    public static WebDriver getDriver(){
        return  DriverInit.getDriver(DriverInit.browserType);
    }

}
