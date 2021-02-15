package chrometest;
import org.openqa.selenium.WebDriver;
import org.schneider.tcoe.core.driver.DriverManager;
import org.schneider.tcoe.core.driver.DriverManagerFactory;
import org.schneider.tcoe.core.driver.DriverType;
import org.testng.annotations.AfterClass;
import org.testng.annotations.DataProvider;

import com.relevantcodes.extentreports.ExtentTest;

import extentreport.ExtentManager;
import extentreport.ExtentTestManager;



public class BaseTest {
	
	
	protected DriverManager driverManager;
    protected WebDriver driver;
    protected ExtentTest test;
    public WebDriver getDriver(DriverType type) {
        driverManager= DriverManagerFactory.getDriverManager(type);
        if(driver==null) {
           driver = driverManager.getDriver();
        }
       return driver;
    }

    public void setDriver(WebDriver driver) {
        this.driver = driver;
    }


    public void closeBrowser(){
        driver.close();
    }

    public ExtentTest getExtentTest(String testname,String desc) {
    	test = ExtentTestManager.startTest(testname, desc);
    	return test;
    }

    @AfterClass
    public void tearDown(){
        driver.quit();
		ExtentTestManager.endTest();
        ExtentManager.getReporter().flush();
    }
    
   /* @DataProvider(name = "multipleIter")
	public static Object[][] getData() {
		return TestUtil.getData(resgressionSuiteXls, "ChromeTest");
	}*/

  
}
