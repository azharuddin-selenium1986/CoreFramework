package utility;
import java.util.List;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class UiActions {
	Actions act ;
	JavascriptExecutor js;
	WebDriver driver;
	public UiActions(WebDriver driver) {
		// TODO Auto-generated constructor stub
		this.driver = driver;
	}
	
	public  void enterText(WebElement element,String text) {
		element.sendKeys(text);
	}
	
	public void click(WebElement element,ClickType type) {
		switch (type) {
		case NORAMALCLICK:
			element.click();
			break;
		case ACTIONCLICK:
			act = new Actions(driver);
			act.moveToElement(element).click().build().perform();
			break;
		case JSCLICK:
			js = ((JavascriptExecutor)driver);
			js.executeScript("arguments[0].click();",element);
			break;

		default:
			element.click();
		}
	}

	public void selectFromList(List<WebElement> list,String options) {
		for(int i =0;i<list.size();i++) {
			if(list.get(i).getText().equals(options)) {
				click(list.get(i), ClickType.NORAMALCLICK);
				break;
			}
		}
		
	}

	public  String getValueOfElementUsingJS(String elementByID) {
		// TODO Auto-generated method stub
		js = ((JavascriptExecutor)driver);
 		Object val = js.executeScript("return document.getElementById('"+elementByID+"').value");
 		return val.toString();
	}

	
	
	
	
}
