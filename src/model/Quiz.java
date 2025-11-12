package model;

public class Quiz extends Assessment {

    public Quiz() {
        super();
    }

    public Quiz(String name, double weight, Double score) {
        super(name, weight, score);
    }

    // Có thể thêm hàm phụ nếu cần
    public String getType() {
        return "Quiz";
    }
}
