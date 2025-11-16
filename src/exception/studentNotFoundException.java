package exception;

public class studentNotFoundException extends Exception {
    public studentNotFoundException(String studentId) {
        super("Không tìm thấy sinh viên với ID: " + studentId);
    }
}