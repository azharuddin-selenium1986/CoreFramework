package javaUtilities;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.testng.annotations.DataProvider;

import com.opencsv.CSVReader;

import commonutils.Xls_Reader;


public class CSV_READER {
	
	enum BookHeaders {
		Users,login
	}
	
	
	public static void main(String[] args) throws IOException  
	{  
		CSV_READER.GetCSVdata();
		
	}  
	
	
	public static Object[][] GetCSVdata() throws IOException{
		 Object[][] data=null;
		  Hashtable<String,String> table =null;
		BufferedReader reader = new BufferedReader(new FileReader("C:\\Users\\azhar\\eclipse-workspace\\CoreFramework\\src\\test\\java\\javaUtilities\\output\\bm_user_1600314487980.csv"));
		List<String> lines = new ArrayList<>();
		String line = null;
		while ((line = reader.readLine()) != null) {
		    lines.add(line);
		}

		String listOfColumns = lines.get(2);
		String[] columns = listOfColumns.split(",");
		
		data= new Object[lines.size()][1];
		for(int j=0;j<lines.size();j++) {
			for(int i=0;i<columns.length;i++) {
				table.put(columns[i],lines.get(j));
			}
		}   
	        data[lines.size()][0]= table;
			return data;
}
	
	
	/*@DataProvider
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
	}*/
	  
	
	/*
	public static void main(String[] args) throws IOException {
		//Reader in = new FileReader("C:\\Users\\azhar\\eclipse-workspace\\CoreFramework\\src\\test\\java\\javaUtilities\\output\\bm_user_1600314487980.csv");
	//	readDataLineByLine("C:\\Users\\azhar\\eclipse-workspace\\CoreFramework\\src\\test\\java\\javaUtilities\\output\\bm_user_1600314487980.csv");
		
		
		BufferedReader csvReader = new BufferedReader(new FileReader("C:\\Users\\azhar\\eclipse-workspace\\CoreFramework\\src\\test\\java\\javaUtilities\\output\\bm_user_1600314487980.csv"));
		System.out.println(csvReader.readLine().length());
		String row;
		while ((row = csvReader.readLine()) != null) {
		   
		}
		csvReader.close();
	}*/
	
	
	public static void readDataLineByLine(String file) throws IOException 
	{ 
		CSVReader csvReader = null;
	    try { 
	  
	        // Create an object of filereader 
	        // class with CSV file as a parameter. 
	        FileReader filereader = new FileReader(file); 
	  
	        // create csvReader object passing 
	        // file reader as a parameter 
	        csvReader = new CSVReader(filereader); 
	        String[] nextRecord; 
	  
	        // we are going to read data line by line 
	        while ((nextRecord = csvReader.readNext()) != null) { 
	            for (String cell : nextRecord) { 
	                System.out.print(cell + "\t"); 
	            } 
	            System.out.println(); 
	        } 
	    } 
	    catch (Exception e) { 
	        e.printStackTrace(); 
	    } 
	    finally {
	    	csvReader.close();
		}
	} 


}


