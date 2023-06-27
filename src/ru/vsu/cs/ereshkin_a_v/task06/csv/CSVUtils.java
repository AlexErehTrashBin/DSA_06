package ru.vsu.cs.ereshkin_a_v.task06.csv;

import ru.vsu.cs.util.ArrayUtils;

import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.Collection;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

public class CSVUtils {
    public static String[][] getFromFile(String path) throws FileNotFoundException {
        String[] lines = ArrayUtils.readLinesFromFile(path);
        String[][] result = new String[lines.length][];
        for (int i = 0; i < lines.length; i++) {
            String[] lineSplit = lines[i].split("(,)+");
            result[i] = lineSplit;
        }
        return result;
    }

    public static void writeToFile(String[][] matrix, String filePath) {
        PrintStream ps = System.out;
        try {
            ps = new PrintStream(filePath);
        } catch (FileNotFoundException ignored) {
        }
        for (int i = 0; i < matrix.length - 1; i++) {
            String[] row = matrix[i];
            int j = 0;
            for (; j < row.length - 1; j++) {
                ps.print(row[j] + ",");
            }
            ps.print(row[j]);
            ps.println();
        }
        String[] row = matrix[matrix.length - 1];
        int i = 0;
        for (; i < row.length - 1; i++) {
            ps.print(row[i] + ",");
        }
        ps.print(row[i]);
        ps.println();
    }

    public static void writeGradesMapToFileBeautifully(Map<String, Map<String, String>> map, String path) {
        PrintStream ps;
        try {
            ps = new PrintStream(path);
        } catch (FileNotFoundException ignored) {
            return;
        }
        for (String studentName : map.keySet()) {
            ps.println(studentName);
            for (Map.Entry<String, String> entry : map.get(studentName).entrySet()) {
                ps.println(entry.getKey() + " -> " + entry.getValue());
            }
        }
    }

    public static void writeGradesMapToFileBullshit(Map<String, Map<String, String>> map, String path) {
        PrintStream ps;
        try {
            ps = new PrintStream(path);
        } catch (FileNotFoundException ignored) {
            return;
        }
        for (String studentName : map.keySet()) {
            Map<String,String> studentMap = map.get(studentName);
            if (studentMap.isEmpty()) {
                ps.println("," + studentName + ",");
            }
            for (Map.Entry<String, String> entry : map.get(studentName).entrySet()) {
                ps.println(entry.getKey() + ',' + studentName + ',' + entry.getValue());
            }
        }
    }
}
