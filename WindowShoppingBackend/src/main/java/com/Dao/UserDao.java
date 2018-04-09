package com.Dao;

import java.util.List;

import javax.persistence.Id;

import com.domain.User;

public interface UserDao {
	@Id
	
	
	public boolean save(User user);
	
	
	
	public boolean update(User user);
	
	
	
	public User get(String emailID);
	
	
	public boolean delete(String emailID);
	
	public List<User> list();
	


	
		public User validate(String emailID, String password);
}
