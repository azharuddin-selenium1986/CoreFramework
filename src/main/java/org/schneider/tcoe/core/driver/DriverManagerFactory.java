package org.schneider.tcoe.core.driver;

public class DriverManagerFactory {
	
	public static DriverManager getDriverManager(DriverType type) {
		DriverManager driverManager = null;
		
		switch (type) {
		case CHROME:
			driverManager = new ChromeDriverManager();
			break;

		default:
			break;
		}
		return driverManager;
		
		
	}

}
