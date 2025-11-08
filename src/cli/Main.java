package cli;

/**
 * Entry point.
 * TODO:
 *  - Khởi tạo repositories + services
 *  - Nạp dữ liệu từ data/*.csv (tạm thời có thể bỏ qua)
 *  - Gọi Menu.start()
 */
public class Main {
    public static void main(String[] args) {
        new Menu().start();
    }
}
