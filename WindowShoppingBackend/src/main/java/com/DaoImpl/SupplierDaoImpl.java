package com.DaoImpl;
import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.Dao.SupplierDao;
import com.domain.Supplier;




@Transactional
@Repository("supplierdao")
public class SupplierDaoImpl implements SupplierDao {
	Logger log = LoggerFactory.getLogger(SupplierDaoImpl.class);

	@Autowired 
	private SessionFactory sessionFactory;
	@Autowired
	private Supplier supplier;
	public boolean save(Supplier supplier) 
	{
		return false;
	
		
	}

	public boolean update(Supplier supplier) 
	{
		/*if (supplier.getSid()==null)
			return false;*/
		try {
			sessionFactory.getCurrentSession().update(supplier);
			return true;
		}
		catch (HibernateException e) {
			
			e.printStackTrace();
			return false;
		}	
		
	}

	public Supplier get(String emailID) 
	{
		return sessionFactory.getCurrentSession().get(Supplier.class, emailID);
		
	}

	public boolean delete(String emailID)
	{
		try 
		{
			supplier =get(emailID);
			if(supplier== null)
			{
			return false;
			}
			sessionFactory.getCurrentSession().delete(supplier);
			return true;
		}
			
		catch (HibernateException e)
		{
			
			e.printStackTrace();
			return false;
		}	
	
	}

	public List<Supplier> list() {
		return sessionFactory.getCurrentSession().createQuery("from Supplier").list();
	}
}
		
