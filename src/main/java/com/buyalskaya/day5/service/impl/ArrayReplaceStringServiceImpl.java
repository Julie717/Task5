package com.buyalskaya.day5.service.impl;

import com.buyalskaya.day5.exception.ProjectException;
import com.buyalskaya.day5.service.ReplaceStringService;
import com.buyalskaya.day5.validator.DataValidator;

public class ArrayReplaceStringServiceImpl implements ReplaceStringService {
    public static final String SPACE = " \t\n\r";
    public static final String PUNCT = "!\"#$%&'()*+,./:;<=>?@[\\]^_`{|}~";
    public static final String DIGIT = "0123456789";
    public static final String SEPARATOR = SPACE + PUNCT + DIGIT;

    @Override
    public String replaceSymbol(String inputString, char symbol, int position) throws ProjectException {
        DataValidator dataValidator = new DataValidator();
        if (inputString == null || inputString.equals("") ||
                !dataValidator.isFitInString(inputString, position)) {
            throw new ProjectException("Input data is incorrect");
        }
        int startPosition = 0;
        int inputStringLength = inputString.length();
        int separatorLength = SEPARATOR.length();
        char[] arrayChar = inputString.toCharArray();
        for (int i = 0; i < inputStringLength; i++) {
            for (int j = 0; j < separatorLength; j++) {
                if (arrayChar[i] == SEPARATOR.charAt(j)) {
                    replaceSymbolInWord(arrayChar, startPosition, i - 1, symbol, position);
                    startPosition = i + 1;
                }
            }
        }
        replaceSymbolInWord(arrayChar, startPosition, inputStringLength - 1, symbol, position);
        return new String(arrayChar);
    }

    private void replaceSymbolInWord(char[] arrayChar, int startWordPosition, int endWordPosition,
                                     char symbol, int replaceablePosition) {
        if (endWordPosition - startWordPosition + 1 >= replaceablePosition) {
            arrayChar[startWordPosition + replaceablePosition - 1] = symbol;
        }
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
        int inputStringLength = inputString.length();
        char lowerLetterAfterWhichReplacement = Character.toLowerCase(letterAfterWhichReplacement);
        char upperLetterAfterWhichReplacement = Character.toUpperCase(letterAfterWhichReplacement);
        char lowerReplaceableLetter = Character.toLowerCase(replaceableLetter);
        char upperReplaceableLetter = Character.toUpperCase(replaceableLetter);
        char lowerNewLetter = Character.toLowerCase(newLetter);
        char upperNewLetter = Character.toUpperCase(newLetter);
        char[] arrayChar = inputString.toCharArray();
        for (int i = 0; i < inputStringLength; i++) {
            if (arrayChar[i] == lowerLetterAfterWhichReplacement ||
                    arrayChar[i] == upperLetterAfterWhichReplacement) {
                replaceLetter(arrayChar, lowerReplaceableLetter, lowerNewLetter, i + 1);
                replaceLetter(arrayChar, upperReplaceableLetter, upperNewLetter, i + 1);
            }
        }
        return new String(arrayChar);
    }

    private void replaceLetter(char[] arrayChar, char replaceableSymbol, char newSymbol, int position) {
        int arrayLength = arrayChar.length;
        if (position < arrayLength && (arrayChar[position] == replaceableSymbol)) {
            arrayChar[position] = newSymbol;
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
        int startPosition = 0;
        int lengthNewWord = newSubstring.length();
        int separatorLength = SEPARATOR.length();
        char[] arrayChar = inputString.toCharArray();
        char[] newWordArrayChar = newSubstring.toCharArray();
        int i = 0;
        while (i < arrayChar.length) {
            for (int j = 0; j < separatorLength; j++) {
                if ((arrayChar[i] == SEPARATOR.charAt(j))) {
                    if (wordLength == (i - startPosition)) {
                        arrayChar = replaceWord(arrayChar, startPosition, i, newWordArrayChar);
                        i = startPosition + lengthNewWord;
                    }
                    startPosition = i + 1;
                }
            }
            i++;
        }
        if (arrayChar.length - startPosition == wordLength) {
            arrayChar = replaceWord(arrayChar, startPosition, arrayChar.length, newWordArrayChar);
        }
        return new String(arrayChar);
    }

    private char[] replaceWord(char[] arrayChar, int startWordPosition, int endWordPosition,
                               char[] newWord) {
        int lengthNewWord = newWord.length;
        int lengthOldWord = endWordPosition - startWordPosition;
        char[] newCharArray;
        int newArrayLength = arrayChar.length + lengthNewWord - lengthOldWord;
        newCharArray = new char[newArrayLength];
        for (int i = 0; i < startWordPosition; i++) {
            newCharArray[i] = arrayChar[i];
        }
        for (int i = 0; i < lengthNewWord; i++) {
            newCharArray[startWordPosition + i] = newWord[i];
        }
        int k = startWordPosition + lengthNewWord;
        for (int i = endWordPosition; i < arrayChar.length; i++) {
            newCharArray[k] = arrayChar[i];
            k++;
        }
        return newCharArray;
    }
}