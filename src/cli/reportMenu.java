package cli;

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
            System.out.println("0. Quay lại");
            System.out.print("Chọn: ");
            String choice = scanner.nextLine().trim();

            switch (choice) {
                case "1" -> topGPA();
                case "2" -> statistics();
                case "3" -> exportReport();
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
