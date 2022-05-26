package com.scd.tech_agent.model.mapper;

import com.scd.tech_agent.entity.Employee;
import com.scd.tech_agent.model.dto.EmployeeDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface EmployeeMapper {
    Employee toEmployee(EmployeeDto employeeDto);
}
