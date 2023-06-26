package ru.vsu.cs.ereshkin_a_v.task06.gui;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class GUIUtils {
    public static String[][] getMatrixFromMap(Map<String, String> map){
        if (map.isEmpty()) return new String[0][];
        List<String[]> list = new ArrayList<>();
        for (Map.Entry<String, String> entry : map.entrySet()) {
            String[] arr = new String[2];
            arr[0] = entry.getKey();
            arr[1] = entry.getValue();
            list.add(arr);
        }
        return getMatrixFromList(list);
    }
    private static String[][] getMatrixFromList(List<String[]> list){
        String[][] matrix = new String[list.size()][];
        for (int i = 0; i < list.size(); i++) {
            matrix[i] = list.get(i);
        }
        return matrix;
    }
}
