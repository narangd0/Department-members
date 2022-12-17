package com.nhnacademy.springboot.test.controller;

import com.nhnacademy.springboot.test.domain.WorkDto;
import com.nhnacademy.springboot.test.exception.DepartmentIdsNotFoundException;
import com.nhnacademy.springboot.test.service.WorkService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Objects;

@RestController
@RequiredArgsConstructor
public class DepartmentController {
    private final WorkService workService;

    @GetMapping("/department-members")
    public List<WorkDto> getDepartmentMembers(@RequestHeader(value = "Accept", required = false) String header,
                                              @RequestParam(value = "departmentIds", required = false) List<String> departmentIds) {
//        System.out.println("### header: " + header);

        // departmentIds 가 없거나 Accept 헤더가 application/json 이 아니면 400 응답
        if (departmentIds.isEmpty()) {
            throw new DepartmentIdsNotFoundException();
        }

        if (Objects.isNull(header) || !header.contains("application/json")) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }

        // TODO 여기나 내부에서 departmentIds 검증!!
        return workService.getDepartmentMembers(departmentIds);
    }
}
