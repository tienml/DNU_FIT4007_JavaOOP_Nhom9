package model;

public class Project extends Assessment {

    public Project() {
        super();
    }

    public Project(String name, double weight, Double score) {
        super(name, weight, score);
    }

    public String getType() {
        return "Project";
    }
}
