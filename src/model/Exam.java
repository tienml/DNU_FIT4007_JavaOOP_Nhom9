package model;

/**
 * Exam (mid/final).
 * TODO:
 *  - Thuộc tính isFinal
 *  - weight = wMid nếu !isFinal, ngược lại wFinal
 */
public class Exam extends Assessment {
    private boolean isFinal;
    @Override public double weight(Course c){ return 0; /* TODO */ }
}
