/*
Navicat MySQL Data Transfer

Source Server         : 本地
Source Server Version : 50644
Source Host           : localhost:3306
Source Database       : test

Target Server Type    : MYSQL
Target Server Version : 50644
File Encoding         : 65001

Date: 2019-05-28 17:00:22
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for sys_test
-- ----------------------------
DROP TABLE IF EXISTS `sys_test`;
CREATE TABLE `sys_test` (
  `id` varchar(50) NOT NULL,
  `a` longtext,
  `b` longtext,
  `c` longtext,
  `d` varchar(255) DEFAULT NULL,
  `createtime` datetime DEFAULT NULL,
  `updatetime` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_test
-- ----------------------------
INSERT INTO `sys_test` VALUES ('1', '1', '1', '1', '1', '2019-05-27 17:09:13', '2019-05-27 17:09:16');
