package CommandPatternExample.Commands;

import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

public class DeleteColCommand implements Command{

    DefaultTableModel model;
    int selectedCol;
    List<String> oldColData;

    public DeleteColCommand(DefaultTableModel model, int selectedCol) {
        this.model = model;
        this.selectedCol = selectedCol;
    }

    @Override
    public void execute() {
        DefaultTableModel tempModel = new DefaultTableModel();
        int columnCount = model.getColumnCount();
        Vector<String> colHeadArr = new Vector<>();

        for(int i = 0; i<columnCount; i++){
            if(i != selectedCol)
                colHeadArr.add(model.getColumnName(i));
        }
        tempModel.setColumnIdentifiers(new List[]{colHeadArr});

        for(int i = 0; i< model.getRowCount(); i++){
            for(int j = 0; j < model.getColumnCount(); j++){
                if(j != selectedCol){
                    Vector<String> colArr = new Vector<>();
                    colArr.add((String) model.getValueAt(i, j));
                    tempModel.insertRow(i,colArr);
                }
            }
        }

        // ToDo take out header values from tempObj and cast them to Vectors and than set model with new values
        // ToDo save old values for undo
        // ToDo you need to save column name and column data that you removed so you can implement undo accordingly
        // ToDo implement save, you have save implemented here
        model.setDataVector(new Vector<>(), new Vector<>());
    }

    @Override
    public void undo() {

    }
}
