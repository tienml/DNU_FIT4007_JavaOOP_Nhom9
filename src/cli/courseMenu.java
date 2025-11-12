package cli;

import java.util.Scanner;

public class courseMenu {
    private Scanner scanner = new Scanner(System.in);

    public void display() {
        while (true) {
            System.out.println("\n--- QUẢN LÝ HỌC PHẦN ---");
            System.out.println("1. Thêm học phần");
            System.out.println("2. Sửa học phần");
            System.out.println("3. Xóa học phần");
            System.out.println("4. Xem danh sách học phần");
            System.out.println("0. Quay lại");
            System.out.print("Chọn: ");

            String choice = scanner.nextLine().trim();
            switch (choice) {
                case "1" -> System.out.println("→ Thêm học phần...");
                case "2" -> System.out.println("→ Sửa học phần...");
                case "3" -> System.out.println("→ Xóa học phần...");
                case "4" -> System.out.println("→ Hiển thị danh sách học phần...");
                case "0" -> { return; }
                default -> System.out.println("Lựa chọn không hợp lệ!");
            }
        }
    }
}
