package com.javarush.task.task29.task2909.human;

import java.util.ArrayList;
import java.util.List;

public class University {
    private List<Student> students = new ArrayList<>();
    private String name;
    private int age;

    public University(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Student getStudentWithAverageGrade(double averageGrade) {
        return students.stream().filter(student -> student.getAverageGrade() == averageGrade).findFirst().orElse(null);
    }

    public Student getStudentWithMaxAverageGrade() {
        if (students.isEmpty())
            return null;
        Student studentWithMaxAverageGrade = students.get(0);
        for (Student student : students) {
            if (studentWithMaxAverageGrade.getAverageGrade() < student.getAverageGrade())
                studentWithMaxAverageGrade = student;
        }
        return studentWithMaxAverageGrade;
    }

    public Student getStudentWithMinAverageGrade() {
        if (students.isEmpty())
            return null;
        Student studentWithMaxAverageGrade = students.get(0);
        for (Student student : students) {
            if (studentWithMaxAverageGrade.getAverageGrade() > student.getAverageGrade())
                studentWithMaxAverageGrade = student;
        }
        return studentWithMaxAverageGrade;
    }

    public void expel(Student student) {
        students.remove(student);
    }
}