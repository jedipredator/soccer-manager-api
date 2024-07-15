-- create root user and grant rights
CREATE USER 'sasad'@'%' IDENTIFIED BY 'local';
GRANT ALL ON *.* TO 'sasad'@'%';


CREATE DATABASE  IF NOT EXISTS `soccer` /*!40100 DEFAULT CHARACTER SET utf8mb4 */;
USE `soccer`;

SET SESSION sql_require_primary_key = 0;
