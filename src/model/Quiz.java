package model;

public class Quiz extends Assessment {

    public Quiz(String name, double weight, Double score) {
        super(name, weight, score);
    }

    @Override
    public String getType() {
        return "Quiz";
    }
}