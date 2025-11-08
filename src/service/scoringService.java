package service;

import model.Course;

/**
 * Tính điểm cuối kỳ.
 * final = quiz*w1 + mid*w2 + fin*w3 + project*w4
 * TODO:
 *  - validate 0..10 (dùng util.Validator)
 *  - kiểm tra tổng trọng số = 1.0
 */
public class scoringService {
    public double computeFinal(Double quiz, Double mid, Double fin, Double proj, Course c){
        return 0.0; // TODO
    }
}
