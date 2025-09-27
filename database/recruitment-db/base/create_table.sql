CREATE DATABASE IF NOT EXISTS recruitment_db
CHARACTER SET utf8mb4
COLLATE utf8mb4_unicode_ci;

-- 1. Bảng Users
CREATE TABLE Users (
    user_id INT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(50) NOT NULL UNIQUE,
    password VARCHAR(100) NOT NULL,
    email VARCHAR(100) NOT NULL UNIQUE,
    full_name VARCHAR(100),
    role VARCHAR(100) NOT NULL, -- 'candidate', 'employer', 'admin'
    phone VARCHAR(20),
    create_date DATETIME DEFAULT CURRENT_TIMESTAMP,
    updated_date DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    del_flg TINYINT DEFAULT 1 -- 0=inactive, 1=active
);

-- 2. Bảng CVs
CREATE TABLE cv (
    cv_id INT AUTO_INCREMENT PRIMARY KEY,
    user_id INT NOT NULL,
    title VARCHAR(100),
    education TEXT,
    experience TEXT,
    skills TEXT,
    certifications TEXT,
    create_date DATETIME DEFAULT CURRENT_TIMESTAMP,
    updated_date DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    del_flg TINYINT DEFAULT 1,
    FOREIGN KEY (user_id) REFERENCES Users(user_id)
);

-- 3. Bảng Jobs
CREATE TABLE Jobs (
    job_id INT AUTO_INCREMENT PRIMARY KEY,
    employer_id INT NOT NULL,
    title VARCHAR(100),
    description TEXT,
    requirements TEXT,
    location VARCHAR(100),
    salary_range VARCHAR(50),
    job_type VARCHAR(100), -- 'full-time', 'part-time', 'intern'
    create_date DATETIME DEFAULT CURRENT_TIMESTAMP,
    updated_date DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    del_flg TINYINT DEFAULT 1,
    FOREIGN KEY (employer_id) REFERENCES Users(user_id)
);

-- 4. Bảng job_cv (Applications)
CREATE TABLE job_cv (
    application_id INT AUTO_INCREMENT PRIMARY KEY,
    cv_id INT NOT NULL,
    job_id INT NOT NULL,
    status VARCHAR(100) DEFAULT 'pending', -- 'pending', 'accepted', 'rejected'
    applied_at DATETIME DEFAULT CURRENT_TIMESTAMP,
    updated_date DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    feedback TEXT,
    FOREIGN KEY (cv_id) REFERENCES cv(cv_id),
    FOREIGN KEY (job_id) REFERENCES Jobs(job_id)
);

-- 5. Bảng AdminLogs
CREATE TABLE AdminLogs (
    log_id INT AUTO_INCREMENT PRIMARY KEY,
    admin_id INT NOT NULL,
    action VARCHAR(255),
    target_type VARCHAR(100), -- 'user', 'cv', 'job'
    target_id INT,
    create_date DATETIME DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (admin_id) REFERENCES Users(user_id)
);
