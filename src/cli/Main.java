package cli;

import java.io.File;

public class Main {
    public static void main(String[] args) {
        try {
            // 1. Hiển thị banner chào mừng
            printWelcomeBanner();

            // 2. Khởi tạo repositories (tầng dữ liệu)
            System.out.println(" Đang khởi tạo repositories...");
            initializeRepositories();
            System.out.println(" Repositories đã sẵn sàng!");
            System.out.println("\n⚙  Đang khởi tạo services...");
            initializeServices();
            System.out.println(" Services đã sẵn sàng!");
            System.out.println("\n Đang nạp dữ liệu...");
            loadDataFromCSV();
            System.out.println(" Dữ liệu đã được nạp!");
            System.out.println("\n Khởi động hệ thống...\n");
            pause();
            startApplication();

        } catch (Exception e) {
            System.err.println("\n LỖI KHỞI ĐỘNG HỆ THỐNG:");
            System.err.println(e.getMessage());
            e.printStackTrace();
            System.exit(1);
        }
    }
    private static void initializeRepositories() {
        System.out.println("   - StudentRepository");
        System.out.println("   - CourseRepository");
        System.out.println("   - GradeRepository");
        System.out.println("   - LecturerRepository");
    }
    private static void initializeServices() {
        System.out.println("   - StudentService");
        System.out.println("   - CourseService");
        System.out.println("   - GradeService");
        System.out.println("   - RankingService");
        System.out.println("   - ReportService");
    }
    private static void loadDataFromCSV() {
        String dataDir = "data/";

        try {
            File directory = new File(dataDir);
            if (!directory.exists()) {
                System.out.println(" Thư mục data/ chưa tồn tại. Bỏ qua nạp dữ liệu.");
                return;
            }
            System.out.println("   - students.csv");
            System.out.println("   - courses.csv");
            System.out.println("   - grades.csv");
            System.out.println("   - lecturers.csv");

        } catch (Exception e) {
            System.out.println(" Không thể nạp dữ liệu từ CSV: " + e.getMessage());
            System.out.println("   Hệ thống sẽ chạy với dữ liệu trống.");
        }
    }
    private static void startApplication() {
        // Tạo menu và bắt đầu
        Menu menu = new Menu();
        menu.displayMainMenu();
    }
    private static void printWelcomeBanner() {
        clearScreen();
        System.out.println("           HỆ THỐNG QUẢN LÝ ĐIỂM SINH VIÊN              ");
    }
    private static void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
    private static void pause() {
        System.out.print("Nhấn Enter để tiếp tục...");
        try {
            System.in.read();
            // Đọc hết buffer
            while (System.in.available() > 0) {
                System.in.read();
            }
        } catch (Exception e) {
            // Ignore
        }
    }
}