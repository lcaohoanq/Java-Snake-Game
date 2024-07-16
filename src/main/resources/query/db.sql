-- Create the database if it doesn't exist
CREATE DATABASE IF NOT EXISTS snake_game_app;
DROP DATABASE IF EXISTS snake_game_app;
-- Use the database
USE snake_game_app;


-- Create the users table
CREATE TABLE IF NOT EXISTS users
(
    user_id      INT         NOT NULL AUTO_INCREMENT PRIMARY KEY,
    email        VARCHAR(255),
    phone_number VARCHAR(15),
    first_name   VARCHAR(45) NOT NULL,
    last_name    VARCHAR(45) NOT NULL,
    password     VARCHAR(50) NOT NULL,
    score        INT                  DEFAULT 0,
    created      TIMESTAMP   NOT NULL DEFAULT NOW(),
    CHECK (email IS NOT NULL OR phone_number IS NOT NULL)
);

-- Insert values into the users table
INSERT INTO users (email, first_name, last_name, password)
VALUES ('hoanglcse181513@fpt.edu.vn', 'Hoang', 'FPT', '$31$16$qEq9u9qMOy_0vhzFnZLwfDBjbjOCodSi5OYJ8NnhT_s');

--  ===========================================Procedure==============================================
-- 1. proc_select_first_name_score
DELIMITER $

DROP PROCEDURE IF EXISTS proc_select_first_name_score;

CREATE PROCEDURE proc_select_first_name_score()
BEGIN
    SELECT first_name, score
    FROM `snake_game_app`.`users`;
END $

DELIMITER ;

-- 2. selectUsernameAndPasswordByUsernameNew
DELIMITER $

DROP PROCEDURE IF EXISTS proc_select_email_password;

CREATE PROCEDURE proc_select_email_password(IN p_email VARCHAR(255))
BEGIN
    SELECT email, password
    FROM `snake_game_app`.`users`
    WHERE email = p_email;
END $

DELIMITER ;

-- 3. proc_select_email_score_by_email
DELIMITER $

DROP PROCEDURE IF EXISTS proc_select_email_score_by_email;

CREATE PROCEDURE proc_select_email_score_by_email(
    IN p_email VARCHAR(255)
)

BEGIN
    SELECT email, score
    FROM `snake_game_app`.`users`
    WHERE email = p_email;
END $

DELIMITER ;

-- 4. insert(email, phone_number, first_name, last_name, password)
DELIMITER $
DROP PROCEDURE IF EXISTS proc_insert_user_new;
CREATE PROCEDURE proc_insert_user_new(
    IN p_email VARCHAR(255),
    IN p_phone_number VARCHAR(15),
    IN p_first_name VARCHAR(45),
    IN p_last_name VARCHAR(45),
    IN p_password VARCHAR(50)
)
BEGIN
    -- Inserting data into a table named 'users'
    INSERT INTO `snake_game_app`.`users` (email, phone_number, first_name, last_name, password)
    VALUES (p_email, p_phone_number, p_first_name, p_last_name, p_password);
END $

-- 5. setSafeUpdate
DELIMITER $

DROP PROCEDURE IF EXISTS proc_set_safe_update;

CREATE PROCEDURE proc_set_safe_update()
BEGIN
    SET
        SQL_SAFE_UPDATES = 0;
END $

DELIMITER ;

-- 6. proc_update_score_by_email
DELIMITER $

DROP PROCEDURE IF EXISTS proc_update_score_by_email;

CREATE PROCEDURE proc_update_score_by_email(
    IN p_email VARCHAR(255),
    IN p_score INT
)

BEGIN
    UPDATE `snake_game_app`.`users`
    SET score = p_score
    WHERE email = p_email;
END $

DELIMITER ;

-- ====================Test Procedure==============================
-- 1.
-- CALL proc_select_user_score();
-- 2.
-- CALL proc_select_user_password('hoang');
-- 3.
CALL proc_select_email_score_by_email('hoangdz1604@gmail.com');
-- 4.
-- CALL proc_insert_user('hoang', '1', @status);
-- SELECT @status as `insert status`;
-- 5.

-- 6.
-- CALL proc_update_score_by_username('hoang', 42);