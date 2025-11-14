package service;

import model.Course;
import repository.courseRepository;

import java.util.List;

public class courseService {
    private final courseRepository repo;

    public courseService(courseRepository repo) {
        this.repo = repo;
    }

    public boolean addCourse(Course c) {
        if (repo.findById(c.getCode()).isPresent()) return false;
        repo.add(c);
        return true;
    }
    public boolean updateCourse(String id, Course c) {
        return repo.update(c);
    }
    public boolean deleteCourse(String id) {
        return repo.delete(id);
    }
    public List<Course> search(String keyword) {
        keyword = keyword.toLowerCase();
        String finalKeyword = keyword;
        return repo.getAll().stream()
                .filter(c -> c.getCode().toLowerCase().contains(finalKeyword)
                        || c.getName().toLowerCase().contains(finalKeyword))
                .toList();
    }

    public List<Course> getAll() {
        return repo.getAll();
    }
}
