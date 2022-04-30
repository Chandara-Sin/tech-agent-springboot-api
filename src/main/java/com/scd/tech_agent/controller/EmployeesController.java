package com.scd.tech_agent.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import com.scd.tech_agent.exception.DataNotFound;
import com.scd.tech_agent.model.Employees;
import com.scd.tech_agent.repository.EmployeesRepository;
import com.scd.tech_agent.util.Gender;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1")
public class EmployeesController {

    @Autowired
    EmployeesRepository employeesRepo;

    public String convertGender(Gender gender) {
        if (gender.equals(Gender.not_known))
            return "not_known";
        else if (gender.equals(Gender.male)) {
            return "male";
        } else if (gender.equals(Gender.female)) {
            return "female";
        } else {
            return "not_application";
        }
    }

    @GetMapping("/employees")
    public List<Employees> getAllEmployees() {
        return employeesRepo.findAll();
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @PostMapping("/employee")
    public ResponseEntity<Employees> addEmployee(@Valid @RequestBody Employees dataparam) {
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
        // Employees employee = new Employees();

        // status.code == 201
        return new ResponseEntity<Employees>(employeesRepo.save(dataparam), HttpStatus.CREATED);
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @PutMapping("/employee/{id}")
    public ResponseEntity<Employees> updateEmployee(@PathVariable(value = "id") Integer emp_id,
            @Valid @RequestBody Employees employeeDetails) throws DataNotFound {
        Employees employee = employeesRepo.findById(emp_id)
                .orElseThrow(() -> new DataNotFound("Employee not found for this id : " + emp_id));

        employee.setFirst_name(employeeDetails.getFirst_name());
        employee.setLast_name(employeeDetails.getLast_name());
        employee.setGender(employeeDetails.getGender());
        employee.setHire_date(employeeDetails.getHire_date());
        employee.setSalr_id(employeeDetails.getSalr_id());
        employee.setDept_id(employeeDetails.getDept_id());
        employee.setPostn_id(employeeDetails.getPostn_id());
        Employees updatedEmployee = employeesRepo.save(employee);
        return new ResponseEntity<Employees>(updatedEmployee, HttpStatus.OK);
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @DeleteMapping("/employee/{id}")
    public ResponseEntity<Map<String, String>> deleteEmployee(@PathVariable(value = "id") Integer emp_id)
            throws DataNotFound {
        Employees employee = employeesRepo.findById(emp_id)
                .orElseThrow(() -> new DataNotFound("Employee not found for this id : " + emp_id));

        employeesRepo.delete(employee);
        Map<String, String> response = new HashMap<>();
        response.put("message", "Employee was deleted successfully");
        return new ResponseEntity<Map<String, String>>(response, HttpStatus.OK);
    }

}
