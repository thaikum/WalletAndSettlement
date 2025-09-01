/*M!999999\- enable the sandbox mode */ 
-- MariaDB dump 10.19  Distrib 10.11.13-MariaDB, for debian-linux-gnu (x86_64)
--
-- Host: localhost    Database: wallet_and_settlement
-- ------------------------------------------------------
-- Server version	10.11.13-MariaDB-0ubuntu0.24.04.1

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `customer`
--

DROP TABLE IF EXISTS `customer`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8mb4 */;
CREATE TABLE `customer` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `address` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `first_name` varchar(255) DEFAULT NULL,
  `last_name` varchar(255) DEFAULT NULL,
  `phone_number` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=41 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `customer`
--

LOCK TABLES `customer` WRITE;
/*!40000 ALTER TABLE `customer` DISABLE KEYS */;
INSERT INTO `customer` VALUES
(1,'123 Main St, New York, NY 10001','john.doe@email.com','John','Doe','+1-555-0101'),
(2,'456 Oak Ave, Los Angeles, CA 90210','jane.smith@email.com','Jane','Smith','+1-555-0102'),
(3,'789 Pine Rd, Chicago, IL 60601','mike.johnson@email.com','Mike','Johnson','+1-555-0103'),
(4,'321 Elm St, Houston, TX 77001','sarah.brown@email.com','Sarah','Brown','+1-555-0104'),
(5,'654 Maple Dr, Phoenix, AZ 85001','david.wilson@email.com','David','Wilson','+1-555-0105'),
(6,'987 Cedar Ln, Philadelphia, PA 19101','lisa.garcia@email.com','Lisa','Garcia','+1-555-0106'),
(7,'147 Birch St, San Antonio, TX 78201','robert.martinez@email.com','Robert','Martinez','+1-555-0107'),
(8,'258 Spruce Ave, San Diego, CA 92101','emily.anderson@email.com','Emily','Anderson','+1-555-0108'),
(9,'369 Willow Rd, Dallas, TX 75201','james.taylor@email.com','James','Taylor','+1-555-0109'),
(10,'741 Aspen Dr, San Jose, CA 95101','maria.rodriguez@email.com','Maria','Rodriguez','+1-555-0110'),
(11,'123 Main St, New York, NY 10001','john.doe@email.com','John','Doe','+1-555-0101'),
(12,'456 Oak Ave, Los Angeles, CA 90210','jane.smith@email.com','Jane','Smith','+1-555-0102'),
(13,'789 Pine Rd, Chicago, IL 60601','mike.johnson@email.com','Mike','Johnson','+1-555-0103'),
(14,'321 Elm St, Houston, TX 77001','sarah.brown@email.com','Sarah','Brown','+1-555-0104'),
(15,'654 Maple Dr, Phoenix, AZ 85001','david.wilson@email.com','David','Wilson','+1-555-0105'),
(16,'987 Cedar Ln, Philadelphia, PA 19101','lisa.garcia@email.com','Lisa','Garcia','+1-555-0106'),
(17,'147 Birch St, San Antonio, TX 78201','robert.martinez@email.com','Robert','Martinez','+1-555-0107'),
(18,'258 Spruce Ave, San Diego, CA 92101','emily.anderson@email.com','Emily','Anderson','+1-555-0108'),
(19,'369 Willow Rd, Dallas, TX 75201','james.taylor@email.com','James','Taylor','+1-555-0109'),
(20,'741 Aspen Dr, San Jose, CA 95101','maria.rodriguez@email.com','Maria','Rodriguez','+1-555-0110'),
(21,'123 Main St, New York, NY 10001','john.doe@email.com','John','Doe','+1-555-0101'),
(22,'456 Oak Ave, Los Angeles, CA 90210','jane.smith@email.com','Jane','Smith','+1-555-0102'),
(23,'789 Pine Rd, Chicago, IL 60601','mike.johnson@email.com','Mike','Johnson','+1-555-0103'),
(24,'321 Elm St, Houston, TX 77001','sarah.brown@email.com','Sarah','Brown','+1-555-0104'),
(25,'654 Maple Dr, Phoenix, AZ 85001','david.wilson@email.com','David','Wilson','+1-555-0105'),
(26,'987 Cedar Ln, Philadelphia, PA 19101','lisa.garcia@email.com','Lisa','Garcia','+1-555-0106'),
(27,'147 Birch St, San Antonio, TX 78201','robert.martinez@email.com','Robert','Martinez','+1-555-0107'),
(28,'258 Spruce Ave, San Diego, CA 92101','emily.anderson@email.com','Emily','Anderson','+1-555-0108'),
(29,'369 Willow Rd, Dallas, TX 75201','james.taylor@email.com','James','Taylor','+1-555-0109'),
(30,'741 Aspen Dr, San Jose, CA 95101','maria.rodriguez@email.com','Maria','Rodriguez','+1-555-0110'),
(31,'123 Main St, New York, NY 10001','john.doe@email.com','John','Doe','+1-555-0101'),
(32,'456 Oak Ave, Los Angeles, CA 90210','jane.smith@email.com','Jane','Smith','+1-555-0102'),
(33,'789 Pine Rd, Chicago, IL 60601','mike.johnson@email.com','Mike','Johnson','+1-555-0103'),
(34,'321 Elm St, Houston, TX 77001','sarah.brown@email.com','Sarah','Brown','+1-555-0104'),
(35,'654 Maple Dr, Phoenix, AZ 85001','david.wilson@email.com','David','Wilson','+1-555-0105'),
(36,'987 Cedar Ln, Philadelphia, PA 19101','lisa.garcia@email.com','Lisa','Garcia','+1-555-0106'),
(37,'147 Birch St, San Antonio, TX 78201','robert.martinez@email.com','Robert','Martinez','+1-555-0107'),
(38,'258 Spruce Ave, San Diego, CA 92101','emily.anderson@email.com','Emily','Anderson','+1-555-0108'),
(39,'369 Willow Rd, Dallas, TX 75201','james.taylor@email.com','James','Taylor','+1-555-0109'),
(40,'741 Aspen Dr, San Jose, CA 95101','maria.rodriguez@email.com','Maria','Rodriguez','+1-555-0110');
/*!40000 ALTER TABLE `customer` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `part_tran`
--

DROP TABLE IF EXISTS `part_tran`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8mb4 */;
CREATE TABLE `part_tran` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `amount` double DEFAULT NULL,
  `tran_type` char(1) DEFAULT NULL,
  `wallet_id` bigint(20) DEFAULT NULL,
  `tran_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKs4bv5kttv40jifckwmpox2nnt` (`wallet_id`),
  KEY `FKe4r3s6t4osot13arta7ta2a64` (`tran_id`),
  CONSTRAINT `FKe4r3s6t4osot13arta7ta2a64` FOREIGN KEY (`tran_id`) REFERENCES `tran_header` (`id`),
  CONSTRAINT `FKs4bv5kttv40jifckwmpox2nnt` FOREIGN KEY (`wallet_id`) REFERENCES `wallet` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `part_tran`
--

LOCK TABLES `part_tran` WRITE;
/*!40000 ALTER TABLE `part_tran` DISABLE KEYS */;
INSERT INTO `part_tran` VALUES
(1,300,'C',2,5635),
(2,-300,'D',1,5635),
(3,3000,'D',2,5636),
(4,-3000,'C',1,5636),
(5,3000,'D',2,5637),
(6,-3000,'C',1,5637);
/*!40000 ALTER TABLE `part_tran` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tran_header`
--

DROP TABLE IF EXISTS `tran_header`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8mb4 */;
CREATE TABLE `tran_header` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `is_verified` bit(1) DEFAULT NULL,
  `posted_date` datetime(6) DEFAULT NULL,
  `verified_date` datetime(6) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5638 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tran_header`
--

LOCK TABLES `tran_header` WRITE;
/*!40000 ALTER TABLE `tran_header` DISABLE KEYS */;
INSERT INTO `tran_header` VALUES
(5635,'',NULL,'2025-08-29 12:45:31.786000'),
(5636,'',NULL,'2025-08-29 12:49:10.218000'),
(5637,'',NULL,'2025-08-31 23:03:37.229000');
/*!40000 ALTER TABLE `tran_header` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `wallet`
--

DROP TABLE IF EXISTS `wallet`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8mb4 */;
CREATE TABLE `wallet` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `balance` double DEFAULT NULL,
  `wallet_name` varchar(255) DEFAULT NULL,
  `wallet_type` enum('CUSTOMER','OFFICE') DEFAULT NULL,
  `customer_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UKfoqy99n2kvofxsmpm7issgqu0` (`wallet_name`),
  KEY `FKpb5ltxtks766lq2b9hgvnr2bq` (`customer_id`),
  CONSTRAINT `FKpb5ltxtks766lq2b9hgvnr2bq` FOREIGN KEY (`customer_id`) REFERENCES `customer` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `wallet`
--

LOCK TABLES `wallet` WRITE;
/*!40000 ALTER TABLE `wallet` DISABLE KEYS */;
INSERT INTO `wallet` VALUES
(1,5700,'CASH','OFFICE',NULL),
(2,6950.5,'Primary Savings','CUSTOMER',1),
(3,750.25,'Business Account','OFFICE',2),
(4,2100.75,'Personal Wallet','CUSTOMER',3),
(5,500,'Emergency Fund','CUSTOMER',4),
(6,3250.8,'Company Expenses','OFFICE',5),
(7,825.4,'Vacation Fund','CUSTOMER',6),
(8,1875.6,'Investment Account','CUSTOMER',7),
(9,650.3,'Office Petty Cash','OFFICE',8),
(10,2950.9,'Main Account','CUSTOMER',9),
(11,1100.15,'Freelance Earnings','CUSTOMER',10);
/*!40000 ALTER TABLE `wallet` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2025-09-01  2:25:03
