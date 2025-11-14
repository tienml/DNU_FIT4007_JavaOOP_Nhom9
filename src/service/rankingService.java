package service;

import model.Student;
import repository.studentRepository;
import repository.gradeRepository;

import java.util.*;

public class rankingService {
    private final studentRepository studentRepo;
    private final gradeRepository gradeRepo;
    private final gradeService gradeService;

    public rankingService(studentRepository studentRepo, gradeRepository gradeRepo) {
        this.studentRepo = studentRepo;
        this.gradeRepo = gradeRepo;
        this.gradeService = new gradeService(gradeRepo);
    }
    private List<Student> sortByGPA(List<Student> students) {
        Map<String, Double> gpaCache = new HashMap<>();

        for (Student s : students) {
            gpaCache.put(s.getId(), gradeService.calculateGPA(s.getId()));
        }

        return students.stream()
                .sorted((a, b) -> Double.compare(
                        gpaCache.get(b.getId()),
                        gpaCache.get(a.getId())
                ))
                .toList();
    }

    public List<Student> rankingByClass(String className) {
        List<Student> filtered = studentRepo.getAll().stream()
                .filter(s -> s.getClassName().equalsIgnoreCase(className))
                .toList();

        return sortByGPA(filtered);
    }

    public List<Student> rankingByFaculty(String faculty) {
        List<Student> filtered = studentRepo.getAll().stream()
                .filter(s -> s.getMajor().equalsIgnoreCase(faculty))
                .toList();

        return sortByGPA(filtered);
    }

    // Xếp hạng toàn trường
    public List<Student> rankingSchool() {
        return sortByGPA(studentRepo.getAll());
    }
}
