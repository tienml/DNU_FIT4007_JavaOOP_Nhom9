package iface;

public interface Rankable {
    double calculateGPA();
    default String classify() {
        double gpa = calculateGPA();
        if (gpa >= 8.5) return "Xuất sắc";
        if (gpa >= 7.0) return "Giỏi";
        if (gpa >= 6.0) return "Khá";
        if (gpa >= 5.0) return "Trung bình";
        if (gpa >= 4.0) return "Yếu";
        return "Kém";
    }
}