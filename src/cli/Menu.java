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
        System.out.println("=== HỆ THỐNG QUẢN LÝ ĐIỂM SINH VIÊN ===");
    }

    private void printMenuOptions() {
        System.out.println("1. Quản lý học phần");
        System.out.println("2. Quản lý sinh viên");
        System.out.println("3. Quản lý điểm");
        System.out.println("4. Bảng xếp hạng");
        System.out.println("5. Báo cáo thống kê");
        System.out.println("0. Thoát");
        System.out.print("Chọn chức năng: ");
    }

    private int getUserChoice() {
        return Integer.parseInt(scanner.nextLine().trim());
    }

    private void handleMenuChoice(int choice) {
        switch (choice) {
            case 1 -> courseMenu.display();
            case 2 -> studentMenu.display();
            case 3 -> gradeMenu.display();
            case 4 -> rankingMenu.display();
            case 5 -> reportMenu.display();
            case 0 -> exitProgram();
            default -> {
                showError("Lựa chọn không hợp lệ!");
                pause();
            }
        }
    }

    private void exitProgram() {
        clearScreen();
        System.out.println("Cảm ơn bạn đã sử dụng hệ thống!");
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

    private void showError(String msg) {
        System.out.println("\n" + msg);
    }
}
