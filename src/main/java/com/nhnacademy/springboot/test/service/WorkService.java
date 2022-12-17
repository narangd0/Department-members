package com.nhnacademy.springboot.test.service;

import com.nhnacademy.springboot.test.domain.WorkDto;
import com.nhnacademy.springboot.test.entity.Work;

import java.util.List;

public interface WorkService {
    Work saveWork(Work work);

    List<WorkDto> getDepartmentMembers(List<String> departmentIds);
}
