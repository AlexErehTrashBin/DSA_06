package ru.vsu.cs.ereshkin_a_v.task06.gui;

import ru.vsu.cs.ereshkin_a_v.task06.csv.CSVUtils;
import ru.vsu.cs.ereshkin_a_v.task06.task.Task;
import ru.vsu.cs.ereshkin_a_v.task06.util.MatrixUtils;
import ru.vsu.cs.util.JTableUtils;
import ru.vsu.cs.util.SwingUtils;

import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FrameMain extends JFrame{
    private JButton openFile;
    private JTable tableStudentGrades;
    private JPanel panelMain;
    private JTable studentsTable;
    private JButton saveButton;
    private JButton saveToCSVButton;
    private JTextField studentNameTextField;
    private JButton plusStudentButton;
    private JButton minusStudentButton;

    private final JFileChooser fileChooserOpen;
    private final JFileChooser fileChooserSave;
    private String[][] matrix = null;
    Map<String, Map<String, String>> map = null;
    private String selectedStudent = null;

    public FrameMain() {
        //new FrameMain();
        this.setTitle("FrameMain");
        this.setContentPane(panelMain);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.pack();
        this.setExtendedState(MAXIMIZED_BOTH);

        JTableUtils.initJTableForArray(
                tableStudentGrades, 120, true,
                false, true, false,
                () -> {}, () -> {}
        );
        tableStudentGrades.setRowHeight(40);
        JTableUtils.initJTableForArray(
                studentsTable, 300, true,
                false, false, false,
                () -> {}, () -> {}
        );
        studentsTable.setRowHeight(40);
        tableStudentGrades.putClientProperty("terminateEditOnFocusLost", true);
        fileChooserOpen = new JFileChooser();
        fileChooserSave = new JFileChooser();
        fileChooserOpen.setCurrentDirectory(new File("."));
        fileChooserSave.setCurrentDirectory(new File("."));
        FileFilter csvFilter = new FileNameExtensionFilter("CSV files", "csv");
        FileFilter txtFilter = new FileNameExtensionFilter("Text files", "txt");
        fileChooserOpen.addChoosableFileFilter(csvFilter);
        fileChooserSave.addChoosableFileFilter(txtFilter);


        openFile.addActionListener(e -> {
            try {
                if (fileChooserOpen.showOpenDialog(panelMain) == JFileChooser.APPROVE_OPTION) {
                    matrix = CSVUtils.getFromFile(fileChooserOpen.getSelectedFile().getPath());
                    map = Task.solve(matrix);
                    JTableUtils.writeArrayToJTable(
                            studentsTable,
                            MatrixUtils.getStringArrInCol(map.keySet().toArray(new String[0]))
                    );
                }
            } catch (Exception ex){
                SwingUtils.showErrorMessageBox(ex);
            }
        });
        saveButton.addActionListener(e -> {
            try {
                if (fileChooserSave.showSaveDialog(panelMain) == JFileChooser.APPROVE_OPTION) {
                    CSVUtils.writeGradesMapToFileBeautifully(map, fileChooserSave.getSelectedFile().getPath());
                }
            } catch (Exception ex){
                SwingUtils.showErrorMessageBox(ex);
            }
        });
        studentsTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                super.mouseReleased(e);
                if (map == null) return;
                int index = studentsTable.getSelectedRow();
                selectedStudent = (String) studentsTable.getModel().getValueAt(index, 0);
                if (index == -1) return;
                Map.Entry<String, Map<String, String>> sth = map.entrySet().stream().toList().get(index);
                String[][] matrixToWrite = getMatrixFromEntry(sth);
                JTableUtils.writeArrayToJTable(tableStudentGrades, matrixToWrite);
            }
        });
        tableStudentGrades.getModel().addTableModelListener(e -> recalculateMap());
        saveToCSVButton.addActionListener(e -> {
            try {
                if (fileChooserSave.showSaveDialog(panelMain) == JFileChooser.APPROVE_OPTION) {
                    CSVUtils.writeGradesMapToFileBullshit(map, fileChooserSave.getSelectedFile().getPath());
                }
            } catch (Exception ex){
                SwingUtils.showErrorMessageBox(ex);
            }
        });
        plusStudentButton.addActionListener(e -> {
            Map<String, String> emptyMap = new HashMap<>();
            //emptyMap.put("Пустой предмет", "незачёт");
            map.put(studentNameTextField.getText(), emptyMap);
            JTableUtils.writeArrayToJTable(studentsTable, MatrixUtils.getMatrixFromKeySet(map.keySet()));
            JTableUtils.writeArrayToJTable(tableStudentGrades, new String[][]{{"",""}});
        });
        minusStudentButton.addActionListener(e -> {
            map.remove(studentNameTextField.getText());
            JTableUtils.writeArrayToJTable(studentsTable, MatrixUtils.getMatrixFromKeySet(map.keySet()));
        });
    }

    public String[][] getTableData (JTable table) {
        DefaultTableModel dtm = (DefaultTableModel) table.getModel();
        int nRow = dtm.getRowCount(), nCol = dtm.getColumnCount();
        String[][] tableData = new String[nRow][nCol];
        for (int i = 0 ; i < nRow ; i++)
            for (int j = 0 ; j < nCol ; j++)
                tableData[i][j] = (String) dtm.getValueAt(i,j);
        return tableData;
    }

    private void recalculateMap() {
        map.put(selectedStudent, getMapFromMatrix(getTableData(tableStudentGrades)));
    }


    public String[][] getMatrixFromEntry(Map.Entry<String, Map<String, String>> entry){
        List<Map.Entry<String, String>> list = entry.getValue().entrySet().stream().toList();
        if (list.isEmpty()) {
            return new String[][]{{"",""}};
        }
        String[][] matrix = new String[list.size()][2];
        for (int i = 0; i < list.size(); i++) {
            String[] row = new String[2];
            row[0] = list.get(i).getKey();
            row[1] = list.get(i).getValue();
            matrix[i] = row;
        }
        return matrix;
    }
    public Map<String, String> getMapFromMatrix(String[][] matrix){
        Map<String, String> map = new HashMap<>();
        for (String[] row : matrix) {
            map.put(row[0], row[1]);
        }
        return map;
    }
}
