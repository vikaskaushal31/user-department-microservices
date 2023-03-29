package com.dailycodebuffer.userservice.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.dailycodebuffer.userservice.VO.Department;
import com.dailycodebuffer.userservice.VO.ResponseTemplateVO;
import com.dailycodebuffer.userservice.controller.UserController;
import com.dailycodebuffer.userservice.entity.User;
import com.dailycodebuffer.userservice.repository.UserRepository;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;

@Service
public class UserService {

	@Autowired
	private RestTemplate restTemplate;
	private Logger logger = LoggerFactory.getLogger(UserController.class);
	@Autowired
	private UserRepository userRepository;
	
	public User saveUser(User user) {
		logger.info("Inside saveUser method of UserService");
		return userRepository.save(user);
	}

	
	public ResponseTemplateVO getUserWithDepartment(Long userId) {
		logger.info("Inside getUserWithDepartment method of UserService");
		ResponseTemplateVO vo = new ResponseTemplateVO();
		User user = userRepository.findByUserId(userId);
		Department department = restTemplate.getForObject("http://DEPARTMENT-SERVICE/departments/"+user.getDepartmentId()
		, Department.class);
		vo.setUser(user);
		vo.setDepartment(department);
		return vo;
	}
	
}
