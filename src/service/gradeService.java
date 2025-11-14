package service;

import model.Grade;
import repository.gradeRepository;

import java.util.List;

public class gradeService {
    private final gradeRepository repo;

    public gradeService(gradeRepository repo) {
        this.repo = repo;
    }
    public boolean UpdateGrade() {
        return repo.update(g);
    }
    public double calculateFinalScore(Grade g) {
        double quiz = g.getQuiz() * g.getFin();
        double mid = g.getMid() * g.getFin();
        double fin = g.getFin() * g.getFin();
        double project = g.getProject() * g.getFin();
        return quiz + mid + fin + project;
    }

    public List<Grade> getGradesByStudent(String studentId) {
        return repo.getAll().stream()
                .filter(g -> g.getStudent().equals(studentId))
                .toList();
    }
    public double calculateGPA(String studentId) {
        List<Grade> list = getGradesByStudent(studentId);
        if (list.isEmpty()) return 0.0;

        double sum = 0;
        for (Grade g : list) {
            sum += calculateFinalScore(g);
        }
        return sum / list.size();
    }
}
