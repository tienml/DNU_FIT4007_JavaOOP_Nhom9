package cli;

import model.Grade;
import model.Student;
import service.reportService;

import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class reportMenu {
    private final reportService service;
    private final Scanner scanner;

    public reportMenu(reportService service, Scanner scanner) {
        this.service = service;
        this.scanner = scanner;
    }

    public void display() {
        while (true) {
            System.out.println("\n--- BÁO CÁO ---");
            System.out.println("1. Sinh viên có GPA cao nhất");
            System.out.println("2. Thống kê phân loại học lực");
            System.out.println("3. Xuất báo cáo ra file");
            System.out.println("4. Sinh viên hoàn thành ≥ 5 học phần");
            System.out.println("5. Tỷ lệ sinh viên đạt Giỏi");
            System.out.println("6. Danh sách sinh viên học lại");
            System.out.println("0. Quay lại");
            System.out.print("Chọn: ");
            String choice = scanner.nextLine().trim();

            switch (choice) {
                case "1" -> topGPA();
                case "2" -> statistics();
                case "3" -> exportReport();
                case "4" -> studentsWithMinCourses();
                case "5" -> excellentStudentsPercentage();
                case "6" -> studentsWithFailedCourses();
                case "0" -> { return; }
                default -> System.out.println("Sai lựa chọn!");
            }
        }
    }

    private void topGPA() {
        List<Student> list = service.getTopGPAStudents();
        if (list.isEmpty()) { System.out.println("→ Không có sinh viên!"); return; }
        System.out.println("--- Sinh viên có GPA cao nhất ---");
        for (Student s : list) {
            System.out.printf("%s - %s - GPA: %.2f%n", s.getId(), s.getFullName(), s.calculateGPA());
        }
    }

    private void studentsWithMinCourses() {
        System.out.print("Nhập số học phần tối thiểu (mặc định 5): ");
        String input = scanner.nextLine().trim();
        int minCourses = 5;

        if (!input.isEmpty()) {
            try {
                minCourses = Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println("→ Số không hợp lệ, dùng mặc định 5");
            }
        }

        List<Student> list = service.getStudentsWithMinCourses(minCourses);

        if (list.isEmpty()) {
            System.out.println("→ Không có sinh viên nào đạt điều kiện!");
            return;
        }

        System.out.printf("--- Sinh viên đã hoàn thành ≥ %d học phần ---\n", minCourses);
        for (Student s : list) {
            System.out.printf("%s - %s - Số HP: %d - GPA: %.2f%n",
                    s.getId(),
                    s.getFullName(),
                    s.getCompletedCoursesCount(),
                    s.calculateGPA());
        }
    }

    private void excellentStudentsPercentage() {
        double percentage = service.getExcellentStudentsPercentage();
        System.out.println("--- Thống kê tỷ lệ sinh viên đạt giỏi ---");
        System.out.printf("Tỷ lệ sinh viên đạt Giỏi trở lên: %.2f%%%n", percentage);
    }

    private void studentsWithFailedCourses() {
        Map<Student, List<Grade>> report = service.getFailedCoursesReport();

        if (report.isEmpty()) {
            System.out.println("→ Không có sinh viên nào phải học lại!");
            return;
        }

        System.out.println("--- Danh sách sinh viên phải học lại ---");
        System.out.printf("Tổng số sinh viên: %d%n%n", report.size());

        int index = 1;
        for (Map.Entry<Student, List<Grade>> entry : report.entrySet()) {
            Student s = entry.getKey();
            List<Grade> failedCourses = entry.getValue();

            System.out.printf("%d. %s - %s (GPA: %.2f)%n",
                    index++, s.getId(), s.getFullName(), s.calculateGPA());
            System.out.println("   Các học phần phải học lại:");

            for (Grade g : failedCourses) {
                String courseName = g.getCourse() != null ?
                        g.getCourse().getName() : g.getCourseId();
                System.out.printf("   - %s: %.2f%n", courseName, g.getTotal());
            }
            System.out.println();
        }
        int totalFailedCourses = report.values().stream()
                .mapToInt(List::size)
                .sum();
        System.out.printf("Tổng số học phần phải học lại: %d%n", totalFailedCourses);
    }

    private void statistics() {
        Map<String, Long> stats = service.getClassificationStatistics();
        System.out.println("--- Thống kê phân loại học lực ---");
        stats.forEach((k, v) -> System.out.printf("%s: %d%n", k, v));
    }

    private void exportReport() {
        System.out.print("Nhập đường dẫn file xuất: ");
        String path = scanner.nextLine().trim();
        service.exportReportToFile(path);
    }
}
