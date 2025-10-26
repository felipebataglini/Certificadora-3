--
-- Grupo 7 - Script SQL - MeninasDigitaisIdeasManagement
--

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

CREATE SCHEMA IF NOT EXISTS `mdim` DEFAULT CHARACTER SET utf8 ;
USE `mdim` ;

CREATE TABLE IF NOT EXISTS `mdim`.`administrador` (
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

CREATE TABLE IF NOT EXISTS `mdim`.`voluntario` (
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

CREATE TABLE IF NOT EXISTS `mdim`.`externo` (
  `ext_id` INT NOT NULL,
  `ext_nome` VARCHAR(90) NOT NULL,
  `ext_email` VARCHAR(90) NOT NULL,
  `ext_senha` VARCHAR(90) NOT NULL,
  PRIMARY KEY (`ext_id`),
  UNIQUE INDEX `ext_email_UNIQUE` (`ext_email` ASC) VISIBLE)
ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS `mdim`.`ideia` (
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
    REFERENCES `mdim`.`voluntario` (`vol_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_ideia_administrador1`
    FOREIGN KEY (`adm_id`)
    REFERENCES `mdim`.`administrador` (`adm_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_ideia_externo1`
    FOREIGN KEY (`ext_id`)
    REFERENCES `mdim`.`externo` (`ext_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;

INSERT INTO `mdim`.`administrador` (`adm_id`, `adm_cpf`, `adm_nome`, `adm_telefone`, `adm_cargo`, `adm_email`, `adm_senha`) VALUES ('1', '12345678912', 'administrador', '11123456789', 'teste', 'admin@admin', 'administrador');
INSERT INTO `mdim`.`voluntario` (`vol_id`, `vol_cpf`, `vol_nome`, `vol_telefone`, `vol_email`, `vol_senha`) VALUES ('10', '12345678901', 'voluntario', '12345678901', 'voluntario@voluntario', 'voluntario');
INSERT INTO `mdim`.`externo` (`ext_id`, `ext_nome`, `ext_email`, `ext_senha`) VALUES ('100', 'externo', 'externo@externo', 'externo');