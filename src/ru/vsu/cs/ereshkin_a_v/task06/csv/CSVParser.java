package ru.vsu.cs.ereshkin_a_v.task06.csv;

import ru.vsu.cs.util.ArrayUtils;

import java.io.FileNotFoundException;

public class CSVParser {
    public static String[][] getFromFile(String path) throws FileNotFoundException {
        String[] lines = ArrayUtils.readLinesFromFile(path);
        String[][] result = new String[lines.length][];
        for (int i = 0; i < lines.length; i++) {
            String[] lineSplit = lines[i].split("(,)+");
            result[i] = lineSplit;
        }
        return result;
    }
}
