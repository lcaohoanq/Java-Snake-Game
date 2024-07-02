![Animation](https://github.com/lcaohoanq/Java-Snake-Game/assets/136492579/1c4782eb-ccf4-437e-a6b1-900ec68a58a3)
# Setup
![image](https://github.com/lcaohoanq/Java-Snake-Game/assets/136492579/e197c7f3-d4eb-4ba1-ba20-33fef4abbcee)
  - `JDK`   : need `JDK 18` to run this application.
  - `Docker`
```bash
# init connection
chmod +x init.sh
./init-db.sh
```

```bash
docker-compose up -d
```

```bash
# verify connection
mysql -h 127.0.0.1 -P 3307 -u lcaohoanq -p
use snake_game_app;
select * from users;
```
  - `MySql` : check my table structure `db.sql` at `src/main/resources`
```shell
CREATE DATABASE IF NOT EXISTS snake_game_app;

USE snake_game_app;

CREATE TABLE IF NOT EXISTS users (
    user_id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(45) NOT NULL,
    password VARCHAR(50) NOT NULL,
    score INT DEFAULT 0,
    created TIMESTAMP NOT NULL DEFAULT NOW()
);

INSERT INTO users (username, password) VALUES
('hoang', '$31$16$tI3b31pfkwiVfW7u1_4LYcyzameV6gGm_fto0tEIBCI'),
('toilaluu', '$31$16$EwBZ6WEqPfIPaeynRV63Nn8UhLOSucwkC7WBY7uAchk'),
('duong', '$31$16$A2fFe83zAcbXOMeZ7NiMBnVOCD7actfU68aIkZE1rEA'),
('huy', '$31$16$5uBNgudXH6BsSz4N7GbbrfjrqAMGh6TcoxDs6ruwnPI'),
('bao', '$31$16$CrqpO_WzDPLWh9jCpPcKeqKv1VEHswyq3d7G6wZQpQg'),
('minhnhu', '$31$16$xEeCSM_NeDhbLzpvpXAJpxdxGUR-dGI46UEH1paLa90');
```
  - `.env`  : create fill the .env file below as your configuration
```shell
DB_URL=jdbc:mysql://<db_host:port>/snake_game_app
DB_USERNAME=<db_username>
DB_PASSWORD=<db_password>
TEST_USERNAME=hoang
TEST_PASSWORD=Luucaohoang1604^^
TEST_ACCOUNT=minhnhu
TEST_PASSWORD_DB=$31$16$xEeCSM_NeDhbLzpvpXAJpxdxGUR-dGI46UEH1paLa90
```
# The web-version will comming soon...

## route
- /users/register

![image](https://github.com/lcaohoanq/Java-Snake-Game/assets/136492579/6eac4f42-24d2-486c-8ab5-527dee6021b2)
