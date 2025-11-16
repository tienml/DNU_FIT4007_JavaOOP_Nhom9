package cli;

import model.Course;
import service.courseService;

import java.util.List;
import java.util.Scanner;

public class courseMenu {
    private final courseService service;
    private final Scanner scanner;

    public courseMenu(courseService service, Scanner scanner) {
        this.service = service;
        this.scanner = scanner;
    }

    public void display() {
        while (true) {
            System.out.println("\n--- QUẢN LÝ MÔN HỌC ---");
            System.out.println("1. Thêm môn học");
            System.out.println("2. Sửa môn học");
            System.out.println("3. Xóa môn học");
            System.out.println("4. Xem danh sách môn học");
            System.out.println("0. Quay lại");
            System.out.print("Chọn: ");
            String choice = scanner.nextLine().trim();

            switch (choice) {
                case "1" -> addCourse();
                case "2" -> editCourse();
                case "3" -> deleteCourse();
                case "4" -> listCourses();
                case "0" -> { return; }
                default -> System.out.println("Sai lựa chọn!");
            }
        }
    }

    private void addCourse() {
        System.out.print("Mã môn học: ");
        String code = scanner.nextLine().trim();

        if (service.getAll().stream().anyMatch(course -> course.getCode().equalsIgnoreCase(code))) {
            System.out.println("→ Mã môn học đã tồn tại!");
            return;
        }

        System.out.print("Tên môn: ");
        String name = scanner.nextLine().trim();
        System.out.print("Số tín chỉ: ");
        int credit = 0;
        try {
            credit = Integer.parseInt(scanner.nextLine().trim());
        } catch (NumberFormatException e) {
            System.out.println("→ Vui lòng nhập số hợp lệ cho tín chỉ.");
            return;
        }

        System.out.print("Trọng số Quiz: ");
        double wq = getValidWeightInput();
        System.out.print("Trọng số Mid: ");
        double wm = getValidWeightInput();
        System.out.print("Trọng số Final: ");
        double wf = getValidWeightInput();
        System.out.print("Trọng số Project: ");
        double wp = getValidWeightInput();

        Course c = new Course(code, name, credit, wq, wm, wf, wp);
        if (service.addCourse(c)) {
            System.out.println("→ Thêm thành công!");
        } else {
            System.out.println("→ Mã môn học đã tồn tại!");
        }
    }

    private double getValidWeightInput() {
        double weight = 0.0;
        try {
            weight = Double.parseDouble(scanner.nextLine().trim());
            if (weight < 0 || weight > 1) {
                System.out.println("→ Trọng số phải trong khoảng từ 0 đến 1. Nhập lại!");
                return getValidWeightInput();
            }
        } catch (NumberFormatException e) {
            System.out.println("→ Vui lòng nhập số hợp lệ cho trọng số.");
        }
        return weight;
    }

    private void editCourse() {
        System.out.print("Mã môn cần sửa: ");
        String code = scanner.nextLine().trim();
        Course c = service.getAll().stream()
                .filter(x -> x.getCode().equalsIgnoreCase(code))
                .findFirst().orElse(null);
        if (c == null) {
            System.out.println("→ Không tìm thấy!");
            return;
        }

        System.out.print("Tên mới: ");
        c.setName(scanner.nextLine().trim());
        System.out.print("Số tín chỉ: ");
        int credit = 0;
        try {
            credit = Integer.parseInt(scanner.nextLine().trim());
        } catch (NumberFormatException e) {
            System.out.println("→ Vui lòng nhập số hợp lệ cho tín chỉ.");
            return;
        }
        c.setCredit(credit);

        System.out.print("Trọng số Quiz: ");
        double wq = getValidWeightInput();
        System.out.print("Trọng số Mid: ");
        double wm = getValidWeightInput();
        System.out.print("Trọng số Final: ");
        double wf = getValidWeightInput();
        System.out.print("Trọng số Project: ");
        double wp = getValidWeightInput();
        c.setWeights(wq, wm, wf, wp);

        service.updateCourse(code, c);
        System.out.println("→ Sửa thành công!");
    }

    private void deleteCourse() {
        System.out.print("Mã môn cần xóa: ");
        String code = scanner.nextLine().trim();
        if (service.deleteCourse(code)) {
            System.out.println("→ Xóa thành công!");
        } else {
            System.out.println("→ Không tìm thấy!");
        }
    }

    private void listCourses() {
        List<Course> list = service.getAll();
        if (list.isEmpty()) System.out.println("→ Chưa có môn học nào!");
        else {
            System.out.println("--- Danh sách môn học ---");
            for (Course c : list) {
                System.out.printf("%s - %s - %d TC%n", c.getCode(), c.getName(), c.getCredit());
            }
        }
    }
}