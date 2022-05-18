package com.scd.tech_agent.repository;

import com.scd.tech_agent.entity.Employee;
import com.scd.tech_agent.entity.Position;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PositionRepository extends JpaRepository<Position, Integer> {

    boolean existsByPosition(String position);

    List<Position> findAllByDeptId(Integer deptId);
}