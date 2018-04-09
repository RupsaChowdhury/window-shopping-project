package com.Dao;

import java.util.List;

import javax.persistence.Id;

import com.domain.Category;

public interface CategoryDao {
	@Id
	
	public boolean save(Category category);
	

	
	public boolean update(Category category);
	

	
	public Category get(String id);
	
	
	
	public boolean delete(String id);
	public List<Category> list();
	}

