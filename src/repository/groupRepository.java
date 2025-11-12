package repository;

import model.Group;

import java.io.File;

public class groupRepository extends baseRepository<Group> {

    public groupRepository(File f) { super(f); }

    @Override
    protected Group fromCSV(String[] cols) {
        if (cols == null || cols.length < 1) return null;
        Group g = new Group();
        if (cols.length > 0) g.setId(cols[0].trim());
        if (cols.length > 1) g.setName(cols[1].trim());
        if (cols.length > 2) g.setFaculty(cols[2].trim());
        return g;
    }

    @Override
    protected String toCSV(Group g) {
        return String.join(",", safe(g.getId()), safe(g.getName()), safe(g.getFaculty()));
    }

    @Override
    protected String[] getHeader() {
        return new String[] {"id","className","facultyName"};
    }

    @Override
    protected String getId(Group g) {
        return g == null ? null : g.getId();
    }

    private String safe(String s) { return s == null ? "" : s.replace(",", ""); }
}