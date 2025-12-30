package CommandPatters;

import CommandPatternExample.CommandPatternHandler;
import CsvLoadingExampleSwing.CsvTableWorker;
import Polymorph.Command;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class CommandPatternExampleV2Worker extends JFrame {

    public CommandPatternExampleV2Worker(){
        CommandPatternHandler cmdWorker = new CommandPatternHandler();

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
        JMenuItem updateRowMenuItem = new JMenuItem("Update Row");
        JMenuItem saveMenuItem = new JMenuItem("Save");
        JMenuItem undoMenuItem = new JMenuItem("Undo");
        JMenuItem redoMenuItem = new JMenuItem("Redo");

        menu.add(saveMenuItem);
        menu.add(undoMenuItem);
        menu.add(redoMenuItem);
        menu.add(deleteRowMenuItem);

        // Top panel (header)
        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        topPanel.add(editButton);
        add(topPanel, BorderLayout.NORTH);

        String csvPath = "./src/csvLoadingExampleSwing/source/students.csv";
        DefaultTableModel model = CsvTableWorker.loadCSV(csvPath, this);
        table.setModel(model);

        setVisible(true);

        editButton.addActionListener(a -> {
            menu.show(editButton, 0, editButton.getHeight());
        });

        deleteRowMenuItem.addActionListener(a -> {
            cmdWorker.execute(new DeleteRowCommandV2(model, table.getSelectedRow()));
        });

        undoMenuItem.addActionListener(a -> {
            cmdWorker.undo();
        });

    }
}
