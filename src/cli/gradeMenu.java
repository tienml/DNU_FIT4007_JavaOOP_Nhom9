package cli;

import model.Course;
import model.Grade;
import model.Student;
import service.scoringService;
import service.studentService;
import service.courseService;

import java.util.Scanner;

public class gradeMenu {
    private final scoringService scoring;
    private final studentService studentService;
    private final courseService courseService;
    private final Scanner scanner;

    public gradeMenu(scoringService scoring, studentService studentService,
                     courseService courseService, Scanner scanner) {
        this.scoring = scoring;
        this.studentService = studentService;
        this.courseService = courseService;
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
                case "0" -> { return; }
                default -> System.out.println("Sai lựa chọn!");
            }
        }
    }

    private void enterGrade() {
        System.out.print("ID sinh viên: ");
        String sid = scanner.nextLine().trim();
        Student s = studentService.findById(sid).orElse(null);
        if (s == null) { System.out.println("→ Không tìm thấy sinh viên!"); return; }

        System.out.print("Mã môn học: ");
        String cid = scanner.nextLine().trim();
        Course c = courseService.getAll().stream()
                .filter(x -> x.getCode().equalsIgnoreCase(cid))
                .findFirst().orElse(null);
        if (c == null) { System.out.println("→ Không tìm thấy môn học!"); return; }

        Grade g = s.getGrades().stream()
                .filter(x -> x.getCourse() == c)
                .findFirst().orElse(new Grade(sid, cid));

        System.out.print("Điểm Quiz: ");
        g.setQuiz(Double.parseDouble(scanner.nextLine().trim()));
        System.out.print("Điểm Mid: ");
        g.setMid(Double.parseDouble(scanner.nextLine().trim()));
        System.out.print("Điểm Final: ");
        g.setFin(Double.parseDouble(scanner.nextLine().trim()));
        System.out.print("Điểm Project: ");
        g.setProject(Double.parseDouble(scanner.nextLine().trim()));

        scoring.calculateTotal(c, g);
        s.addGrade(g);
        System.out.println("→ Cập nhật thành công! Tổng: " + g.getTotal());
    }

    private void viewGrades() {
        System.out.print("ID sinh viên: ");
        String sid = scanner.nextLine().trim();
        Student s = studentService.findById(sid).orElse(null);
        if (s == null) { System.out.println("→ Không tìm thấy sinh viên!"); return; }

        System.out.println("--- Điểm của sinh viên " + s.getFullName() + " ---");
        for (Grade g : s.getGrades()) {
            Course c = g.getCourse();
            System.out.printf("%s - Quiz: %.2f, Mid: %.2f, Final: %.2f, Project: %.2f, Tổng: %.2f%n",
                    c.getName(), g.getQuiz(), g.getMid(), g.getFin(), g.getProject(), g.getTotal());
        }
    }
}
