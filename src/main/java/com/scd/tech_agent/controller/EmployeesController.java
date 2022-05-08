package com.scd.tech_agent.controller;

import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import com.scd.tech_agent.model.Employees;
import com.scd.tech_agent.service.EmployeeService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1")
public class EmployeesController {

    @Autowired
    EmployeeService employeeServ;

    @GetMapping("/employees")
    public ResponseEntity<List<Employees>> getAllEmployees() throws Exception {
        return new ResponseEntity<>(employeeServ.getAllEmployees(), HttpStatus.OK);
    }

    @GetMapping("/employee/{id}")
    public ResponseEntity<Employees> getEmployeeById(@PathVariable(value = "id") Integer emp_id) throws Exception {
        return new ResponseEntity<>(employeeServ.getEmployeeById(emp_id), HttpStatus.OK);
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @PostMapping("/employee")
    public ResponseEntity<Employees> addEmployee(@Valid @RequestBody Employees dataRequest)
            throws Exception {
        return new ResponseEntity<Employees>(employeeServ.addEmployee(dataRequest), HttpStatus.CREATED);
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @PutMapping("/employee/{id}")
    public ResponseEntity<Employees> updateEmployee(@PathVariable(value = "id") Integer emp_id,
            @Valid @RequestBody Employees employeeDetails) throws Exception {
        return new ResponseEntity<>(employeeServ.updateEmployee(emp_id, employeeDetails), HttpStatus.OK);
    }

    @DeleteMapping("/employee/{id}")
    public ResponseEntity<Map<String, String>> deleteEmployee(@PathVariable(value = "id") Integer emp_id)
            throws Exception {
        return new ResponseEntity<>(employeeServ.deleteEmployee(emp_id), HttpStatus.OK);
    }

}
