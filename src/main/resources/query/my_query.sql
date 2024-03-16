SELECT *
FROM users;

SELECT *
FROM users_schema.users;
WHERE username = 'duong';

DESCRIBE users;

-- lay score
SELECT username, score
FROM users;

-- update by id and name
usersUPDATE
users_schema.users
SET score = 8 WHERE username = 'admin' AND id = '2';

-- delete by id
DELETE
FROM users_schema.users
WHERE id = '14';

-- update by name only but need to safe
UPDATE users_schema.users
SET score = 8
WHERE username = 'fpt';

-- set safe update 
-- update by name
SET
    SQL_SAFE_UPDATES = 0;
UPDATE users_schema.users
SET score = 100
WHERE username = 'fpt';

DROP TABLE users;

-- alter table for update password nvarchar(45) to 50
ALTER TABLE users
    MODIFY COLUMN password NVARCHAR(50);

INSERT INTO `users_schema`.`users` (`username`, `password`, `score`, `reg_date`)
VALUES ('1', 'hoang', '$31$16$tI3b31pfkwiVfW7u1_4LYcyzameV6gGm_fto0tEIBCI', '42', '2024-02-28 16:20:30'),
       ('3', 'toilaluu', '$31$16$EwBZ6WEqPfIPaeynRV63Nn8UhLOSucwkC7WBY7uAchk', '0', '2024-03-01 13:54:29'),
       ('4', 'duong', '$31$16$A2fFe83zAcbXOMeZ7NiMBnVOCD7actfU68aIkZE1rEA', '0', '2024-03-01T07:05:52.294924200Z'),
       ('5', 'huy', '$31$16$5uBNgudXH6BsSz4N7GbbrfjrqAMGh6TcoxDs6ruwnPI', '0', '2024-03-01T07:07:42.942166400Z'),
       ('6', 'bao', '$31$16$CrqpO_WzDPLWh9jCpPcKeqKv1VEHswyq3d7G6wZQpQg', '0', '2024-03-03T02:19:38.028955200Z'),
       ('7', 'minhnhu', '$31$16$xEeCSM_NeDhbLzpvpXAJpxdxGUR-dGI46UEH1paLa90', '0', '2024-03-03T02:25:42.098824600Z');


SELECT username, password
FROM users
WHERE username = 'hoang';

-- Update password
-- update by id and name
UPDATE users_schema.users
SET password = 'admin'
WHERE username = 'admin'
  AND id = '4';

-- Now i want to add field register_date
ALTER TABLE users_schema.users
    ADD COLUMN reg_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP;

DROP TABLE users;

--  =============================Procedure================================
-- 1. selectUsernameAndScore
DELIMITER
$

DROP PROCEDURE IF EXISTS proc_select_username_score;

CREATE PROCEDURE proc_select_username_score()
BEGIN
    SELECT username, score
    FROM `users_schema`.`users`;
END $;
DELIMITER ;
-- CALL proc_select_user_score();

-- 2. selectUsernameAndPasswordByUsername
DELIMITER
$

DROP PROCEDURE IF EXISTS proc_select_username_password;

CREATE PROCEDURE proc_select_username_password(IN p_username NVARCHAR(45))
BEGIN
    SELECT username, password
    FROM `users_schema`.`users`
    WHERE username = p_username;
END $

DELIMITER ;
CALL proc_select_user_password('hoang');

-- 3. selectUsernameAndScoreByUsername
DELIMITER
$
CREATE PROCEDURE proc_select_username_score_by_username(
    IN p_username NVARCHAR(45)
)
BEGIN
    SELECT username, score
    FROM `users_schema`.`users`
    WHERE username = p_username;
END $;

DELIMITER ;
-- CALL proc_select_username_score_by_username('hoang');

-- 4. insert(username, password, score, reg_date)
DELIMITER
$
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
END $;

DELIMITER ; -- Khai báo dấu phân cách trở lại mặc định là dấu chấm phẩy ;
-- CALL proc_insert_user('hoang', '1', 10, '2024-03-03T02:25:42.098824600Z', @status);
-- SELECT @status as `insert status`;
-- 5. setSafeUpdate
DELIMITER
$
DROP PROCEDURE IF EXISTS proc_set_safe_update;
CREATE PROCEDURE proc_set_safe_update()
BEGIN
    SET
        SQL_SAFE_UPDATES = 0;
END $;
DELIMITER ;

-- 6. updateScoreByUsername
DELIMITER
$

DROP PROCEDURE IF EXISTS proc_update_score_by_username;
CREATE PROCEDURE proc_update_score_by_username(
    IN p_username NVARCHAR(45),
    IN p_score INT
)
BEGIN
    UPDATE `users_schema`.`users`
    SET score = p_score
    WHERE username = p_username;
END $;

DELIMITER ;
-- CALL proc_update_score_by_username('hoang', 42);









