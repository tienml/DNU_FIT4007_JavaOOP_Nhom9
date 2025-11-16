package cli;

import model.Student;
import service.rankingService;

import java.util.List;
import java.util.Scanner;

public class rankingMenu {
    private final rankingService service;
    private final Scanner scanner;

    public rankingMenu(rankingService service, Scanner scanner) {
        this.service = service;
        this.scanner = scanner;
    }

    public void display() {
        while (true) {
            System.out.println("\n--- BẢNG XẾP HẠNG ---");
            System.out.println("1. Xếp hạng theo lớp");
            System.out.println("2. Xếp hạng theo khoa");
            System.out.println("3. Xếp hạng toàn trường");
            System.out.println("0. Quay lại");
            System.out.print("Chọn: ");
            String choice = scanner.nextLine().trim();

            switch (choice) {
                case "1" -> rankByMajorYear();
                case "2" -> rankByMajor();
                case "3" -> rankSchool();
                case "0" -> { return; }
                default -> System.out.println("Sai lựa chọn!");
            }
        }
    }

    private void rankByMajorYear() {
        System.out.print("Nhập mã lớp (VD: CG01): ");
        String classId = scanner.nextLine().trim();
        List<Student> list = service.rankingByClass(classId);
        printRanking(list);
    }

    private void rankByMajor() {
        System.out.print("Nhập tên khoa (VD: Cong nghe thong tin): ");
        String faculty = scanner.nextLine().trim();
        List<Student> list = service.rankingByFaculty(faculty);
        printRanking(list);
    }

    private void rankSchool() {
        List<Student> list = service.rankingSchool();
        printRanking(list);
    }

    private void printRanking(List<Student> list) {
        if (list.isEmpty()) { System.out.println("→ Không có sinh viên nào!"); return; }
        System.out.println("--- BẢNG XẾP HẠNG ---");
        int rank = 1;
        for (Student s : list) {
            System.out.printf("%d. %s - GPA: %.2f%n", rank++, s.getFullName(), s.calculateGPA());
        }
    }
}
