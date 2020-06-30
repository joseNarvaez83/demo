DROP DATABASE IF EXISTS demoservice;
DROP USER IF EXISTS `demo_user`@`%`;
CREATE DATABASE IF NOT EXISTS demoservice CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
CREATE USER IF NOT EXISTS `demo_user`@`%` IDENTIFIED WITH mysql_native_password BY 'password';
GRANT SELECT, INSERT, UPDATE, DELETE, CREATE, DROP, REFERENCES , INDEX, ALTER, EXECUTE, CREATE VIEW, SHOW VIEW,
    CREATE ROUTINE, ALTER ROUTINE, EVENT, TRIGGER ON `demoservice`.* TO `demo_user`@`%`;
FLUSH PRIVILEGES;