package com.buyalskaya.day5.service.impl;

import com.buyalskaya.day5.exception.ProjectException;
import com.buyalskaya.day5.service.ReplaceStringService;
import com.buyalskaya.day5.validator.DataValidator;

public class StringFunctionReplaceServiceImpl implements ReplaceStringService {
    public static final String WORD_BORDER = "\\b(?!-)(?<!-)";

    @Override
    public String replaceSymbol(String inputString, char symbol, int position) throws ProjectException {
        DataValidator dataValidator = new DataValidator();
        if (inputString == null || inputString.equals("") ||
                !dataValidator.isFitInString(inputString, position)) {
            throw new ProjectException("Input data is incorrect");
        }
        StringBuilder resultString = new StringBuilder();
        String[] words = inputString.split(WORD_BORDER);
        for (String word : words) {
            resultString.append(replaceSymbolInWord(word, position, symbol));
        }
        return resultString.toString();
    }

    private String replaceSymbolInWord(String word, int position, char symbol) {
        DataValidator dataValidator = new DataValidator();
        char firstLetter = word.charAt(0);
        if (word.length() >= position && dataValidator.isLetter(firstLetter)) {
            word = word.substring(0, position - 1) + symbol + word.substring(position);
        }
        return word;
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
        StringBuilder resultString = new StringBuilder(inputString);
        replaceLetter(inputString, resultString, lowerSymbolAfterWhichReplacement,
                lowerReplaceableSymbol, lowerNewSymbol);
        replaceLetter(inputString, resultString, upperSymbolAfterWhichReplacement,
                upperReplaceableSymbol, upperNewSymbol);
        replaceLetter(inputString, resultString, lowerSymbolAfterWhichReplacement,
                upperReplaceableSymbol, upperNewSymbol);
        replaceLetter(inputString, resultString, upperSymbolAfterWhichReplacement,
                lowerReplaceableSymbol, lowerNewSymbol);
        return resultString.toString();
    }

    private void replaceLetter(String inputString, StringBuilder resultString,
                               char letter, char replaceableSymbol, char newSymbol) {
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
        DataValidator dataValidator = new DataValidator();
        if (inputString == null || newSubstring == null ||
                !dataValidator.isFitInString(inputString, wordLength)) {
            throw new ProjectException("Input data is incorrect");
        }
        StringBuilder resultString = new StringBuilder();
        String[] words = inputString.split(WORD_BORDER);
        for (String word : words) {
            char firstLetter = word.charAt(0);
            if (word.length() == wordLength && dataValidator.isLetter(firstLetter)) {
                resultString.append(newSubstring);
            } else {
                resultString.append(word);
            }
        }
        return resultString.toString();
    }
}