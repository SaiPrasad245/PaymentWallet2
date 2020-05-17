package com.cg.sprint2.user.ms.service;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.cg.sprint2.user.ms.dao.UserDAO;
import com.cg.sprint2.user.ms.dto.User;

@Service
public class UserService {
	
	@Autowired
	UserDAO udao;

	public void setUdao(UserDAO udao)
	{
		this.udao = udao;
	}
	// Verification of the user
	@Transactional (readOnly=true)
   public Optional<User> validateLogin(String mobileno)
   {
	   return udao.findById(mobileno); 
   }
	
	@Transactional
	// Registration of new user
	public User createUser(User user)
	{
		return udao.save(user);
	}
	
	// Login Of the User 
	public ResponseEntity<Optional<User>> checkUser( String mobileno, String password)
	{
		try
		{
		Optional<User> user =validateLogin(mobileno);
		Optional<User> empty = Optional.empty();
		if(user.isPresent()&&user.get().getPassword().equals(password))
		{
			return new ResponseEntity<Optional<User>>(user,HttpStatus.OK);
		}
		else
			if(user.get().getPassword().equals(password))
			{
				System.out.println("Invalid Password");
				return new ResponseEntity<Optional<User>>(empty,HttpStatus.NOT_FOUND);
			}
			else
			{
				System.out.println("Invalid User");
				return new ResponseEntity<Optional<User>>(empty,HttpStatus.NOT_FOUND);
			}
		}
		catch (Exception ex)
		{ Optional<User> empty = Optional.empty();
			return new ResponseEntity<Optional<User>>(empty,HttpStatus.NOT_FOUND);
		}
	}
	// Get User Profile
	public Optional<User> getUser(String mobileno)
	{
		return udao.findById(mobileno);
	}
	

}
