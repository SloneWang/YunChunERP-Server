/*
SQLyog Ultimate v12.09 (64 bit)
MySQL - 5.7.19-log : Database - yunchun
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
/*Table structure for table `account_record` */

DROP TABLE IF EXISTS `account_record`;

CREATE TABLE `account_record` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `account_date` datetime NOT NULL COMMENT '动账时间',
  `pay_or_collect` varchar(50) NOT NULL COMMENT '收入/支出',
  `account_type` varchar(50) NOT NULL COMMENT '收支类型',
  `employee_no` varchar(50) NOT NULL COMMENT '员工编号',
  `target_name` varchar(50) NOT NULL COMMENT '目标单位',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `account_record` */

/*Table structure for table `contract_history` */

DROP TABLE IF EXISTS `contract_history`;

CREATE TABLE `contract_history` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `contract_no` varchar(50) DEFAULT NULL COMMENT '合同编号',
  `modify_time` datetime DEFAULT NULL COMMENT '修改时间',
  `modify_by` varchar(50) DEFAULT NULL COMMENT '改动人编号',
  `review_by` varchar(50) DEFAULT NULL COMMENT '审核人编号',
  `remark` varchar(500) DEFAULT NULL COMMENT '备注信息',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8;

/*Data for the table `contract_history` */

insert  into `contract_history`(`id`,`contract_no`,`modify_time`,`modify_by`,`review_by`,`remark`) values (11,'XS000001','2023-04-08 07:41:05','wizard','wizard','发票类型不小心填错了'),(12,'XS000001','2023-04-08 08:21:58','wizard',NULL,NULL);

/*Table structure for table `contract_information` */

DROP TABLE IF EXISTS `contract_information`;

CREATE TABLE `contract_information` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `member_no` varchar(10) DEFAULT NULL COMMENT '会员编号',
  `invoice_type` varchar(10) DEFAULT NULL COMMENT '发票类型',
  `create_employee_no` varchar(50) DEFAULT NULL COMMENT '创建员工编号',
  `contract_lifecycle` varchar(10) DEFAULT NULL COMMENT '合同生命周期',
  `install_address` varchar(50) DEFAULT NULL COMMENT '安装地址',
  `delivery_method` varchar(2) DEFAULT NULL COMMENT '发货方式',
  `employee_no` varchar(20) DEFAULT NULL COMMENT '负责员工编号',
  `balance` decimal(10,2) DEFAULT NULL COMMENT '余额或欠款',
  `sign_date` date DEFAULT NULL COMMENT '签约日期',
  `sign_fee` decimal(10,2) DEFAULT NULL COMMENT '订金',
  `pick_fee` decimal(10,2) DEFAULT NULL COMMENT '提货收款',
  `install_fee` decimal(10,2) DEFAULT NULL COMMENT '安装收款',
  `warranty_fee` decimal(10,2) DEFAULT NULL COMMENT '质保收款',
  `warranty_period` int(3) DEFAULT NULL COMMENT '质保时间(月)',
  `contract_tax` decimal(10,2) DEFAULT NULL COMMENT '合同税率',
  `contract_no` varchar(50) DEFAULT NULL COMMENT '合同编号',
  `reviewer_no` varchar(50) DEFAULT NULL COMMENT '审核人编号',
  `contract_document` varchar(100) DEFAULT NULL COMMENT '合同文件地址',
  `tag` int(11) DEFAULT '0' COMMENT '审核标志',
  `pick_date` date DEFAULT NULL COMMENT '实际提货日期',
  `install_date` date DEFAULT NULL COMMENT '实际安装日期',
  `install_cost` decimal(10,2) DEFAULT NULL COMMENT '安装成本',
  `total_gross_profit` decimal(10,2) DEFAULT NULL COMMENT '总毛利润',
  `total_net_profit` decimal(10,2) DEFAULT NULL COMMENT '总净利润',
  PRIMARY KEY (`id`),
  KEY `SIGNDATE` (`sign_date`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8;

/*Data for the table `contract_information` */

/*Table structure for table `control_` */

DROP TABLE IF EXISTS `control_`;

CREATE TABLE `control_` (
  `id` int(11) NOT NULL,
  `air_source_circulating_pump1motor_power` float NOT NULL,
  `air_source_circulating_pump2motor_power` float NOT NULL,
  `air_source_consumption_power` float NOT NULL,
  `blower_motor_power` float DEFAULT NULL,
  `circulating_pump1fail` int(11) NOT NULL,
  `circulating_pump1motor_power` float NOT NULL,
  `circulating_pump1run` int(11) NOT NULL,
  `circulating_pump1start_stop_automatically` int(11) NOT NULL,
  `circulating_pump1start_stop_manually` int(11) NOT NULL,
  `circulating_pump2fail` int(11) NOT NULL,
  `circulating_pump2motor_power` float NOT NULL,
  `circulating_pump2run` int(11) NOT NULL,
  `circulating_pump2start_stop_automatically` int(11) NOT NULL,
  `circulating_pump2start_stop_manually` int(11) NOT NULL,
  `controller_fail` int(11) NOT NULL,
  `controller_motor_power` float DEFAULT NULL,
  `controller_run` int(11) DEFAULT NULL,
  `controller_start` int(11) DEFAULT NULL,
  `controller_start_automatically` int(11) DEFAULT NULL,
  `controller_start_manually` int(11) DEFAULT NULL,
  `denitration_mixer_motor_power` float NOT NULL,
  `denitration_spray_pump_motor_power` float NOT NULL,
  `discharge_valve_motor_power` float NOT NULL,
  `flue_gas_recirculation_fan` float NOT NULL,
  `gas_storage_motor_power_device` float NOT NULL,
  `grate_reducer_fail` int(11) DEFAULT NULL,
  `grate_reducer_power` float DEFAULT NULL,
  `grate_reducer_run` int(11) DEFAULT NULL,
  `grate_reducer_start_automatically` int(11) DEFAULT NULL,
  `grate_reducer_start_manually` int(11) DEFAULT NULL,
  `grate_reducer_start_stop_automatically` int(11) DEFAULT NULL,
  `grate_reducer_start_stop_manually` int(11) DEFAULT NULL,
  `induced_draft_fan_fail` int(11) DEFAULT NULL,
  `induced_draft_fan_motor_power` float DEFAULT NULL,
  `induced_draft_fan_run` int(11) DEFAULT NULL,
  `induced_draft_fan_start_automatically` int(11) DEFAULT NULL,
  `induced_draft_fan_start_manually` int(11) DEFAULT NULL,
  `multibucket_elevator_power` float NOT NULL,
  `regulator_pump_motor_power` float NOT NULL,
  `secondary_blower_power` float DEFAULT NULL,
  `slag_remover_fail` int(11) NOT NULL,
  `slag_remover_motor_power` float NOT NULL,
  `slag_remover_run` int(11) NOT NULL,
  `slag_remover_start_stop_automatically` int(11) NOT NULL,
  `slag_remover_start_stop_manually` int(11) NOT NULL,
  `z_type_conveyor_motor_power` float NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_estonian_ci;

/*Data for the table `control_` */

/*Table structure for table `controlmodule` */

DROP TABLE IF EXISTS `controlmodule`;

CREATE TABLE `controlmodule` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `induced_draft_fan_start_manually` int(11) DEFAULT NULL COMMENT '引风机手动启动',
  `induced_draft_fan_start_automatically` int(11) DEFAULT NULL COMMENT '引风机自动启动',
  `induced_draft_fan_run` int(11) DEFAULT NULL COMMENT '引风机运行',
  `induced_draft_fan_fail` int(11) DEFAULT NULL COMMENT '引风机故障',
  `induced_draft_fan_motor_power` float(14,5) DEFAULT NULL COMMENT '引风机电机功率',
  `blower_motor_power` float(14,5) DEFAULT NULL COMMENT '鼓风机电机功率',
  `secondary_blower_power` float(14,5) DEFAULT NULL COMMENT '二次鼓风机功率',
  `grate_reducer_start_manually` int(11) DEFAULT NULL COMMENT '炉排减速机手动启动',
  `grate_reducer_start_automatically` int(11) DEFAULT NULL COMMENT '炉排减速机自动启动',
  `grate_reducer_start_stop_manually` int(11) DEFAULT NULL COMMENT '炉排减速机手动启停',
  `grate_reducer_start_stop_automatically` int(11) DEFAULT NULL COMMENT '炉排减速机自动启停',
  `grate_reducer_fail` int(11) DEFAULT NULL COMMENT '炉排减速机故障',
  `grate_reducer_run` int(11) DEFAULT NULL COMMENT '炉排减速机运行',
  `grate_reducer_power` float(14,5) DEFAULT NULL COMMENT '炉排减速机功率',
  `controller_start_manually` int(11) DEFAULT NULL COMMENT '控料器手动启动',
  `controller_start_automatically` int(11) DEFAULT NULL COMMENT '控料器自动启动',
  `controller_start` int(11) DEFAULT NULL COMMENT '控料器启动',
  `controller_run` int(11) DEFAULT NULL COMMENT '控料器运行',
  `controller_motor_power` float(14,5) DEFAULT NULL COMMENT '控料器电机功率',
  `controller_fail` int(11) DEFAULT NULL COMMENT '控料器故障',
  `multibucket_elevator_power` float(14,5) DEFAULT NULL COMMENT '多斗提升机电机功率',
  `discharge_valve_motor_power` float(14,5) DEFAULT NULL COMMENT '卸料阀电机功率',
  `z_type_conveyor_motor_power` float(14,5) DEFAULT NULL COMMENT 'Z型输送机电机功率',
  `slag_remover_start_stop_manually` int(11) DEFAULT NULL COMMENT '除渣机手动启停',
  `slag_remover_start_stop_automatically` int(11) DEFAULT NULL COMMENT '除渣机自动启停',
  `slag_remover_run` int(11) DEFAULT NULL COMMENT '除渣机运行',
  `slag_remover_fail` int(11) DEFAULT NULL COMMENT '除渣机故障',
  `slag_remover_motor_power` float(14,5) DEFAULT NULL COMMENT '除渣机电机功率',
  `gas_storage_motor_power_device` float(14,5) DEFAULT NULL COMMENT '储气装置电机功率',
  `circulating_pump1_start_stop_manually` int(11) DEFAULT NULL COMMENT '循环泵1手动启停',
  `circulating_pump1_start_stop_automatically` int(11) DEFAULT NULL COMMENT '循环泵1自动启停',
  `circulating_pump1_run` int(11) DEFAULT NULL COMMENT '循环泵1运行',
  `circulating_pump1_fail` int(11) DEFAULT NULL COMMENT '循环泵1故障',
  `circulating_pump1_motor_power` float(14,5) DEFAULT NULL COMMENT '循环泵1电机功率',
  `circulating_pump2_start_stop_manually` int(11) DEFAULT NULL COMMENT '循环泵2手动启停',
  `circulating_pump2_start_stop_automatically` int(11) DEFAULT NULL COMMENT '循环泵2自动启停',
  `circulating_pump2_run` int(11) DEFAULT NULL COMMENT '循环泵2运行',
  `circulating_pump2_fail` int(11) DEFAULT NULL COMMENT '循环泵2故障',
  `circulating_pump2_motor_power` float(14,5) DEFAULT NULL COMMENT '循环泵2电机功率',
  `regulator_pump_motor_power` float(14,5) DEFAULT NULL COMMENT '稳压泵电机功率',
  `denitration_spray_pump_motor_power` float(14,5) DEFAULT NULL COMMENT '脱硝喷淋泵电机功率',
  `denitration_mixer_motor_power` float(14,5) DEFAULT NULL COMMENT '脱硝搅拌机电机功率',
  `air_source_consumption_power` float(14,5) DEFAULT NULL COMMENT '空气源用电功率',
  `air_source_circulating_pump1_motor_power` float(14,5) DEFAULT NULL COMMENT '空气源循环泵1电机功率',
  `air_source_circulating_pump2_motor_power` float(14,5) DEFAULT NULL COMMENT '空气源循环泵2电机功率',
  `flue_gas_recirculation_fan` float(14,5) DEFAULT NULL COMMENT '烟气再循环风机',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

/*Data for the table `controlmodule` */

insert  into `controlmodule`(`id`,`induced_draft_fan_start_manually`,`induced_draft_fan_start_automatically`,`induced_draft_fan_run`,`induced_draft_fan_fail`,`induced_draft_fan_motor_power`,`blower_motor_power`,`secondary_blower_power`,`grate_reducer_start_manually`,`grate_reducer_start_automatically`,`grate_reducer_start_stop_manually`,`grate_reducer_start_stop_automatically`,`grate_reducer_fail`,`grate_reducer_run`,`grate_reducer_power`,`controller_start_manually`,`controller_start_automatically`,`controller_start`,`controller_run`,`controller_motor_power`,`controller_fail`,`multibucket_elevator_power`,`discharge_valve_motor_power`,`z_type_conveyor_motor_power`,`slag_remover_start_stop_manually`,`slag_remover_start_stop_automatically`,`slag_remover_run`,`slag_remover_fail`,`slag_remover_motor_power`,`gas_storage_motor_power_device`,`circulating_pump1_start_stop_manually`,`circulating_pump1_start_stop_automatically`,`circulating_pump1_run`,`circulating_pump1_fail`,`circulating_pump1_motor_power`,`circulating_pump2_start_stop_manually`,`circulating_pump2_start_stop_automatically`,`circulating_pump2_run`,`circulating_pump2_fail`,`circulating_pump2_motor_power`,`regulator_pump_motor_power`,`denitration_spray_pump_motor_power`,`denitration_mixer_motor_power`,`air_source_consumption_power`,`air_source_circulating_pump1_motor_power`,`air_source_circulating_pump2_motor_power`,`flue_gas_recirculation_fan`) values (1,1,1,1,1,2.00000,1.00000,1.00000,1,1,2,2,2,0,0.00000,0,0,0,0,0.00000,0,0.00000,0.00000,1.00000,0,0,0,0,0.00000,0.00000,0,0,0,0,0.00000,0,0,0,0,0.00000,0.00000,0.00000,0.00000,0.00000,0.00000,0.00000,0.00000),(2,2,2,2,3,4.00000,5.00000,6.00000,7,8,1,9,1,1,NULL,1,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(5,1,0,0,0,3.00000,0.00000,0.00000,0,0,0,0,0,0,0.00000,0,0,0,0,0.00000,0,0.00000,0.00000,0.00000,0,0,0,0,0.00000,0.00000,0,0,0,0,0.00000,0,0,0,0,0.00000,0.00000,0.00000,0.00000,0.00000,0.00000,0.00000,0.00000),(6,1,2,3,0,6.00000,6.00000,0.00000,0,0,0,0,0,0,0.00000,0,0,0,0,0.00000,0,0.00000,0.00000,0.00000,0,0,0,0,0.00000,0.00000,0,0,0,0,0.00000,0,0,0,0,0.00000,0.00000,0.00000,0.00000,0.00000,0.00000,0.00000,0.00000),(7,0,0,0,0,3.00000,4.00000,0.00000,0,0,0,0,0,0,0.00000,0,0,0,0,0.00000,0,0.00000,0.00000,0.00000,0,0,0,0,0.00000,0.00000,0,0,0,0,0.00000,0,0,0,0,0.00000,0.00000,0.00000,0.00000,0.00000,0.00000,0.00000,0.00000);

/*Table structure for table `customer_information` */

DROP TABLE IF EXISTS `customer_information`;

CREATE TABLE `customer_information` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `member_no` varchar(20) DEFAULT NULL COMMENT '会员号码',
  `customer_name` varchar(50) DEFAULT NULL COMMENT '客户姓名',
  `customer_phone` varchar(50) DEFAULT NULL COMMENT '客户电话',
  `customer_name2` varchar(50) DEFAULT NULL COMMENT '客户姓名2',
  `customer_phone2` varchar(50) DEFAULT NULL COMMENT '客户电话2',
  `company` varchar(50) DEFAULT NULL COMMENT '客户公司',
  `industry` varchar(50) DEFAULT NULL COMMENT '所属行业',
  `customer_job` varchar(50) DEFAULT NULL COMMENT '客户职务',
  `channel` varchar(50) DEFAULT NULL COMMENT '渠道来源',
  `tag` int(2) DEFAULT '1' COMMENT '是否生效',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;

/*Data for the table `customer_information` */

insert  into `customer_information`(`id`,`member_no`,`customer_name`,`customer_phone`,`customer_name2`,`customer_phone2`,`company`,`industry`,`customer_job`,`channel`,`tag`) values (7,'00000001','李汉','15544426066','李阳','11262673348','天津钢铁厂','钢铁生产','业务员','微信公众号',1),(10,'00000002','网三','13344426066','王五','18862673348','北京钢铁厂','货物运输','经理','qqqq公众号',1);

/*Table structure for table `heat_authority` */

DROP TABLE IF EXISTS `heat_authority`;

CREATE TABLE `heat_authority` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `username` varchar(50) DEFAULT NULL COMMENT '用户名',
  `admin_authority` varchar(500) DEFAULT NULL COMMENT '人员管理权限',
  `custom_authority` varchar(500) DEFAULT NULL COMMENT '负责区域权限',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=27 DEFAULT CHARSET=utf8;

/*Data for the table `heat_authority` */

insert  into `heat_authority`(`id`,`username`,`admin_authority`,`custom_authority`) values (16,'55555',NULL,'130827兆丰润景小区'),(17,'wizardhibiki',NULL,'120111中北天软创业学院'),(18,'wizard','13',NULL),(19,'1993362493','1308',NULL),(20,'wizard','11',NULL),(21,'wizard','12',NULL),(22,'850154120','1101',NULL),(23,'hibiki','130827',NULL),(24,'admintest','13',NULL),(25,'admintest','11',NULL),(26,'admintest','12',NULL);

/*Table structure for table `heat_cost` */

DROP TABLE IF EXISTS `heat_cost`;

CREATE TABLE `heat_cost` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '序号',
  `name` varchar(10) DEFAULT NULL COMMENT '姓名',
  `phonenumber` varchar(20) DEFAULT NULL COMMENT '联系方式',
  `location` varchar(200) DEFAULT NULL COMMENT '位置',
  `heated_area` int(11) DEFAULT NULL COMMENT '供热面积(㎡)',
  `total_area` int(11) DEFAULT NULL COMMENT '全部面积(㎡)',
  `unheated_area` int(11) DEFAULT NULL COMMENT '非供热面积(㎡)',
  `unit_price` int(11) DEFAULT NULL COMMENT '单价(元/㎡)',
  `total_price` int(11) DEFAULT NULL COMMENT '总金额(元)',
  `pay` varchar(20) DEFAULT NULL COMMENT '是否缴费',
  `throughout_half_year` varchar(2) DEFAULT NULL COMMENT '全年/半年',
  `heat_state` varchar(10) DEFAULT NULL COMMENT '供热状态',
  `open_valve_cost` int(11) DEFAULT NULL COMMENT '启阀费(元/次)',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

/*Data for the table `heat_cost` */

insert  into `heat_cost`(`id`,`name`,`phonenumber`,`location`,`heated_area`,`total_area`,`unheated_area`,`unit_price`,`total_price`,`pay`,`throughout_half_year`,`heat_state`,`open_valve_cost`) values (1,'黄鎏都','11111325','黑龙江省哈尔滨市哑巴村',60,100,40,5,20,'是','全年','正在供热',20),(2,'王旺旺','14433222','黑龙江省哈尔滨市寂静岭',50,100,50,3,0,'否','半年','未供热',20),(3,'张三',NULL,NULL,0,0,0,0,0,'老赖',NULL,NULL,0);

/*Table structure for table `heat_district` */

DROP TABLE IF EXISTS `heat_district`;

CREATE TABLE `heat_district` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `province` varchar(50) DEFAULT NULL COMMENT '省',
  `city` varchar(50) DEFAULT NULL COMMENT '市',
  `county` varchar(50) DEFAULT NULL COMMENT '县',
  `residential_quarter` varchar(50) DEFAULT NULL COMMENT '小区',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;

/*Data for the table `heat_district` */

insert  into `heat_district`(`id`,`province`,`city`,`county`,`residential_quarter`) values (1,'13','08','27','兆丰润景小区'),(2,'12','01','11','中北天软创业学院'),(4,'13','08','11','龙傲天小区'),(5,'13','08','27','顺溜小区'),(6,'12','01','11','王顶堤小区'),(7,'11','01','08','魏公村社区'),(8,'13','08','26','丰收小区'),(9,'13','09','01','西湖里小区'),(10,'13','08','27','北河沿小区');

/*Table structure for table `heat_record` */

DROP TABLE IF EXISTS `heat_record`;

CREATE TABLE `heat_record` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `date` datetime DEFAULT NULL COMMENT '缴费时间',
  `amount` decimal(12,2) DEFAULT NULL COMMENT '缴费金额',
  `collector` varchar(50) DEFAULT NULL COMMENT '收费人员',
  `district` varchar(50) DEFAULT NULL COMMENT '所在小区',
  `account` varchar(50) DEFAULT NULL COMMENT '户号',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

/*Data for the table `heat_record` */

insert  into `heat_record`(`id`,`date`,`amount`,`collector`,`district`,`account`) values (3,'2022-11-02 08:10:19','2000.00','user2','130827兆丰润景小区','602-5'),(4,'2022-11-02 08:11:58','500.00','user2','130827兆丰润景小区','601-4'),(5,'2022-11-02 08:31:09','500.00','user2','130827兆丰润景小区','601-4'),(6,'2022-11-02 08:47:42','5000.00','user2','130827兆丰润景小区','602-10');

/*Table structure for table `heat_sys` */

DROP TABLE IF EXISTS `heat_sys`;

CREATE TABLE `heat_sys` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `pay_cycle` int(11) NOT NULL DEFAULT '12' COMMENT '缴费周期(月)',
  `interest_rate` decimal(12,2) NOT NULL DEFAULT '0.10' COMMENT '滞纳金利率(天)',
  `late_times` int(11) NOT NULL DEFAULT '0' COMMENT '欠缴标识次数',
  `unit_price` decimal(12,2) NOT NULL DEFAULT '10.00' COMMENT '单价(元/m2)',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

/*Data for the table `heat_sys` */

insert  into `heat_sys`(`id`,`pay_cycle`,`interest_rate`,`late_times`,`unit_price`) values (1,12,'0.10',3,'0.98');

/*Table structure for table `heat_user` */

DROP TABLE IF EXISTS `heat_user`;

CREATE TABLE `heat_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `name` varchar(50) DEFAULT NULL COMMENT '姓名',
  `phone` varchar(50) DEFAULT NULL COMMENT '电话',
  `district` varchar(50) DEFAULT NULL COMMENT '小区编号',
  `account` varchar(50) DEFAULT NULL COMMENT '户号',
  `heat_area` decimal(12,2) DEFAULT NULL COMMENT '供热面积',
  `date` date DEFAULT NULL COMMENT '缴费日期',
  `pay_status` varchar(50) DEFAULT '0' COMMENT '缴费状态',
  `fail_pay` int(11) DEFAULT '0' COMMENT '欠缴次数',
  `user_balance` decimal(12,2) DEFAULT NULL COMMENT '账户余额',
  `pay_amount` decimal(12,2) DEFAULT NULL COMMENT '本月应付金额',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8;

/*Data for the table `heat_user` */

insert  into `heat_user`(`id`,`name`,`phone`,`district`,`account`,`heat_area`,`date`,`pay_status`,`fail_pay`,`user_balance`,`pay_amount`) values (6,'天乐','13775674252','130827兆丰润景小区','602-5','101.00','2023-07-18','未缴费',0,'812.24','1187.76'),(7,'多啦a梦','13878434252','120111中北天软创业学院','16-2-205','77.00','2023-09-11','未缴费',0,'0.00','905.52'),(8,'阿凡达','12228434252','130827兆丰润景小区','601-4','88.00','2023-11-15','未缴费',8,'-9680.33','1034.88'),(9,'叶城','14428434252','130827兆丰润景小区','602-10','67.00','2023-04-25','已缴费',0,'3424.16','787.92'),(11,'鎏都','12328434252','130827兆丰润景小区','602-11','50.00','2023-09-11','未缴费',0,'0.00','588.00'),(12,'飞科','12328434251','130827兆丰润景小区','6011','67.00','2023-09-17','未缴费',0,'0.00','787.92'),(13,'鎏都温','12328434252','130811龙傲天小区','602-11','50.00','2023-09-11','未缴费',0,'0.00','588.00'),(15,'叶城乘','14428434252','130827顺溜小区','602-10','67.00','2023-04-25','已缴费',0,'3424.16','787.92'),(16,'阿凡','12228434252','130826丰收小区','601-4','88.00','2023-11-15','未缴费',8,'-9680.33','1034.88'),(17,'阿达','12228434252','130827北河沿小区','601-4','88.00','2023-11-15','未缴费',8,'-9680.33','1034.88'),(18,'叶问','15528434252','130827顺溜小区','602-10','67.00','2023-04-25','已缴费',0,'3424.16','787.92'),(19,'天乐乐','13775674252','120211王顶堤小区','602-5','101.00','2023-07-18','未缴费',0,'812.24','1187.76'),(20,'多啦a梦','13878434252','110108魏公村小区','16-2-205','77.00','2023-09-11','未缴费',0,'0.00','905.52');

/*Table structure for table `hibernate_sequence` */

DROP TABLE IF EXISTS `hibernate_sequence`;

CREATE TABLE `hibernate_sequence` (
  `next_val` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_estonian_ci;

/*Data for the table `hibernate_sequence` */

insert  into `hibernate_sequence`(`next_val`) values (1);

/*Table structure for table `inputparm` */

DROP TABLE IF EXISTS `inputparm`;

CREATE TABLE `inputparm` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键序号',
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
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

/*Data for the table `inputparm` */

/*Table structure for table `loading_system_module` */

DROP TABLE IF EXISTS `loading_system_module`;

CREATE TABLE `loading_system_module` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `feeding_motor_start` int(11) DEFAULT NULL COMMENT '上料电机启动',
  `feeding_machine_run` int(11) DEFAULT NULL COMMENT '上料机运行',
  `feeding_machine_fail` int(11) DEFAULT NULL COMMENT '上料机故障',
  `feeding_machine_start_manually` int(11) DEFAULT NULL COMMENT '上料机手动启动',
  `feeding_machine_start_automatically` int(11) DEFAULT NULL COMMENT '上料机自动启动',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

/*Data for the table `loading_system_module` */

insert  into `loading_system_module`(`id`,`feeding_motor_start`,`feeding_machine_run`,`feeding_machine_fail`,`feeding_machine_start_manually`,`feeding_machine_start_automatically`) values (1,1,1,2,2,4);

/*Table structure for table `mainenginemodule` */

DROP TABLE IF EXISTS `mainenginemodule`;

CREATE TABLE `mainenginemodule` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
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
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

/*Data for the table `mainenginemodule` */

insert  into `mainenginemodule`(`id`,`expansion_water_tank_level`,`expansion_water_tank_high_limit`,`expansion_water_tank_low_limit`,`hopper_temp`,`hopper_high_limit`,`hopper_low_limit`,`hopper_temp_set`,`firepot_temp`,`firepot_pres`,`firepot_high_temp_set`,`firepot_pres_high_set`,`firepot_temp_high_set`,`exhaust_smoke_temperature`,`smoke_exhaust_temperature_high_setting`,`supply_water_temp`,`supply_water_temp_set`,`supply_water_pres`,`supply_water_pres_set`,`return_water_temp`,`return_water_temp_set`,`return_water_pres`,`return_water_pres_set`,`furnace_outlet_smoke_temperature`) values (1,1.00000,1.00000,3.00000,5.00000,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(2,2.00000,0.00000,0.00000,0.00000,0.00000,0.00000,0.00000,0.00000,0.00000,0.00000,0.00000,0.00000,0.00000,0.00000,0.00000,0.00000,0.00000,0.00000,0.00000,0.00000,0.00000,0.00000,3.00000);

/*Table structure for table `material_apply` */

DROP TABLE IF EXISTS `material_apply`;

CREATE TABLE `material_apply` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `contract_no` varchar(20) DEFAULT NULL COMMENT '合同编号',
  `material_type` varchar(11) DEFAULT NULL COMMENT '材料编号',
  `material_number` decimal(10,2) DEFAULT NULL COMMENT '申领数量',
  `charge_person` varchar(10) DEFAULT NULL COMMENT '申领人编号',
  `apply_time` datetime DEFAULT NULL COMMENT '申请时间',
  `reviewer_no` varchar(20) DEFAULT NULL COMMENT '审核人编号',
  `remark` varchar(50) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `material_apply` */

/*Table structure for table `material_information` */

DROP TABLE IF EXISTS `material_information`;

CREATE TABLE `material_information` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `material_type` varchar(20) DEFAULT NULL COMMENT '材料类型',
  `material_name` varchar(20) DEFAULT NULL COMMENT '材料名称',
  `material_no` varchar(20) DEFAULT NULL COMMENT '材料编号',
  `supplier` varchar(20) DEFAULT NULL COMMENT '供应商',
  `unit` varchar(10) DEFAULT NULL COMMENT '单位',
  `unit_price` decimal(10,2) DEFAULT NULL COMMENT '单价',
  `lowest_add_rate` decimal(5,2) DEFAULT NULL COMMENT '最低加价率',
  `tag` int(2) DEFAULT '1' COMMENT '是否失效',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

/*Data for the table `material_information` */

insert  into `material_information`(`id`,`material_type`,`material_name`,`material_no`,`supplier`,`unit`,`unit_price`,`lowest_add_rate`,`tag`) values (1,'仪表阀门','铸钢截止阀','DN25','浙江耐益','台','103.00','0.28',1),(2,'金属材料','钢板','2.75*1.51*6',NULL,'吨','4460.00','0.28',1),(3,'辅机','控料器','KLQ-0.5','瓦房店永宁机械厂','台','3240.00','0.28',1),(5,'零部件','国标法兰','DN20 PN16',NULL,'片','17.00','0.30',1);

/*Table structure for table `material_list` */

DROP TABLE IF EXISTS `material_list`;

CREATE TABLE `material_list` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `material_id` int(11) DEFAULT NULL COMMENT '材料id',
  `material_number` decimal(8,2) DEFAULT NULL COMMENT '材料数量',
  `contract_id` int(50) DEFAULT NULL COMMENT '合同id',
  `tag` int(10) DEFAULT '0' COMMENT '是否生效',
  `material_price` decimal(10,2) DEFAULT NULL COMMENT '材料售价',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

/*Data for the table `material_list` */

/*Table structure for table `material_requirement` */

DROP TABLE IF EXISTS `material_requirement`;

CREATE TABLE `material_requirement` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `material_id` int(11) DEFAULT NULL COMMENT '材料id',
  `material_number` decimal(10,2) DEFAULT NULL COMMENT '材料数量',
  `product_id` int(11) DEFAULT NULL COMMENT '产品id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8;

/*Data for the table `material_requirement` */

insert  into `material_requirement`(`id`,`material_id`,`material_number`,`product_id`) values (4,1,'1.00',6),(5,2,'3.00',6),(6,3,'1.00',6),(13,1,'3.00',7),(14,2,'10.00',7),(15,3,'1.00',7);

/*Table structure for table `material_return` */

DROP TABLE IF EXISTS `material_return`;

CREATE TABLE `material_return` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `material_type` varchar(10) DEFAULT NULL COMMENT '物品种类',
  `material_number` decimal(10,2) DEFAULT NULL COMMENT '退还数量',
  `project_number` varchar(10) DEFAULT NULL COMMENT '项目编号',
  `charge_person` varchar(10) DEFAULT NULL COMMENT '负责人',
  `return_time` datetime DEFAULT NULL COMMENT '退还时间',
  `remark` varchar(50) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `material_return` */

/*Table structure for table `pay_remind_setting` */

DROP TABLE IF EXISTS `pay_remind_setting`;

CREATE TABLE `pay_remind_setting` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `employee_no` varchar(50) DEFAULT NULL COMMENT '员工编号',
  `pay_remind_time` varchar(50) DEFAULT '72' COMMENT '逾期提醒时间(天)',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `pay_remind_setting` */

/*Table structure for table `product_information` */

DROP TABLE IF EXISTS `product_information`;

CREATE TABLE `product_information` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `product_no` varchar(50) COLLATE utf8_estonian_ci DEFAULT NULL COMMENT '产品编号',
  `product_name` varchar(50) COLLATE utf8_estonian_ci DEFAULT NULL COMMENT '产品名称',
  `lowest_add_rate` decimal(8,2) DEFAULT NULL COMMENT '最低加价率',
  `co_consumable_material` decimal(10,0) DEFAULT NULL COMMENT '共耗材料',
  `composite_material` decimal(10,0) DEFAULT NULL COMMENT '合体材料',
  `masonry_labor` decimal(10,0) DEFAULT NULL COMMENT '包砌人工',
  `ontology_labor` decimal(10,0) DEFAULT NULL COMMENT '本体人工',
  `tag` int(2) DEFAULT '1' COMMENT '是否失效',
  `random_files` decimal(10,2) DEFAULT '50.00' COMMENT '随机文件',
  PRIMARY KEY (`id`),
  KEY `PRODUCTTYPE` (`product_name`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8 COLLATE=utf8_estonian_ci;

/*Data for the table `product_information` */

insert  into `product_information`(`id`,`product_no`,`product_name`,`lowest_add_rate`,`co_consumable_material`,`composite_material`,`masonry_labor`,`ontology_labor`,`tag`,`random_files`) values (6,'DZC0.5-0.7-S','蒸汽锅炉','0.28','500','0','1565','2425',1,'50.00'),(7,'DZC0.35-0.7/95/70-S','承压热水锅炉','0.28','400','101','1500','2225',1,'50.00');

/*Table structure for table `product_list` */

DROP TABLE IF EXISTS `product_list`;

CREATE TABLE `product_list` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `product_id` int(11) DEFAULT NULL COMMENT '产品id',
  `contract_id` int(50) DEFAULT NULL COMMENT '合同id',
  `product_number` decimal(8,2) DEFAULT NULL COMMENT '产品数量',
  `tag` int(10) DEFAULT '0' COMMENT '是否生效',
  `product_price` decimal(10,2) DEFAULT NULL COMMENT '产品售价',
  `project_status` varchar(10) DEFAULT '正在生产本体' COMMENT '项目状态',
  `project_start` date DEFAULT NULL COMMENT '项目开始日期',
  `project_end` date DEFAULT NULL COMMENT '项目结束日期',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

/*Data for the table `product_list` */

/*Table structure for table `product_project` */

DROP TABLE IF EXISTS `product_project`;

CREATE TABLE `product_project` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `start_time` datetime DEFAULT NULL COMMENT '开始时间',
  `end_time` datetime DEFAULT NULL COMMENT '结束时间',
  `charge_person` varchar(10) DEFAULT NULL COMMENT '项目负责人',
  `project_status` varchar(10) DEFAULT NULL COMMENT '项目状态',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `product_project` */

/*Table structure for table `project_cost` */

DROP TABLE IF EXISTS `project_cost`;

CREATE TABLE `project_cost` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `project_number` varchar(10) DEFAULT NULL COMMENT '项目编号',
  `cost_type` varchar(10) DEFAULT NULL COMMENT '成本类型',
  `detail_information` varchar(20) DEFAULT NULL COMMENT '详细信息',
  `cost_fee` decimal(10,2) DEFAULT NULL COMMENT '成本金额',
  `remark` varchar(50) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `project_cost` */

/*Table structure for table `review_request` */

DROP TABLE IF EXISTS `review_request`;

CREATE TABLE `review_request` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `review_type` varchar(20) DEFAULT NULL COMMENT '审核类型',
  `employee_no` varchar(20) DEFAULT NULL COMMENT '申请人编号',
  `remark` varchar(500) DEFAULT NULL COMMENT '申请信息',
  `request_date` datetime DEFAULT NULL COMMENT '申请时间',
  `reviewer_no` varchar(20) DEFAULT NULL COMMENT '审核人编号',
  `review_result` varchar(5) DEFAULT NULL COMMENT '审核结果',
  `review_date` datetime DEFAULT NULL COMMENT '审核时间',
  `index_no` int(10) DEFAULT '0' COMMENT '修改索引',
  `additional_information` varchar(500) DEFAULT NULL COMMENT '附加信息',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

/*Data for the table `review_request` */

insert  into `review_request`(`id`,`review_type`,`employee_no`,`remark`,`request_date`,`reviewer_no`,`review_result`,`review_date`,`index_no`,`additional_information`) values (2,'创建新合同','wizard','客户非常着急，请尽快批复','2023-04-08 07:03:40','wizard','审核通过','2023-04-08 07:07:50',16,'总基价：27260.16\n总售价：31000\n订金：5000\n提货收款：15000\n安装收款：1000\n质保收款：10000\n质保期：6'),(6,'修改合同信息','wizard','发票类型不小心填错了','2023-04-08 07:41:05','wizard','审核通过','2023-04-08 07:42:00',16,'UpdateContractVO(id=16, modifyBy=wizard, invoiceType=普通, installAddress=null, deliveryMethod=null, employeeNo=null, signFee=null, pickFee=null, installFee=null, warrantyFee=null, warrantyPeriod=null, updateReason=发票类型不小心填错了)');

/*Table structure for table `sys_dict` */

DROP TABLE IF EXISTS `sys_dict`;

CREATE TABLE `sys_dict` (
  `name` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '名称',
  `value` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '内容',
  `type` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '类型'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci ROW_FORMAT=DYNAMIC;

/*Data for the table `sys_dict` */

insert  into `sys_dict`(`name`,`value`,`type`) values ('user','el-icon-user','icon'),('house','el-icon-house','icon'),('menu','el-icon-menu','icon'),('s-custom','el-icon-s-custom','icon'),('s-grid','el-icon-s-grid','icon'),('document','el-icon-document','icon');

/*Table structure for table `sys_menu` */

DROP TABLE IF EXISTS `sys_menu`;

CREATE TABLE `sys_menu` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `name` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '名称',
  `path` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '路径',
  `icon` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '图标',
  `description` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '描述',
  `pid` int(11) DEFAULT NULL COMMENT '父级id',
  `page_path` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '页面路径',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci ROW_FORMAT=DYNAMIC;

/*Data for the table `sys_menu` */

insert  into `sys_menu`(`id`,`name`,`path`,`icon`,`description`,`pid`,`page_path`) values (1,'主页','/home','el-icon-house','1',NULL,'Home'),(2,'系统管理',NULL,'el-icon-s-grid',NULL,NULL,NULL),(3,'用户管理','/user','el-icon-user',NULL,2,'User'),(4,'角色管理','/salesman','el-icon-s-custom',NULL,2,'Salesman'),(5,'菜单管理','/menu','el-icon-menu',NULL,2,'Menu'),(8,'控制模块','/controll','el-icon-menu',NULL,1,'Controll');

/*Table structure for table `sys_role` */

DROP TABLE IF EXISTS `sys_role`;

CREATE TABLE `sys_role` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `name` varchar(50) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '名称',
  `description` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '描述',
  `flag` varchar(50) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '唯一标识',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci ROW_FORMAT=DYNAMIC;

/*Data for the table `sys_role` */

insert  into `sys_role`(`id`,`name`,`description`,`flag`) values (1,'管理员','管理员','ROLE_ADMIN'),(2,'普通用户','普通用户','ROLE_USER');

/*Table structure for table `sys_role_menu` */

DROP TABLE IF EXISTS `sys_role_menu`;

CREATE TABLE `sys_role_menu` (
  `role_id` int(11) NOT NULL COMMENT '角色id',
  `menu_id` int(11) NOT NULL COMMENT '菜单id',
  PRIMARY KEY (`role_id`,`menu_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci ROW_FORMAT=DYNAMIC COMMENT='角色菜单关系表';

/*Data for the table `sys_role_menu` */

insert  into `sys_role_menu`(`role_id`,`menu_id`) values (1,1),(1,2),(1,3),(1,4),(1,5),(1,6),(2,2),(2,3),(2,4);

/*Table structure for table `sys_settings` */

DROP TABLE IF EXISTS `sys_settings`;

CREATE TABLE `sys_settings` (
  `id` int(3) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `tax_rate` decimal(10,2) NOT NULL DEFAULT '0.04' COMMENT '税率',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

/*Data for the table `sys_settings` */

insert  into `sys_settings`(`id`,`tax_rate`) values (1,'0.04');

/*Table structure for table `sys_user` */

DROP TABLE IF EXISTS `sys_user`;

CREATE TABLE `sys_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '用户表的ID主键',
  `username` varchar(50) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '用户名',
  `password` varchar(50) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '用户密码',
  `name` varchar(50) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '用户真实姓名',
  `nickname` varchar(50) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '昵称',
  `email` varchar(50) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '邮箱',
  `phone` varchar(20) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '电话',
  `address` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '地址',
  `role` varchar(20) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '角色',
  `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  `avatar_url` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '头像',
  `position` varchar(50) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '职位',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE KEY `username` (`username`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=38 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci ROW_FORMAT=DYNAMIC;

/*Data for the table `sys_user` */

insert  into `sys_user`(`id`,`username`,`password`,`name`,`nickname`,`email`,`phone`,`address`,`role`,`create_time`,`avatar_url`,`position`) values (1,'wizard','123456','黄鎏都','黄咕咕不是咕咕侠','1993362493@qq.com','1505238210912','重庆市江北区1','admin','2022-11-16 21:27:31',NULL,'管理员'),(8,'hibiki','123456','安达明日梦','响鬼','16633@qq.com',NULL,'重庆','普通用户','2022-11-16 21:27:38',NULL,'供热县级经理'),(21,'wizardhibiki','1234567','我','巫骑响鬼',NULL,NULL,NULL,NULL,'2022-11-16 21:28:22',NULL,'供热收费员'),(25,'850154120','123456','王旺旺','王汪汪',NULL,NULL,NULL,NULL,'2022-11-16 21:29:10',NULL,'供热市级经理'),(26,'1993362493','12345678','吴玉青','吴吴吴',NULL,NULL,NULL,NULL,'2022-11-16 21:29:06',NULL,'供热市级经理'),(27,'15052382109','123456789','wuwuwu','12345',NULL,NULL,NULL,NULL,'2023-04-08 15:11:00',NULL,'销售员'),(28,'saber','123456','黄','咕咕侠','','','重庆市','','2022-08-30 15:27:02',NULL,NULL),(29,'revice','1234567','aaaa','黄','1993362493@qq.com','1091','江北区1','','2022-08-31 11:41:08',NULL,NULL),(30,'55555','12345678','王天乐','黄','1993362493@qq.com','1091','江北区1','','2022-11-16 21:28:26',NULL,'供热收费员'),(36,'test','test',NULL,NULL,NULL,NULL,NULL,'普通用户','2022-09-24 12:36:35',NULL,NULL),(37,'admintest','123456','系统测试','系统测试账号','1993362493@qq.com','1505238210912','重庆市江北区1','admin','2022-12-11 16:44:40',NULL,'管理员');

/*Table structure for table `water_system_module` */

DROP TABLE IF EXISTS `water_system_module`;

CREATE TABLE `water_system_module` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `soft_water_tank_level` float(14,5) DEFAULT NULL COMMENT '软化水箱液位',
  `soft_water_tank_high_limit` float(14,5) DEFAULT NULL COMMENT '软化水箱高限位',
  `soft_water_tank_low_limit` float(14,5) DEFAULT NULL COMMENT '软化水箱低限位',
  `refill_pump_start` int(11) DEFAULT NULL COMMENT '补水泵启动',
  `refill_pump_start_manually` int(11) DEFAULT NULL COMMENT '补水泵手动启动',
  `refill_pump_start_automatically` int(11) DEFAULT NULL COMMENT '补水泵自动启动',
  `refill_pump_run` int(11) DEFAULT NULL COMMENT '补水泵运行',
  `refill_pump_fail` int(11) DEFAULT NULL COMMENT '补水泵故障',
  `water_divider_supplywater_temperature` float(14,5) DEFAULT NULL COMMENT '分水器供水温度',
  `water_divider_supplywater_high_temperature_setting` float(14,5) DEFAULT NULL COMMENT '分水器供水温度高设定',
  `water_divider_supplywater_pressure` float(14,5) DEFAULT NULL COMMENT '分水器供水压力',
  `water_divider_supplywater_high_pressure_setting` float(14,5) DEFAULT NULL COMMENT '分水器供水压力高设定',
  `water_collector_backwater_temperature` float(14,5) DEFAULT NULL COMMENT '集水器回水温度',
  `water_collector_backwater_high_temperature_setting` float(14,5) DEFAULT NULL COMMENT '集水器回水温度高设定',
  `water_collector_backwater_pressure` float(14,5) DEFAULT NULL COMMENT '集水器回水压力',
  `water_collector_backwater_high_pressure_setting` float(14,5) DEFAULT NULL COMMENT '集水器回水压力高设定',
  `pressure_relief_valve_start` int(11) DEFAULT NULL COMMENT '泄压阀启动',
  `pressure_relief_valve_start_manually` int(11) DEFAULT NULL COMMENT '泄压阀手动启动',
  `pressure_relief_valve_start_automatically` int(11) DEFAULT NULL COMMENT '泄压阀自动启动',
  `pressure_relief_valve_run` int(11) DEFAULT NULL COMMENT '泄压阀运行',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

/*Data for the table `water_system_module` */

insert  into `water_system_module`(`id`,`soft_water_tank_level`,`soft_water_tank_high_limit`,`soft_water_tank_low_limit`,`refill_pump_start`,`refill_pump_start_manually`,`refill_pump_start_automatically`,`refill_pump_run`,`refill_pump_fail`,`water_divider_supplywater_temperature`,`water_divider_supplywater_high_temperature_setting`,`water_divider_supplywater_pressure`,`water_divider_supplywater_high_pressure_setting`,`water_collector_backwater_temperature`,`water_collector_backwater_high_temperature_setting`,`water_collector_backwater_pressure`,`water_collector_backwater_high_pressure_setting`,`pressure_relief_valve_start`,`pressure_relief_valve_start_manually`,`pressure_relief_valve_start_automatically`,`pressure_relief_valve_run`) values (1,1.00000,111.00000,11.00000,1,1,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL);

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
