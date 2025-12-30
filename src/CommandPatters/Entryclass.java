package CommandPatters;

import CsvLoadingExampleSwing.CsvTableWorker;

import javax.swing.*;

public class Entryclass {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(CommandPatternExampleV2Worker::new);
    }
}
