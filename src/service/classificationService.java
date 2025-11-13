package service;

public class classificationService {

    public String classify(double gpa) {
        if (gpa >= 3.6) return "Xuất sắc";
        if (gpa >= 3.2) return "Giỏi";
        if (gpa >= 2.5) return "Khá";
        if (gpa >= 2.0) return "Trung bình";
        if (gpa >= 1.0) return "Yếu";
        return "Kém";
    }
}
