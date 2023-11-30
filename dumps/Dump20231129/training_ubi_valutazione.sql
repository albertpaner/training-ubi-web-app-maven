-- MySQL dump 10.13  Distrib 8.0.34, for Win64 (x86_64)
--
-- Host: 212.227.27.155    Database: training_ubi
-- ------------------------------------------------------
-- Server version	8.2.0

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `valutazione`
--

DROP TABLE IF EXISTS `valutazione`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `valutazione` (
  `valut_id` int NOT NULL AUTO_INCREMENT,
  `valore_val` int DEFAULT NULL,
  `descr_val` varchar(50) DEFAULT NULL,
  `utente_id` int NOT NULL,
  `data_ult_mod` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `data_creaz` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `flg_del` tinyint(1) DEFAULT '0',
  PRIMARY KEY (`valut_id`),
  KEY `utente_id` (`utente_id`),
  CONSTRAINT `valutazione_ibfk_1` FOREIGN KEY (`utente_id`) REFERENCES `utente` (`utente_id`)
) ENGINE=InnoDB AUTO_INCREMENT=36 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `valutazione`
--

LOCK TABLES `valutazione` WRITE;
/*!40000 ALTER TABLE `valutazione` DISABLE KEYS */;
INSERT INTO `valutazione` VALUES (18,NULL,'Conseguito',13,'2023-11-24 14:00:07','2023-11-23 16:15:11',0),(19,NULL,'Conseguito parzialmente',13,'2023-11-24 14:00:41','2023-11-23 16:15:11',0),(20,NULL,'Superiore alle aspettative',13,'2023-11-24 14:01:31','2023-11-23 16:15:11',0),(21,1,'Teorica',13,'2023-11-23 16:15:11','2023-11-23 16:15:11',0),(22,2,'Applicativa',13,'2023-11-23 16:15:11','2023-11-23 16:15:11',0),(23,3,'Approfondita e autonoma',13,'2023-11-23 16:15:11','2023-11-23 16:15:11',0),(24,4,'Specialistica',13,'2023-11-23 16:15:11','2023-11-23 16:15:11',0),(25,NULL,'Raramente',13,'2023-11-24 14:01:51','2023-11-23 16:15:11',0),(26,NULL,'Talvolta',13,'2023-11-24 14:02:50','2023-11-23 16:15:11',0),(27,NULL,'Spesso',13,'2023-11-24 14:03:10','2023-11-23 16:15:11',0),(28,NULL,'Sempre',13,'2023-11-24 14:03:36','2023-11-23 16:15:11',0),(29,1,'',13,'2023-11-23 16:15:11','2023-11-23 16:15:11',0),(30,2,'',13,'2023-11-23 16:15:11','2023-11-23 16:15:11',0),(31,3,'',13,'2023-11-23 16:15:11','2023-11-23 16:15:11',0),(32,4,'',13,'2023-11-23 16:15:11','2023-11-23 16:15:11',0),(35,1,'-',1,'2023-11-24 16:22:39','2023-11-24 16:22:39',0);
/*!40000 ALTER TABLE `valutazione` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-11-29 16:58:16
