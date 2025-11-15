package cli;

import model.Student;
import service.studentService;

import java.util.Scanner;

public class studentMenu {
    private final Scanner scanner;
    private final studentService service;

    public studentMenu(studentService service) {
        this.service = service;
        this.scanner = new Scanner(System.in);
    }

    public void display() {
        while (true) {
            System.out.println("\n--- QUẢN LÝ SINH VIÊN ---");
            System.out.println("1. Thêm sinh viên");
            System.out.println("2. Sửa thông tin");
            System.out.println("3. Xóa sinh viên");
            System.out.println("4. Tìm kiếm theo ID");
            System.out.println("0. Quay lại");
            System.out.print("Chọn: ");

            switch (scanner.nextLine().trim()) {
                case "1" -> add();
                case "2" -> edit();
                case "3" -> delete();
                case "4" -> find();
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
        System.out.print("Ngành: ");
        String major = scanner.nextLine().trim();
        System.out.print("Năm: ");
        int year = Integer.parseInt(scanner.nextLine().trim());

        Student s = new Student(id, name, major, year);
        if (service.addStudent(s))
            System.out.println("→ Thêm thành công!");
        else
            System.out.println("→ ID đã tồn tại!");
    }

    private void edit() {
        System.out.print("ID sinh viên cần sửa: ");
        String id = scanner.nextLine().trim();

        var s = service.findById(id).orElse(null);
        if (s == null) {
            System.out.println("→ Không tìm thấy!");
            return;
        }

        System.out.print("Tên mới: ");
        s.setFullName(scanner.nextLine().trim());
        System.out.print("Ngành mới: ");
        s.setMajor(scanner.nextLine().trim());
        System.out.print("Năm mới: ");
        s.setYear(Integer.parseInt(scanner.nextLine().trim()));

        service.updateStudent(s);
        System.out.println("→ Sửa thành công!");
    }

    private void delete() {
        System.out.print("Nhập ID để xóa: ");
        String id = scanner.nextLine().trim();

        if (service.deleteStudent(id))
            System.out.println("→ Xóa thành công!");
        else
            System.out.println("→ Không tìm thấy!");
    }

    private void find() {
        System.out.print("Nhập ID: ");
        String id = scanner.nextLine().trim();

        var s = service.findById(id).orElse(null);
        if (s == null) {
            System.out.println("→ Không tìm thấy!");
            return;
        }

        System.out.println("ID: " + s.getId());
        System.out.println("Tên: " + s.getFullName());
        System.out.println("Ngành: " + s.getMajor());
        System.out.println("Năm: " + s.getYear());
        System.out.printf("GPA: %.2f\n", s.calculateGPA());
    }
}
