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
-- Table structure for table `uitdaging`
--

DROP TABLE IF EXISTS `uitdaging`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `uitdaging` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `speler` varchar(255) NOT NULL,
  `tegenspeler` varchar(255) NOT NULL,
  `scoreSpeler` int(11) DEFAULT NULL,
  `scoreTegenspeler` int(11) DEFAULT NULL,
  `moeilijkheidsgraad` varchar(255) NOT NULL,
  `teRadenCode` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=55 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `uitdaging`
--

LOCK TABLES `uitdaging` WRITE;
/*!40000 ALTER TABLE `uitdaging` DISABLE KEYS */;
INSERT INTO `uitdaging` VALUES (50,'marccoucke','cooldude',NULL,3,'gemakkelijk','[3, 7, 5, 8]'),(51,'marccoucke','meikever',1,NULL,'gemakkelijk','[1, 5, 7, 4]'),(52,'marccoucke','danny',NULL,NULL,'gemakkelijk','[3, 4, 1, 2]'),(53,'marccoucke','bernardanita',NULL,NULL,'gemakkelijk','[7, 8, 4, 2]'),(54,'marccoucke','marccoucke',NULL,NULL,'normaal','[5, 8, 2, 3]');
/*!40000 ALTER TABLE `uitdaging` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-05-13 22:03:58
