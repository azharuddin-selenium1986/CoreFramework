package chrometest;
import org.schneider.tcoe.core.driver.DriverType;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import commonutils.Xls_Reader;
import pages.HomePage;
import pages.LoginPage;
import pages.OppurtunityPage; 



public class ChromeTest extends BaseTest {
	public static Xls_Reader resgressionSuiteXls=null;
	 
	  LoginPage  loginPage;
	  OppurtunityPage opportunityPage;
	    @BeforeTest
	    public void setUp() throws Exception {
	    resgressionSuiteXls = new Xls_Reader(System.getProperty("user.dir")+"/resources/xls/Regression Suite.xlsx");
	    }
	
	
	@Test()
	public void LoginToSalesforceBFO() {
		test = getExtentTest("Login To Salesforce BFO", "FRA Clone and Move Quote in BFO.");
		loginPage = new LoginPage(getDriver(DriverType.CHROME), test);
		HomePage homePage=loginPage.launchApp().enterUsername("seleniumfra03salesrep@bridge-fo.com.uatbfo19").enterPassword("bFO!SCH123")
				.clickOnLoginButton();
		homePage.waitForElement("//*[@data-id='Opportunity']/a");
		opportunityPage =homePage.navigateToOpportunity().openOpportunity("AUTO TEST OPPORTUNITY SOURCE");
		opportunityPage.clickOnShowAllObjects().clickOnCPQObject();
		opportunityPage.selectQuoteCheckbox("2020-49240").clickOnActionIcon("2020-49240").waitForCPQApplicationLandingPage();
		opportunityPage.clickOnCopyButton().waitForCPQApplicationLandingPage().navigateTotabs("Opportunity and Quote Info");
		opportunityPage.enterQuoteName("Test Auto Cloned Quote");
		opportunityPage.clickOnSaveAndPriceButton();
		
		opportunityPage.clickOnReturnToOpportunity().clickOnShowAllObjects().clickOnCPQObject()
		.selectQuoteCheckbox(opportunityPage.getQuoteNumber()).clickOnMoveQuoteButton()
		.waitforModelOpprtunityModelBox().enterOpportunityName("AUTO TEST OPPORTUNITY TARGET").selectOpportunity().clickOnSave().selectQuoteCheckbox(opportunityPage.quoteNumber);
	
		opportunityPage.waitforModelOpprtunityModelBox().enterOpportunityName("AUTO TEST OPPORTUNITY TARGET").selectOpportunity().clickOnSave().selectQuoteCheckbox(opportunityPage.quoteNumber);
		try {
			Thread.sleep(60000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		/*opportunityPage=homePage.navigateToOpportunity().openOpportunity("AUTO TEST OPPORTUNITY SOURCE").clickOnShowAllObjects()
				.clickOnCPQObject().selectQuoteCheckbox("2020-49240").clickOnActionIcon("2020-49240")
				.waitForCPQApplicationLandingPage().clickOnCopyButton().waitForCPQApplicationLandingPage()
				.navigateTotabs("Opportunity and Quote Info").enterQuoteName("Test Auto Cloned Quote").clickOnSaveAndPriceButton();
		
		opportunityPage.clickOnReturnToOpportunity().clickOnShowAllObjects().clickOnCPQObject()
				.selectQuoteCheckbox(opportunityPage.getQuoteNumber()).clickOnMoveQuoteButton()
				.waitforModelOpprtunityModelBox().enterOpportunityName("AUTO TEST OPPORTUNITY TARGET").selectOpportunity().clickOnSave().selectQuoteCheckbox(opportunityPage.quoteNumber);
		try {
			Thread.sleep(60000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
*/
	}
	
	
	
	@Test(enabled=false)
	public void MoveQuote() {
		test = getExtentTest("Login To Salesforce BFO", "MoveQuote");
		loginPage = new LoginPage(getDriver(DriverType.CHROME), test);
		HomePage homePage=loginPage.launchApp().enterUsername("seleniumfra03salesrep@bridge-fo.com.uatbfo19").enterPassword("bFO!SCH123")
				.clickOnLoginButton();
		homePage.navigateToOpportunity().openOpportunity("AUTO TEST OPPORTUNITY SOURCE").clickOnShowAllObjects()
				.clickOnCPQObject()
				.selectQuoteCheckbox("2020-49476").clickOnMoveQuoteButton()
				.waitforModelOpprtunityModelBox().enterOpportunityName("AUTO TEST OPPORTUNITY TARGET");
		try {
			Thread.sleep(60000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	@Test(enabled=false)
	public void returnToOpp() {
		test = getExtentTest("Login To Salesforce BFO", "Return to Opportunity");
		loginPage = new LoginPage(getDriver(DriverType.CHROME), test);
		HomePage homePage=loginPage.launchApp().enterUsername("seleniumfra03salesrep@bridge-fo.com.uatbfo19").enterPassword("bFO!SCH123")
				.clickOnLoginButton();
		homePage.navigateToOpportunity().openOpportunity("AUTO TEST OPPORTUNITY SOURCE").clickOnShowAllObjects()
				.clickOnCPQObject().selectQuoteCheckbox("2020-49476").clickOnActionIcon("2020-49476")
				.waitForCPQApplicationLandingPage()
				.navigateTotabs("Opportunity and Quote Info").enterQuoteName("Test Auto Cloned Quote")
				.clickOnSaveAndPriceButton().clickOnReturnToOpportunity().clickOnShowAllObjects().clickOnCPQObject()
				.selectQuoteCheckbox("2020-49476").clickOnMoveQuoteButton()
				.waitforModelOpprtunityModelBox().enterOpportunityName("AUTO TEST OPPORTUNITY TARGET").selectOpportunity().clickOnSave().selectQuoteCheckbox("2020-49476");
		try {
			Thread.sleep(60000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	/*@Test(dataProvider="getData1")
	public void testcase2(Hashtable<String, String>data) {
		System.out.println(data.get("login"));
	}
	 
	@DataProvider
	public Object[][] getData1() throws IOException{
		Object data[][] = TestUtil.GetCSVdata();
		return data;
	}*/

}
