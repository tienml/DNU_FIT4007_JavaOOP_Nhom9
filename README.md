# Student Ranking (Java Console)
Skeleton dự án cho nhóm 3 người. Không dùng Maven (có thể thêm sau).

## Cách chạy nhanh
mkdir -p bin
javac -d bin $(find src -name "*.java")
java -cp bin cli.Main

## Thư mục chính
- src/model, src/service, src/repository, src/cli, src/util, src/exception, src/iface
- data/: CSV mẫu (có header)
- docs/: tài liệu (csv-spec.md)

## Lưu ý
- Thư mục "interface" đổi thành "iface" vì "interface" là từ khóa Java.
- Mỗi file có TODO rõ ràng; thành viên chỉ việc bổ sung code, KHÔNG cần đổi tên file/packge.
