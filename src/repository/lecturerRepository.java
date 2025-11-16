package repository;

import model.Lecturer;
import util.IDGenerator;

import java.io.File;

public class lecturerRepository extends baseRepository<Lecturer> {

    public lecturerRepository(File f) {
        super(f);
    }

    @Override
    protected Lecturer fromCSV(String[] cols) {
        if (cols == null || cols.length < 3) return null;
        Lecturer l = new Lecturer();
        if (cols.length > 1) l.setId(cols[1].trim());
        if (cols.length > 2) l.setFullName(cols[2].trim());
        if (cols.length > 3) l.setDepartment(cols[3].trim());
        if (cols.length > 4) l.setEmail(cols[4].trim());
        return l;
    }

    @Override
    protected String toCSV(Lecturer l) {
        String rowId = safe(l.getId());
        String lecturerCode = safe(l.getId());

        if (lecturerCode.matches("L\\d+")) {
            try {
                rowId = String.valueOf(Integer.parseInt(lecturerCode.substring(1)));
            } catch (Exception ignored) {}
        }

        return String.join(",",
                rowId,
                lecturerCode,
                safe(l.getFullName()),
                safe(l.getDepartment()),
                safe(l.getEmail())
        );
    }

    @Override
    protected String[] getHeader() {
        return new String[] {"id","lecturerCode","fullName","department","email"};
    }

    @Override
    protected String getId(Lecturer l) {
        return l == null ? null : l.getId();
    }

    @Override
    public boolean add(Lecturer l) {
        if (l.getId() == null || l.getId().isEmpty()) {
            int nextNumber = this.getAll().size() + 1;
            l.setId(IDGenerator.nextId("L", nextNumber));
        }

        if (findById(l.getId()).isPresent()) {
            return false;
        }

        items.add(l);
        return true;
    }

    private String safe(String s) {
        return s == null ? "" : s.replace(",", "");
    }
}