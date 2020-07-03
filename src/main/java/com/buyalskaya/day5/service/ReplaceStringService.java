package com.buyalskaya.day5.service;

import com.buyalskaya.day5.exception.ProjectException;

public interface ReplaceStringService {
    String replaceSymbol(String inputString, char symbol, int position) throws ProjectException;

    String replaceLetterAfterSuitableLetter(String inputString, char letterAfterWhichReplacement,
                                            char replaceableLetter, char newLetter) throws ProjectException;

    String replaceWordSuitableLength(String inputString, int wordLength,
                                     String newSubstring) throws ProjectException;
}
