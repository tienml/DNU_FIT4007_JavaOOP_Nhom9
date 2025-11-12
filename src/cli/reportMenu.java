package cli;

import java.util.Scanner;

public class reportMenu {
    private Scanner scanner = new Scanner(System.in);

    public void display() {
        while (true) {
            System.out.println("\n--- BÁO CÁO THỐNG KÊ ---");
            System.out.println("1. Báo cáo điểm trung bình");
            System.out.println("2. Báo cáo sinh viên theo lớp");
            System.out.println("3. Xuất file thống kê");
            System.out.println("0. Quay lại");
            System.out.print("Chọn: ");

            String choice = scanner.nextLine().trim();
            switch (choice) {
                case "1" -> System.out.println("→ Tạo báo cáo điểm trung bình...");
                case "2" -> System.out.println("→ Báo cáo theo lớp...");
                case "3" -> System.out.println("→ Xuất file báo cáo...");
                case "0" -> { return; }
                default -> System.out.println("Lựa chọn không hợp lệ!");
            }
        }
    }
}
