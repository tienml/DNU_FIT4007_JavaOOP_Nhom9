package cli;

import repository.*;
import service.*;

import java.io.File;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        studentRepository studentRepo = new studentRepository(new File("data/students.csv"));
        courseRepository courseRepo = new courseRepository(new File("data/courses.csv"));
        groupRepository groupRepo = new groupRepository(new File("data/class_groups.csv"));
        gradeRepository gradeRepo = new gradeRepository(new File("data/grades.csv"));
        lecturerRepository lecturerRepo = new lecturerRepository(new File("data/lecturers.csv"));

        studentRepo.load();
        courseRepo.load();
        groupRepo.load();
        gradeRepo.load();
        lecturerRepo.load();

        gradeRepo.attachTo(studentRepo, courseRepo);

        studentService studentService = new studentService(studentRepo);
        courseService courseService = new courseService(courseRepo);
        rankingService rankingService = new rankingService(studentRepo, groupRepo);
        reportService reportService = new reportService(studentRepo);
        scoringService scoringService = new scoringService();
        lecturerService lecturerService = new lecturerService(lecturerRepo);
        searchService searchService = new searchService(studentRepo);

        Scanner scanner = new Scanner(System.in);

        Menu menu = new Menu(studentService, courseService, rankingService,
                reportService, scoringService, lecturerService, gradeRepo,searchService, scanner);
        menu.display();

        studentRepo.save();
        courseRepo.save();
        gradeRepo.save();
        lecturerRepo.save();
    }
}
