package com.scd.tech_agent.service;

import com.scd.tech_agent.exception.DataInvalid;
import com.scd.tech_agent.exception.DataNotFound;
import com.scd.tech_agent.entity.Department;
import com.scd.tech_agent.model.dto.DepartmentDto;
import com.scd.tech_agent.model.mapper.DepartmentMapper;
import com.scd.tech_agent.repository.DepartmentRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class DepartmentService {

    private final DepartmentRepository departmentRepo;
    private final DepartmentMapper departmentMapper;

    public List<Department> getDepartmentList() {
        List<Department> departmentList;
        try {
            departmentList = departmentRepo.findAll();
        } catch (RuntimeException e) {
            throw new RuntimeException("Some error occurred while retrieving Departments");
        }
        if (departmentList.isEmpty())
            throw new DataNotFound("Found no Departments in Database");
        return departmentList;
    }

    public Department addDepartment(DepartmentDto dataRequest) {
        if (departmentRepo.existsByDeptName(dataRequest.getDepartment()))
            throw new DataInvalid("duplicate department");

        var department = departmentMapper.toDepartment(dataRequest);
        department.setCreatedAt(LocalDateTime.now());
        department.setUpdatedAt(LocalDateTime.now());

        try {
            return departmentRepo.save(department);
        } catch (RuntimeException e) {
            throw new RuntimeException("Some error occurred while creating the Department");
        }
    }

    public Department updateDepartment(Integer deptId, DepartmentDto dataRequest) {
        Department department = departmentRepo.findById(deptId)
                .orElseThrow(() -> new DataNotFound("Department not found for this id : " + deptId));

        if (departmentRepo.existsByDeptName(dataRequest.getDepartment()))
            throw new DataInvalid("duplicate department name");

        department.setDeptName(dataRequest.getDepartment());
        department.setUpdatedAt(LocalDateTime.now());

        try {
            return departmentRepo.save(department);
        } catch (RuntimeException e) {
            throw new RuntimeException("Some error occurred while updating the department");
        }
    }

    public Map<String, String> deleteDepartment(Integer deptId) {
        Department department = departmentRepo.findById(deptId)
                .orElseThrow(() -> new DataNotFound("Department not found for this id : " + deptId));

        try {
            departmentRepo.delete(department);
        } catch (RuntimeException e) {
            throw new RuntimeException("Could not delete Department with id : " + deptId);
        }
        Map<String, String> response = new HashMap<>();
        response.put("message", "Department was deleted successfully");
        response.put("status", HttpStatus.OK.toString());
        return response;
    }
}
