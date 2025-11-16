package model;

public class Exam extends Assessment {

    public Exam(String name, double weight, Double score) {
        super(name, weight, score);
    }

    @Override
    public String getType() {
        return "Exam";
    }
}