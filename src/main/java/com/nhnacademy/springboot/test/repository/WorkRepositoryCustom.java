package com.nhnacademy.springboot.test.repository;

import com.nhnacademy.springboot.test.domain.WorkDto;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.List;

@NoRepositoryBean
public interface WorkRepositoryCustom {
    List<WorkDto> getDepartmentMembers(String departmentId);
}
