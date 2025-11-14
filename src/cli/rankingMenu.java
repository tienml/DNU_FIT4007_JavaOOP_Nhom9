package cli;

import service.rankingService;

import java.util.Scanner;

public class rankingMenu {

    private final Scanner scanner = new Scanner(System.in);
    private rankingService service;

    public rankingMenu() {
        this.service = service;
    }

    public void display() {
        while (true) {
            System.out.println("\n--- BẢNG XẾP HẠNG ---");
            System.out.println("1. Theo lớp");
            System.out.println("2. Theo khoa");
            System.out.println("3. Toàn trường");
            System.out.println("0. Quay lại");
            System.out.print("Chọn: ");

            switch (scanner.nextLine()) {
                case "1" -> byClass();
                case "2" -> byFaculty();
                case "3" -> bySchool();
                case "0" -> { return; }
                default -> System.out.println("Sai lựa chọn!");
            }
        }
    }

    private void byClass() {
        System.out.print("Nhập mã lớp: ");
        var list = service.rankingByClass(scanner.nextLine());
        print(list);
    }

    private void byFaculty() {
        System.out.print("Nhập tên khoa: ");
        var list = service.rankingByFaculty(scanner.nextLine());
        print(list);
    }

    private void bySchool() {
        print(service.rankingSchool());
    }

    private void print(java.util.List<?> list) {
        int i = 1;
        for (var s : list) {
            System.out.println((i++) + ". " + s.toString());
        }
    }
}
