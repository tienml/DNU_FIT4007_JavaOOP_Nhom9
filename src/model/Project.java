package model;

public class Project extends Assessment {

    public Project(String name, double weight, Double score) {
        super(name, weight, score);
    }

    @Override
    public String getType() {
        return "Project";
    }
}