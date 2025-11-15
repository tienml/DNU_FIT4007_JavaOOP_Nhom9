package cli;

import model.Lecturer;
import service.lecturerService;

import java.util.List;
import java.util.Scanner;

public class lecturerMenu {
    private final lecturerService service;
    private final Scanner scanner;

    public lecturerMenu(lecturerService service, Scanner scanner) {
        this.service = service;
        this.scanner = scanner;
    }

    public void display() {
        while (true) {
            System.out.println("\n--- QUẢN LÝ GIẢNG VIÊN ---");
            System.out.println("1. Thêm giảng viên");
            System.out.println("2. Sửa giảng viên");
            System.out.println("3. Xóa giảng viên");
            System.out.println("4. Xem danh sách giảng viên");
            System.out.println("0. Quay lại");
            System.out.print("Chọn: ");
            String choice = scanner.nextLine().trim();

            switch (choice) {
                case "1" -> addLecturer();
                case "2" -> editLecturer();
                case "3" -> deleteLecturer();
                case "4" -> listLecturers();
                case "0" -> { return; }
                default -> System.out.println("Sai lựa chọn!");
            }
        }
    }

    private void addLecturer() {
        System.out.print("ID: ");
        String id = scanner.nextLine().trim();
        System.out.print("Họ tên: ");
        String name = scanner.nextLine().trim();
        System.out.print("Bộ môn: ");
        String dept = scanner.nextLine().trim();
        System.out.print("Email: ");
        String email = scanner.nextLine().trim();

        Lecturer l = new Lecturer(id, name, dept, email);
        if (service.addLecturer(l)) System.out.println("→ Thêm thành công!");
        else System.out.println("→ ID đã tồn tại!");
    }

    private void editLecturer() {
        System.out.print("ID cần sửa: ");
        String id = scanner.nextLine().trim();
        Lecturer l = service.findById(id).orElse(null);
        if (l == null) { System.out.println("→ Không tìm thấy!"); return; }

        System.out.print("Họ tên mới: ");
        l.setFullName(scanner.nextLine().trim());
        System.out.print("Bộ môn mới: ");
        l.setDepartment(scanner.nextLine().trim());
        System.out.print("Email mới: ");
        l.setEmail(scanner.nextLine().trim());
        service.updateLecturer(l);
        System.out.println("→ Sửa thành công!");
    }

    private void deleteLecturer() {
        System.out.print("ID cần xóa: ");
        String id = scanner.nextLine().trim();
        if (service.deleteLecturer(id)) System.out.println("→ Xóa thành công!");
        else System.out.println("→ Không tìm thấy!");
    }

    private void listLecturers() {
        List<Lecturer> list = service.getAll();
        if (list.isEmpty()) System.out.println("→ Chưa có giảng viên nào!");
        else {
            System.out.println("--- Danh sách giảng viên ---");
            for (Lecturer l : list) {
                System.out.printf("%s - %s - %s - %s%n",
                        l.getId(), l.getFullName(), l.getDepartment(), l.getEmail());
            }
        }
    }
}
