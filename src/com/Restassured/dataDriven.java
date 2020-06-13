package com.Restassured;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.util.NumberToTextConverter;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class dataDriven {
	public ArrayList<String> getdata(String testname, String sheetname) throws IOException {
		ArrayList<String> a1 = new ArrayList<String>();
		FileInputStream fis = new FileInputStream("C:\\Cucumber\\Excel\\demodata.xlsx");
		XSSFWorkbook workbook = new XSSFWorkbook(fis);
		int sheets = workbook.getNumberOfSheets();
		System.out.println(sheets);
		for (int i = 0; i < sheets; i++) {

			if (workbook.getSheetName(i).equalsIgnoreCase(sheetname)) {
				XSSFSheet sheet = workbook.getSheetAt(i);

				// Identify Testcases coloum by scanning the entire 1st row

				Iterator<Row> rows = sheet.iterator(); // sheet is collection of rows
				Row firstrow = rows.next();
				Iterator<Cell> ce = firstrow.cellIterator(); // row is collection of cells
				int k = 0;
				int coloumn = 0;
				while (ce.hasNext()) {
					Cell value = ce.next();
					if (value.getStringCellValue().equalsIgnoreCase("Data3")) {

						coloumn = k;
					}

					k++;

				}
				System.out.println(coloumn);

				/*
				 * testcase coloum to identify purcjhase testcase row //after you grab purchase
				 * testcase row = pull all the data of that row and feed into test
				 */
				while (rows.hasNext()) {
					Row r = rows.next();
					if (r.getCell(coloumn).getStringCellValue().equalsIgnoreCase(testname)) {
						Iterator<Cell> cv = r.cellIterator();
						while (cv.hasNext()) {
							Cell c = cv.next();
							if (c.getCellTypeEnum() == CellType.STRING) {

								a1.add(c.getStringCellValue());
							} else {

								a1.add(NumberToTextConverter.toText(c.getNumericCellValue()));

							}

						}

					}
				}

			}
		}
		return a1;

	}
}
