package stepDefinitions;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;



public class AddressBook         //DO NOT change the class name

{



    //Location of Test data excel file

    public static String filePath = System.getProperty("user.dir")+"/AddressBook.xlsx";

    public static XSSFWorkbook workbook;

    public static XSSFSheet spsheet;

    public static FileInputStream fis;

    // public static XSSFSheet sheet;

    public static File file;

    //  public static FileInputStream fis;

    public static FileOutputStream fos;



    // public static XSSFWorkbook wb;

    public static Row row;

    public static Cell cell;

    public  String[][] excelDataGrid;



    public static String getExcelPath(String firstSheetName)  throws IOException

    {

        //get the file path of the excel sheet, store it in the variable 'filePath' and return it
System.out.println(firstSheetName);
System.out.println(firstSheetName);
        fis = new FileInputStream(filePath);

        workbook = new XSSFWorkbook(fis);

        spsheet= workbook.getSheet(firstSheetName);

        System.out.println("Sheet name is "+firstSheetName);

        return filePath;

    }



    public static Object[][] readExcelData(String firstSheetName) throws Exception

    {



        //read the data from excel sheet and store it in 2-D array. Return the array

        int rows= spsheet.getPhysicalNumberOfRows();

        int columns=spsheet.getRow(0).getPhysicalNumberOfCells();

        String[][] excelDataGrid = new String[rows][columns];





        for (int i=0; i<rows; i++) {

            XSSFRow row = spsheet.getRow(i);

            for (int j=0; j<columns; j++) {

                XSSFCell cell = row.getCell(j);

                DataFormatter formatter = new DataFormatter();

                excelDataGrid[i][j] = formatter.formatCellValue(cell);





            }



        }

        return excelDataGrid;





    }



    public static void writeExcelData(String firstSheetName, String[][] result) throws Exception

    {



        //Write the data from 'result' in the new sheet 'customervalid1'

        fis= new FileInputStream(filePath);

        workbook= new XSSFWorkbook(fis);

        spsheet= workbook.getSheet(firstSheetName);

        System.out.println("fpath " +filePath);

        int sheetcount=workbook.getNumberOfSheets();

        System.out.println("sheetcount write" + sheetcount);

        if(spsheet==null) {

            spsheet = workbook.createSheet(firstSheetName);

        }

        sheetcount=workbook.getNumberOfSheets();

        System.out.println("Sht created write" + sheetcount);

        row= spsheet.createRow(0);

        row= spsheet.createRow(1);

        row= spsheet.createRow(2);

        row= spsheet.createRow(3);

        row= spsheet.createRow(4);

        row= spsheet.createRow(5);

        cell= row.createCell(0);

        cell= row.createCell(1);

        System.out.println("second sheet name is "+ firstSheetName);

        spsheet.getRow(0).createCell(0).setCellValue(result[0][0]);

        spsheet.getRow(0).createCell(1).setCellValue(result[0][1]);

        spsheet.getRow(1).createCell(0).setCellValue(result[1][0]);

        spsheet.getRow(1).createCell(1).setCellValue(result[1][1]);

        spsheet.getRow(2).createCell(0).setCellValue(result[2][0]);

        spsheet.getRow(2).createCell(1).setCellValue(result[2][1]);

        spsheet.getRow(3).createCell(0).setCellValue(result[3][0]);

        spsheet.getRow(3).createCell(1).setCellValue(result[3][1]);

        spsheet.getRow(4).createCell(0).setCellValue(result[4][0]);

        spsheet.getRow(4).createCell(1).setCellValue(result[4][1]);

        spsheet.getRow(5).createCell(0).setCellValue(result[5][0]);

        spsheet.getRow(5).createCell(1).setCellValue(result[5][1]);

        fos= new FileOutputStream(filePath);

        workbook.write(fos);

        fos.close();

    }



}