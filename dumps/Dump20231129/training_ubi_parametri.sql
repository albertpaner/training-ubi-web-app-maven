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
-- Table structure for table `parametri`
--

DROP TABLE IF EXISTS `parametri`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `parametri` (
  `param_id` int NOT NULL AUTO_INCREMENT,
  `paragrafo_id` int NOT NULL,
  `descr_param` varchar(50) NOT NULL,
  `data_ult_mod` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `data_creazione` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `flg_del` tinyint DEFAULT NULL,
  `valut_id` int NOT NULL,
  PRIMARY KEY (`param_id`),
  KEY `paragrafo_id` (`paragrafo_id`),
  KEY `valut_id` (`valut_id`),
  CONSTRAINT `parametri_ibfk_2` FOREIGN KEY (`paragrafo_id`) REFERENCES `paragrafo` (`paragrafo_id`),
  CONSTRAINT `parametri_ibfk_3` FOREIGN KEY (`valut_id`) REFERENCES `valutazione` (`valut_id`)
) ENGINE=InnoDB AUTO_INCREMENT=38 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `parametri`
--

LOCK TABLES `parametri` WRITE;
/*!40000 ALTER TABLE `parametri` DISABLE KEYS */;
/*!40000 ALTER TABLE `parametri` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-11-29 16:58:21
