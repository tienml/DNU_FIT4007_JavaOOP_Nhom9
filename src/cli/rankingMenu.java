package cli;

import java.util.Scanner;

public class rankingMenu {
    private Scanner scanner = new Scanner(System.in);

    public void display() {
        while (true) {
            System.out.println("\n--- BẢNG XẾP HẠNG ---");
            System.out.println("1. Xếp hạng theo GPA");
            System.out.println("2. Xếp hạng theo học phần");
            System.out.println("0. Quay lại");
            System.out.print("Chọn: ");

            String choice = scanner.nextLine().trim();
            switch (choice) {
                case "1" -> System.out.println("→ Xếp hạng theo GPA...");
                case "2" -> System.out.println("→ Xếp hạng theo học phần...");
                case "0" -> { return; }
                default -> System.out.println("Lựa chọn không hợp lệ!");
            }
        }
    }
}
