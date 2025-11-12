package repository;

import model.Lecturer;

import java.io.File;

public class lecturerRepository extends baseRepository<Lecturer> {

    public lecturerRepository(File f) {
        super(f);
    }

    @Override
    protected Lecturer fromCSV(String[] cols) {
        if (cols == null || cols.length < 1) return null;
        Lecturer l = new Lecturer();
        if (cols.length > 0) l.setId(cols[0].trim());
        // if lecturerCode present at cols[1] we ignore (no field in model)
        if (cols.length > 2) l.setFullName(cols[2].trim());
        if (cols.length > 3) l.setDepartment(cols[3].trim());
        if (cols.length > 4) l.setEmail(cols[4].trim());
        return l;
    }

    @Override
    protected String toCSV(Lecturer l) {
        return String.join(",", safe(l.getId()), safe(l.getId()), safe(l.getFullName()), safe(l.getDepartment()), safe(l.getEmail()));
    }

    @Override
    protected String[] getHeader() {
        return new String[] {"id","lecturerCode","fullName","department","email"};
    }

    @Override
    protected String getId(Lecturer l) {
        return l == null ? null : l.getId();
    }

    private String safe(String s) { return s == null ? "" : s.replace(",", ""); }
}