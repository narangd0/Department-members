package com.nhnacademy.springboot.test.service;

import com.nhnacademy.springboot.test.entity.Department;
import com.nhnacademy.springboot.test.repository.DepartmentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class DepartmentServiceImpl implements DepartmentService {
    private final DepartmentRepository departmentRepository;

    @Transactional
    @Override
    public Department saveDepartment(Department department) {
        return departmentRepository.save(department);
    }
}
