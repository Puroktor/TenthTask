package ru.vsu.cs.skofenko.jTableVersion;

import ru.vsu.cs.skofenko.common.Logic;
import ru.vsu.cs.skofenko.common.Triangle;
import ru.vsu.cs.util.JTableUtils;
import ru.vsu.cs.util.SwingUtils;

import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import java.io.File;
import java.util.List;

public class FrameMain extends JFrame {

    private JPanel panelMain;
    private JTable table1;
    private JButton loadInputButton;
    private JButton writeInputButton;
    private JButton solveButton;
    private JButton saveButton;
    private JTable tableOut;

    private JFileChooser fileChooserOpen;
    private JFileChooser fileChooserSave;

    public FrameMain() {
        this.setTitle("Task 10");
        this.setContentPane(panelMain);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.pack();

        initJtable(table1);
        initJtable(tableOut);

        FileFilter filter = new FileNameExtensionFilter("Text files", "txt");
        fileChooserOpen = new JFileChooser();
        fileChooserOpen.setCurrentDirectory(new File("."));
        fileChooserOpen.addChoosableFileFilter(filter);

        fileChooserSave = new JFileChooser();
        fileChooserSave.setCurrentDirectory(new File("."));
        fileChooserSave.addChoosableFileFilter(filter);

        fileChooserSave.setAcceptAllFileFilterUsed(false);
        fileChooserSave.setDialogType(JFileChooser.SAVE_DIALOG);
        fileChooserSave.setApproveButtonText("Save");

        loadInputButton.addActionListener(e -> {
            try {
                if (fileChooserOpen.showOpenDialog(panelMain) == JFileChooser.APPROVE_OPTION) {
                    List<Triangle> list = Logic.readListFromFile(fileChooserOpen.getSelectedFile().getPath());
                    double[][] arr = Logic.toDoubleArray(list);
                    if (arr == null) {
                        DefaultTableModel model = (DefaultTableModel) table1.getModel();
                        model.setRowCount(0);
                        JTableUtils.recalcJTableSize(table1);
                    } else {
                        JTableUtils.writeArrayToJTable(table1, Logic.toDoubleArray(list));
                    }
                }
            } catch (Exception exception) {
                SwingUtils.showErrorMessageBox(exception);
            }
        });
        solveButton.addActionListener(e -> {
            try {
                double[][] inArray = JTableUtils.readDoubleMatrixFromJTable(table1);
                List<Triangle> list = Logic.toTriangleList(inArray);
                var answ = Logic.findSimilarTriangles(list);
                JTableUtils.writeArrayToJTable(tableOut, Logic.answToArray(answ));
            } catch (Exception exception) {
                SwingUtils.showErrorMessageBox(exception);
            }
        });
        saveButton.addActionListener(e -> {
            try {
                if (fileChooserSave.showSaveDialog(panelMain) == JFileChooser.APPROVE_OPTION) {
                    String file = fileChooserSave.getSelectedFile().getPath();
                    if (!file.toLowerCase().endsWith(".txt")) {
                        file += ".txt";
                    }
                    double[][] outArray = JTableUtils.readDoubleMatrixFromJTable(tableOut);
                    Logic.writeMatrixToFile(file, outArray);
                }
            } catch (Exception exception) {
                SwingUtils.showErrorMessageBox(exception);
            }
        });
        writeInputButton.addActionListener(e -> {
            try {
                if (fileChooserSave.showSaveDialog(panelMain) == JFileChooser.APPROVE_OPTION) {
                    String file = fileChooserSave.getSelectedFile().getPath();
                    if (!file.toLowerCase().endsWith(".txt")) {
                        file += ".txt";
                    }
                    double[][] outArray = JTableUtils.readDoubleMatrixFromJTable(table1);
                    Logic.writeMatrixToFile(file, outArray);
                }
            } catch (Exception exception) {
                SwingUtils.showErrorMessageBox(exception);
            }
        });
    }

    private static void initJtable(JTable table) {
        JTableUtils.initJTableForArray(table, 60, false, false, true, false);
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        model.addColumn(new TableColumn());
        model.setRowCount(3);
        JTableUtils.recalcJTableSize(table);
    }
}
