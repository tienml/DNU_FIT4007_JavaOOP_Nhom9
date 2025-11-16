package service;

import exception.invalidScoreException;
import model.*;
import java.util.List;

public class scoringService {

    public double calculateTotal(Course c, Grade g) throws invalidScoreException {
        if (c == null || g == null)
            throw new invalidScoreException("Course hoặc Grade bị null.");

        double sumWeight = c.getwQuiz() + c.getwMid() + c.getwFinal() + c.getwProject();
        if (Math.abs(sumWeight - 1.0) > 1e-6)
            throw new invalidScoreException("Tổng trọng số phải bằng 1.0.");

        List<Assessment> assessments = List.of(
                new Quiz("Quiz", c.getwQuiz(), g.getQuiz()),
                new Exam("Mid", c.getwMid(), g.getMid()),
                new Exam("Final", c.getwFinal(), g.getFin()),
                new Project("Project", c.getwProject(), g.getProject())
        );

        double total = 0.0;
        for (Assessment a : assessments) {
            Double score = a.getScore();
            if (score == null) {
                score = 0.0;
                System.out.println("→ Cảnh báo: Điểm của " + a.getName() + " bị thiếu, gán giá trị mặc định là 0.");
            }
            total += score * a.getWeight();
        }

        g.setTotal(total);
        return total;
    }
}