package com.scd.tech_agent.controller;

import com.scd.tech_agent.entity.Department;
import com.scd.tech_agent.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/departments")
public class DepartmentController {

    @Autowired
    DepartmentService departmentServ;

    @GetMapping("")
    public ResponseEntity<List<Department>> getDepartmentList() {
        return new ResponseEntity<>(departmentServ.getDepartmentList(), HttpStatus.OK);
    }

    @PostMapping("")
    public ResponseEntity<Department> addDepartment(@Valid @RequestBody Department dataRequest) {
        System.out.println(dataRequest);
        return new ResponseEntity<>(departmentServ.addDepartment(dataRequest), HttpStatus.CREATED);
    }

    @PutMapping("/{deptId}")
    public ResponseEntity<Department> updateDepartment(@PathVariable(value = "deptId") Integer deptId,
                                                       @Valid @RequestBody Department dataRequest) {
        return new ResponseEntity<>(departmentServ.updateDepartment(deptId, dataRequest), HttpStatus.OK);
    }

    @DeleteMapping("/{deptId}")
    public ResponseEntity<Map<String, String>> deleteDepartment(@PathVariable(value = "deptId") Integer deptId) {
        return new ResponseEntity<>(departmentServ.deleteDepartment(deptId), HttpStatus.OK);
    }
}
