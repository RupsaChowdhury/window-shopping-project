package com.DaoImpl;
import java.sql.Date;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.Dao.UserDao;
import com.domain.User;


@Transactional
@Repository("userdao")
public class UserDaoImpl implements UserDao{
	
	private static final Logger log = LoggerFactory.getLogger(UserDaoImpl.class);
	
@Autowired
	
	private SessionFactory sessionFactory;
@Autowired
private User user;
public boolean save(User user) 
{
	log.debug("starting of the save method");
	try 
	{
		user.setRole('C');
		user.setRegisterDate(new Date(System.currentTimeMillis()) + "");
		sessionFactory.getCurrentSession().save(user);
		return true;
	} 
	catch (HibernateException e) 
	{
		e.printStackTrace();
		return false;
	}
	
}

public boolean update(User user) 
{
	log.debug("starting of the update method");
	/*if (user.getEmailD()==null)
		return false;*/
	try {
		sessionFactory.getCurrentSession().update(user);
		log.debug("ending of the update method");
		return true;
	}
	catch (HibernateException e) {
		
		e.printStackTrace();
		log.error("Error occured in update method"+e.getMessage());
		return false;
	}	
	
}

public User get(String emailID) 
{
	log.debug("starting of the get method");
	return sessionFactory.getCurrentSession().get(User.class, emailID);
	
}

public boolean delete(String emailID)
{
	log.debug("starting of the delete method");
	try 
	{	
		user =get(emailID);
		if(user== null)
		{
		return false;
		}
		sessionFactory.getCurrentSession().delete(user);
		log.debug("ending of the delete method");
		return true;
	}
		
	catch (HibernateException e)
	{
		
		e.printStackTrace();
		log.error("error ocurred in delete method"+ e.getMessage());;
		return false;
	}	
	
}

public List<User> list() {
	log.debug("starting of the list method");
	return	sessionFactory.getCurrentSession().createQuery("from User").list();
	}

	public User validate(String emailID, String password) {
		//select * from usre where emailID = 'dipika@gmail.com' and password = 'dipika@123'
		log.debug("starting of the validate method");
		log.info("users"+emailID+"trying to login");
	user = (User)sessionFactory.getCurrentSession().
	createCriteria(User.class).
	add(Restrictions.eq("emailID", emailID))
	.add(Restrictions.eq("pwd",password)).uniqueResult();
	return user;
	}
}