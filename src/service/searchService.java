package service;

import model.Student;
import repository.studentRepository;

import java.util.ArrayList;
import java.util.List;

public class searchService {

    private studentRepository studentRepo;

    public searchService(studentRepository repo) {
        this.studentRepo = repo;
    }
    public Student searchById(String studentId) {
        for (Student s : studentRepo.getAll()) {
            if (s.getId().equalsIgnoreCase(studentId)) {
                return s;
            }
        }
        return null;
    }
    public List<Student> searchByName(String name) {
        List<Student> result = new ArrayList<>();
        for (Student s : studentRepo.getAll()) {
            if (s.getFullName().toLowerCase().contains(name.toLowerCase())) {
                result.add(s);
            }
        }
        return result;
    }
}
