package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import utility.ClickType;

public class LoginPage2 extends BasePage {
	
	
	public LoginPage2(WebDriver driver,ExtentTest test) {
		super(driver,test);
	}

	@FindBy(name="q")
	private WebElement searchTextbox;
	
	@FindBy(name="btnK")
	private WebElement searchButton;
	
	public void enterText(String text) {
		driver.get("https://google.com");
		uiAction.enterText(searchTextbox, text);
		extentLogger.addInfo("Enter text as "+text);
		wait.until(ExpectedConditions.visibilityOf(searchButton));
		uiAction.click(searchButton,ClickType.JSCLICK);
		extentLogger.addInfo("Click on Search Button");
		driver.navigate().back();
	}

}
