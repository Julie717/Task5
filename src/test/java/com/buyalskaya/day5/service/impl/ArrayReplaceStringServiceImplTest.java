package com.buyalskaya.day5.service.impl;

import com.buyalskaya.day5.dataprovider.DataProviderClassReplace;
import com.buyalskaya.day5.exception.ProjectException;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class ArrayReplaceStringServiceImplTest {
    ArrayReplaceStringServiceImpl arrayReplaceStringService = new ArrayReplaceStringServiceImpl();

    @BeforeClass
    public void setUp() {
        arrayReplaceStringService = new ArrayReplaceStringServiceImpl();
    }

    @Test(dataProvider = "dataForReplaceSymbol", dataProviderClass = DataProviderClassReplace.class)
    public void replaceSymbolTestParams(String inputString, char symbol,
                                        int position, String expected) {
        try {
            String actual = arrayReplaceStringService.replaceSymbol(inputString, symbol, position);
            assertEquals(actual, expected);
        } catch (ProjectException ex) {
            fail("Incorrect data");
        }
    }

    @Test
    public void replaceSymbolTestNull() {
        String inputString = null;
        assertThrows(ProjectException.class,
                () -> arrayReplaceStringService.replaceSymbol(inputString, 't', 2));
    }

    @Test
    public void replaceSymbolTestEmpty() {
        String inputString = "";
        assertThrows(ProjectException.class,
                () -> arrayReplaceStringService.replaceSymbol(inputString, 't', 2));
    }

    @Test
    public void replaceSymbolTestNegativePosition() {
        String inputString = "Hello";
        assertThrows(ProjectException.class,
                () -> arrayReplaceStringService.replaceSymbol(inputString, 't', -2));
    }

    @Test
    public void replaceSymbolTestPositionGreaterThenLength() {
        String inputString = "Hello";
        assertThrows(ProjectException.class,
                () -> arrayReplaceStringService.replaceSymbol(inputString, 't', 6));
    }

    @Test(dataProvider = "dataForReplaceLetterAfterSuitableLetter",
            dataProviderClass = DataProviderClassReplace.class)
    public void replaceLetterAfterSuitableLetterTestParams(String inputString, char letterAfterWhichReplacement,
                                                           char replaceableLetter, char newLetter, String expected) {
        try {
            String actual = arrayReplaceStringService.
                    replaceLetterAfterSuitableLetter(inputString, letterAfterWhichReplacement,
                            replaceableLetter, newLetter);
            assertEquals(actual, expected);
        } catch (ProjectException ex) {
            fail("Incorrect data");
        }
    }

    @Test
    public void replaceLetterAfterSuitableLetterTestNull() {
        String inputString = null;
        assertThrows(ProjectException.class,
                () -> arrayReplaceStringService.replaceLetterAfterSuitableLetter(inputString,
                        'p', 'a', 'o'));
    }

    @Test
    public void replaceLetterAfterSuitableLetterTestIncorrectLetter() {
        String inputString = null;
        assertThrows(ProjectException.class,
                () -> arrayReplaceStringService.replaceLetterAfterSuitableLetter(inputString,
                        'p', '*', 'o'));
    }

    @Test(dataProvider = "dataForReplaceWordSuitableLength", dataProviderClass = DataProviderClassReplace.class)
    public void replaceWordSuitableLength(String inputString, int wordLength,
                                          String newSubstring, String expected) {
        try {
            String actual = arrayReplaceStringService.
                    replaceWordSuitableLength(inputString, wordLength, newSubstring);
            assertEquals(actual, expected);
        } catch (ProjectException ex) {
            fail("Incorrect data");
        }
    }

    @Test
    public void replaceWordSuitableLengthTestNullSubstring() {
        String inputString = "Hello";
        String newSubstring=null;
        assertThrows(ProjectException.class,
                () -> arrayReplaceStringService.replaceWordSuitableLength(inputString,
                        10, newSubstring));
    }

    @Test
    public void replaceWordSuitableLengthTestNullInputString() {
        String inputString = null;
        assertThrows(ProjectException.class,
                () -> arrayReplaceStringService.replaceWordSuitableLength(inputString,
                        10, "new string"));
    }

    @Test
    public void replaceWordSuitableLengthTestEmptyString() {
        String inputString = "";
        assertThrows(ProjectException.class,
                () -> arrayReplaceStringService.replaceWordSuitableLength(inputString,
                        10, "new string"));
    }

    @Test
    public void replaceWordSuitableLengthTestEmptyNewSubstring() {
        String inputString = "Hello";
        String newSubstring = "";
        assertThrows(ProjectException.class,
                () -> arrayReplaceStringService.replaceWordSuitableLength(inputString,
                        10, newSubstring));
    }

    @Test
    public void replaceWordSuitableLengthTestNegativeWordLength() {
        String inputString = "Hello, world!";
        String newSubstring = "summer";
        assertThrows(ProjectException.class,
                () -> arrayReplaceStringService.replaceWordSuitableLength(inputString,
                        -5, newSubstring));
    }

    @Test
    public void replaceWordSuitableLengthTestZeroWordLength() {
        String inputString = "Hello, world!";
        String newSubstring = "summer";
        assertThrows(ProjectException.class,
                () -> arrayReplaceStringService.replaceWordSuitableLength(inputString,
                        0, newSubstring));
    }
    @Test
    public void replaceWordSuitableLengthTestWordLengthGreaterInputString() {
        String inputString = "Hello";
        String newSubstring = "summer";
        assertThrows(ProjectException.class,
                () -> arrayReplaceStringService.replaceWordSuitableLength(inputString,
                        7, newSubstring));
    }
}