package com.example.College.EXCEL;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.College.DTO.College;

@Service
public class ExcelService 
{
	public List<College> parseExcelFile(MultipartFile file) throws EncryptedDocumentException, IOException
	{
		List<College> colleges = new ArrayList<College>();
		
		Workbook book = WorkbookFactory.create(file.getInputStream());
		
		Sheet sheet = book.getSheetAt(0);
		
		Iterator<Row> rowIterator = sheet.iterator();
		
		while(rowIterator.hasNext())
		{
			Row row = rowIterator.next();
			
			String name = row.getCell(0).getStringCellValue();
			String address = row.getCell(1).getStringCellValue();
			
			College col = new College();
			col.setName(name);
			col.setAddress(address);
			
			
			colleges.add(col);
			
		}
		return colleges;
		
	}
}
