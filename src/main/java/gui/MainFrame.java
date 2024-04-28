package main.java.gui;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import main.java.algorithms.*;
import main.java.models.Process;
import main.java.util.CSVReader;
import main.java.util.CSVToClass;

public class MainFrame {
    private String path;
    private List<Process> processList;
    private List<String[]> processListString;
    private JPanel mainPanel;
    private JPanel bgPanel;
    private JButton abrirCSV;
    private JTable processTable;
    private JCheckBox SJFNPCheckBox;
    private JCheckBox prioridadCheckBox;
    private JCheckBox RRCheckBox;
    private JCheckBox HHRNCheckBox;
    private JCheckBox SJFPCheckBox;
    private JCheckBox FCFSCheckBox;
    private JButton ejecutarButton;
    private JTextField quantumField;
    private JPanel GrantPanel;
    private int quantum = 0;
    private static Algorithm algorithmProcess;

    public MainFrame() {
        List<String> selectedAlgorithms = new ArrayList<>();
        abrirCSV.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                fileChooser.setCurrentDirectory(new java.io.File("/home/"));
                fileChooser.setDialogTitle("Select a CSV file");
                fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
                fileChooser.setAcceptAllFileFilterUsed(false);
                if (fileChooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
                    path = fileChooser.getSelectedFile().getAbsolutePath();
                    try {
                        processListString = CSVReader.readFile(path);
                    } catch (Exception ex) {
                        System.out.println("An error occurred while reading the file" + ex.getMessage());
                        JDialog dialog = new JDialog();
                        dialog.setAlwaysOnTop(true);
                        dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
                        dialog.add(new JLabel("Formato incorrecto de CSV"));
                        dialog.pack();
                        dialog.setLocationRelativeTo(null);
                        dialog.setVisible(true);
                    }
                }
                String[] columnNames = {"ID", "Arrival Time", "Burst Time", "Priority"};
                processList = CSVToClass.convertToClass(processListString);
                String[][] data = new String[processList.size()][4];
                for (int i = 0; i < processList.size(); i++) {
                    data[i][0] = String.valueOf(processList.get(i).getProcessId());
                    data[i][1] = String.valueOf(processList.get(i).getArrivalTime());
                    data[i][2] = String.valueOf(processList.get(i).getBurstTime());
                    data[i][3] = String.valueOf(processList.get(i).getPriority());
                }
                DefaultTableModel model = new DefaultTableModel(data, columnNames);
                processTable.setModel(model);
            }
        });
        ejecutarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                processList = CSVToClass.convertToClass(processListString);
                selectedAlgorithms.clear();
                if (SJFNPCheckBox.isSelected()) {
                    selectedAlgorithms.add("SJFNP");
                }
                if (prioridadCheckBox.isSelected()) {
                    selectedAlgorithms.add("priority");
                }
                if (RRCheckBox.isSelected()) {
                    try {
                       quantum = Integer.parseInt(quantumField.getText());
                        selectedAlgorithms.add("RR");
                    } catch (NumberFormatException ex) {
                        JDialog dialog = new JDialog();
                        dialog.setAlwaysOnTop(true);
                        dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
                        dialog.add(new JLabel("Quantum debe ser un número entero"));
                        dialog.pack();
                        dialog.setLocationRelativeTo(null);
                        dialog.setVisible(true);
                    }
                }
                if (HHRNCheckBox.isSelected()) {
                    selectedAlgorithms.add("HHRN");
                }
                if (SJFPCheckBox.isSelected()) {
                    selectedAlgorithms.add("SJFP");
                }
                if (FCFSCheckBox.isSelected()) {
                    selectedAlgorithms.add("FCFS");
                }
                for (String algorithm : selectedAlgorithms) {
                    processList = CSVToClass.convertToClass(processListString);
                    switch (algorithm) {
                        case "SJFNP":
                            SJF_NP sjf_np = new SJF_NP(processList);
                            sjf_np.execute();
                            sjf_np.printDetails();
                            algorithmProcess = sjf_np;

                            break;
                        case "priority":
                            Priority priority = new Priority(processList);
                            priority.execute();
                            priority.printDetails();
                            algorithmProcess = priority;
                            break;
                        case "RR":
                            RR rr = new RR(processList, quantum);
                            rr.execute();
                            rr.printDetails();
                            algorithmProcess = rr;
                            break;
                        case "HHRN":
                            HRRN hrrn = new HRRN(processList);
                            hrrn.execute();
                            hrrn.printDetails();
                            algorithmProcess = hrrn;
                            break;
                        case "SJFP":
                            SJF_P sjf_p = new SJF_P(processList);
                            sjf_p.execute();
                            sjf_p.printDetails();
                            algorithmProcess = sjf_p;
                            break;
                        case "FCFS":
                            FCFS fcfs = new FCFS(processList);
                            fcfs.execute();
                            fcfs.printDetails();
                            algorithmProcess = fcfs;
                            break;
                    }
                    System.out.println(algorithm);
                }
            }
        });

    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("MainFrame");
        frame.setContentPane(new MainFrame().mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);





    }


}
