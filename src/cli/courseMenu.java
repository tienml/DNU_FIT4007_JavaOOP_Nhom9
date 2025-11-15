package cli;

import model.Course;
import service.courseService;

import java.util.List;
import java.util.Scanner;

public class courseMenu {
    private final Scanner scanner = new Scanner(System.in);
    private final courseService service;

    public courseMenu(courseService service) {
        this.service = service;
    }

    public void display() {
        while (true) {
            System.out.println("\n--- QUẢN LÝ MÔN HỌC ---");
            System.out.println("1. Thêm môn học");
            System.out.println("2. Sửa môn học");
            System.out.println("3. Xóa môn học");
            System.out.println("4. Xem danh sách");
            System.out.println("0. Quay lại");
            System.out.print("Chọn: ");

            switch (scanner.nextLine().trim()) {
                case "1" -> add();
                case "2" -> edit();
                case "3" -> delete();
                case "4" -> listAll();
                case "0" -> { return; }
                default -> System.out.println("Sai lựa chọn!");
            }
        }
    }

    private void add() {
        System.out.print("Mã môn học: ");
        String code = scanner.nextLine().trim();
        System.out.print("Tên môn học: ");
        String name = scanner.nextLine().trim();
        System.out.print("Số tín chỉ: ");
        int credit = Integer.parseInt(scanner.nextLine().trim());
        System.out.print("Trọng số Quiz: ");
        double wq = Double.parseDouble(scanner.nextLine().trim());
        System.out.print("Trọng số Mid: ");
        double wm = Double.parseDouble(scanner.nextLine().trim());
        System.out.print("Trọng số Final: ");
        double wf = Double.parseDouble(scanner.nextLine().trim());
        System.out.print("Trọng số Project: ");
        double wp = Double.parseDouble(scanner.nextLine().trim());

        Course c = new Course(code, name, credit, wq, wm, wf, wp);
        if (service.addCourse(c)) System.out.println("→ Thêm thành công!");
        else System.out.println("→ Không thể thêm môn học!");
    }

    private void edit() {
        System.out.print("Mã môn học cần sửa: ");
        String code = scanner.nextLine().trim();

        Course existing = service.getAll().stream()
                .filter(c -> c.getCode().equalsIgnoreCase(code))
                .findFirst().orElse(null);

        if (existing == null) {
            System.out.println("→ Không tìm thấy môn học!");
            return;
        }

        System.out.print("Tên mới: ");
        existing.setName(scanner.nextLine().trim());
        System.out.print("Số tín chỉ mới: ");
        existing.setCredit(Integer.parseInt(scanner.nextLine().trim()));
        System.out.print("Trọng số Quiz: ");
        double wq = Double.parseDouble(scanner.nextLine().trim());
        System.out.print("Trọng số Mid: ");
        double wm = Double.parseDouble(scanner.nextLine().trim());
        System.out.print("Trọng số Final: ");
        double wf = Double.parseDouble(scanner.nextLine().trim());
        System.out.print("Trọng số Project: ");
        double wp = Double.parseDouble(scanner.nextLine().trim());
        existing.setWeights(wq, wm, wf, wp);

        if (service.updateCourse(code, existing)) System.out.println("→ Sửa thành công!");
        else System.out.println("→ Không thể sửa môn học!");
    }

    private void delete() {
        System.out.print("Mã môn học cần xóa: ");
        String code = scanner.nextLine().trim();

        if (service.deleteCourse(code)) System.out.println("→ Xóa thành công!");
        else System.out.println("→ Không tìm thấy môn học!");
    }

    private void listAll() {
        List<Course> list = service.getAll();
        if (list.isEmpty()) {
            System.out.println("→ Không có môn học!");
            return;
        }
        int i = 1;
        for (Course c : list) {
            System.out.println(i++ + ". " + c);
        }
    }
}
