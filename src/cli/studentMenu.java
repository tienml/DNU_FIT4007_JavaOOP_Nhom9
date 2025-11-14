package cli;

import model.Student;
import service.studentService;

import java.util.Scanner;

public class studentMenu {

    private Scanner scanner;
    private studentService service;
    public studentMenu() {
        this.service = service;
        this.scanner = scanner;
    }

    public void display() {
        while (true) {
            System.out.println("\n--- QUẢN LÝ SINH VIÊN ---");
            System.out.println("1. Thêm sinh viên");
            System.out.println("2. Sửa thông tin");
            System.out.println("3. Xóa sinh viên");
            System.out.println("4. Tìm kiếm theo mã");
            System.out.println("0. Quay lại");
            System.out.print("Chọn: ");

            switch (scanner.nextLine()) {
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
        String id = scanner.nextLine();
        System.out.print("Họ tên: ");
        String name = scanner.nextLine();
        System.out.print("Lớp: ");
        String className = scanner.nextLine();
        System.out.print("Khoa: ");
        String major = scanner.nextLine();

        Student s = new Student(id, name, className, major);
        if (service.addStudent(s))
            System.out.println("→ Thêm thành công!");
        else
            System.out.println("→ ID đã tồn tại!");
    }

    private void edit() {
        System.out.print("ID sinh viên cần sửa: ");
        String id = scanner.nextLine();

        Student s = service.findById(id).orElse(null);
        if (s == null) {
            System.out.println("→ Không tìm thấy!");
            return;
        }

        System.out.print("Tên mới: ");
        s.setName(scanner.nextLine().trim());

        System.out.print("Lớp mới: ");
        s.setClassName(scanner.nextLine().trim());

        System.out.print("Khoa mới: ");
        s.setMajor(scanner.nextLine().trim());

        service.updateStudent(s);
        System.out.println("→ Sửa thành công!");
    }

    private void delete() {
        System.out.print("Nhập ID để xóa: ");
        String id = scanner.nextLine();

        if (service.deleteStudent(id))
            System.out.println("→ Xóa thành công!");
        else
            System.out.println("→ Không tìm thấy!");
    }

    private void find() {
        System.out.print("Nhập ID: ");
        String id = scanner.nextLine();

        Student s = service.findById(id).orElse(null);
        if (s == null) {
            System.out.println("→ Không tìm thấy!");
            return;
        }

        System.out.println("ID: " + s.getId());
        System.out.println("Tên: " + s.getName());
        System.out.println("Lớp: " + s.getClass());
        System.out.println("Khoa: " + s.getMajor());
    }
}
