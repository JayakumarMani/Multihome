package com.tradenet.dashboard.util;

import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.tradenet.dashboard.model.LoggingDTO;

public class PdfUtility {
	public static byte[] showHelp(List<LoggingDTO> logList) throws Exception {
		ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
		FileOutputStream stream = new FileOutputStream("D://test.pdf");
		Document document = new Document();
		Font fontLabel = new Font(FontFactory.getFont("Calibri", 9, Font.BOLD));
		Font fontInfo = new Font(FontFactory.getFont("Calibri", 9, Font.NORMAL));
		PdfWriter.getInstance(document, stream);
		document.open();

		Paragraph createTime = new Paragraph(
				new Chunk(new Date().toString(), FontFactory.getFont("Calibri", 8, Font.BOLD, BaseColor.GRAY)));
		createTime.setAlignment(Element.ALIGN_RIGHT);
		document.add(createTime);

		Paragraph title = new Paragraph(
				new Chunk("Logging Data", FontFactory.getFont("Calibri", 16, Font.BOLD, BaseColor.GRAY)));
		title.setAlignment(Element.ALIGN_CENTER);
		title.setSpacingAfter(6);
		document.add(title);

		float[] widths = new float[] { 13f, 35f, 35f, 35f, 35f, 30f, 25f, 40f };
		PdfPTable detailsTable = new PdfPTable(widths);
		detailsTable.getDefaultCell().setBorder(5); // set table border
		detailsTable.setSpacingBefore(10f); // set space before table
		detailsTable.setTotalWidth(1000); // set table's width
		detailsTable.setWidthPercentage(100); // set table's width 100%
		detailsTable = createTableHeader(detailsTable);
		int count = 1;
		if (logList != null && !logList.isEmpty()) {
			for (LoggingDTO line : logList) {

				createPaperDetails(line, count, detailsTable);
				count++;
			}
		} else {
			PdfPCell c1 = new PdfPCell(new Phrase("No Records to display.", fontInfo));
			c1.setColspan(8);
			c1.setHorizontalAlignment(Element.ALIGN_LEFT);
			detailsTable.addCell(c1);
		}

		document.add(detailsTable);

		document.close();
		return byteArrayOutputStream.toByteArray();
	}

	private static PdfPTable createPaperDetails(LoggingDTO line, int index, PdfPTable table) {
		Font fontH1 = new Font(FontFactory.getFont("Calibri", 9, Font.NORMAL));
		PdfPCell c1 = new PdfPCell(new Phrase(String.valueOf(index), fontH1));
		c1.setHorizontalAlignment(Element.ALIGN_LEFT);
		table.addCell(c1);
		c1 = new PdfPCell(new Phrase(line.getAppId().toString(), fontH1));
		c1.setHorizontalAlignment(Element.ALIGN_LEFT);
		table.addCell(c1);
		c1 = new PdfPCell(new Phrase(line.getModuleName(), fontH1));
		c1.setHorizontalAlignment(Element.ALIGN_LEFT);
		table.addCell(c1);
		c1 = new PdfPCell(new Phrase(line.getFunctionName(), fontH1));
		c1.setHorizontalAlignment(Element.ALIGN_LEFT);
		table.addCell(c1);
		c1 = new PdfPCell(new Phrase(line.getTransactionRefId().toString(), fontH1));
		c1.setHorizontalAlignment(Element.ALIGN_LEFT);
		table.addCell(c1);
		c1 = new PdfPCell(new Phrase(line.getSubTransactionId().toString(), fontH1));
		c1.setHorizontalAlignment(Element.ALIGN_LEFT);
		table.addCell(c1);
		c1 = new PdfPCell(new Phrase(line.getLogLevel().toString(), fontH1));
		c1.setHorizontalAlignment(Element.ALIGN_LEFT);
		table.addCell(c1);
		c1 = new PdfPCell(new Phrase(line.getLogDateTime().toString(), fontH1));
		c1.setHorizontalAlignment(Element.ALIGN_LEFT);
		table.addCell(c1);
		table.setHeaderRows(1);
		return table;
	}

	private static PdfPTable createTableHeader(PdfPTable table) {
		Font fontH1 = new Font(FontFactory.getFont("Calibri", 9, Font.BOLD));
		PdfPCell c1 = new PdfPCell(new Phrase("S/N", fontH1));
		c1.setHorizontalAlignment(Element.ALIGN_CENTER);
		c1.setBackgroundColor(BaseColor.LIGHT_GRAY);
		table.addCell(c1);
		c1 = new PdfPCell(new Phrase("Application Id", fontH1));
		c1.setHorizontalAlignment(Element.ALIGN_CENTER);
		c1.setBackgroundColor(BaseColor.LIGHT_GRAY);
		table.addCell(c1);
		c1 = new PdfPCell(new Phrase("Module Name", fontH1));
		c1.setHorizontalAlignment(Element.ALIGN_CENTER);
		c1.setBackgroundColor(BaseColor.LIGHT_GRAY);
		table.addCell(c1);
		c1 = new PdfPCell(new Phrase("Function Name", fontH1));
		c1.setHorizontalAlignment(Element.ALIGN_CENTER);
		c1.setBackgroundColor(BaseColor.LIGHT_GRAY);
		table.addCell(c1);
		c1 = new PdfPCell(new Phrase("Transaction Ref Id", fontH1));
		c1.setHorizontalAlignment(Element.ALIGN_CENTER);
		c1.setBackgroundColor(BaseColor.LIGHT_GRAY);
		table.addCell(c1);
		c1 = new PdfPCell(new Phrase("Sub Transaction Id", fontH1));
		c1.setHorizontalAlignment(Element.ALIGN_CENTER);
		c1.setBackgroundColor(BaseColor.LIGHT_GRAY);
		table.addCell(c1);
		c1 = new PdfPCell(new Phrase("Log Level", fontH1));
		c1.setHorizontalAlignment(Element.ALIGN_CENTER);
		c1.setBackgroundColor(BaseColor.LIGHT_GRAY);
		table.addCell(c1);
		c1 = new PdfPCell(new Phrase("Log Date  Time", fontH1));
		c1.setHorizontalAlignment(Element.ALIGN_CENTER);
		c1.setBackgroundColor(BaseColor.LIGHT_GRAY);
		table.addCell(c1);
		table.setHeaderRows(1);
		return table;
	}
}
