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
  `ur_id` INT NOT NULL AUTO_INCREMENT,
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


CREATE TABLE `parksmart`.`ps_vehicle` (
  `v_id` INT NOT NULL AUTO_INCREMENT,
  `v_number` VARCHAR(45) NULL DEFAULT NULL,
  `v_name` VARCHAR(45) NULL DEFAULT NULL,
  `v_location` VARCHAR(255) NULL DEFAULT NULL,
  `v_user_id` INT NULL DEFAULT NULL,
  `v_is_active` TINYINT NULL,
  `v_manufacturer_name` VARCHAR(255) NULL DEFAULT NULL,
  PRIMARY KEY (`v_id`),
  INDEX `u_id_idx` (`u_id` ASC),
  CONSTRAINT `v_user_id`
    FOREIGN KEY (`v_user_id`)
    REFERENCES `parksmart`.`ps_user` (`u_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);

CREATE TABLE `parksmart`.`ps_vehicle_details` (
  `v_id` INT NOT NULL AUTO_INCREMENT,
  `v_number` VARCHAR(45) NULL DEFAULT NULL,
  `v_name` VARCHAR(45) NULL DEFAULT NULL,
  `v_location` VARCHAR(255) NULL DEFAULT NULL,
  `v_user_id` INT NOT NULL,
  `v_is_active` TINYINT(1) NULL,
  `v_manufacturer_name` VARCHAR(255) NULL DEFAULT NULL,
  PRIMARY KEY (`v_id`))

CREATE TABLE `parksmart`.`ps_parking_area` (
  `parking_area_id` INT NOT NULL AUTO_INCREMENT,
  `parking_area_location` VARCHAR(45) NULL,
  PRIMARY KEY (`parking_area_id`));

ALTER TABLE `parksmart`.`ps_parking_area`
ADD COLUMN `parking_area` VARCHAR(45) NULL DEFAULT NULL AFTER `parking_area_location`;

CREATE TABLE `parksmart`.`ps_slot` (
  `s_id` INT NOT NULL AUTO_INCREMENT,
  `p_id` INT NULL DEFAULT NULL,
  `s_location` VARCHAR(255) NULL,
  PRIMARY KEY (`s_id`),
  INDEX `fk_p_idk_idx` (`p_id` ASC),
  CONSTRAINT `fk_p_idk`
    FOREIGN KEY (`p_id`)
    REFERENCES `parksmart`.`ps_parking_area` (`parking_area_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);

CREATE TABLE `parksmart`.`ps_parking` (
  `parking_id` INT NOT NULL AUTO_INCREMENT,
  `v_id` INT NULL DEFAULT NULL,
  `s_id` INT NULL DEFAULT NULL,
  `start_time` TIMESTAMP NULL DEFAULT NULL,
  `end_time` TIMESTAMP NULL DEFAULT NULL,
  `parking_is_active` TINYINT NULL,
  `amount` VARCHAR(45) NULL,
  PRIMARY KEY (`parking_id`),
  INDEX `fk_ps_parking_1_idx` (`v_id` ASC),
  INDEX `fk_ps_parking_2_idx` (`s_id` ASC),
  CONSTRAINT `fk_ps_parking_1`
    FOREIGN KEY (`v_id`)
    REFERENCES `parksmart`.`ps_vehicle` (`v_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_ps_parking_2`
    FOREIGN KEY (`s_id`)
    REFERENCES `parksmart`.`ps_slot` (`s_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);