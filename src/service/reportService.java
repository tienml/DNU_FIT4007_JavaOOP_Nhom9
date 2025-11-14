package service;

import model.Student;
import repository.studentRepository;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class reportService {

    private studentRepository studentRepo;

    public reportService(studentRepository repo) {
        this.studentRepo = repo;
    }
    public void reportAverageScore() {
        List<Student> students = studentRepo.getAll();
        System.out.println("\n--- BÁO CÁO ĐIỂM TRUNG BÌNH ---");
        System.out.printf("%-5s %-20s %-10s %-5s%n", "STT", "Họ Tên", "Lớp", "Điểm TB");

        int index = 1;
        for (Student s : students) {
            System.out.printf("%-5d %-20s %-10s %-5.2f%n",
                    index++, s.getFullName(), s.getClass(), s.getGPA());
        }
    }
    public void reportByClass(String className) {
        List<Student> students = studentRepo.getAll();
        System.out.println("\n--- BÁO CÁO SINH VIÊN LỚP: " + className + " ---");
        System.out.printf("%-5s %-20s %-10s %-5s%n", "STT", "Họ Tên", "Lớp", "Điểm TB");

        int index = 1;
        for (Student s : students) {
            if (s.getClass().equals(className)) {
                System.out.printf("%-5d %-20s %-10s %-5.2f%n",
                        index++, s.getFullName(), s.getClass(), s.getGPA());
            }
        }
    }
    public void exportReport(String fileName) {
        List<Student> students = studentRepo.getAll();

        try (FileWriter writer = new FileWriter(fileName)) {
            writer.write("STT,Họ Tên,Lớp,Điểm TB\n");
            int index = 1;
            for (Student s : students) {
                writer.write(String.format("%d,%s,%s,%.2f\n",
                        index++, s.getFullName(), s.getClass(), s.getGPA()));
            }
            System.out.println("→ Xuất báo cáo thành công: " + fileName);
        } catch (IOException e) {
            System.out.println("→ Lỗi khi xuất file: " + e.getMessage());
        }
    }
}
