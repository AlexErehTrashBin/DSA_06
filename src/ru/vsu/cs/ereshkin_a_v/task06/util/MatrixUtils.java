package ru.vsu.cs.ereshkin_a_v.task06.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Set;

public class MatrixUtils {
    public static String toString(String[][] arr2){
        StringBuilder sb = new StringBuilder();
        for (int r = 0; r < arr2.length; r++) {
            if (r > 0) {
                sb.append(System.lineSeparator());
            }
            sb.append(toString(arr2[r]));
        }
        return sb.toString();
    }
    private static String toString(String[] arr) {
        if (arr == null) {
            return null;
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < arr.length; i++) {
            if (i > 0) {
                sb.append(",");
            }
            sb.append(String.format(Locale.ROOT, arr[i]));
        }
        return sb.toString();
    }
    public static String[][] getStringArrInCol(String[] arr){
        String[][] matrix = new String[arr.length][1];
        for (int i = 0; i < arr.length; i++) {
            matrix[i][0] = arr[i];
        }
        return matrix;
    }
    public static String[][] getMatrixFromKeySet(Set<String> set){
        List<String> list = new ArrayList<>(set);
        String[][] matrix = new String[list.size()][1];
        for (int i = 0; i < list.size(); i++) {
            matrix[i][0] = list.get(i);
        }
        return matrix;
    }
}
