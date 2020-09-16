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
 * @date 2020��9��10��
 * 
 */
public class ReadUtil {
	public static int readCenter(Context context, Workbook workbook) {
		List<Map<String, Object>> excelData = null;
		excelData = getExcelData(workbook);
//		System.out.println("��ȡ����:" + excelData.get(8).get("ƾ֤����"));
//		System.out.println("��ȡ����"+excelData.size()+"��");
		ImportVoucherTask task = new ImportVoucherTask();
		task.setExcelData(excelData);
		context.handle(task);		
		return 1;
	}

	private static List<Map<String, Object>> getExcelData(Workbook workbook) {

		Sheet rs = workbook.getSheetAt(0);
		List<Map<String, Object>> excelData = new ArrayList<Map<String, Object>>();
		// ��ȡ��ͷ��
		Map<String, Integer> headIndex = new HashMap<String, Integer>();
		Row head = rs.getRow(0);
		List<String> titleList = ExcelField.FIELDS;
		// ������ͷ�����Ҹ���Ԥ�������кţ�Ĭ��ȱʡֵΪ-1��
		for (String title : titleList) {
			headIndex.put(title, -1);
			for (int i = 0; i < head.getLastCellNum(); i++) {
				if (title.equals(head.getCell(i).getStringCellValue())) {
					headIndex.put(title, i);
					break;
				}
			}
		}
		// ���б�����ֻȡԤ������ж�Ӧ�����ݡ����ñ���������
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
	 * ��Ԫ�����ݶ�ȡ���ߡ�
	 * 
	 * @param cell
	 * @return
	 */

	public static Object getCellFormatValue(Cell cell) {
		Object cellValue = null;
		if (cell != null) {
			// �ж�cell����
			switch (cell.getCellType()) {
			case Cell.CELL_TYPE_NUMERIC: {
				// �ж�cell�Ƿ�Ϊ���ڸ�ʽ
				if (DateUtil.isCellDateFormatted(cell)) {
					// ת��Ϊ���ڸ�ʽYYYY-mm-dd
					cellValue = cell.getDateCellValue();
					cellValue = new SimpleDateFormat("yyyy-MM-dd").format(cellValue);
				} else {
					cell.setCellType(Cell.CELL_TYPE_STRING);
					cellValue = cell.toString();
				}
				break;
			}
			case Cell.CELL_TYPE_FORMULA: {
				// �ж�cell�Ƿ�Ϊ���ڸ�ʽ
				if (DateUtil.isCellDateFormatted(cell)) {
					// ת��Ϊ���ڸ�ʽYYYY-mm-dd
					cellValue = cell.getDateCellValue();
					cellValue = new SimpleDateFormat("yyyy-MM-dd").format(cellValue);
				} else {
					// ����
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
