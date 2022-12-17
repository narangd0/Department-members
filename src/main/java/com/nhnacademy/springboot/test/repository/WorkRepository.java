package com.nhnacademy.springboot.test.repository;

import com.nhnacademy.springboot.test.entity.Work;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WorkRepository extends JpaRepository<Work, Work.Pk>, WorkRepositoryCustom {
}
