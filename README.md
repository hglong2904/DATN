# DATN
Kiểm thử tự động cho website quản lý nhân sự OrangeHRM Live Demo

**1. Giới thiệu**

Dự án này là hệ thống kiểm thử tự động dành cho trang web OrangeHRM Live Demo, sử dụng các công nghệ:
- Java
- Selenium WebDriver
- TestNG
- ExtentReports
- Log4j2
- WebDriverManager
- Quản lý phụ thuộc bằng Maven

**2. Cấu trúc thư mục chính**

├── src
│   ├── main
│   │   └── resources
│   │       └── log4j2.properties      // Cấu hình logging
│   └── test
│       └── java
│           ├── Common                // Cấu hình driver, helper dùng chung
│           ├── Pages                 // Page Object của các màn hình
│           ├── TestCases             // Các class kiểm thử
│           ├── ExtentReport          // Tạo báo cáo kiểm thử
│           └── RunTestNG.xml         // File cấu hình chạy test
├── pom.xml                           // File khai báo Maven

**3. Cài đặt môi trường**
_3.1. Yêu cầu:_
   - Java JDK 15 hoặc cao hơn
   - Maven 3.6+
   - IDE (IntelliJ IDEA/ Eclipse)
   - Chrome Browser

_3.2. Clone hoặc copy mã nguồn_
   Mở terminal và di chuyển đến thư mục chứa mã: cd DATN2024.2
   git clone https://github.com/hglong2904/DATN.git

_3.3. Cài đặt thư viện_
   Tại thư mục gốc của dự án, chạy: mvn clean install
