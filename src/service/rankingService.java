package service;

import model.Student;
import model.Grade;
import repository.studentRepository;

import java.util.Comparator;
import java.util.List;

public class rankingService {

    private studentRepository studentRepo;

    public rankingService(studentRepository repo) {
        this.studentRepo = repo;
    }
    public void rankByGPA() {
        List<Student> students = studentRepo.getAll();
        students.sort(Comparator.comparingDouble(Student::getGPA).reversed());

        System.out.println("\n--- BẢNG XẾP HẠNG THEO GPA ---");
        System.out.printf("%-5s %-20s %-5s%n", "STT", "Họ Tên", "GPA");
        int rank = 1;
        for (Student s : students) {
            System.out.printf("%-5d %-20s %-5.2f%n", rank++, s.getFullName(), s.getGPA());
        }
    }
    public void rankByCourse(String courseName) {
        List<Student> students = studentRepo.getAll();
        students.sort((s1, s2) -> Double.compare(
                getGradeByCourse(s2, courseName),
                getGradeByCourse(s1, courseName)
        ));

        System.out.println("\n--- BẢNG XẾP HẠNG HỌC PHẦN: " + courseName + " ---");
        System.out.printf("%-5s %-20s %-5s%n", "STT", "Họ Tên", "Điểm");
        int rank = 1;
        for (Student s : students) {
            double grade = getGradeByCourse(s, courseName);
            System.out.printf("%-5d %-20s %-5.2f%n", rank++, s.getFullName(), grade);
        }
    }

    private double getGradeByCourse(Student s, String courseName) {
        for (Grade g : s.getGrades()) {
            if (g.getCourse().equals(courseName)) {
                return g.getMid();
            }
        }
        return 0.0;
    }
}
