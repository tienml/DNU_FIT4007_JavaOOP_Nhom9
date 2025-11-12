package repository;

import model.Student;

import java.io.File;

public class studentRepository extends baseRepository<Student> {

    public studentRepository(File f) { super(f); }

    @Override
    protected Student fromCSV(String[] cols) {
        if (cols == null || cols.length < 1) return null;
        String id = cols.length > 0 ? cols[0].trim() : "";
        String fullName = cols.length > 1 ? cols[1].trim() : "";
        String major = cols.length > 2 ? cols[2].trim() : "";
        int year = 0;
        if (cols.length > 3) {
            try { year = Integer.parseInt(cols[3].trim()); } catch (Exception ignored) {}
        }
        Student s = new Student();
        s.setId(id);
        s.setFullName(fullName);
        s.setMajor(major);
        s.setYear(year);
        return s;
    }

    @Override
    protected String toCSV(Student s) {
        String id = s.getId() == null ? "" : s.getId();
        String name = s.getFullName() == null ? "" : s.getFullName();
        String major = s.getMajor() == null ? "" : s.getMajor();
        String year = String.valueOf(s.getYear());
        return String.join(",", id, name, major, year);
    }

    @Override
    protected String[] getHeader() {
        return new String[] {"id","fullName","major","year"};
    }

    @Override
    protected String getId(Student s) {
        return s == null ? null : s.getId();
    }
}