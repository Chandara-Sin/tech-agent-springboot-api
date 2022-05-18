package com.scd.tech_agent.repository;

import com.scd.tech_agent.entity.Department;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, Integer> {

    boolean existsByDeptName(String deptName);

    boolean existsById(Integer deptId);

    Optional<Department> findByDeptName(String deptName);

}
