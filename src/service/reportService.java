package service;

import cli.reportGenerator;
import model.Student;
import repository.studentRepository;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class reportService {
    private final studentRepository studentRepo;
    private final reportGenerator generator = new reportGenerator();
    private final classificationService classifier = new classificationService();

    public reportService(studentRepository studentRepo) {
        this.studentRepo = studentRepo;
    }

    public List<Student> getTopGPAStudents() {
        double maxGPA = studentRepo.getAll().stream()
                .mapToDouble(Student::calculateGPA)
                .max().orElse(0);
        return studentRepo.getAll().stream()
                .filter(s -> Math.abs(s.calculateGPA() - maxGPA) < 1e-6)
                .collect(Collectors.toList());
    }

    public Map<String, Long> getClassificationStatistics() {
        return studentRepo.getAll().stream()
                .collect(Collectors.groupingBy(
                        s -> classifier.classify(s.calculateGPA()),
                        Collectors.counting()
                ));
    }

    public void exportReportToFile(String path) {
        generator.exportReportToFile(path, studentRepo.getAll());
    }
}
