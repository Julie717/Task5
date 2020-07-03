package com.buyalskaya.day5.service;

import com.buyalskaya.day5.exception.ProjectException;

public interface DeleteStringService {
    String deleteNotLetterExceptSpace(String inputString) throws ProjectException;

    String deleteWordsStartedConsonant(String inputString, int wordLength) throws ProjectException;
}
