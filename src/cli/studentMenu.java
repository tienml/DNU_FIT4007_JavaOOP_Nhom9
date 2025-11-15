package cli;

import model.Student;
import service.studentService;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class studentMenu {

    private final studentService service;
    private final Scanner scanner;

    public studentMenu(studentService service, Scanner scanner) {
        this.service = service;
        this.scanner = scanner;
    }

    public void display() {
        while (true) {
            System.out.println("\n--- QUẢN LÝ SINH VIÊN ---");
            System.out.println("1. Thêm sinh viên");
            System.out.println("2. Sửa thông tin sinh viên");
            System.out.println("3. Xóa sinh viên");
            System.out.println("4. Tìm sinh viên theo ID");
            System.out.println("5. Tìm sinh viên theo tên");
            System.out.println("6. Hiển thị tất cả sinh viên");
            System.out.println("0. Quay lại");
            System.out.print("Chọn: ");

            String choice = scanner.nextLine().trim();
            switch (choice) {
                case "1" -> addStudent();
                case "2" -> editStudent();
                case "3" -> deleteStudent();
                case "4" -> findById();
                case "5" -> findByName();
                case "6" -> listAll();
                case "0" -> { return; }
                default -> System.out.println("Sai lựa chọn, vui lòng nhập lại!");
            }
        }
    }

    private void addStudent() {
        System.out.print("Nhập ID: ");
        String id = scanner.nextLine().trim();
        if (service.findById(id).isPresent()) {
            System.out.println("→ ID đã tồn tại!");
            return;
        }
        System.out.print("Nhập họ tên: ");
        String name = scanner.nextLine().trim();
        System.out.print("Nhập ngành: ");
        String major = scanner.nextLine().trim();
        int year = inputYear();

        Student s = new Student(id, name, major, year);
        if (service.addStudent(s)) {
            System.out.println("→ Thêm sinh viên thành công!");
        } else {
            System.out.println("→ Thêm sinh viên thất bại!");
        }
    }

    private void editStudent() {
        System.out.print("Nhập ID sinh viên cần sửa: ");
        String id = scanner.nextLine().trim();
        Optional<Student> optional = service.findById(id);
        if (optional.isEmpty()) {
            System.out.println("→ Không tìm thấy sinh viên!");
            return;
        }
        Student s = optional.get();
        System.out.print("Tên mới (để trống nếu giữ nguyên): ");
        String name = scanner.nextLine().trim();
        if (!name.isEmpty()) s.setFullName(name);
        System.out.print("Ngành mới (để trống nếu giữ nguyên): ");
        String major = scanner.nextLine().trim();
        if (!major.isEmpty()) s.setMajor(major);
        System.out.print("Năm mới (để trống nếu giữ nguyên): ");
        String yearInput = scanner.nextLine().trim();
        if (!yearInput.isEmpty()) {
            try { s.setYear(Integer.parseInt(yearInput)); }
            catch (NumberFormatException e) { System.out.println("→ Năm không hợp lệ, giữ nguyên."); }
        }

        if (service.updateStudent(s)) {
            System.out.println("→ Cập nhật thông tin sinh viên thành công!");
        } else {
            System.out.println("→ Cập nhật thất bại!");
        }
    }

    private void deleteStudent() {
        System.out.print("Nhập ID sinh viên cần xóa: ");
        String id = scanner.nextLine().trim();
        if (service.deleteStudent(id)) {
            System.out.println("→ Xóa sinh viên thành công!");
        } else {
            System.out.println("→ Không tìm thấy sinh viên!");
        }
    }

    private void findById() {
        System.out.print("Nhập ID sinh viên: ");
        String id = scanner.nextLine().trim();
        Optional<Student> optional = service.findById(id);
        if (optional.isEmpty()) {
            System.out.println("→ Không tìm thấy sinh viên!");
            return;
        }
        printStudentInfo(optional.get());
    }

    private void findByName() {
        System.out.print("Nhập tên hoặc một phần tên: ");
        String keyword = scanner.nextLine().trim().toLowerCase();
        List<Student> result = service.getAll().stream()
                .filter(s -> s.getFullName().toLowerCase().contains(keyword))
                .toList();

        if (result.isEmpty()) {
            System.out.println("→ Không tìm thấy sinh viên nào!");
        } else {
            result.forEach(this::printStudentInfo);
        }
    }

    private void listAll() {
        List<Student> students = service.getAll();
        if (students.isEmpty()) {
            System.out.println("→ Chưa có sinh viên nào!");
            return;
        }
        students.forEach(this::printStudentInfo);
    }

    private void printStudentInfo(Student s) {
        System.out.printf("ID: %s | Họ tên: %s | Ngành: %s | Năm: %d | GPA: %.2f\n",
                s.getId(), s.getFullName(), s.getMajor(), s.getYear(), s.calculateGPA());
    }

    private int inputYear() {
        int year;
        while (true) {
            System.out.print("Nhập năm: ");
            String input = scanner.nextLine().trim();
            try {
                year = Integer.parseInt(input);
                break;
            } catch (NumberFormatException e) {
                System.out.println("→ Vui lòng nhập số hợp lệ!");
            }
        }
        return year;
    }
}
