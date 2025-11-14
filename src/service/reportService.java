package service;

import model.Student;
import model.Grade;
import repository.studentRepository;
import repository.gradeRepository;

import java.util.*;
import java.util.stream.Collectors;

public class reportService {
    private final studentRepository studentRepo;
    private final gradeService gradeService;

    public reportService(studentRepository studentRepo, gradeRepository gradeRepo) {
        this.studentRepo = studentRepo;
        this.gradeService = new gradeService(gradeRepo);
    }

    // Danh sách sinh viên có GPA cao nhất
    public List<Student> getTopGPAStudents() {
        double maxGPA = studentRepo.getAll().stream()
                .mapToDouble(s -> gradeService.calculateGPA(s.getId()))
                .max()
                .orElse(0);

        return studentRepo.getAll().stream()
                .filter(s -> gradeService.calculateGPA(s.getId()) == maxGPA)
                .toList();
    }

    // Tỷ lệ các mức xếp loại
    public Map<String, Long> classifyStudents() {
        return studentRepo.getAll().stream()
                .collect(Collectors.groupingBy(
                        s -> classify(gradeService.calculateGPA(s.getId())),
                        Collectors.counting()
                ));
    }

    private String classify(double gpa) {
        if (gpa >= 8.5) return "Xuất sắc";
        if (gpa >= 7.0) return "Giỏi";
        if (gpa >= 5.5) return "Khá";
        if (gpa >= 4.0) return "Trung bình";
        return "Yếu";
    }
}
