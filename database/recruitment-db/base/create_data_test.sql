-- ==========================================
-- USERS
-- ==========================================
INSERT INTO users
(user_id, username, password, email, full_name, role, phone, create_date, updated_date, del_flg, locked)
VALUES
(1, 'employer1', '1', 'employer1@example.com', 'Công ty ABC', 'employer', '0901111111', NOW(), NOW(), false, false),
(2, 'employer2', '1', 'employer2@example.com', 'Công ty XYZ', 'employer', '0905555555', NOW(), NOW(), false, false),
(3, 'candidate1', '1', 'candidate1@example.com', 'Nguyen Van A', 'candidate', '0902222222', NOW(), NOW(), false, false),
(4, 'candidate2', '1', 'candidate2@example.com', 'Tran Thi B', 'candidate', '0903333333', NOW(), NOW(), false, false),
(5, 'candidate3', '1', 'candidate3@example.com', 'Le Van C', 'candidate', '0906666666', NOW(), NOW(), false, false),
(6, 'candidate4', '1', 'candidate4@example.com', 'Pham Thi D', 'candidate', '0907777777', NOW(), NOW(), false, false),
(7, 'candidate5', '1', 'candidate5@example.com', 'Hoang Van E', 'candidate', '0908888888', NOW(), NOW(), false, false),
(8, 'candidate6', '1', 'candidate6@example.com', 'Nguyen Thi F', 'candidate', '0909999999', NOW(), NOW(), false, false),
(9, 'admin1', '1', 'admin1@example.com', 'Admin', 'admin', '0904444444', NOW(), NOW(), false, false),
(10, 'admin2', '1', 'admin2@example.com', 'Admin 2', 'admin', '0900000000', NOW(), NOW(), false, false);

-- ==========================================
-- JOBS
-- ==========================================
INSERT INTO jobs (job_id, user_id, company_name, title, description, requirements, skills, benefits, location, work_mode, job_type, industry, salary_range, experience_level, education_level, deadline, logo_path, employment_status, views, applications_count, del_flg, create_date, updated_date) VALUES
(1, 1, 'Công ty ABC', 'Java Developer', 'Phát triển ứng dụng web Spring Boot.', 'Kinh nghiệm Java >=1 năm', 'Java, Spring Boot, SQL', 'Bảo hiểm, thưởng', 'Hồ Chí Minh', 'Onsite', 'Full-time', 'IT', '10-15 triệu', 'Junior', 'Đại học', '2025-10-31', 'abc_logo.png', 'Open', 0, 0, false, NOW(), NOW()),
(2, 1, 'Công ty ABC', 'Frontend Developer', 'Phát triển giao diện ReactJS.', 'Thành thạo ReactJS, HTML, CSS', 'ReactJS, JS, CSS', 'Thưởng dự án', 'Hà Nội', 'Remote', 'Full-time', 'IT', '12-18 triệu', 'Junior', 'Đại học', '2025-11-15', 'abc_logo.png', 'Open', 0, 0, false, NOW(), NOW()),
(3, 2, 'Công ty XYZ', 'Python Developer', 'Backend Python/Django.', 'Python >=1 năm, Django', 'Python, Django, REST', 'Thưởng cuối năm', 'Đà Nẵng', 'Hybrid', 'Full-time', 'IT', '15-20 triệu', 'Junior', 'Đại học', '2025-11-30', 'xyz_logo.png', 'Open', 0, 0, false, NOW(), NOW()),
(4, 2, 'Công ty XYZ', 'QA Engineer', 'Kiểm thử phần mềm.', 'Kinh nghiệm QA, Automation', 'Selenium, Java', 'Bảo hiểm', 'Hà Nội', 'Onsite', 'Full-time', 'IT', '8-12 triệu', 'Junior', 'Đại học', '2025-12-15', 'xyz_logo.png', 'Open', 0, 0, false, NOW(), NOW()),
(5, 1, 'Công ty ABC', 'Project Manager', 'Quản lý dự án phần mềm.', 'PM >=2 năm', 'Agile, Scrum', 'Thưởng + bảo hiểm', 'Hồ Chí Minh', 'Onsite', 'Full-time', 'IT', '20-30 triệu', 'Mid', 'Đại học', '2025-12-01', 'abc_logo.png', 'Open', 0, 0, false, NOW(), NOW()),
(6, 1, 'Công ty ABC', 'DevOps Engineer', 'Triển khai CI/CD.', 'Kinh nghiệm DevOps >=1 năm', 'Docker, Jenkins, AWS', 'Bảo hiểm', 'Hồ Chí Minh', 'Hybrid', 'Full-time', 'IT', '15-20 triệu', 'Junior', 'Đại học', '2025-11-20', 'abc_logo.png', 'Open', 0, 0, false, NOW(), NOW()),
(7, 2, 'Công ty XYZ', 'Business Analyst', 'Phân tích nghiệp vụ.', 'BA >=1 năm', 'Excel, SQL, UML', 'Bảo hiểm, thưởng', 'Hà Nội', 'Onsite', 'Full-time', 'IT', '12-18 triệu', 'Junior', 'Đại học', '2025-12-10', 'xyz_logo.png', 'Open', 0, 0, false, NOW(), NOW()),
(8, 2, 'Công ty XYZ', 'Mobile Developer', 'Phát triển ứng dụng Android/iOS.', 'Kinh nghiệm >=1 năm', 'Kotlin, Swift, ReactNative', 'Bảo hiểm, thưởng', 'Đà Nẵng', 'Remote', 'Full-time', 'IT', '12-20 triệu', 'Junior', 'Đại học', '2025-11-25', 'xyz_logo.png', 'Open', 0, 0, false, NOW(), NOW()),
(9, 1, 'Công ty ABC', 'UI/UX Designer', 'Thiết kế giao diện và trải nghiệm.', 'Thành thạo Figma, Photoshop', 'Figma, Photoshop', 'Thưởng dự án', 'Hồ Chí Minh', 'Onsite', 'Full-time', 'Design', '10-15 triệu', 'Junior', 'Đại học', '2025-11-18', 'abc_logo.png', 'Open', 0, 0, false, NOW(), NOW()),
(10, 1, 'Công ty ABC', 'Database Administrator', 'Quản trị cơ sở dữ liệu.', 'Kinh nghiệm MySQL, PostgreSQL >=2 năm', 'SQL, DBA', 'Bảo hiểm', 'Hà Nội', 'Onsite', 'Full-time', 'IT', '15-25 triệu', 'Mid', 'Đại học', '2025-12-05', 'abc_logo.png', 'Open', 0, 0, false, NOW(), NOW());

-- ==========================================
-- CVS
-- ==========================================
INSERT INTO cvs (cv_id, user_id, title, file_path, file_type, file_size, summary, created_date, updated_date, del_flg) VALUES
(1, 3, 'CV Java Developer', '/uploads/cvs/cv_candidate1.pdf', 'pdf', 250, 'Ứng viên Java 2 năm kinh nghiệm', NOW(), NOW(), false),
(2, 4, 'CV Frontend Developer', '/uploads/cvs/cv_candidate2.pdf', 'pdf', 200, 'Ứng viên ReactJS 1 năm kinh nghiệm', NOW(), NOW(), false),
(3, 5, 'CV Python Developer', '/uploads/cvs/cv_candidate3.pdf', 'pdf', 300, 'Ứng viên Python/Django 2 năm kinh nghiệm', NOW(), NOW(), false),
(4, 6, 'CV QA Engineer', '/uploads/cvs/cv_candidate4.pdf', 'pdf', 180, 'Ứng viên QA Automation 1 năm', NOW(), NOW(), false),
(5, 7, 'CV Project Manager', '/uploads/cvs/cv_candidate5.pdf', 'pdf', 220, 'Ứng viên PM 3 năm', NOW(), NOW(), false),
(6, 8, 'CV DevOps', '/uploads/cvs/cv_candidate6.pdf', 'pdf', 210, 'Ứng viên DevOps 2 năm', NOW(), NOW(), false),
(7, 3, 'CV UI/UX', '/uploads/cvs/cv_candidate1_uiux.pdf', 'pdf', 190, 'Ứng viên UI/UX Designer', NOW(), NOW(), false),
(8, 4, 'CV Mobile', '/uploads/cvs/cv_candidate2_mobile.pdf', 'pdf', 200, 'Ứng viên Mobile Developer', NOW(), NOW(), false),
(9, 5, 'CV DBA', '/uploads/cvs/cv_candidate3_dba.pdf', 'pdf', 230, 'Ứng viên DBA', NOW(), NOW(), false),
(10, 6, 'CV Business Analyst', '/uploads/cvs/cv_candidate4_ba.pdf', 'pdf', 210, 'Ứng viên BA', NOW(), NOW(), false);

-- ==========================================
-- JOB APPLICATIONS
-- ==========================================
INSERT INTO job_applications (id, cv_id, job_id, status, applied_date, note) VALUES
(1, 1, 1, 'PENDING', NOW(), 'Ứng tuyển Java Developer'),
(2, 2, 2, 'PENDING', NOW(), 'Ứng tuyển Frontend Developer'),
(3, 3, 3, 'PENDING', NOW(), 'Ứng tuyển Python Developer'),
(4, 4, 4, 'PENDING', NOW(), 'Ứng tuyển QA Engineer'),
(5, 5, 5, 'PENDING', NOW(), 'Ứng tuyển Project Manager'),
(6, 6, 6, 'PENDING', NOW(), 'Ứng tuyển DevOps Engineer'),
(7, 7, 9, 'PENDING', NOW(), 'Ứng tuyển UI/UX Designer'),
(8, 8, 8, 'PENDING', NOW(), 'Ứng tuyển Mobile Developer'),
(9, 9, 10, 'PENDING', NOW(), 'Ứng tuyển DBA'),
(10, 10, 7, 'PENDING', NOW(), 'Ứng tuyển Business Analyst'),
(11, 1, 2, 'PENDING', NOW(), 'Ứng tuyển Frontend Developer'),
(12, 2, 1, 'PENDING', NOW(), 'Ứng tuyển Java Developer'),
(13, 3, 5, 'PENDING', NOW(), 'Ứng tuyển Project Manager'),
(14, 4, 6, 'PENDING', NOW(), 'Ứng tuyển DevOps Engineer'),
(15, 5, 8, 'PENDING', NOW(), 'Ứng tuyển Mobile Developer');
