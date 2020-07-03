package com.buyalskaya.day5.service.impl;

import com.buyalskaya.day5.exception.ProjectException;
import com.buyalskaya.day5.service.ReplaceStringService;
import com.buyalskaya.day5.validator.DataValidator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexReplaceStringServiceImpl implements ReplaceStringService {
    public static final String REGEX_REPLACE_SYMBOL = "([\\pL-]{%d})([\\pL-])([\\pL-]*)";
    public static final String REGEX_REPLACEMENT = "$1%c$3";
    public static final String REGEX_REPLACE_LETTER = "([%c%c])(%c)";
    public static final String REGEX_REPLACEMENT_AFTER = "$1%c";
    public static final String REGEX_WORD = "\\b[\\pL-]{%d}\\b";

    @Override
    public String replaceSymbol(String inputString, char symbol, int position) throws ProjectException {
        DataValidator dataValidator = new DataValidator();
        if (inputString == null || inputString.equals("") ||
                !dataValidator.isFitInString(inputString, position)) {
            throw new ProjectException("Input data is incorrect");
        }
        int previousPosition = position - 1;
        String regexWord = String.format(REGEX_REPLACE_SYMBOL, previousPosition);
        Pattern pattern = Pattern.compile(regexWord);
        Matcher matcher = pattern.matcher(inputString);
        return matcher.replaceAll(String.format(REGEX_REPLACEMENT, symbol));
    }

    @Override
    public String replaceLetterAfterSuitableLetter(String inputString,
                                                   char letterAfterWhichReplacement, char replaceableLetter,
                                                   char newLetter) throws ProjectException {
        DataValidator dataValidator = new DataValidator();
        if (inputString == null ||
                !dataValidator.isLetter(letterAfterWhichReplacement) ||
                !dataValidator.isLetter(replaceableLetter) ||
                !dataValidator.isLetter(newLetter)) {
            throw new ProjectException("Input data is incorrect");
        }
        char lowerSymbolAfterWhichReplacement = Character.toLowerCase(letterAfterWhichReplacement);
        char upperSymbolAfterWhichReplacement = Character.toUpperCase(letterAfterWhichReplacement);
        char lowerReplaceableSymbol = Character.toLowerCase(replaceableLetter);
        char upperReplaceableSymbol = Character.toUpperCase(replaceableLetter);
        char lowerNewSymbol = Character.toLowerCase(newLetter);
        char upperNewSymbol = Character.toUpperCase(newLetter);
        String regexExpression = String.format(REGEX_REPLACE_LETTER, lowerSymbolAfterWhichReplacement,
                upperSymbolAfterWhichReplacement, lowerReplaceableSymbol);
        Pattern pattern = Pattern.compile(regexExpression);
        Matcher matcher = pattern.matcher(inputString);
        String resultString = matcher.replaceAll(String.format(REGEX_REPLACEMENT_AFTER, lowerNewSymbol));
        regexExpression = String.format(REGEX_REPLACE_LETTER, lowerSymbolAfterWhichReplacement,
                upperSymbolAfterWhichReplacement, upperReplaceableSymbol);
        pattern = Pattern.compile(regexExpression);
        matcher = pattern.matcher(resultString);
        resultString = matcher.replaceAll(String.format(REGEX_REPLACEMENT_AFTER, upperNewSymbol));
        return resultString;
    }

    @Override
    public String replaceWordSuitableLength(String inputString, int wordLength, String newSubstring)
            throws ProjectException {
        DataValidator dataValidator = new DataValidator();
        if (inputString == null || newSubstring == null ||
                !dataValidator.isFitInString(inputString, wordLength)) {
            throw new ProjectException("Input data is incorrect");
        }
        String regexExpression = String.format(REGEX_WORD, wordLength);
        return inputString.replaceAll(regexExpression, newSubstring);
    }
}