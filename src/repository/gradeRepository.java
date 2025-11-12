package repository;

import model.Grade;
import model.Student;
import model.Course;
import exception.invalidScoreException;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class gradeRepository extends baseRepository<Grade> {

    private final List<String> tmpStudentIds = new ArrayList<>();
    private final List<String> tmpCourseIds = new ArrayList<>();

    public gradeRepository(File f) { super(f); }

    @Override
    public void load() {
        items.clear();
        tmpStudentIds.clear();
        tmpCourseIds.clear();
        try {
            if (!file.exists()) {
                if (file.getParentFile() != null) file.getParentFile().mkdirs();
                try (java.io.PrintWriter pw = new java.io.PrintWriter(new java.io.FileWriter(file, false))) {
                    String[] h = getHeader();
                    if (h != null && h.length > 0) pw.println(String.join(",", h));
                }
                return;
            }
            try (java.util.Scanner sc = new java.util.Scanner(file)) {
                boolean first = true;
                while (sc.hasNextLine()) {
                    String line = sc.nextLine();
                    if (line.trim().isEmpty()) continue;
                    if (first) { first = false; continue; }
                    String[] cols = simpleSplit(line);
                    Grade g = new Grade();
                    try {
                        if (cols.length > 3 && !cols[3].trim().isEmpty()) g.setQuiz(parseDouble(cols[3]));
                        if (cols.length > 4 && !cols[4].trim().isEmpty()) g.setMid(parseDouble(cols[4]));
                        if (cols.length > 5 && !cols[5].trim().isEmpty()) g.setFin(parseDouble(cols[5]));
                        if (cols.length > 6 && !cols[6].trim().isEmpty()) g.setProject(parseDouble(cols[6]));
                        if (cols.length > 7 && !cols[7].trim().isEmpty()) g.setTotal(parseDouble(cols[7]));
                    } catch (invalidScoreException ise) {
                        System.err.println("[GradeRepository] Invalid score in file line -> " + ise.getMessage());
                    }
                    items.add(g);
                    String sid = cols.length > 1 ? cols[1].trim() : "";
                    String cid = cols.length > 2 ? cols[2].trim() : "";
                    tmpStudentIds.add(sid);
                    tmpCourseIds.add(cid);
                }
            }
        } catch (Exception e) {
            System.err.println("[GradeRepository] load error: " + e.getMessage());
        }
    }

    @Override
    protected Grade fromCSV(String[] cols) {
        return null;
    }

    @Override
    protected String toCSV(Grade g) {
        String studentId = g.getStudent() != null ? g.getStudent().getId() : "";
        String courseId  = g.getCourse() != null ? g.getCourse().getCode() : "";
        String quiz = fmt(g.getQuiz());
        String mid  = fmt(g.getMid());
        String fin  = fmt(g.getFin());
        String proj = fmt(g.getProject());
        String total = fmt(g.getTotal());
        return String.join(",", "", studentId, courseId, quiz, mid, fin, proj, total);
    }

    @Override
    protected String[] getHeader() {
        return new String[] {"id","studentId","courseId","quiz","mid","final","project","finalScore"};
    }

    @Override
    protected String getId(Grade g) {
        if (g == null || g.getStudent() == null || g.getCourse() == null) return null;
        return g.getStudent().getId() + ":" + g.getCourse().getCode();
    }

    public void attachTo(studentRepository studentRepo, courseRepository courseRepo) {
        for (int i = 0; i < items.size(); i++) {
            Grade g = items.get(i);
            String sid = tmpStudentIds.size() > i ? tmpStudentIds.get(i) : "";
            String cid = tmpCourseIds.size() > i ? tmpCourseIds.get(i) : "";
            if (sid != null && !sid.isEmpty()) {
                studentRepo.findById(sid).ifPresent(student -> {
                    g.setStudent(student);
                    student.addGrade(g);
                });
            }
            if (cid != null && !cid.isEmpty()) {
                courseRepo.findById(cid).ifPresent(g::setCourse);
            }
        }
    }

    private double parseDouble(String s) {
        try { return Double.parseDouble(s.trim()); } catch (Exception e) { return 0.0; }
    }

    private String fmt(Double d) { return d == null ? "" : String.format(Locale.ROOT, "%.2f", d); }
}