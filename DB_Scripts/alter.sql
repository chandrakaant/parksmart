ALTER TABLE `parksmart`.`ps_user`
ADD COLUMN `u_phone` VARCHAR(45) NULL DEFAULT NULL AFTER `u_is_active`,
ADD COLUMN `u_location` VARCHAR(255) NULL DEFAULT NULL AFTER `u_phone`;

ALTER TABLE `parksmart`.`ps_user`
MODIFY COLUMN `u_name` VARCHAR(45) NULL


ALTER TABLE `parksmart`.`ps_user`
	modify `u_phone` Varchar(20) NOT NULL
