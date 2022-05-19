package com.scd.tech_agent.service;

import java.time.LocalDateTime;
import java.util.*;

import com.scd.tech_agent.entity.Department;
import com.scd.tech_agent.entity.Position;
import com.scd.tech_agent.exception.DataInvalid;
import com.scd.tech_agent.exception.DataNotFound;
import com.scd.tech_agent.entity.Employee;
import com.scd.tech_agent.model.EmployeeInfo;
import com.scd.tech_agent.repository.DepartmentRepository;
import com.scd.tech_agent.repository.EmployeeRepository;
import com.scd.tech_agent.repository.PositionRepository;
import com.scd.tech_agent.util.Helpers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class EmployeeService {

    @Autowired
    EmployeeRepository employeesRepo;

    @Autowired
    DepartmentRepository departmentRepo;

    @Autowired
    PositionRepository positionRepo;

    @Autowired
    Helpers helpers;

    public List<Employee> getEmployeeList() {
        List<Employee> employeeList = employeesRepo.findAll();
        if (employeeList.isEmpty()) {
            throw new RuntimeException("Some error occurred while retrieving Employees");
        }else return employeeList;
    }

    public Employee getEmployee(UUID empId) {
        return employeesRepo.findById(empId)
                .orElseThrow(() -> new DataNotFound("Employee not found for this id : " + empId));
    }

    public List<Employee> getEmployeeListByDepartment(Integer deptId) {
        List<Employee> employeeList = employeesRepo.findAllByDeptId(deptId);
        if (employeeList.isEmpty()) {
            throw new DataNotFound("Employees Not Found with this department id : " + deptId);
        } else return employeeList;
    }

    public List<Employee> getEmployeeListByPosition(Integer postnId) {
        List<Employee> employeeList = employeesRepo.findAllByPostnId(postnId);
        if (employeeList.isEmpty()) {
            throw new DataNotFound("Employees Not Found with this position id : " + postnId);
        } else return employeeList;
    }

    public Employee addEmployee(EmployeeInfo dataRequest) {
        if (employeesRepo.existsByEmail(dataRequest.getEmail()))
            throw new DataInvalid("duplicate email");
        if (helpers.validateGender(dataRequest.getGender()))
            throw new DataInvalid("gender value : not_known, male, female, not_application");

        Department department = departmentRepo.findByDeptName(dataRequest.getDeptName())
                .orElseGet(() -> departmentRepo.save(new Department(null, dataRequest.getDeptName(), LocalDateTime.now(), LocalDateTime.now())));

        Position position = positionRepo.findByPosition(dataRequest.getPosition())
                .orElseGet(() -> positionRepo.save(new Position(null,dataRequest.getPosition(),LocalDateTime.now(),LocalDateTime.now(),department,department.getId())));

        Employee employee = new Employee();
        employee.setFirstName(dataRequest.getFirstName());
        employee.setLastName(dataRequest.getLastName());
        employee.setEmail(dataRequest.getEmail());
        employee.setGender(dataRequest.getGender());
        employee.setHireDate(dataRequest.getHireDate());
        employee.setDeptId(department.getId());
        employee.setPostnId(position.getId());

        try {
            return employeesRepo.save(employee);
        } catch (RuntimeException e) {
            throw new RuntimeException("Some error occurred while creating the Employee");
        }
    }

    public Employee updateEmployee(UUID empId, EmployeeInfo dataRequest) {
        Employee employee = employeesRepo.findById(empId)
                .orElseThrow(() -> new DataNotFound("Employee not found for this id : " + empId));

        if (employeesRepo.existsByEmailAndIdNot(dataRequest.getEmail(), empId))
            throw new DataInvalid("duplicate email");

        if (helpers.validateGender(dataRequest.getGender()))
            throw new DataInvalid("gender value : not_known, male, female, not_application");

        Department department = departmentRepo.findByDeptName(dataRequest.getDeptName())
                .orElseGet(() -> departmentRepo.save(new Department(null, dataRequest.getDeptName(),  LocalDateTime.now(),  LocalDateTime.now())));

        Position position = positionRepo.findByPosition(dataRequest.getPosition())
                .orElseGet(() -> positionRepo.save(new Position(null,dataRequest.getPosition(), LocalDateTime.now(), LocalDateTime.now(),department,department.getId())));

        employee.setFirstName(dataRequest.getFirstName());
        employee.setLastName(dataRequest.getLastName());
        employee.setEmail(dataRequest.getEmail());
        employee.setGender(dataRequest.getGender());
        employee.setHireDate(dataRequest.getHireDate());
        employee.setDeptId(department.getId());
        employee.setPostnId(position.getId());

        try {
            return employeesRepo.save(employee);
        } catch (RuntimeException e) {
            throw new RuntimeException("Some error occurred while updating the Employee");
        }
    }

    public Map<String, String> deleteEmployee(UUID empId) {
        Employee employee = employeesRepo.findById(empId).orElseThrow(
                () -> new DataNotFound("Employee not found for this id : " + empId));

        try {
            employeesRepo.delete(employee);
        } catch (RuntimeException e) {
            throw new RuntimeException("Could not delete Employee with id : " + empId);
        }
        Map<String, String> response = new HashMap<>();
        response.put("message", "Employee was deleted successfully");
        response.put("status_code", HttpStatus.OK.toString());
        return response;
    }

}
