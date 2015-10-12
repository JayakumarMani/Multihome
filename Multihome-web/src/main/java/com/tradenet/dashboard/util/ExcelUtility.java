package com.tradenet.dashboard.util;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;

import com.tradenet.dashboard.model.LoggingDTO;

public class ExcelUtility {

	public static byte[] exportToExcel(List<LoggingDTO> logList) {
		HSSFWorkbook workbook = new HSSFWorkbook();
		HSSFSheet sheet = workbook.createSheet("Sample sheet");

		Map<String, Object[]> data = new HashMap<String, Object[]>();
		data.put("1", new Object[] { "S/N.", "Application Id", "Module Name", "Function Name", "Transaction Ref Id",
				"Sub Transaction Id", "Log Level", "Log Date & Time" });
		Integer count = 2;
		int index = 1;
		for (LoggingDTO dto : logList) {
			if (dto != null) {
				data.put(count.toString(),
						new Object[] { index, dto.getAppId(), dto.getModuleName(), dto.getFunctionName(),
								dto.getTransactionRefId(), dto.getSubTransactionId(), dto.getLogLevel(),
								dto.getLogDateTime().toString() });
				count++;
				index++;
			}
		}

		Set<String> keyset = data.keySet();
		int rownum = 0;
		for (String key : keyset) {
			HSSFRow row = sheet.createRow(rownum++);
			Object[] objArr = data.get(key);
			int cellnum = 0;
			if (key.equals("1")) {
				for (Object obj : objArr) {
					HSSFCell cell = row.createCell(cellnum++);

					cell.setCellValue((String) obj);
					HSSFCellStyle cellStyle = workbook.createCellStyle();

					cellStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
					cellStyle.setFillForegroundColor(HSSFColor.GREY_25_PERCENT.index);
					HSSFFont font = workbook.createFont();
					font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
					font.setColor(HSSFColor.WHITE.index);

					cellStyle.setFont(font);
					cellStyle.setLocked(true);
					cell.setCellStyle(cellStyle);

				}
			} else {
				for (Object obj : objArr) {
					HSSFCell cell = row.createCell(cellnum++);
					if (obj instanceof Date)
						cell.setCellValue((Date) obj);
					else if (obj instanceof Boolean)
						cell.setCellValue((Boolean) obj);
					else if (obj instanceof String)
						cell.setCellValue((String) obj);
					else if (obj instanceof Double)
						cell.setCellValue((Double) obj);
					else if (obj instanceof Timestamp)
						cell.setCellValue((Timestamp) obj);
					else if (obj instanceof Long)
						cell.setCellValue((Long) obj);
					else if (obj instanceof Integer)
						cell.setCellValue((Integer) obj);

				}
			}

		}
		sheet.autoSizeColumn(1);
		sheet.autoSizeColumn(2);
		sheet.autoSizeColumn(3);
		sheet.autoSizeColumn(4);
		sheet.autoSizeColumn(5);
		sheet.autoSizeColumn(6);
		sheet.autoSizeColumn(7);
		sheet.autoSizeColumn(8);
		ByteArrayOutputStream file = new ByteArrayOutputStream();
		try {
			
			workbook.write(file);
			FileOutputStream out = new FileOutputStream(new File("D:\\LogExportData.xls"));
			workbook.write(out);
			out.close();
			file.close();
			System.out.println(file.toByteArray());
			System.out.println("Excel written successfully..");
			return file.toByteArray();

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return file.toByteArray();
	}
}
