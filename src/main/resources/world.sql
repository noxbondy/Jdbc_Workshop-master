-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema todoit
-- -----------------------------------------------------personperson

-- -----------------------------------------------------
-- Schema todoit
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `todoit` DEFAULT CHARACTER SET utf8 ;
-- -----------------------------------------------------
-- Schema todoit
-- -----------------------------------------------------
USE `todoit` ;

-- -----------------------------------------------------
-- Table `todoit`.`person`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `todoit`.`person` (
  `person_id` INT NOT NULL AUTO_INCREMENT,
  `first_name` VARCHAR(255) NULL,
  `last_name` VARCHAR(255) NULL,
  PRIMARY KEY (`person_id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `todoit`.`todo_item`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `todoit`.`todo_item` (
  `todo_id` INT NOT NULL AUTO_INCREMENT,
  `title` VARCHAR(50) NULL,
  `description` VARCHAR(1000) NULL,
  `deadline` DATE NULL,
  `done` TINYINT NULL DEFAULT 0,
  `assignee_id` INT NULL,
  PRIMARY KEY (`todo_id`),
  INDEX `fk_todo_item_person_idx` (`assignee_id` ASC) VISIBLE,
  CONSTRAINT `fk_todo_item_person`
    FOREIGN KEY (`assignee_id`)
    REFERENCES `todoit`.`person` (`person_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;


desc todo_item;
use todoit;
select * from todo_item;
use world;
select * from city ;

