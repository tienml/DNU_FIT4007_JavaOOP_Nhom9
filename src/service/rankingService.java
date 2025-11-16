package service;

import model.Group;
import model.Student;
import repository.groupRepository;
import repository.studentRepository;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class rankingService {
    private final studentRepository studentRepo;
    private final groupRepository groupRepo;

    public rankingService(studentRepository studentRepo, groupRepository groupRepo) {
        this.studentRepo = studentRepo;
        this.groupRepo = groupRepo;
    }

    public List<Student> rankingByClass(String classId) {
        return studentRepo.getAll().stream()
                .filter(s -> s.getMajor() != null &&
                        s.getMajor().equalsIgnoreCase(classId)) // major đang là classGroupId
                .sorted(Comparator.comparingDouble(Student::calculateGPA).reversed())
                .collect(Collectors.toList());
    }

    public List<Student> rankingByFaculty(String facultyName) {
        return studentRepo.getAll().stream()
                .filter(s -> {
                    String classId = s.getMajor(); // CG01...
                    if (classId == null) return false;
                    Group g = groupRepo.findById(classId).orElse(null);
                    if (g == null) return false;
                    return g.getFaculty() != null &&
                            g.getFaculty().equalsIgnoreCase(facultyName);
                })
                .sorted(Comparator.comparingDouble(Student::calculateGPA).reversed())
                .collect(Collectors.toList());
    }

    public List<Student> rankingSchool() {
        return studentRepo.getAll().stream()
                .sorted(Comparator.comparingDouble(Student::calculateGPA).reversed())
                .collect(Collectors.toList());
    }
}