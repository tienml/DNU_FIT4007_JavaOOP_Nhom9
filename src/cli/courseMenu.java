package cli;

import java.util.Scanner;

public class courseMenu {

    private Scanner scanner;

    public courseMenu() {
        scanner = new Scanner(System.in);
    }

    public void display() {
        while (true) {
            clearScreen();
            printMenu();

            try {
                int choice = Integer.parseInt(scanner.nextLine().trim());

                if (!handleChoice(choice)) {
                    break;
                }

            } catch (NumberFormatException e) {
                System.out.println(" Vui lòng nhập số!");
                pause();
            }
        }
    }

    private void printMenu() {
        System.out.println("QUẢN LÝ HỌC PHẦN ");
        System.out.println("  1. Thêm học phần");
        System.out.println("  2. Xem danh sách");
        System.out.println("  3. Sửa học phần");
        System.out.println("  4. Xóa học phần");
        System.out.println("  5. Tìm kiếm ");
        System.out.println("  0. Quay lại");
        System.out.print("Chọn: ");
    }

    private boolean handleChoice(int choice) {
        switch (choice) {
            case 1: createCourse(); break;
            case 2: readCourses(); break;
            case 3: updateCourse(); break;
            case 4: deleteCourse(); break;
            case 5: searchCourse(); break;
            case 0: return false;
            default:
                System.out.println(" Lựa chọn không hợp lệ!");
                pause();
        }
        return true;
    }

    private void createCourse() {
        clearScreen();
        System.out.println("=== THÊM HỌC PHẦN MỚI ===\n");

        try {
            System.out.print("Mã học phần: ");
            String id = scanner.nextLine().trim().toUpperCase();

            if (id.isEmpty()) {
                System.out.println(" Mã không được rỗng!");
                pause();
                return;
            }

            System.out.print("Tên học phần: ");
            String name = scanner.nextLine().trim();

            if (name.isEmpty()) {
                System.out.println(" Tên không được rỗng!");
                pause();
                return;
            }

            System.out.print("Số tín chỉ (1-5): ");
            int credits = Integer.parseInt(scanner.nextLine().trim());

            if (credits < 1 || credits > 5) {
                System.out.println(" Tín chỉ phải từ 1-5!");
                pause();
                return;
            }

            System.out.print("Mã giảng viên (Enter bỏ qua): ");
            String lecturer = scanner.nextLine().trim();

            System.out.println("\n--- Xác nhận ---");
            System.out.println("Mã: " + id);
            System.out.println("Tên: " + name);
            System.out.println("Tín chỉ: " + credits);
            System.out.println("GV: " + (lecturer.isEmpty() ? "(Chưa có)" : lecturer));

            System.out.print("\nXác nhận? (Y/N): ");
            if (scanner.nextLine().trim().equalsIgnoreCase("Y")) {
                System.out.println(" Đã thêm thành công!");
            } else {
                System.out.println(" Đã hủy!");
            }

        } catch (NumberFormatException e) {
            System.out.println(" Số tín chỉ không hợp lệ!");
        }

        pause();
    }

    private void readCourses() {
        clearScreen();
        System.out.println("=== DANH SÁCH HỌC PHẦN ===\n");

        System.out.println("Mã HP    | Tên                    | TC | GV");
        System.out.println("---------+------------------------+----+-------");
        System.out.println("IT6001   | Lập trình Java         | 3  | GV001");
        System.out.println("IT6002   | Cơ sở dữ liệu          | 3  | GV002");
        System.out.println("IT6003   | Cấu trúc dữ liệu       | 3  | GV001");
        System.out.println("IT6004   | Mạng máy tính          | 3  | GV003");
        System.out.println("IT6005   | Hệ điều hành           | 3  | GV002");

        System.out.println("\nTổng: 5 học phần");

        pause();
    }

    private void updateCourse() {
        clearScreen();
        System.out.println("=== SỬA HỌC PHẦN ===\n");

        System.out.print("Mã học phần cần sửa: ");
        String id = scanner.nextLine().trim().toUpperCase();

        if (id.isEmpty()) {
            System.out.println(" Mã không được rỗng!");
            pause();
            return;
        }

        System.out.println("\nThông tin hiện tại:");
        System.out.println("Mã: IT6001");
        System.out.println("Tên: Lập trình Java");
        System.out.println("Tín chỉ: 3");
        System.out.println("GV: GV001");

        System.out.println("\nChọn thông tin cần sửa:");
        System.out.println("1. Tên");
        System.out.println("2. Tín chỉ");
        System.out.println("3. Giảng viên");
        System.out.println("0. Hủy");
        System.out.print("Chọn: ");

        try {
            int choice = Integer.parseInt(scanner.nextLine().trim());

            switch (choice) {
                case 1:
                    System.out.print("\nTên mới: ");
                    String name = scanner.nextLine().trim();
                    if (!name.isEmpty()) {
                        System.out.println(" Đã cập nhật!");
                    }
                    break;
                case 2:
                    System.out.print("\nTín chỉ mới (1-5): ");
                    int credits = Integer.parseInt(scanner.nextLine().trim());
                    if (credits >= 1 && credits <= 5) {
                        System.out.println(" Đã cập nhật!");
                    } else {
                        System.out.println(" Tín chỉ không hợp lệ!");
                    }
                    break;
                case 3:
                    System.out.print("\nMã GV mới: ");
                    String lec = scanner.nextLine().trim();
                    System.out.println(" Đã cập nhật!");
                    break;
                case 0:
                    System.out.println(" Đã hủy!");
                    break;
                default:
                    System.out.println(" Lựa chọn không hợp lệ!");
            }
        } catch (NumberFormatException e) {
            System.out.println(" Dữ liệu không hợp lệ!");
        }

        pause();
    }

    private void deleteCourse() {
        clearScreen();
        System.out.println("=== XÓA HỌC PHẦN ===\n");

        System.out.print("Mã học phần cần xóa: ");
        String id = scanner.nextLine().trim().toUpperCase();

        if (id.isEmpty()) {
            System.out.println(" Mã không được rỗng!");
            pause();
            return;
        }

        System.out.println("\n  Sẽ xóa:");
        System.out.println("Mã: " + id);
        System.out.println("Tên: Lập trình Java");
        System.out.println("Tín chỉ: 3");

        System.out.println("\n  Cảnh báo: Không thể hoàn tác!");
        System.out.print("Gõ 'XOA' để xác nhận: ");

        if (scanner.nextLine().trim().equals("XOA")) {
            System.out.println(" Đã xóa!");
        } else {
            System.out.println(" Đã hủy!");
        }

        pause();
    }

    private void searchCourse() {
        clearScreen();
        System.out.println("=== TÌM KIẾM HỌC PHẦN ===\n");

        System.out.println("1. Tìm theo mã");
        System.out.println("2. Tìm theo tên");
        System.out.println("0. Quay lại");
        System.out.print("Chọn: ");

        try {
            int choice = Integer.parseInt(scanner.nextLine().trim());

            switch (choice) {
                case 1:
                    searchById();
                    break;
                case 2:
                    searchByName();
                    break;
                case 0:
                    return;
                default:
                    System.out.println(" Lựa chọn không hợp lệ!");
                    pause();
            }
        } catch (NumberFormatException e) {
            System.out.println(" Vui lòng nhập số!");
            pause();
        }
    }

    private void searchById() {
        System.out.print("\nMã học phần: ");
        String id = scanner.nextLine().trim().toUpperCase();

        if (id.isEmpty()) {
            System.out.println(" Mã không được rỗng!");
            pause();
            return;
        }

        System.out.println("\n=== Kết quả ===");
        System.out.println("✅ Tìm thấy!");
        System.out.println("Mã: IT6001");
        System.out.println("Tên: Lập trình Java");
        System.out.println("Tín chỉ: 3");
        System.out.println("GV: GV001");

        pause();
    }

    private void searchByName() {
        System.out.print("\nTên học phần (hoặc từ khóa): ");
        String keyword = scanner.nextLine().trim();

        if (keyword.isEmpty()) {
            System.out.println("❌ Từ khóa không được rỗng!");
            pause();
            return;
        }

        System.out.println("\n=== Kết quả: \"" + keyword + "\" ===");
        System.out.println("Tìm thấy 2 kết quả:\n");

        System.out.println("Mã HP    | Tên                    | TC");
        System.out.println("---------+------------------------+----");
        System.out.println("IT6001   | Lập trình Java         | 3");
        System.out.println("IT6010   | Lập trình nâng cao     | 3");

        pause();
    }

    private void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    private void pause() {
        System.out.print("\nEnter để tiếp tục...");
        scanner.nextLine();
    }
}