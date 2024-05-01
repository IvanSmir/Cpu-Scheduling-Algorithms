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
    private JTable averageTable;
    private Algorithm algorithmProcess;


    public GanttChart(Algorithm algorithmProcess) {
        this.algorithmProcess = algorithmProcess;
        rellenarResults();
        rellenarTabla();
    }
    public void rellenarTabla() {
        Integer[] columnNames = new Integer[algorithmProcess.getCurrentTime()];
        for (int j = 0; j < columnNames.length; j++) {
            columnNames[j] = j;
        }

        List<Process> processQueue = algorithmProcess.getProcessQueue();
        String[][] data = new String[processQueue.size()][columnNames.length];
        for (int rowIndex = 0; rowIndex < processQueue.size(); rowIndex++) {
            Process process = processQueue.get(rowIndex);
            List<Integer> startTimes = process.getStartTimes();
            List<Integer> endTimes = process.getEndTimes();
            int lastEndTime = endTimes.getLast();
            for (int t = 0; t < startTimes.getFirst(); t++) {
                data[rowIndex][t] = "";
            }
            // Llena los   espacios antes del primer startTime y entre ejecuciones
            for (int burstIndex = 0; burstIndex < startTimes.size(); burstIndex++) {
                for (int i = process.getArrivalTime() ; i< startTimes.getFirst() ; i++) {
                    data[rowIndex][i] = "0";
                }
                for (int t = lastEndTime; t < startTimes.get(burstIndex); t++) {
                    data[rowIndex][t] = "0";
                }
                for (int t = startTimes.get(burstIndex); t < endTimes.get(burstIndex); t++) {
                    data[rowIndex][t] = "X";
                }
                lastEndTime = endTimes.get(burstIndex);

            }

            for (int t = endTimes.getLast(); t < columnNames.length; t++) {
                data[rowIndex][t] = "";
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

    private void createUIComponents() {
        // TODO: place custom component creation code here
    }
}

