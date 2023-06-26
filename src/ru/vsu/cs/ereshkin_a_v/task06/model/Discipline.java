package ru.vsu.cs.ereshkin_a_v.task06.model;

import java.util.Objects;

public class Discipline {
    private final String disciplineName;

    public Discipline(String disciplineName) {
        this.disciplineName = disciplineName.trim();
    }

    public String getDisciplineName() {
        return disciplineName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Discipline that = (Discipline) o;
        return Objects.equals(disciplineName, that.disciplineName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(disciplineName);
    }
}
