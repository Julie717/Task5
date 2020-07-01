package com.buyalskaya.day5.service.impl;

import com.buyalskaya.day5.dataprovider.DataProviderClassDelete;
import com.buyalskaya.day5.exception.ProjectException;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class StringFunctionDeleteServiceImplTest {
    StringFunctionDeleteServiceImpl stringFunctionDeleteService = new StringFunctionDeleteServiceImpl();

    @BeforeClass
    public void setUp() {
        stringFunctionDeleteService = new StringFunctionDeleteServiceImpl();
    }

    @Test(dataProvider = "dataForDeleteNotLetterExceptSpace", dataProviderClass = DataProviderClassDelete.class)
    public void deleteNotLetterExceptSpaceTestParams(String inputString, String expected) {
        try {
            String actual = stringFunctionDeleteService.deleteNotLetterExceptSpace(inputString);
            assertEquals(actual, expected);
        } catch (ProjectException ex) {
            fail("Input string is null");
        }
    }

    @Test
    public void deleteNotLetterExceptSpaceTestNull() {
        String inputString = null;
        assertThrows(ProjectException.class,
                () -> stringFunctionDeleteService.deleteNotLetterExceptSpace(inputString));
    }

    @Test(dataProvider = "dataForDeleteWords", dataProviderClass = DataProviderClassDelete.class)
    public void deleteWordsTestParams(String inputString, int wordLength, char startLetter, String expected) {
        try {
            String actual = stringFunctionDeleteService.deleteWords(inputString, wordLength, startLetter);
            assertEquals(actual, expected);
        } catch (ProjectException ex) {
            fail("Input string is null");
        }
    }

    @Test
    public void deleteWordsNull() {
        String inputString = null;
        assertThrows(ProjectException.class,
                () -> stringFunctionDeleteService.deleteWords(inputString, 2, 'r'));
    }

    @Test
    public void deleteWordsNegativeWordLength() {
        String inputString = "November and December as they do in the other 10 months of the year.";
        assertThrows(ProjectException.class,
                () -> stringFunctionDeleteService.deleteWords(inputString, -2, 'r'));
    }

    @Test
    public void deleteWordsWordLengthGreaterThanInputString() {
        String inputString = "November and December";
        assertThrows(ProjectException.class,
                () -> stringFunctionDeleteService.deleteWords(inputString, 45, 'r'));
    }

    @Test
    public void deleteWordsWordLengthZero() {
        String inputString = "";
        assertThrows(ProjectException.class,
                () -> stringFunctionDeleteService.deleteWords(inputString, 0, 'r'));
    }

    @Test
    public void deleteWordsWordIncorrectStartLetter() {
        String inputString = "November and December";
        assertThrows(ProjectException.class,
                () -> stringFunctionDeleteService.deleteWords(inputString, 3, '*'));
    }

    @Test
    public void deleteWordsInputStringNull() {
        String inputString = null;
        assertThrows(ProjectException.class,
                () -> stringFunctionDeleteService.deleteWords(inputString, 2, 'r'));
    }
}