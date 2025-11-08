package exception;

/**
 * Ném khi không tìm thấy học phần.
 */
public class courseNotFoundException extends RuntimeException {
    public courseNotFoundException(String id){ super("Course not found: " + id); }
}
