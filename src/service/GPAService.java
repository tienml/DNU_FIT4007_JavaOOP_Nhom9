package service;

import model.Grade;
import java.util.List;

public class GPAService {

    public double calculateGPA(List<Grade> grades) {
        if (grades == null || grades.isEmpty()) return 0.0;

        double totalWeightedScore = 0.0;
        int totalCredits = 0;

        for (Grade g : grades) {
            if (g.getCourse() == null || g.getTotal() == null) continue;
            int credit = g.getCourse().getCredit();
            double total10 = g.getTotal();
            double total4 = convertTo4Scale(total10);

            totalWeightedScore += total4 * credit;
            totalCredits += credit;
        }

        return totalCredits == 0 ? 0.0 : totalWeightedScore / totalCredits;
    }

    private double convertTo4Scale(double total10) {
        if (total10 >= 8.5) return 4.0;
        if (total10 >= 7.8) return 3.5;
        if (total10 >= 7.0) return 3.0;
        if (total10 >= 6.3) return 2.5;
        if (total10 >= 5.5) return 2.0;
        if (total10 >= 4.8) return 1.5;
        if (total10 >= 4.0) return 1.0;
        return 0.0;
    }
}