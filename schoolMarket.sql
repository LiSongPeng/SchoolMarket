-- MySQL dump 10.13  Distrib 5.7.20, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: schoolmarket
-- ------------------------------------------------------
-- Server version	5.7.20-log

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
-- Table structure for table `admin`
--

DROP TABLE IF EXISTS `admin`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `admin` (
  `id` char(36) NOT NULL,
  `name` varchar(30) NOT NULL,
  `gender` tinyint(4) DEFAULT '1' COMMENT '1 男 0 女',
  `status` tinyint(4) DEFAULT '1' COMMENT '1 正常 0 禁用',
  `email` varchar(60) NOT NULL,
  `phone` varchar(20) NOT NULL,
  `last_login_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `password` varchar(20) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `admin`
--

LOCK TABLES `admin` WRITE;
/*!40000 ALTER TABLE `admin` DISABLE KEYS */;
/*!40000 ALTER TABLE `admin` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `admin_role`
--

DROP TABLE IF EXISTS `admin_role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `admin_role` (
  `id` char(36) NOT NULL,
  `admin_id` char(36) DEFAULT NULL,
  `role_id` char(36) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `admin_role_admin_fk` (`admin_id`),
  KEY `admin_role_role_fk` (`role_id`),
  CONSTRAINT `admin_role_admin_fk` FOREIGN KEY (`admin_id`) REFERENCES `admin` (`id`),
  CONSTRAINT `admin_role_role_fk` FOREIGN KEY (`role_id`) REFERENCES `role` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `admin_role`
--

LOCK TABLES `admin_role` WRITE;
/*!40000 ALTER TABLE `admin_role` DISABLE KEYS */;
/*!40000 ALTER TABLE `admin_role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `auction_record`
--

DROP TABLE IF EXISTS `auction_record`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `auction_record` (
  `id` char(36) NOT NULL,
  `price` int(11) DEFAULT NULL,
  `time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `product_id` char(36) DEFAULT NULL,
  `user_id` char(36) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `auction_record_product_fk` (`product_id`),
  KEY `auction_record_user_fk` (`user_id`),
  CONSTRAINT `auction_record_product_fk` FOREIGN KEY (`product_id`) REFERENCES `product` (`id`),
  CONSTRAINT `auction_record_user_fk` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `auction_record`
--

LOCK TABLES `auction_record` WRITE;
/*!40000 ALTER TABLE `auction_record` DISABLE KEYS */;
/*!40000 ALTER TABLE `auction_record` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `category`
--

DROP TABLE IF EXISTS `category`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `category` (
  `id` char(36) NOT NULL,
  `name` varchar(10) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `category_name_uindex` (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `category`
--

LOCK TABLES `category` WRITE;
/*!40000 ALTER TABLE `category` DISABLE KEYS */;
/*!40000 ALTER TABLE `category` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `comment`
--

DROP TABLE IF EXISTS `comment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `comment` (
  `id` char(36) NOT NULL,
  `content` tinytext NOT NULL,
  `time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `like` int(11) NOT NULL DEFAULT '0',
  `dislike` int(11) NOT NULL DEFAULT '0',
  `product_id` char(36) DEFAULT NULL,
  `user_id` char(36) DEFAULT NULL,
  `status` tinyint(4) NOT NULL DEFAULT '1' COMMENT '1 审核中 0 未通过 2 通过',
  `level` tinyint(4) NOT NULL DEFAULT '0' COMMENT '0 1 2 3 4 5星',
  PRIMARY KEY (`id`),
  KEY `comment_product_fk` (`product_id`),
  KEY `comment_user_fk` (`user_id`),
  CONSTRAINT `comment_product_fk` FOREIGN KEY (`product_id`) REFERENCES `product` (`id`),
  CONSTRAINT `comment_user_fk` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `comment`
--

LOCK TABLES `comment` WRITE;
/*!40000 ALTER TABLE `comment` DISABLE KEYS */;
/*!40000 ALTER TABLE `comment` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `notification`
--

DROP TABLE IF EXISTS `notification`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `notification` (
  `id` char(36) NOT NULL,
  `title` varchar(30) NOT NULL DEFAULT '无标题',
  `content` tinytext NOT NULL,
  `target` char(36) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `notification_user_fk` (`target`),
  CONSTRAINT `notification_user_fk` FOREIGN KEY (`target`) REFERENCES `user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `notification`
--

LOCK TABLES `notification` WRITE;
/*!40000 ALTER TABLE `notification` DISABLE KEYS */;
/*!40000 ALTER TABLE `notification` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `order`
--

DROP TABLE IF EXISTS `order`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `order` (
  `id` char(36) NOT NULL,
  `number` int(11) DEFAULT NULL,
  `order_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `finish_time` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  `status` tinyint(4) NOT NULL DEFAULT '1' COMMENT '1 未派送 2 派送中 3 已完成',
  `user_id` char(36) DEFAULT NULL,
  `product_id` char(36) DEFAULT NULL,
  `total_price` int(11) NOT NULL DEFAULT '0',
  `price` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`),
  KEY `order_user_fk` (`user_id`),
  KEY `order_product_fk` (`product_id`),
  CONSTRAINT `order_product_fk` FOREIGN KEY (`product_id`) REFERENCES `product` (`id`),
  CONSTRAINT `order_user_fk` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `order`
--

LOCK TABLES `order` WRITE;
/*!40000 ALTER TABLE `order` DISABLE KEYS */;
/*!40000 ALTER TABLE `order` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `permission`
--

DROP TABLE IF EXISTS `permission`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `permission` (
  `id` char(36) NOT NULL,
  `name` varchar(30) NOT NULL,
  `url` varchar(60) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `permission`
--

LOCK TABLES `permission` WRITE;
/*!40000 ALTER TABLE `permission` DISABLE KEYS */;
/*!40000 ALTER TABLE `permission` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `product`
--

DROP TABLE IF EXISTS `product`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `product` (
  `id` char(36) NOT NULL,
  `name` varchar(30) NOT NULL,
  `price` int(11) NOT NULL DEFAULT '9999999',
  `disc` tinytext COMMENT '商品描述',
  `category_id` char(36) DEFAULT NULL COMMENT '商品类别',
  `release_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '发布时间',
  `type` tinyint(4) NOT NULL DEFAULT '1' COMMENT '1 常规商品 2 二手商品 3拍卖商品',
  `status` tinyint(4) NOT NULL DEFAULT '1' COMMENT '1 销售中 0 禁止销售 2 审核中 3 已下架',
  `number` int(11) DEFAULT NULL COMMENT '库存数量',
  `publisher` char(36) DEFAULT NULL,
  `imga` varchar(60) DEFAULT NULL,
  `imgb` varchar(60) DEFAULT NULL,
  `imgc` varchar(60) DEFAULT NULL,
  `imgd` varchar(60) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `product_category_fk` (`category_id`),
  KEY `product_user_fk` (`publisher`),
  CONSTRAINT `product_category_fk` FOREIGN KEY (`category_id`) REFERENCES `category` (`id`),
  CONSTRAINT `product_user_fk` FOREIGN KEY (`publisher`) REFERENCES `user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `product`
--

LOCK TABLES `product` WRITE;
/*!40000 ALTER TABLE `product` DISABLE KEYS */;
/*!40000 ALTER TABLE `product` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `role`
--

DROP TABLE IF EXISTS `role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `role` (
  `name` varchar(30) NOT NULL,
  `id` char(36) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `role`
--

LOCK TABLES `role` WRITE;
/*!40000 ALTER TABLE `role` DISABLE KEYS */;
/*!40000 ALTER TABLE `role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `role_permission`
--

DROP TABLE IF EXISTS `role_permission`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `role_permission` (
  `id` char(36) NOT NULL,
  `role_id` char(36) DEFAULT NULL,
  `permission_id` char(36) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `role_permission_role_fk` (`role_id`),
  KEY `role_permission_permission_fk` (`permission_id`),
  CONSTRAINT `role_permission_permission_fk` FOREIGN KEY (`permission_id`) REFERENCES `permission` (`id`),
  CONSTRAINT `role_permission_role_fk` FOREIGN KEY (`role_id`) REFERENCES `role` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `role_permission`
--

LOCK TABLES `role_permission` WRITE;
/*!40000 ALTER TABLE `role_permission` DISABLE KEYS */;
/*!40000 ALTER TABLE `role_permission` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `shoppingcart`
--

DROP TABLE IF EXISTS `shoppingcart`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `shoppingcart` (
  `number` int(11) NOT NULL COMMENT '数量',
  `add_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `user_id` char(36) DEFAULT NULL,
  `product_id` char(36) DEFAULT NULL,
  `id` char(36) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `shoppingcart_user_fk` (`user_id`),
  KEY `shoppingcart_product_fk` (`product_id`),
  CONSTRAINT `shoppingcart_product_fk` FOREIGN KEY (`product_id`) REFERENCES `product` (`id`),
  CONSTRAINT `shoppingcart_user_fk` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `shoppingcart`
--

LOCK TABLES `shoppingcart` WRITE;
/*!40000 ALTER TABLE `shoppingcart` DISABLE KEYS */;
/*!40000 ALTER TABLE `shoppingcart` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user` (
  `id` char(36) NOT NULL,
  `name` varchar(30) NOT NULL,
  `gender` tinyint(4) NOT NULL DEFAULT '1' COMMENT '1 男 0 女',
  `identify` varchar(30) NOT NULL COMMENT '身份证',
  `location` varchar(60) NOT NULL COMMENT '收货地址',
  `status` tinyint(4) NOT NULL DEFAULT '1' COMMENT '1 正常 0 禁用',
  `email` varchar(60) NOT NULL,
  `phone` varchar(20) NOT NULL,
  `register_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `last_login_time` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  `password` varchar(20) NOT NULL,
  `head_img` varchar(60) NOT NULL DEFAULT '/upload/defaultUserIcon.png',
  `alipay` varchar(60) DEFAULT NULL COMMENT '支付宝收钱码',
  PRIMARY KEY (`id`),
  UNIQUE KEY `user_email_uindex` (`email`),
  UNIQUE KEY `user_phone_uindex` (`phone`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-04-17 20:54:38
