package CommandPatternExample;

import javax.swing.*;

public class CommandPatternEntry {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(CommandPatternWorker::new);
    }
}
