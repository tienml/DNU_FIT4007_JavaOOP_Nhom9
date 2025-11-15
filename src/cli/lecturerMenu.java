package cli;

import model.Lecturer;
import service.lecturerService;

import java.util.List;
import java.util.Scanner;

public class lecturerMenu {

    private final Scanner scanner;
    private final lecturerService service;

    public lecturerMenu(lecturerService service) {
        this.service = service;
        this.scanner = new Scanner(System.in);
    }

    public void display() {
        while (true) {
            System.out.println("\n--- QUẢN LÝ GIẢNG VIÊN ---");
            System.out.println("1. Thêm giảng viên");
            System.out.println("2. Sửa thông tin giảng viên");
            System.out.println("3. Xóa giảng viên");
            System.out.println("4. Tìm kiếm giảng viên theo ID");
            System.out.println("5. Danh sách tất cả giảng viên");
            System.out.println("0. Quay lại");
            System.out.print("Chọn: ");

            String choice = scanner.nextLine().trim();
            switch (choice) {
                case "1" -> add();
                case "2" -> edit();
                case "3" -> delete();
                case "4" -> find();
                case "5" -> viewAll();
                case "0" -> { return; }
                default -> System.out.println("Sai lựa chọn!");
            }
        }
    }

    private void add() {
        System.out.print("ID: ");
        String id = scanner.nextLine().trim();
        System.out.print("Họ tên: ");
        String name = scanner.nextLine().trim();
        System.out.print("Khoa/Phòng ban: ");
        String dept = scanner.nextLine().trim();
        System.out.print("Email: ");
        String email = scanner.nextLine().trim();

        Lecturer l = new Lecturer(id, name, dept, email);
        if (service.addLecturer(l)) {
            System.out.println("→ Thêm giảng viên thành công!");
        } else {
            System.out.println("→ ID đã tồn tại hoặc lỗi!");
        }
    }

    private void edit() {
        System.out.print("Nhập ID giảng viên cần sửa: ");
        String id = scanner.nextLine().trim();

        Lecturer l = service.findById(id).orElse(null);
        if (l == null) {
            System.out.println("→ Không tìm thấy!");
            return;
        }

        System.out.print("Họ tên mới: ");
        l.setFullName(scanner.nextLine().trim());
        System.out.print("Khoa/Phòng ban mới: ");
        l.setDepartment(scanner.nextLine().trim());
        System.out.print("Email mới: ");
        l.setEmail(scanner.nextLine().trim());

        service.updateLecturer(l);
        System.out.println("→ Cập nhật thành công!");
    }

    private void delete() {
        System.out.print("Nhập ID giảng viên cần xóa: ");
        String id = scanner.nextLine().trim();
        if (service.deleteLecturer(id)) {
            System.out.println("→ Xóa thành công!");
        } else {
            System.out.println("→ Không tìm thấy giảng viên!");
        }
    }

    private void find() {
        System.out.print("Nhập ID giảng viên: ");
        String id = scanner.nextLine().trim();
        Lecturer l = service.findById(id).orElse(null);
        if (l == null) {
            System.out.println("→ Không tìm thấy!");
            return;
        }
        System.out.println("ID: " + l.getId());
        System.out.println("Họ tên: " + l.getFullName());
        System.out.println("Khoa/Phòng ban: " + l.getDepartment());
        System.out.println("Email: " + l.getEmail());
    }

    private void viewAll() {
        List<Lecturer> list = service.getAll();
        if (list.isEmpty()) {
            System.out.println("→ Không có giảng viên nào!");
            return;
        }
        System.out.println("\n--- DANH SÁCH GIẢNG VIÊN ---");
        int i = 1;
        for (Lecturer l : list) {
            System.out.printf("%d. %s - %s - %s\n", i++, l.getId(), l.getFullName(), l.getDepartment());
        }
    }
}
