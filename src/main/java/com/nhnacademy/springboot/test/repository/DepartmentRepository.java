package com.nhnacademy.springboot.test.repository;

import com.nhnacademy.springboot.test.entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepartmentRepository extends JpaRepository<Department, String> {
}
