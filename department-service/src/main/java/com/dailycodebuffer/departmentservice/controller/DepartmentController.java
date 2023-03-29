package com.dailycodebuffer.departmentservice.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dailycodebuffer.departmentservice.entity.Department;
import com.dailycodebuffer.departmentservice.service.DepartmentService;

@RestController
@RequestMapping("/departments")
public class DepartmentController {

	private Logger logger = LoggerFactory.getLogger(DepartmentController.class);
	
	@Autowired
	private DepartmentService departmentService;
	
	@PostMapping("/")
	public Department saveDepartment(@RequestBody Department department) {
		logger.info("Inside saveDepartment method of DepartmentController");
		Department dept = departmentService.saveDepartment(department);
		logger.info("data returned"+dept);
		return dept;
	}
	
	@GetMapping("/{id}")
	public Department findByDepartmentId(@PathVariable("id") Long departmentId) {
		logger.info("Inside findByDepartmentId method of DepartmentController");
		return departmentService.findByDepartmentId(departmentId);
	}
}
