package com.example.College.CONTROLLER;

import java.io.IOException;
import java.util.List;

import org.apache.poi.EncryptedDocumentException;
import org.apache.regexp.RE;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.College.DTO.College;
import com.example.College.EXCEL.ExcelService;
import com.example.College.SERVICE.CollegeService;


@RestController
@RequestMapping("/college")
public class CollegeController 
{
	@Autowired
	CollegeService col_ser;
	
	@Autowired
	ExcelService exc_ser;
	
	
	//----------------API to save the college obj to DB --------------
	@PostMapping
	public ResponseEntity<College> saveCollege(@RequestBody College col)
	{
		College  savedcollege = col_ser.addCollege(col);
		return ResponseEntity.ok(savedcollege);
	}
	
	//----------------API to display all the college obj ------------------
	@GetMapping
	public ResponseEntity<List<College>> displayAll()
	{
		List<College> col = col_ser.getAll();
		return ResponseEntity.ok(col);
	}
	
	// --------------------- API to get a contact based on ID ----------------------------------
	@GetMapping("/byid")
	public ResponseEntity<College> getById(@RequestParam int id)
	{
		College col = col_ser.searchCollege(id);
		if(col != null)
		{
		return ResponseEntity.ok(col);
		}
		return ResponseEntity.notFound().build();
	}
	
	//------------------ API to update contact in DataBase ----------------------------------
	@PutMapping
	public ResponseEntity<College> updateCollege(@RequestParam int id, String name)
	{
		College col = col_ser.updateCollege(id, name);
		
		if(col != null)
		{
		return ResponseEntity.ok(col);
		}
		return ResponseEntity.notFound().build();
	}
	
	// ---------------API to Delete contact in DB ------------------------
		@DeleteMapping
		public ResponseEntity<Void> deleteCollege(@RequestParam int id)
		{
			int a = col_ser.removeCollege(id);
			
			if(a == 1)
			{
				return ResponseEntity.ok().build();
			}
			else
			{
				return ResponseEntity.notFound().build();
			}
		}
		
		//APi to upload file to DATABASE
		@PostMapping("/excel")
		public ResponseEntity<List<College>> uploadFile(@RequestParam("file") MultipartFile file) throws  EncryptedDocumentException
		{
			try 
			{
				List<College> list = exc_ser.parseExcelFile(file);
				
				for(College c : list)
				{
					col_ser.addCollege(c);
				}
				return ResponseEntity.ok().build();
				
			} catch (IOException e) {
				
				return ResponseEntity.notFound().build();
			} 
			
		}
	
	
}
