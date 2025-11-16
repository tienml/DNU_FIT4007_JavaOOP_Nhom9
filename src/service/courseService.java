package service;

import exception.courseNotFoundException;
import model.Course;
import repository.courseRepository;

import java.util.List;
import java.util.stream.Collectors;

public class courseService {
    private final courseRepository repo;

    public courseService(courseRepository repo) {
        this.repo = repo;
    }

    public boolean addCourse(Course c) {
        if (repo.findById(c.getCode()).isPresent()) {
            System.out.println("→ Mã môn học đã tồn tại!");
            return false;
        }
        return repo.add(c);
    }

    public boolean updateCourse(String code, Course updated) {
        var existing = repo.findById(code).orElse(null);
        if (existing == null) {
            System.out.println("→ Môn học không tồn tại.");
            return false;
        }
        updated.setCode(code);
        return repo.update(updated);
    }

    public boolean deleteCourse(String code) {
        var existing = repo.findById(code).orElse(null);
        if (existing == null) {
            System.out.println("→ Môn học không tồn tại.");
            return false;
        }
        return repo.delete(code);
    }

    public List<Course> search(String keyword) {
        return repo.getAll().stream()
                .filter(c -> c.getCode().toLowerCase().contains(keyword.toLowerCase()) ||
                        c.getName().toLowerCase().contains(keyword.toLowerCase()))
                .collect(Collectors.toList());
    }

    public List<Course> getAll() {
        return repo.getAll();
    }

    public Course findOrThrow(String code) throws courseNotFoundException {
        return repo.findById(code)
                .orElseThrow(() -> new courseNotFoundException(code));
    }
}