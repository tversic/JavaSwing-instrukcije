package CommandPatters;

import CommandPatternExample.Commands.Command;

import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

public class DeleteRowCommandV2 implements Command {

    private DefaultTableModel model;
    private int selectedRow;
    List<String> oldRowData = new ArrayList<>();


    public DeleteRowCommandV2(DefaultTableModel model,
                              int selectedRow){
         this.model = model;
         this.selectedRow = selectedRow;
    }

    @Override
    public void execute() {

        for (int i = 0; i < model.getColumnCount(); i++){
            String value = model
                    .getValueAt(selectedRow, i)
                    .toString();

            oldRowData.add(value);
        }

        model.removeRow(selectedRow);
    }

    @Override
    public void undo() {
        model.insertRow(selectedRow, (Vector<?>) oldRowData);
    }
}
