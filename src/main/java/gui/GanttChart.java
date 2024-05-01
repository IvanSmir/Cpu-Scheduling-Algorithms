package main.java.gui;

import main.java.algorithms.*;
import main.java.models.Process;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class GanttChart {
    private JPanel panel;
    private JTable ganttTable;
    private JTable resultsTable;
    private JTable averageTable;
    private JLabel algoritmName;
    private Algorithm algorithmProcess;


    public GanttChart(Algorithm algorithmProcess, String algorithmName) {
        this.algoritmName.setFont(new Font("Serif", Font.BOLD, 18));
        this.algorithmProcess = algorithmProcess;
        this.algoritmName.setText(algorithmName);
        rellenarResults();
        rellenarTabla();
        rellenarAverage();
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
                    data[rowIndex][i] = "o";
                }
                for (int t = lastEndTime; t < startTimes.get(burstIndex); t++) {
                    data[rowIndex][t] = "o";
                }
                for (int t = startTimes.get(burstIndex); t < endTimes.get(burstIndex); t++) {
                    data[rowIndex][t] = "x";
                }
                lastEndTime = endTimes.get(burstIndex);

            }

            for (int t = endTimes.getLast(); t < columnNames.length; t++) {
                data[rowIndex][t] = "";
            }
        }

        DefaultTableModel model = new DefaultTableModel(data, columnNames) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;  // Evita que las celdas sean editables
            }
        };
        ganttTable.setModel(model);
        ganttTable.setDefaultRenderer(Object.class, new GanttCellRenderer());
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
        String[] columnNames = {"ID", "Wating", "Ejecution", "Response"};

        DefaultTableModel model = new DefaultTableModel(data, columnNames);
        resultsTable.setModel(model);
    }
    public void rellenarAverage() {
        List <Process> processList = algorithmProcess.getProcessQueue();
        String[][] data = new String[1][3];
        float watingSum = 0;
        float ejecutionSum = 0;
        float responseSum = 0;
        for (int i = 0; i < processList.size(); i++) {
            watingSum += Float.valueOf(processList.get(i).getWaitingTime());
            ejecutionSum += Float.valueOf(processList.get(i).getEjucutionTime());
            responseSum += Float.valueOf(processList.get(i).getResponseTime());
        }
        data[0][0] = String.valueOf(watingSum/processList.size());
        data[0][1] = String.valueOf(ejecutionSum/processList.size());
        data[0][2] = String.valueOf(responseSum/processList.size());

        String[] columnNames = { "Wating", "Ejecution", "Response"};

        DefaultTableModel model = new DefaultTableModel(data, columnNames);
        averageTable.setModel(model);
    }

    public JPanel getPanel() {
        return panel;
    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
    }
    class GanttCellRenderer extends DefaultTableCellRenderer {
        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            Component cellComponent = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
            if ("x".equals(value)) {
                cellComponent.setBackground(Color.YELLOW);
            } else if ("o".equals(value)) {
                cellComponent.setBackground(Color.GRAY);
            } else {
                cellComponent.setBackground(Color.WHITE);
            }
            setText("");
            return cellComponent;
        }
    }


}


