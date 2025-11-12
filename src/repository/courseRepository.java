package repository;

import model.Course;

import java.io.File;

public class courseRepository extends baseRepository<Course> {

    public courseRepository(File f) { super(f); }

    @Override
    protected Course fromCSV(String[] cols) {
        if (cols == null || cols.length < 2) return null;
        Course c = new Course();
        if (cols.length > 1) c.setCode(cols[1].trim());
        if (cols.length > 2) c.setName(cols[2].trim());
        if (cols.length > 3) {
            try { c.setCredit(Integer.parseInt(cols[3].trim())); } catch (Exception ignored) {}
        }
        double wq = cols.length > 4 ? parseDouble(cols[4]) : 0.0;
        double wm = cols.length > 5 ? parseDouble(cols[5]) : 0.0;
        double wf = cols.length > 6 ? parseDouble(cols[6]) : 0.0;
        double wp = cols.length > 7 ? parseDouble(cols[7]) : 0.0;
        try {
            c.setWeights(wq, wm, wf, wp);
        } catch (IllegalArgumentException ignored) { /* keep zeros or handle later */ }
        return c;
    }

    @Override
    protected String toCSV(Course c) {
        String code = safe(c.getCode());
        String name = safe(c.getName());
        String credit = String.valueOf(c.getCredit());
        String wq = String.format(java.util.Locale.ROOT, "%.2f", c.getwQuiz());
        String wm = String.format(java.util.Locale.ROOT, "%.2f", c.getwMid());
        String wf = String.format(java.util.Locale.ROOT, "%.2f", c.getwFinal());
        String wp = String.format(java.util.Locale.ROOT, "%.2f", c.getwProject());
        // write code into both id and courseCode column for compatibility
        return String.join(",", code, code, name, credit, wq, wm, wf, wp);
    }

    @Override
    protected String[] getHeader() {
        return new String[] {"id","courseCode","name","credit","weightQuiz","weightMid","weightFinal","weightProject"};
    }

    @Override
    protected String getId(Course c) {
        return c == null ? null : c.getCode();
    }

    private double parseDouble(String s) {
        if (s == null || s.trim().isEmpty()) return 0.0;
        try { return Double.parseDouble(s.trim()); } catch (Exception e) { return 0.0; }
    }

    private String safe(String s) { return s == null ? "" : s.replace(",", ""); }
}