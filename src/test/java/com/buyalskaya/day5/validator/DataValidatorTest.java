package com.buyalskaya.day5.validator;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class DataValidatorTest {
    DataValidator dataValidator;

    @BeforeClass
    public void setUp() {
        dataValidator = new DataValidator();
    }

    @DataProvider(name = "dataForIsLetter")
    public Object[][] dataForIsLetter() {
        return new Object[][]{
                {'f', true},
                {'T', true},
                {'и', true},
                {'Ц', true},
                {'*', false},
                {'7', false},
                {' ', false},
                {'.', false}
        };
    }

    @Test(dataProvider = "dataForIsLetter")
    public void isLetterTestParams(char symbol, boolean expected) {
        boolean actual = dataValidator.isLetter(symbol);
        assertEquals(actual, expected);
    }

    @DataProvider(name = "dataForIsWord")
    public Object[][] dataForIsWord() {
        return new Object[][]{
                {"Hello", true},
                {"привет", true},
                {"кое-как", true},
                {"a", true},
                {"Привет  ", false},
                {"hello.", false},
                {"-", false},
                {"1", false}
        };
    }

    @Test(dataProvider = "dataForIsWord")
    public void isWordTestParams(String word, boolean expected) {
        boolean actual = dataValidator.isWord(word);
        assertEquals(actual, expected);
    }

    @DataProvider(name = "dataForIsFitInString")
    public Object[][] dataForIsFitInString() {
        return new Object[][]{
                {"Hello, world!", 5, true},
                {"Привет, мир!", 7, true},
                {"Hello", 6, false},
                {null, 4, false},
                {"мир", -1, false},
                {"hello", 0, false},
                {"", 0, false}
        };
    }

    @Test(dataProvider = "dataForIsFitInString")
    public void isFitInStringTestParams(String inputString, int length, boolean expected) {
        boolean actual = dataValidator.isFitInString(inputString, length);
        assertEquals(actual, expected);
    }
}