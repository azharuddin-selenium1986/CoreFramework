package pages;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import com.relevantcodes.extentreports.ExtentTest;
import utility.ClickType;


public class HomePage extends BasePage {
	
	
	public HomePage(WebDriver driver,ExtentTest test) {
		super(driver,test);
	}

	
	@FindBy(xpath="//*[@data-id='Opportunity']/a")
	private WebElement opportunityTab;
	
	public OppurtunityPage navigateToOpportunity() {
		try {
			JavascriptExecutor executor = (JavascriptExecutor)driver;
			executor.executeScript("arguments[0].click();", opportunityTab);
		//	driver.navigate().to("https://se--uatbfo19.lightning.force.com/lightning/o/Opportunity/home");
			extentLogger.addInfo("Click on Opportunities Tab to navigate Opportunities");
		}catch(Exception ex) {
			extentLogger.addFail(ex.getLocalizedMessage());
		}
	
		return new OppurtunityPage(driver, test) ;
	}

	
	
}
