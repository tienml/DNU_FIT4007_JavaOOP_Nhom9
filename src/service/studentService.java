package service;

import model.Student;
import repository.studentRepository;

import java.util.List;
import java.util.Optional;

public class studentService {
    private final studentRepository repo;

    public studentService(studentRepository repo) {
        this.repo = repo;
    }

    public boolean addStudent(Student s) {
        if (repo.findById(s.getId()).isPresent()) return false;
        repo.add(s);
        return true;
    }

    public boolean updateStudent(Student s) {
        return repo.update(s);
    }

    public boolean deleteStudent(String id) {
        return repo.delete(id);
    }

    public Optional<Student> findById(String id) {
        return repo.findById(id);
    }

    public List<Student> search(String keyword) {
        keyword = keyword.toLowerCase();
        return repo.getAll().stream()
                .filter(s -> s.getId().toLowerCase().contains(keyword)
                        || s.getFullName().toLowerCase().contains(keyword))
                .toList();
    }

    public List<Student> getAll() {
        return repo.getAll();
    }
}
