-- Create the database if it doesn't exist
CREATE DATABASE IF NOT EXISTS users_schema;

-- Use the database
USE users_schema;

-- Create the users table
CREATE TABLE IF NOT EXISTS users (
    id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(45) NOT NULL UNIQUE,
    password VARCHAR(50) NOT NULL,
    score INT,
    reg_date VARCHAR(30) NOT NULL
);

-- Insert values into the users table
INSERT INTO users (username, password, score, reg_date) VALUES
('hoang', '$31$16$tI3b31pfkwiVfW7u1_4LYcyzameV6gGm_fto0tEIBCI', 99, '2024-02-28 16:20:30'),
('toilaluu', '$31$16$EwBZ6WEqPfIPaeynRV63Nn8UhLOSucwkC7WBY7uAchk', 0, '2024-03-01 13:54:29'),
('duong', '$31$16$A2fFe83zAcbXOMeZ7NiMBnVOCD7actfU68aIkZE1rEA', 0, '2024-03-01 07:05:52.294924200'),
('huy', '$31$16$5uBNgudXH6BsSz4N7GbbrfjrqAMGh6TcoxDs6ruwnPI', 0, '2024-03-01 07:07:42.942166400'),
('bao', '$31$16$CrqpO_WzDPLWh9jCpPcKeqKv1VEHswyq3d7G6wZQpQg', 0, '2024-03-03 02:19:38.028955200'),
('minhnhu', '$31$16$xEeCSM_NeDhbLzpvpXAJpxdxGUR-dGI46UEH1paLa90', 0, '2024-03-03 02:25:42.098824600');

-------------------------------------------------------------------------------------------------------
--  ===========================================Procedure==============================================
-- 1. selectUsernameAndScore
DELIMITER $

DROP PROCEDURE IF EXISTS proc_select_username_score;

CREATE PROCEDURE proc_select_username_score()
BEGIN
    SELECT username, score
    FROM `users_schema`.`users`;
END $

DELIMITER ;

-- 2. selectUsernameAndPasswordByUsername
DELIMITER $

DROP PROCEDURE IF EXISTS proc_select_username_password;

CREATE PROCEDURE proc_select_username_password(IN p_username NVARCHAR(45))
BEGIN
    SELECT username, password
    FROM `users_schema`.`users`
    WHERE username = p_username;
END $

DELIMITER ;

-- 3. selectUsernameAndScoreByUsername
DELIMITER $

DROP PROCEDURE IF EXISTS proc_select_username_score_by_username;

CREATE PROCEDURE proc_select_username_score_by_username(
    IN p_username NVARCHAR(45)
)
BEGIN
    SELECT username, score
    FROM `users_schema`.`users`
    WHERE username = p_username;
END $

DELIMITER ;

-- 4. insert(username, password, score, reg_date)
DELIMITER $
DROP PROCEDURE IF EXISTS proc_insert_user;
CREATE PROCEDURE proc_insert_user(
    IN p_username VARCHAR(45),
    IN p_password VARCHAR(50),
    IN p_score INT,
    IN p_reg_date VARCHAR(45)
)
BEGIN
    -- Inserting data into a table named 'users'
    INSERT INTO `users_schema`.`users` (username, password, score, reg_date)
    VALUES (p_username, p_password, p_score, p_reg_date);
END $

DELIMITER ; -- Khai báo dấu phân cách trở lại mặc định là dấu chấm phẩy ;

-- 5. setSafeUpdate
DELIMITER $

DROP PROCEDURE IF EXISTS proc_set_safe_update;

CREATE PROCEDURE proc_set_safe_update()
BEGIN
    SET
        SQL_SAFE_UPDATES = 0;
END $

DELIMITER ;

-- 6. updateScoreByUsername
DELIMITER $

DROP PROCEDURE IF EXISTS proc_update_score_by_username;

CREATE PROCEDURE proc_update_score_by_username(
    IN p_username NVARCHAR(45),
    IN p_score INT
)
BEGIN
    UPDATE `users_schema`.`users`
    SET score = p_score
    WHERE username = p_username;
END $

DELIMITER ;

-- ====================Test Procedure==============================
-- 1.
-- CALL proc_select_user_score();
-- 2.
-- CALL proc_select_user_password('hoang');
-- 3.
-- CALL proc_select_username_score_by_username('hoang');
-- 4.
-- CALL proc_insert_user('hoang', '1', 10, '2024-03-03T02:25:42.098824600Z', @status);
-- SELECT @status as `insert status`;
-- 5.

-- 6.
-- CALL proc_update_score_by_username('hoang', 42);