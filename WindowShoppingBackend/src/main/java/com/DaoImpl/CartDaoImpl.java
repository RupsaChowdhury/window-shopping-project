package com.DaoImpl;
import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.Dao.CartDao;
import com.domain.Cart;


@Transactional
@Repository("cartdao") 
public class CartDaoImpl implements CartDao {
	private static final Logger log = LoggerFactory.getLogger(UserDaoImpl.class);
	@Autowired
	
	private SessionFactory sessionFactory;
	@Autowired
	private Cart cart;
	
	
	//Logger log=LoggerFactory.getLogger(name)
	public boolean save(Cart cart) 
	{
		log.debug("starting of the save method");
	try{

		sessionFactory.getCurrentSession().saveOrUpdate(cart);
		return true;
	} catch (HibernateException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		return false;
	}
		
	}
	public boolean update(Cart cart) 
	{
		log.debug("starting of the update method");
		
		try {
			sessionFactory.getCurrentSession().update(cart);
			log.debug("ending of the update method");
			return true;
		}
		catch (HibernateException e) {
			
			e.printStackTrace();
			log.error("Error occured in update method"+e.getMessage());
			return false;
		}	
		
	}

	public Cart get(String id) 
	{
		log.debug("starting of the get method");
		return sessionFactory.getCurrentSession().get(Cart.class, id);
		
	}
	public boolean delete(String emailID)
	{
		log.debug("starting of the delete method");
		try 
		{
			cart =get(emailID);
			if(cart== null)
			{
			return false;
			}
			sessionFactory.getCurrentSession().delete(cart);
			log.debug("ending of the delete method");
			return true;
		}
			
		catch (HibernateException e)
		{
			
			e.printStackTrace();
			return false;
		}	
	}
	public List<Cart> list(String emailID) {
		log.debug("starting of the list method");
		//select * from cart whrer emailid= ?
		return sessionFactory.getCurrentSession().createCriteria(Cart.class).add(Restrictions.eq("emailID", "emailID")).list();
	}
	public boolean update(String emailID) {
		log.debug("Starting of the method update");
		log.debug("Going to place order of"+emailID);
		String hql="update Cart set status = 'O' where emailid='" + emailID+ "'";
		
		log.info("The given query:"+hql);
				try {
					sessionFactory.getCurrentSession().createQuery(hql).executeUpdate();
					log.debug("Ending of the method update");
                     return true;
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		
		// TODO Auto-generated method stub
		return false;
	}
}



