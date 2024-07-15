package com.soccer.manager.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.soccer.manager.domain.repo.entity.User;

@SpringBootTest
class UserServiceTest {

	@Autowired
	private UserService userService;
	
	@Test
	void testUserService_getUser() {
		var userFromDB = userService.getUser("ead0281a-be12-407b-a4b5-06c8829fdfc2");
		
		assertEquals("ead0281a-be12-407b-a4b5-06c8829fdfc2", userFromDB.getId());
		assertEquals("mighty@gmail.com", userFromDB.getUsername());
	}
	
	@Test
	void testUserService_getUserByUsername() {
		var userFromDB = userService.getUserByUsername("mighty@gmail.com");
		
		assertEquals("ead0281a-be12-407b-a4b5-06c8829fdfc2", userFromDB.getId());
		assertEquals("mighty@gmail.com", userFromDB.getUsername());
	}
	
	@Test
	void testUserService_save() {
		var user = new User();
		
		user.setUsername("stranger@yahoo.com");
		user.setPassword("moreblahblah");
		
		userService.save(user);
		
		assertNotNull(user.getId());
	}
}
