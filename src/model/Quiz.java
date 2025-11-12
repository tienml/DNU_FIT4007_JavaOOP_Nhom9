package model;

public class Quiz extends Assessment {

    public Quiz() {
        super();
    }

    public Quiz(String name, double weight, Double score) {
        super(name, weight, score);
    }

    public String getType() {
        return "Quiz";
    }
}