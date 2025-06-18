package com.etvwin.utility;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.HashMap;
import java.util.Map;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtility {
    
    private XSSFSheet excelSheet;
    private XSSFWorkbook excelWorkbook;
    private Cell cell;
    private XSSFRow row;
    
    public void setExcelFile(String sheetPath, String sheetName) throws Exception {
        try {
            Log.info("Taking sheets from the workbook.");
            FileInputStream excelFile = new FileInputStream(sheetPath);
            excelWorkbook = new XSSFWorkbook("excelFile");  
            excelSheet = excelWorkbook.getSheet(sheetName);
        } catch (Exception exp) {
            Log.error("Exception occurred in setExcelFile: " + exp.getMessage());
            throw (exp);
        }
    }

    private int getTestCaseRow(String testCaseName, int testCaseColumn) throws Exception {
        int row;
        try {
            int rowCount = excelSheet.getLastRowNum();
            for (row = 0; row < rowCount; row++) {
                if (getCellData(row, testCaseColumn).equalsIgnoreCase(testCaseName)) {
                    break;
                }
            }
        } catch (Exception exp) {
            Log.error("Exception occurred in getTestCaseRow: " + exp.getMessage());
            throw (exp);
        }

        return row;
    }

    public String getCellData(int rowNumb, int colNumb) throws Exception {
        try {
            cell = excelSheet.getRow(rowNumb).getCell(colNumb);
            if (cell.getCellType() == CellType.NUMERIC) { 
                cell.setCellType(CellType.STRING);  
            }
            String cellData = cell.getStringCellValue();
            return cellData;
        } catch (Exception exp) {
            return "";
        }
    }

    public void setCellData(String result, int rowNumb, int colNumb, String excelFilePath) throws Exception {
        try {
            row = excelSheet.getRow(rowNumb);
            cell = row.getCell(colNumb);
            Log.info("Setting results into the excel sheet.");
            if (cell == null) {
                cell = row.createCell(colNumb);
                cell.setCellValue(result);
            } else {
                cell.setCellValue(result);
            }

            Log.info("Creating file output stream.");
            FileOutputStream fileOut = new FileOutputStream(excelFilePath);
            excelWorkbook.write(fileOut);
            fileOut.flush();
            fileOut.close();

        } catch (Exception exp) {
            Log.error("Exception occurred in setCellData: " + exp.getMessage());
            throw (exp);
        }
    }
    public Map<String, String> getData(String testCaseName, String sheetPath, String sheetName) {
        Map<String, String> dataMap = new HashMap<String, String>();
        try {
            setExcelFile(sheetPath, sheetName);
            int dataRow = getTestCaseRow(testCaseName.trim(), 0);
            int columnCount = excelSheet.getRow(dataRow).getLastCellNum();
            for (int i = 0; i < columnCount; i++) {
                String cellData = null;
                cell = excelSheet.getRow(dataRow).getCell(i);
                if (cell != null && cell.getCellType() == CellType.NUMERIC) {  
                    cell.setCellType(CellType.STRING);  
                }                          
                if (cell != null) {
                    cellData = cell.getStringCellValue();
                }
                dataMap.put(excelSheet.getRow(0).getCell(i).getStringCellValue(), cellData);
            }
        } catch (Exception e) {
            System.out.println("Exception occurred while adding data to Map:\n");
            e.printStackTrace();
        }
        return dataMap;
    }
}