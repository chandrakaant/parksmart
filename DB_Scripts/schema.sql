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

CREATE TABLE IF NOT EXISTS `parksmart`.`ps_role` (
  `r_id` INT(11) NOT NULL AUTO_INCREMENT,
  `r_name` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`r_id`))
ENGINE = InnoDB
AUTO_INCREMENT = 3
DEFAULT CHARACTER SET = latin1;

CREATE TABLE `parksmart`.`ps_user_role` (
  `ur_id` INT NOT NULL,
  `ur_user_id` INT NOT NULL,
  `ur_role_id` INT NOT NULL,
  PRIMARY KEY (`ur_id`),
  INDEX `fk_ps_user_role_1_idx` (`ur_user_id` ASC),
  INDEX `fk_ps_user_role_2_idx` (`ur_role_id` ASC),
  CONSTRAINT `fk_ps_user_role_1`
    FOREIGN KEY (`ur_user_id`)
    REFERENCES `parksmart`.`ps_user` (`u_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_ps_user_role_2`
    FOREIGN KEY (`ur_role_id`)
    REFERENCES `parksmart`.`ps_role` (`r_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);


