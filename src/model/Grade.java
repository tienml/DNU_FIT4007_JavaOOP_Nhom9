package model;

/**
 * GradeEntry liên kết student-course.
 * TODO:
 *  - id, studentId, courseId
 *  - quiz, mid, fin, project, finalScore
 */
public class Grade {
    private String id;
    private String studentId;
    private String courseId;
    private Double quiz, mid, fin, project;
    private Double finalScore;
}
