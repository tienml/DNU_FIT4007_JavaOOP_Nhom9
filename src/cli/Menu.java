package cli;

import service.*;
import repository.gradeRepository;
import java.util.Scanner;

public class Menu {
    private final studentService studentService;
    private final courseService courseService;
    private final rankingService rankingService;
    private final reportService reportService;
    private final scoringService scoringService;
    private final lecturerService lecturerService;
    private final gradeRepository gradeRepo;
    private final searchService searchService;
    private final Scanner scanner;
    public Menu(studentService studentService,
                courseService courseService,
                rankingService rankingService,
                reportService reportService,
                scoringService scoringService,
                lecturerService lecturerService,
                gradeRepository gradeRepo,
                searchService searchService,
                Scanner scanner) {
        this.studentService = studentService;
        this.courseService = courseService;
        this.rankingService = rankingService;
        this.reportService = reportService;
        this.scoringService = scoringService;
        this.lecturerService = lecturerService;
        this.gradeRepo = gradeRepo;
        this.searchService = searchService;
        this.scanner = scanner;
    }

    public void display() {
        while (true) {
            System.out.println("\n--- MENU CHÍNH ---");
            System.out.println("1. Quản lý sinh viên");
            System.out.println("2. Quản lý môn học");
            System.out.println("3. Quản lý giảng viên");
            System.out.println("4. Nhập/Sửa điểm");
            System.out.println("5. Xếp hạng");
            System.out.println("6. Báo cáo");
            System.out.println("0. Thoát");
            System.out.print("Chọn: ");

            String choice = scanner.nextLine().trim();
            switch (choice) {
                case "1" -> new studentMenu(studentService, searchService, scanner).display();
                case "2" -> new courseMenu(courseService, scanner).display();
                case "3" -> new lecturerMenu(lecturerService, scanner).display();
                case "4" -> new gradeMenu(scoringService, studentService, courseService, gradeRepo, scanner).display();
                case "5" -> new rankingMenu(rankingService, scanner).display();
                case "6" -> new reportMenu(reportService, scanner).display();
                case "0" -> { return; }
                default -> System.out.println("Sai lựa chọn, vui lòng nhập lại!");
            }
        }
    }
}
