package com.stock.details;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.stock.details.domain.stockDetails;

public class CSVUtility {
	  protected Logger logger = LoggerFactory.getLogger(getClass());
	//Delimiter used in CSV file

	    private static final String COMMA_DELIMITER = ",";

	    private static final String NEW_LINE_SEPARATOR = "\n";
	    //CSV file header
	    private static final String FILE_HEADER = "id,firstName,lastName,gender,age";
	    
	    public static FileWriter createFileHeader(String fileName) throws StockException{
	    	 //Create new students objects
	        FileWriter fileWriter = null;
	        try {
	            fileWriter = new FileWriter(fileName);
	            //Write the CSV file header
	            fileWriter.append(FILE_HEADER.toString());
	            //Add a new line separator after the header
	            fileWriter.append(NEW_LINE_SEPARATOR);
	            
	        }catch(Exception e){
	        	
	        	throw new StockException("Exception in csv file header ");
	        }
	       return fileWriter; 
	    }
	    
	    public static void writeCsvFile(FileWriter fileWriter,List<stockDetails>  stockDetails) {
	    
	        try {
	        	Iterator stockD = stockDetails.iterator();
	            while(stockD.hasNext()) {
	            	stockDetails element =(stockDetails) stockD.next();
	            	 fileWriter.append(element.getStockSymbol());
	            	 fileWriter.append(element.getCurrentPrice());
	            	 fileWriter.append(element.getYearTargetPrice());
	            	 fileWriter.append(element.getYearHigh());
	            	 fileWriter.append(element.getYearLow());
	            	 fileWriter.append(COMMA_DELIMITER);
		              
	            }
	           
	           
	           
	        } catch (Exception e) {
	           
	            e.printStackTrace();
	        } finally {
	             
	            try {
	                fileWriter.flush();
	                fileWriter.close();
	            } catch (IOException e) {
	                System.out.println("Error while flushing/closing fileWriter !!!");
	
	                e.printStackTrace();
	            }
	        }
	    }

}
