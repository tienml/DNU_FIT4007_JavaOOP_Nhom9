package cli;

import service.reportService;

import java.util.Scanner;

public class reportMenu {
    private final Scanner scanner = new Scanner(System.in);
    private reportService service;

    public reportMenu() {
        this.service = service;
    }

    public void display() {
        while (true) {
            System.out.println("\n--- BÁO CÁO THỐNG KÊ ---");
            System.out.println("1. GPA cao nhất");
            System.out.println("2. Tỷ lệ xếp loại");
            System.out.println("3. Xuất báo cáo ra file");
            System.out.println("0. Quay lại");
            System.out.print("Chọn: ");

            switch (scanner.nextLine()) {
                case "1" -> topGPA();
                case "2" -> ratio();
                case "3" -> export();
                case "0" -> { return; }
                default -> System.out.println("Sai lựa chọn!");
            }
        }
    }

    private void topGPA() {
        var list = service.getTopGPAStudents();
        list.forEach(System.out::println);
    }

    private void ratio() {
        var stats = service.getClassificationStatistics();
        stats.forEach((k, v) -> System.out.println(k + ": " + v));
    }

    private void export() {
        System.out.print("Nhập đường dẫn file: ");
        service.exportReportToFile(scanner.nextLine());
        System.out.println("→ Xuất thành công!");
    }
}
