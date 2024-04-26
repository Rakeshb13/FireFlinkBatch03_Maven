package org.automation.generic_library;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class DataUtility implements FrameworkConstant{
	
	public String getDataFromPropertiesFile(String key) throws IOException {
		FileInputStream fis = new FileInputStream(properties_Path);
		Properties pobj = new Properties();
		pobj.load(fis);

		String value = pobj.getProperty(key);
		return value;
	}

	public String getDataFromExcelFile(String sheetName, int rowNum, int cellNum)
			throws EncryptedDocumentException, IOException {

		FileInputStream fis = new FileInputStream(excel_Path);
		Workbook book = WorkbookFactory.create(fis);
		String value = book.getSheet(sheetName).getRow(rowNum).getCell(cellNum).toString();
		return value;
	}

	public static Object[][]  getMultipleDataFromExcelFile(String sheetName) throws EncryptedDocumentException, IOException {
		
		FileInputStream fis = new FileInputStream(excel_Path);
		Workbook book = WorkbookFactory.create(fis);
		Sheet sh = book.getSheet(sheetName);

		int lastRowNum = sh.getPhysicalNumberOfRows();
		int lastCellNum = sh.getRow(0).getPhysicalNumberOfCells();

		Object[][] arr = new Object[lastRowNum - 1][lastCellNum];

		for (int i = 1; i < lastRowNum; i++) {
			for (int j = 0; j < lastCellNum; j++) {
				arr[i - 1][j] = sh.getRow(i).getCell(j).toString();
			}
		}
		return arr;
	}

}
