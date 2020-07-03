package com.buyalskaya.day5.reader;

import com.buyalskaya.day5.exception.ProjectException;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class DataReaderTest {
    DataReader dataReader;
    StringBuilder defaultDataExpected;

    @BeforeClass
    public void setUp() {
        dataReader = new DataReader();
        defaultDataExpected = new StringBuilder();
        defaultDataExpected.append("«Мой дядя самых честных правил,\n");
        defaultDataExpected.append("Когда не в шутку занемог,\n");
        defaultDataExpected.append("Он уважать себя заставил\n");
        defaultDataExpected.append("И лучше выдумать не мог.\n");
        defaultDataExpected.append("Его пример другим наука;\n");
        defaultDataExpected.append("Но, боже мой, какая скука\n");
        defaultDataExpected.append("С больным сидеть и день и ночь,\n");
        defaultDataExpected.append("Не отходя ни шагу прочь!\n");
        defaultDataExpected.append("Какое низкое коварство\n");
        defaultDataExpected.append("Полуживого забавлять,\n");
        defaultDataExpected.append("Ему подушки поправлять,\n");
        defaultDataExpected.append("Печально подносить лекарство,\n");
        defaultDataExpected.append("Вздыхать и думать про себя:\n");
        defaultDataExpected.append("Когда же черт возьмет тебя!»");
    }

    @Test
    public void readStringFromFileTestPositive() {
        String filePath = "resources/inputData.txt";
        try {
            String actual = dataReader.readStringFromFile(filePath);
            assertEquals(actual, defaultDataExpected.toString());
        } catch (ProjectException ex) {
            fail("Incorrect file path");
        }
    }

    @Test
    public void readStringFromFileTestNegative() {
        String filePath = "resources/input.txt";
        try {
            String actual = dataReader.readStringFromFile(filePath);
            assertEquals(actual, defaultDataExpected.toString());
        } catch (ProjectException ex) {
            fail("Incorrect file path");
        }
    }

    @Test
    public void readStringFromFileTestNull() {
        String filePath = null;
        try {
            String actual = dataReader.readStringFromFile(filePath);
            assertEquals(actual, defaultDataExpected.toString());
        } catch (ProjectException ex) {
            fail("Incorrect file path");
        }
    }

    @Test
    public void readStringFromFileEmpty() {
        String filePath = "";
        try {
            String actual = dataReader.readStringFromFile(filePath);
            assertEquals(actual, defaultDataExpected.toString());
        } catch (ProjectException ex) {
            fail("Incorrect file path");
        }
    }
}