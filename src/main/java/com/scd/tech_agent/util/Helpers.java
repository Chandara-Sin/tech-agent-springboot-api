package com.scd.tech_agent.util;

import org.springframework.stereotype.Component;

@Component
public class Helpers {

    public Boolean validateGender(String value) {
        Boolean is_valid = false;
        for (Gender all_gender : Gender.values()) {
            if (all_gender.toString().equals(value)) {
                is_valid = true;
                break;
            }
        }
        return is_valid;
    }
}
