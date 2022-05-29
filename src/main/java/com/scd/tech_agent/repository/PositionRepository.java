package com.scd.tech_agent.repository;

import com.scd.tech_agent.entity.Position;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PositionRepository extends JpaRepository<Position, Integer> {

    boolean existsByPosition(String position);

    boolean existsByPositionAndIdNot(String position, Integer postnId);

    List<Position> findAllByDeptId(Integer deptId);

    Optional<Position> findByPosition(String position);
}
