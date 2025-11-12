package model;

public class Exam extends Assessment {

    public Exam() {
        super();
    }

    public Exam(String name, double weight, Double score) {
        super(name, weight, score);
    }

    public String getType() {
        return "Exam";
    }
}