package service;

import exception.invalidScoreException;
import model.Course;
import model.Grade;

public class scoringService {

    public double calculateTotal(Course c, Grade g) throws invalidScoreException {
        if (c == null || g == null)
            throw new invalidScoreException("Course hoặc Grade bị null.");

        double wq = c.getwQuiz();
        double wm = c.getwMid();
        double wf = c.getwFinal();
        double wp = c.getwProject();

        double sumWeight = wq + wm + wf + wp;
        if (Math.abs(sumWeight - 1.0) > 1e-6)
            throw new invalidScoreException("Tổng trọng số phải bằng 1.0.");

        double q = safe(g.getQuiz());
        double m = safe(g.getMid());
        double f = safe(g.getFin());
        double p = safe(g.getProject());

        double total = q * wq + m * wm + f * wf + p * wp;
        g.setTotal(total);

        return total;
    }

    private double safe(Double d) {
        return d == null ? 0.0 : d;
    }
}
