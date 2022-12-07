package ru.hogwarts.school.model;

public class Student {
    private long id;
    private String name;
    private int age;

    public Student() {
    }

    public Student(long id, String name, int age) {
        this.id = id;
        this.name = name;
        this.age = age;
    }
}
