-- MySQL dump 10.13  Distrib 5.6.24, for Win32 (x86)
--
-- Host: localhost    Database: hotel
-- ------------------------------------------------------
-- Server version	5.6.26-log

DROP DATABASE IF EXISTS hotel;
create database hotel;
use hotel;
--
-- Table structure for table `categorie`
--

DROP TABLE IF EXISTS `categorie`;
CREATE TABLE `categorie` (
  `idCategorie` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `nom` varchar(20) DEFAULT NULL,
  `prix` double DEFAULT NULL,
  `description` text,
  PRIMARY KEY (`idCategorie`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Table structure for table `chambre`
--

DROP TABLE IF EXISTS `chambre`;
CREATE TABLE `chambre` (
  `idChambre` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `numeroChambre` int(11) DEFAULT NULL,
  `etage` int(11) DEFAULT NULL,
  `checked` tinyint(1) DEFAULT NULL,
  `id_categorie` int(10) unsigned DEFAULT NULL,
  PRIMARY KEY (`idChambre`),
  UNIQUE KEY `unique_chambre_etage` (`etage`,`numeroChambre`),
  KEY `fk_id_caregorie_idx` (`id_categorie`),
  CONSTRAINT `fk_id_caregorie` FOREIGN KEY (`id_categorie`) REFERENCES `categorie` (`idCategorie`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


-- Table structure for table `client`
--

DROP TABLE IF EXISTS `client`;
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

--
-- Table structure for table `facture`
--

DROP TABLE IF EXISTS `facture`;
CREATE TABLE `facture` (
  `idFacture` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `datePaiement` date DEFAULT NULL,
  `somme` double DEFAULT NULL,
  PRIMARY KEY (`idFacture`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Table structure for table `usertype`
--

DROP TABLE IF EXISTS `usertype`;
CREATE TABLE `usertype` (
  `idType` varchar(2) NOT NULL,
  `description` varchar(15) DEFAULT NULL,
  PRIMARY KEY (`idType`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

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
  UNIQUE KEY `username_UNIQUE` (`username`),
  KEY `fk_type_idx` (`type`),
  CONSTRAINT `fk_type` FOREIGN KEY (`type`) REFERENCES `usertype` (`idType`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

--
-- Table structure for table `permission`
--

DROP TABLE IF EXISTS `permission`;
CREATE TABLE `permission` (
  `type` varchar(2) NOT NULL,
  `create` tinyint(1) DEFAULT NULL,
  `read` tinyint(1) DEFAULT NULL,
  `updat` tinyint(1) DEFAULT NULL,
  `delet` tinyint(1) DEFAULT NULL,
  `information` varchar(20) NOT NULL,
  PRIMARY KEY (`type`,`information`),
  KEY `fk_type2_idx` (`type`),
  CONSTRAINT `fk_type2` FOREIGN KEY (`type`) REFERENCES `usertype` (`idType`) ON DELETE NO ACTION ON UPDATE NO ACTION
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

-- Dump completed on 2017-05-23 16:12:13


--
-- Dumping data for table `chambre`
--

LOCK TABLES `chambre` WRITE;
/*!40000 ALTER TABLE `chambre` DISABLE KEYS */;
/*!40000 ALTER TABLE `chambre` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `chambre_reservation`
--

LOCK TABLES `chambre_reservation` WRITE;
/*!40000 ALTER TABLE `chambre_reservation` DISABLE KEYS */;
/*!40000 ALTER TABLE `chambre_reservation` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `client`
--

LOCK TABLES `client` WRITE;
/*!40000 ALTER TABLE `client` DISABLE KEYS */;
/*!40000 ALTER TABLE `client` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `facture`
--

LOCK TABLES `facture` WRITE;
/*!40000 ALTER TABLE `facture` DISABLE KEYS */;
/*!40000 ALTER TABLE `facture` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `usertype`
--

LOCK TABLES `usertype` WRITE;
/*!40000 ALTER TABLE `usertype` DISABLE KEYS */;
INSERT INTO `usertype` VALUES ('A','ADMIN'),('C','CHEF'),('R','RECEPTIONNISTE');
/*!40000 ALTER TABLE `usertype` ENABLE KEYS */;
UNLOCK TABLES;


--
-- Dumping data for table `permission`
--

LOCK TABLES `permission` WRITE;
/*!40000 ALTER TABLE `permission` DISABLE KEYS */;
INSERT INTO `permission` VALUES ('A',1,1,1,1,'CHAMBRE'),('A',1,1,1,1,'CLIENT'),('A',1,1,1,1,'HISTORIQUE'),('A',1,1,1,1,'RESERVATION'),('A',1,1,1,1,'USER'),('C',0,1,0,0,'CHAMBRE'),('C',1,1,1,1,'CLIENT'),('C',0,0,0,0,'HISTORIQUE'),('C',1,1,1,1,'RESERVATION'),('C',0,0,0,0,'USER'),('R',0,1,0,0,'CHAMBRE'),('R',1,1,0,0,'CLIENT'),('R',0,0,0,0,'HISTORIQUE'),('R',1,1,0,0,'RESERVATION'),('R',0,0,0,0,'USER');
/*!40000 ALTER TABLE `permission` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `reservation`
--

LOCK TABLES `reservation` WRITE;
/*!40000 ALTER TABLE `reservation` DISABLE KEYS */;
/*!40000 ALTER TABLE `reservation` ENABLE KEYS */;
UNLOCK TABLES;


--
-- Dumping data for table `utilisateur`
--

LOCK TABLES `utilisateur` WRITE;
INSERT INTO `utilisateur` VALUES (1,'admin','admin',NULL,'A','admin','fb001dfcffd1c899f3297871406242f097aecf1a5342ccf3ebcd116146188e4b');
UNLOCK TABLES;
