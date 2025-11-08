package exception;

/**
 * Ném khi điểm <0 hoặc >10, hoặc trọng số sai.
 */
public class invalidScoreException extends RuntimeException {
    public invalidScoreException(String msg){ super(msg); }
}
