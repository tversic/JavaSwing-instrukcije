package DeleteRowCommandExample;
import CsvLoadingExampleSwing.CsvTableWorker;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class DeleteRowTableWorker extends JFrame {

    public DeleteRowTableWorker() {
        setTitle("Delete row");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 400);

        // Table
        JTable table = new JTable();
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        table.setSelectionBackground(Color.LIGHT_GRAY);
        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane, BorderLayout.CENTER);

        // EDIT button + dropdown
        JButton editButton = new JButton("Edit");
        JPopupMenu menu = new JPopupMenu();

        JMenuItem deleteRowMenuItem = new JMenuItem("Delete Row");
        menu.add(deleteRowMenuItem);

        JMenuItem saveMenuItem = new JMenuItem("Save");
        menu.add(saveMenuItem);

        // Top panel (header)
        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        topPanel.add(editButton);
        add(topPanel, BorderLayout.NORTH);

        String csvPath = "./src/csvLoadingExampleSwing/source/students.csv";
        DefaultTableModel model = CsvTableWorker.loadCSV(csvPath, this);
        table.setModel(model);

        setVisible(true);

        editButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                menu.show(editButton, 0, editButton.getHeight());
            }
        });

        deleteRowMenuItem.addActionListener(a -> {
            int selectedRow = table.getSelectedRow();
            model.removeRow(selectedRow);
        });

        saveMenuItem.addActionListener(a -> {
            SaveModel(model, csvPath);
        });
    }


    // ToDo
    private void SaveModel(DefaultTableModel model, String filepath) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(filepath))) {
            for (int i = 0; i < model.getColumnCount(); i++) {
                writer.print(model.getColumnName(i));
                if (i != model.getColumnCount() -1)
                    writer.print(",");
            }
            writer.print("\n");

            for (int i = 0; i < model.getRowCount(); i ++){
                for (int j = 0; j < model.getColumnCount(); j++){
                    writer.print(model.getValueAt(i, j));
                    if (i != model.getColumnCount() -1)
                        writer.print(",");
                }
                writer.print("\n");
            }

        }
        catch (IOException e) {
            JOptionPane.showMessageDialog(
                    null,
                    "Failed to save file:\n" + e.getMessage(),
                    "Save error",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }
}
