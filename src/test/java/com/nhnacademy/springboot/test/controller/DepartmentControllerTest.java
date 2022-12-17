package com.nhnacademy.springboot.test.controller;

import com.nhnacademy.springboot.test.domain.WorkDto;
import com.nhnacademy.springboot.test.entity.Department;
import com.nhnacademy.springboot.test.entity.Member;
import com.nhnacademy.springboot.test.service.WorkService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.http.*;

import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(DepartmentController.class)
class DepartmentControllerTest {
    @Autowired
    private MockMvc mvc;
    @MockBean
    private WorkService workService;
    private Department department;
    private Member member;
    private WorkDto workDto;

    @BeforeEach
    void setUp() {
        department = new Department("L1001", "백엔드100팀");
        member = new Member("12341234", "서민지");
        workDto = new WorkDto(department, member);
    }

    @Test
    @DisplayName("200 OK")
    void testGetDepartmentMembers_success() throws Exception {
        // given
        String id = "L1001";
        Department department = new Department("L1001", "백엔드100팀");
        Member member = new Member("11112222", "Manty");
        WorkDto workDto2 = new WorkDto(department, member);

        // when()
        // TODO given : BDD인데 , given도 알고 쓰냐?
        given(workService.getDepartmentMembers(List.of(id)))
                .willReturn(List.of(workDto, workDto2));

        // when then...
        mvc.perform(get("/department-members?departmentIds={id}", id).accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].department.id", equalTo(id)));
    }

    @Test
    @DisplayName("400 - departmentIds 가 존재하지 않으면")
    void testGetDepartmentMembers_fail_noDepartmentIdsParam() throws Exception {
        String id = "L1001";

        given(workService.getDepartmentMembers(List.of(id)))
                .willReturn(List.of(workDto));

        mvc.perform(get("/department-members?departmentIds=").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    @DisplayName("400 - accept : application/json 이 아니면")
    void testGetDepartmentMembers_fail_noAcceptHeader() throws Exception {
        String id = "L1001";

        given(workService.getDepartmentMembers(List.of(id)))
                .willReturn(List.of(workDto));

        mvc.perform(get("/department-members?departmentIds={id}", id))
                .andExpect(status().isBadRequest());
    }
}