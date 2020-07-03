package com.buyalskaya.day5.service.impl;

import com.buyalskaya.day5.exception.ProjectException;
import com.buyalskaya.day5.service.DeleteStringService;
import com.buyalskaya.day5.validator.DataValidator;

public class RegexDeleteStringServiceImpl implements DeleteStringService {
    public static final String REGEX_NOT_LETTER_SPACE = "[^\\pL\\s]";
    public static final String REGEX_SPACE = " ";
    public static final String REGEX_WORD = "\\b[\\pL&&[^aAeEiIoOuUаyYАеЕёЁиИоОуУыЫэЭюЮяЯ]][\\pL-]{%d}\\b";
    public static final String EMPTY_STRING = "";

    @Override
    public String deleteNotLetterExceptSpace(String inputString) throws ProjectException {
        if (inputString == null) {
            throw new ProjectException("Input data is incorrect");
        }
        return inputString.replaceAll(REGEX_NOT_LETTER_SPACE, REGEX_SPACE);
    }

    @Override
    public String deleteWordsStartedConsonant(String inputString, int wordLength) throws ProjectException {
        DataValidator dataValidator = new DataValidator();
        if (inputString == null || !dataValidator.isFitInString(inputString, wordLength)) {
            throw new ProjectException("Input data is incorrect");
        }
        int lastIndex = wordLength - 1;
        String regexExpression = String.format(REGEX_WORD, lastIndex);
        return inputString.replaceAll(regexExpression, EMPTY_STRING);
    }
}