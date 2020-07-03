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
    public static final String CONSONANT = "bBcCdDfFgGhHjJkKlLmMnNpPqQrRsStTvVwWxXzZ" +
            "бБвВгГдДжЖзЗйЙкКлЛмМнНпПрРсСтТфФхХцЦчЧшШщЩъЪьЬ";

    @Override
    public String deleteNotLetterExceptSpace(String inputString) throws ProjectException {
        if (inputString == null) {
            throw new ProjectException("Input data is incorrect");
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
    public String deleteWordsStartedConsonant(String inputString, int wordLength) throws ProjectException {
        DataValidator dataValidator = new DataValidator();
        if (inputString == null || !dataValidator.isFitInString(inputString, wordLength)) {
            throw new ProjectException("Input data is incorrect");
        }
        int startPosition = 0;
        int separatorLength = ALL_SEPARATOR.length();
        char[] arrayChar = inputString.toCharArray();
        int i = 0;
        while (i < arrayChar.length) {
            for (int j = 0; j < separatorLength; j++) {
                if (arrayChar[i] == ALL_SEPARATOR.charAt(j)) {
                    if ((i - startPosition) == wordLength &&
                            (isConsonant(arrayChar[startPosition]))) {
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
                (isConsonant(arrayChar[startPosition]))) {
            arrayChar = deleteOneWord(arrayChar, startPosition, wordLength);
        }
        return new String(arrayChar);
    }

    private boolean isConsonant(char letter) {
        int amountOfConsonant = CONSONANT.length();
        for (int i = 0; i < amountOfConsonant; i++) {
            if (letter == CONSONANT.charAt(i)) {
                return true;
            }
        }
        return false;
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