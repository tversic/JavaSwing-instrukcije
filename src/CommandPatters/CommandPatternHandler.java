package CommandPatters;

import CommandPatternExample.Commands.Command;

import javax.swing.table.DefaultTableModel;
import java.util.Stack;

public class CommandPatternHandler {

    private Stack<Command> undoStack = new Stack<>();
    private Stack<Command> redoStack = new Stack<>();

    public void execute(Command command){
        command.execute();
        undoStack.push(command);
    }
}
