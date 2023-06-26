package ru.vsu.cs.ereshkin_a_v.task06.model;

import java.util.Locale;
import java.util.Objects;

public class Grade {
    private final GradeType type;
    private Integer score = null;

    public Grade(String str) {
        String trimmed = str.trim().toLowerCase(Locale.ROOT);
        int l = 0;
        boolean correctNumber = true;
        try {
            l = Integer.parseInt(trimmed);
        } catch (NumberFormatException ignored){
            correctNumber = false;
        }
        if (correctNumber) {
            this.score = l;
            this.type = GradeType.SCORE;
            return;
        }
        if (trimmed.charAt(0) == 'з'){
            this.type = GradeType.OK;
            return;
        }
        this.type = GradeType.NOT_OK;
    }
    public String getString(){
        if (type == GradeType.OK){
            return "ЗАЧЁТ";
        }
        if (type == GradeType.NOT_OK){
            return "НЕЗАЧЁТ";
        }
        return score.toString();
    }

    public GradeType getType() {
        return type;
    }

    public Integer getScore() {
        return score;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Grade grade = (Grade) o;
        return type == grade.type && Objects.equals(score, grade.score);
    }

    @Override
    public int hashCode() {
        return Objects.hash(type, score);
    }
}
