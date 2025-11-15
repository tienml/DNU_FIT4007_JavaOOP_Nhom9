package cli;

import model.Course;
import model.Grade;
import repository.courseRepository;
import repository.gradeRepository;
import service.scoringService;

import java.util.List;
import java.util.Scanner;

public class gradeMenu {
    private final Scanner scanner;
    private final scoringService scoringService;
    private final gradeRepository gradeRepo;
    private final courseRepository courseRepo;

    public gradeMenu(scoringService scoringService, gradeRepository gradeRepo, courseRepository courseRepo) {
        this.scanner = new Scanner(System.in);
        this.scoringService = scoringService;
        this.gradeRepo = gradeRepo;
        this.courseRepo = courseRepo;
    }

    public void display() {
        while (true) {
            System.out.println("\n--- NHẬP ĐIỂM HỌC PHẦN ---");
            System.out.println("1. Nhập điểm");
            System.out.println("2. Sửa điểm");
            System.out.println("3. Xem điểm sinh viên");
            System.out.println("0. Quay lại");
            System.out.print("Chọn: ");

            switch (scanner.nextLine()) {
                case "1" -> input(false);
                case "2" -> input(true);
                case "3" -> view();
                case "0" -> { return; }
                default -> System.out.println("Sai lựa chọn!");
            }
        }
    }

    private void input(boolean isEdit) {
        System.out.print("ID sinh viên: ");
        String sid = scanner.nextLine().trim();

        System.out.print("Mã học phần: ");
        String cid = scanner.nextLine().trim();

        Course c = courseRepo.findById(cid).orElse(null);
        if (c == null) {
            System.out.println("→ Không tìm thấy học phần!");
            return;
        }

        double quiz = getScore("Quiz: ");
        double mid = getScore("Giữa kỳ: ");
        double fin = getScore("Cuối kỳ: ");
        double project = getScore("Bài tập lớn: ");

        Grade g = gradeRepo.findById(sid + ":" + cid).orElse(new Grade());
        g.setStudentId(sid);
        g.setCourseId(cid);
        g.setCourse(c);
        g.setQuiz(quiz);
        g.setMid(mid);
        g.setFin(fin);
        g.setProject(project);

        try {
            scoringService.calculateTotal(c, g);
        } catch (Exception e) {
            System.out.println("→ Lỗi tính tổng điểm: " + e.getMessage());
            return;
        }

        boolean ok;
        if (gradeRepo.findById(sid + ":" + cid).isPresent()) {
            ok = gradeRepo.update(g);
        } else {
            ok = gradeRepo.add(g);
        }

        System.out.println(ok ? (isEdit ? "→ Cập nhật thành công!" : "→ Nhập điểm thành công!")
                : "→ Không thể lưu điểm!");
    }

    private double getScore(String prompt) {
        while (true) {
            try {
                System.out.print(prompt);
                return Double.parseDouble(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Vui lòng nhập số hợp lệ!");
            }
        }
    }

    private void view() {
        System.out.print("Nhập ID sinh viên: ");
        String sid = scanner.nextLine().trim();

        List<Grade> grades = gradeRepo.getAll().stream()
                .filter(g -> g.getStudentId().equals(sid))
                .toList();

        if (grades.isEmpty()) {
            System.out.println("→ Sinh viên chưa có điểm!");
            return;
        }

        System.out.println("\n--- DANH SÁCH ĐIỂM ---");
        for (Grade g : grades) {
            System.out.printf("HP: %s | Quiz: %.2f | Mid: %.2f | Fin: %.2f | Project: %.2f | Total: %.2f\n",
                    g.getCourseId(),
                    g.getQuiz(),
                    g.getMid(),
                    g.getFin(),
                    g.getProject(),
                    g.getTotal());
        }
    }
}
