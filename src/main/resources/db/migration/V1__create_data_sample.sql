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