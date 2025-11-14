package model;
import exception.invalidScoreException;

public class Grade {
    private String studentId;
    private String courseId;
    private Student student;
    private Course course;
    private Double quiz;
    private Double mid;
    private Double fin;
    private Double project;
    private Double total;

    public Grade() {
    }

    public Grade(String studentId, String courseId) {
        this.studentId = studentId;
        this.courseId = courseId;
    }

    public Grade(Student student, Course course) {
        setStudent(student); // sẽ đồng bộ luôn studentId
        setCourse(course);   // sẽ đồng bộ luôn courseId
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getCourseId() {
        return courseId;
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }


    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
        if (student != null) {
            this.studentId = student.getId();
        }
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
        if (course != null) {
            this.courseId = course.getCode();
        }
    }

    public Double getQuiz() {
        return quiz;
    }

    public void setQuiz(Double quiz) throws invalidScoreException {
        validateScore(quiz);
        this.quiz = quiz;
    }

    public Double getMid() {
        return mid;
    }

    public void setMid(Double mid) throws invalidScoreException {
        validateScore(mid);
        this.mid = mid;
    }

    public Double getFin() {
        return fin;
    }

    public void setFin(Double fin) throws invalidScoreException {
        validateScore(fin);
        this.fin = fin;
    }

    public Double getProject() {
        return project;
    }

    public void setProject(Double project) throws invalidScoreException {
        validateScore(project);
        this.project = project;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) throws invalidScoreException {
        validateScore(total);
        this.total = total;
    }

    public void validateScore(Double s) throws invalidScoreException {
        if (s != null && (s < 0 || s > 10)) {
            throw new invalidScoreException("The score must be between 0 and 10, or null if it has not been entered.");
        }
    }

    @Override
    public String toString() {
        String sName = student != null ? student.getFullName() : (studentId != null ? studentId : "N/A");
        String cName = course != null ? course.getName() : (courseId != null ? courseId : "N/A");
        return String.format("%s - %s: [quiz=%.2f, mid=%.2f, final=%.2f, proj=%.2f, total=%.2f]",
                sName,
                cName,
                quiz != null ? quiz : 0,
                mid != null ? mid : 0,
                fin != null ? fin : 0,
                project != null ? project : 0,
                total != null ? total : 0);
    }
}