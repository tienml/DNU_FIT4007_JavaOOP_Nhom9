package exception;

public class courseNotFoundException extends Exception {
    public courseNotFoundException(String courseId) {
        super("Không tìm thấy học phần với mã: " + courseId);
    }
}