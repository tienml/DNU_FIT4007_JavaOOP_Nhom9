package repository;

import java.io.*;
import java.util.*;

public abstract class baseRepository<T> {
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

    public void load() {
        items.clear();
        if (!file.exists()) {
            if (file.getParentFile() != null) file.getParentFile().mkdirs();
            try (PrintWriter pw = new PrintWriter(new FileWriter(file, false))) {
                String[] h = getHeader();
                if (h != null && h.length > 0) pw.println(String.join(",", h));
            } catch (IOException e) {
                System.err.println("[baseRepository] create file error: " + e.getMessage());
            }
            return;
        }

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            boolean first = true;
            while ((line = br.readLine()) != null) {
                if (line.trim().isEmpty()) continue;
                if (first) { first = false; continue; } // skip header
                String[] cols = simpleSplit(line);
                T obj = fromCSV(cols);
                if (obj != null) items.add(obj);
            }
        } catch (IOException e) {
            System.err.println("[baseRepository] load error: " + e.getMessage());
        }
    }

    public void save() {
        try {
            if (file.getParentFile() != null) file.getParentFile().mkdirs();
            try (PrintWriter pw = new PrintWriter(new FileWriter(file, false))) {
                String[] h = getHeader();
                if (h != null && h.length > 0) pw.println(String.join(",", h));
                for (T obj : items) {
                    pw.println(toCSV(obj));
                }
            }
        } catch (IOException e) {
            System.err.println("[baseRepository] save error: " + e.getMessage());
        }
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