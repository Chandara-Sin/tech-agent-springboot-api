package com.scd.tech_agent.repository;

import com.scd.tech_agent.entity.Position;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<Position, UUID> {

}
