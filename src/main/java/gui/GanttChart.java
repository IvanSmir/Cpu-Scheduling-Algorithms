package main.java.gui;

import main.java.algorithms.*;
import main.java.models.Process;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.List;

public class GanttChart {
    private JPanel panel;
    private JTable ganttTable;
    private JTable resultsTable;
    private Algorithm algorithmProcess;


    public GanttChart(Algorithm algorithmProcess) {
        this.algorithmProcess = algorithmProcess;
        rellenarResults();
        rellenarTabla();
    }
    public void rellenarTabla() {
        Integer[] columnNames = new Integer[algorithmProcess.getCurrentTime()];

        List<Process> processQueue = algorithmProcess.getProcessQueue();
        String[][] data = new String[columnNames.length][processQueue.size()];
        for (int j = 0; j < columnNames.length; j++) {
            columnNames[j] = j;
        }
        for (Process process : processQueue) {
            List<Integer> startTimes = process.getStartTimes();
            List<Integer> endTimes = process.getEndTimes();
            int count = 0;
            for (int i = 0; i < startTimes.size() - 1; i++) {
                while (count < process.getArrivalTime()) {
                    data[i][count] = "";
                    count++;
                }
                while (count < startTimes.get(i)) {
                    data[i][count] = "0";
                    count++;
                }
                for (int j = startTimes.get(i); j < endTimes.get(i); j++) {
                    data[i][j] = "X";
                }
            }
        }
        DefaultTableModel model = new DefaultTableModel(data, columnNames);
        ganttTable.setModel(model);
    }
    public void rellenarResults() {
        List <Process> processList = algorithmProcess.getProcessQueue();
        String[][] data = new String[processList.size()][4];
        for (int i = 0; i < processList.size(); i++) {
            data[i][0] = String.valueOf(processList.get(i).getProcessId());
            data[i][1] = String.valueOf(processList.get(i).getWaitingTime());
            data[i][2] = String.valueOf(processList.get(i).getEjucutionTime());
            data[i][3] = String.valueOf(processList.get(i).getResponseTime());
        }
        String[] columnNames = {"ID", "Wating time", "Ejecution Time", "Response time"};

        DefaultTableModel model = new DefaultTableModel(data, columnNames);
        resultsTable.setModel(model);
    }

    public JPanel getPanel() {
        return panel;
    }
}

