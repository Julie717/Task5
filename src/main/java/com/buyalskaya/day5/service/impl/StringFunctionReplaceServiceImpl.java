package com.buyalskaya.day5.service.impl;

import com.buyalskaya.day5.exception.ProjectException;
import com.buyalskaya.day5.service.ReplaceStringService;
import com.buyalskaya.day5.validator.DataValidator;

public class StringFunctionReplaceServiceImpl implements ReplaceStringService {
    public static final String SPACE = " \t\n\r";
    public static final String PUNCT = "!\"#$%&'()*+,./:;<=>?@[\\]^_`{|}~";
    public static final String DIGIT = "0123456789";
    public static final String SEPARATOR = SPACE + PUNCT + DIGIT;

    @Override
    public String replaceSymbol(String inputString, char symbol, int position) throws ProjectException {
        if (inputString == null || inputString == "") {
            throw new ProjectException("Input string is empty");
        }
        DataValidator dataValidator = new DataValidator();
        if (!dataValidator.isFitInString(inputString, position)) {
            throw new ProjectException("Position is incorrect");
        }
        int startPosition = 0;
        int inputStringLength = inputString.length();
        int separatorLength = SEPARATOR.length();
        for (int i = 0; i < inputStringLength; i++) {
            for (int j = 0; j < separatorLength; j++) {
                if (inputString.charAt(i) == SEPARATOR.charAt(j)) {
                    inputString = replaceSymbolInWord(inputString, startPosition, i - 1, symbol, position);
                    startPosition = i + 1;
                }
            }
        }
        return replaceSymbolInWord(inputString, startPosition, inputStringLength - 1, symbol, position);
    }

    private String replaceSymbolInWord(String inputString, int startWordPosition, int endWordPosition,
                                       char symbol, int replaceablePosition) {
        if (endWordPosition - startWordPosition + 1 >= replaceablePosition) {
            inputString = inputString.substring(0, startWordPosition + replaceablePosition - 1) + symbol +
                    inputString.substring(startWordPosition + replaceablePosition);
        }
        return inputString;
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
        StringBuilder resultString = new StringBuilder(inputString);
        replaceLetter(inputString, resultString, lowerSymbolAfterWhichReplacement, lowerReplaceableSymbol, lowerNewSymbol);
        replaceLetter(inputString, resultString, upperSymbolAfterWhichReplacement, upperReplaceableSymbol, upperNewSymbol);
        replaceLetter(inputString, resultString, lowerSymbolAfterWhichReplacement, upperReplaceableSymbol, upperNewSymbol);
        replaceLetter(inputString, resultString, upperSymbolAfterWhichReplacement, lowerReplaceableSymbol, lowerNewSymbol);
        return resultString.toString();
    }

    private void replaceLetter(String inputString, StringBuilder resultString, char letter, char replaceableSymbol, char newSymbol) {
        int index = inputString.indexOf(letter);
        int inputStringLength = inputString.length();
        while ((index != -1) && (index + 1 < inputStringLength)) {
            if (inputString.charAt(index + 1) == replaceableSymbol) {
                resultString.setCharAt(index + 1, newSymbol);
            }
            index = inputString.indexOf(letter, index + 1);
        }
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
        int startPosition = 0;
        int lengthNewWord = newSubstring.length();
        int separatorLength = SEPARATOR.length();
        int i = 0;
        while (i < inputString.length()) {
            for (int j = 0; j < separatorLength; j++) {
                if (inputString.charAt(i) == SEPARATOR.charAt(j)) {
                    if (wordLength == (i - startPosition)) {
                        inputString = replaceWord(inputString, startPosition, i, newSubstring);
                        i = startPosition + lengthNewWord;
                    }
                    startPosition = i + 1;
                }
            }
            i++;
        }
        if (inputString.length() - startPosition == wordLength) {
            inputString = replaceWord(inputString, startPosition, inputString.length(), newSubstring);
        }
        return inputString;
    }

    private String replaceWord(String inputString, int startWordPosition, int endWordPosition,
                               String newWord) {
        inputString = inputString.substring(0, startWordPosition) + newWord +
                inputString.substring(endWordPosition);
        return inputString;
    }
}
