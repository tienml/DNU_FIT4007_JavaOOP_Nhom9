package cli;

import service.reportService;
import java.util.Scanner;

public class reportMenu {
    private final Scanner scanner = new Scanner(System.in);
    private final reportService service;

    public reportMenu(reportService service) {
        this.service = service;
    }

    public void display() {
        while (true) {
            System.out.println("\n--- BÁO CÁO SINH VIÊN ---");
            System.out.println("1. Sinh viên GPA cao nhất");
            System.out.println("2. Thống kê xếp loại học lực");
            System.out.println("3. Xuất báo cáo ra file");
            System.out.println("0. Quay lại");
            System.out.print("Chọn: ");

            switch (scanner.nextLine().trim()) {
                case "1" -> topGPA();
                case "2" -> statistics();
                case "3" -> export();
                case "0" -> { return; }
                default -> System.out.println("Sai lựa chọn!");
            }
        }
    }

    private void topGPA() {
        var list = service.getTopGPAStudents();
        if (list.isEmpty()) {
            System.out.println("→ Không có dữ liệu!");
            return;
        }
        System.out.println("--- Sinh viên GPA cao nhất ---");
        list.forEach(s -> System.out.printf("%s | GPA: %.2f\n", s, s.calculateGPA()));
    }

    private void statistics() {
        var stats = service.getClassificationStatistics();
        System.out.println("--- Thống kê xếp loại ---");
        stats.forEach((k,v) -> System.out.println(k + ": " + v));
    }

    private void export() {
        System.out.print("Nhập đường dẫn file: ");
        String path = scanner.nextLine().trim();
        service.exportReportToFile(path);
    }
}
