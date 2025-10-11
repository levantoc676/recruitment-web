# ============================================================
# 🏗️ STAGE 1: Build application using Maven + JDK 23
# ============================================================
FROM maven:3.9.9-eclipse-temurin-23 AS builder
WORKDIR /app

# Copy pom.xml để tận dụng cache layer Maven
COPY pom.xml .

# Tải sẵn dependencies (tăng tốc build)
RUN mvn -B dependency:go-offline -Dmaven.test.skip=true

# Copy source code vào container và build
COPY src ./src
RUN mvn -B clean package -DskipTests

# ============================================================
# 🚀 STAGE 2: Runtime (JRE image nhỏ gọn)
# ============================================================
FROM eclipse-temurin:23-jre-alpine AS runtime
WORKDIR /app

# Copy file JAR từ stage build
COPY --from=builder /app/target/*.jar app.jar

# Mở cổng ứng dụng
EXPOSE 8080

# Biến môi trường JVM (có thể override khi chạy)
ENV JAVA_OPTS="-Xms256m -Xmx512m"

# Chạy app trực tiếp
# Flyway sẽ tự connect MySQL và apply migration khi app start
ENTRYPOINT ["sh", "-c", "java $JAVA_OPTS -jar app.jar"]
