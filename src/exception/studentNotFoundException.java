package exception;

/**
 * Ném khi không tìm thấy sinh viên.
 */
public class studentNotFoundException extends RuntimeException {
    public studentNotFoundException(String id){ super("Student not found: " + id); }
}
