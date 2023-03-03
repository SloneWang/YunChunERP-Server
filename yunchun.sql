CREATE DATABASE  IF NOT EXISTS `yunchun` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `yunchun`;
-- MySQL dump 10.13  Distrib 8.0.30, for Win64 (x86_64)
--
-- Host: localhost    Database: yunchun
-- ------------------------------------------------------
-- Server version	8.0.30

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
-- Table structure for table `contract_history`
--

DROP TABLE IF EXISTS `contract_history`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `contract_history` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '主键',
  `contract_no` varchar(50) DEFAULT NULL COMMENT '合同编号',
  `modify_time` datetime DEFAULT NULL COMMENT '修改时间',
  `modify_by` varchar(50) DEFAULT NULL COMMENT '改动人编号',
  `review_by` varchar(50) DEFAULT NULL COMMENT '审核人编号',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `contract_history`
--

LOCK TABLES `contract_history` WRITE;
/*!40000 ALTER TABLE `contract_history` DISABLE KEYS */;
INSERT INTO `contract_history` VALUES (1,'XS2302160001','2023-02-16 02:37:47',NULL,NULL),(2,'XS2302160001','2023-02-16 08:10:45',NULL,NULL),(3,'XS2302160001','2023-02-16 08:47:22','15052382109',NULL),(4,'XS2302160002','2023-02-16 15:07:28','55555',NULL),(5,'XS2302160002','2023-02-16 15:24:05','55555',NULL);
/*!40000 ALTER TABLE `contract_history` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `controlmodule`
--

DROP TABLE IF EXISTS `controlmodule`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `controlmodule` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '主键',
  `induced_draft_fan_start_manually` int DEFAULT NULL COMMENT '引风机手动启动',
  `induced_draft_fan_start_automatically` int DEFAULT NULL COMMENT '引风机自动启动',
  `induced_draft_fan_run` int DEFAULT NULL COMMENT '引风机运行',
  `induced_draft_fan_fail` int DEFAULT NULL COMMENT '引风机故障',
  `induced_draft_fan_motor_power` float(14,5) DEFAULT NULL COMMENT '引风机电机功率',
  `blower_motor_power` float(14,5) DEFAULT NULL COMMENT '鼓风机电机功率',
  `secondary_blower_power` float(14,5) DEFAULT NULL COMMENT '二次鼓风机功率',
  `grate_reducer_start_manually` int DEFAULT NULL COMMENT '炉排减速机手动启动',
  `grate_reducer_start_automatically` int DEFAULT NULL COMMENT '炉排减速机自动启动',
  `grate_reducer_start_stop_manually` int DEFAULT NULL COMMENT '炉排减速机手动启停',
  `grate_reducer_start_stop_automatically` int DEFAULT NULL COMMENT '炉排减速机自动启停',
  `grate_reducer_fail` int DEFAULT NULL COMMENT '炉排减速机故障',
  `grate_reducer_run` int DEFAULT NULL COMMENT '炉排减速机运行',
  `grate_reducer_power` float(14,5) DEFAULT NULL COMMENT '炉排减速机功率',
  `controller_start_manually` int DEFAULT NULL COMMENT '控料器手动启动',
  `controller_start_automatically` int DEFAULT NULL COMMENT '控料器自动启动',
  `controller_start` int DEFAULT NULL COMMENT '控料器启动',
  `controller_run` int DEFAULT NULL COMMENT '控料器运行',
  `controller_motor_power` float(14,5) DEFAULT NULL COMMENT '控料器电机功率',
  `controller_fail` int DEFAULT NULL COMMENT '控料器故障',
  `multibucket_elevator_power` float(14,5) DEFAULT NULL COMMENT '多斗提升机电机功率',
  `discharge_valve_motor_power` float(14,5) DEFAULT NULL COMMENT '卸料阀电机功率',
  `z_type_conveyor_motor_power` float(14,5) DEFAULT NULL COMMENT 'Z型输送机电机功率',
  `slag_remover_start_stop_manually` int DEFAULT NULL COMMENT '除渣机手动启停',
  `slag_remover_start_stop_automatically` int DEFAULT NULL COMMENT '除渣机自动启停',
  `slag_remover_run` int DEFAULT NULL COMMENT '除渣机运行',
  `slag_remover_fail` int DEFAULT NULL COMMENT '除渣机故障',
  `slag_remover_motor_power` float(14,5) DEFAULT NULL COMMENT '除渣机电机功率',
  `gas_storage_motor_power_device` float(14,5) DEFAULT NULL COMMENT '储气装置电机功率',
  `circulating_pump1_start_stop_manually` int DEFAULT NULL COMMENT '循环泵1手动启停',
  `circulating_pump1_start_stop_automatically` int DEFAULT NULL COMMENT '循环泵1自动启停',
  `circulating_pump1_run` int DEFAULT NULL COMMENT '循环泵1运行',
  `circulating_pump1_fail` int DEFAULT NULL COMMENT '循环泵1故障',
  `circulating_pump1_motor_power` float(14,5) DEFAULT NULL COMMENT '循环泵1电机功率',
  `circulating_pump2_start_stop_manually` int DEFAULT NULL COMMENT '循环泵2手动启停',
  `circulating_pump2_start_stop_automatically` int DEFAULT NULL COMMENT '循环泵2自动启停',
  `circulating_pump2_run` int DEFAULT NULL COMMENT '循环泵2运行',
  `circulating_pump2_fail` int DEFAULT NULL COMMENT '循环泵2故障',
  `circulating_pump2_motor_power` float(14,5) DEFAULT NULL COMMENT '循环泵2电机功率',
  `regulator_pump_motor_power` float(14,5) DEFAULT NULL COMMENT '稳压泵电机功率',
  `denitration_spray_pump_motor_power` float(14,5) DEFAULT NULL COMMENT '脱硝喷淋泵电机功率',
  `denitration_mixer_motor_power` float(14,5) DEFAULT NULL COMMENT '脱硝搅拌机电机功率',
  `air_source_consumption_power` float(14,5) DEFAULT NULL COMMENT '空气源用电功率',
  `air_source_circulating_pump1_motor_power` float(14,5) DEFAULT NULL COMMENT '空气源循环泵1电机功率',
  `air_source_circulating_pump2_motor_power` float(14,5) DEFAULT NULL COMMENT '空气源循环泵2电机功率',
  `flue_gas_recirculation_fan` float(14,5) DEFAULT NULL COMMENT '烟气再循环风机',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb3 ROW_FORMAT=DYNAMIC;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `controlmodule`
--

LOCK TABLES `controlmodule` WRITE;
/*!40000 ALTER TABLE `controlmodule` DISABLE KEYS */;
INSERT INTO `controlmodule` VALUES (1,1,1,1,1,2.00000,1.00000,1.00000,1,1,2,2,2,0,0.00000,0,0,0,0,0.00000,0,0.00000,0.00000,1.00000,0,0,0,0,0.00000,0.00000,0,0,0,0,0.00000,0,0,0,0,0.00000,0.00000,0.00000,0.00000,0.00000,0.00000,0.00000,0.00000),(2,2,2,2,3,4.00000,5.00000,6.00000,7,8,1,9,1,1,NULL,1,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(5,1,0,0,0,3.00000,0.00000,0.00000,0,0,0,0,0,0,0.00000,0,0,0,0,0.00000,0,0.00000,0.00000,0.00000,0,0,0,0,0.00000,0.00000,0,0,0,0,0.00000,0,0,0,0,0.00000,0.00000,0.00000,0.00000,0.00000,0.00000,0.00000,0.00000),(6,1,2,3,0,6.00000,6.00000,0.00000,0,0,0,0,0,0,0.00000,0,0,0,0,0.00000,0,0.00000,0.00000,0.00000,0,0,0,0,0.00000,0.00000,0,0,0,0,0.00000,0,0,0,0,0.00000,0.00000,0.00000,0.00000,0.00000,0.00000,0.00000,0.00000),(7,0,0,0,0,3.00000,4.00000,0.00000,0,0,0,0,0,0,0.00000,0,0,0,0,0.00000,0,0.00000,0.00000,0.00000,0,0,0,0,0.00000,0.00000,0,0,0,0,0.00000,0,0,0,0,0.00000,0.00000,0.00000,0.00000,0.00000,0.00000,0.00000,0.00000);
/*!40000 ALTER TABLE `controlmodule` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `customer_information`
--

DROP TABLE IF EXISTS `customer_information`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `customer_information` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '主键',
  `contract_number` varchar(50) DEFAULT NULL COMMENT '合同编号',
  `customer_name` varchar(50) DEFAULT NULL COMMENT '客户姓名',
  `customer_phone` varchar(50) DEFAULT NULL COMMENT '客户电话',
  `customer_name2` varchar(50) DEFAULT NULL COMMENT '客户姓名2',
  `customer_phone2` varchar(50) DEFAULT NULL COMMENT '客户电话2',
  `company` varchar(50) DEFAULT NULL COMMENT '客户公司',
  `industry` varchar(50) DEFAULT NULL COMMENT '所属行业',
  `customer_job` varchar(50) DEFAULT NULL COMMENT '客户职务',
  `channel` varchar(50) DEFAULT NULL COMMENT '渠道来源',
  `address` varchar(50) DEFAULT NULL COMMENT '客户地址',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `customer_information`
--

LOCK TABLES `customer_information` WRITE;
/*!40000 ALTER TABLE `customer_information` DISABLE KEYS */;
INSERT INTO `customer_information` VALUES (3,'XS2302160001','石永明','17744426066','王红伟','15562673348','河北造船厂','船舶制造','市场部经理','熟人推荐','河北省张家口市三里屯县五河小区3号楼3单元4楼'),(4,'XS2302160002','李汉','15544426066','李阳','11262673348','天津钢铁厂','钢铁生产','业务员','微信公众号','天津市和平区窝窝乡三笠小区3号楼3单元4楼'),(5,'XS2302160002','李汉','15544426066','李阳','11262673348','天津钢铁厂','钢铁生产','业务员','微信公众号','天津市和平区窝窝乡三笠小区3号楼3单元4楼'),(6,'XS2302160002','李汉','15544426066','李阳','11262673348','天津钢铁厂','钢铁生产','业务员','微信公众号','天津市和平区窝窝乡三笠小区3号楼3单元4楼');
/*!40000 ALTER TABLE `customer_information` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `heat_authority`
--

DROP TABLE IF EXISTS `heat_authority`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `heat_authority` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '主键',
  `username` varchar(50) DEFAULT NULL COMMENT '用户名',
  `admin_authority` varchar(500) DEFAULT NULL COMMENT '人员管理权限',
  `custom_authority` varchar(500) DEFAULT NULL COMMENT '负责区域权限',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=25 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `heat_authority`
--

LOCK TABLES `heat_authority` WRITE;
/*!40000 ALTER TABLE `heat_authority` DISABLE KEYS */;
INSERT INTO `heat_authority` VALUES (16,'55555',NULL,'130827兆丰润景小区'),(17,'wizardhibiki',NULL,'120111中北天软创业学院'),(18,'wizard','13',NULL),(19,'1993362493','1308',NULL),(20,'wizard','11',NULL),(21,'wizard','12',NULL),(22,'850154120','1101',NULL),(23,'hibiki','130827',NULL);
/*!40000 ALTER TABLE `heat_authority` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `heat_cost`
--

DROP TABLE IF EXISTS `heat_cost`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `heat_cost` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '序号',
  `name` varchar(10) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL COMMENT '姓名',
  `phonenumber` varchar(20) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL COMMENT '联系方式',
  `location` varchar(200) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL COMMENT '位置',
  `heated_area` int DEFAULT NULL COMMENT '供热面积(㎡)',
  `total_area` int DEFAULT NULL COMMENT '全部面积(㎡)',
  `unheated_area` int DEFAULT NULL COMMENT '非供热面积(㎡)',
  `unit_price` int DEFAULT NULL COMMENT '单价(元/㎡)',
  `total_price` int DEFAULT NULL COMMENT '总金额(元)',
  `pay` varchar(20) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL COMMENT '是否缴费',
  `throughout_half_year` varchar(2) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL COMMENT '全年/半年',
  `heat_state` varchar(10) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL COMMENT '供热状态',
  `open_valve_cost` int DEFAULT NULL COMMENT '启阀费(元/次)',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb3 ROW_FORMAT=DYNAMIC;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `heat_cost`
--

LOCK TABLES `heat_cost` WRITE;
/*!40000 ALTER TABLE `heat_cost` DISABLE KEYS */;
INSERT INTO `heat_cost` VALUES (1,'黄鎏都','11111325','黑龙江省哈尔滨市哑巴村',60,100,40,5,20,'是','全年','正在供热',20),(2,'王旺旺','14433222','黑龙江省哈尔滨市寂静岭',50,100,50,3,0,'否','半年','未供热',20),(3,'张三',NULL,NULL,0,0,0,0,0,'老赖',NULL,NULL,0);
/*!40000 ALTER TABLE `heat_cost` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `heat_district`
--

DROP TABLE IF EXISTS `heat_district`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `heat_district` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '主键',
  `province` varchar(50) DEFAULT NULL COMMENT '省',
  `city` varchar(50) DEFAULT NULL COMMENT '市',
  `county` varchar(50) DEFAULT NULL COMMENT '县',
  `residential_quarter` varchar(50) DEFAULT NULL COMMENT '小区',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `heat_district`
--

LOCK TABLES `heat_district` WRITE;
/*!40000 ALTER TABLE `heat_district` DISABLE KEYS */;
INSERT INTO `heat_district` VALUES (1,'13','08','27','兆丰润景小区'),(2,'12','01','11','中北天软创业学院'),(4,'13','08','24','龙傲天小区'),(5,'13','08','27','顺溜小区'),(6,'12','01','11','王顶堤小区'),(7,'11','01','08','魏公村社区'),(8,'13','08','26','丰收小区'),(9,'13','09','01','西湖里小区'),(10,'13','08','27','北河沿小区');
/*!40000 ALTER TABLE `heat_district` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `heat_record`
--

DROP TABLE IF EXISTS `heat_record`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `heat_record` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '主键',
  `date` datetime DEFAULT NULL COMMENT '缴费时间',
  `amount` decimal(12,2) DEFAULT NULL COMMENT '缴费金额',
  `collector` varchar(50) DEFAULT NULL COMMENT '收费人员',
  `district` varchar(50) DEFAULT NULL COMMENT '所在小区',
  `account` varchar(50) DEFAULT NULL COMMENT '户号',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=29 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `heat_record`
--

LOCK TABLES `heat_record` WRITE;
/*!40000 ALTER TABLE `heat_record` DISABLE KEYS */;
INSERT INTO `heat_record` VALUES (3,'2022-11-02 08:10:19',2000.00,'user2','130827兆丰润景小区','602-5'),(4,'2022-11-02 08:11:58',500.00,'user2','130827兆丰润景小区','601-4'),(5,'2022-11-02 08:31:09',500.00,'user2','130827兆丰润景小区','601-4'),(6,'2022-11-02 08:47:42',5000.00,'user2','130827兆丰润景小区','602-10'),(7,'2022-11-07 03:06:08',0.00,'wizard','120111中北天软创业学院','16-2-205'),(8,'2022-11-07 03:06:16',123.00,'wizard','120111中北天软创业学院','16-2-205'),(9,'2022-11-07 03:06:21',-123.00,'wizard','120111中北天软创业学院','16-2-205'),(10,'2022-11-07 03:09:14',123.00,'wizard','120111中北天软创业学院','16-2-205'),(11,'2022-11-07 03:09:33',123.00,'wizard','120111中北天软创业学院','16-2-205'),(12,'2022-11-07 03:10:59',2000.00,'wizard','120111中北天软创业学院','16-2-205'),(13,'2022-11-07 03:13:56',10.00,'wizard','130827兆丰润景小区','602-11'),(14,'2022-11-07 03:14:09',10.00,'wizard','130827兆丰润景小区','602-11'),(15,'2022-11-07 03:14:11',10.00,'wizard','130827兆丰润景小区','602-11'),(16,'2022-11-07 03:14:12',10.00,'wizard','130827兆丰润景小区','602-11'),(17,'2022-11-08 11:31:35',2000.00,'wizard','130827兆丰润景小区','602-5'),(18,'2022-11-13 07:35:49',1.00,'wizard','120111中北天软创业学院','16-2-205'),(19,'2022-11-13 11:31:35',123.00,'wizard','120111中北天软创业学院','16-2-205'),(20,'2022-11-17 00:00:00',1000.00,'wizard','130827兆丰润景小区','602-5'),(21,'2022-11-20 00:00:00',10000.00,'55555','130827兆丰润景小区','601-4'),(22,'2022-11-20 00:00:00',1000.00,'55555','130827兆丰润景小区','601-4'),(23,'2022-11-20 00:00:00',123.00,'55555','130827兆丰润景小区','601-4'),(24,'2022-11-20 11:48:04',1233.00,'55555','130827兆丰润景小区','601-4'),(25,'2022-11-23 12:01:55',123.00,'wizard','130827兆丰润景小区','602-5'),(26,'2023-02-25 01:47:56',10000.00,'wizard','130826丰收小区','601-4'),(27,'2023-02-25 01:48:26',10000.00,'wizard','130827北河沿小区','601-4'),(28,'2023-02-25 01:48:37',1000.00,'wizard','130827北河沿小区','601-4');
/*!40000 ALTER TABLE `heat_record` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `heat_sys`
--

DROP TABLE IF EXISTS `heat_sys`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `heat_sys` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '主键',
  `pay_cycle` int NOT NULL DEFAULT '12' COMMENT '缴费周期(月)',
  `interest_rate` decimal(12,2) NOT NULL DEFAULT '0.10' COMMENT '滞纳金利率(天)',
  `late_times` int NOT NULL DEFAULT '0' COMMENT '欠缴标识次数',
  `unit_price` decimal(12,2) NOT NULL DEFAULT '10.00' COMMENT '单价(元/m2)',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `heat_sys`
--

LOCK TABLES `heat_sys` WRITE;
/*!40000 ALTER TABLE `heat_sys` DISABLE KEYS */;
INSERT INTO `heat_sys` VALUES (1,12,0.10,3,1.98);
/*!40000 ALTER TABLE `heat_sys` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `heat_user`
--

DROP TABLE IF EXISTS `heat_user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `heat_user` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '主键',
  `name` varchar(50) DEFAULT NULL COMMENT '姓名',
  `phone` varchar(50) DEFAULT NULL COMMENT '电话',
  `district` varchar(50) DEFAULT NULL COMMENT '小区编号',
  `account` varchar(50) DEFAULT NULL COMMENT '户号',
  `heat_area` decimal(12,2) DEFAULT NULL COMMENT '供热面积',
  `date` date DEFAULT NULL COMMENT '缴费日期',
  `pay_status` varchar(50) DEFAULT '0' COMMENT '缴费状态',
  `fail_pay` int DEFAULT '0' COMMENT '欠缴次数',
  `user_balance` decimal(12,2) DEFAULT NULL COMMENT '账户余额',
  `pay_amount` decimal(12,2) DEFAULT NULL COMMENT '本月应付金额',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `heat_user`
--

LOCK TABLES `heat_user` WRITE;
/*!40000 ALTER TABLE `heat_user` DISABLE KEYS */;
INSERT INTO `heat_user` VALUES (6,'天乐','13775674252','130827兆丰润景小区','602-5',101.00,'2023-07-18','已缴费',0,747.48,1187.76),(7,'多啦a梦','13878434252','120111中北天软创业学院','16-2-205',77.00,'2023-09-11','未缴费',0,0.00,905.52),(8,'阿凡达','12228434252','130827兆丰润景小区','601-4',88.00,'2023-11-15','已缴费',0,605.91,1034.88),(9,'叶城','14428434252','130827兆丰润景小区','602-10',67.00,'2023-04-25','已缴费',0,3424.16,787.92),(11,'鎏都','12328434252','130827兆丰润景小区','602-11',50.00,'2023-09-11','未缴费',0,0.00,588.00),(12,'飞科','12328434251','130827兆丰润景小区','6011',67.00,'2023-09-17','未缴费',0,0.00,787.92),(13,'鎏都温','12328434252','130824龙傲天小区','602-11',50.00,'2023-09-11','未缴费',0,0.00,588.00),(15,'叶城乘','14428434252','130827顺溜小区','602-10',67.00,'2023-04-25','已缴费',0,3424.16,787.92),(16,'阿凡','12228434252','130826丰收小区','601-4',88.00,'2023-11-15','未缴费',0,319.67,1034.88),(17,'阿达','12228434252','130827北河沿小区','601-4',88.00,'2023-11-15','已缴费',0,284.79,1034.88),(18,'叶问','15528434252','130827顺溜小区','602-10',67.00,'2023-04-25','已缴费',0,3424.16,787.92),(19,'天乐乐','13775674252','120211王顶堤小区','602-5',101.00,'2023-07-18','未缴费',0,812.24,1187.76),(20,'多啦a梦','13878434252','110108魏公村小区','16-2-205',77.00,'2023-09-11','未缴费',0,0.00,905.52);
/*!40000 ALTER TABLE `heat_user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `inputparm`
--

DROP TABLE IF EXISTS `inputparm`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `inputparm` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '主键序号',
  `HopperTemperature` float(14,5) DEFAULT NULL COMMENT '料斗温度',
  `FurnaceTemperature` float(14,5) DEFAULT NULL COMMENT '炉膛温度',
  `ChamberPressure` float(14,5) DEFAULT NULL COMMENT '炉膛压力',
  `FirstBlowerPressure` float(14,5) DEFAULT NULL COMMENT '一次鼓风压力',
  `FurnaceOutletSmokeTemperature` float(14,5) DEFAULT NULL COMMENT '炉膛出口烟温',
  `ExhaustSmokeTemperature` float(14,5) DEFAULT NULL COMMENT '排烟温度',
  `SupplyWaterTemperature` float(14,5) DEFAULT NULL COMMENT '供水温度',
  `ReturnWaterTemperature` float(14,5) DEFAULT NULL COMMENT '回水温度',
  `ReturnWaterPressure` float(14,5) DEFAULT NULL COMMENT '回水压力',
  `TankLevel` float(14,5) DEFAULT NULL COMMENT '水箱液位',
  `1StationBlowerFrequency` float(14,5) DEFAULT NULL COMMENT '1站鼓风机频率',
  `2StationInducedRaftFanFrequency` float(14,5) DEFAULT NULL COMMENT '2站引风机频率',
  `3StationGrateFrequency` float(14,5) DEFAULT NULL COMMENT '3站炉排频率',
  `4StationControllerFrequency` float(14,5) DEFAULT NULL COMMENT '4站控料器频率',
  `5StationCirculatingPumpFrequency` float(14,5) DEFAULT NULL COMMENT '5站循环泵频率',
  `InducedDraftFanRun` float(14,5) DEFAULT NULL COMMENT '引风机运行',
  `InducedDraftFanFail` float(14,5) DEFAULT NULL COMMENT '引风机故障',
  `FirstBlowerRun` float(14,5) DEFAULT NULL COMMENT '一次鼓风运行',
  `FirstBlowerFail` float(14,5) DEFAULT NULL COMMENT '一次鼓风故障',
  `ControllerRun` float(14,5) DEFAULT NULL COMMENT '控料器运行',
  `ControllerFail` float(14,5) DEFAULT NULL COMMENT '控料器故障',
  `GrateMotorRun` float(14,5) DEFAULT NULL COMMENT '炉排电机运行',
  `GrateFail` float(14,5) DEFAULT NULL COMMENT '炉排故障',
  `CirculatingPump1Run` float(14,5) DEFAULT NULL COMMENT '循环泵1运行',
  `CirculatingPump1Fail` float(14,5) DEFAULT NULL COMMENT '循环泵1故障',
  `CirculatingPump2Run` float(14,5) DEFAULT NULL COMMENT '循环泵2运行',
  `CirculatingPump2Fail` float(14,5) DEFAULT NULL COMMENT '循环泵2故障',
  `ControllerStart` float(14,5) DEFAULT NULL COMMENT '控料器启动',
  `ReplenishmentPumpRun` float(14,5) DEFAULT NULL COMMENT '补水泵运行',
  `WaterPumpFail` float(14,5) DEFAULT NULL COMMENT '补水泵故障',
  `SlagRemoverRun` float(14,5) DEFAULT NULL COMMENT '除渣机运行',
  `SlagRemoverFail` float(14,5) DEFAULT NULL COMMENT '除渣机故障',
  `FeedingMachineRun` float(14,5) DEFAULT NULL COMMENT '上料机运行',
  `FeedingMachineFail` float(14,5) DEFAULT NULL COMMENT '上料机故障',
  `PressureReliefValveRun` float(14,5) DEFAULT NULL COMMENT '泄压阀运行',
  `HopperHighLimit` float(14,5) DEFAULT NULL COMMENT '料斗高限位',
  `HopperLowLimit` float(14,5) DEFAULT NULL COMMENT '料斗低限位',
  `WatertankHighLimit` float(14,5) DEFAULT NULL COMMENT '水箱高限位',
  `WatertankLowLimit` float(14,5) DEFAULT NULL COMMENT '水箱低限位',
  `GrateStartManually` float(14,5) DEFAULT NULL COMMENT '炉排手动启动',
  `GrateStartAutomatically` float(14,5) DEFAULT NULL COMMENT '炉排自动启动',
  `InducedDraftFanStartManually` float(14,5) DEFAULT NULL COMMENT '引风机手动启动',
  `InducedDraftFanStartAutomatically` float(14,5) DEFAULT NULL COMMENT '引风机自动启动',
  `FirstBlowerStartManually` float(14,5) DEFAULT NULL COMMENT '一次鼓风手动启动',
  `FirstBlowerStartAutomatically` float(14,5) DEFAULT NULL COMMENT '一次鼓风自动启动',
  `ControllerStartManually` float(14,5) DEFAULT NULL COMMENT '控料器手动启动',
  `ControllerStartAutomatically` float(14,5) DEFAULT NULL COMMENT '控料器自动启动',
  `GratemotorStartStopManually` float(14,5) DEFAULT NULL COMMENT '炉排电机手动启停',
  `GratemotorStartStopAutomatically` float(14,5) DEFAULT NULL COMMENT '炉排电机自动启停',
  `CirculatingPump1StartStopManually` float(14,5) DEFAULT NULL COMMENT '循环泵1手动启停',
  `CirculatingPump1StartStopAutomatically` float(14,5) DEFAULT NULL COMMENT '循环泵1自动启停',
  `CirculatingPump2StartStopManually` float(14,5) DEFAULT NULL COMMENT '循环泵2手动启停',
  `CirculationPump2StartStopAutomatically` float(14,5) DEFAULT NULL COMMENT '循环泵2自动启停',
  `RefillPumpStartManually` float(14,5) DEFAULT NULL COMMENT '补水泵手动启动',
  `WaterRefillPumpStartAutomatically` float(14,5) DEFAULT NULL COMMENT '补水泵自动启动',
  `SlagMachineStartStopManually` float(14,5) DEFAULT NULL COMMENT '除渣机手动启停',
  `SlagMachineStartStopAutomatically` float(14,5) DEFAULT NULL COMMENT '除渣机自动启停',
  `FeedingMachineStartManually` float(14,5) DEFAULT NULL COMMENT '上料机手动启动',
  `FeedingMachineStartAutomatically` float(14,5) DEFAULT NULL COMMENT '上料机自动启动',
  `PressureReliefValveStartManually` float(14,5) DEFAULT NULL COMMENT '泄压阀手动启动',
  `PressureReliefValveStartAutomatically` float(14,5) DEFAULT NULL COMMENT '泄压阀自动启动',
  `AlarmLightStart` float(14,5) DEFAULT NULL COMMENT '报警灯启动',
  `HopperHighTemperatureSetting` float(14,5) DEFAULT NULL COMMENT '料斗高温设定',
  `FurnaceHighTemperatureSetting` float(14,5) DEFAULT NULL COMMENT '炉膛高温设定',
  `FurnacePressureHighSetting` float(14,5) DEFAULT NULL COMMENT '炉膛压力高设定',
  `FirstBlowerPressureHighSetting` float(14,5) DEFAULT NULL COMMENT '一次鼓风压力高设定',
  `FurnaceTemperatureHighSetting` float(14,5) DEFAULT NULL COMMENT '炉膛温度高设定',
  `SmokeExhaustTemperatureHighSetting` float(14,5) DEFAULT NULL COMMENT '排烟温度高设定',
  `WaterSupplyTemperatureHighSetting` float(14,5) DEFAULT NULL COMMENT '供水温度高设定',
  `WaterSupplyPressureHighSetting` float(14,5) DEFAULT NULL COMMENT '供水压力高设定',
  `BackWaterTemperatureHighSetting` float(14,5) DEFAULT NULL COMMENT '回水温度高设定',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 ROW_FORMAT=DYNAMIC;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `inputparm`
--

LOCK TABLES `inputparm` WRITE;
/*!40000 ALTER TABLE `inputparm` DISABLE KEYS */;
/*!40000 ALTER TABLE `inputparm` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `loading_system_module`
--

DROP TABLE IF EXISTS `loading_system_module`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `loading_system_module` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '主键',
  `feeding_motor_start` int DEFAULT NULL COMMENT '上料电机启动',
  `feeding_machine_run` int DEFAULT NULL COMMENT '上料机运行',
  `feeding_machine_fail` int DEFAULT NULL COMMENT '上料机故障',
  `feeding_machine_start_manually` int DEFAULT NULL COMMENT '上料机手动启动',
  `feeding_machine_start_automatically` int DEFAULT NULL COMMENT '上料机自动启动',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb3 ROW_FORMAT=DYNAMIC;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `loading_system_module`
--

LOCK TABLES `loading_system_module` WRITE;
/*!40000 ALTER TABLE `loading_system_module` DISABLE KEYS */;
INSERT INTO `loading_system_module` VALUES (1,1,1,2,2,4);
/*!40000 ALTER TABLE `loading_system_module` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `mainenginemodule`
--

DROP TABLE IF EXISTS `mainenginemodule`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `mainenginemodule` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '主键',
  `expansion_water_tank_level` float(14,5) DEFAULT NULL COMMENT '膨胀水箱液位',
  `expansion_water_tank_high_limit` float(14,5) DEFAULT NULL COMMENT '膨胀水箱高限位',
  `expansion_water_tank_low_limit` float(14,5) DEFAULT NULL COMMENT '膨胀水箱低限位',
  `hopper_temp` float(14,5) DEFAULT NULL COMMENT '料斗温度',
  `hopper_high_limit` float(14,5) DEFAULT NULL COMMENT '料斗高限位',
  `hopper_low_limit` float(14,5) DEFAULT NULL COMMENT '料斗低限位',
  `hopper_temp_set` float(14,5) DEFAULT NULL COMMENT '料斗高温设定',
  `firepot_temp` float(14,5) DEFAULT NULL COMMENT '炉膛温度',
  `firepot_pres` float(14,5) DEFAULT NULL COMMENT '炉膛压力',
  `firepot_high_temp_set` float(14,5) DEFAULT NULL COMMENT '炉膛高温设定',
  `firepot_pres_high_set` float(14,5) DEFAULT NULL COMMENT '炉膛压力高设定',
  `firepot_temp_high_set` float(14,5) DEFAULT NULL COMMENT '炉膛温度高设定',
  `exhaust_smoke_temperature` float(14,5) DEFAULT NULL COMMENT '排烟温度',
  `smoke_exhaust_temperature_high_setting` float(14,5) DEFAULT NULL COMMENT '排烟温度高设定',
  `supply_water_temp` float(14,5) DEFAULT NULL COMMENT '供水温度',
  `supply_water_temp_set` float(14,5) DEFAULT NULL COMMENT '供水温度高设定',
  `supply_water_pres` float(14,5) DEFAULT NULL COMMENT '供水压力',
  `supply_water_pres_set` float(14,5) DEFAULT NULL COMMENT '供水压力高设定',
  `return_water_temp` float(14,5) DEFAULT NULL COMMENT '回水温度',
  `return_water_temp_set` float(14,5) DEFAULT NULL COMMENT '回水温度高设定',
  `return_water_pres` float(14,5) DEFAULT NULL COMMENT '回水压力',
  `return_water_pres_set` float(14,5) DEFAULT NULL COMMENT '回水压力高设定',
  `furnace_outlet_smoke_temperature` float(14,5) DEFAULT NULL COMMENT '炉膛出口烟温',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb3 ROW_FORMAT=DYNAMIC;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `mainenginemodule`
--

LOCK TABLES `mainenginemodule` WRITE;
/*!40000 ALTER TABLE `mainenginemodule` DISABLE KEYS */;
INSERT INTO `mainenginemodule` VALUES (1,1.00000,1.00000,3.00000,5.00000,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(2,2.00000,0.00000,0.00000,0.00000,0.00000,0.00000,0.00000,0.00000,0.00000,0.00000,0.00000,0.00000,0.00000,0.00000,0.00000,0.00000,0.00000,0.00000,0.00000,0.00000,0.00000,0.00000,3.00000);
/*!40000 ALTER TABLE `mainenginemodule` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `outputparm`
--

DROP TABLE IF EXISTS `outputparm`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `outputparm` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '主键序号',
  `MaterialControllerEnable` bit(1) DEFAULT NULL COMMENT '控料器启动',
  `ExhausterEnable` bit(1) DEFAULT NULL COMMENT '炉排电机启动',
  `InducerEnable` bit(1) DEFAULT NULL COMMENT '引风机启动',
  `Blower1Enable` bit(1) DEFAULT NULL COMMENT '鼓风机启动',
  `Blower2Enable` bit(1) DEFAULT NULL COMMENT '二次鼓风机启动',
  `Circulator1Enable` bit(1) DEFAULT NULL COMMENT '循环泵1启动',
  `Circulator2Enable` bit(1) DEFAULT NULL COMMENT '循环泵2启动',
  `PumperEnable` bit(1) DEFAULT NULL COMMENT '泵水泵启动',
  `SlagRemoverEnable` bit(1) DEFAULT NULL COMMENT '除渣机启动',
  `FeederEnable` bit(1) DEFAULT NULL COMMENT '上料电机启动',
  `DecompressorEnable` bit(1) DEFAULT NULL COMMENT '泄压阀启动',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci ROW_FORMAT=DYNAMIC;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `outputparm`
--

LOCK TABLES `outputparm` WRITE;
/*!40000 ALTER TABLE `outputparm` DISABLE KEYS */;
/*!40000 ALTER TABLE `outputparm` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `pay_plan`
--

DROP TABLE IF EXISTS `pay_plan`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `pay_plan` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '主键',
  `contract_number` varchar(50) DEFAULT NULL COMMENT '合同编号',
  `pay_cycle` int DEFAULT NULL COMMENT '付款周期',
  `pay_status` varchar(50) DEFAULT NULL COMMENT '完成状态',
  `pay_date` date DEFAULT NULL COMMENT '本次付款截止时间',
  `amount_paid` decimal(10,2) DEFAULT NULL COMMENT '已付金额',
  `balance` decimal(10,2) DEFAULT NULL COMMENT '余额或欠款',
  `amount_once` decimal(10,2) DEFAULT NULL COMMENT '每次应付金额',
  `late_times` int DEFAULT NULL COMMENT '逾期次数',
  `amount_not_paid` decimal(11,2) DEFAULT NULL COMMENT '剩余待付金额',
  `employee_no` varchar(50) DEFAULT NULL COMMENT '负责员工编号',
  `employee_name` varchar(50) DEFAULT NULL COMMENT '负责员工姓名',
  `amount_plan` decimal(10,2) DEFAULT NULL COMMENT '计划付款金额',
  `pay_create_date` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`),
  KEY `PAYORNOT` (`pay_status`),
  KEY `PAYDATE` (`pay_date`),
  KEY `PAYAMOUNT` (`amount_not_paid`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pay_plan`
--

LOCK TABLES `pay_plan` WRITE;
/*!40000 ALTER TABLE `pay_plan` DISABLE KEYS */;
INSERT INTO `pay_plan` VALUES (5,'XS2302160001',6,'未缴费','2023-02-16',0.00,0.00,300.00,0,4300.50,'1993362493','吴玉青',300.00,'2023-02-16 00:00:00'),(8,'XS2302160002',1,'未逾期','2023-02-16',0.00,0.00,400.00,0,6600.00,'55555','王天乐',400.00,'2023-02-16 00:00:00');
/*!40000 ALTER TABLE `pay_plan` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `payplan_history`
--

DROP TABLE IF EXISTS `payplan_history`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `payplan_history` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '主键',
  `contract_no` varchar(50) DEFAULT NULL COMMENT '回款计划对应合同编号',
  `modify_time` datetime DEFAULT NULL COMMENT '修改时间',
  `modify_by` varchar(50) DEFAULT NULL COMMENT '修改人',
  `review_by` varchar(50) DEFAULT NULL COMMENT '审核人',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `payplan_history`
--

LOCK TABLES `payplan_history` WRITE;
/*!40000 ALTER TABLE `payplan_history` DISABLE KEYS */;
INSERT INTO `payplan_history` VALUES (1,'XS2302160001','2023-02-16 09:17:57','15052382109',NULL),(2,'XS2302160001','2023-02-16 09:26:04','15052382109',NULL),(3,'XS2302160001','2023-02-16 09:28:15','15052382109',NULL),(4,'XS2302160001','2023-02-16 14:33:21','15052382109',NULL);
/*!40000 ALTER TABLE `payplan_history` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `product_information`
--

DROP TABLE IF EXISTS `product_information`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `product_information` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '主键',
  `product_no` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_estonian_ci DEFAULT NULL COMMENT '产品编号',
  `product_type` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_estonian_ci DEFAULT NULL COMMENT '产品类型',
  `product_able` tinyint(1) DEFAULT NULL COMMENT '能否生产',
  `price` decimal(8,2) DEFAULT NULL COMMENT '价格',
  `notes` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_estonian_ci DEFAULT NULL COMMENT '注释',
  PRIMARY KEY (`id`),
  KEY `PRODUCTTYPE` (`product_type`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_estonian_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `product_information`
--

LOCK TABLES `product_information` WRITE;
/*!40000 ALTER TABLE `product_information` DISABLE KEYS */;
INSERT INTO `product_information` VALUES (1,'DNGL10086A','电能锅炉10086A',1,3000.00,'无'),(2,'ZQGL12138B','蒸汽锅炉12138B',1,50000.00,'无'),(3,'RMGL56523A','燃煤锅炉56523A',1,4000.00,'无'),(4,'SWZGL23125C','生物质锅炉SWZGL23125C',1,60000.00,'无');
/*!40000 ALTER TABLE `product_information` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `salesman_contract`
--

DROP TABLE IF EXISTS `salesman_contract`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `salesman_contract` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '主键',
  `contract_number` varchar(50) NOT NULL COMMENT '合同号',
  `sign_date` date DEFAULT NULL COMMENT '签约日期',
  `invoice_type` varchar(10) DEFAULT NULL COMMENT '发票类型',
  `employee_no` varchar(50) DEFAULT NULL COMMENT '创建员工编号',
  `employee_name` varchar(10) DEFAULT NULL COMMENT '创建员工姓名',
  `reviewer_no` varchar(50) DEFAULT NULL COMMENT '审核人编号',
  `reviewer_name` varchar(10) DEFAULT NULL COMMENT '审核人姓名',
  `reviewer_comment` varchar(300) DEFAULT NULL COMMENT '审批意见(300字内)',
  `customer_company` varchar(50) DEFAULT NULL COMMENT '客户所属公司',
  `customer_name` varchar(10) DEFAULT NULL COMMENT '客户姓名',
  `customer_phonenum` varchar(11) DEFAULT NULL COMMENT '客户电话',
  `sec_customer_name` varchar(10) DEFAULT NULL COMMENT '第二联系人姓名',
  `sec_customer_phonenum` varchar(11) DEFAULT NULL COMMENT '第二联系人电话',
  `contract_lifecycle` varchar(10) DEFAULT NULL COMMENT '合同生命周期',
  `estimated_install_date` date DEFAULT NULL COMMENT '预计安装完成日期',
  `install_date` date DEFAULT NULL COMMENT '实际安装完成日期',
  `install_address` varchar(50) DEFAULT NULL COMMENT '安装地址',
  `contract_document` varchar(100) DEFAULT NULL COMMENT '合同文件',
  `subitem_profit` decimal(10,2) DEFAULT NULL COMMENT '合同利润',
  `subitem_profit_rate` double DEFAULT NULL COMMENT '利润率',
  `delivery_fee` decimal(10,2) DEFAULT NULL COMMENT '发货金额',
  `up_amount` decimal(10,2) DEFAULT NULL COMMENT '加价金额',
  `delivery_method` varchar(2) DEFAULT NULL COMMENT '发货方式',
  `product_number` int DEFAULT NULL COMMENT '产品数量',
  `install_fee` decimal(10,2) DEFAULT NULL COMMENT '安装费用',
  `product_no` varchar(20) DEFAULT NULL COMMENT '产品编号',
  PRIMARY KEY (`id`),
  KEY `SIGNDATE` (`sign_date`),
  KEY `CUSTOMERCOMPANY` (`customer_company`),
  KEY `CUSTOMERNAME` (`customer_name`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `salesman_contract`
--

LOCK TABLES `salesman_contract` WRITE;
/*!40000 ALTER TABLE `salesman_contract` DISABLE KEYS */;
INSERT INTO `salesman_contract` VALUES (8,'XS2302160001','2023-02-16','专用','15052382109','wuwuwu',NULL,NULL,NULL,'河北造船厂','石永明','17744426066','王红伟','15562673348','合同已签订',NULL,NULL,'河北省张家口市三里屯县五河小区3号楼3单元4楼',NULL,NULL,NULL,NULL,300.50,'送货',1,NULL,'RMGL56523A'),(11,'XS2302160002','2023-02-16','普通','55555','王天乐',NULL,NULL,NULL,'天津钢铁厂','李汉','15544426066','李阳','11262673348','合同已签订',NULL,NULL,'天津市和平区窝窝乡三笠小区3号楼3单元4楼',NULL,NULL,NULL,NULL,600.00,'自提',2,NULL,'DNGL10086A');
/*!40000 ALTER TABLE `salesman_contract` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sys_dict`
--

DROP TABLE IF EXISTS `sys_dict`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `sys_dict` (
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '名称',
  `value` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '内容',
  `type` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '类型'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci ROW_FORMAT=DYNAMIC;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_dict`
--

LOCK TABLES `sys_dict` WRITE;
/*!40000 ALTER TABLE `sys_dict` DISABLE KEYS */;
INSERT INTO `sys_dict` VALUES ('user','el-icon-user','icon'),('house','el-icon-house','icon'),('menu','el-icon-menu','icon'),('s-custom','el-icon-s-custom','icon'),('s-grid','el-icon-s-grid','icon'),('document','el-icon-document','icon');
/*!40000 ALTER TABLE `sys_dict` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sys_menu`
--

DROP TABLE IF EXISTS `sys_menu`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `sys_menu` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT 'id',
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '名称',
  `path` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '路径',
  `icon` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '图标',
  `description` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '描述',
  `pid` int DEFAULT NULL COMMENT '父级id',
  `page_path` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '页面路径',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci ROW_FORMAT=DYNAMIC;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_menu`
--

LOCK TABLES `sys_menu` WRITE;
/*!40000 ALTER TABLE `sys_menu` DISABLE KEYS */;
INSERT INTO `sys_menu` VALUES (1,'主页','/home','el-icon-house','1',NULL,'Home'),(2,'系统管理',NULL,'el-icon-s-grid',NULL,NULL,NULL),(3,'用户管理','/user','el-icon-user',NULL,2,'User'),(4,'角色管理','/role','el-icon-s-custom',NULL,2,'Role'),(5,'菜单管理','/menu','el-icon-menu',NULL,2,'Menu'),(6,'控制模块','/controll','el-icon-menu',NULL,NULL,'Controll');
/*!40000 ALTER TABLE `sys_menu` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sys_role`
--

DROP TABLE IF EXISTS `sys_role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `sys_role` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT 'id',
  `name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '名称',
  `description` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '描述',
  `flag` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '唯一标识',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci ROW_FORMAT=DYNAMIC;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_role`
--

LOCK TABLES `sys_role` WRITE;
/*!40000 ALTER TABLE `sys_role` DISABLE KEYS */;
INSERT INTO `sys_role` VALUES (1,'admin','admin','ROLE_ADMIN'),(2,'other','other','ROLE_USER');
/*!40000 ALTER TABLE `sys_role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sys_role_menu`
--

DROP TABLE IF EXISTS `sys_role_menu`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `sys_role_menu` (
  `role_id` int NOT NULL COMMENT '角色id',
  `menu_id` int NOT NULL COMMENT '菜单id',
  PRIMARY KEY (`role_id`,`menu_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci ROW_FORMAT=DYNAMIC COMMENT='角色菜单关系表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_role_menu`
--

LOCK TABLES `sys_role_menu` WRITE;
/*!40000 ALTER TABLE `sys_role_menu` DISABLE KEYS */;
INSERT INTO `sys_role_menu` VALUES (1,1),(1,2),(1,3),(1,4),(1,5),(1,6),(2,2),(2,3),(2,4);
/*!40000 ALTER TABLE `sys_role_menu` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sys_user`
--

DROP TABLE IF EXISTS `sys_user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `sys_user` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '用户表的ID主键',
  `username` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '用户名',
  `password` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '用户密码',
  `name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '用户真实姓名',
  `nickname` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '昵称',
  `email` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '邮箱',
  `phone` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '电话',
  `address` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '地址',
  `role` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '角色',
  `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  `avatar_url` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '头像',
  `position` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '职位',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE KEY `username` (`username`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=37 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci ROW_FORMAT=DYNAMIC;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_user`
--

LOCK TABLES `sys_user` WRITE;
/*!40000 ALTER TABLE `sys_user` DISABLE KEYS */;
INSERT INTO `sys_user` VALUES (1,'wizard','123456','黄鎏都','黄咕咕不是咕咕侠','1993362493@qq.com','1505238210912','重庆市江北区1','admin','2022-11-16 13:27:31',NULL,'管理员'),(8,'hibiki','123456','安达明日梦','响鬼','16633@qq.com',NULL,'重庆','admin','2022-11-17 02:52:56',NULL,'供热县级经理'),(21,'wizardhibiki','1234567','我','巫骑响鬼',NULL,NULL,NULL,NULL,'2022-11-16 13:28:22',NULL,'供热收费员'),(25,'850154120','123456','王旺旺','王汪汪',NULL,NULL,NULL,'admin','2022-11-17 11:39:11',NULL,'供热市级经理'),(26,'1993362493','12345678','吴玉青','吴吴吴',NULL,NULL,NULL,'other','2022-11-17 11:36:52',NULL,'供热市级经理'),(27,'15052382109','123456789','wuwuwu','12345',NULL,NULL,NULL,NULL,'2022-08-28 11:32:18',NULL,NULL),(28,'saber','123456','黄','咕咕侠','','','重庆市','','2022-08-30 07:27:02',NULL,NULL),(29,'revice','1234567','aaaa','黄','1993362493@qq.com','1091','江北区1','','2022-08-31 03:41:08',NULL,NULL),(30,'55555','12345678','王天乐','黄','1993362493@qq.com','1091','江北区1','other','2022-11-17 02:52:04',NULL,'供热收费员'),(36,'test','test',NULL,NULL,NULL,NULL,NULL,'other','2022-11-17 02:50:27',NULL,NULL);
/*!40000 ALTER TABLE `sys_user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `water_system_module`
--

DROP TABLE IF EXISTS `water_system_module`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `water_system_module` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '主键',
  `soft_water_tank_level` float(14,5) DEFAULT NULL COMMENT '软化水箱液位',
  `soft_water_tank_high_limit` float(14,5) DEFAULT NULL COMMENT '软化水箱高限位',
  `soft_water_tank_low_limit` float(14,5) DEFAULT NULL COMMENT '软化水箱低限位',
  `refill_pump_start` int DEFAULT NULL COMMENT '补水泵启动',
  `refill_pump_start_manually` int DEFAULT NULL COMMENT '补水泵手动启动',
  `refill_pump_start_automatically` int DEFAULT NULL COMMENT '补水泵自动启动',
  `refill_pump_run` int DEFAULT NULL COMMENT '补水泵运行',
  `refill_pump_fail` int DEFAULT NULL COMMENT '补水泵故障',
  `water_divider_supplywater_temperature` float(14,5) DEFAULT NULL COMMENT '分水器供水温度',
  `water_divider_supplywater_high_temperature_setting` float(14,5) DEFAULT NULL COMMENT '分水器供水温度高设定',
  `water_divider_supplywater_pressure` float(14,5) DEFAULT NULL COMMENT '分水器供水压力',
  `water_divider_supplywater_high_pressure_setting` float(14,5) DEFAULT NULL COMMENT '分水器供水压力高设定',
  `water_collector_backwater_temperature` float(14,5) DEFAULT NULL COMMENT '集水器回水温度',
  `water_collector_backwater_high_temperature_setting` float(14,5) DEFAULT NULL COMMENT '集水器回水温度高设定',
  `water_collector_backwater_pressure` float(14,5) DEFAULT NULL COMMENT '集水器回水压力',
  `water_collector_backwater_high_pressure_setting` float(14,5) DEFAULT NULL COMMENT '集水器回水压力高设定',
  `pressure_relief_valve_start` int DEFAULT NULL COMMENT '泄压阀启动',
  `pressure_relief_valve_start_manually` int DEFAULT NULL COMMENT '泄压阀手动启动',
  `pressure_relief_valve_start_automatically` int DEFAULT NULL COMMENT '泄压阀自动启动',
  `pressure_relief_valve_run` int DEFAULT NULL COMMENT '泄压阀运行',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb3 ROW_FORMAT=DYNAMIC;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `water_system_module`
--

LOCK TABLES `water_system_module` WRITE;
/*!40000 ALTER TABLE `water_system_module` DISABLE KEYS */;
INSERT INTO `water_system_module` VALUES (1,1.00000,111.00000,11.00000,1,1,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL);
/*!40000 ALTER TABLE `water_system_module` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-03-03 14:38:03
