package model;

/**
 * Course cấu hình trọng số.
 * TODO:
 *  - courseCode, name, credit
 *  - wQuiz, wMid, wFinal, wProject (tổng = 1.0)
 */
public class Course {
    private String id;
    private String courseCode;
    private String name;
    private int credit;
    private double wQuiz, wMid, wFinal, wProject;
}
