package com.jiuqi.jnds05.common.bean;

/**
 * Excel Reader Tools
 * 
 * @author likeshuang
 * 
 */
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelReader {
	public static List<ImportEnity> readExcel(InputStream inputStream)
			throws IOException {
		Workbook workbook = new XSSFWorkbook(inputStream);
		List<ImportEnity> resultDataList = parseExcel(workbook);
		return resultDataList;
	}

	private static List<ImportEnity> parseExcel(Workbook workbook) {
		List<ImportEnity> resultDataList = new ArrayList<ImportEnity>();
		for (int sheetNum = 0; sheetNum < workbook.getNumberOfSheets(); sheetNum++) {
			Sheet sheet = workbook.getSheetAt(sheetNum);
			int firstRowNum = sheet.getFirstRowNum();
			int rowStart = firstRowNum + 1;
			int rowEnd = sheet.getPhysicalNumberOfRows();
			for (int rowNum = rowStart; rowNum < rowEnd; rowNum++) {
				Row row = sheet.getRow(rowNum);
				ImportEnity resultData = convertRowToData(row);
				resultDataList.add(resultData);
			}
		}
		return resultDataList;
	}

	private static ImportEnity convertRowToData(Row row) {
		ImportEnity resultData = new ImportEnity();
		int cellNum = 0;
		resultData.setCredentialId(row.getCell(cellNum++).getStringCellValue());
		resultData.setCredentialNum(new Integer((int) row.getCell(cellNum++)
				.getNumericCellValue()));
		resultData.setYear(new Integer((int) row.getCell(cellNum++)
				.getNumericCellValue()));
		resultData.setPeriod(new Integer((int) row.getCell(cellNum++)
				.getNumericCellValue()));
		resultData.setUnitCode(row.getCell(cellNum++).getStringCellValue());
		resultData.setUnitName(row.getCell(cellNum++).getStringCellValue());
		resultData.setCredentialDate(row.getCell(cellNum++).getDateCellValue());
		resultData.setDebitAmount(row.getCell(cellNum++).getNumericCellValue());
		resultData
				.setLenderAmount(row.getCell(cellNum++).getNumericCellValue());
		resultData.setSubjectCode(String.valueOf(new Integer((int) row.getCell(
				cellNum++).getNumericCellValue())));
		resultData.setSubjectName(row.getCell(cellNum++).getStringCellValue());
		Cell cell = row.getCell(cellNum++);
		if (null == cell) {
			resultData.setProjectCode(null);
		} else {
			resultData.setProjectCode(cell.getStringCellValue());
		}

		cell = row.getCell(cellNum++);
		if (null == cell) {
			resultData.setProjectName(null);
		} else {
			resultData.setProjectName(cell.getStringCellValue());
		}

		cell = row.getCell(cellNum++);
		if (null == cell) {
			resultData.setFunctionTypeCode(null);
		} else {
			resultData.setFunctionTypeCode(String.valueOf(new Integer(
					(int) cell.getNumericCellValue())));
		}

		cell = row.getCell(cellNum++);
		if (null == cell) {
			resultData.setFunctionTypeName(null);
		} else {
			resultData.setFunctionTypeName(cell.getStringCellValue());
		}

		cell = row.getCell(cellNum++);
		if (null == cell) {
			resultData.setEconomicTypeCode(null);
		} else {
			resultData.setEconomicTypeCode(String.valueOf(new Integer(
					(int) cell.getNumericCellValue())));
		}

		cell = row.getCell(cellNum++);
		if (null == cell) {
			resultData.setEconomicTypeName(null);
		} else {
			resultData.setEconomicTypeName(cell.getStringCellValue());
		}
		return resultData;
	}
}