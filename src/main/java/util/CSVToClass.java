package main.java.util;

import main.java.models.Process;

import java.util.ArrayList;
import java.util.List;

public class CSVToClass {

   static public List<Process> convertToClass( List<String[]> proccesList) {
       List<Process> processList = new ArrayList<>();

        for (String[] process : proccesList) {
            Process newProcess = new Process(Integer.parseInt(process[0]), Integer.parseInt(process[1]), Integer.parseInt(process[2]));
            if(process.length == 4){
                newProcess.setPriority(Integer.parseInt(process[3]));
            }
            processList.add(newProcess);
        }
        return processList;
    }
}
