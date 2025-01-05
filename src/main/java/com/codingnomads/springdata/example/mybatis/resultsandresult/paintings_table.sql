CREATE TABLE `mybatis`.`paintings` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  `artist_name` VARCHAR(45) NOT NULL,
  `medium` VARCHAR(45) NOT NULL,
  `year_created` INT NOT NULL,
  PRIMARY KEY (`id`));