package CsvLoadingExampleSwing;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class CsvTableWorker extends JFrame {

    public CsvTableWorker() {
        setTitle("CSV Table Viewer");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 400);
        setLocationRelativeTo(null);

        JTable table = new JTable();
        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane, BorderLayout.CENTER);

        String csvPath = "./src/csvLoadingExampleSwing/source/students.csv";
        DefaultTableModel model = CsvTableWorker.loadCSV(csvPath, this);
        table.setModel(model);

        setVisible(true);
    }

    public static DefaultTableModel loadCSV(String filePath, JFrame parent) {
        DefaultTableModel model = new DefaultTableModel();

        try (BufferedReader reader = new BufferedReader(
            new FileReader(filePath)))
            {
                    String line;
                    boolean firstLine = true;

                    while ((line = reader.readLine()) != null) {
                        String[] parts = line.split(",");

                        if (firstLine) {
                            // first line is header
                            model.setColumnIdentifiers(parts);
                            firstLine = false;
                        } else {
                            model.addRow(parts);
                        }
             }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(parent, "Error reading file: " + e.getMessage());
        }

        return model;
    }
}
