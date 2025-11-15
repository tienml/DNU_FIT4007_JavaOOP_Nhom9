package service;

import model.Student;
import repository.studentRepository;

import java.util.ArrayList;
import java.util.List;

public class searchService {

    private final studentRepository studentRepo;

    public searchService(studentRepository repo) {
        this.studentRepo = repo;
    }

    public Student searchById(String studentId) {
        if (studentId == null || studentId.isEmpty()) return null;

        return studentRepo.getAll().stream()
                .filter(s -> s.getId().equalsIgnoreCase(studentId))
                .findFirst().orElse(null);
    }

    public List<Student> searchByName(String name) {
        if (name == null || name.trim().isEmpty()) return new ArrayList<>();

        String keyword = name.toLowerCase();

        List<Student> result = new ArrayList<>();
        for (Student s : studentRepo.getAll()) {
            if (s.getFullName() != null && s.getFullName().toLowerCase().contains(keyword)) {
                result.add(s);
            }
        }
        return result;
    }
}
