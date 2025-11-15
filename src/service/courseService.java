package service;

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
        return repo.add(c);
    }

    public boolean updateCourse(String code, Course updated) {
        var existing = repo.findById(code).orElse(null);
        if (existing == null) return false;
        updated.setCode(code);
        return repo.update(updated);
    }

    public boolean deleteCourse(String code) {
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
}
