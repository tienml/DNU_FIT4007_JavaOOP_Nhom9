package cli;

import repository.courseRepository;
import repository.gradeRepository;
import repository.lecturerRepository;
import repository.studentRepository;
import service.*;

import java.io.File;
import java.util.Scanner;

public class Menu {

    private final Scanner scanner = new Scanner(System.in);

    private final studentRepository studentRepo;
    private final courseRepository courseRepo;
    private final gradeRepository gradeRepo;
    private final lecturerRepository lecturerRepo;

    private final studentService studentService;
    private final courseService courseService;
    private final lecturerService lecturerService;
    private final rankingService rankingService;
    private final reportService reportService;
    private final GPAService gpaService;
    private final scoringService scoringService;

    private final studentMenu studentMenu;
    private final courseMenu courseMenu;
    private final gradeMenu gradeMenu;
    private final lecturerMenu lecturerMenu;
    private final rankingMenu rankingMenu;
    private final reportMenu reportMenu;

    public Menu() {
        File studentFile = new File("students.csv");
        File courseFile = new File("courses.csv");
        File gradeFile = new File("grades.csv");
        File lecturerFile = new File("lecturers.csv");

        // Khởi tạo repository
        studentRepo = new studentRepository(studentFile);
        courseRepo = new courseRepository(courseFile);
        gradeRepo = new gradeRepository(gradeFile);
        lecturerRepo = new lecturerRepository(lecturerFile);

        // Khởi tạo service
        studentService = new studentService(studentRepo);
        courseService = new courseService(courseRepo);
        lecturerService = new lecturerService(lecturerRepo);
        rankingService = new rankingService(studentRepo, gradeRepo);
        reportService = new reportService(studentRepo);
        gpaService = new GPAService();
        scoringService = new scoringService();

        // Khởi tạo menu con
        studentMenu = new studentMenu(studentService);
        courseMenu = new courseMenu(courseService);
        gradeMenu = new gradeMenu(scoringService, gradeRepo, courseRepo);
        lecturerMenu = new lecturerMenu(lecturerService);
        rankingMenu = new rankingMenu(rankingService);
        reportMenu = new reportMenu(reportService);
    }

    public void run() {
        while (true) {
            System.out.println("\n--- QUẢN LÝ HỌC TẬP ---");
            System.out.println("1. Quản lý sinh viên");
            System.out.println("2. Quản lý học phần");
            System.out.println("3. Nhập/sửa điểm");
            System.out.println("4. Quản lý giảng viên");
            System.out.println("5. Bảng xếp hạng");
            System.out.println("6. Báo cáo");
            System.out.println("0. Thoát");
            System.out.print("Chọn: ");

            String choice = scanner.nextLine().trim();
            switch (choice) {
                case "1" -> studentMenu.display();
                case "2" -> courseMenu.display();
                case "3" -> gradeMenu.display();
                case "4" -> lecturerMenu.display();
                case "5" -> rankingMenu.display();
                case "6" -> reportMenu.display();
                case "0" -> {
                    System.out.println("Thoát chương trình.");
                    return;
                }
                default -> System.out.println("Sai lựa chọn!");
            }
        }
    }
}
