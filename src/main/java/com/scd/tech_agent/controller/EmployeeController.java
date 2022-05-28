package com.scd.tech_agent.controller;

import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.validation.Valid;

import com.scd.tech_agent.entity.Employee;
import com.scd.tech_agent.model.dto.EmployeeDto;
import com.scd.tech_agent.service.EmployeeService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

//@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/v1")
public record EmployeeController(EmployeeService employeeServ) {

    @GetMapping("/employees")
    public ResponseEntity<List<Employee>> getEmployeeList() {
        return new ResponseEntity<>(employeeServ.getEmployeeList(), HttpStatus.OK);
    }

    @GetMapping("/employees/{empId}")
    public ResponseEntity<Employee> getEmployee(@PathVariable(value = "empId") String empId) {
        return new ResponseEntity<>(employeeServ.getEmployee(UUID.fromString(empId)), HttpStatus.OK);
    }

    @GetMapping("/departments/{deptId}/employees")
    public ResponseEntity<List<Employee>> getEmployeeListByDepartment(@PathVariable(value = "deptId") Integer deptId) {
        return new ResponseEntity<>(employeeServ.getEmployeeListByDepartment(deptId), HttpStatus.OK);
    }

    @GetMapping("/positions/{postnId}/employees")
    public ResponseEntity<List<Employee>> getEmployeeListByPosition(@PathVariable(value = "postnId") Integer postnId) {
        return new ResponseEntity<>(employeeServ.getEmployeeListByPosition(postnId), HttpStatus.OK);
    }

    @PostMapping("/employees")
    public ResponseEntity<Employee> addEmployee(@Valid @RequestBody EmployeeDto dataRequest) {
        return new ResponseEntity<>(employeeServ.addEmployee(dataRequest), HttpStatus.CREATED);
    }

    @PutMapping("/employees/{empId}")
    public ResponseEntity<Employee> updateEmployee(@PathVariable(value = "empId") String empId,
                                                   @Valid @RequestBody EmployeeDto dataRequest) {
        return new ResponseEntity<>(employeeServ.updateEmployee(UUID.fromString(empId), dataRequest), HttpStatus.OK);
    }

    @DeleteMapping("/employees/{empId}")
    public ResponseEntity<Map<String, String>> deleteEmployee(@PathVariable(value = "empId") String empId) {
        return new ResponseEntity<>(employeeServ.deleteEmployee(UUID.fromString(empId)), HttpStatus.OK);
    }

}
