USE users_schema;

SELECT *
FROM users
WHERE username = 'duong';

DESCRIBE users;

-- lay score
SELECT username, score
FROM users;

-- update by id and name
UPDATE users_schema.users
SET score = 8
WHERE username = 'admin'
  AND id = '2';

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
SET SQL_SAFE_UPDATES = 0;
UPDATE users_schema.users
SET score = 100
WHERE username = 'fpt';

DROP TABLE users;

-- alter table for update password nvarchar(45) to 50
ALTER TABLE users
    MODIFY COLUMN password NVARCHAR(50);

INSERT INTO `users_schema`.`users` (`username`, `password`, `score`)
VALUES ('hoang', '1', 99);

SELECT username, password
FROM users
WHERE username = 'hoang';

-- Update password
-- update by id and name
UPDATE users_schema.users
SET password = 'admin'
WHERE username = 'admin'
  AND id = '4';
