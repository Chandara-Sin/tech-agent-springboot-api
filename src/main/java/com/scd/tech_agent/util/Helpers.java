package com.scd.tech_agent.util;

import java.util.List;

import com.scd.tech_agent.model.Employees;
import com.scd.tech_agent.repository.EmployeesRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Helpers {

    @Autowired
    EmployeesRepository employeesRepo;

    public Boolean checkDuplicateEmail(String value) {
        List<Employees> duplicate_email = employeesRepo.findByEmail(value);
        return !duplicate_email.isEmpty();
    }

    public Boolean validateGender(String value) {
        Boolean is_valid = false;
        for (Gender all_gender : Gender.values()) {
            if (all_gender.toString() != value) {
                is_valid = false;
                break;
            } else
                is_valid = true;
        }
        return is_valid;
    }
}
