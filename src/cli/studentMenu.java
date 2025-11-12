package cli;

import java.util.Scanner;

public class studentMenu {
    private Scanner scanner = new Scanner(System.in);

    public void display() {
        while (true) {
            System.out.println("\n--- QUẢN LÝ SINH VIÊN ---");
            System.out.println("1. Thêm sinh viên");
            System.out.println("2. Sửa sinh viên");
            System.out.println("3. Xóa sinh viên");
            System.out.println("4. Tìm kiếm sinh viên theo tên");
            System.out.println("5. Danh sách sinh viên");
            System.out.println("0. Quay lại");
            System.out.print("Chọn: ");

            String choice = scanner.nextLine().trim();
            switch (choice) {
                case "1" -> System.out.println("→ Thêm sinh viên...");
                case "2" -> System.out.println("→ Sửa sinh viên...");
                case "3" -> System.out.println("→ Xóa sinh viên...");
                case "4" -> System.out.println("→ Tìm kiếm mờ theo tên (SearchService)...");
                case "5" -> System.out.println("→ Hiển thị danh sách sinh viên...");
                case "0" -> { return; }
                default -> System.out.println("Lựa chọn không hợp lệ!");
            }
        }
    }
}
