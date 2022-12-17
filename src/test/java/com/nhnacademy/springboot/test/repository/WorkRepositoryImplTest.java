package com.nhnacademy.springboot.test.repository;

import com.nhnacademy.springboot.test.domain.WorkDto;
import com.nhnacademy.springboot.test.entity.Department;
import com.nhnacademy.springboot.test.entity.Member;
import com.nhnacademy.springboot.test.entity.Work;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class WorkRepositoryTest {
    @Autowired
    TestEntityManager entityManager;

    @Autowired
    WorkRepository workRepository;

    @Test
    @DisplayName("departmentId 에 해당하는 member 가져오기")
    void testGetDepartmentMembers() {
        // given
        String departmentId = "L1111";
        Department department = new Department(departmentId, "백엔드9팀");
        Member member = new Member("11112222", "Manty");
        Work work = new Work(new Work.Pk(departmentId, member.getId()), department, member);
        entityManager.persist(work);

        // when
        List<WorkDto> departmentMembers = workRepository.getDepartmentMembers(departmentId);

        // then
        assertThat(departmentMembers).hasSize(1);
        assertThat(departmentMembers.get(0).getMember().getName()).isEqualTo("Manty");
    }
}