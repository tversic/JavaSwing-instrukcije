package CommandPatternExample.Commands;

import javax.swing.table.DefaultTableModel;

public class DeleteRowCommand implements Command {

    private DefaultTableModel model;
    private int selectedRow = 0;
    private Object[] rowData;

    private DefaultTableModel undoModel;

    public DeleteRowCommand(DefaultTableModel model, int selectedRow){
        this.model = model;
        this.selectedRow = selectedRow;
    }

    @Override
    public void execute() {
        undoModel = this.model;

        // save history
        rowData = new Object[model.getColumnCount()];
        for (int c = 0; c < model.getColumnCount(); c++) {
            rowData[c] = model.getValueAt(selectedRow, c);
        }

        model.removeRow(selectedRow);
    }

    @Override
    public void undo() {
        model.insertRow(selectedRow, rowData);
    }
}
