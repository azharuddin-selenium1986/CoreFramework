package commonutils;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.opencsv.CSVReader;

import commonutils.Xls_Reader;

public class TestUtil{
	static Workbook book;
	static Sheet sheet;
	public static String TESTDATA_SHEET_PATH = "C:\\Users\\azhar\\eclipse-workspace\\CoreFramework\\resources\\xls\\Regression Suite.xlsx";
	
	public static Object[][] getTestData(String sheetName) {
		FileInputStream file = null;
		try {
			file = new FileInputStream(TESTDATA_SHEET_PATH);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		try {
			book = WorkbookFactory.create(file);
		} catch (InvalidFormatException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		sheet = book.getSheet(sheetName);
		Object[][] data = new Object[sheet.getLastRowNum()][sheet.getRow(0).getLastCellNum()];
		// System.out.println(sheet.getLastRowNum() + "--------" +
		// sheet.getRow(0).getLastCellNum());
		for (int i = 0; i < sheet.getLastRowNum(); i++) {
			for (int k = 0; k < sheet.getRow(0).getLastCellNum(); k++) {
				data[i][k] = sheet.getRow(i + 1).getCell(k).toString();
				// System.out.println(data[i][k]);
			}
		}
		return data;
	}
	

	// finds if the test suite is runnable
	public static boolean isSuiteRunnable(Xls_Reader xls, String suiteName) {
		boolean isExecutable = false;
		for (int i = 2; i <= xls.getRowCount("Test Suite"); i++) {
			String suite = xls.getCellData("Test Suite", "TSID", i);
			String runmode = xls.getCellData("Test Suite", "Runmode", i);
			System.out.println(suite);
			System.out.println(runmode);
			if (xls.getCellData("Test Suite", "TSID", i).equalsIgnoreCase(suiteName)) {
				if (xls.getCellData("Test Suite", "Runmode", i).equalsIgnoreCase("Y")) {
					isExecutable = true;
				} else {
					isExecutable = false;
				}
			}

		}
		xls = null; // release memory
		return isExecutable;

	}

	// find the test case is runnable or not
	public static boolean isTestCaseRunnable(Xls_Reader xls, String testCaseName) {
		boolean isExecutable = false;
		for (int i = 1; i <= xls.getRowCount("Test Cases"); i++) {
			if (xls.getCellData("Test Cases", "TCID", i).equalsIgnoreCase(testCaseName)) {
				System.out.println("Runmodes " + xls.getCellData("Test Cases", "Runmode", i));
				if (xls.getCellData("Test Cases", "Runmode", i).equalsIgnoreCase("Y")) {
					isExecutable = true;

				}

				else {
					isExecutable = false;
				}
			}
		}
		xls = null;
		return isExecutable;
	}

	

	// update result for a perticular data set
	public static void reportDataSetResult(Xls_Reader xls, String testCaseName, int rowNum, String result) {
		xls.setCellData(testCaseName, "Results", rowNum, result);
	}
	
	// update result for a perticular data set
		public static void reportDataSetResult1(Xls_Reader xls, String testCaseName, int rowNum, String result) {
			xls.setCellData(testCaseName, "PO_NUMBER", rowNum, result);
		}

	// checks RUnmode for dataSet
	public static String[] getDataSetRunmodes(Xls_Reader xlsFile, String sheetName) {
		String[] runmodes = null;
		if (!xlsFile.isSheetExist(sheetName)) {
			xlsFile = null;
			sheetName = null;
			runmodes = new String[1];
			runmodes[0] = "Y";
			xlsFile = null;
			sheetName = null;
			return runmodes;
		}
		runmodes = new String[xlsFile.getRowCount(sheetName) - 1];
		for (int i = 2; i <= runmodes.length + 1; i++) {
			runmodes[i - 2] = xlsFile.getCellData(sheetName, "Runmode", i);
		}
		xlsFile = null;
		sheetName = null;
		return runmodes;
	}

	// return the row num for a test
	public static int getRowNum(Xls_Reader xls, String id) {
		for (int i = 2; i <= xls.getRowCount("Test Cases"); i++) {
			String tcid = xls.getCellData("Test Cases", "TCID", i);

			if (tcid.equals(id)) {
				xls = null;
				return i;
			}

		}

		return -1;
	}
	
	
	//Using HashTable
	@DataProvider
	public static Object[][] getTestdataUsingTable(Xls_Reader xls,String testCaseName){
	    
	    Object[][] data=null;
	    Hashtable<String,String> table =null;
	    
	    //Find number of columns in xls
	    int numofcols=0;
	    while(!xls.getCellData(testCaseName, numofcols, 1).equals("")){
	        
	        numofcols++; 
	    }
	    
	    System.out.println("Number of Columns are : " + numofcols);
	    //Find number of rows in xls
	    int numofrows=0;
	    while(!xls.getCellData(testCaseName, 0, numofrows+2).equals("")){      
	    	//numofrow+2 is given as we need to start from 2nd row.First row is column Heading
	       //System.out.println(xls.getCellData("TestData", 0, numofrows));  
	        numofrows++;
	        
	    }
	    
	    System.out.println("Number of rows are : " + numofrows) ;
	    // initialising data object with number of rows and one column.The data of one row will be put in one column of Hashtable
	    
	    data= new Object[numofrows][1];    //The column will be 1 only as we will put data in hash table.
	        
	    // Putting data in data Object        
	    for(int row=2;row<numofrows+2;row++ ){
	        table=new Hashtable<String,String>();
	        for(int col=0;col<numofcols;col++){
	            
	            table.put(xls.getCellData(testCaseName, col, 1),xls.getCellData(testCaseName, col, row) );
	            
	        }
	        
	        data[row-2][0]= table;
	        
	    }
	    
	    return data;
	}
	
	
	//Starts from 3 row
	@DataProvider
	public static Object[][] getMultipleIterationFrom3rdRow(Xls_Reader xls,String testCaseName){
		 Object[][] data=null;
		    Hashtable<String,String> table =null;
		    
		    //Find number of columns in xls
		    int numofcols=0;
		    while(!xls.getCellData(testCaseName, numofcols, 1).equals("")){
		        
		        numofcols++;
		        
		    }
		    
		    System.out.println("Number of Columns are : " + numofcols);
		    //Find number of rows in xls
		    int numofrows=1;
		    while(!xls.getCellData(testCaseName, 0, numofrows+3).equals("")){      
		    	//numofrow+2 is given as we need to start from 2nd row.First row is column Heading
		       //System.out.println(xls.getCellData("TestData", 0, numofrows));  
		        numofrows++;
		        
		    }
		    
		    System.out.println("Number of rows are : " + numofrows) ;
		    // initialising data object with number of rows and one column.The data of one row will be put in one column of Hashtable
		    
		    data= new Object[numofrows][1];    //The column will be 1 only as we will put data in hash table.
		        
		    // Putting data in data Object        
		    for(int row=3;row<=numofrows+2;row++ ){
		        table=new Hashtable<String,String>();
		        for(int col=0;col<numofcols;col++){
		            
		            table.put(xls.getCellData(testCaseName, col, 1),xls.getCellData(testCaseName, col, row) );
		            
	        }
		        
		        data[row-3][0]= table;
		        
		    }
		    
		    
		    return data;
	}

	//Using Hashmap
	@DataProvider
	public static Object[][] getTestDataUsingMap(Xls_Reader xls,String testCaseName) {

		Object[][] data = null;
		//Xls_Reader xls = new Xls_Reader(excelPath);

		HashMap<String, String> table = null;

		// Find number of columns in xls
		int numofcols = 0;
		while (!xls.getCellData(testCaseName, numofcols, 1).equals("")) {

			numofcols++;

		}

		System.out.println("Number of Columns are : " + numofcols);
		// Find number of rows in xls
		int numofrows = 0;
		while (!xls.getCellData(testCaseName, 0, numofrows + 2).equals("")) {
			// numofrow+2 is given as we need to start from 2nd row.First row is
			// column Heading
			// System.out.println(xls.getCellData("TestData", 0, numofrows));
			numofrows++;

		}

		System.out.println("Number of rows are : " + numofrows);
		// initialising data object with number of rows and one column.The data
		// of one row will be put in one column of Hashtable

		data = new Object[numofrows][1]; // The column will be 1 only as we will
											// put data in hash table.

		// Putting data in data Object
		for (int row = 2; row < numofrows + 2; row++) {
			table = new HashMap<String, String>();
			for (int col = 0; col < numofcols; col++) {
				table.put(xls.getCellData(testCaseName, col, 1), xls.getCellData(testCaseName, col, row));
			}
			data[row - 2][0] = table;
		}

		return data;
	}
	
	// Return the data from a testcase sheet in 2 dimensional array
	//old way of returning data using data provider
		public static Object[][] getData(Xls_Reader xls, String testCaseName) {
			// if the sheet is not present
			if (!xls.isSheetExist(testCaseName)) {
				xls = null;
				return new Object[1][0];
			}

			int rows = xls.getRowCount(testCaseName);
			int cols = xls.getColumnCount(testCaseName);
			System.out.println("Rows are  " + rows);
			System.out.println("Columns are " + cols);

			Object[][] data = new Object[rows - 1][cols - 3];
			for (int rowNum = 2; rowNum <= rows; rowNum++) {
				for (int colNum = 0; colNum < cols - 3; colNum++) {
					System.out.println(xls.getCellData(testCaseName, colNum, rowNum));
					data[rowNum - 2][colNum] = xls.getCellData(testCaseName, colNum, rowNum);
				}
				System.out.println();

			}
			return data;
		}
		
		
		@DataProvider
		public static Object[][] GetCSVdata() throws IOException{
			 
			 
			BufferedReader reader = new BufferedReader(new FileReader("C:\\Users\\azhar\\eclipse-workspace\\CoreFramework\\src\\test\\java\\javaUtilities\\output\\bm_user_1600314487980.csv"));
			List<String> lines = new ArrayList<>();
			String line = null;
			while ((line = reader.readLine()) != null) {
			    lines.add(line);
			}
			
			System.out.println(lines.get(3));
			String listOfColumns = lines.get(2);
			String[] columns = listOfColumns.split(",");
			int totalColumns = columns.length;
			
			int totalRows = lines.size();
			
			
			 Hashtable<String,String> table = null;
			Object[][] data=new Object[totalRows][1];
			table = new Hashtable<String, String>();
			// Putting data in data Object
			for (int row =3; row <totalRows; row++) {
			
				for (int col = 2; col < totalColumns; col++) {
					table.put(columns[col],lines.get(row).split(",")[2]);
				}
				data[row][0] = table;
			}
		       
				return data;
	}
	
		
		@DataProvider(name = "data",indices= {0})
		  public Object[][] dataSupplier() throws IOException {

			BufferedReader reader = new BufferedReader(new FileReader("C:\\Users\\azhar\\eclipse-workspace\\CoreFramework\\src\\test\\java\\javaUtilities\\output\\bm_user_1600314487980.csv"));
			List<String> lines = new ArrayList<>();
			String line = null;
			while ((line = reader.readLine()) != null) {
			    lines.add(line);
			}
			
			System.out.println(lines.get(3));
			String listOfColumns = lines.get(2);
			String[] columns = listOfColumns.split(",");
		    int lastRowNum = lines.size();
		    int lastCellNum = columns.length;
		    Object[][] obj = new Object[lastRowNum][1];

		    for (int i = 3; i < lastRowNum; i++) {
		      Map<Object, Object> datamap = new HashMap<>();
		      for (int j = 0; j < lastCellNum; j++) {
		        datamap.put(columns[2], lines.get(j).split(",")[0]);
		      }
		      obj[i][0] = datamap;

		    }
		    return  obj;
		  }
		
		
		@Test(dataProvider="GetCSVdata")
		public void testcase1(Hashtable<String,String>data) {
			
		}

		
		
	
		/*
		@DataProvider(name = "userDetails")
		public static Object[][] readCsv() throws IOException {
			String path = System.getProperty("user.dir")+"/src/test/java/javaUtilities/output/bm_user_1600314487980.csv";
			
			Reader reader = new FileReader(path);
		
			CSVReader csvreader = new CSVReader(reader);
		    List<String[]> csvData=csvReader.readAll();
		    Object[][] csvDataObject=new Object[csvData.size()][2];
		    for (int i=0;i<csvData.size();i++) {
		        csvDataObject[i]=csvData.get(i);
		    }
		    return  csvDataObject;
		}
*/
	
	    
	    
/*	    public static void main(String[] args) throws IOException {
			
String path = System.getProperty("user.dir")+"/src/test/java/javaUtilities/output/bm_user_1600314487980.csv";
			
			Reader reader = new FileReader(path);
		
			CSVReader csvreader = new CSVReader(reader);
			
			List<String[]> data = csvreader.readAll();
			
			for(String[] d : data){
				
				for(String c : d ){
					
					System.out.println(c);
				}
			}
			
		}
	 */
		
}
