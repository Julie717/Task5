package com.buyalskaya.day5.service.impl;

import com.buyalskaya.day5.dataprovider.DataProviderClassDelete;
import com.buyalskaya.day5.exception.ProjectException;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class RegexDeleteStringServiceImplTest {
    RegexDeleteStringServiceImpl regexDeleteStringService = new RegexDeleteStringServiceImpl();

    @BeforeClass
    public void setUp() {
        regexDeleteStringService = new RegexDeleteStringServiceImpl();
    }

    @Test(dataProvider = "dataForDeleteNotLetterExceptSpace",
            dataProviderClass = DataProviderClassDelete.class)
    public void deleteNotLetterExceptSpaceTestParams(String inputString, String expected) {
        try {
            String actual = regexDeleteStringService.deleteNotLetterExceptSpace(inputString);
            assertEquals(actual, expected);
        } catch (ProjectException ex) {
            fail("Input string is null");
        }
    }

    @Test
    public void deleteNotLetterExceptSpaceTestNull() {
        String inputString = null;
        assertThrows(ProjectException.class,
                () -> regexDeleteStringService.deleteNotLetterExceptSpace(inputString));
    }

    @Test(dataProvider = "dataForDeleteWordsStartedConsonant",
            dataProviderClass = DataProviderClassDelete.class)
    public void deleteWordsStartedConsonantTestParams
            (String inputString, int wordLength, String expected) {
        try {
            String actual = regexDeleteStringService.deleteWordsStartedConsonant(inputString, wordLength);
            assertEquals(actual, expected);
        } catch (ProjectException ex) {
            fail("Input string is null");
        }
    }

    @Test
    public void deleteWordsStartedConsonantNull() {
        String inputString = null;
        assertThrows(ProjectException.class,
                () -> regexDeleteStringService.deleteWordsStartedConsonant(inputString, 2));
    }

    @Test
    public void deleteWordsStartedConsonantNegativeWordLength() {
        String inputString = "November and December as they do in the other 10 months of the year.";
        assertThrows(ProjectException.class,
                () -> regexDeleteStringService.deleteWordsStartedConsonant(inputString, -2));
    }

    @Test
    public void deleteWordsStartedConsonantWordLengthGreaterThanInputString() {
        String inputString = "November and December";
        assertThrows(ProjectException.class,
                () -> regexDeleteStringService.deleteWordsStartedConsonant(inputString, 45));
    }

    @Test
    public void deleteWordsStartedConsonantWordLengthZero() {
        String inputString = "";
        assertThrows(ProjectException.class,
                () -> regexDeleteStringService.deleteWordsStartedConsonant(inputString, 0));
    }

    @Test
    public void deleteWordsStartedConsonantInputStringNull() {
        String inputString = null;
        assertThrows(ProjectException.class,
                () -> regexDeleteStringService.deleteWordsStartedConsonant(inputString, 2));
    }
}