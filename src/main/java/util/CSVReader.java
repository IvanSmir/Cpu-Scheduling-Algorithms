package main.java.util;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CSVReader {

    public static List<String[]> readFile(String fileName) throws IOException, IllegalArgumentException {
        List<String[]> processList = new ArrayList<>();
        String line;
        String splitBy = ",";
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fileName))) {
            if (!validateHeader(bufferedReader.readLine().split(splitBy))) {
                throw new IllegalArgumentException("Invalid CSV file format");
            }
            while ((line = bufferedReader.readLine()) != null) {
                String[] process = line.split(splitBy);
                processList.add(process);
            }
        }
        return processList;
    }

    private static boolean validateHeader(String[] header) {
        if (header.length < 3 || header.length > 4) {
            return false;
        }
        if (!header[0].equals("ID") || !header[1].equals("Arrival Time") || !header[2].equals("Burst Time")) {
            return false;
        }
        return header.length != 4 || header[3].equals("Priority");
    }
}
