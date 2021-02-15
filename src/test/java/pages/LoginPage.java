package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import utility.ClickType;
import utility.UiActions;

public class LoginPage extends BasePage {
	
	
	public LoginPage(WebDriver driver,ExtentTest test) {
		super(driver,test);
	}

	@FindBy(id="username")
	private WebElement username;
	
	@FindBy(id="password")
	private WebElement password;
	
	@FindBy(id="Login")
	private WebElement loginButton;
	

	
	
	public LoginPage launchApp() {
		launchApplication();
		return this;
	}
	
	public LoginPage enterUsername(String user) {
		uiAction.enterText(username, user);
		extentLogger.addInfo("Enter Username as "+user);
		return this;
	}
	
	public LoginPage enterPassword(String pass) {
		uiAction.enterText(password,pass);
		extentLogger.addInfo("Enter Password as "+pass);
		return this;
	}
	
	public HomePage clickOnLoginButton() {
		uiAction.click(loginButton, ClickType.NORAMALCLICK);
		extentLogger.addInfo("Click on Login Button.");
		wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//*[@data-id='Opportunity']/a"))));
		wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("//*[@data-id='Opportunity']/a"))));
		return new HomePage(driver,test);
	}
	
	
	
	
	
}
