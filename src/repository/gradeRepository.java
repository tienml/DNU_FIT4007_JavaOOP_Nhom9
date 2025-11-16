package repository;

import exception.invalidScoreException;
import model.Grade;
import util.NumberFormatter;
import java.io.File;
import java.util.Optional;

public class gradeRepository extends baseRepository<Grade> {

    public gradeRepository(File f) {
        super(f);
    }

    @Override
    protected Grade fromCSV(String[] cols) {
        if (cols == null || cols.length < 3) return null;

        Grade g = new Grade();

        if (cols.length > 1) g.setStudentId(cols[1].trim());
        if (cols.length > 2) g.setCourseId(cols[2].trim());

        try {
            if (cols.length > 3 && !cols[3].trim().isEmpty())
                g.setQuiz(parseDouble(cols[3]));
            if (cols.length > 4 && !cols[4].trim().isEmpty())
                g.setMid(parseDouble(cols[4]));
            if (cols.length > 5 && !cols[5].trim().isEmpty())
                g.setFin(parseDouble(cols[5]));
            if (cols.length > 6 && !cols[6].trim().isEmpty())
                g.setProject(parseDouble(cols[6]));
            if (cols.length > 7 && !cols[7].trim().isEmpty())
                g.setTotal(parseDouble(cols[7]));
        } catch (invalidScoreException e) {
            System.err.println("[gradeRepository] invalid score in file: " + e.getMessage());
        }

        return g;
    }

    @Override
    protected String toCSV(Grade g) {
        String studentId = g.getStudentId();
        String courseId  = g.getCourseId();

        if ((studentId == null || studentId.isBlank()) && g.getStudent() != null) {
            studentId = g.getStudent().getId();
        }
        if ((courseId == null || courseId.isBlank()) && g.getCourse() != null) {
            courseId = g.getCourse().getCode();
        }

        int rowId = items.indexOf(g) + 1;

        String quiz  = NumberFormatter.formatCSV(g.getQuiz());
        String mid   = NumberFormatter.formatCSV(g.getMid());
        String fin   = NumberFormatter.formatCSV(g.getFin());
        String proj  = NumberFormatter.formatCSV(g.getProject());
        String total = NumberFormatter.formatCSV(g.getTotal());

        return String.join(",",
                String.valueOf(rowId),
                safe(studentId),
                safe(courseId),
                quiz, mid, fin, proj, total
        );
    }

    @Override
    protected String[] getHeader() {
        return new String[] {
                "id","studentId","courseId","quiz","mid","final","project","finalScore"
        };
    }

    @Override
    protected String getId(Grade g) {
        if (g == null) return null;
        String sid = g.getStudentId();
        String cid = g.getCourseId();

        if (sid != null && !sid.isBlank() && cid != null && !cid.isBlank()) {
            return sid + ":" + cid;
        }

        if (g.getStudent() != null && g.getCourse() != null &&
                g.getStudent().getId() != null && g.getCourse().getCode() != null) {
            return g.getStudent().getId() + ":" + g.getCourse().getCode();
        }

        return null;
    }

    public void attachTo(studentRepository studentRepo, courseRepository courseRepo) {
        for (Grade g : items) {
            String sid = g.getStudentId();
            String cid = g.getCourseId();

            if (sid != null && !sid.isBlank()) {
                studentRepo.findById(sid).ifPresent(student -> {
                    g.setStudent(student);
                    student.addGrade(g);
                });
            }
            if (cid != null && !cid.isBlank()) {
                courseRepo.findById(cid).ifPresent(g::setCourse);
            }
        }
    }

    public Optional<Grade> findByStudentAndCourse(String studentId, String courseId) {
        if (studentId == null || courseId == null) return Optional.empty();
        return findById(studentId + ":" + courseId);
    }

    private double parseDouble(String s) {
        try {
            return Double.parseDouble(s.trim());
        }
        catch (Exception e) {
            return 0.0;
        }
    }

    private String safe(String s) {
        return s == null ? "" : s.replace(",", "");
    }
}