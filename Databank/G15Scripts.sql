-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
-- -----------------------------------------------------
-- Schema ID222177_g15
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema ID222177_g15
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `ID222177_g15` DEFAULT CHARACTER SET latin1 ;
USE `ID222177_g15` ;

-- -----------------------------------------------------
-- Table `ID222177_g15`.`rij`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `ID222177_g15`.`rij` (
  `idrij` VARCHAR(50) NOT NULL,
  `cel0` INT(11) NULL DEFAULT NULL,
  `cel1` INT(11) NULL DEFAULT NULL,
  `cel2` INT(11) NULL DEFAULT NULL,
  `cel3` INT(11) NULL DEFAULT NULL,
  `cel4` INT(11) NULL DEFAULT NULL,
  PRIMARY KEY (`idrij`),
  UNIQUE INDEX `idrij_UNIQUE` (`idrij` ASC))
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;


-- -----------------------------------------------------
-- Table `ID222177_g15`.`spel`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `ID222177_g15`.`spel` (
  `naamSpel` VARCHAR(45) NOT NULL,
  `naam` VARCHAR(45) NOT NULL,
  `speler` VARCHAR(45) NOT NULL,
  `moeilijkheidsgraad` VARCHAR(45) NOT NULL,
  `code` VARCHAR(500) NULL DEFAULT NULL,
  `uitdagingnr` INT(11) NULL DEFAULT NULL,
  `idRij0` VARCHAR(255) NULL DEFAULT NULL,
  `idRij1` VARCHAR(255) NULL DEFAULT NULL,
  `idRij2` VARCHAR(255) NULL DEFAULT NULL,
  `idRij3` VARCHAR(255) NULL DEFAULT NULL,
  `idRij4` VARCHAR(255) NULL DEFAULT NULL,
  `idRij5` VARCHAR(255) NULL DEFAULT NULL,
  `idRij6` VARCHAR(255) NULL DEFAULT NULL,
  `idRij7` VARCHAR(255) NULL DEFAULT NULL,
  `idRij8` VARCHAR(255) NULL DEFAULT NULL,
  `idRij9` VARCHAR(255) NULL DEFAULT NULL,
  `idRij10` VARCHAR(255) NULL DEFAULT NULL,
  `idRij11` VARCHAR(255) NULL DEFAULT NULL,
  PRIMARY KEY (`naamSpel`),
  UNIQUE INDEX `naamSpel_UNIQUE` (`naamSpel` ASC),
  INDEX `speler_idx` (`naam` ASC))
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;


-- -----------------------------------------------------
-- Table `ID222177_g15`.`speler`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `ID222177_g15`.`speler` (
  `spelersnaam` VARCHAR(25) NOT NULL,
  `wachtwoord` VARCHAR(25) NOT NULL,
  `gemakkelijk` INT(11) NULL DEFAULT NULL,
  `normaal` INT(11) NULL DEFAULT NULL,
  `moeilijk` INT(11) NULL DEFAULT NULL,
  PRIMARY KEY (`spelersnaam`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;


-- -----------------------------------------------------
-- Table `ID222177_g15`.`uitdaging`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `ID222177_g15`.`uitdaging` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `speler` VARCHAR(255) NOT NULL,
  `tegenspeler` VARCHAR(255) NOT NULL,
  `scoreSpeler` INT(11) NULL DEFAULT NULL,
  `scoreTegenspeler` INT(11) NULL DEFAULT NULL,
  `moeilijkheidsgraad` VARCHAR(255) NOT NULL,
  `teRadenCode` VARCHAR(255) NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
AUTO_INCREMENT = 55
DEFAULT CHARACTER SET = latin1;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
