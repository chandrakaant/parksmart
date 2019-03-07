create database parksmart;

use parksmart;

CREATE TABLE IF NOT EXISTS `parksmart`.`ps_user` (
  `u_id` INT(11) NOT NULL AUTO_INCREMENT,
  `u_name` VARCHAR(45) NOT NULL,
  `u_email` VARCHAR(45) NOT NULL,
  `u_password` VARCHAR(225) NULL DEFAULT NULL,
  `u_created_date` TIMESTAMP(6) NULL DEFAULT NULL,
  `u_modified_date` TIMESTAMP(6) NULL DEFAULT NULL,
  `u_token_expiry_date` TIMESTAMP(6) NULL DEFAULT NULL,
  `u_refresh_token` VARCHAR(350) NULL DEFAULT NULL,
  `u_is_active` TINYINT(1) NOT NULL,
  PRIMARY KEY (`u_id`))


