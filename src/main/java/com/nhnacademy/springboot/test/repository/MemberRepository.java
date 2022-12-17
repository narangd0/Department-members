package com.nhnacademy.springboot.test.repository;

import com.nhnacademy.springboot.test.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, String> {
}
