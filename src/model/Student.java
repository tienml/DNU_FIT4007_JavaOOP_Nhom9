package model;

import java.util.ArrayList;
import java.util.List;

public class Student extends Person {
    private String major;
    private int year;
    private List<Grade> grades;

    public Student(String id, String fullName, String major, int year) {
        super(id, fullName);
        this.major = major;
        this.year = year;
        this.grades = new ArrayList<>();
    }

    public Student() {
        this.grades = new ArrayList<>();
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public List<Grade> getGrades() {
        return grades;
    }

    public void setGrades(List<Grade> grades) {
        this.grades = grades;
    }

    public void addGrade(Grade g) {
        grades.add(g);
    }
    public double calculateGPA() {
        double totalWeightedScore = 0.0;
        double totalCredits = 0.0;

        for (Grade g : grades) {
            if (g.getTotal() != null && g.getCourse() != null) {
                double score = g.getTotal();
                double credit = g.getCourse().getCredit();
                totalWeightedScore += score * credit;
                totalCredits += credit;
            }
        }

        if (totalCredits == 0) return 0.0;

        return totalWeightedScore / totalCredits;
    }

    @Override
    public String toString() {
        return String.format("%s - %s (Ngành: %s, Năm: %d)", id, fullName, major, year);
    }
}