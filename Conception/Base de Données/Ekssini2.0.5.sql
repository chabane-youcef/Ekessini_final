-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema Ekessini
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema Ekessini
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `Ekessini` DEFAULT CHARACTER SET utf8 ;
USE `Ekessini` ;

-- -----------------------------------------------------
-- Table `Ekessini`.`Personne`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Ekessini`.`Personne` (
  `idPersonne` INT NOT NULL AUTO_INCREMENT,
  `nom` VARCHAR(45) NULL,
  `prenom` VARCHAR(45) NULL,
  `sexe` varchar(5) NULL,
  `numTel` VARCHAR(45) NULL,
  `dateNaiss` DATE NULL,
  `email` VARCHAR(45) NULL,
  PRIMARY KEY (`idPersonne`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `Ekessini`.`Donateur`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Ekessini`.`Donateur` (
  `idDonateur` INT NOT NULL,
  `motdePass` VARCHAR(45) NULL,
  `nbrAvertissement` int null,
  PRIMARY KEY (`idDonateur`),
  CONSTRAINT `fk_Donateur_Personne`
    FOREIGN KEY (`idDonateur`)
    REFERENCES `Ekessini`.`Personne` (`idPersonne`)
    ON DELETE CASCADE
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `Ekessini`.`Necessiteux`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Ekessini`.`Necessiteux` (
  `idNecessiteux` INT NOT NULL,
  `sitFamiliale` VARCHAR(45) NULL,
  `paixMois` INT NULL,
  `NbrEnfants` INT NULL,
  PRIMARY KEY (`idNecessiteux`),
  CONSTRAINT `fk_Necessiteux_Personne1`
    FOREIGN KEY (`idNecessiteux`)
    REFERENCES `Ekessini`.`Personne` (`idPersonne`)
    ON DELETE CASCADE
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `Ekessini`.`Necessiteux_Admis`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Ekessini`.`Necessiteux_Admis` (
  `idNecessiteuxAdmis` INT NOT NULL,
  `priorite` CHAR(1) NULL,
  `motdePasse` VARCHAR(45) NULL,
  `nbrAvertissement` int null,
  PRIMARY KEY (`idNecessiteuxAdmis`),
  CONSTRAINT `fk_Necessiteux_Admis_Necessiteux1`
    FOREIGN KEY (`idNecessiteuxAdmis`)
    REFERENCES `Ekessini`.`Necessiteux` (`idNecessiteux`)
    ON DELETE CASCADE
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `Ekessini`.`Don`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Ekessini`.`Don` (
  `idDon` INT NOT NULL AUTO_INCREMENT,
  `type` VARCHAR(45) NULL,
  `genre` VARCHAR(10) NULL,
  PRIMARY KEY (`idDon`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `Ekessini`.`Depot`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Ekessini`.`Depot` (
  `idDepot` INT NOT NULL AUTO_INCREMENT,
  `localisation` VARCHAR(45) NULL,
  PRIMARY KEY (`idDepot`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `Ekessini`.`Demande`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Ekessini`.`Demande` (
  `idDemande` INT NOT NULL AUTO_INCREMENT,
  `idDon` INT NOT NULL,
  `idNecessiteuxAdmis` INT NOT NULL,
  `idDepot` INT NOT NULL,
  `quantite` INT NULL,
  `date` DATE NULL,
  `etat` VARCHAR(45) NULL,
  PRIMARY KEY (`idDemande`, `idDon`, `idNecessiteuxAdmis`, `idDepot`),
  INDEX `fk_Demande_Don1_idx` (`idDon` ASC) VISIBLE,
  INDEX `fk_Demande_Depot1_idx` (`idDepot` ASC) VISIBLE,
  INDEX `fk_Demande_Necessiteux_Admis1_idx` (`idNecessiteuxAdmis` ASC) VISIBLE,
  CONSTRAINT `fk_Demande_Don1`
    FOREIGN KEY (`idDon`)
    REFERENCES `Ekessini`.`Don` (`idDon`)
    ON DELETE CASCADE
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Demande_Depot1`
    FOREIGN KEY (`idDepot`)
    REFERENCES `Ekessini`.`Depot` (`idDepot`)
    ON DELETE CASCADE
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Demande_Necessiteux_Admis1`
    FOREIGN KEY (`idNecessiteuxAdmis`)
    REFERENCES `Ekessini`.`Necessiteux_Admis` (`idNecessiteuxAdmis`)
    ON DELETE CASCADE
    ON UPDATE NO ACTION)
ENGINE = InnoDB;




-- -----------------------------------------------------
-- Table `Ekessini`.`Donation`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Ekessini`.`Donation` (
  `idDonation` INT NOT NULL AUTO_INCREMENT,
  `idDonateur` INT NOT NULL,
  `idDon` INT NOT NULL,
  `idDepot` INT NOT NULL,
  `quantite` INT NULL,
  `date` DATE NULL,
  `period` int NULL,
  `etat` VARCHAR(45) NULL,
  PRIMARY KEY (`idDonation`, `idDonateur`, `idDon`, `idDepot`),
  INDEX `fk_Donation_Donateur1_idx` (`idDonateur` ASC) VISIBLE,
  INDEX `fk_Donation_Don1_idx` (`idDon` ASC) VISIBLE,
  INDEX `fk_Donation_Depot1_idx` (`idDepot` ASC) VISIBLE,
  CONSTRAINT `fk_Donation_Donateur1`
    FOREIGN KEY (`idDonateur`)
    REFERENCES `Ekessini`.`Donateur` (`idDonateur`)
    ON DELETE CASCADE
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Donation_Don1`
    FOREIGN KEY (`idDon`)
    REFERENCES `Ekessini`.`Don` (`idDon`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Donation_Depot1`
    FOREIGN KEY (`idDepot`)
    REFERENCES `Ekessini`.`Depot` (`idDepot`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `Ekessini`.`Chambre`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Ekessini`.`Chambre` (
  `idChambre` INT NOT NULL AUTO_INCREMENT,
  `idDepot` INT NOT NULL,
  `type` VARCHAR(45) NULL,
  `max` int NULL,
  PRIMARY KEY (`idChambre`, `idDepot`),
  INDEX `fk_Chambre_Depot1_idx` (`idDepot` ASC) VISIBLE,
  CONSTRAINT `fk_Chambre_Depot1`
    FOREIGN KEY (`idDepot`)
    REFERENCES `Ekessini`.`Depot` (`idDepot`)
    ON DELETE CASCADE
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `Ekessini`.`Emplacement`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Ekessini`.`Emplacement` (
  `idEmplacement` INT NOT NULL AUTO_INCREMENT,
  `idChambre` INT NOT NULL,
  `idDepot` INT NOT NULL,
  `idDon` INT NULL,
  `idDonateur` INT NULL,
  `typeA` VARCHAR(50) NULL,
  `etat` VARCHAR(45) NULL,
  `status` TINYINT NULL,
  PRIMARY KEY (`idEmplacement`, `idChambre`, `idDepot`),
  INDEX `fk_Emplacement_Chambre1_idx` (`idChambre` ASC, `idDepot` ASC) VISIBLE,
  INDEX `fk_Emplacement_Don1_idx` (`idDon` ASC) VISIBLE,
  CONSTRAINT `fk_Emplacement_Chambre1`
    FOREIGN KEY (`idChambre` , `idDepot`)
    REFERENCES `Ekessini`.`Chambre` (`idChambre` , `idDepot`)
    ON DELETE CASCADE
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Emplacement_Don1`
    FOREIGN KEY (`idDon`)
    REFERENCES `Ekessini`.`Don` (`idDon`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `Ekessini`.`DonationNonP`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Ekessini`.`DonationNonP` (
  `idDonationNonP` INT NOT NULL AUTO_INCREMENT,
  `idPersonne` INT NOT NULL,
  `idDepot` INT NOT NULL,
  `idDon` INT NOT NULL,
  `quantite` INT NULL,
  `date` DATE NULL,
  `etat` VARCHAR(45) NULL,
  PRIMARY KEY (`idDonationNonP`, `idPersonne`, `idDepot`, `idDon`),
  INDEX `fk_DonationNonP_Personne1_idx` (`idPersonne` ASC) VISIBLE,
  INDEX `fk_DonationNonP_Depot1_idx` (`idDepot` ASC) VISIBLE,
  INDEX `fk_DonationNonP_Don1_idx` (`idDon` ASC) VISIBLE,
  CONSTRAINT `fk_DonationNonP_Personne1`
    FOREIGN KEY (`idPersonne`)
    REFERENCES `Ekessini`.`Personne` (`idPersonne`)
    ON DELETE CASCADE
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_DonationNonP_Depot1`
    FOREIGN KEY (`idDepot`)
    REFERENCES `Ekessini`.`Depot` (`idDepot`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_DonationNonP_Don1`
    FOREIGN KEY (`idDon`)
    REFERENCES `Ekessini`.`Don` (`idDon`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

-- -----------------------------------------------------
-- Table `Ekessini`.`Données statique de donation dans l'assossiation`
-- -----------------------------------------------------
/*insert into table Don*/
insert	into	Don(type,genre)	VALUES	('T-shirt','m'),('T-shirt','f'),
										('pantalon','m'),('pantalon','f'),
										('chaussure','m'),('chaussure','f');


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
