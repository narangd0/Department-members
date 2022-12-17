package com.nhnacademy.springboot.test.domain;

import com.nhnacademy.springboot.test.entity.Department;
import com.nhnacademy.springboot.test.entity.Member;
import com.querydsl.core.annotations.QueryProjection;
import lombok.Data;

@Data
public class WorkDto {
    private Department department;
    private Member member;

    @QueryProjection
    public WorkDto(Department department, Member member) {
        this.department = department;
        this.member = member;
    }
}
