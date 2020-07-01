package com.buyalskaya.day5.reader;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;
import java.util.stream.Collectors;

public class DataReader {
    public String readStringFromFile(String filePath) {
        String data;
        try (FileReader fileReader = new FileReader(filePath);
             BufferedReader bufferedReader = new BufferedReader(fileReader)) {
            data = bufferedReader.lines().collect(Collectors.joining("\n"));
        } catch (IOException e) {
            throw new RuntimeException("Error in opening file " + filePath, e);
        }
        return data;
    }

    public String readStringFromConsole() {
        System.out.println("Enter string:");
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }
}
