# DATN - Kiểm thử tự động cho website quản lý nhân sự OrangeHRM Live Demo

## 1. Giới thiệu

Dự án **DATN** là hệ thống kiểm thử tự động dành cho trang web OrangeHRM Live Demo, giúp kiểm tra các chức năng của website một cách tự động, nhanh chóng và chính xác. Dự án sử dụng các công nghệ hiện đại, phù hợp với kiểm thử phần mềm web.

**Công nghệ sử dụng:**
- **Java**: Ngôn ngữ lập trình chính cho các kịch bản kiểm thử.
- **Selenium WebDriver**: Tự động hóa thao tác trên trình duyệt.
- **TestNG**: Quản lý và thực thi các bộ kiểm thử.
- **ExtentReports**: Báo cáo kết quả kiểm thử chi tiết, trực quan.
- **Log4j2**: Ghi log cho quá trình thực thi kiểm thử.
- **WebDriverManager**: Quản lý driver cho Selenium tự động.
- **Maven**: Quản lý phụ thuộc, build project.

## 2. Hướng dẫn cài đặt

### 2.1. Yêu cầu môi trường

- **Java JDK** 15 trở lên
- **Maven** 3.6+
- Trình soạn thảo mã nguồn: **IntelliJ IDEA** hoặc **Eclipse**
- **Chrome Browser** (để chạy kiểm thử trên Chrome)

### 2.2. Clone mã nguồn

Mở terminal và thực hiện lệnh sau để lấy mã nguồn về máy:

```bash
git clone https://github.com/hglong2904/DATN.git
cd DATN
```

### 2.3. Cài đặt thư viện

Tại thư mục gốc của dự án, chạy lệnh sau để tự động tải về các thư viện cần thiết:

```bash
mvn clean install
```

## 3. Cấu trúc thư mục

```
├── src
│   ├── main
│   │   └── resources
│   │       └── log4j2.properties    // Cấu hình logging
│   └── test
│       └── java
│           ├── Common               // Cấu hình driver, helper dùng chung
│           ├── Pages                // Page Object của các màn hình
│           ├── TestCases            // Các class kiểm thử
│           ├── ExtentReport         // Tạo báo cáo kiểm thử
│           └── RunTestNG.xml        // File cấu hình chạy test
├── pom.xml                          // File khai báo 
├── README.md
```
## 4. Hướng dẫn chạy kiểm thử

Sau khi cài đặt xong môi trường, thực hiện các bước sau để chạy kiểm thử:

#### Cách 1: Chạy từ IDE
- Mở file `RunTestNG.xml` trong thư mục `src/test/java`.
- Nhấn chuột phải và chọn Run `RunTestNG.xml` để chạy toàn bộ test case.

#### Cách 2: Chạy bằng lệnh Maven
```bash
mvn clean test
```

Kết quả kiểm thử sẽ được lưu tại thư mục `test-output` hoặc thư mục chỉ định trong cấu hình ExtentReports.

## 5. Báo cáo & Log

- **ExtentReports**: Báo cáo dạng HTML sử dụng ExtentReports, truy cập tại: `test-output/ExtentReports/ExtentReport.html`.
- **Log4j2**: Hệ thống sử dụng Log4j2 để ghi log. File cấu hình nằm tại `src/main/resources/log4j2.properties`.

## 6. Ghi chú
- Cấu trúc tuân theo mô hình Page Object Model (POM) giúp dễ mở rộng và bảo trì.
- Có thể thêm các test case mới vào thư mục TestCases.
- Các trang mới cần được định nghĩa tại thư mục Pages.

## 7. Thông tin liên hệ

- Tác giả: [hglong2904](https://github.com/hglong2904)
- Email: trieuhoanglong2904@gmail.com

---

**Lưu ý:** Dự án phục vụ cho mục đích học tập, nghiên cứu về kiểm thử tự động website với Selenium và TestNG.
