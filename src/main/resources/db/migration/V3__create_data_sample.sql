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
