package cli;

import java.util.Scanner;

public class teacherMenu {
    private final Scanner scanner = new Scanner(System.in);

    public void display() {
        while (true) {
            System.out.println("\n--- QUẢN LÝ GIẢNG VIÊN ---");
            System.out.println("1. Thêm giảng viên");
            System.out.println("2. Sửa thông tin giảng viên");
            System.out.println("3. Xóa giảng viên");
            System.out.println("4. Tìm kiếm giảng viên");
            System.out.println("0. Quay lại");
            System.out.print("Chọn: ");

            switch (scanner.nextLine().trim()) {
                case "1" -> System.out.println("→ Thêm giảng viên...");
                case "2" -> System.out.println("→ Sửa thông tin giảng viên...");
                case "3" -> System.out.println("→ Xóa giảng viên...");
                case "4" -> System.out.println("→ Tìm kiếm giảng viên...");
                case "0" -> { return; }
                default -> System.out.println("Lựa chọn không hợp lệ!");
            }
        }
    }
}
