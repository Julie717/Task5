package com.buyalskaya.day5.reader;

import com.buyalskaya.day5.exception.ProjectException;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;
import java.util.stream.Collectors;

public class DataReader {
    private static final String DEFAULT_PATH = "resources/inputData.txt";

    public String readStringFromFile(String filePath) throws ProjectException {
        String data;
        if (filePath == null || filePath.equals("") || !Files.exists(Paths.get(filePath))) {
            filePath = DEFAULT_PATH;
        }
        try (FileReader fileReader = new FileReader(filePath);
             BufferedReader bufferedReader = new BufferedReader(fileReader)) {
            data = bufferedReader.lines().collect(Collectors.joining("\n"));
        } catch (IOException e) {
            throw new ProjectException("Error in opening file " + filePath, e);
        }
        return data;
    }

    public String readStringFromConsole() {
        System.out.println("Enter string:");
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }
}