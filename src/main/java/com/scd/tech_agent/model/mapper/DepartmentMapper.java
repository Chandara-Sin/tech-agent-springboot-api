package com.scd.tech_agent.model.mapper;

import com.scd.tech_agent.model.dto.DepartmentDto;
import com.scd.tech_agent.entity.Department;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface DepartmentMapper {
    @Mapping(target = "deptName", source = "department")
    Department toDepartment(DepartmentDto departmentDto);
}
