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
    public static final String REGEX_WORD = "(?<=[\\p{Punct}\\s\\d&&[^-]])([\\pL-]{%d})(?=[\\p{Punct}\\s\\d&&[^-]])";
    //TODO It's impossible to use \\b like a word's border because word "кое-как" is separated into 2 words,
    // but in the task it must be one word. Thus, here was added a check of previous and next symbol
    public static final String REGEX_LAST_WORD = "(?<=[\\p{Punct}\\s\\d&&[^-]])([\\pL-]{%d})$";

    @Override
    public String replaceSymbol(String inputString, char symbol, int position) throws ProjectException {
        if (inputString == null || inputString == "") {
            throw new ProjectException("Input string is empty");
        }
        DataValidator dataValidator = new DataValidator();
        if (!dataValidator.isFitInString(inputString, position)) {
            throw new ProjectException("Position is incorrect");
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
        if (inputString == null) {
            throw new ProjectException("Input string is null");
        }
        DataValidator dataValidator = new DataValidator();
        if (!dataValidator.isLetter(letterAfterWhichReplacement) ||
                !dataValidator.isLetter(replaceableLetter) ||
                !dataValidator.isLetter(newLetter)) {
            throw new ProjectException("Incorrect input symbols: they must be letters.");
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
        if (inputString == null || newSubstring == null) {
            throw new ProjectException("String is null");
        }
        DataValidator dataValidator = new DataValidator();
        if (!dataValidator.isFitInString(inputString, wordLength)) {
            throw new ProjectException("Length of word is incorrect");
        }
        String regexExpression = String.format(REGEX_WORD, wordLength);
        Pattern pattern = Pattern.compile(regexExpression);
        Matcher matcher = pattern.matcher(inputString);
        String newString = matcher.replaceAll(newSubstring);
        regexExpression = String.format(REGEX_LAST_WORD, wordLength);
        pattern = Pattern.compile(regexExpression);
        matcher = pattern.matcher(newString);
        return matcher.replaceAll(newSubstring);
    }
}