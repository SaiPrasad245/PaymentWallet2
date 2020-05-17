package com.cg.sprint2.user.ms;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;

import com.cg.sprint2.user.ms.dto.User;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
class UserApplicationTests {

	@Autowired
	TestRestTemplate testRestTemplate;

	public void setTestRestTemplate(TestRestTemplate testRestTemplate) {
		this.testRestTemplate = testRestTemplate;
	}
	@LocalServerPort 
	int  localServerPort;
	
	@Test
    public void testCheckUser_Positive() throws Exception
    {
		String url="http://localhost:"+localServerPort+"login/9398165142/ajay";
		ResponseEntity<String> response =testRestTemplate.getForEntity(url,String.class);
		Assertions.assertEquals(200, response.getStatusCodeValue());
		
    }
	@Test
    public void testCheckUser_Negative() throws Exception
    {
		String url="http://localhost:"+localServerPort+"login/9398165142/aja";
		ResponseEntity<String> response =testRestTemplate.getForEntity(url,String.class);
		Assertions.assertEquals(404, response.getStatusCodeValue());
		
    }
	@Test
    public void testSignup_Positive() throws Exception
    {
		String url="http://localhost:"+localServerPort+"signup";
		User user = new User("Prasad","8328692013","prasad","prasad@gmail,com",0.0);
		ResponseEntity<String> response =testRestTemplate.postForEntity(url,user,String.class);
		Assertions.assertEquals(200, response.getStatusCodeValue());
		
    }
	@Test
    public void testSignup_Negative() throws Exception
    {
		String url="http://localhost:"+localServerPort+"signup";
		User user = new User("","8328692013","prasad","prasad@gmail,com",0.0);
		ResponseEntity<String> response =testRestTemplate.postForEntity(url,user,String.class);
		Assertions.assertEquals(500, response.getStatusCodeValue());
		
    }
	@Test
    public void testGetUser_Positive() throws Exception
    {
		String url="http://localhost:"+localServerPort+"profile/7799208319";
		ResponseEntity<String> response =testRestTemplate.getForEntity(url,String.class);
		Assertions.assertEquals(200, response.getStatusCodeValue());
		
    }
}
