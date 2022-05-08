package com.scd.tech_agent.repository;

import java.util.Optional;
import java.util.UUID;

import com.scd.tech_agent.model.Employee;

import org.springframework.data.jpa.repository.JpaRepository;
// import org.springframework.data.jpa.repository.Modifying;
// import org.springframework.data.jpa.repository.Query;
// import org.springframework.data.repository.query.Param;
// import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

        Boolean existsByEmail(String email);

        Optional<Employee> findById(UUID id);

        Boolean existsByEmailAndIdNot(String email, UUID id);

        // Wrap database operations in a transaction to prevent data corruption.
        // @Transactional
        // @Modifying
        // @Query(value = "INSERT INTO tb_employees
        // (first_name,last_name,gender,hire_date,salr_id,dept_id,postn_id)" +
        // "VALUES
        // (:first_name,:last_name,:gender,:hire_date,:salr_id,:dept_id,:postn_id) ",
        // nativeQuery = true)
        // Integer addEmployee(String first_name, String last_name, String gender,
        // String hire_date,
        // Integer salr_id, Integer dept_id, Integer postn_id);

        // @Query(value = "SELECT * FROM tb_employees ORDER BY emp_id DESC LIMIT 1",
        // nativeQuery = true)
        // Employees getNewEmployee();

        // @Query(value = "SELECT COUNT(email) FROM tb_employees WHERE email = :email ",
        // nativeQuery = true)

}
