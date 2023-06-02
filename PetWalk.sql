-- MySQL Workbench Forward Engineering
SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema PetWalk
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema PetWalk
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `PetWalk` DEFAULT CHARACTER SET utf8 ;
USE `PetWalk` ;

-- -----------------------------------------------------
-- Table `PetWalk`.`KORISNIK`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `PetWalk`.`KORISNIK` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `Ime` VARCHAR(45) NOT NULL,
  `Prezime` VARCHAR(45) NOT NULL,
  `Korisnicko_ime` VARCHAR(45) NOT NULL,
  `Lozinka` VARCHAR(100) NOT NULL,
  `Email` VARCHAR(45) NOT NULL,
  `Broj_telefona` VARCHAR(45) NOT NULL,
  `photo` VARCHAR(225),
  `opis` VARCHAR(225),
  `role` tinyint(4) not null,
  `status` tinyint(4) not null,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;

-- -----------------------------------------------------
-- Table `PetWalk`.`USLUGA`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `PetWalk`.`USLUGA` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `Naziv` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;

INSERT INTO `petwalk`.`usluga` (`Naziv`) VALUES ('Čuvanje');
INSERT INTO `petwalk`.`usluga` (`Naziv`) VALUES ('Šetanje');
INSERT INTO `petwalk`.`usluga` (`Naziv`) VALUES ('Kupanje');
INSERT INTO `petwalk`.`usluga` (`Naziv`) VALUES ('Šišanje');

-- -----------------------------------------------------
-- Table `PetWalk`.`CIJENA`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `PetWalk`.`CIJENA` (
  `id` INT NOT NULL auto_increment,
  `cijena` DECIMAL NOT NULL,
  `usluga_id` INT NOT NULL,
  `korisnik_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_CIJENA_USLUGA1_idx` (`usluga_id` ASC) VISIBLE,
  INDEX `fk_CIJENA_KORISNIK1_idx` (`korisnik_id` ASC) VISIBLE,
  CONSTRAINT `fk_CIJENA_USLUGA1`
    FOREIGN KEY (`usluga_id`)
    REFERENCES `PetWalk`.`USLUGA` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_CIJENA_KORISNIK1`
    FOREIGN KEY (`korisnik_id`)
    REFERENCES `PetWalk`.`KORISNIK` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

-- -----------------------------------------------------
-- Table `PetWalk`.`MJESTO`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `PetWalk`.`MJESTO` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `Naziv` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;

INSERT INTO `petwalk`.`mjesto` (`Naziv`) VALUES ('Borik');
INSERT INTO `petwalk`.`mjesto` (`Naziv`) VALUES ('Centar');
INSERT INTO `petwalk`.`mjesto` (`Naziv`) VALUES ('Mejdan');
INSERT INTO `petwalk`.`mjesto` (`Naziv`) VALUES ('Starčevica');
INSERT INTO `petwalk`.`mjesto` (`Naziv`) VALUES ('Lauš');
INSERT INTO `petwalk`.`mjesto` (`Naziv`) VALUES ('Nova Varoš');
INSERT INTO `petwalk`.`mjesto` (`Naziv`) VALUES ('Rosulje');
INSERT INTO `petwalk`.`mjesto` (`Naziv`) VALUES ('Lazarevo');
INSERT INTO `petwalk`.`mjesto` (`Naziv`) VALUES ('Paprikovac');
INSERT INTO `petwalk`.`mjesto` (`Naziv`) VALUES ('Petrićevac');
INSERT INTO `petwalk`.`mjesto` (`Naziv`) VALUES ('Hisete');

-- -----------------------------------------------------
-- Table `PetWalk`.`LOKACIJA`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `PetWalk`.`LOKACIJA` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `mjesto_id` INT NOT NULL,
  `korisnik_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_LOKACIJA_MJESTO1_idx` (`mjesto_id` ASC) VISIBLE,
  CONSTRAINT `fk_LOKACIJA_MJESTO1`
    FOREIGN KEY (`mjesto_id`)
    REFERENCES `PetWalk`.`MJESTO` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
    CONSTRAINT `fk_LOKACIJA_KORISNIK1`
     FOREIGN KEY (`korisnik_id`)
    REFERENCES `PetWalk`.`KORISNIK` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

-- -----------------------------------------------------
-- Table `PetWalk`.`VRSTA`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `PetWalk`.`VRSTA` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `Naziv` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;

INSERT INTO `petwalk`.`vrsta` (`Naziv`) VALUES ('Pas');
INSERT INTO `petwalk`.`vrsta` (`Naziv`) VALUES ('Mačka');
INSERT INTO `petwalk`.`vrsta` (`Naziv`) VALUES ('Hrčak');
INSERT INTO `petwalk`.`vrsta` (`Naziv`) VALUES ('Ptica');
INSERT INTO `petwalk`.`vrsta` (`Naziv`) VALUES ('Ribica');

-- -----------------------------------------------------
-- Table `PetWalk`.`PONUDA`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `PetWalk`.`PONUDA` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `vrsta_id` INT NOT NULL,
  `usluga_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_PONUDA_VRSTA1_idx` (`vrsta_id` ASC) VISIBLE,
  INDEX `fk_PONUDA_USLUGA1_idx` (`usluga_id` ASC) VISIBLE,
  CONSTRAINT `fk_PONUDA_VRSTA1`
    FOREIGN KEY (`vrsta_id`)
    REFERENCES `PetWalk`.`VRSTA` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
 CONSTRAINT `fk_PONUDA_USLUGA1`
    FOREIGN KEY (`usluga_id`)
    REFERENCES `PetWalk`.`USLUGA` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

-- -----------------------------------------------------
-- Table `PetWalk`.`LJUBIMAC`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `PetWalk`.`LJUBIMAC` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `Ime` VARCHAR(45) NOT NULL,
  `Slika` VARCHAR(255) NOT NULL,
  `Opis` VARCHAR(225) NULL,
  `korisnik_id` INT NOT NULL,
  `vrsta_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_LJUBIMAC_KORISNIK1_idx` (`korisnik_id` ASC) VISIBLE,
  INDEX `fk_LJUBIMAC_VRSTA1_idx` (`vrsta_id` ASC) VISIBLE,
  CONSTRAINT `fk_LJUBIMAC_KORISNIK1`
    FOREIGN KEY (`korisnik_id`)
    REFERENCES `PetWalk`.`KORISNIK` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_LJUBIMAC_VRSTA1`
    FOREIGN KEY (`vrsta_id`)
    REFERENCES `PetWalk`.`VRSTA` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

-- -----------------------------------------------------
-- Table `PetWalk`.`RECENZIJA`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `PetWalk`.`RECENZIJA` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `Komentar` VARCHAR(225) NOT NULL,
  `Ocjena` INT NULL,
  `od_id` INT NOT NULL,
  `za_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_RECENZIJA_KORISNIK1_idx` (`od_id` ASC) VISIBLE,
  INDEX `fk_RECENZIJA_KORISNIK2_idx` (`za_id` ASC) VISIBLE,
  CONSTRAINT `fk_RECENZIJA_KORISNIK1`
    FOREIGN KEY (`od_id`)
    REFERENCES `PetWalk`.`KORISNIK` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_RECENZIJA_KORISNIK2`
    FOREIGN KEY (`za_id`)
    REFERENCES `PetWalk`.`KORISNIK` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

-- -----------------------------------------------------
-- Table `mydb`.`IZVJESTAJ`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `PetWalk`.`IZVJESTAJ` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `Sadrzaj` VARCHAR(225) NOT NULL,
  `korisnik_id` INT NOT NULL,
  `ljubimac_id` INT NOT NULL,
  PRIMARY KEY (`id`),
    INDEX `fk_IZVJESTAJ_KORISNIK1_idx` (`korisnik_id` ASC) VISIBLE,
 CONSTRAINT `fk_IZVJESTAJ_KORISNIK1`
    FOREIGN KEY (`korisnik_id`)
    REFERENCES `PetWalk`.`KORISNIK` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
 CONSTRAINT `fk_IZVJESTAJ_LJUBIMAC`
    FOREIGN KEY (`ljubimac_id`)
    REFERENCES `PetWalk`.`LJUBIMAC` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS `PetWalk`.`PROBLEM` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `Sadrzaj` VARCHAR(225) NOT NULL,
  `korisnik_id` INT NOT NULL,
  PRIMARY KEY (`id`),
    INDEX `fk_PROBLEM_KORISNIK1_idx` (`korisnik_id` ASC) VISIBLE,
    CONSTRAINT `fk_PROBLEM_KORISNIK1`
    FOREIGN KEY (`korisnik_id`)
    REFERENCES `PetWalk`.`KORISNIK` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;