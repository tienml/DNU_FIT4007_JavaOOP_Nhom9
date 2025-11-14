package cli;

import repository.*;
import service.*;

import java.util.Scanner;

public class Menu {
    private Scanner scanner = new Scanner(System.in);

    private studentRepository studentRepo = new studentRepository("students.csv");
    private courseRepository courseRepo = new courseRepository("courses.csv");
    private gradeRepository gradeRepo = new gradeRepository("grades.csv");

    private courseService courseService = new courseService(courseRepo);
    private studentService studentService = new studentService(studentRepo);
    private gradeService gradeService = new gradeService(gradeRepo);
    private rankingService rankingService = new rankingService(studentRepo, gradeRepo);
    private reportService reportService = new reportService(studentRepo, gradeRepo);

    private courseMenu courseMenu = new courseMenu();
    private studentMenu studentMenu = new studentMenu();
    private gradeMenu gradeMenu = new gradeMenu();
    private rankingMenu rankingMenu = new rankingMenu();
    private reportMenu reportMenu = new reportMenu();

    public void run() {
        while (true) {
            System.out.println("\n===== HỆ THỐNG QUẢN LÝ ĐIỂM SINH VIÊN =====");
            System.out.println("1. Quản lý học phần");
            System.out.println("2. Quản lý sinh viên");
            System.out.println("3. Nhập / chỉnh sửa điểm");
            System.out.println("4. Bảng xếp hạng");
            System.out.println("5. Báo cáo thống kê");
            System.out.println("0. Thoát");
            System.out.print("Chọn: ");

            String c = scanner.nextLine().trim();
            switch (c) {
                case "1" -> courseMenu.display();
                case "2" -> studentMenu.display();
                case "3" -> gradeMenu.display();
                case "4" -> rankingMenu.display();
                case "5" -> reportMenu.display();
                case "0" -> {
                    System.out.println("→ Lưu dữ liệu & thoát...");
                    studentRepo.save();
                    courseRepo.save();
                    gradeRepo.save();
                    return;
                }
                default -> System.out.println("Lựa chọn không hợp lệ!");
            }
        }
    }

    public void display() {

    }
}
