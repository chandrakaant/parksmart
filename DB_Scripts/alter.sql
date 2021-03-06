ALTER TABLE `parksmart`.`ps_user`
ADD COLUMN `u_phone` VARCHAR(45) NULL DEFAULT NULL AFTER `u_is_active`,
ADD COLUMN `u_location` VARCHAR(255) NULL DEFAULT NULL AFTER `u_phone`;

ALTER TABLE `parksmart`.`ps_user`
MODIFY COLUMN `u_name` VARCHAR(45) NULL


ALTER TABLE `parksmart`.`ps_parking_area`
CHANGE COLUMN `parking_area_location` `parking_area_lat` FLOAT(10,6) NULL DEFAULT NULL ,
ADD COLUMN `parking_area_long` FLOAT(10,6) NULL DEFAULT NULL AFTER `parking_area`,
ADD COLUMN `parking_area_is_active` TINYINT NULL AFTER `parking_area_long`;

ALTER TABLE `parksmart`.`ps_slot`
CHANGE COLUMN `s_location` `s_lat` FLOAT(10,6) NULL DEFAULT NULL ,
ADD COLUMN `s_long` FLOAT(10,6) NULL DEFAULT NULL AFTER `s_lat`;

ALTER TABLE `parksmart`.`ps_slot`
ADD COLUMN `s_active` TINYINT NULL AFTER `s_long`;


ALTER TABLE `parksmart`.`ps_user`
	modify `u_phone` Varchar(20) NOT NULL
