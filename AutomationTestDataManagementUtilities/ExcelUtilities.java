import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Dictionary;
import java.util.Hashtable;
import java.util.Iterator;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;


public class ExcelUtilities {

	public   Dictionary <String,String> GetTestDataMapFromExcel(String strFilePath, String strSheetName, String strDataRowName) throws IOException
	{
		Dictionary<String, String> oDict = new Hashtable<String, String>();
		Dictionary<Integer, String> oDictColumnNames = new Hashtable<Integer, String>();
		
        FileInputStream oFile = new FileInputStream(new File(strFilePath));
        HSSFWorkbook oworkbook = new HSSFWorkbook(oFile);
        HSSFSheet osheet = oworkbook.getSheet( strSheetName );
        Iterator<Row> rowIterator = osheet.iterator();
        boolean isHeader = true;
        
        while (rowIterator.hasNext())
        {
        	Row row = rowIterator.next();     
        	// Take First Row as Column Names and Add to Dictionary
        	
        	if ( isHeader == true)
        	{
                Iterator<Cell> cellIterator = row.cellIterator();
                int columnIterator = 0; 
        		  while (cellIterator.hasNext())
                  {
        			  columnIterator++;
        			  Cell cell = cellIterator.next();
        			  oDict.put( cell.getStringCellValue()   ,  "");
        			  oDictColumnNames.put(    columnIterator  , cell.getStringCellValue());
                  }
        	  isHeader = false;
        	}
        	else
        	{
        		
                if ( row.getCell( 0 ).toString().equals(strDataRowName) )
                {	
                	   Iterator<Cell> cellIterator = row.cellIterator();
                       int columnIterator = 0; 
           		  	   while (cellIterator.hasNext())
                         {
               			  columnIterator++;
               			  Cell cell = cellIterator.next();   
               			  String strCellValue = "";
               			  switch (cell.getCellType()) 
                          {
                          case Cell.CELL_TYPE_STRING:

                                        strCellValue =cell.getStringCellValue();
                                        break;
         
                          case Cell.CELL_TYPE_BOOLEAN:

                                        boolean bValue= cell.getBooleanCellValue();
                                        strCellValue=Boolean.toString(bValue);
                                        break;

                          case Cell.CELL_TYPE_NUMERIC:

                                        int iValue=(int) cell.getNumericCellValue();
                                        strCellValue=Integer.toString(iValue);
                                        break;
                                        
                          }
               			  oDict.put(  oDictColumnNames.get( (cell.getColumnIndex() + 1) )  ,  strCellValue);               			  
                         }
               		break;  
                }
                
        	}
        }
        
        oFile.close();
        oFile = null;
        oDictColumnNames = null;
        return oDict;
	}
	
	public void InsertDataIntoExcel(String exlFilePath, String exlSheetName, String exlRowName, String exlColumnName, String outputValue) throws IOException
	{
		Dictionary<String, String> oBook = new Hashtable<String, String>();
		Dictionary<String, Integer> oBookColumnNames = new Hashtable<String, Integer>();
		
		
		
        FileInputStream oFile = new FileInputStream(new File(exlFilePath));
        HSSFWorkbook oworkbook = new HSSFWorkbook(oFile);
        HSSFSheet osheet = oworkbook.getSheet(exlSheetName);
        Iterator<Row> rowIterator = osheet.iterator();
        boolean isHeader = true;
        
        while (rowIterator.hasNext())
        {
        	Row row = rowIterator.next();     
        	// Take First Row as Column Names and Add to Dictionary
        	
        	if ( isHeader == true)
        	{
                Iterator<Cell> cellIterator = row.cellIterator();
                int columnIterator = 0; 
        		  while (cellIterator.hasNext())
                  {
        			  columnIterator++;
        			  Cell cell = cellIterator.next();
        			  oBook.put( cell.getStringCellValue()   ,  "");
        			  oBookColumnNames.put(cell.getStringCellValue() , columnIterator);
                  }
        	  isHeader = false;
        	}
        	else
        	{
        		
                if ( row.getCell( 0 ).toString().equals(exlRowName) )
                {	
                	   Iterator<Cell> cellIterator = row.cellIterator();
                       int columnIterator; 
                       int indexOfColumn = oBookColumnNames.get(exlColumnName);
            		   columnIterator= indexOfColumn-1;
            			
            		   Cell c = row.createCell(columnIterator);
            		   c.setCellValue(outputValue);
               		
            		   break;  
                }
                
        	}
        
       }
        FileOutputStream fos= new FileOutputStream(exlFilePath);
        oworkbook.write(fos);
        fos.close();
        oFile.close();
	}
}
