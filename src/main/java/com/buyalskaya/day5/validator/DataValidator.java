package com.buyalskaya.day5.validator;

import java.util.regex.Pattern;

public class DataValidator {
    private static final String CHECK_LETTER = "\\pL";

    public boolean isLetter(char symbol) {
        return Pattern.matches(CHECK_LETTER, String.valueOf(symbol));
    }

    public boolean isFitInString(String inputString, int length) {
        if (inputString == null || length <= 0) {
            return false;
        }
        return inputString.length() >= length;
    }
}