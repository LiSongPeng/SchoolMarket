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
INSERT INTO `auction_record` VALUES ('23423423423',2222,'2018-04-26 08:41:47','adsfasdfad','c07455bc-d21a-490f-88f3-0401d1899998');
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
  `comment_product_id` char(36) DEFAULT NULL,
  `comment_user_id` char(36) DEFAULT NULL,
  `comment_status` tinyint(4) NOT NULL DEFAULT '1' COMMENT '1 审核中 0 未通过 2 通过',
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
INSERT INTO `comment` VALUES ('23dd410b-9291-4577-a52d-e69a6477723b','weafdsfadsfadsfadsfadf','2018-04-26 04:31:22','200a7429-1e48-4ca6-bf53-3eed9e015c19','c07455bc-d21a-490f-88f3-0401d1899998',1),('3423423423','hello world','2018-04-26 03:08:59','200a7429-1e48-4ca6-bf53-3eed9e015c19','c07455bc-d21a-490f-88f3-0401d1899998',2),('bbee74f9-f74f-4af1-a230-2a098dfe6176','undefined','2018-04-26 04:30:29','200a7429-1e48-4ca6-bf53-3eed9e015c19','c07455bc-d21a-490f-88f3-0401d1899998',1),('bf1a8701-3a41-429f-aa64-4ff81dd68f03','sdfdsafadfad','2018-04-26 10:32:44','200a7429-1e48-4ca6-bf53-3eed9e015c19','c07455bc-d21a-490f-88f3-0401d1899998',1),('cb73b8e2-61f2-4db4-a415-6ea741ce57ad','asdsadasdassdsafsdfdsfsdfsdfdsfasdfasd','2018-04-26 10:50:55','61c4c78a-aad1-4774-9ccc-07ceb69bf89f','234234324234',2),('d26b1d3e-b1c7-4897-89f7-6f8848a1d57e','niadfadfadsfadsfasdf','2018-04-26 10:32:19','200a7429-1e48-4ca6-bf53-3eed9e015c19','c07455bc-d21a-490f-88f3-0401d1899998',1);
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
INSERT INTO `notification` VALUES ('62e64a6a-d169-4515-bbbd-8839c2ef966c','您的商品有了一份新订单!','您的名为：iphone8的商品有了一份新订单！','234234324234'),('ca694134-f987-43d0-b538-32bb9330c197','您的订单有了新状态!','您的编号为：1ab4f5b2-9482-4256-8388-b60f3f99193f的订单已发货！','234234324234');
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
  `order_status` tinyint(4) NOT NULL DEFAULT '1' COMMENT '4 未付款 1 未派送 2 派送中 3 已完成',
  `order_user_id` char(36) DEFAULT NULL,
  `order_product_id` char(36) DEFAULT NULL,
  `order_total_price` int(11) NOT NULL DEFAULT '0',
  `order_price` int(11) NOT NULL DEFAULT '0',
  `order_target` char(36) DEFAULT NULL,
  `order_finish_time` timestamp NULL DEFAULT NULL,
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
INSERT INTO `order` VALUES ('1ab4f5b2-9482-4256-8388-b60f3f99193f',1,'2018-04-26 08:42:06',3,'c07455bc-d21a-490f-88f3-0401d1899998','adsfasdfad',2222,1000,'234234324234','2018-04-26 09:21:21'),('6c176fc3-61bb-40fb-94ad-1fd5659ddfc3',1,'2018-04-26 10:20:55',1,'c07455bc-d21a-490f-88f3-0401d1899998','200a7429-1e48-4ca6-bf53-3eed9e015c19',3400,3400,'234234324234',NULL);
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
  `product_status` tinyint(4) NOT NULL DEFAULT '2' COMMENT '1 销售中 0 禁止销售 2 审核中 3 已下架',
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
INSERT INTO `product` VALUES ('200a7429-1e48-4ca6-bf53-3eed9e015c19','iphone8',3400,'<ul><li>超薄</li><li>A11处理器</li><li>2GB内存</li></ul>','adfasdfas','2018-04-26 02:54:16',1,3,0,'234234324234','/upload/0059ec60-d567-422a-85f7-82aed4eee399.png','/upload/ca53a876-e285-4826-bd9f-2d0a77f888b5.png','/upload/8cb462d6-398f-4009-995f-f99fad60010c.png','/upload/1e4e1edd-c8d7-437d-a9c1-b93632460b97.PNG',1),('61c4c78a-aad1-4774-9ccc-07ceb69bf89f','智能手表',559,'<i>撒旦飞洒地方手动阀打发是</i>','adfasdfas','2018-04-26 10:48:49',1,1,1,'c07455bc-d21a-490f-88f3-0401d1899998','/upload/0e0547e4-1a5b-4adf-9f07-b65012cae47b.png','/upload/011686ee-7c6f-4956-9ce4-d5749a8f6d49.png','/upload/31ac27c0-eadb-4b24-a61c-8efdf8450ef0.png','/upload/b1fc6767-9a18-4d90-b8bb-cf68d508b5d4.PNG',1),('adsfasdfad','手机拍卖',1000,'<ul><li><i>是十分</i></li><li><i>手动阀打发</i></li><li><i>的说法地方</i></li></ul>','adfasdfas','2018-04-26 06:30:28',3,3,0,'234234324234','/upload/892e36d1-bcb7-46df-a04c-8b281c780116.png','/upload/47c48d89-afa9-46fd-84bd-a8b569ed717e.png','/upload/d126b362-cc5f-4974-9f5e-4e1b1fecf592.png','/upload/869acf8b-e5d7-4e4f-90a5-f408170f56a8.PNG',1);
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
INSERT INTO `shoppingcart` VALUES (1,'2018-04-26 11:30:12','c07455bc-d21a-490f-88f3-0401d1899998','61c4c78a-aad1-4774-9ccc-07ceb69bf89f','381a0b8b-fd3f-48b8-a166-7494d6acbb88'),(1,'2018-04-27 09:04:43','ccda69fe-8472-4371-95da-c48273681789','61c4c78a-aad1-4774-9ccc-07ceb69bf89f','b8496209-1ab4-426e-9c41-1e5e9684ceb2');
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
  `user_password` varchar(20) NOT NULL,
  `user_head_img` varchar(60) NOT NULL DEFAULT '/upload/defaultUserIcon.png',
  `user_alipay` varchar(60) DEFAULT NULL COMMENT '支付宝收钱码',
  `user_last_login_time` timestamp NULL DEFAULT NULL,
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
INSERT INTO `user` VALUES ('234234324234','hello',1,'124124124124124','河南省商丘市',1,'1312432423@qq.com','15510841744','2018-04-26 02:53:14','123456789','/upload/64879062-51bf-4f7f-a669-b27d6a60d0c4.PNG','/upload/02ee0d21-f776-46f8-aff2-16033d9f3b38.png','2018-04-26 10:43:59'),('c07455bc-d21a-490f-88f3-0401d1899998','李松鹏',1,'411403199701266313','河北省石家庄市',1,'1231241243@qq.com','14410222915','2018-04-25 12:52:57','123456789','/upload/64879062-51bf-4f7f-a669-b27d6a60d0c4.PNG','/upload/02ee0d21-f776-46f8-aff2-16033d9f3b38.png','2018-04-26 10:46:53'),('ccda69fe-8472-4371-95da-c48273681789','神奇',1,'167424684567567895','天津市',1,'13212321@qq.com','14422338383','2018-04-26 11:14:15','123456789','/upload/43b311e0-6067-4aff-bc0c-b4a541e9d8c4.png','/upload/c513d8e4-f531-48aa-9449-f2072c0265be.png','2018-04-30 09:00:05');
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

-- Dump completed on 2018-05-04 15:54:56
