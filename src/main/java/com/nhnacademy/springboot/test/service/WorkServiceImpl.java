package com.nhnacademy.springboot.test.service;

import com.nhnacademy.springboot.test.domain.WorkDto;
import com.nhnacademy.springboot.test.entity.Work;
import com.nhnacademy.springboot.test.repository.WorkRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
@Transactional(readOnly = true)
public class WorkServiceImpl implements WorkService {
    private final WorkRepository workRepository;

    @Transactional
    @Override
    public Work saveWork(Work work) {
        return workRepository.save(work);
    }

    @Override
    public List<WorkDto> getDepartmentMembers(List<String> departmentIds) {
        List<WorkDto> workDtos = new ArrayList<>();
        for (String departmentId : departmentIds) {
            // TODO 정규식 등으로 체크...
            // 사원 정보가 없으면 사이즈가 0이니까 이부부 ㄴ생각
            List<WorkDto> work = workRepository.getDepartmentMembers(departmentId);
            workDtos.addAll(work);
        }
        return workDtos;
    }
}
