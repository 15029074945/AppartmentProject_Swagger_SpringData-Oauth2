-- MySQL dump 10.13  Distrib 5.7.17, for Linux (x86_64)
--
-- Host: localhost    Database: BOOKING
-- ------------------------------------------------------
-- Server version	5.7.17-0ubuntu0.16.04.1

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
-- Table structure for table `ADDRESS`
--

DROP TABLE IF EXISTS `ADDRESS`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ADDRESS` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `COUNTRY` varchar(50) DEFAULT NULL,
  `CITY` varchar(50) DEFAULT NULL,
  `REGION` varchar(50) DEFAULT NULL,
  `STREET` varchar(50) DEFAULT NULL,
  `APPARTMENT` varchar(50) DEFAULT NULL,
  `LATITUDE` decimal(11,8) DEFAULT NULL,
  `LONGITUDE` decimal(11,8) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ADDRESS`
--

LOCK TABLES `ADDRESS` WRITE;
/*!40000 ALTER TABLE `ADDRESS` DISABLE KEYS */;
INSERT INTO `ADDRESS` VALUES (1,'country','city','region','street','appartment',12.20000000,14.40000000);
/*!40000 ALTER TABLE `ADDRESS` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ANNOUNCEMENT`
--

DROP TABLE IF EXISTS `ANNOUNCEMENT`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ANNOUNCEMENT` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `HIDDEN` tinyint(1) DEFAULT NULL,
  `USER_ID` int(11) DEFAULT NULL,
  `ADDRESS_ID` int(11) DEFAULT NULL,
  `ROOM` int(11) DEFAULT NULL,
  `LIVING_PLACES` int(11) DEFAULT NULL,
  `TITLE` varchar(50) DEFAULT NULL,
  `SHORT_DESCRIPTION` varchar(50) DEFAULT NULL,
  `CREATED_DATE` datetime DEFAULT NULL,
  `UPDATED_DATE` datetime DEFAULT NULL,
  `ACTIVE` tinyint(1) DEFAULT '1',
  PRIMARY KEY (`ID`),
  KEY `fk_ANNOUNSMENT_USER_idx` (`USER_ID`),
  KEY `fk_ANNOUNSMENT_ADDRESS_idx` (`ADDRESS_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ANNOUNCEMENT`
--

LOCK TABLES `ANNOUNCEMENT` WRITE;
/*!40000 ALTER TABLE `ANNOUNCEMENT` DISABLE KEYS */;
INSERT INTO `ANNOUNCEMENT` VALUES (1,1,1,1,0,0,'string','string',NULL,NULL,1),(4,1,10,NULL,0,0,'string','string',NULL,NULL,NULL),(5,1,1,NULL,0,0,'string','string',NULL,NULL,NULL),(6,1,10,NULL,0,0,'string','string',NULL,NULL,NULL),(7,1,1,NULL,0,0,'string','string',NULL,NULL,NULL),(9,1,10,NULL,0,0,'string','string',NULL,NULL,NULL),(10,0,10,NULL,0,5,'sss','sss',NULL,NULL,NULL),(12,1,10,NULL,0,0,'string','111',NULL,NULL,NULL);
/*!40000 ALTER TABLE `ANNOUNCEMENT` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ANNOUNCEMENT_FACILITY`
--

DROP TABLE IF EXISTS `ANNOUNCEMENT_FACILITY`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ANNOUNCEMENT_FACILITY` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `ENABLED` tinyint(1) DEFAULT NULL,
  `ANNOUNCEMENT_ID` int(11) DEFAULT NULL,
  `FACILITY_ID` int(11) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  UNIQUE KEY `ANNOUNCEMENT_FACILITY_idx` (`FACILITY_ID`,`ANNOUNCEMENT_ID`),
  KEY `fk_ANNOUNSMENT_FACILITY_ANNOUNSMENT_idx` (`ANNOUNCEMENT_ID`,`FACILITY_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ANNOUNCEMENT_FACILITY`
--

LOCK TABLES `ANNOUNCEMENT_FACILITY` WRITE;
/*!40000 ALTER TABLE `ANNOUNCEMENT_FACILITY` DISABLE KEYS */;
/*!40000 ALTER TABLE `ANNOUNCEMENT_FACILITY` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ANNOUNCEMENT_IMAGE`
--

DROP TABLE IF EXISTS `ANNOUNCEMENT_IMAGE`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ANNOUNCEMENT_IMAGE` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `TITLE` varchar(50) DEFAULT NULL,
  `ANNOUNCEMENT_ID` int(11) DEFAULT NULL,
  `IMAGE_ID` int(11) DEFAULT NULL,
  `ACTIVE` tinyint(1) DEFAULT '1',
  PRIMARY KEY (`ID`),
  KEY `fk_ANNOUNSMENT_IMAGE_IMAGE_idx` (`IMAGE_ID`),
  KEY `fk_ANNOUNSMENT_IMAGE_ANNOUNSMENT_idx` (`ANNOUNCEMENT_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ANNOUNCEMENT_IMAGE`
--

LOCK TABLES `ANNOUNCEMENT_IMAGE` WRITE;
/*!40000 ALTER TABLE `ANNOUNCEMENT_IMAGE` DISABLE KEYS */;
/*!40000 ALTER TABLE `ANNOUNCEMENT_IMAGE` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ATTENDER`
--

DROP TABLE IF EXISTS `ATTENDER`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ATTENDER` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `USER_ID` int(11) DEFAULT NULL,
  `CONVERSATION_ID` int(11) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `fk_ATTENDER_USER_idx` (`USER_ID`),
  KEY `fk_ATTENDER_CONVERSATION_idx` (`CONVERSATION_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ATTENDER`
--

LOCK TABLES `ATTENDER` WRITE;
/*!40000 ALTER TABLE `ATTENDER` DISABLE KEYS */;
/*!40000 ALTER TABLE `ATTENDER` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `BOOKING_REQUEST`
--

DROP TABLE IF EXISTS `BOOKING_REQUEST`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `BOOKING_REQUEST` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `CHECK_IN` date DEFAULT NULL,
  `CHECK_OUT` date DEFAULT NULL,
  `USER_ID` int(11) DEFAULT NULL,
  `ANNOUNCEMENT_ID` int(11) DEFAULT NULL,
  `CREATED_DATE` datetime DEFAULT NULL,
  `UPDATED_DATE` datetime DEFAULT NULL,
  `ACTIVE` tinyint(1) DEFAULT '1',
  PRIMARY KEY (`ID`),
  KEY `fk_BOOKING_REQUEST_USER_idx` (`USER_ID`),
  KEY `fk_BOOKING_REQUEST_ANNOUNSMENT_idx` (`ANNOUNCEMENT_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `BOOKING_REQUEST`
--

LOCK TABLES `BOOKING_REQUEST` WRITE;
/*!40000 ALTER TABLE `BOOKING_REQUEST` DISABLE KEYS */;
/*!40000 ALTER TABLE `BOOKING_REQUEST` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `COMMENT`
--

DROP TABLE IF EXISTS `COMMENT`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `COMMENT` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `TEXT` longtext,
  `DATE` datetime DEFAULT NULL,
  `USER_ID` int(11) DEFAULT NULL,
  `ANNOUNCEMENT_ID` int(11) DEFAULT NULL,
  `CREATED_DATE` datetime DEFAULT NULL,
  `UPDATED_DATE` datetime DEFAULT NULL,
  `ACTIVE` tinyint(1) DEFAULT '1',
  PRIMARY KEY (`ID`),
  KEY `fk_COMMENT_USER_idx` (`USER_ID`),
  KEY `fk_COMMENT_ANNOUNSMENT_idx` (`ANNOUNCEMENT_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `COMMENT`
--

LOCK TABLES `COMMENT` WRITE;
/*!40000 ALTER TABLE `COMMENT` DISABLE KEYS */;
/*!40000 ALTER TABLE `COMMENT` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `CONVERSATION`
--

DROP TABLE IF EXISTS `CONVERSATION`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `CONVERSATION` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `ANNOUNCEMENT_ID` int(11) DEFAULT NULL,
  `CREATED_DATE` datetime DEFAULT NULL,
  `UPDATED_DATE` datetime DEFAULT NULL,
  `ACTIVE` tinyint(1) DEFAULT '1',
  PRIMARY KEY (`ID`),
  KEY `fk_CONVERSATION_ANNOUNSMENT_idx` (`ANNOUNCEMENT_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `CONVERSATION`
--

LOCK TABLES `CONVERSATION` WRITE;
/*!40000 ALTER TABLE `CONVERSATION` DISABLE KEYS */;
/*!40000 ALTER TABLE `CONVERSATION` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `FACILITY`
--

DROP TABLE IF EXISTS `FACILITY`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `FACILITY` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `TITLE` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `FACILITY`
--

LOCK TABLES `FACILITY` WRITE;
/*!40000 ALTER TABLE `FACILITY` DISABLE KEYS */;
/*!40000 ALTER TABLE `FACILITY` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `FAVORITE`
--

DROP TABLE IF EXISTS `FAVORITE`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `FAVORITE` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `USER_ID` int(11) DEFAULT NULL,
  `ANNOUNCEMENT_ID` int(11) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `fk_FAVOURITE_USER_idx` (`USER_ID`),
  KEY `fk_FAVOURITE_ANNOUNSMENT_idx` (`ANNOUNCEMENT_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `FAVORITE`
--

LOCK TABLES `FAVORITE` WRITE;
/*!40000 ALTER TABLE `FAVORITE` DISABLE KEYS */;
/*!40000 ALTER TABLE `FAVORITE` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `IMAGE`
--

DROP TABLE IF EXISTS `IMAGE`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `IMAGE` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `DATA` longblob,
  `MIME` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `IMAGE`
--

LOCK TABLES `IMAGE` WRITE;
/*!40000 ALTER TABLE `IMAGE` DISABLE KEYS */;
/*!40000 ALTER TABLE `IMAGE` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `MESSAGE`
--

DROP TABLE IF EXISTS `MESSAGE`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `MESSAGE` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `TEXT` longtext,
  `RECEIVED` tinyint(1) DEFAULT NULL,
  `SENDER_ID` int(11) DEFAULT NULL,
  `CONVERSATION_ID` int(11) DEFAULT NULL,
  `CREATED_DATE` datetime DEFAULT NULL,
  `UPDATED_DATE` datetime DEFAULT NULL,
  `ACTIVE` tinyint(1) DEFAULT '1',
  PRIMARY KEY (`ID`),
  KEY `fk_MESSAGE_USER_idx` (`SENDER_ID`),
  KEY `fk_MESSAGE_CONVERSATION_idx` (`CONVERSATION_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `MESSAGE`
--

LOCK TABLES `MESSAGE` WRITE;
/*!40000 ALTER TABLE `MESSAGE` DISABLE KEYS */;
/*!40000 ALTER TABLE `MESSAGE` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `PRICE`
--

DROP TABLE IF EXISTS `PRICE`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `PRICE` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `PRICE` decimal(10,0) DEFAULT NULL,
  `TYPE` varchar(50) DEFAULT NULL,
  `ANNOUNCEMENT_ID` int(11) DEFAULT NULL,
  `ACTIVE` tinyint(1) DEFAULT '1',
  PRIMARY KEY (`ID`),
  KEY `fk_PRICE_ANNOUNSMENT_idx` (`ANNOUNCEMENT_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `PRICE`
--

LOCK TABLES `PRICE` WRITE;
/*!40000 ALTER TABLE `PRICE` DISABLE KEYS */;
/*!40000 ALTER TABLE `PRICE` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `PRICE_TYPE`
--

DROP TABLE IF EXISTS `PRICE_TYPE`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `PRICE_TYPE` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `TYPE` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `PRICE_TYPE`
--

LOCK TABLES `PRICE_TYPE` WRITE;
/*!40000 ALTER TABLE `PRICE_TYPE` DISABLE KEYS */;
INSERT INTO `PRICE_TYPE` VALUES (1,'PER DAY'),(2,'PER MONTHS');
/*!40000 ALTER TABLE `PRICE_TYPE` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `USER`
--

DROP TABLE IF EXISTS `USER`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `USER` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `FIRST_NAME` varchar(50) DEFAULT NULL,
  `LAST_NAME` varchar(50) DEFAULT NULL,
  `EMAIL` varchar(50) DEFAULT NULL,
  `PASSWORD` varchar(50) DEFAULT NULL,
  `IMAGE_ID` int(11) DEFAULT NULL,
  `CREATED_DATE` datetime DEFAULT NULL,
  `UPDATED_DATE` datetime DEFAULT NULL,
  `ACTIVE` tinyint(1) DEFAULT '1',
  `STATUS` tinyint(1) DEFAULT NULL,
  `VERIFICATION_KEY` varchar(50) DEFAULT NULL,
  `UPDATE_DATE` datetime DEFAULT NULL,
  PRIMARY KEY (`ID`),
  UNIQUE KEY `EMAIL_UNIQUE` (`EMAIL`),
  KEY `fk_USER_1_idx` (`IMAGE_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `USER`
--

LOCK TABLES `USER` WRITE;
/*!40000 ALTER TABLE `USER` DISABLE KEYS */;
INSERT INTO `USER` VALUES (1,'string','string','1','1',0,'2017-03-20 15:36:33','2017-03-20 15:36:35',1,1,NULL,'2017-03-20 15:36:44'),(10,'em','str','email','password',1010,'2017-03-16 01:14:01','2017-03-16 01:14:03',1,1,'5',NULL),(20,'firstName','lastName','str','str',0,NULL,NULL,1,1,NULL,NULL);
/*!40000 ALTER TABLE `USER` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `USER_EMAIL`
--

DROP TABLE IF EXISTS `USER_EMAIL`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `USER_EMAIL` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `USER_ID` int(11) DEFAULT NULL,
  `EMAIL` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `fk_USER_EMAIL_1_idx` (`USER_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `USER_EMAIL`
--

LOCK TABLES `USER_EMAIL` WRITE;
/*!40000 ALTER TABLE `USER_EMAIL` DISABLE KEYS */;
INSERT INTO `USER_EMAIL` VALUES (1,10,'email extra'),(2,10,'email extra 2'),(17,10,'test1'),(18,10,'test2'),(19,10,'test3');
/*!40000 ALTER TABLE `USER_EMAIL` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `USER_TELEPHONE`
--

DROP TABLE IF EXISTS `USER_TELEPHONE`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `USER_TELEPHONE` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `USER_ID` int(11) DEFAULT NULL,
  `TELEPHONE` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `fk_USER_TELEPHONE_1_idx` (`USER_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `USER_TELEPHONE`
--

LOCK TABLES `USER_TELEPHONE` WRITE;
/*!40000 ALTER TABLE `USER_TELEPHONE` DISABLE KEYS */;
INSERT INTO `USER_TELEPHONE` VALUES (1,10,'11111'),(2,10,'22222'),(3,10,'33333'),(4,10,'44444');
/*!40000 ALTER TABLE `USER_TELEPHONE` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `hibernate_sequence`
--

DROP TABLE IF EXISTS `hibernate_sequence`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `hibernate_sequence` (
  `next_val` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hibernate_sequence`
--

LOCK TABLES `hibernate_sequence` WRITE;
/*!40000 ALTER TABLE `hibernate_sequence` DISABLE KEYS */;
INSERT INTO `hibernate_sequence` VALUES (42);
/*!40000 ALTER TABLE `hibernate_sequence` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2017-03-26 19:52:03
