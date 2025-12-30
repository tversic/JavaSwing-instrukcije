package CommandPatternExample;

import CommandPatternExample.Commands.DeleteColCommand;
import CommandPatternExample.Commands.DeleteRowCommand;
import CommandPatternExample.Commands.UpdateRowCommand;
import CsvLoadingExampleSwing.CsvTableWorker;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class CommandPatternWorker extends JFrame {

    private CommandPatternHandler commandPatternHandler = new CommandPatternHandler();

    CommandPatternWorker() {
        setTitle("Delete row");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 400);

        // Table
        JTable table = new JTable();
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION); // i want to enable table so i can select col also
        table.setSelectionBackground(Color.LIGHT_GRAY);
        table.setCellSelectionEnabled(true);
        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane, BorderLayout.CENTER);

        // EDIT button + dropdown
        JButton editButton = new JButton("Edit");

        JPopupMenu menu = new JPopupMenu();

        JMenuItem deleteRowMenuItem = new JMenuItem("Delete Row");
        JMenuItem updateRowMenuItem = new JMenuItem("Update Row");
        JMenuItem saveMenuItem = new JMenuItem("Save");
        JMenuItem undoMenuItem = new JMenuItem("Undo");
        JMenuItem redoMenuItem = new JMenuItem("Redo");
        JMenuItem delColItem = new JMenuItem("DeleteColumn");


        menu.add(saveMenuItem);
        menu.add(undoMenuItem);
        menu.add(redoMenuItem);
        menu.add(deleteRowMenuItem);
        menu.add(delColItem);

        // Top panel (header)
        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        topPanel.add(editButton);
        add(topPanel, BorderLayout.NORTH);

        String csvPath = "./src/csvLoadingExampleSwing/source/students.csv";
        DefaultTableModel model = CsvTableWorker.loadCSV(csvPath, this);
        table.setModel(model);

        editButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                menu.show(editButton, 0, editButton.getHeight());
            }
        });

        updateRowMenuItem.addActionListener(a -> {
            commandPatternHandler.execute(new UpdateRowCommand());
        });

        deleteRowMenuItem.addActionListener(a -> {
            commandPatternHandler.execute(new DeleteRowCommand(model, table.getSelectedRow()));
        });

        undoMenuItem.addActionListener(a -> {
            commandPatternHandler.undo();
        });

        redoMenuItem.addActionListener(a -> {
            commandPatternHandler.redo();
        });

        delColItem.addActionListener(a ->{
            commandPatternHandler.execute(new DeleteColCommand(model, table.getSelectedColumn()));
            table.setModel(model);
        });

        setVisible(true);
    }
}
