package com.Dao;

import java.util.List;

import javax.persistence.Id;

import com.domain.Supplier;

public interface SupplierDao {
	@Id

	
	public boolean save(Supplier supplier);
	

	
	public boolean update(Supplier supplier);
	
public Supplier get(String id);
	

	
	public boolean delete(String id);
	
	public List<Supplier> list();
	
}

