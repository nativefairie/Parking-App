
-- MySQL dump 10.13  Distrib 5.7.17, for Win64 (x86_64)
--
-- Host: localhost    Database: parkingapp
-- ------------------------------------------------------
-- Server version	5.7.21-log

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
-- Table structure for table `day_schedule`
--

DROP TABLE IF EXISTS `day_schedule`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `day_schedule` (
  `id_entry` int(11) NOT NULL,
  `date` date NOT NULL,
  `end_hour` time(2) NOT NULL,
  `parking_spot_id` int(11) NOT NULL,
  `start_hour` time(2) NOT NULL,
  `user_email` varchar(255) NOT NULL,
  PRIMARY KEY (`id_entry`),
  UNIQUE KEY `user_email_UNIQUE` (`user_email`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `day_schedule`
--

LOCK TABLES `day_schedule` WRITE;
/*!40000 ALTER TABLE `day_schedule` DISABLE KEYS */;
INSERT INTO `day_schedule` VALUES (94,'2018-04-25','01:00:00.00',30,'11:11:09.00','floroiu.cristina@gmail.com'),(118,'2018-04-25','12:00:00.00',25,'20:55:52.00','admin');
/*!40000 ALTER TABLE `day_schedule` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `hibernate_sequence`
--

DROP TABLE IF EXISTS `hibernate_sequence`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `hibernate_sequence` (
  `next_val` bigint(20) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hibernate_sequence`
--

LOCK TABLES `hibernate_sequence` WRITE;
/*!40000 ALTER TABLE `hibernate_sequence` DISABLE KEYS */;
INSERT INTO `hibernate_sequence` VALUES (119),(119);
/*!40000 ALTER TABLE `hibernate_sequence` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `history`
--

DROP TABLE IF EXISTS `history`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `history` (
  `id_entry` int(10) NOT NULL AUTO_INCREMENT,
  `date` date NOT NULL,
  `start_hour` time(2) NOT NULL,
  `end_hour` time(2) NOT NULL DEFAULT '23:59:00.00',
  `parking_spot` int(10) NOT NULL,
  `user_email` varchar(30) NOT NULL,
  PRIMARY KEY (`id_entry`)
) ENGINE=InnoDB AUTO_INCREMENT=87 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `history`
--

LOCK TABLES `history` WRITE;
/*!40000 ALTER TABLE `history` DISABLE KEYS */;
INSERT INTO `history` VALUES (1,'2011-10-22','09:10:11.00','14:09:13.00',14,'abcd@gmail.com'),
(38,'2018-04-24','01:34:43.00','01:36:56.08',12,'floroiu.cristina@gmail.com'),
(41,'2018-04-24','09:58:21.00','09:58:57.48',12,'floroiu.cristina@gmail.com'),
(43,'2018-04-24','10:09:15.00','10:10:58.47',12,'floroiu.cristina@gmail.com'),
(45,'2018-04-24','10:11:16.00','10:15:02.36',12,'floroiu.cristina@gmail.com'),
(49,'2018-04-19','21:55:52.00','11:05:00.12',13,'floroiu.cristina@gmail.com'),
(50,'2018-04-19','22:55:52.00','11:05:00.19',25,'abcd@gmail.com'),
(51,'2018-04-24','10:21:44.00','11:05:00.21',12,'abcd@gmail.com'),
(52,'2018-04-20','09:24:16.00','11:05:00.23',30,'abcd@gmail.com'),
(55,'2018-04-24','11:30:29.00','11:31:00.02',13,'abcd@gmail.com'),
(56,'2018-04-24','11:10:13.00','11:31:00.04',25,'abcd@gmail.com'),
(59,'2018-04-24','11:34:13.00','11:35:00.01',13,'abcd@gmail.com'),
(60,'2018-04-24','11:34:28.00','11:35:00.04',25,'abcd@gmail.com'),
(73,'2018-04-24','13:29:27.00','16:30:58.00',13,'abcd@gmail.com'),
(74,'2018-04-24','16:30:32.00','16:31:04.00',25,'floroiu.cristina@gmail.com'),
(77,'2018-04-24','16:32:20.00','16:33:29.00',25,'floroiu.cristina@gmail.com'),
(82,'2018-04-24','17:11:53.00','17:23:54.00',25,'floroiu.cristina@gmail.com'),
(85,'2018-04-24','19:24:01.00','23:48:17.00',30,'floroiu.cristina@gmail.com'),
(86,'2018-04-24','17:25:50.00','00:35:19.00',25,'floroiu.cristina@gmail.com');
/*!40000 ALTER TABLE `history` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `parking_spots`
--

DROP TABLE IF EXISTS `parking_spots`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `parking_spots` (
  `parking_spot_id` int(11) NOT NULL,
  `status` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`parking_spot_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `parking_spots`
--

LOCK TABLES `parking_spots` WRITE;
/*!40000 ALTER TABLE `parking_spots` DISABLE KEYS */;
INSERT INTO `parking_spots` VALUES (1,2),(13,0),(25,1),(30,1);
/*!40000 ALTER TABLE `parking_spots` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `users` (
  `user_email` varchar(50) NOT NULL,
  `user_password` varchar(80) NOT NULL,
  `cookie` varchar(80) DEFAULT NULL,
  PRIMARY KEY (`user_email`),
  UNIQUE KEY `user_email_UNIQUE` (`user_email`),
  UNIQUE KEY `cookie_UNIQUE` (`cookie`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES ('admin','8c6976e5b5410415bde908bd4dee15dfb167a9c873fc4bb8a81f6f2ab448a918','8c6976e5b5410415bde908bd4dee15dfb167a9c873fc4bb8a81f6f2ab448a918'),('admin@db.ro','8c6976e5b5410415bde908bd4dee15dfb167a9c873fc4bb8a81f6f2ab448a918','ac75f6232aa3c5be99ed53badad3c0055afd84223da52d38c4a691a785567e07');
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping routines for database 'dbparking'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-04-25 20:59:56
