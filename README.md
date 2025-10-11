# 🚀 Recruitment Web — Spring Boot + MySQL + Docker

Dự án Spring Boot (JDK 23) sử dụng Maven, kết nối MySQL, và chạy hoàn toàn trong Docker.

---

## 🧩 Cấu trúc dự án
recruitment-web/
├── src/
├── pom.xml
├── dockerfile
├── docker-compose.yml
├── wait-for-it.sh
├── .dockerignore
└── README.md

---

## 🛠️ Yêu cầu môi trường

- **Docker Desktop** (hoặc Docker Engine + Docker Compose v2)
- **Cổng trống:** 8081 (ứng dụng), 3307 (MySQL)
- **JDK 23**, **Maven 3.9** (nếu bạn muốn build local)

---

## ⚙️ Cấu hình

### MySQL Container
- **Database:** `recruitment_db`
- **User:** `recruitment_user`
- **Password:** `123456`
- **Port:** `3307`

### Spring Boot App
- **Port:** `8081`
- **Datasource:** `jdbc:mysql://mysql:3306/recruitment_db`

---

## 🚀 Build và chạy bằng Docker

```bash
# 1️⃣ Build và chạy toàn bộ (MySQL + App)
docker compose up --build
Sau khi build xong:

App sẵn sàng tại: http://localhost:8081

MySQL chạy tại: localhost:3307