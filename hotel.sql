-- MySQL dump 10.13  Distrib 5.6.24, for Win32 (x86)
--
-- Host: localhost    Database: hotel
-- ------------------------------------------------------
-- Server version	5.6.26-log

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `categorie`
--
drop Database IF EXISTS hotel;
CREATE database hotel;
use hotel;
DROP TABLE IF EXISTS `categorie`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `categorie` (
  `idCategorie` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `nom` varchar(20) DEFAULT NULL,
  `prix` double DEFAULT NULL,
  PRIMARY KEY (`idCategorie`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `chambre`
--

DROP TABLE IF EXISTS `chambre`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `chambre` (
  `idChambre` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `numeroChambre` int(11) DEFAULT NULL,
  `etage` int(11) DEFAULT NULL,
  `checked` tinyint(1) DEFAULT NULL,
   `description` text,
  `id_categorie` int(10) unsigned DEFAULT NULL,
  PRIMARY KEY (`idChambre`),
  KEY `fk_id_caregorie_idx` (`id_categorie`),
  CONSTRAINT `fk_id_caregorie` FOREIGN KEY (`id_categorie`) REFERENCES `categorie` (`idCategorie`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;



--
-- Table structure for table `client`
--

DROP TABLE IF EXISTS `client`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `client` (
  `idClient` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `nom` varchar(20) DEFAULT NULL,
  `prenom` varchar(20) DEFAULT NULL,
  `dateNaissance` date DEFAULT NULL,
  `tel` varchar(10) DEFAULT NULL,
  `numeroPieceIdentite` varchar(25) DEFAULT NULL,
  PRIMARY KEY (`idClient`),
  KEY `idx_nom_prenoù` (`nom`,`prenom`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `facture`
--

DROP TABLE IF EXISTS `facture`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `facture` (
  `idFacture` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `datePaiement` date DEFAULT NULL,
  `somme` double DEFAULT NULL,
  PRIMARY KEY (`idFacture`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;
--
-- Table structure for table `utilisateur`
--
DROP TABLE IF EXISTS `utilisateur`;

CREATE TABLE `utilisateur` (
  `idUser` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `nom` varchar(20) DEFAULT NULL,
  `prenom` varchar(20) DEFAULT NULL,
  `tel` varchar(15) DEFAULT NULL,
  `type` varchar(2) NOT NULL,
  `username` varchar(20) NOT NULL,
  `password` varchar(64) NOT NULL,
  PRIMARY KEY (`idUser`),
  UNIQUE KEY `username_UNIQUE` (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


--
-- Table structure for table `reservation`
--

DROP TABLE IF EXISTS `reservation`;

CREATE TABLE `reservation` (
  `idReservation` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `dateReservation` date DEFAULT NULL,
  `dateArrive` date DEFAULT NULL,
  `dateSortie` date DEFAULT NULL,
  `id_facture` int(11) unsigned DEFAULT NULL,
  `id_user` int(11) unsigned DEFAULT NULL,
  `id_client` int(11) unsigned DEFAULT NULL,
  PRIMARY KEY (`idReservation`),
  KEY `fk_id_facture_idx` (`id_facture`),
  KEY `fk_id_client_idx` (`id_client`),
  KEY `fk_id_user_idx` (`id_user`),
  CONSTRAINT `fk_id_client` FOREIGN KEY (`id_client`) REFERENCES `client` (`idClient`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_id_facture` FOREIGN KEY (`id_facture`) REFERENCES `facture` (`idFacture`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_id_user` FOREIGN KEY (`id_user`) REFERENCES `utilisateur` (`idUser`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Table structure for table `chambre_reservation`
--

DROP TABLE IF EXISTS `chambre_reservation`;
CREATE TABLE `chambre_reservation` (
  `id_chambre` int(10) unsigned NOT NULL,
  `id_reservation` int(10) unsigned NOT NULL,
  PRIMARY KEY (`id_chambre`,`id_reservation`),
  KEY `fk_id_reservation_idx` (`id_reservation`),
  CONSTRAINT `fk_id_chambre` FOREIGN KEY (`id_chambre`) REFERENCES `chambre` (`idChambre`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_id_reservation` FOREIGN KEY (`id_reservation`) REFERENCES `reservation` (`idReservation`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


INSERT INTO Utilisateur(type,username,password)VALUES('A','admin','fb001dfcffd1c899f3297871406242f097aecf1a5342ccf3ebcd116146188e4b');
-- Dump completed on 2017-05-18 18:33:49
