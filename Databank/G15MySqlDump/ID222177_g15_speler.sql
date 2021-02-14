-- MySQL dump 10.13  Distrib 5.7.17, for Win64 (x86_64)
--
-- Host: ID222177_g15.db.webhosting.be    Database: ID222177_g15
-- ------------------------------------------------------
-- Server version	5.7.20-18

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
-- Table structure for table `speler`
--

DROP TABLE IF EXISTS `speler`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `speler` (
  `spelersnaam` varchar(25) NOT NULL,
  `wachtwoord` varchar(25) NOT NULL,
  `gemakkelijk` int(11) DEFAULT NULL,
  `normaal` int(11) DEFAULT NULL,
  `moeilijk` int(11) DEFAULT NULL,
  PRIMARY KEY (`spelersnaam`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `speler`
--

LOCK TABLES `speler` WRITE;
/*!40000 ALTER TABLE `speler` DISABLE KEYS */;
INSERT INTO `speler` VALUES ('axitrans','1axitrans1',0,0,0),('bernardanita','1databanken1',80,80,80),('cooldude','1cooldude1',3,0,0),('danny','1dannykeerman1',4,0,0),('eennaam','1eennaam1',3,0,0),('lievensmits','3wiskunde3',1,0,0),('marccoucke','1marccoucke1',43,42,3),('meikever','1meikever1',0,0,0),('meikever1','1meikever1',0,0,0),('nathalie','1netwerken1',1,0,0),('noemieslaats','1noemieslaats1',0,0,0),('nopenope','1nopenope1',2,0,0),('olivierrosseel','1netwerken1',1,0,0),('robinv','1robinv1',3,0,0),('testing','1testing1',0,0,0),('testReeven','1reeventest1',0,0,0),('uitdager','1uitdager1',1,0,0),('uitgedaagde','1uitgedaagde1',2,0,0);
/*!40000 ALTER TABLE `speler` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-05-13 22:03:55
