package api.utilities;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.DataFormat;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class XLUtility {
    private FileInputStream fileInput;
    private FileOutputStream fileOut;
    private XSSFWorkbook workbook;
    private XSSFSheet sheet;
    private XSSFRow row;
    private XSSFCell cell;
    private String path;
    private String data;

    public XLUtility(String path) {
        this.path = path;
    }
    // method for get row couunt
    public int getRowCount(String sheetName){
        int rowCount = 0; 
        try {
            fileInput = new FileInputStream(path);
            workbook = new XSSFWorkbook(fileInput);
            sheet = workbook.getSheet(sheetName);
            rowCount = sheet.getLastRowNum();
            workbook.close();
            fileInput.close();
            

        }catch(FileNotFoundException e) {
            System.out.println("File asking is not found on directory " + path);

        }catch(IOException e){
            System.out.println("Error reading file " + e.getCause());
        }
        return rowCount;
    }
    
    // method for get cell count
    public int getCellCount(String sheetName, int rowNumber){
        int cellCount = 0;
        try {
            fileInput = new FileInputStream(path);
            workbook = new XSSFWorkbook(fileInput);
            sheet = workbook.getSheet(sheetName);
            row = sheet.getRow(rowNumber);
            cellCount = row.getLastCellNum();
            workbook.close();
            fileInput.close();

        } catch (FileNotFoundException e) {
            System.out.println("File asking is not found on directory " + path);

        } catch(IOException e){
            System.out.println("Error reading file " + e.getCause());
        }
        return cellCount;
    }

    // method for get cell data
    public String getCellData(String sheetName, int rowNumber, int column){
        
        try {
            fileInput = new FileInputStream(path);
            workbook = new XSSFWorkbook(fileInput);
            sheet = workbook.getSheet(sheetName);
            row = sheet.getRow(rowNumber);
            cell = row.getCell(column);
            System.out.println(row.getCell(column));
            workbook.close();
            fileInput.close();

            DataFormatter formatter = new DataFormatter();
            data = formatter.formatCellValue(cell);
            workbook.close();
            fileInput.close();
            
        } catch (FileNotFoundException e) {
            System.out.println("File asking is not found on directory " + path);
            
        } catch (IOException e) {
            System.out.println("Error reading file " + e.getCause());

        } catch(RuntimeException e) {
            System.out.println("Runtime exception is occured " + e.getCause());

        } catch(Exception e) {
            System.out.println("Something went wrong " + e.getCause());
        }
        return data;
    }
    

}
