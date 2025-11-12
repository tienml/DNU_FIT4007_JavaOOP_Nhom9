package service;

import exception.invalidScoreException;
import model.Course;
import model.Grade;

public class scoringService {

    public double calcTotal(Course c, Grade g) throws invalidScoreException {
        double wQuiz = c.getwQuiz();
        double wMid = c.getwMid();
        double wFinal = c.getwFinal();
        double wProject = c.getwProject();

        double sum = wQuiz + wMid + wFinal + wProject;
        if (sum > 1.0 + 1e-6) {
            throw new invalidScoreException("Tổng trọng số của học phần " + c.getCode() + " vượt quá 1.0");
        }

        Double quiz = g.getQuiz();
        Double mid = g.getMid();
        Double fin = g.getFin();
        Double proj = g.getProject();

        if (!isValid(quiz) || !isValid(mid) || !isValid(fin) || !isValid(proj)) {
            throw new invalidScoreException("Điểm thành phần phải nằm trong khoảng 0..10 hoặc null");
        }

        if (quiz == null || mid == null || fin == null) {
            g.setTotal(null);
            return 0;
        }

        double total = quiz * wQuiz + mid * wMid + fin * wFinal;
        if (proj != null) total += proj * wProject;

        g.setTotal(total);
        return total;
    }

    private boolean isValid(Double s) {
        if (s == null) return true;
        return s >= 0 && s <= 10;
    }
}