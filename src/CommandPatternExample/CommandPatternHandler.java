package CommandPatternExample;

import CommandPatternExample.Commands.Command;

import javax.swing.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Stack;

public class CommandPatternHandler {

    private final Stack<Command> redoStack = new Stack<>();
    private final Stack<Command> undoStack = new Stack<>();

    public void execute(Command command){
        command.execute();

        undoStack.push(command);
        redoStack.clear();
    }

    public void undo() {
        var undoCommand = undoStack.pop();

        undoCommand.undo();
        redoStack.push(undoCommand);
    }

    public void redo(){
        if (redoStack.empty())
            return;
        var redoCommand = redoStack.pop();
        redoCommand.execute();
        undoStack.push(redoCommand);
    }
}
