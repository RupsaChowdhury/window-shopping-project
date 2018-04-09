package com.DaoImpl;
import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.Dao.ProductDao;
import com.domain.Product;

@Transactional
@Repository("productdao") 
public class ProductDaoImpl implements ProductDao {

	Logger log = LoggerFactory.getLogger(ProductDaoImpl.class);
	@Autowired 
	private SessionFactory sessionFactory;
	@Autowired
	private Product product;
	public boolean save(Product product) 
	{
		return false;
	
		
	}

	public boolean update(Product product) 
	{
		/*if (product.getPid()==null)
			return false;*/
		try {
			sessionFactory.getCurrentSession().update(product);
			return true;
		}
		catch (HibernateException e) {
			
			e.printStackTrace();
			return false;
		}	
		
	}

	public Product get(String id) 
	{
		return sessionFactory.getCurrentSession().get(Product.class, id);
		
	}
	public boolean delete(String id)
	{
		try 
		{
			product =get(id);
			if(product== null)
			{
			return false;
			}
			sessionFactory.getCurrentSession().delete(product);
			return true;
		}
			
		catch (HibernateException e)
		{
			
			e.printStackTrace();
			return false;
		}	
	
	}

	public List<Product> list() {
		return sessionFactory.getCurrentSession().createQuery("from Product").list();
		
	}

	public List<Product> search(String searchString) {
		String hql="select * from product where description like '%" + searchString + "%'";
		// TODO Auto-generated method stub
		return sessionFactory.getCurrentSession().createQuery(hql).list();
	}

	public List<Product> search(String searchString, int maxPrice) {
		String hql="select * from product where description like '%" + searchString + "%' and price<" + maxPrice;
		// TODO Auto-generated method stub
	 return sessionFactory.getCurrentSession().createQuery(hql).list();
	}

	public List<Product> search(String searchString, int minPrice, int maxprice) {
		// TODO Auto-generated method stub
		return null;
	}
}
