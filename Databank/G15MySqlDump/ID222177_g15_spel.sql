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
-- Table structure for table `spel`
--

DROP TABLE IF EXISTS `spel`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `spel` (
  `naamSpel` varchar(45) NOT NULL,
  `naam` varchar(45) NOT NULL,
  `speler` varchar(45) NOT NULL,
  `moeilijkheidsgraad` varchar(45) NOT NULL,
  `code` varchar(500) DEFAULT NULL,
  `uitdagingnr` int(11) DEFAULT NULL,
  `idRij0` varchar(255) DEFAULT NULL,
  `idRij1` varchar(255) DEFAULT NULL,
  `idRij2` varchar(255) DEFAULT NULL,
  `idRij3` varchar(255) DEFAULT NULL,
  `idRij4` varchar(255) DEFAULT NULL,
  `idRij5` varchar(255) DEFAULT NULL,
  `idRij6` varchar(255) DEFAULT NULL,
  `idRij7` varchar(255) DEFAULT NULL,
  `idRij8` varchar(255) DEFAULT NULL,
  `idRij9` varchar(255) DEFAULT NULL,
  `idRij10` varchar(255) DEFAULT NULL,
  `idRij11` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`naamSpel`),
  UNIQUE KEY `naamSpel_UNIQUE` (`naamSpel`),
  KEY `speler_idx` (`naam`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `spel`
--

LOCK TABLES `spel` WRITE;
/*!40000 ALTER TABLE `spel` DISABLE KEYS */;
INSERT INTO `spel` VALUES ('andagainmarccoucke','andagain','marccoucke','normaal','7-6-8-6',0,'andagainmarccoucker00','andagainmarccoucker01','andagainmarccoucker02','andagainmarccoucker03','andagainmarccoucker04','andagainmarccoucker05','andagainmarccoucker06','andagainmarccoucker07','andagainmarccoucker08','andagainmarccoucker09','andagainmarccoucker10','andagainmarccoucker11'),('bewarentweemarccoucke','bewarentwee','marccoucke','gemakkelijk','6-1-3-4',0,'bewarentweemarccoucker00','bewarentweemarccoucker01','bewarentweemarccoucker02','bewarentweemarccoucker03','bewarentweemarccoucker04','bewarentweemarccoucker05','bewarentweemarccoucker06','bewarentweemarccoucker07','bewarentweemarccoucker08','bewarentweemarccoucker09','bewarentweemarccoucker10','bewarentweemarccoucker11'),('eenmuismarccoucke','eenmuis','marccoucke','gemakkelijk','2-7-3-1',0,'eenmuismarccoucker00','eenmuismarccoucker01','eenmuismarccoucker02','eenmuismarccoucker03','eenmuismarccoucker04','eenmuismarccoucker05','eenmuismarccoucker06','eenmuismarccoucker07','eenmuismarccoucker08','eenmuismarccoucker09','eenmuismarccoucker10','eenmuismarccoucker11'),('hp2marccoucke','hp2','marccoucke','normaal','7-4-2-7',0,'hp2marccoucker00','hp2marccoucker01','hp2marccoucker02','hp2marccoucker03','hp2marccoucker04','hp2marccoucker05','hp2marccoucker06','hp2marccoucker07','hp2marccoucker08','hp2marccoucker09','hp2marccoucker10','hp2marccoucker11'),('hpmarccoucke','hp','marccoucke','gemakkelijk','3-8-1-7',0,'hpmarccoucker00','hpmarccoucker01','hpmarccoucker02','hpmarccoucker03','hpmarccoucker04','hpmarccoucker05','hpmarccoucker06','hpmarccoucker07','hpmarccoucker08','hpmarccoucker09','hpmarccoucker10','hpmarccoucker11'),('letstryagainmarccoucke','letstryagain','marccoucke','normaal','5-8-2-2',0,'letstryagainmarccoucker00','letstryagainmarccoucker01','letstryagainmarccoucker02','letstryagainmarccoucker03','letstryagainmarccoucker04','letstryagainmarccoucker05','letstryagainmarccoucker06','letstryagainmarccoucker07','letstryagainmarccoucker08','letstryagainmarccoucker09','letstryagainmarccoucker10','letstryagainmarccoucker11'),('letstrymarccoucke','letstry','marccoucke','gemakkelijk','3-7-4-2',0,'letstrymarccoucker00','letstrymarccoucker01','letstrymarccoucker02','letstrymarccoucker03','letstrymarccoucker04','letstrymarccoucker05','letstrymarccoucker06','letstrymarccoucker07','letstrymarccoucker08','letstrymarccoucker09','letstrymarccoucker10','letstrymarccoucker11'),('niggercooldude','nigger','cooldude','normaal','7-5-8-8',0,'niggercoolduder00','niggercoolduder01','niggercoolduder02','niggercoolduder03','niggercoolduder04','niggercoolduder05','niggercoolduder06','niggercoolduder07','niggercoolduder08','niggercoolduder09','niggercoolduder10','niggercoolduder11'),('nopemarccoucke','nope','marccoucke','gemakkelijk','2-3-4-8',0,'nopemarccoucker00','nopemarccoucker01','nopemarccoucker02','nopemarccoucker03','nopemarccoucker04','nopemarccoucker05','nopemarccoucker06','nopemarccoucker07','nopemarccoucker08','nopemarccoucker09','nopemarccoucker10','nopemarccoucker11'),('opgeslagenuitdagingfxmarccoucke','opgeslagenuitdagingfx','marccoucke','gemakkelijk','7-8-4-2',53,'opgeslagenuitdagingfxmarccoucker00','opgeslagenuitdagingfxmarccoucker01','opgeslagenuitdagingfxmarccoucker02','opgeslagenuitdagingfxmarccoucker03','opgeslagenuitdagingfxmarccoucker04','opgeslagenuitdagingfxmarccoucker05','opgeslagenuitdagingfxmarccoucker06','opgeslagenuitdagingfxmarccoucker07','opgeslagenuitdagingfxmarccoucker08','opgeslagenuitdagingfxmarccoucker09','opgeslagenuitdagingfxmarccoucker10','opgeslagenuitdagingfxmarccoucker11'),('opslaanmmarccoucke','opslaanm','marccoucke','normaal','5-8-2-2',0,'opslaanmmarccoucker00','opslaanmmarccoucker01','opslaanmmarccoucker02','opslaanmmarccoucker03','opslaanmmarccoucker04','opslaanmmarccoucker05','opslaanmmarccoucker06','opslaanmmarccoucker07','opslaanmmarccoucker08','opslaanmmarccoucker09','opslaanmmarccoucker10','opslaanmmarccoucker11'),('probeeropnieuwmarccoucke','probeeropnieuw','marccoucke','moeilijk','5-6-5-0-5',0,'probeeropnieuwmarccoucker00','probeeropnieuwmarccoucker01','probeeropnieuwmarccoucker02','probeeropnieuwmarccoucker03','probeeropnieuwmarccoucker04','probeeropnieuwmarccoucker05','probeeropnieuwmarccoucker06','probeeropnieuwmarccoucker07','probeeropnieuwmarccoucker08','probeeropnieuwmarccoucker09','probeeropnieuwmarccoucker10','probeeropnieuwmarccoucker11'),('roodmarccoucke','rood','marccoucke','gemakkelijk','6-2-8-5',0,'roodmarccoucker00','roodmarccoucker01','roodmarccoucker02','roodmarccoucker03','roodmarccoucker04','roodmarccoucker05','roodmarccoucker06','roodmarccoucker07','roodmarccoucker08','roodmarccoucker09','roodmarccoucker10','roodmarccoucker11'),('roosmarccoucke','roos','marccoucke','gemakkelijk','3-4-6-2',0,'roosmarccoucker00','roosmarccoucker01','roosmarccoucker02','roosmarccoucker03','roosmarccoucker04','roosmarccoucker05','roosmarccoucker06','roosmarccoucker07','roosmarccoucker08','roosmarccoucker09','roosmarccoucker10','roosmarccoucker11'),('spel8/5marccoucke','spel8/5','marccoucke','normaal','6-6-2-2',45,'spel8/5marccoucker00','spel8/5marccoucker01','spel8/5marccoucker02','spel8/5marccoucker03','spel8/5marccoucker04','spel8/5marccoucker05','spel8/5marccoucker06','spel8/5marccoucker07','spel8/5marccoucker08','spel8/5marccoucker09','spel8/5marccoucker10','spel8/5marccoucker11'),('uitdagingTestmarccoucke','uitdagingTest','marccoucke','gemakkelijk','3-7-5-8',50,'uitdagingTestmarccoucker00','uitdagingTestmarccoucker01','uitdagingTestmarccoucker02','uitdagingTestmarccoucker03','uitdagingTestmarccoucker04','uitdagingTestmarccoucker05','uitdagingTestmarccoucker06','uitdagingTestmarccoucker07','uitdagingTestmarccoucker08','uitdagingTestmarccoucker09','uitdagingTestmarccoucker10','uitdagingTestmarccoucker11');
/*!40000 ALTER TABLE `spel` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-05-13 22:03:56
