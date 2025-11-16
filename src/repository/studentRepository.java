package repository;

import model.Student;
import util.IDGenerator;

import java.io.File;

public class studentRepository extends baseRepository<Student> {

    public studentRepository(File f) { super(f); }

    @Override
    protected Student fromCSV(String[] cols) {
        if (cols == null || cols.length < 6) return null;

        String rowId       = cols[0].trim();
        String studentCode = cols[1].trim();
        String fullName    = cols[2].trim();
        String gender      = cols[3].trim();
        String birthDate   = cols[4].trim();
        String classGroup  = cols[5].trim();

        Student s = new Student();
        s.setRowId(rowId);
        s.setId(studentCode);
        s.setFullName(fullName);
        s.setGender(gender);
        s.setBirthDate(birthDate);
        s.setClassGroupId(classGroup);
        s.setMajor(classGroup);
        try {
            if (!birthDate.isEmpty()) {
                String[] parts = birthDate.split("-");
                s.setYear(Integer.parseInt(parts[0]));
            }
        } catch (Exception ignored) {}

        return s;
    }

    @Override
    protected String toCSV(Student s) {
        String rowId       = s.getRowId()        == null ? "" : s.getRowId();
        String studentCode = s.getId()           == null ? "" : s.getId();
        String fullName    = s.getFullName()     == null ? "" : s.getFullName();
        String gender      = s.getGender()       == null ? "" : s.getGender();
        String birthDate   = s.getBirthDate();
        if (birthDate == null || birthDate.isBlank()) {
            if (s.getYear() > 0) {
                birthDate = s.getYear() + "-01-01";
            } else {
                birthDate = "";
            }
        }
        String classGroup  = s.getClassGroupId();
        if (classGroup == null || classGroup.isBlank()) {
            classGroup = s.getMajor() == null ? "" : s.getMajor();
        }

        return String.join(",", rowId, studentCode, fullName, gender, birthDate, classGroup);
    }

    @Override
    protected String[] getHeader() {
        return new String[] {"id","studentCode","fullName","gender","birthDate","classGroupId"};
    }

    @Override
    protected String getId(Student s) {
        return s == null ? null : s.getId();
    }

    private int nextRowId() {
        int max = 0;
        for (Student s : items) {
            try {
                if (s.getRowId() != null && !s.getRowId().isBlank()) {
                    int v = Integer.parseInt(s.getRowId().trim());
                    if (v > max) max = v;
                }
            } catch (NumberFormatException ignored) {}
        }
        return max + 1;
    }

    private int nextStudentNumber() {
        int max = 0;
        for (Student s : items) {
            String code = s.getId(); // SV001
            if (code == null) continue;
            try {
                String numPart = code.replaceAll("[^0-9]", "");
                if (!numPart.isEmpty()) {
                    int v = Integer.parseInt(numPart);
                    if (v > max) max = v;
                }
            } catch (NumberFormatException ignored) {}
        }
        return max + 1;
    }

    @Override
    public boolean add(Student s) {
        String currentCode = s.getId();
        if (currentCode != null && findById(currentCode).isPresent()) {
            return false;
        }

        int newRowId = nextRowId();
        int newNum   = nextStudentNumber();

        s.setRowId(String.valueOf(newRowId));
        s.setId(IDGenerator.nextId("SV", newNum));

        items.add(s);
        return true;
    }
}