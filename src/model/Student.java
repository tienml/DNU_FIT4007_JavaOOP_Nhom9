package model;

import iface.Rankable;
import java.util.ArrayList;
import java.util.List;

public class Student extends Person implements Rankable {
    private String major;
    private int year;
    private String rowId;
    private String gender;
    private String birthDate;
    private String classGroupId;
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

    public String getRowId() {
        return rowId;
    }
    public void setRowId(String rowId) {
        this.rowId = rowId;
    }

    public String getGender() {
        return gender;
    }
    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getBirthDate() {
        return birthDate;
    }
    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    public String getClassGroupId() {
        return classGroupId;
    }
    public void setClassGroupId(String classGroupId) {
        this.classGroupId = classGroupId;
    }

    public void addGrade(Grade g) {
        grades.add(g);
    }

    @Override
    public double calculateGPA() {
        double totalWeightedScore = 0.0;
        double totalCredits = 0.0;

        for (Grade g : grades) {
            if (g.getTotal() != null && g.getCourse() != null) {
                double total10 = g.getTotal();
                double total4  = convertTo4Scale(total10);
                int credit     = g.getCourse().getCredit();

                totalWeightedScore += total4 * credit;
                totalCredits       += credit;
            }
        }

        if (totalCredits == 0) return 0.0;
        return totalWeightedScore / totalCredits;
    }

    private double convertTo4Scale(double total10) {
        if (total10 >= 8.5) return 4.0;
        if (total10 >= 7.8) return 3.5;
        if (total10 >= 7.0) return 3.0;
        if (total10 >= 6.3) return 2.5;
        if (total10 >= 5.5) return 2.0;
        if (total10 >= 4.8) return 1.5;
        if (total10 >= 4.0) return 1.0;
        return 0.0;
    }

    @Override
    public String toString() {
        return String.format("%s - %s (Ngành: %s, Năm: %d)", id, fullName, major, year);
    }
}