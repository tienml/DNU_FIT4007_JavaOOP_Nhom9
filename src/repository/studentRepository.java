package repository;

import model.Student;

import java.io.File;

public class studentRepository extends baseRepository<Student> {

    public studentRepository(File f) { super(f); }

    @Override
    protected Student fromCSV(String[] cols) {
        if (cols == null || cols.length < 6) return null;

        String id = cols[1].trim();
        String fullName = cols[2].trim();
        String major = cols[5].trim();

        int year = 0;
        try {
            String[] parts = cols[4].trim().split("-"); // YYYY-MM-DD
            year = Integer.parseInt(parts[0]);
        } catch (Exception ignored) {}

        Student s = new Student();
        s.setId(id);
        s.setFullName(fullName);
        s.setMajor(major);
        s.setYear(year);

        return s;
    }

    @Override
    protected String toCSV(Student s) {
        String stt = ""; // STT nếu cần, hoặc để trống
        String id = s.getId() == null ? "" : s.getId();
        String fullName = s.getFullName() == null ? "" : s.getFullName();
        String gender = ""; // chưa lưu
        String dob = s.getYear() == 0 ? "" : s.getYear() + "-01-01"; // giả lập ngày sinh
        String major = s.getMajor() == null ? "" : s.getMajor();

        return String.join(",", stt, id, fullName, gender, dob, major);
    }

    @Override
    protected String[] getHeader() {
        return new String[] {"STT","ID","FullName","Gender","DOB","Major"};
    }

    @Override
    protected String getId(Student s) {
        return s == null ? null : s.getId();
    }
}
