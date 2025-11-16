package model;

public class Course {
    private String code;
    private String name;
    private int credit;
    private double wQuiz;
    private double wMid;
    private double wFinal;
    private double wProject;

    public Course() {}

    public Course(String code, String name, int credit,
                  double wQuiz, double wMid, double wFinal, double wProject) {
        this.code = code;
        this.name = name;
        this.credit = credit;
        setWeights(wQuiz, wMid, wFinal, wProject);
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCredit() {
        return credit;
    }

    public void setCredit(int credit) {
        this.credit = credit;
    }

    public double getwQuiz() {
        return wQuiz;
    }

    public double getwMid() {
        return wMid;
    }

    public double getwFinal() {
        return wFinal;
    }

    public double getwProject() {
        return wProject;
    }

    public void setWeights(double wQuiz, double wMid, double wFinal, double wProject) {
        double sum = wQuiz + wMid + wFinal + wProject;
        if (sum != 0 && Math.abs(sum - 1.0) > 1e-6) {
            throw new IllegalArgumentException("The total weight must equal 1.0, or 0 if not used.");
        }
        this.wQuiz = wQuiz;
        this.wMid = wMid;
        this.wFinal = wFinal;
        this.wProject = wProject;
    }

    @Override
    public String toString() {
        return String.format("%s - %s (%d TC) [wQ=%.2f, wM=%.2f, wF=%.2f, wP=%.2f]",
                code, name, credit, wQuiz, wMid, wFinal, wProject);
    }
}