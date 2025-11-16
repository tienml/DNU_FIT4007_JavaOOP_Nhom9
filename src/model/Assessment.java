package model;

public abstract class Assessment {
    private String name;
    private double weight;
    private Double score;
    public Assessment(String name, double weight) {
        this.name = name;
        this.weight = weight;
    }

    public Assessment(String name, double weight, Double score) {
        this.name = name;
        this.weight = weight;
        this.score = score;
    }

    public String getName() { return name; }
    public void setName(String name) {
        this.name = name;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public Double getScore() {
        return score;
    }

    public void setScore(Double score) {
        this.score = score;
    }

    public abstract String getType();

    @Override
    public String toString() {
        return getType() + ": " + name + " (weight: " + weight + ", score: "
                + (score != null ? score : "not entered") + ")";
    }
}