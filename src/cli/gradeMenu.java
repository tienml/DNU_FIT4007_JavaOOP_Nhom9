package cli;

import exception.courseNotFoundException;
import exception.invalidScoreException;
import exception.studentNotFoundException;
import model.Course;
import model.Grade;
import model.Student;
import repository.gradeRepository;
import service.courseService;
import service.scoringService;
import service.studentService;

import java.util.Scanner;

public class gradeMenu {
    private final scoringService scoring;
    private final studentService studentService;
    private final courseService courseService;
    private final gradeRepository gradeRepo;
    private final Scanner scanner;

    public gradeMenu(scoringService scoring,
                     studentService studentService,
                     courseService courseService,
                     gradeRepository gradeRepo,
                     Scanner scanner) {
        this.scoring = scoring;
        this.studentService = studentService;
        this.courseService = courseService;
        this.gradeRepo = gradeRepo;
        this.scanner = scanner;
    }

    public void display() {
        while (true) {
            System.out.println("\n--- QUẢN LÝ ĐIỂM ---");
            System.out.println("1. Nhập/Chỉnh sửa điểm");
            System.out.println("2. Xem điểm sinh viên");
            System.out.println("0. Quay lại");
            System.out.print("Chọn: ");
            String choice = scanner.nextLine().trim();

            switch (choice) {
                case "1" -> enterGrade();
                case "2" -> viewGrades();
                case "0" -> {
                    return;
                }
                default -> System.out.println("Sai lựa chọn!");
            }
        }
    }

    private void enterGrade() {
        try {
            System.out.print("ID sinh viên: ");
            String sid = scanner.nextLine().trim();
            Student s = studentService.findOrThrow(sid);

            System.out.print("Mã môn học: ");
            String cid = scanner.nextLine().trim();
            Course c = courseService.findOrThrow(cid);

            Grade g = gradeRepo.findByStudentAndCourse(sid, cid)
                    .orElseGet(() -> {
                        Grade ng = new Grade(s, c);
                        s.addGrade(ng);
                        gradeRepo.add(ng);
                        return ng;
                    });

            System.out.print("Điểm Quiz: ");
            g.setQuiz(Double.parseDouble(scanner.nextLine().trim()));

            System.out.print("Điểm Mid: ");
            g.setMid(Double.parseDouble(scanner.nextLine().trim()));

            System.out.print("Điểm Final: ");
            g.setFin(Double.parseDouble(scanner.nextLine().trim()));

            System.out.print("Điểm Project: ");
            g.setProject(Double.parseDouble(scanner.nextLine().trim()));

            scoring.calculateTotal(c, g);
            gradeRepo.update(g);

            System.out.println("→ Cập nhật thành công! Tổng: " + String.format("%.2f", g.getTotal()));

        } catch (studentNotFoundException | courseNotFoundException e) {
            System.out.println("→ " + e.getMessage());
        } catch (invalidScoreException e) {
            System.out.println("→ Lỗi điểm: " + e.getMessage());
        } catch (NumberFormatException e) {
            System.out.println("→ Vui lòng nhập điểm hợp lệ (số từ 0 đến 10).");
        }
    }

    private void viewGrades() {
        System.out.print("ID sinh viên: ");
        String sid = scanner.nextLine().trim();
        Student s = studentService.findById(sid).orElse(null);

        if (s == null) {
            System.out.println("→ Không tìm thấy sinh viên!");
            return;
        }

        if (s.getGrades().isEmpty()) {
            System.out.println("→ Sinh viên chưa có điểm học phần nào!");
            return;
        }

        System.out.println("--- Điểm của sinh viên " + s.getFullName() + " ---");
        for (Grade g : s.getGrades()) {
            Course c = g.getCourse();
            String courseName = (c != null ? c.getName() : g.getCourseId());
            System.out.printf(
                    "%s - Quiz: %.2f, Mid: %.2f, Final: %.2f, Project: %.2f, Tổng: %.2f%n",
                    courseName,
                    g.getQuiz() != null ? g.getQuiz() : 0.0,
                    g.getMid() != null ? g.getMid() : 0.0,
                    g.getFin() != null ? g.getFin() : 0.0,
                    g.getProject() != null ? g.getProject() : 0.0,
                    g.getTotal() != null ? g.getTotal() : 0.0
            );
        }
    }
}