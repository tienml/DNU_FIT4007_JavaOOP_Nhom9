package service;

import cli.reportGenerator;
import iface.Rankable;
import model.Grade;
import model.Student;
import repository.studentRepository;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class reportService {
    private final studentRepository studentRepo;
    private final reportGenerator generator = new reportGenerator();

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
                .map(s -> (Rankable) s)
                .collect(Collectors.groupingBy(
                        Rankable::classify,
                        Collectors.counting()
                ));
    }

    public List<Student> getStudentsWithMinCourses(int minCourses) {
        return studentRepo.getAll().stream()
                .filter(s -> s.hasCompletedCourses(minCourses))
                .sorted(Comparator.comparingDouble(Student::calculateGPA).reversed())
                .collect(Collectors.toList());
    }

    public double getExcellentStudentsPercentage() {
        List<Student> allStudents = studentRepo.getAll();
        if (allStudents.isEmpty()) return 0.0;

        long excellentCount = allStudents.stream()
                .filter(s -> {
                    String classification = ((Rankable) s).classify();
                    return classification.equals("Giỏi") || classification.equals("Xuất sắc");
                })
                .count();

        return (excellentCount * 100.0) / allStudents.size();
    }

    public List<Student> getStudentsWithFailedCourses() {
        return studentRepo.getAll().stream()
                .filter(Student::hasFailedCourses)
                .collect(Collectors.toList());
    }

    public Map<Student, List<Grade>> getFailedCoursesReport() {
        Map<Student, List<Grade>> report = new HashMap<>();

        for (Student s : studentRepo.getAll()) {
            List<Grade> failedCourses = s.getFailedCourses();
            if (!failedCourses.isEmpty()) {
                report.put(s, failedCourses);
            }
        }

        return report;
    }

    public void exportReportToFile(String path) {
        generator.exportReportToFile(path, studentRepo.getAll());
    }
}