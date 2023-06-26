package ru.vsu.cs.ereshkin_a_v.task06.gui;

import ru.vsu.cs.ereshkin_a_v.task06.csv.CSVParser;
import ru.vsu.cs.ereshkin_a_v.task06.task.Task;
import ru.vsu.cs.util.JTableUtils;
import ru.vsu.cs.util.SwingUtils;

import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.File;
import java.util.Map;
import java.util.Objects;

public class FrameMain extends JFrame{
    private JButton openFile;
    private JComboBox<String> studentComboBox;
    private NotEditableJTable tableStudentGrades;
    private JPanel panelMain;

    private final JFileChooser fileChooserOpen;
    private String[][] matrix = null;
    Map<String, Map<String, String>> map = null;

    public FrameMain() {
        this.setTitle("FrameMain");
        this.setContentPane(panelMain);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.pack();


        JTableUtils.initJTableForArray(tableStudentGrades, 120, true, true, false, false);
        tableStudentGrades.setRowHeight(50);
        fileChooserOpen = new JFileChooser();
        fileChooserOpen.setCurrentDirectory(new File("."));
        FileFilter filter = new FileNameExtensionFilter("Text files", "txt");
        fileChooserOpen.addChoosableFileFilter(filter);


        openFile.addActionListener(e -> {
            try {
                if (fileChooserOpen.showOpenDialog(panelMain) == JFileChooser.APPROVE_OPTION) {
                    matrix = CSVParser.getFromFile(fileChooserOpen.getSelectedFile().getPath());
                    map = Task.solve(matrix);
                    studentComboBox.removeAllItems();
                    for (String name : map.keySet()) {
                        studentComboBox.addItem(name);
                    }
                }
            } catch (Exception ex){
                SwingUtils.showErrorMessageBox(ex);
            }
        });

        studentComboBox.addActionListener(e -> {
            String selected = Objects.requireNonNull(studentComboBox.getSelectedItem()).toString();
            Map<String, String> disciplineGradeMap = map.get(selected);
            JTableUtils.writeArrayToJTable(tableStudentGrades, GUIUtils.getMatrixFromMap(disciplineGradeMap));
        });
    }
}
