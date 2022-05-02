package com.scd.tech_agent.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.scd.tech_agent.exception.DataInvalid;
import com.scd.tech_agent.exception.DataNotFound;
import com.scd.tech_agent.model.Employees;
import com.scd.tech_agent.repository.EmployeesRepository;
import com.scd.tech_agent.util.Gender;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class EmployeeService {

    @Autowired
    EmployeesRepository employeesRepo;

    public Boolean checkDuplicateEmail(String value) {
        List<Employees> duplicate_email = employeesRepo.findByEmail(value);
        return !duplicate_email.isEmpty() ? true : false;
    }

    public Boolean validateGender(String value) {
        Boolean is_valid = false;
        for (Gender all_gender : Gender.values()) {
            if (all_gender.toString() != value) {
                is_valid = false;
                break;
            } else {
                is_valid = true;
            }
        }
        return is_valid;
    }

    public List<Employees> getAllEmployees() {
        return employeesRepo.findAll();
    }

    public Employees addEmployee(Employees datarequest) throws DataInvalid, Exception {

        if (checkDuplicateEmail(datarequest.getEmail()))
            throw new DataInvalid("duplicate email");

        if (!validateGender(datarequest.getGender()))
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
        // System.out.print(employeesRepo.getNewEmployee());
        // Employees new_employee = employeesRepo.getNewEmployee();
        // return new_employee;
        // } else {
        // Employees new_employee = employeesRepo.getNewEmployee();
        // return new_employee;
        // }
        //

        try {
            return employeesRepo.save(datarequest);
        } catch (Exception e) {
            throw new Exception("Some error occurred while creating the Employee");
        }
    }

    public Employees updateEmployee(Integer emp_id, Employees employeeDetails)
            throws DataNotFound, DataInvalid, Exception {
        Employees employee = employeesRepo.findById(emp_id)
                .orElseThrow(() -> new DataNotFound("Employee not found for this id : " + emp_id));

        if (checkDuplicateEmail(employeeDetails.getEmail()))
            throw new DataInvalid("duplicate email");

        if (!validateGender(employeeDetails.getGender()))
            throw new DataInvalid("gender value : not_known, male, female, not_application");

        employee.setFirst_name(employeeDetails.getFirst_name());
        employee.setLast_name(employeeDetails.getLast_name());
        employee.setEmail(employeeDetails.getEmail());
        employee.setGender(employeeDetails.getGender());
        employee.setHire_date(employeeDetails.getHire_date());
        employee.setSalr_id(employeeDetails.getSalr_id());
        employee.setDept_id(employeeDetails.getDept_id());
        employee.setPostn_id(employeeDetails.getPostn_id());

        try {
            return employeesRepo.save(employee);
        } catch (Exception e) {
            throw new Exception("Some error occurred while updating the Employee");
        }

    }

    public Map<String, String> deleteEmployee(Integer emp_id) throws DataNotFound, Exception {
        Employees employee = employeesRepo.findById(emp_id)
                .orElseThrow(() -> new DataNotFound("Employee not found for this id : " + emp_id));
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
