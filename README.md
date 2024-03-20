![Animation](https://github.com/lcaohoanq/Java-Snake-Game/assets/136492579/1c4782eb-ccf4-437e-a6b1-900ec68a58a3)
# Setup
![image](https://github.com/lcaohoanq/Java-Snake-Game/assets/136492579/e197c7f3-d4eb-4ba1-ba20-33fef4abbcee)
  - `JDK`   : need `JDK 18` to run this application.
  - `MySql` : check my table structure `db.sql` at `src/main/resources`
```shell
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
```
  - `.env`  : create fill the .env file below as your configuration
```shell
DB_URL=jdbc:mysql://<your_port>/users_schema
DB_USERNAME=<your_db_username>
DB_PASSWORD=<your_db_password>
TEST_USERNAME=hoang
TEST_PASSWORD=Luucaohoang1604^^
TEST_ACCOUNT=minhnhu
TEST_PASSWORD_DB=$31$16$xEeCSM_NeDhbLzpvpXAJpxdxGUR-dGI46UEH1paLa90
```
# The web-version will comming soon...
