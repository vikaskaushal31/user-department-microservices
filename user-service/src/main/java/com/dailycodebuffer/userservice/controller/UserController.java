package com.dailycodebuffer.userservice.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dailycodebuffer.userservice.VO.ResponseTemplateVO;
import com.dailycodebuffer.userservice.entity.User;
import com.dailycodebuffer.userservice.service.UserService;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;

@RestController
@RequestMapping("/users")
public class UserController {

	private Logger logger = LoggerFactory.getLogger(UserController.class);
	@Autowired
	private UserService userService;
	
	
	@PostMapping("/")
	public User saveUser(@RequestBody User user) {
		logger.info("Inside saveUser method of UserController");
		return userService.saveUser(user);
	}
	
	@GetMapping("/{id}")
	@CircuitBreaker(name="userservice", fallbackMethod="serviceFallback")
	public ResponseEntity<Object> getUserWithDepartment(@PathVariable("id") Long userId) throws Exception{
		logger.info("Inside getUserWithDepartment method of UserController");
		return new ResponseEntity<>(userService.getUserWithDepartment(userId), HttpStatus.OK);
	}
	public ResponseEntity<Object> serviceFallback(Throwable e) {
		return new ResponseEntity<>("This is a fallback method from from userService", HttpStatus.OK);
	}
	
}
