package ru.vsu.cs.ereshkin_a_v.task06.model;

import java.util.Objects;

public class FullName {
    private final String firstName;
    private final String lastName;
    private final String patronymic;

    public FullName(String firstName, String lastName, String patronymic) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.patronymic = patronymic;
    }

    public FullName(String FIO) throws IllegalArgumentException{
       String[] split = FIO.trim().split("(\\s)+");
       if (split.length != 3) {
           throw new IllegalArgumentException("Должно быть 3 слова - \"Фамилия Имя Отчество\".");
       }
       this.lastName = split[0];
       this.firstName = split[1];
       this.patronymic = split[2];
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getPatronymic() {
        return patronymic;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FullName fullName = (FullName) o;
        return Objects.equals(firstName, fullName.firstName) && Objects.equals(lastName, fullName.lastName) && Objects.equals(patronymic, fullName.patronymic);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstName, lastName, patronymic);
    }

    public String getString(){
        return lastName + ' ' + firstName + ' ' + patronymic;
    }
}
