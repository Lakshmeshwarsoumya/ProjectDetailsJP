package com.joom.automation.generic.fileutility;

import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ExcelUtilityForAdmin {
	public String getDataFromExcel(String sheetName, int rowNum, int colNum) throws EncryptedDocumentException, IOException {
	    FileInputStream fis = new FileInputStream("C:\\Users\\HP\\git\\JoomRepo\\com.joom.automation\\src\\test\\resources\\TestScriptData\\CategoryFields.xlsx");
	    Workbook wb = WorkbookFactory.create(fis);
	    Sheet sh = wb.getSheet(sheetName);
	    Row row = sh.getRow(rowNum);
	    Cell cell = row.getCell(colNum);
	    String value = cell.getStringCellValue();
	    wb.close();
	    return value;
	}

	public int getRowCount(String sheetName) throws EncryptedDocumentException, IOException {
		FileInputStream fis = new FileInputStream(
				"C:\\Users\\HP\\git\\JoomRepo\\com.joom.automation\\src\\test\\resources\\TestScriptData\\CategoryFields.xlsx");
		Workbook wb = WorkbookFactory.create(fis);
		int lastRowNum = wb.getSheet(sheetName).getLastRowNum();
		wb.close();
		return lastRowNum;

	}

}
