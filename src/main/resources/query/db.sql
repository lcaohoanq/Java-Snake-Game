-- Create the database if it doesn't exist
CREATE DATABASE IF NOT EXISTS snake_game_app;

-- Use the database
USE snake_game_app;

-- Create the users table
CREATE TABLE IF NOT EXISTS users (
    user_id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(45) NOT NULL,
    password VARCHAR(50) NOT NULL,
    score INT DEFAULT 0,
    created TIMESTAMP NOT NULL DEFAULT NOW()
);

-- Insert values into the users table
INSERT INTO users (username, password) VALUES
('hoang', '$31$16$tI3b31pfkwiVfW7u1_4LYcyzameV6gGm_fto0tEIBCI'),
('toilaluu', '$31$16$EwBZ6WEqPfIPaeynRV63Nn8UhLOSucwkC7WBY7uAchk'),
('duong', '$31$16$A2fFe83zAcbXOMeZ7NiMBnVOCD7actfU68aIkZE1rEA'),
('huy', '$31$16$5uBNgudXH6BsSz4N7GbbrfjrqAMGh6TcoxDs6ruwnPI'),
('bao', '$31$16$CrqpO_WzDPLWh9jCpPcKeqKv1VEHswyq3d7G6wZQpQg'),
('minhnhu', '$31$16$xEeCSM_NeDhbLzpvpXAJpxdxGUR-dGI46UEH1paLa90');

-------------------------------------------------------------------------------------------------------
--  ===========================================Procedure==============================================
-- 1. selectUsernameAndScore
DELIMITER $

DROP PROCEDURE IF EXISTS proc_select_username_score;

CREATE PROCEDURE proc_select_username_score()
BEGIN
    SELECT username, score
    FROM `snake_game_app`.`users`;
END $

DELIMITER ;

-- 2. selectUsernameAndPasswordByUsername
DELIMITER $

DROP PROCEDURE IF EXISTS proc_select_username_password;

CREATE PROCEDURE proc_select_username_password(IN p_username NVARCHAR(45))
BEGIN
    SELECT username, password
    FROM `snake_game_app`.`users`
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
    FROM `snake_game_app`.`users`
    WHERE username = p_username;
END $

DELIMITER ;

-- 4. insert(username, password, score, created)
DELIMITER $
DROP PROCEDURE IF EXISTS proc_insert_user;
CREATE PROCEDURE proc_insert_user(
    IN p_username VARCHAR(45),
    IN p_password VARCHAR(50)
)
BEGIN
    -- Inserting data into a table named 'users'
    INSERT INTO `snake_game_app`.`users` (username, password)
    VALUES (p_username, p_password);
END $

DELIMITER ;

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
    UPDATE `snake_game_app`.`users`
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
-- CALL proc_insert_user('hoang', '1', @status);
-- SELECT @status as `insert status`;
-- 5.

-- 6.
-- CALL proc_update_score_by_username('hoang', 42);