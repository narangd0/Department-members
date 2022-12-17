package com.nhnacademy.springboot.test.repository;

import com.nhnacademy.springboot.test.domain.QWorkDto;
import com.nhnacademy.springboot.test.domain.WorkDto;
import com.nhnacademy.springboot.test.entity.QMember;
import com.nhnacademy.springboot.test.entity.QWork;
import com.nhnacademy.springboot.test.entity.Work;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import java.util.List;

public class WorkRepositoryImpl extends QuerydslRepositorySupport implements WorkRepositoryCustom {
    public WorkRepositoryImpl() {
        super(Work.class);
    }

    @Override
    public List<WorkDto> getDepartmentMembers(String departmentId) {
        QWork work = QWork.work;
        QMember member = QMember.member;


        // FIXME 근데 DB에서 가져올 때.. 괜히 select문을 썼어야할 이유가 있나?
        // Work와 WorkDto가 동일하게 생겼는데.....
        // service layer에서 변환시켜주었으면 됐을 듯...
        return from(work)
                .innerJoin(work.member, member).fetchJoin()
                .where(work.department.id.eq(departmentId))
                .select(new QWorkDto(work.department, work.member))
                .orderBy(member.id.asc())
                .fetch();

//        return null;
    }
}
