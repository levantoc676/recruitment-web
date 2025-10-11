# Recruitment Web App - Docker Setup

Hướng dẫn này dành cho khách hàng muốn chạy **Spring Boot + MySQL** bằng Docker, không cần cài Maven hay JDK.

---

## 1. Yêu cầu

* Docker >= 20.x
* Docker Compose >= 1.29.x
* Hệ điều hành: Windows, macOS, Linux

---

## 2. Clone project

```bash
git clone https://github.com/yourname/recruitment-web.git
cd recruitment-web
```

> Lưu ý: Project đã bao gồm **file `.jar`** sẵn trong folder `target/`

---

## 3. Build Docker image

```bash
docker build -t recruitment-app .
```

* Docker sẽ tạo **runtime image** từ file `.jar`
* Script `wait-for-it.sh` được sử dụng để chờ MySQL sẵn sàng

---

## 4. Run app + MySQL bằng Docker Compose

```bash
docker compose up
```

* Container **MySQL** sẽ chạy trước
* Container **App** sẽ chờ DB sẵn sàng
* App chạy ở: `http://localhost:8081`
* MySQL host: `localhost:3307`, user/password trong `docker-compose.yml`

---

## 5. Chạy container riêng (tùy chọn)

### MySQL:

```bash
docker run -d \
  --name recruitment-mysql \
  -e MYSQL_ROOT_PASSWORD=root_password_here \
  -e MYSQL_DATABASE=recruitment_db \
  -e MYSQL_USER=recruitment_user \
  -e MYSQL_PASSWORD=123456 \
  -p 3307:3306 \
  -v mysql-data:/var/lib/mysql \
  mysql:8.0
```

### App:

```bash
docker run -d \
  --name recruitment-app \
  --link recruitment-mysql:mysql \
  -p 8081:8080 \
  -e SPRING_DATASOURCE_URL=jdbc:mysql://mysql:3306/recruitment_db?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=Asia/Bangkok \
  -e SPRING_DATASOURCE_USERNAME=recruitment_user \
  -e SPRING_DATASOURCE_PASSWORD=123456 \
  -e SPRING_JPA_HIBERNATE_DDL_AUTO=update \
  recruitment-app
```

---

## 6. Lưu ý quan trọng

* **LF line endings** cho dockerfile, `.sh`, `docker-compose.yml` → tránh lỗi trên Linux container
* Nếu app không kết nối DB → kiểm tra `wait-for-it.sh` hoặc MySQL container
* Dữ liệu MySQL được lưu trong **volume `mysql-data`** → giữ dữ liệu khi restart container

---

## 7. Stop containers

```bash
docker compose down
```

> Hoặc nếu chạy container riêng:

```bash
docker stop recruitment-app recruitment-mysql
docker rm recruitment-app recruitment-mysql
```

---

## 8. Liên hệ hỗ trợ

Nếu gặp lỗi khi chạy, liên hệ nhóm phát triển để được hỗ trợ kèm file `.jar` mới nhất.
