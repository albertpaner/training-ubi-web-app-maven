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
-- Table structure for table `utente`
--

DROP TABLE IF EXISTS `utente`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `utente` (
  `utente_id` int NOT NULL AUTO_INCREMENT,
  `email` varchar(50) NOT NULL,
  `password` varchar(500) NOT NULL,
  `ruolo_id` int DEFAULT '2',
  `dipendente_id` int DEFAULT NULL,
  `nome` varchar(50) NOT NULL,
  `cognome` varchar(50) NOT NULL,
  `responsabile_id` int DEFAULT NULL,
  `societa_op` varchar(50) DEFAULT NULL,
  `mansione` varchar(50) DEFAULT NULL,
  `ambito` varchar(50) DEFAULT NULL,
  `job_fam` varchar(50) DEFAULT NULL,
  `sub_fam` varchar(50) DEFAULT NULL,
  `std_job` varchar(50) DEFAULT NULL,
  `job_level` varchar(50) DEFAULT NULL,
  `data_ult_acc` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `data_ult_mod` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `data_creaz` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `flg_del` tinyint(1) DEFAULT '0',
  PRIMARY KEY (`utente_id`),
  UNIQUE KEY `email` (`email`),
  UNIQUE KEY `responsabile_id` (`responsabile_id`),
  KEY `dipendente_id` (`dipendente_id`),
  KEY `ruolo_id` (`ruolo_id`),
  CONSTRAINT `utente_ibfk_1` FOREIGN KEY (`dipendente_id`) REFERENCES `utente` (`responsabile_id`),
  CONSTRAINT `utente_ibfk_2` FOREIGN KEY (`ruolo_id`) REFERENCES `ruolo` (`ruolo_id`)
) ENGINE=InnoDB AUTO_INCREMENT=26 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `utente`
--

LOCK TABLES `utente` WRITE;
/*!40000 ALTER TABLE `utente` DISABLE KEYS */;
INSERT INTO `utente` VALUES (1,'spongebob@bikinibottom.com','krabbypatty',1,NULL,'SpongeBob','SquarePants',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'2023-11-23 14:35:21','2023-11-23 14:35:21','2023-11-23 14:35:21',0),(2,'patrick@bikinibottom.com','starfish',2,NULL,'Patrick','Star',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'2023-11-23 14:35:21','2023-11-23 14:35:21','2023-11-23 14:35:21',0),(3,'squidward@bikinibottom.com','clarinet',1,NULL,'Squidward','Tentacles',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'2023-11-23 15:18:08','2023-11-23 14:35:21','2023-11-23 14:35:21',0),(4,'mrkrabs@bikinibottom.com','money',1,NULL,'Mr.','Krabs',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'2023-11-23 15:30:52','2023-11-23 14:35:21','2023-11-23 14:35:21',0),(5,'sandy@bikinibottom.com','science',1,NULL,'Sandy','Cheeks',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'2023-11-23 15:31:05','2023-11-23 14:35:21','2023-11-23 14:35:21',0),(6,'plankton@bikinibottom.com','evilplan',1,NULL,'Plankton','',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'2023-11-23 15:31:11','2023-11-23 14:35:21','2023-11-23 14:35:21',0),(7,'gary@bikinibottom.com','meow',2,NULL,'Gary','the Snail',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'2023-11-23 15:31:22','2023-11-23 14:35:21','2023-11-23 14:35:21',0),(8,'pearl@bikinibottom.com','whale',2,NULL,'Pearl','Krabs',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'2023-11-23 15:31:31','2023-11-23 14:35:21','2023-11-23 14:35:21',0),(9,'mrs.puff@bikinibottom.com','driving',1,NULL,'Mrs.','Puff',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'2023-11-23 15:31:41','2023-11-23 14:35:21','2023-11-23 14:35:21',0),(10,'larry@bikinibottom.com','lobster',1,NULL,'Larry','the Lobster',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'2023-11-23 15:31:57','2023-11-23 14:35:21','2023-11-23 14:35:21',0),(11,'mermaidman@bikinibottom.com','hero',1,NULL,'Mermaid','Man',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'2023-11-23 15:32:03','2023-11-23 14:35:21','2023-11-23 14:35:21',0),(12,'barnacleboy@bikinibottom.com','sidekick',NULL,NULL,'Barnacle','Boy',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'2023-11-23 15:32:27','2023-11-23 14:35:21','2023-11-23 14:35:21',0),(13,'enrico.montemurro@bikinibottom.com','password',1,1,'Enrico','Montemurro',1,'Krusty Krab','Manager',NULL,NULL,NULL,NULL,NULL,'2023-11-23 14:45:30','2023-11-23 14:45:30','2023-11-23 14:45:30',0),(14,'alice@tanga.com','fbccedd61528383fcf15a836bea22491d2af4064931de4231f46ea23e75bfcc',1,NULL,'Alice','Mermaid',0,'null','null','null','null','null','null','null','2023-11-28 11:05:35','2023-11-28 11:05:35','2023-11-28 11:05:35',0),(17,'massimo.bossetti@gmail.com','48cbc54a50d22476b176bb73549621ae53e8b6432d88d9f9c0a63f81ff174446',2,NULL,'Massimo','Bossetti',2,'null','null','null','null','null','null','null','2023-11-28 11:14:43','2023-11-28 11:14:43','2023-11-28 11:14:43',0),(23,'marchinoBello@email.com','2106d4d07b6a9b6640e92c329608e90a7f732e0a15bbb735cd7e9174bb09f111',1,NULL,'Marchino','Bello',14,'societaOp','mansione','ambito','jobFam','subFam','stdJob','jobLevel','2023-11-28 13:17:47','2023-11-28 13:17:47','2023-11-28 13:17:47',0),(25,'Bob@submarine.com','646bcf915c06383356c36a589310a2c11928281175cb592378916d26c62d8b3e',2,NULL,'Bob','Narvhal',9,'null','null','null','null','null','null','null','2023-11-28 13:35:43','2023-11-28 13:35:43','2023-11-28 13:35:43',0);
/*!40000 ALTER TABLE `utente` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-11-29 16:58:13
