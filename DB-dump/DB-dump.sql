CREATE DATABASE  IF NOT EXISTS `freedb_RPR baza - projekt` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `freedb_RPR baza - projekt`;
-- MySQL dump 10.13  Distrib 8.0.31, for Win64 (x86_64)
--
-- Host: sql.freedb.tech    Database: freedb_RPR baza - projekt
-- ------------------------------------------------------
-- Server version	8.0.34-0ubuntu0.22.04.1

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
-- Table structure for table `gosti`
--

DROP TABLE IF EXISTS `gosti`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `gosti` (
  `gost_id` int NOT NULL,
  `ime` varchar(45) NOT NULL,
  `prezime` varchar(45) NOT NULL,
  `email` varchar(45) NOT NULL,
  `kontakt_broj` varchar(45) NOT NULL,
  PRIMARY KEY (`gost_id`),
  UNIQUE KEY `gost_id_UNIQUE` (`gost_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `gosti`
--

LOCK TABLES `gosti` WRITE;
/*!40000 ALTER TABLE `gosti` DISABLE KEYS */;
INSERT INTO `gosti` VALUES (1,'Kyle','Cooper','neque.non@outlook.couk','123-456-789'),(2,'Camila','Roob','cmail@outlook.couk','456-123-789'),(3,'Macy','Book','macy@domain.com','123-456-123'),(111,'Test','test','test@domain.com','145-789-110'),(14711,'ime','prezime','mail','144-111-010'),(121312,'test','Prezime','m@mail.com','123-234-456');
/*!40000 ALTER TABLE `gosti` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `rezervacije`
--

DROP TABLE IF EXISTS `rezervacije`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `rezervacije` (
  `broj_rezervacije` int NOT NULL AUTO_INCREMENT,
  `datum_dolaska` date NOT NULL,
  `datum_odlaska` date NOT NULL,
  `gost_id` int NOT NULL,
  `br_sobe` int NOT NULL,
  PRIMARY KEY (`broj_rezervacije`),
  UNIQUE KEY `broj_rezervacije_UNIQUE` (`broj_rezervacije`),
  KEY `gost_id_idx` (`gost_id`),
  KEY `br_sobe_idx` (`br_sobe`),
  CONSTRAINT `br_sobe` FOREIGN KEY (`br_sobe`) REFERENCES `sobe` (`broj_sobe`),
  CONSTRAINT `gost_id` FOREIGN KEY (`gost_id`) REFERENCES `gosti` (`gost_id`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `rezervacije`
--

LOCK TABLES `rezervacije` WRITE;
/*!40000 ALTER TABLE `rezervacije` DISABLE KEYS */;
INSERT INTO `rezervacije` VALUES (1,'2021-05-06','2021-07-16',2,101),(2,'2021-03-05','2021-06-10',3,100),(3,'2022-06-18','2022-06-25',3,130),(4,'2023-08-22','2023-08-23',111,100),(5,'2023-08-29','2023-08-30',121312,103),(10,'2023-10-23','2023-10-30',2,200);
/*!40000 ALTER TABLE `rezervacije` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sobe`
--

DROP TABLE IF EXISTS `sobe`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `sobe` (
  `broj_sobe` int NOT NULL,
  `tip_sobe` int NOT NULL,
  `cijena` double NOT NULL,
  `VIP` varchar(45) NOT NULL,
  `status` varchar(45) NOT NULL,
  PRIMARY KEY (`broj_sobe`),
  UNIQUE KEY `broj_sobe_UNIQUE` (`broj_sobe`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sobe`
--

LOCK TABLES `sobe` WRITE;
/*!40000 ALTER TABLE `sobe` DISABLE KEYS */;
INSERT INTO `sobe` VALUES (1,2,1000,'DA','slobodna'),(12,1,85.95,'NE','zauzeta'),(100,2,200.55,'NO','slobodna'),(101,3,350.25,'YES','zauzeta'),(102,5,1200,'YES','slobodna'),(103,2,450.5,'NO','slobodna'),(105,2,550.25,'NO','slobodna'),(106,1,125.99,'NE','slobodna'),(107,1,125.99,'NE','slobodna'),(125,3,522.55,'NE','slobodna'),(127,2,230,'NE','slobodna'),(128,2,1250,'DA','slobodna'),(129,3,1500,'DA','slobodna'),(130,2,850,'DA','slobodna'),(200,2,250.5,'DA','zauzeta');
/*!40000 ALTER TABLE `sobe` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `zaposlenici`
--

DROP TABLE IF EXISTS `zaposlenici`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `zaposlenici` (
  `zaposlenik_id` int NOT NULL,
  `korisnicko_ime` varchar(45) NOT NULL,
  `sifra` varchar(45) NOT NULL,
  `ime` varchar(45) NOT NULL,
  `prezime` varchar(45) NOT NULL,
  `email` varchar(45) NOT NULL,
  `posao` varchar(45) NOT NULL,
  `plata` double NOT NULL,
  `admin` int NOT NULL,
  PRIMARY KEY (`zaposlenik_id`),
  UNIQUE KEY `zaposlenik_id_UNIQUE` (`zaposlenik_id`),
  UNIQUE KEY `korisnicko_ime_UNIQUE` (`korisnicko_ime`),
  UNIQUE KEY `sifra_UNIQUE` (`sifra`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `zaposlenici`
--

LOCK TABLES `zaposlenici` WRITE;
/*!40000 ALTER TABLE `zaposlenici` DISABLE KEYS */;
INSERT INTO `zaposlenici` VALUES (1,'e','e1234','Emma','Road','emy@domain.com','recepcioner',1050,0),(2,'worker1','w123','Robin','Graham','enim.sed@icloud.com','recepcioner',1020,0),(3,'worker2','w213','Mari','Gross','venenatis.a@yahoo.ca','mender',1500.25,0),(10,'username','password','name2','lastname','test@mail.com','job',1000,0),(11,'test1','t2','TEST','TEST','mail','decorator',1220,0),(12,'ad','a1234','Admin','admin','mail@domain.com','administrator',1200,1),(13,'w','w1234','Worker','Wo','mail','worker',1200,0),(150,'admi','12345','Admin','Admin','admin@mail.com','administrator',1250,1),(1456,'t','t1234','test','testz','t@domain.com','recepcioner',1250,0);
/*!40000 ALTER TABLE `zaposlenici` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-09-08  0:29:40
