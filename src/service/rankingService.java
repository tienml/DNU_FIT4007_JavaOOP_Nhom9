package service;

import model.Student;
import repository.studentRepository;
import repository.gradeRepository;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class rankingService {
    private final studentRepository studentRepo;
    private final gradeRepository gradeRepo; // thêm

    public rankingService(studentRepository studentRepo, gradeRepository gradeRepo) {
        this.studentRepo = studentRepo;
        this.gradeRepo = gradeRepo;
    }

    public List<Student> rankingByMajorAndYear(String major, int year) {
        return studentRepo.getAll().stream()
                .filter(s -> s.getMajor().equalsIgnoreCase(major) && s.getYear() == year)
                .sorted(Comparator.comparingDouble(Student::calculateGPA).reversed())
                .collect(Collectors.toList());
    }

    public List<Student> rankingByMajor(String major) {
        return studentRepo.getAll().stream()
                .filter(s -> s.getMajor().equalsIgnoreCase(major))
                .sorted(Comparator.comparingDouble(Student::calculateGPA).reversed())
                .collect(Collectors.toList());
    }

    public List<Student> rankingSchool() {
        return studentRepo.getAll().stream()
                .sorted(Comparator.comparingDouble(Student::calculateGPA).reversed())
                .collect(Collectors.toList());
    }
}
