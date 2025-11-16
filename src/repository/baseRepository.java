package repository;

import iface.Persistable;
import java.io.*;
import java.util.*;
import util.CSVUtils;

public abstract class baseRepository<T> implements Persistable {
    protected final File file;
    protected final List<T> items = new ArrayList<>();

    protected baseRepository(File file) {
        this.file = file;
    }

    protected abstract T fromCSV(String[] cols);
    protected abstract String toCSV(T obj);
    protected abstract String[] getHeader();
    protected abstract String getId(T obj);

    protected String[] simpleSplit(String line) {
        return line.split(",", -1);
    }

    @Override
    public void load() {
        items.clear();

        if (!file.exists()) {
            if (file.getParentFile() != null) file.getParentFile().mkdirs();
            save();
            return;
        }

        List<String[]> rows = CSVUtils.read(file);
        if (rows.isEmpty()) return;

        boolean first = true;
        for (String[] cols : rows) {
            if (first) { first = false; continue; }
            T obj = fromCSV(cols);
            if (obj != null) items.add(obj);
        }
    }

    @Override
    public void save() {
        List<String[]> rows = new ArrayList<>();
        String[] header = getHeader();
        if (header != null && header.length > 0) {
            rows.add(header);
        }
        for (T obj : items) {
            rows.add(toCSV(obj).split(",", -1));
        }
        CSVUtils.write(file, rows);
    }

    public List<T> getAll() {
        return new ArrayList<>(items);
    }

    public Optional<T> findById(String id) {
        if (id == null) return Optional.empty();
        return items.stream()
                .filter(i -> {
                    String oid = getId(i);
                    return oid != null && oid.equals(id);
                })
                .findFirst();
    }

    public boolean add(T obj) {
        String id = getId(obj);
        if (id != null && findById(id).isPresent()) return false;
        items.add(obj);
        return true;
    }

    public boolean update(T obj) {
        String id = getId(obj);
        if (id == null) return false;
        for (int i = 0; i < items.size(); i++) {
            if (id.equals(getId(items.get(i)))) {
                items.set(i, obj);
                return true;
            }
        }
        return false;
    }

    public boolean delete(String id) {
        return items.removeIf(i -> {
            String oid = getId(i);
            return oid != null && oid.equals(id);
        });
    }
}