package main.java.gui;

import main.java.algorithms.Algorithm;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.Arrays;

public class GantChart {
    private JTable tablaGrantt;
    private JPanel grantPanel;
    private Algorithm algorithmProcess;

    public GantChart(Algorithm algorithmProcess) {
        this.algorithmProcess = algorithmProcess;
        initializeUI();
    }

    private void initializeUI() {
        String[][] data = new String[algorithmProcess.getProcessQueue().size()][4];
        int i = 0;
        for (main.java.models.Process process : algorithmProcess.getProcessQueue()) {
            data[i][0] = String.valueOf(process.getProcessId());
            data[i][1] = String.valueOf(process.getStartTimes().getFirst());
            data[i][2] = String.valueOf(process.getEndTimes().getFirst());
            data[i][3] = String.valueOf(process.getBurstTime());
            i++;
        }
        System.out.println(Arrays.deepToString(data));
        String[] columns = {"Process ID", "Start Time", "End Time", "Duration"};

        System.out.println(tablaGrantt);

        DefaultTableModel model = new DefaultTableModel(data, columns);

        tablaGrantt.setModel(model);

    }


    public JPanel getPanel() {
        return grantPanel;
    }

}
