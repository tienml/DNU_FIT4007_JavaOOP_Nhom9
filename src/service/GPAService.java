package service;

import model.Course;
import model.Grade;
import java.util.List;
import java.util.Map;

/**
 * Tính GPA có trọng số tín chỉ.
 * TODO:
 *  - sum(finalScore * credit) / sum(credit)
 */
public class GPAService {
    public double computeGpa(List<Grade> grades, Map<String, Course> courseById){
        return 0.0; // TODO
    }
}
