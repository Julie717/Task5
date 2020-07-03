package com.buyalskaya.day5.service.impl;

import com.buyalskaya.day5.exception.ProjectException;
import com.buyalskaya.day5.service.DeleteStringService;
import com.buyalskaya.day5.validator.DataValidator;

public class StringFunctionDeleteServiceImpl implements DeleteStringService {
    public static final char SPACE = ' ';
    public static final String SYMBOL_SPACE = "\t\n\r";
    public static final String PUNCT = "!\"#$%&'-()*+,./:;<=>?@[\\]^_`{|}~";
    public static final String DIGIT = "0123456789";
    public static final String SEPARATOR = SYMBOL_SPACE + PUNCT + DIGIT;
    public static final String VOWELS = "[aAeEiIoOuUyYаАеЕёЁиИоОуУыЫэЭюЮяЯ]";
    public static final String WORD_BORDER = "\\b(?!-)(?<!-)";

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
    public String deleteWordsStartedConsonant(String inputString, int wordLength) throws ProjectException {
        DataValidator dataValidator = new DataValidator();
        if (inputString == null || !dataValidator.isFitInString(inputString, wordLength)) {
            throw new ProjectException("Input data is incorrect");
        }
        StringBuilder resultString = new StringBuilder();
        String[] words = inputString.split(WORD_BORDER);
        char firstLetter;
        for (String word : words) {
            firstLetter = word.charAt(0);
            if (word.length() != wordLength || !dataValidator.isLetter(firstLetter)
                    || VOWELS.indexOf(firstLetter) >= 0) {
                resultString.append(word);
            }
        }
        return resultString.toString();
    }
}