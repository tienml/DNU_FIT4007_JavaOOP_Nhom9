package cli;

import java.util.Scanner;

/**
 * Menu chính (stub).
 * TODO:
 *  - Điều hướng sang các menu con: CourseMenu, StudentMenu, GradeMenu, RankingMenu, ReportMenu
 */
public class Menu {
    private final Scanner sc = new Scanner(System.in);

    public void start() {
        System.out.println("=== Student Ranking Console ===");
        System.out.println("1) Quản lý học phần");
        System.out.println("2) Quản lý sinh viên");
        System.out.println("3) Nhập/Sửa điểm");
        System.out.println("4) Xếp hạng (lớp/khoa/toàn trường)");
        System.out.println("5) Báo cáo/Thống kê");
        System.out.println("0) Thoát");
        // TODO: switch-case gọi menu con
    }
}
