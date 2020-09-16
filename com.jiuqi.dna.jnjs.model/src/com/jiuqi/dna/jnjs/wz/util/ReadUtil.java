package com.jiuqi.dna.jnjs.wz.util;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.ss.usermodel.Cell;
//import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import com.jiuqi.dna.core.Context;
import com.jiuqi.dna.jnjs.consts.ExcelField;
import com.jiuqi.dna.jnjs.entity.ImportVoucherTask;


/**
 * @author wangzhe01
 * @date 2020年9月10日
 * 
 */
public class ReadUtil {
	public static int readCenter(Context context, Workbook workbook) {
		List<Map<String, Object>> excelData = null;
		excelData = getExcelData(workbook);
//		System.out.println("提取数据:" + excelData.get(8).get("凭证日期"));
//		System.out.println("获取数据"+excelData.size()+"行");
		ImportVoucherTask task = new ImportVoucherTask();
		task.setExcelData(excelData);
		context.handle(task);		
		return 1;
	}

	private static List<Map<String, Object>> getExcelData(Workbook workbook) {

		Sheet rs = workbook.getSheetAt(0);
		List<Map<String, Object>> excelData = new ArrayList<Map<String, Object>>();
		// 读取表头：
		Map<String, Integer> headIndex = new HashMap<String, Integer>();
		Row head = rs.getRow(0);
		List<String> titleList = ExcelField.FIELDS;
		// 遍历表头，查找各个预设标题的列号，默认缺省值为-1。
		for (String title : titleList) {
			headIndex.put(title, -1);
			for (int i = 0; i < head.getLastCellNum(); i++) {
				if (title.equals(head.getCell(i).getStringCellValue())) {
					headIndex.put(title, i);
					break;
				}
			}
		}
		// 逐行遍历，只取预设标题列对应的数据。并用标题做键。
		for (int row = 1; row < rs.getLastRowNum(); row++) {
			Row rowData = rs.getRow(row);
			Map<String, Object> excelData_row = new HashMap<String, Object>();
			for (String title : titleList) {
				int index = headIndex.get(title);
				if (index >= 0) {
					excelData_row.put(title, getCellFormatValue(rowData.getCell(index)));
				} else {
					excelData_row.put(title, null);
				}
			}
			excelData.add(excelData_row);
		}
		return excelData;
	}

	/**
	 * 单元格数据读取工具。
	 * 
	 * @param cell
	 * @return
	 */

	public static Object getCellFormatValue(Cell cell) {
		Object cellValue = null;
		if (cell != null) {
			// 判断cell类型
			switch (cell.getCellType()) {
			case Cell.CELL_TYPE_NUMERIC: {
				// 判断cell是否为日期格式
				if (DateUtil.isCellDateFormatted(cell)) {
					// 转换为日期格式YYYY-mm-dd
					cellValue = cell.getDateCellValue();
					cellValue = new SimpleDateFormat("yyyy-MM-dd").format(cellValue);
				} else {
					cell.setCellType(Cell.CELL_TYPE_STRING);
					cellValue = cell.toString();
				}
				break;
			}
			case Cell.CELL_TYPE_FORMULA: {
				// 判断cell是否为日期格式
				if (DateUtil.isCellDateFormatted(cell)) {
					// 转换为日期格式YYYY-mm-dd
					cellValue = cell.getDateCellValue();
					cellValue = new SimpleDateFormat("yyyy-MM-dd").format(cellValue);
				} else {
					// 数字
					cellValue = String.valueOf(cell.getNumericCellValue());
				}
				break;
			}
			case Cell.CELL_TYPE_STRING: {
				cellValue = cell.getRichStringCellValue().getString();
				break;
			}
			default:
				cellValue = "";
			}
		} else {
			cellValue = "";
		}
		return cellValue;
	}

}
