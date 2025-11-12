package cli;

import java.util.Scanner;

public class gradeMenu {
    private Scanner scanner = new Scanner(System.in);

    public void display() {
        while (true) {
            System.out.println("\n--- QUẢN LÝ ĐIỂM ---");
            System.out.println("1. Nhập điểm");
            System.out.println("2. Sửa điểm");
            System.out.println("3. Xóa điểm");
            System.out.println("4. Xem bảng điểm");
            System.out.println("0. Quay lại");
            System.out.print("Chọn: ");

            String choice = scanner.nextLine().trim();
            switch (choice) {
                case "1" -> System.out.println("→ Nhập điểm...");
                case "2" -> System.out.println("→ Sửa điểm...");
                case "3" -> System.out.println("→ Xóa điểm...");
                case "4" -> System.out.println("→ Hiển thị bảng điểm...");
                case "0" -> { return; }
                default -> System.out.println("Lựa chọn không hợp lệ!");
            }
        }
    }
}
