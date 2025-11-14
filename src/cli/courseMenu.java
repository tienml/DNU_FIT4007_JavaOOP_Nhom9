package cli;

import model.Course;
import service.courseService;

import java.util.List;
import java.util.Scanner;

public class courseMenu {
    private final Scanner scanner = new Scanner(System.in);
    private courseService service;

    public courseMenu() {
        this.service = service;
    }

    public void display() {
        while (true) {
            System.out.println("\n--- QUẢN LÝ HỌC PHẦN ---");
            System.out.println("1. Thêm học phần");
            System.out.println("2. Sửa học phần");
            System.out.println("3. Xóa học phần");
            System.out.println("4. Tìm kiếm học phần");
            System.out.println("0. Quay lại");
            System.out.print("Chọn: ");

            switch (scanner.nextLine().trim()) {
                case "1" -> add();
                case "2" -> edit();
                case "3" -> delete();
                case "4" -> search();
                case "0" -> { return; }
                default -> System.out.println("Lựa chọn không hợp lệ!");
            }
        }
    }

    private void add() {
        System.out.print("Mã học phần: ");
        String id = scanner.nextLine();

        System.out.print("Tên học phần: ");
        String name = scanner.nextLine();

        System.out.print("Số tín chỉ: ");
        int credit = Integer.parseInt(scanner.nextLine());

        Course c = new Course(id, name);
        service.addCourse(c);

        System.out.println("→ Thêm thành công!");
    }

    private void edit() {
        System.out.print("Nhập mã học phần cần sửa: ");
        String id = scanner.nextLine();

        System.out.print("Tên mới: ");
        String name = scanner.nextLine();

        System.out.print("Số tín chỉ mới: ");
        int credit = Integer.parseInt(scanner.nextLine());

        Course updated = new Course(id, name);
        if (service.updateCourse(id, updated))
            System.out.println("→ Sửa thành công!");
        else
            System.out.println("→ Không tìm thấy học phần!");
    }

    private void delete() {
        System.out.print("Nhập mã học phần cần xóa: ");
        String id = scanner.nextLine();

        if (service.deleteCourse(id))
            System.out.println("→ Xóa thành công!");
        else
            System.out.println("→ Không tìm thấy học phần!");
    }

    private void search() {
        System.out.print("Nhập mã/tên để tìm: ");
        String keyword = scanner.nextLine();

        List<Course> results = service.search(keyword);
        if (results.isEmpty()) {
            System.out.println("→ Không tìm thấy!");
            return;
        }

        System.out.println("\n--- KẾT QUẢ ---");
        results.forEach(c ->
                System.out.printf("%s - %s (%d tín)\n", c.getCode(), c.getName(), c.getCredit()));
    }
}
