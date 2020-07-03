package com.buyalskaya.day5.service.impl;

import com.buyalskaya.day5.dataprovider.DataProviderClassDelete;
import com.buyalskaya.day5.exception.ProjectException;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class ArrayDeleteStringServiceImplTest {
    ArrayDeleteStringServiceImpl arrayDeleteStringService;

    @BeforeClass
    public void setUp() {
        arrayDeleteStringService = new ArrayDeleteStringServiceImpl();
    }

    @Test(dataProvider = "dataForDeleteNotLetterExceptSpace",dataProviderClass = DataProviderClassDelete.class)
    public void deleteNotLetterExceptSpaceTestParams(String inputString, String expected) {
        try {
            String actual = arrayDeleteStringService.deleteNotLetterExceptSpace(inputString);
            assertEquals(actual, expected);
        } catch (ProjectException ex) {
            fail("Input string is null");
        }
    }

    @Test
    public void deleteNotLetterExceptSpaceTestNull() {
        String inputString = null;
        assertThrows(ProjectException.class,
                () -> arrayDeleteStringService.deleteNotLetterExceptSpace(inputString));
    }

    @Test(dataProvider = "dataForDeleteWordsStartedConsonant",
            dataProviderClass = DataProviderClassDelete.class)
    public void deleteWordsStartedConsonantTestParams
            (String inputString, int wordLength, String expected) {
        try {
            String actual = arrayDeleteStringService.deleteWordsStartedConsonant(inputString, wordLength);
            assertEquals(actual, expected);
        } catch (ProjectException ex) {
            fail("Input string is null");
        }
    }

    @Test
    public void deleteWordsStartedConsonantNull() {
        String inputString = null;
        assertThrows(ProjectException.class,
                () -> arrayDeleteStringService.deleteWordsStartedConsonant(inputString, 2));
    }

    @Test
    public void deleteWordsStartedConsonantNegativeWordLength() {
        String inputString = "November and December as they do in the other 10 months of the year.";
        assertThrows(ProjectException.class,
                () -> arrayDeleteStringService.deleteWordsStartedConsonant(inputString, -2));
    }

    @Test
    public void deleteWordsStartedConsonantWordLengthGreaterThanInputString() {
        String inputString = "November and December";
        assertThrows(ProjectException.class,
                () -> arrayDeleteStringService.deleteWordsStartedConsonant(inputString, 45));
    }

    @Test
    public void deleteWordsStartedConsonantWordLengthZero() {
        String inputString = "";
        assertThrows(ProjectException.class,
                () -> arrayDeleteStringService.deleteWordsStartedConsonant(inputString, 0));
    }

    @Test
    public void deleteWordsStartedConsonantInputStringNull() {
        String inputString = null;
        assertThrows(ProjectException.class,
                () -> arrayDeleteStringService.deleteWordsStartedConsonant(inputString, 2));
    }
}