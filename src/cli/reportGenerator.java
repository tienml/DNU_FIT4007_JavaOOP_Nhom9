package cli;

import model.Student;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class reportGenerator {
    public void exportReportToFile(String fileName, List<Student> students) {
        if (students.isEmpty()) {
            System.out.println("→ Không có sinh viên nào để xuất báo cáo.");
            return;
        }

        try (FileWriter writer = new FileWriter(fileName)) {
            writer.write("STT,Họ Tên,Ngành,Năm,GPA\n");
            int index = 1;
            for (Student s : students) {
                writer.write(String.format("%d,%s,%s,%d,%.2f\n",
                        index++, s.getFullName(), s.getMajor(), s.getYear(), s.calculateGPA()));
            }
            System.out.println("→ Xuất báo cáo thành công: " + fileName);
        } catch (IOException e) {
            System.out.println("→ Lỗi khi xuất file: " + e.getMessage());
        }
    }
}
