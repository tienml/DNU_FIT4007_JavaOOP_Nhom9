package cli;

import java.util.Scanner;

public class Menu {
    private Scanner scanner;
    private courseMenu courseMenu;
    private studentMenu studentMenu;
    private gradeMenu gradeMenu;
    private rankingMenu rankingMenu;
    private reportMenu reportMenu;

    public Menu() {
        scanner = new Scanner(System.in);
        courseMenu = new courseMenu();
        studentMenu = new studentMenu();
        gradeMenu = new gradeMenu();
        rankingMenu = new rankingMenu();
        reportMenu = new reportMenu();
    }
    public void displayMainMenu() {
        while (true) {
            clearScreen();
            printHeader();
            printMenuOptions();

            try {
                int choice = getUserChoice();
                handleMenuChoice(choice);

            } catch (NumberFormatException e) {
                showError("Vui lòng nhập số!");
                pause();
            } catch (Exception e) {
                showError("Có lỗi xảy ra: " + e.getMessage());
                pause();
            }
        }
    }
    private void printHeader() {
        System.out.println("HỆ THỐNG QUẢN LÝ ĐIỂM SINH VIÊN");
    }
    private void printMenuOptions() {
        System.out.println(" 1. Quản lý học phần ");
        System.out.println(" 2. Quản lý sinh viên");
        System.out.println(" 3. Quản lý điểm     ");
        System.out.println(" 4. Bảng xếp hạng    ");
        System.out.println(" 5. Báo cáo thống kê ");
        System.out.println(" 0. Thoát            ");
        System.out.print("Chọn chức năng: ");
    }
    private int getUserChoice() {
        String input = scanner.nextLine().trim();
        return Integer.parseInt(input);
    }
    private void handleMenuChoice(int choice) {
        switch (choice) {
            case 1:
                navigateToCourseMenu();
                break;
            case 2:
                navigateToStudentMenu();
                break;
            case 3:
                navigateToGradeMenu();
                break;
            case 4:
                navigateToRankingMenu();
                break;
            case 5:
                navigateToReportMenu();
                break;
            case 0:
                exitProgram();
                break;
            default:
                showError("Lựa chọn không hợp lệ! Vui lòng chọn từ 0-5");
                pause();
        }
    }
    private void navigateToCourseMenu() {
        courseMenu.display();
    }

    private void navigateToStudentMenu() {
        studentMenu.display();
    }

    private void navigateToGradeMenu() {
        gradeMenu.display();
    }

    private void navigateToRankingMenu() {
        rankingMenu.display();
    }

    private void navigateToReportMenu() {
        reportMenu.display();
    }
    private void exitProgram() {
        clearScreen();
        System.out.println(" Cảm ơn bạn đã sử dụng hệ thống!");
        scanner.close();
        System.exit(0);
    }
    private void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();

    }
    private void pause() {
        System.out.println("\nNhấn Enter để tiếp tục...");
        scanner.nextLine();
    }
    private void showError(String message) {
        System.out.println("\n " + message);
    }
    private void showSuccess(String message) {
        System.out.println("\n " + message);
    }
}