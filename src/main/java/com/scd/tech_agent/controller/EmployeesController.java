package com.scd.tech_agent.controller;

import java.util.HashMap;
import java.util.Map;

import javax.validation.Valid;

import com.scd.tech_agent.exception.DataNotFound;
import com.scd.tech_agent.model.Employees;
import com.scd.tech_agent.repository.EmployeesRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class EmployeesController {

    @Autowired
    EmployeesRepository employeesRepo;

    // public String convertGender(Gender gender) {
    // if (gender.equals(Gender.not_known))
    // return "0";
    // else if (gender.equals(Gender.male)) {
    // return "1";
    // } else if (gender.equals(Gender.female)) {
    // return "2";
    // } else {
    // return "9";
    // }
    // }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @PostMapping("/addemployee")
    public Employees addEmployee(@Valid @RequestBody Employees dataparam) {
        // String first_name = dataparam.getFirst_name();
        // String last_name = dataparam.getLast_name();
        // String gender = convertGender(dataparam.getGender());
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
        return employeesRepo.save(dataparam);
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @DeleteMapping("/delemployee/{id}")
    public Map<String, String> deleteEmployee(@PathVariable(value = "id") Integer emp_id) throws DataNotFound {
        Employees employee = employeesRepo.findById(emp_id)
                .orElseThrow(() -> new DataNotFound("Not Found User with id : " + emp_id));

        employeesRepo.delete(employee);
        Map<String, String> response = new HashMap<>();
        response.put("message", "User was deleted successfully");
        return response;
    }

}
