package main.java;

import main.java.models.Process;
import main.java.util.CSVReader;
import main.java.util.CSVToClass;

import java.io.IOException;
import java.util.List;

public class App {
    public static void main(String[] args) {
        System.out.println("Hello World!");
        try {
            List<String[]> processListString = CSVReader.readFile("src/main/resources/processes.csv");
            List<Process> processList= CSVToClass.convertToClass(processListString);
            for (Process process : processList) {
                System.out.println("Process [ID=" + process.getProcessId() + ", Arrival Time=" + process.getArrivalTime() + ", Burst Time=" + process.getBurstTime() + (process.getPriority() != 0 ? ", Priority=" + process.getPriority() + "]":  "]"));
            }
        } catch (IOException e) {
            System.out.println("An error occurred while reading the file" + e.getMessage());
        }

    }
}
