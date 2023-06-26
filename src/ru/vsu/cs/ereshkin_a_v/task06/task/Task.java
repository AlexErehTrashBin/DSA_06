package ru.vsu.cs.ereshkin_a_v.task06.task;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;


/**
 * Вариант "как требуется в задании"
 * */
@SuppressWarnings("unused")
public class Task {
    /**
     * Словарь вида:
     * Студент -> Индексы строк в матрице
     *
     * @param matrix матрица строк (получается из CSV файла)
     */
    private static Map<String, List<Integer>> getStudentIndexMap(String[][] matrix) {
        Map<String, List<Integer>> studentIndexMap = new HashMap<>();
        for (int i = 0; i < matrix.length; i++) {
            String fullName = matrix[i][1];
            if (!studentIndexMap.containsKey(fullName)) {
                studentIndexMap.put(fullName, new LinkedList<>());
                studentIndexMap.get(fullName).add(i);
                continue;
            }
            studentIndexMap.get(fullName).add(i);
        }
        return studentIndexMap;
    }

    /**
     * Словарь вида:
     * Студент -> Словарь "Дисциплина -> Оценка по дисциплине"
     *
     * @param matrix матрица строк (получается из CSV файла)
     */
    public static Map<String, Map<String, String>> solve(String[][] matrix) {
        Map<String, List<Integer>> studentIndexMap = getStudentIndexMap(matrix);
        Map<String, Map<String, String>> result = new HashMap<>();
        for (Map.Entry<String, List<Integer>> name : studentIndexMap.entrySet()) {
            Map<String, String> disciplineGradeMap = new HashMap<>();

            for (Integer line : name.getValue()) {
                String discipline = matrix[line][0];
                String grade = matrix[line][2];
                disciplineGradeMap.put(discipline, grade);
            }
            result.put(name.getKey(), disciplineGradeMap);
        }
        return result;
    }
}
