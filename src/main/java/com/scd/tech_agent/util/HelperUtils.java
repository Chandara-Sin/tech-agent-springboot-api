package com.scd.tech_agent.util;

public class HelperUtils {

    public static boolean validateGender(String gender) {
        boolean isValid = false;
        for (Gender genders : Gender.values()) {
            if (genders.toString().equals(gender)) {
                isValid = true;
                break;
            }
        }
        return !isValid;
    }
}
