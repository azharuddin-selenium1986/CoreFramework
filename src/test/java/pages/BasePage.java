package pages;

import java.time.Duration; 
import java.util.function.Function;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import com.relevantcodes.extentreports.ExtentTest;
import utility.ExtentLogger;
import utility.UiActions;

public class BasePage {
	
	
	protected WebDriver driver ;
	protected ExtentTest test;
	protected UiActions uiAction;
	protected WebDriverWait  wait ;
	protected ExtentLogger extentLogger;
	public static final String APP_URL ="https://test.salesforce.com/";
	
	public BasePage(WebDriver driver,ExtentTest test) {
		this.driver = driver;
		this.wait = new WebDriverWait(driver, 120);
		PageFactory.initElements(driver, this);
		this.uiAction = new UiActions(driver);
		this.test = test;
		extentLogger = new ExtentLogger(test);
	}
	
	
	public void launchApplication() {
		driver.get(APP_URL);
		extentLogger.addInfo("User navigated to Application : "+APP_URL);
	}
	
	public WebElement waitForElement(String locator) {
	  Wait<WebDriver> wait = new FluentWait<WebDriver>(driver)
			  .withTimeout(Duration.ofSeconds(20)).pollingEvery(Duration.ofMillis(500)).ignoring(JavascriptException.class)
			  .ignoring(NoSuchElementException.class)
			  .ignoring(StaleElementReferenceException.class);
	  WebElement element = wait.until(new Function<WebDriver, WebElement>() {
		  public WebElement apply(WebDriver driver) {
			  return driver.findElement(By.xpath(locator));
		  }
	});
	return element;
	}
	

}
