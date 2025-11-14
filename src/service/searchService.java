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

        List<Student> students = studentRepo.getAll();
        if (students == null || students.isEmpty()) return null;

        for (Student s : students) {
            if (s.getId().equalsIgnoreCase(studentId)) {
                return s;
            }
        }
        return null;
    }
    public List<Student> searchByName(String name) {
        List<Student> result = new ArrayList<>();

        if (name == null || name.trim().isEmpty()) return result;

        List<Student> students = studentRepo.getAll();
        if (students == null || students.isEmpty()) return result;

        String keyword = name.toLowerCase();

        for (Student s : students) {
            if (s.getFullName().toLowerCase().contains(keyword)) {
                result.add(s);
            }
        }
        return result;
    }
}
