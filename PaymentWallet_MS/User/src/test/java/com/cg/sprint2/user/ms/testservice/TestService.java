package com.cg.sprint2.user.ms.testservice;

import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.ResponseEntity;

import com.cg.sprint2.user.ms.dto.User;
import com.cg.sprint2.user.ms.service.UserService;

@SpringBootTest(webEnvironment=WebEnvironment.RANDOM_PORT)
public class TestService {
	@Autowired
	UserService uservice;
	@Test
	 public void testCheckUser_Positive() throws Exception
	    {
			
			ResponseEntity<Optional<User>> u = uservice.checkUser("9398165142", "ajay");
			Assertions.assertEquals(200, u.getStatusCodeValue());
			
	    }
		@Test
	    public void testCheckUser_Negative() throws Exception
	    {
			ResponseEntity<Optional<User>> u = uservice.checkUser("9398165142", "aja");
		    Assertions.assertEquals(404, u.getStatusCodeValue());
			
	    }
		
		  @Test public void testSignup_Positive() throws Exception { 
		  User u = new User("Prasad","8328692013","prasad","prasad@gmail,com",0.0);
		    User c =uservice.createUser(u);
		  Assertions.assertEquals(c.getName(),u.getName() );
		  
		  }

}
