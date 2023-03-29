package com.dailycodebuffer.departmentservice.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dailycodebuffer.departmentservice.entity.Department;
import com.dailycodebuffer.departmentservice.repository.DepartmentRepository;

@Service
public class DepartmentService {

	private Logger logger = LoggerFactory.getLogger(DepartmentService.class);
	@Autowired
	private DepartmentRepository departmentRepository;

	public Department saveDepartment(Department department) {
		
		logger.info("Inside saveDepartment method of DepartmentService");
		return departmentRepository.save(department);
	}

	public Department findByDepartmentId(Long departmentId) {
		logger.info("Inside findByDepartmentId method of DepartmentService");
		return departmentRepository.findByDepartmentId(departmentId);
	}
}
