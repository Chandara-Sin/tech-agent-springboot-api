package com.scd.tech_agent.controller;

import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import com.scd.tech_agent.exception.DataNotFound;
import com.scd.tech_agent.model.Employees;
import com.scd.tech_agent.service.EmployeeService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1")
public class EmployeesController {

    @Autowired
    EmployeeService employeeServ;

    @GetMapping("/employees")
    public List<Employees> getAllEmployees() {
        return employeeServ.getAllEmployees();
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @PostMapping("/employee")
    public ResponseEntity<Employees> addEmployee(@Valid @RequestBody Employees dataRequest) {
        return employeeServ.addEmployee(dataRequest);
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @PutMapping("/employee/{id}")
    public ResponseEntity<Employees> updateEmployee(@PathVariable(value = "id") Integer emp_id,
            @Valid @RequestBody Employees employeeDetails) throws DataNotFound {
        return employeeServ.updateEmployee(emp_id, employeeDetails);
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @DeleteMapping("/employee/{id}")
    public ResponseEntity<Map<String, String>> deleteEmployee(@PathVariable(value = "id") Integer emp_id)
            throws DataNotFound {
        return employeeServ.deleteEmployee(emp_id);
    }

}
