package com.buyalskaya.day5.service.impl;

import com.buyalskaya.day5.exception.ProjectException;
import com.buyalskaya.day5.service.DeleteStringService;
import com.buyalskaya.day5.validator.DataValidator;

public class RegexDeleteStringServiceImpl implements DeleteStringService {
    public static final String REGEX_NOT_LETTER_SPACE = "[^\\pL\\s]";
    public static final String REGEX_SPACE = " ";
    public static final String REGEX_WORD =
            "(?<=[\\p{Punct}\\s\\d&&[^-]])([%c%c][\\pL-]{%d})(?=[\\p{Punct}\\s\\d&&[^-]])";
    public static final String REGEX_LAST_WORD = "(?<=[\\p{Punct}\\s\\d&&[^-]])([%c%c][\\pL-]{%d})$";
    public static final String REGEX_FIRST_WORD = "^([%c%c][\\pL-]{%d})(?=[\\p{Punct}\\s\\d&&[^-]])";
    public static final String REGEX_ONE_WORD = "^([%c%c][\\pL-]{%d})$";
    //TODO It's impossible to use \\b like a word's border because word "кое-как" is separated into 2 words,
    // but in the task it must be one word. Thus, here was added a check of previous and next symbol
    public static final String EMPTY_STRING = "";

    @Override
    public String deleteNotLetterExceptSpace(String inputString) throws ProjectException {
        if (inputString == null) {
            throw new ProjectException("Input string is null");
        }
        String regexExpression = REGEX_NOT_LETTER_SPACE;
        return inputString.replaceAll(regexExpression, REGEX_SPACE);
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
        char lowerLetter = Character.toLowerCase(startLetter);
        char upperLetter = Character.toUpperCase(startLetter);
        String regexExpression = String.format(REGEX_WORD, lowerLetter, upperLetter, wordLength - 1);
        inputString = inputString.replaceAll(regexExpression, EMPTY_STRING);
        regexExpression = String.format(REGEX_FIRST_WORD, lowerLetter, upperLetter, wordLength - 1);
        inputString = inputString.replaceAll(regexExpression, EMPTY_STRING);
        regexExpression = String.format(REGEX_LAST_WORD, lowerLetter, upperLetter, wordLength - 1);
        inputString = inputString.replaceAll(regexExpression, EMPTY_STRING);
        regexExpression = String.format(REGEX_ONE_WORD, lowerLetter, upperLetter, wordLength - 1);
        return inputString.replaceAll(regexExpression, EMPTY_STRING);
    }
}
