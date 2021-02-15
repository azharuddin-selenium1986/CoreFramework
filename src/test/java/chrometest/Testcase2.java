package chrometest;

import commonutils.TestUtil;
import commonutils.Xls_Reader;

public class Testcase2 {
	 public static Xls_Reader resgressionSuiteXls=null;
	
	public static void main(String[] args) {
		 resgressionSuiteXls = new Xls_Reader(System.getProperty("user.dir")+"/resources/xls/Regression Suite.xlsx");
		 
		 TestUtil.getData(resgressionSuiteXls, "ChromeTest");
		 
	}

}
