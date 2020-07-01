package com.buyalskaya.day5.service;

import com.buyalskaya.day5.exception.ProjectException;

public interface DeleteStringService {
    String deleteNotLetterExceptSpace(String inputString) throws ProjectException;

    String deleteWords(String inputString, int wordLength, char startLetter) throws ProjectException;
}
