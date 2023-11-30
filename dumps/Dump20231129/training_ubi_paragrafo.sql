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
-- Table structure for table `paragrafo`
--

DROP TABLE IF EXISTS `paragrafo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `paragrafo` (
  `paragrafo_id` int NOT NULL AUTO_INCREMENT,
  `sezione_id` int NOT NULL,
  `titolo_par` varchar(500) DEFAULT NULL,
  `descr_par` varchar(50) NOT NULL,
  `data_ult_mod` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `data_creaz` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `flg_del` tinyint(1) DEFAULT '0',
  PRIMARY KEY (`paragrafo_id`),
  KEY `sezione_id` (`sezione_id`),
  CONSTRAINT `paragrafo_ibfk_1` FOREIGN KEY (`sezione_id`) REFERENCES `sezione` (`sezione_id`)
) ENGINE=InnoDB AUTO_INCREMENT=66 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `paragrafo`
--

LOCK TABLES `paragrafo` WRITE;
/*!40000 ALTER TABLE `paragrafo` DISABLE KEYS */;
INSERT INTO `paragrafo` VALUES (33,2,'LEGALE, NORMATIVA E COMPLIANCE','xxx','2023-11-23 15:21:15','2023-11-23 15:21:15',0),(34,2,'CREDITI','xxx','2023-11-23 15:21:15','2023-11-23 15:21:15',0),(35,2,'ORGANIZZAZIONE','xxx','2023-11-23 15:21:15','2023-11-23 15:21:15',0),(36,2,'COMMERCIALE','xxx','2023-11-23 15:21:15','2023-11-23 15:21:15',0),(37,2,'COMMERCIALE','xxxdifferent','2023-11-23 15:21:15','2023-11-23 15:21:15',0),(38,3,'CREDITO AL CONSUMO','xxx','2023-11-23 15:21:15','2023-11-23 15:21:15',0),(39,3,'MONETICA E PAGAMENTI EVOLUTI','xxx','2023-11-23 15:21:15','2023-11-23 15:21:15',0),(40,3,'MULTICANALITA\'','xxx','2023-11-23 15:21:15','2023-11-23 15:21:15',0),(41,3,'NORMATIVA TITOLI CREDITO, USURA, ANTIRICICLAGGIO E PRIVACY','xxx','2023-11-23 15:21:15','2023-11-23 15:21:15',0),(42,3,'PIANO COMMERCIALE (PER I PRODOTTI/SERVIZI DI COMPETENZA)','xxx','2023-11-23 15:21:15','2023-11-23 15:21:15',0),(43,3,'PROCEDURE OPERATIVE DI FRONT E/O BACK OFFICE','xxx','2023-11-23 15:21:15','2023-11-23 15:21:15',0),(44,3,'RISCHI OPERATIVI','xxx','2023-11-23 15:21:15','2023-11-23 15:21:15',0),(45,4,'AGGIORNAMENTO NORMATIVO ECONOMICO E FINANZIARIO del Consulente','xxx','2023-11-23 15:21:15','2023-11-23 15:21:15',0),(46,4,'COLLABORAZIONE E INTEGRAZIONE CON LA SQUADRA del Consulente','xxx','2023-11-23 15:21:15','2023-11-23 15:21:15',0),(47,4,'CULTURA E SVILUPPO DIGITALE del Consulente','xxx','2023-11-23 15:21:15','2023-11-23 15:21:15',0),(48,4,'GESTIONE ATTIVITA AMMINISTRATIVE-CONTABILI del Collaboratore','xxx','2023-11-23 15:21:15','2023-11-23 15:21:15',0),(49,4,'GESTIONE E SVILUPPO DEL PORTAFOGLIO del Consulente','xxx','2023-11-23 15:21:15','2023-11-23 15:21:15',0),(50,4,'MIGLIORAMENTO CUSTOMER EXPERIENCE del Consulente','xxx','2023-11-23 15:21:15','2023-11-23 15:21:15',0),(51,4,'PREVENZIONE RECLAMI del Consulente','xxx','2023-11-23 15:21:15','2023-11-23 15:21:15',0),(52,5,'AGILITA\' DI PENSIERO','xxx','2023-11-23 15:21:15','2023-11-23 15:21:15',0),(53,5,'AUTOCONTROLLO E GESTIONE DELLO STRESS','xxx','2023-11-23 15:21:15','2023-11-23 15:21:15',0),(54,5,'COLLABORAZIONE E INTEGRAZIONE','xxx','2023-11-23 15:21:15','2023-11-23 15:21:15',0),(55,5,'DECISIONALITA\'','xxx','2023-11-23 15:21:15','2023-11-23 15:21:15',0),(56,5,'EFFICACIA COMUNICATIVA','xxx','2023-11-23 15:21:15','2023-11-23 15:21:15',0),(57,5,'ENERGIA REALIZZATIVA E ORIENTAMENTO AL RISULTATO','xxx','2023-11-23 15:21:15','2023-11-23 15:21:15',0),(58,5,'ORIENTAMENTO AL CLIENTE','xxx','2023-11-23 15:21:15','2023-11-23 15:21:15',0),(59,5,'PIANIFICAZIONE E ORGANIZZAZIONE','xxx','2023-11-23 15:21:15','2023-11-23 15:21:15',0),(60,5,'PROPENSIONE AL NUOVO','xxx','2023-11-23 15:21:15','2023-11-23 15:21:15',0),(61,8,'NOTE COLLABORATORE','xxx','2023-11-23 15:21:15','2023-11-23 15:21:15',0),(62,8,'NOTE RESPONSABILE','xxx','2023-11-23 15:21:15','2023-11-23 15:21:15',0),(63,9,'NOTE COLLABORATORE','xxx','2023-11-23 15:21:15','2023-11-23 15:21:15',0),(64,9,'NOTE RESPONSABILE','xxx','2023-11-23 15:21:15','2023-11-23 15:21:15',0);
/*!40000 ALTER TABLE `paragrafo` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-11-29 16:58:18
