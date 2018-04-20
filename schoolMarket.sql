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
  `last_login_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
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
  `auction_id` char(36) NOT NULL,
  `auction_price` int(11) DEFAULT NULL,
  `auction_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `auction_product_id` char(36) DEFAULT NULL,
  `auction_user_id` char(36) DEFAULT NULL,
  PRIMARY KEY (`auction_id`),
  KEY `auction_record_product_fk` (`auction_product_id`),
  KEY `auction_record_user_fk` (`auction_user_id`),
  CONSTRAINT `auction_record_product_fk` FOREIGN KEY (`auction_product_id`) REFERENCES `product` (`product_id`),
  CONSTRAINT `auction_record_user_fk` FOREIGN KEY (`auction_user_id`) REFERENCES `user` (`user_id`)
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
  `category_id` char(36) NOT NULL,
  `category_name` varchar(10) NOT NULL,
  PRIMARY KEY (`category_id`),
  UNIQUE KEY `category_name_uindex` (`category_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `category`
--

LOCK TABLES `category` WRITE;
/*!40000 ALTER TABLE `category` DISABLE KEYS */;
INSERT INTO `category` VALUES ('adfasdfas','数码电子');
/*!40000 ALTER TABLE `category` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `comment`
--

DROP TABLE IF EXISTS `comment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `comment` (
  `comment_id` char(36) NOT NULL,
  `comment_content` tinytext NOT NULL,
  `comment_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `comment_like` int(11) NOT NULL DEFAULT '0',
  `comment_dislike` int(11) NOT NULL DEFAULT '0',
  `comment_product_id` char(36) DEFAULT NULL,
  `comment_user_id` char(36) DEFAULT NULL,
  `comment_status` tinyint(4) NOT NULL DEFAULT '1' COMMENT '1 审核中 0 未通过 2 通过',
  `comment_level` tinyint(4) NOT NULL DEFAULT '0' COMMENT '0 1 2 3 4 5星',
  PRIMARY KEY (`comment_id`),
  KEY `comment_product_fk` (`comment_product_id`),
  KEY `comment_user_fk` (`comment_user_id`),
  CONSTRAINT `comment_product_fk` FOREIGN KEY (`comment_product_id`) REFERENCES `product` (`product_id`),
  CONSTRAINT `comment_user_fk` FOREIGN KEY (`comment_user_id`) REFERENCES `user` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `comment`
--

LOCK TABLES `comment` WRITE;
/*!40000 ALTER TABLE `comment` DISABLE KEYS */;
INSERT INTO `comment` VALUES ('e07aec2d-7217-4d05-8078-31b3fa2bf0a6','Hello world!','2018-04-18 08:56:39',0,0,'adfadf','22222222222',1,2),('sdfdsfdssfs','cvcxxxxxxxxxxxxxx','2018-04-18 04:11:25',2,1,'adfadf','234234324234',1,0);
/*!40000 ALTER TABLE `comment` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `notification`
--

DROP TABLE IF EXISTS `notification`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `notification` (
  `notification_id` char(36) NOT NULL,
  `notification_title` varchar(30) NOT NULL DEFAULT '无标题',
  `notification_content` tinytext NOT NULL,
  `notification_target` char(36) DEFAULT NULL,
  PRIMARY KEY (`notification_id`),
  KEY `notification_user_fk` (`notification_target`),
  CONSTRAINT `notification_user_fk` FOREIGN KEY (`notification_target`) REFERENCES `user` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `notification`
--

LOCK TABLES `notification` WRITE;
/*!40000 ALTER TABLE `notification` DISABLE KEYS */;
INSERT INTO `notification` VALUES ('85b27367-fb08-4ab5-983b-19aa3a5c5f7d','您的订单有了新状态!','您的编号为：072a58c3-0a34-463f-9d65-2fb8717b5d4c的订单已发货！','234234324234'),('sdfds','sdfsd','3243232','22222222222');
/*!40000 ALTER TABLE `notification` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `order`
--

DROP TABLE IF EXISTS `order`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `order` (
  `order_id` char(36) NOT NULL,
  `order_number` int(11) DEFAULT NULL,
  `order_order_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `order_finish_time` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  `order_status` tinyint(4) NOT NULL DEFAULT '1' COMMENT '1 未派送 2 派送中 3 已完成',
  `order_user_id` char(36) DEFAULT NULL,
  `order_product_id` char(36) DEFAULT NULL,
  `order_total_price` int(11) NOT NULL DEFAULT '0',
  `order_price` int(11) NOT NULL DEFAULT '0',
  `order_target` char(36) DEFAULT NULL,
  PRIMARY KEY (`order_id`),
  KEY `order_user_fk` (`order_user_id`),
  KEY `order_product_fk` (`order_product_id`),
  KEY `order_user_fk2` (`order_target`),
  CONSTRAINT `order_product_fk` FOREIGN KEY (`order_product_id`) REFERENCES `product` (`product_id`),
  CONSTRAINT `order_user_fk` FOREIGN KEY (`order_user_id`) REFERENCES `user` (`user_id`),
  CONSTRAINT `order_user_fk2` FOREIGN KEY (`order_target`) REFERENCES `user` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `order`
--

LOCK TABLES `order` WRITE;
/*!40000 ALTER TABLE `order` DISABLE KEYS */;
INSERT INTO `order` VALUES ('072a58c3-0a34-463f-9d65-2fb8717b5d4c',1,'2018-04-18 07:29:11','2018-04-19 07:14:12',2,'22222222222','adfadf',23,23,'234234324234'),('893bdbc7-e316-482d-b6c5-5b3712da8224',1,'2018-04-18 07:33:29','2018-04-18 07:38:17',1,'22222222222','adfadf',23,23,'234234324234'),('e851eba3-db5b-4293-b70f-c74333e85838',1,'2018-04-18 07:38:17','2018-04-19 04:45:54',3,'22222222222','adfadf',23,23,'234234324234');
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
  `product_id` char(36) NOT NULL,
  `product_name` varchar(30) NOT NULL,
  `product_price` int(11) DEFAULT NULL,
  `product_disc` tinytext COMMENT '商品描述',
  `product_category_id` char(36) DEFAULT NULL COMMENT '商品类别',
  `product_release_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '发布时间',
  `product_type` tinyint(4) NOT NULL DEFAULT '1' COMMENT '1 常规商品 2 二手商品 3拍卖商品',
  `product_status` tinyint(4) NOT NULL DEFAULT '1' COMMENT '1 销售中 0 禁止销售 2 审核中 3 已下架',
  `product_number` int(11) DEFAULT NULL COMMENT '库存数量',
  `product_publisher` char(36) DEFAULT NULL,
  `product_imga` varchar(60) DEFAULT NULL,
  `product_imgb` varchar(60) DEFAULT NULL,
  `product_imgc` varchar(60) DEFAULT NULL,
  `product_imgd` varchar(60) DEFAULT NULL,
  `product_comment` tinyint(1) NOT NULL DEFAULT '1' COMMENT '是否开启评论功能',
  PRIMARY KEY (`product_id`),
  KEY `product_category_fk` (`product_category_id`),
  KEY `product_user_fk` (`product_publisher`),
  CONSTRAINT `product_category_fk` FOREIGN KEY (`product_category_id`) REFERENCES `category` (`category_id`),
  CONSTRAINT `product_user_fk` FOREIGN KEY (`product_publisher`) REFERENCES `user` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `product`
--

LOCK TABLES `product` WRITE;
/*!40000 ALTER TABLE `product` DISABLE KEYS */;
INSERT INTO `product` VALUES ('adfadf','sdf',44,'agasfdsafa','adfasdfas','2018-04-18 04:09:30',1,1,120,'234234324234',NULL,NULL,NULL,NULL,1);
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
  `shoppingcart_number` int(11) NOT NULL COMMENT '数量',
  `shoppingcart_add_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `shoppingcart_user_id` char(36) DEFAULT NULL,
  `shoppingcart_product_id` char(36) DEFAULT NULL,
  `shoppingcart_id` char(36) NOT NULL,
  PRIMARY KEY (`shoppingcart_id`),
  KEY `shoppingcart_user_fk` (`shoppingcart_user_id`),
  KEY `shoppingcart_product_fk` (`shoppingcart_product_id`),
  CONSTRAINT `shoppingcart_product_fk` FOREIGN KEY (`shoppingcart_product_id`) REFERENCES `product` (`product_id`),
  CONSTRAINT `shoppingcart_user_fk` FOREIGN KEY (`shoppingcart_user_id`) REFERENCES `user` (`user_id`)
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
  `user_id` char(36) NOT NULL,
  `user_name` varchar(30) NOT NULL,
  `user_gender` tinyint(4) NOT NULL DEFAULT '1' COMMENT '1 男 0 女',
  `user_identify` varchar(30) NOT NULL COMMENT '身份证',
  `user_location` varchar(60) NOT NULL COMMENT '收货地址',
  `user_status` tinyint(4) NOT NULL DEFAULT '1' COMMENT '1 正常 0 禁用',
  `user_email` varchar(60) NOT NULL,
  `user_phone` varchar(20) NOT NULL,
  `user_register_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `user_last_login_time` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  `user_password` varchar(20) NOT NULL,
  `user_head_img` varchar(60) NOT NULL DEFAULT '/upload/defaultUserIcon.png',
  `user_alipay` varchar(60) DEFAULT NULL COMMENT '支付宝收钱码',
  PRIMARY KEY (`user_id`),
  UNIQUE KEY `user_email_uindex` (`user_email`),
  UNIQUE KEY `user_phone_uindex` (`user_phone`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES ('22222222222','dfdgxcv',1,'23333333333','sssssssssss',1,'23432@ss.com','43543543534','2018-04-18 04:22:45','0000-00-00 00:00:00','4354sdfsd','ssss.png','dsfsd.png'),('234234324234','sdfds',1,'124214214124214214','heeee',1,'143432@qq.com','15510841744','2018-04-18 03:04:33','2018-04-19 01:41:32','12345566666','/upload/defaultUserIcon.png','alipay.png'),('43c0e4a3-6972-4a7b-9b96-bb6ffa9cf42e','sssss',1,'324444444234234','sdfsdfdsfdsfsdf',1,'23423423422@qq.com','234543543543','2018-04-18 04:33:45','0000-00-00 00:00:00','12322423423','sdfsdfsdf.png','sdfsdfsdsss.png');
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

-- Dump completed on 2018-04-20  9:59:16
