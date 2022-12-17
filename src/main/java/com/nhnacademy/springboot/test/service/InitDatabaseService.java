package com.nhnacademy.springboot.test.service;

import com.nhnacademy.springboot.test.config.InitFileProperties;
import com.nhnacademy.springboot.test.entity.Department;
import com.nhnacademy.springboot.test.entity.Member;
import com.nhnacademy.springboot.test.entity.Work;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class InitDatabaseService {
    private final InitFileProperties fileProperties;
    private final DepartmentService departmentService;
    private final MemberService memberService;
    private final WorkService workService;

    @Transactional
    @EventListener(ApplicationReadyEvent.class)
    public void init() throws IOException {
        // init.file.name
        String fileName = fileProperties.getName();

        if (Objects.isNull(fileName)) { return; }

        List<String> table = Files.readAllLines(Path.of(fileName));

        for (int i = 3; i < table.size() - 1; i++) {
            String delimiter = "\\|";
            List<String> record = Arrays.stream(table.get(i).split(delimiter))
                    .map(String::trim).collect(Collectors.toList());
            saveRecord(record);
        }
    }

    private void saveRecord(List<String> recode) {
        Member member = new Member(recode.get(1), recode.get(2));
        Department department = new Department(recode.get(4), recode.get(3));
        Work work = new Work(new Work.Pk(department.getId(), member.getId()), department, member);

        memberService.saveMember(member);
        departmentService.saveDepartment(department);
        workService.saveWork(work);
    }
}
