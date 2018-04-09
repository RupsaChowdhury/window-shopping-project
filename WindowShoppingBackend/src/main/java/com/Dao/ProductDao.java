package com.Dao;

import java.util.List;

import javax.persistence.Id;

import com.domain.Product;

public interface ProductDao {
@Id
	
	public boolean save(Product product);
	
	
	
	public boolean update(Product product);
	
	public Product get(String id);
	
	
	
	public boolean delete(String id);
	
	public List<Product> list();
	public List<Product> search(String searchString);
	public List<Product> search(String searchString,int maxPrice);
	public List<Product> search(String searchString,int minPrice,int maxprice);
	
}
