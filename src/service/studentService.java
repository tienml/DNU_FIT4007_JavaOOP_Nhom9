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
        return repo.add(s);
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

    public List<Student> getAll() {
        return repo.getAll();
    }
}
