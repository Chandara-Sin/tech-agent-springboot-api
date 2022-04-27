package com.scd.tech_agent.repository;

import com.scd.tech_agent.model.Employees;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface EmployeesRepository extends JpaRepository<Employees, Integer> {

        // Wrap database operations in a transaction to prevent data corruption.
        @Transactional
        @Modifying
        @Query(value = "INSERT INTO tb_employees (first_name,last_name,gender,hire_date,salr_id,dept_id,postn_id)" +
                        "VALUES (:first_name,:last_name,:gender,:hire_date,:salr_id,:dept_id,:postn_id) ", nativeQuery = true)
        Integer addEmployee(@Param("first_name") String first_name, @Param("last_name") String last_name,
                        @Param("gender") String gender, @Param("hire_date") String hire_date,
                        @Param("salr_id") Integer salr_id,
                        @Param("dept_id") Integer dept_id, @Param("postn_id") Integer postn_id);

}
