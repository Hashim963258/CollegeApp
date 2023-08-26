package com.example.College.SERVICE;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.College.DTO.College;
import com.example.College.REPOSITORY.CollegeRepository;

import io.swagger.models.Contact;

@Service
public class CollegeService 
{
	@Autowired
	CollegeRepository repo;
	
	public College addCollege(College col)
	{
		return repo.save(col);
	}
	
	public List<College> getAll()
	{
		List<College> list = repo.findAll();
		return list;
	}
	
	public College searchCollege(int id)
	{
		Optional<College> opt = repo.findById(id);
		
		if(opt.isPresent())
		{
		  return opt.get();	
		}
		return null;
	}
	
	public int removeCollege(int id)
	{
			College c = searchCollege(id);
			if(c != null)
			{
				repo.deleteById(id);
				return 1;
			}
			return 0;
	}
	
	public College updateCollege(int id, String name)
	{
		College c =	searchCollege(id);
		
		if(c != null)
		{
			c.setName(name);
			repo.save(c);
			return c;
		}
		return null;
	}
	
	
}
