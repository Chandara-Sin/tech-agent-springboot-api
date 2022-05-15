package com.scd.tech_agent.util;

import org.springframework.stereotype.Component;

@Component
public class Helpers {

    public boolean validateGender(String gender) {
        boolean isValid = false;
        for (Gender genders : Gender.values()) {
            if (genders.toString().equals(gender)) {
                isValid = true;
                break;
            }
        }
        return isValid;
    }
}
