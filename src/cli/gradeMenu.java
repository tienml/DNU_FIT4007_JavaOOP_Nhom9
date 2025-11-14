package cli;

import service.gradeService;

import java.util.Scanner;

public class gradeMenu {
    private final Scanner scanner = new Scanner(System.in);
    private gradeService service;

    public gradeMenu() {
        this.service = service;
    }

    public void display() {
        while (true) {
            System.out.println("\n--- NHẬP ĐIỂM HỌC PHẦN ---");
            System.out.println("1. Nhập điểm");
            System.out.println("2. Sửa điểm");
            System.out.println("3. Xem điểm");
            System.out.println("0. Quay lại");
            System.out.print("Chọn: ");

            switch (scanner.nextLine()) {
                case "1" -> input();
                case "2" -> edit();
                case "3" -> view();
                case "0" -> { return; }
                default -> System.out.println("Sai lựa chọn!");
            }
        }
    }

    private void input() {
        System.out.print("ID SV: ");
        String sid = scanner.nextLine();

        System.out.print("Mã học phần: ");
        String cid = scanner.nextLine();

        System.out.print("Quiz: ");
        double quiz = Double.parseDouble(scanner.nextLine());

        System.out.print("Giữa kỳ: ");
        double mid = Double.parseDouble(scanner.nextLine());

        System.out.print("Cuối kỳ: ");
        double fin = Double.parseDouble(scanner.nextLine());

        System.out.print("Bài tập lớn: ");
        double prj = Double.parseDouble(scanner.nextLine());

        service.UpdateGrade();
    }

    private void edit() {
        input();
    }

    private void view() {
        System.out.print("Nhập ID SV: ");
        service.UpdateGrade();
    }
}
