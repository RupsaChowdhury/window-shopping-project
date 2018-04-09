package com.DaoImpl;
import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.Dao.CategoryDao;
import com.domain.Category;


@Transactional
@Repository("categorydao") 
public class CategoryDaoImpl implements CategoryDao {
	
	private static final Logger log = LoggerFactory.getLogger(UserDaoImpl.class);
	@Autowired
	
	private SessionFactory sessionFactory;
	@Autowired
	private Category category;
	public boolean save(Category category) 
	{
		log.debug("starting of the save method");
	
	try{

		sessionFactory.getCurrentSession().saveOrUpdate(category);
		return true;
	} catch (HibernateException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		return false;
	}
	}
		
	

	public boolean update(Category category)
	{
		log.debug("starting of the update method");
		//if (category.getCid()==null)
			//return false;
		try {
			sessionFactory.getCurrentSession().update(category);
			log.debug("ending of the update method");
			return true;
		}
		catch (HibernateException e) {
			e.printStackTrace();
			log.error("Error occured in update method"+e.getMessage());
			return false;
		}	
		
	}

	public Category get(String id) 
	{
		log.debug("starting of the get method");
		return sessionFactory.getCurrentSession().get(Category.class, id);
		
	}

	public boolean delete(String emailID)
	{
		log.debug("starting of the delete method");
		try 
		{
			category =get(emailID);
			if(category== null)
			{
			return false;
			}
			sessionFactory.getCurrentSession().delete(category);
			log.debug("ending of the delete method");
			return true;
		}
			
		catch (HibernateException e)
		{
			
			e.printStackTrace();
			return false;
		}	
	}

	public List<Category>list()
	{
		log.debug("starting of the list method");
		return sessionFactory.getCurrentSession().createQuery("from Category").list();
	}
	}
