package cli;

import model.Student;
import service.searchService;
import service.studentService;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class studentMenu {

    private final studentService service;
    private final searchService searchService;
    private final Scanner scanner;

    public studentMenu(studentService service, searchService searchService, Scanner scanner) {
        this.service = service;
        this.searchService = searchService;
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
        Student s = new Student();

        System.out.print("Nhập họ tên: ");
        String name = scanner.nextLine().trim();
        s.setFullName(name);
        System.out.print("Nhập giới tính (Male/Female): ");
        String gender = scanner.nextLine().trim();
        s.setGender(gender);
        System.out.print("Nhập ngành / lớp (VD: CG01): ");
        String majorOrGroup = scanner.nextLine().trim();
        s.setMajor(majorOrGroup);
        s.setClassGroupId(majorOrGroup);

        int year = inputYear();
        s.setYear(year);
        s.setBirthDate(year + "-01-01");

        if (service.addStudent(s)) {
            System.out.println("→ Thêm sinh viên thành công! ID được gán: " + s.getId());
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

        System.out.print("Giới tính mới (để trống nếu giữ nguyên): ");
        String gender = scanner.nextLine().trim();
        if (!gender.isEmpty()) s.setGender(gender);

        System.out.print("Ngành / lớp mới (để trống nếu giữ nguyên): ");
        String major = scanner.nextLine().trim();
        if (!major.isEmpty()) {
            s.setMajor(major);
            s.setClassGroupId(major);
        }

        System.out.print("Năm sinh mới (để trống nếu giữ nguyên): ");
        String yearInput = scanner.nextLine().trim();
        if (!yearInput.isEmpty()) {
            try {
                int y = Integer.parseInt(yearInput);
                s.setYear(y);
                s.setBirthDate(y + "-01-01");
            } catch (NumberFormatException e) {
                System.out.println("→ Năm không hợp lệ, giữ nguyên.");
            }
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
        Student s = searchService.searchById(id);
        if (s == null) {
            System.out.println("→ Không tìm thấy sinh viên!");
        } else {
            printStudentInfo(s);
        }
    }

    private void findByName() {
        System.out.print("Nhập tên hoặc một phần tên: ");
        String keyword = scanner.nextLine().trim();
        List<Student> result = searchService.searchByName(keyword);

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
        System.out.printf(
                "ID: %s | Họ tên: %s | Ngành/Lớp: %s | Năm sinh: %d | Giới tính: %s | GPA: %.2f%n",
                s.getId(),
                s.getFullName(),
                s.getMajor(),
                s.getYear(),
                (s.getGender() != null ? s.getGender() : "N/A"),
                s.calculateGPA()
        );
    }

    private int inputYear() {
        int year;
        while (true) {
            System.out.print("Nhập năm sinh (VD: 2003): ");
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