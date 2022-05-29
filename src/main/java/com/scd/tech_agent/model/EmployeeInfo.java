package com.scd.tech_agent.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

@ToString
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeInfo {
    private String id;
    private String firstName;
    private String lastName;
    private String gender;
    private String email;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private String hireDate;

    private String deptName;
    private String position;
}
