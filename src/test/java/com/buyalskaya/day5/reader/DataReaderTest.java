package com.buyalskaya.day5.reader;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class DataReaderTest {
    DataReader dataReader;

    @BeforeClass
    public void setUp() {
        dataReader = new DataReader();
    }

    @Test
    public void readArrayFromFileTestPositive() {
        String filePath = "resources/inputData.txt";
        String actual = dataReader.readStringFromFile(filePath);
        StringBuilder expected = new StringBuilder();
        expected.append("«Мой дядя самых честных правил,\n");
        expected.append("Когда не в шутку занемог,\n");
        expected.append("Он уважать себя заставил\n");
        expected.append("И лучше выдумать не мог.\n");
        expected.append("Его пример другим наука;\n");
        expected.append("Но, боже мой, какая скука\n");
        expected.append("С больным сидеть и день и ночь,\n");
        expected.append("Не отходя ни шагу прочь!\n");
        expected.append("Какое низкое коварство\n");
        expected.append("Полуживого забавлять,\n");
        expected.append("Ему подушки поправлять,\n");
        expected.append("Печально подносить лекарство,\n");
        expected.append("Вздыхать и думать про себя:\n");
        expected.append("Когда же черт возьмет тебя!»");
        assertEquals(actual, expected.toString());
    }

    @Test
    public void readArrayFromFileTestNegative() {
        String filePath = "resources/input.txt";
        assertThrows(RuntimeException.class, () -> dataReader.readStringFromFile(filePath));
    }

    @Test
    public void readArrayFromFileTestNull() {
        String filePath = null;
        assertThrows(RuntimeException.class, () -> dataReader.readStringFromFile(filePath));
    }

    @Test
    public void readArrayFromFileTestEmpty() {
        String filePath = "";
        assertThrows(RuntimeException.class, () -> dataReader.readStringFromFile(filePath));
    }
}