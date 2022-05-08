package com.scd.tech_agent.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import com.scd.tech_agent.exception.DataInvalid;
import com.scd.tech_agent.exception.DataNotFound;
import com.scd.tech_agent.model.Employee;
import com.scd.tech_agent.repository.EmployeeRepository;
import com.scd.tech_agent.util.Helpers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class EmployeeService {

    @Autowired
    EmployeeRepository employeesRepo;

    @Autowired
    Helpers helpers;

    public List<Employee> getAllEmployees() throws Exception {
        try {
            return employeesRepo.findAll();
        } catch (Exception e) {
            throw new Exception("Some error occurred while retrieving Employees");
        }
    }

    public Employee getEmployeeById(UUID emp_id) throws DataNotFound {
        return employeesRepo.findById(emp_id)
                .orElseThrow(() -> new DataNotFound("Employee not found for this id : " + emp_id));
    }

    public Employee addEmployee(Employee datarequest) throws Exception {
        if (employeesRepo.existsByEmail(datarequest.getEmail()))
            throw new DataInvalid("duplicate email");
        if (!helpers.validateGender(datarequest.getGender()))
            throw new DataInvalid("gender value : not_known, male, female, not_application");

        // System.out.println(dataparam.getFirst_name());
        // System.out.println(dataparam.getLast_name());
        // System.out.println(dataparam.getGender());
        // System.out.println(dataparam.getHire_date());
        // System.out.println(dataparam.getSalr_id());
        // System.out.println(dataparam.getDept_id());
        // String first_name = dataparam.getFirst_name();
        // String last_name = dataparam.getLast_name();
        // String gender = dataparam.getGender();
        // String hire_date = dataparam.getHire_date();
        // Integer salr_id = dataparam.getSalr_id();
        // Integer dept_id = dataparam.getDept_id();
        // Integer postn_id = dataparam.getPostn_id();
        // Integer is_success = employeesRepo.addEmployee(first_name, last_name, gender,
        // hire_date, salr_id, dept_id,
        // postn_id);
        // if (is_success != 0) {
        // Employees new_employee = employeesRepo.getNewEmployee();
        // return new_employee;
        // }

        try {
            return employeesRepo.save(datarequest);
        } catch (Exception e) {
            throw new Exception("Some error occurred while creating the Employee");
        }
    }

    public Employee updateEmployee(UUID emp_id, Employee employeeDetails)
            throws Exception {
        Employee employee = employeesRepo.findById(emp_id)
                .orElseThrow(() -> new DataNotFound("Employee not found for this id : " + emp_id));

        if (employeesRepo.existsByEmailAndIdNot(employeeDetails.getEmail(), emp_id))
            throw new DataInvalid("duplicate email");

        if (!helpers.validateGender(employeeDetails.getGender()))
            throw new DataInvalid("gender value : not_known, male, female, not_application");

        employee.setFirst_name(employeeDetails.getFirst_name());
        employee.setLast_name(employeeDetails.getLast_name());
        employee.setEmail(employeeDetails.getEmail());
        employee.setGender(employeeDetails.getGender());
        employee.setHire_date(employeeDetails.getHire_date());
        employee.setDept_id(employeeDetails.getDept_id());
        employee.setPostn_id(employeeDetails.getPostn_id());

        try {
            return employeesRepo.save(employee);
        } catch (Exception e) {
            throw new Exception("Some error occurred while updating the Employee");
        }

    }

    public Map<String, String> deleteEmployee(UUID emp_id) throws Exception {
        Employee employee = employeesRepo.findById(emp_id).orElseThrow(
                () -> new DataNotFound("Employee not found for this id : " + emp_id));

        try {
            employeesRepo.delete(employee);
        } catch (Exception e) {
            throw new Exception("Could not delete Employee with id : " + emp_id);
        }
        Map<String, String> response = new HashMap<>();
        response.put("message", "Employee was deleted successfully");
        response.put("status_code", HttpStatus.OK.toString());
        return response;
    }

}
