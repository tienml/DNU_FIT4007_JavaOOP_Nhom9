package cli;

import model.Student;
import service.rankingService;

import java.util.List;
import java.util.Scanner;

public class rankingMenu {

    private final Scanner scanner = new Scanner(System.in);
    private final rankingService service;

    public rankingMenu(rankingService service) {
        this.service = service;
    }

    public void display() {
        while (true) {
            System.out.println("\n--- BẢNG XẾP HẠNG SINH VIÊN ---");
            System.out.println("1. Theo ngành và năm");
            System.out.println("2. Theo ngành");
            System.out.println("3. Toàn trường");
            System.out.println("0. Quay lại");
            System.out.print("Chọn: ");

            String choice = scanner.nextLine().trim();
            switch (choice) {
                case "1" -> byMajorAndYear();
                case "2" -> byMajor();
                case "3" -> bySchool();
                case "0" -> { return; }
                default -> System.out.println("Sai lựa chọn!");
            }
        }
    }

    private void byMajorAndYear() {
        System.out.print("Nhập ngành: ");
        String major = scanner.nextLine().trim();

        int year;
        try {
            System.out.print("Nhập năm: ");
            year = Integer.parseInt(scanner.nextLine().trim());
        } catch (NumberFormatException e) {
            System.out.println("→ Năm không hợp lệ!");
            return;
        }

        List<Student> list = service.rankingByMajorAndYear(major, year);
        print(list);
    }

    private void byMajor() {
        System.out.print("Nhập ngành: ");
        String major = scanner.nextLine().trim();

        List<Student> list = service.rankingByMajor(major);
        print(list);
    }

    private void bySchool() {
        List<Student> list = service.rankingSchool();
        print(list);
    }

    private void print(List<Student> list) {
        if (list == null || list.isEmpty()) {
            System.out.println("→ Không có dữ liệu!");
            return;
        }
        int i = 1;
        System.out.println("\n--- KẾT QUẢ BẢNG XẾP HẠNG ---");
        for (Student s : list) {
            System.out.printf("%d. %s | GPA: %.2f\n", i++, s.toString(), s.calculateGPA());
        }
    }
}
