package com.buyalskaya.day5.service.impl;

import com.buyalskaya.day5.exception.ProjectException;
import com.buyalskaya.day5.service.DeleteStringService;
import com.buyalskaya.day5.validator.DataValidator;

public class StringFunctionDeleteServiceImpl implements DeleteStringService {
    public static final char SPACE = ' ';
    public static final String SYMBOL_SPACE = "\t\n\r";
    public static final String ALL_SPACE = SYMBOL_SPACE + SPACE;
    public static final String PUNCT_WITHOUT_DASH = "!\"#$%&'()*+,./:;<=>?@[\\]^_`{|}~";
    public static final String PUNCT = "!\"#$%&'-()*+,./:;<=>?@[\\]^_`{|}~";
    public static final String DIGIT = "0123456789";
    public static final String SEPARATOR = SYMBOL_SPACE + PUNCT + DIGIT;
    public static final String ALL_SEPARATOR = ALL_SPACE + PUNCT_WITHOUT_DASH + DIGIT;

    @Override
    public String deleteNotLetterExceptSpace(String inputString) throws ProjectException {
        if (inputString == null) {
            throw new ProjectException("Input string is null");
        }
        int separatorLength = SEPARATOR.length();
        StringBuilder resultString = new StringBuilder(inputString);
        int indexSeparator;
        for (int i = 0; i < separatorLength; i++) {
            indexSeparator = inputString.indexOf(SEPARATOR.charAt(i));
            while (indexSeparator != -1) {
                resultString.setCharAt(indexSeparator, SPACE);
                indexSeparator = inputString.indexOf(SEPARATOR.charAt(i), indexSeparator + 1);
            }
        }
        return resultString.toString();
    }

    @Override
    public String deleteWords(String inputString, int wordLength, char startLetter) throws ProjectException {
        DataValidator dataValidator = new DataValidator();
        if (!dataValidator.isFitInString(inputString, wordLength)) {
            throw new ProjectException("Word length is incorrect");
        }
        if (!dataValidator.isLetter(startLetter)) {
            throw new ProjectException("The word must start from letter");
        }
        int startPosition = 0;
        char lowerLetter = Character.toLowerCase(startLetter);
        char upperLetter = Character.toUpperCase(startLetter);
        int separatorLength = ALL_SEPARATOR.length();
        StringBuilder resultString = new StringBuilder(inputString);
        int i = 0;
        while (i < resultString.length()) {
            for (int j = 0; j < separatorLength; j++) {
                if (resultString.charAt(i) == ALL_SEPARATOR.charAt(j)) {
                    if ((i - startPosition) == wordLength &&
                            (resultString.charAt(startPosition) == lowerLetter ||
                                    resultString.charAt(startPosition) == upperLetter)) {
                        resultString.delete(startPosition, startPosition + wordLength);
                        startPosition = startPosition + 1;
                        i = i - wordLength;
                    } else {
                        startPosition = i + 1;
                    }
                }
            }
            i++;
        }
        if ((resultString.length() - startPosition) == wordLength &&
                (resultString.charAt(startPosition) == lowerLetter ||
                        resultString.charAt(startPosition) == upperLetter)) {
            resultString.delete(startPosition, startPosition + wordLength);
        }
        return resultString.toString();
    }
}