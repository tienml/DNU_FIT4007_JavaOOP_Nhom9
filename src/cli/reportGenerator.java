package cli;

import model.Student;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class reportGenerator {
    public void exportReportToFile(String fileName, List<Student> students) {
        if (students.isEmpty()) {
            System.out.println("Không có sinh viên nào để xuất báo cáo.");
            return;
        }

        try (FileWriter writer = new FileWriter(fileName)) {
            writer.write("STT,Họ Tên,Lớp,Khoa,GPA\n");
            int index = 1;
            for (Student s : students) {
                writer.write(String.format("%d,%s,%s,%s,%.2f\n",
                        index++, s.getFullName(), s.getClass(), s.getMajor(), s.getGrades()));
            }
            System.out.println("→ Xuất báo cáo thành công: " + fileName);
        } catch (IOException e) {
            System.out.println("→ Lỗi khi xuất file: " + e.getMessage());
        }
    }
}

