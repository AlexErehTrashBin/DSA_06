package ru.vsu.cs.ereshkin_a_v.task06.task;

import ru.vsu.cs.ereshkin_a_v.task06.model.Discipline;
import ru.vsu.cs.ereshkin_a_v.task06.model.FullName;
import ru.vsu.cs.ereshkin_a_v.task06.model.Grade;

import java.util.*;


/**
 * Вариант "как хотелось бы"
 * Естественно, что в каком-нибудь реальном приложении может потребоваться не просто иметь кучки строк,
 * а объекты, чтобы если что расширять функционал.
 * */
public class TaskExp {
    /**
     * Словарь вида:
     * Студент -> Индексы строк в матрице
     *
     * @param matrix матрица строк (получается из CSV файла)
     */
    private static Map<FullName, List<Integer>> getStudentIndexMap(String[][] matrix) {
        Map<FullName, List<Integer>> studentIndexMap = new HashMap<>();
        for (int i = 0; i < matrix.length; i++) {
            FullName fullName = new FullName(matrix[i][1]);
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
    public static Map<FullName, Map<Discipline, Grade>> solve(String[][] matrix) {
        Map<FullName, List<Integer>> studentIndexMap = getStudentIndexMap(matrix);
        Map<FullName, Map<Discipline, Grade>> result = new HashMap<>();
        for (Map.Entry<FullName, List<Integer>> name : studentIndexMap.entrySet()) {
            Map<Discipline, Grade> disciplineGradeMap = new HashMap<>();

            for (Integer line : name.getValue()) {
                Discipline discipline = new Discipline(matrix[line][0]);
                Grade grade = new Grade(matrix[line][2]);
                disciplineGradeMap.put(discipline, grade);
            }
            result.put(name.getKey(), disciplineGradeMap);
        }
        return result;
    }
}
