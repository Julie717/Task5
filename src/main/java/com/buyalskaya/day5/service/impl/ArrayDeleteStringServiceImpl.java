package com.buyalskaya.day5.service.impl;

import com.buyalskaya.day5.exception.ProjectException;
import com.buyalskaya.day5.service.DeleteStringService;
import com.buyalskaya.day5.validator.DataValidator;

public class ArrayDeleteStringServiceImpl implements DeleteStringService {
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
        int inputStringLength = inputString.length();
        int separatorLength = SEPARATOR.length();
        char[] arrayChar = inputString.toCharArray();
        for (int i = 0; i < inputStringLength; i++) {
            for (int j = 0; j < separatorLength; j++) {
                if (arrayChar[i] == SEPARATOR.charAt(j)) {
                    arrayChar[i] = SPACE;
                }
            }
        }
        return new String(arrayChar);
    }

    @Override
    public String deleteWords(String inputString, int wordLength, char startLetter) throws ProjectException {
        if (inputString == null) {
            throw new ProjectException("Input string is null");
        }
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
        char[] arrayChar = inputString.toCharArray();
        int i = 0;
        while (i < arrayChar.length) {
            for (int j = 0; j < separatorLength; j++) {
                if (arrayChar[i] == ALL_SEPARATOR.charAt(j)) {
                    if ((i - startPosition) == wordLength &&
                            (arrayChar[startPosition] == lowerLetter || arrayChar[startPosition] == upperLetter)) {
                        arrayChar = deleteOneWord(arrayChar, startPosition, wordLength);
                        startPosition = startPosition + 1;
                        i = i - wordLength;
                    } else {
                        startPosition = i + 1;
                    }
                }
            }
            i++;
        }
        if ((arrayChar.length - startPosition) == wordLength &&
                (arrayChar[startPosition] == lowerLetter || arrayChar[startPosition] == upperLetter)) {
            arrayChar = deleteOneWord(arrayChar, startPosition, wordLength);
        }
        return new String(arrayChar);
    }

    private char[] deleteOneWord(char[] arrayChar, int startPosition, int wordLength) {
        int lengthArrayChar = arrayChar.length;
        char[] resultArray = new char[lengthArrayChar - wordLength];
        for (int i = 0; i < startPosition; i++) {
            resultArray[i] = arrayChar[i];
        }
        int nextPosition = startPosition + wordLength;
        int j = startPosition;
        for (int i = nextPosition; i < lengthArrayChar; i++) {
            resultArray[j] = arrayChar[i];
            j++;
        }
        return resultArray;
    }
}