package pages;

import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.relevantcodes.extentreports.ExtentTest;
import utility.ClickType;


public class OppurtunityPage extends BasePage {
	
	public String quoteNumber = "";
	public OppurtunityPage(WebDriver driver,ExtentTest test) {
		super(driver,test);
	}

	@FindBy(xpath="//table[contains(@class,'uiVirtualDataTable')]/tbody/tr/th")
	private WebElement opportunityHeader;
	@FindBy(xpath="//a[contains(text(),'Show')]")
	private WebElement showAllLink;
	@FindBy(xpath="//a/span[contains(text(),'Oracle CPQ')]")
	private WebElement oracleCPQObjectLink;
	@FindBy(xpath="//span/span[contains(@class,'uiInput forceVirtualCheckbox')]")
	private WebElement checkboxSpan;
	
	public OppurtunityPage openOpportunity(String opportunityName) {
		wait.until(ExpectedConditions.elementToBeClickable(opportunityHeader));
		uiAction.click(opportunityHeader.findElement(By.xpath("//span/a[@title='"+opportunityName+"']")),ClickType.NORAMALCLICK);
		extentLogger.addInfo("Click on "+opportunityName+" link Navigated to Opportunity "+ opportunityName+" Details");
		return this;
	}

	public OppurtunityPage clickOnShowAllObjects() {
		wait.until(ExpectedConditions.visibilityOf(showAllLink));
		wait.until(ExpectedConditions.elementToBeClickable(showAllLink));
		uiAction.click(showAllLink, ClickType.NORAMALCLICK);
		extentLogger.addInfo("Click on Show All to display all Objects.");
		return this;
	}

	public OppurtunityPage clickOnCPQObject() {
		wait.until(ExpectedConditions.visibilityOf(oracleCPQObjectLink));
		wait.until(ExpectedConditions.elementToBeClickable(oracleCPQObjectLink));
		uiAction.click(oracleCPQObjectLink,ClickType.NORAMALCLICK);
		extentLogger.addInfo("Click on Oracle CPQ Object to Open existing CPQ Quotes.");
		return this;
	}
	
	public OppurtunityPage selectQuoteCheckbox(String quoteNumber) {
		wait.until(ExpectedConditions.visibilityOf(checkboxSpan));
		wait.ignoring(NoSuchElementException.class).until(ExpectedConditions.elementToBeClickable(checkboxSpan));
		uiAction.click(driver.findElement(By.xpath("//a[@title='"+quoteNumber+"']/../../preceding-sibling::td[1]/span/span[contains(@class,'uiInput forceVirtualCheckbox')]")), ClickType.NORAMALCLICK);
		extentLogger.addInfo("Selected Quote Number "+quoteNumber+" checkbox to Edit");
		return this;	
	}
	
	@FindBy(xpath="//div[@role='menu']/ul/li/a[@title='Edit']")
	private WebElement editLink;
	public OppurtunityPage clickOnActionIcon(String quoteNumber) {
		driver.findElement(By.xpath("//a[@title='"+quoteNumber+"']/../../following-sibling::td[10]/span/div[@class='forceVirtualActionMarker forceVirtualAction']/a")).click();
		wait.ignoring(NoSuchElementException.class).until(ExpectedConditions.elementToBeClickable(editLink));
		uiAction.click(editLink, ClickType.NORAMALCLICK);
		return this;
	}
	
	//Page Locators
		@FindBy(xpath="//ul[@role='presentation'][contains(@class,'tab-list')]/li/a")
		private List<WebElement> navigationTabList;
		
		@FindBy(css="button[name*='return_to_opportunity']")
		private WebElement returnToOpprutnityButton;
		
		public OppurtunityPage waitForCPQApplicationLandingPage() {
			try {
				wait.ignoring(NoSuchElementException.class).until(ExpectedConditions.visibilityOf(returnToOpprutnityButton));
				wait.ignoring(NoSuchElementException.class).until(ExpectedConditions.elementToBeClickable(returnToOpprutnityButton));
			}catch(Exception ex) {
				extentLogger.addFail(ex.getLocalizedMessage());
				System.out.println(ex.getLocalizedMessage());
			}
			return this;
		}
	@FindBy(css="button[name='copy']")
	private WebElement copyButton;
	public OppurtunityPage clickOnCopyButton() {
		wait.ignoring(NoSuchElementException.class).until(ExpectedConditions.elementToBeClickable(copyButton));
		uiAction.click(copyButton, ClickType.JSCLICK);
		extentLogger.addInfo("Clicked on Copy Button to copy Quote");
		try {
			Thread.sleep(15000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return this;
	}
	
	
	public OppurtunityPage navigateTotabs(String tabsName) {
	    System.out.println(navigationTabList.size()+" Total List");
		try {
			if(navigationTabList.size()==0) {
				throw new Exception();
			}
			for(int i=0;i<navigationTabList.size();i++) {
				if(navigationTabList.get(i).getAttribute("title").equals(tabsName)) {
					System.out.println(navigationTabList.get(i).getAttribute("title"));
					uiAction.click(navigationTabList.get(i),ClickType.JSCLICK);
					extentLogger.addInfo("Navigated to "+ tabsName);
					break;
				}
			}
		}catch(Exception ex) {
			extentLogger.addInfo("failed to navigate to "+tabsName);
			extentLogger.addInfo(ex.getLocalizedMessage());
		}
		return this;
	}
	
	//Page Locators
		@FindBy(css="input[id='quoteDescription_quote']")
		private WebElement quoteNameTextbox;

	

	
	public OppurtunityPage enterQuoteName(String quoteName){
		try{
			String quoteDescription =this.getTimewithTimeStamp(quoteName);
			System.out.println(quoteDescription);
			wait.until(ExpectedConditions.visibilityOf(quoteNameTextbox));
			wait.until(ExpectedConditions.elementToBeClickable(quoteNameTextbox));
			Thread.sleep(4000);
		   quoteNameTextbox.clear();
			//UiAction.enterTextUsingJavaScript(quoteNameTextbox, quoteName);
			uiAction.enterText(quoteNameTextbox, quoteDescription);
			Thread.sleep(5000);
		}catch(Exception ex){
			System.out.println(ex.getLocalizedMessage());
		}
		return this;
	}
	
	  private static String getYear(Date date){
		  SimpleDateFormat simpleDateformat = new SimpleDateFormat("Y"); // the day of the week abbreviated
		     return simpleDateformat.format(date);
	  }
	  
	  
	  private static String getMonth(Date date){
		  SimpleDateFormat simpleDateformat = new SimpleDateFormat("MMM"); // the day of the week abbreviated
		     return simpleDateformat.format(date);
	  }
	  
	  private static String getDate(Date date){
		  SimpleDateFormat simpleDateformat = new SimpleDateFormat("dd"); // the day of the week abbreviated
		     return simpleDateformat.format(date);
	  }
	  
	  private static String getTimeStamp(Date date){
		  SimpleDateFormat simpleDateformat = new SimpleDateFormat("hh:mm:ss"); // the day of the week abbreviated
		     return simpleDateformat.format(date);
	  }
	 
	  private static String getTimeZone(Date date){
		  SimpleDateFormat simpleDateformat = new SimpleDateFormat("z"); // the day of the week abbreviated
		     return simpleDateformat.format(date);
	  }
	  
	  private static String getNameOfDay(Date date){
		   SimpleDateFormat simpleDateformat = new SimpleDateFormat("E"); // the day of the week abbreviated
		     return simpleDateformat.format(date);
	   }

	  public  String getTimewithTimeStamp(String text){
		  Date now = new Date();
		  if(text==null){
			  return getNameOfDay(now)+" "+getMonth(now)+" "+getDate(now)+" "+getYear(now)+" "+getTimeStamp(now)+" "+getTimeZone(now);
		  }
	       return text+" "+getNameOfDay(now)+" "+getMonth(now)+" "+getDate(now)+" "+getYear(now)+" "+getTimeStamp(now)+" "+getTimeZone(now);
	  }
	  
	  
	  
	  @FindBy(name="save_and_price")
		WebElement SaveAndPrice;
	  
	  public OppurtunityPage clickOnSaveAndPriceButton() {
		  uiAction.click(SaveAndPrice, ClickType.NORAMALCLICK);
		  extentLogger.addInfo("Click on Save and Price Button");
		  try {
			Thread.sleep(30000);
			quoteNumber = uiAction.getValueOfElementUsingJS("quoteNumber_quote");
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return this;
	  }
	
	  public OppurtunityPage clickOnReturnToOpportunity() {
		  uiAction.click(returnToOpprutnityButton, ClickType.NORAMALCLICK);
		  try {
				Thread.sleep(30000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		  wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//*[@data-id='Opportunity']/a"))));
			wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("//*[@data-id='Opportunity']/a"))));
		  return this;
	  }
	  
	  @FindBy(xpath="//a[@title='Move Quote']")
	  private WebElement moveQuoteButton;
	  public OppurtunityPage clickOnMoveQuoteButton() {
		  try {
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		  uiAction.click(moveQuoteButton, ClickType.NORAMALCLICK);
		  return this;
	  }
	  
	  @FindBy(xpath="//div[@class='sl-lookup__input']/input[@placeholder='Select a Opportunity']")
	  private WebElement opportunityModelBar;
	  public OppurtunityPage waitforModelOpprtunityModelBox() {
		  wait.ignoring(NoSuchElementException.class).until(ExpectedConditions.visibilityOf(opportunityModelBar));
			wait.ignoring(NoSuchElementException.class).until(ExpectedConditions.elementToBeClickable(opportunityModelBar));
		  return this;
	  }

	public OppurtunityPage enterOpportunityName(String oppName) {
		// TODO Auto-generated method stub
		opportunityModelBar.clear();
		uiAction.enterText(opportunityModelBar, oppName);
		extentLogger.addInfo("Enter Target Opportunity name as "+oppName);
		return this;
	}

	@FindBy(xpath="//button[contains(text(),'Save')]")
	private WebElement moveQuoteSaveButton;
	public OppurtunityPage clickOnSave() {
		// TODO Auto-generated method stub
		uiAction.click(moveQuoteSaveButton, ClickType.NORAMALCLICK);
		extentLogger.addInfo("Click on Save Button to move Quote from Source to Target Opportunity.");
		return this;
	}

	@FindBy(xpath="//ul[@role='listbox']/li/span/div/div")
	List<WebElement> listOfOpportunities;
	public OppurtunityPage selectOpportunityFromList(String option) {
		// TODO Auto-generated method stub
		  try {
				Thread.sleep(15000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		uiAction.selectFromList(listOfOpportunities,option);
		return this;
		
	}
	
	@FindBy(xpath="(//div[contains(text(),'AUTO TEST OPPORTUNITY TARGET')])[2]")
	private WebElement opportunityText;
	public OppurtunityPage selectOpportunity() {
		 try {
				Thread.sleep(15000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		uiAction.click(opportunityText, ClickType.ACTIONCLICK);
		return this;
	}
	
	public String getQuoteNumber() {
		return quoteNumber;
	}
	
	
}
