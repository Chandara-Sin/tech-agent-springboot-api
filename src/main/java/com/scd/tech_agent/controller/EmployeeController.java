package com.scd.tech_agent.controller;

import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.validation.Valid;

import com.scd.tech_agent.model.Employee;
import com.scd.tech_agent.service.EmployeeService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/employees")
public class EmployeeController {

    @Autowired
    EmployeeService employeeServ;

    @GetMapping("")
    public ResponseEntity<List<Employee>> getAllEmployees() throws Exception {
        return new ResponseEntity<>(employeeServ.getAllEmployees(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable(value = "id") String id) throws Exception {
        UUID emp_id = UUID.fromString(id);
        return new ResponseEntity<>(employeeServ.getEmployeeById(emp_id), HttpStatus.OK);
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @PostMapping("")
    public ResponseEntity<Employee> addEmployee(@Valid @RequestBody Employee dataRequest)
            throws Exception {
        return new ResponseEntity<Employee>(employeeServ.addEmployee(dataRequest), HttpStatus.CREATED);
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @PutMapping("/{id}")
    public ResponseEntity<Employee> updateEmployee(@PathVariable(value = "id") String id,
            @Valid @RequestBody Employee employeeDetails) throws Exception {
        UUID emp_id = UUID.fromString(id);
        return new ResponseEntity<>(employeeServ.updateEmployee(emp_id, employeeDetails), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, String>> deleteEmployee(@PathVariable(value = "id") String id)
            throws Exception {
        UUID emp_id = UUID.fromString(id);
        return new ResponseEntity<>(employeeServ.deleteEmployee(emp_id), HttpStatus.OK);
    }

}
