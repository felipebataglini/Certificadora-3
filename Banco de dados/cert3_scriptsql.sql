--
-- Grupo 7 - Script SQL - MeninasDigitaisIdeasManagement
--

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

CREATE SCHEMA IF NOT EXISTS `mdmngt` DEFAULT CHARACTER SET utf8 ;
USE `mdmngt` ;

CREATE TABLE IF NOT EXISTS `mdmngt`.`administrador` (
  `adm_id` INT NOT NULL,
  `adm_cpf` BIGINT(11) NOT NULL,
  `adm_nome` VARCHAR(90) NOT NULL,
  `adm_telefone` BIGINT(11) NULL,
  `adm_cargo` VARCHAR(45) NOT NULL,
  `adm_email` VARCHAR(90) NOT NULL,
  `adm_senha` VARCHAR(90) NOT NULL,
  PRIMARY KEY (`adm_id`),
  UNIQUE INDEX `adm_cpf_UNIQUE` (`adm_cpf` ASC) VISIBLE,
  UNIQUE INDEX `adm_email_UNIQUE` (`adm_email` ASC) VISIBLE)
ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS `mdmngt`.`voluntario` (
  `vol_id` INT NOT NULL,
  `vol_cpf` BIGINT(11) NOT NULL,
  `vol_nome` VARCHAR(90) NOT NULL,
  `vol_telefone` BIGINT(11) NULL,
  `vol_email` VARCHAR(90) NOT NULL,
  `vol_senha` VARCHAR(90) NOT NULL,
  PRIMARY KEY (`vol_id`),
  UNIQUE INDEX `vol_cpf_UNIQUE` (`vol_cpf` ASC) VISIBLE,
  UNIQUE INDEX `vol_email_UNIQUE` (`vol_email` ASC) VISIBLE)
ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS `mdmngt`.`externo` (
  `ext_id` INT NOT NULL,
  `ext_nome` VARCHAR(90) NOT NULL,
  `ext_email` VARCHAR(90) NOT NULL,
  `ext_senha` VARCHAR(90) NOT NULL,
  PRIMARY KEY (`ext_id`),
  UNIQUE INDEX `ext_email_UNIQUE` (`ext_email` ASC) VISIBLE)
ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS `mdmngt`.`ideia` (
  `ide_id` INT NOT NULL,
  `ide_titulo` VARCHAR(45) NOT NULL,
  `ide_conteudo` VARCHAR(1000) NOT NULL,
  `ide_status` VARCHAR(45) NOT NULL,
  `vol_id` INT NULL,
  `adm_id` INT NULL,
  `ext_id` INT NULL,
  PRIMARY KEY (`ide_id`),
  INDEX `fk_ideia_voluntario_idx` (`vol_id` ASC) VISIBLE,
  INDEX `fk_ideia_administrador1_idx` (`adm_id` ASC) VISIBLE,
  INDEX `fk_ideia_externo1_idx` (`ext_id` ASC) VISIBLE,
  CONSTRAINT `fk_ideia_voluntario`
    FOREIGN KEY (`vol_id`)
    REFERENCES `mdmngt`.`voluntario` (`vol_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_ideia_administrador1`
    FOREIGN KEY (`adm_id`)
    REFERENCES `mdmngt`.`administrador` (`adm_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_ideia_externo1`
    FOREIGN KEY (`ext_id`)
    REFERENCES `mdmngt`.`externo` (`ext_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;