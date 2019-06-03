/*
Navicat MySQL Data Transfer

Source Server         : 本地
Source Server Version : 50644
Source Host           : localhost:3306
Source Database       : oauth2

Target Server Type    : MYSQL
Target Server Version : 50644
File Encoding         : 65001

Date: 2019-06-03 11:25:22
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for oauth_client_details
-- ----------------------------
DROP TABLE IF EXISTS `oauth_client_details`;
CREATE TABLE `oauth_client_details` (
  `client_id` varchar(128) NOT NULL,
  `client_name` varchar(255) DEFAULT NULL,
  `client_secret` varchar(256) DEFAULT NULL COMMENT '密码',
  `system_type` varchar(255) DEFAULT NULL,
  `resource_ids` varchar(256) DEFAULT NULL,
  `scope` varchar(256) DEFAULT NULL,
  `authorized_grant_types` varchar(256) DEFAULT NULL COMMENT '支持的授权方式',
  `web_server_redirect_uri` varchar(256) DEFAULT NULL,
  `authorities` varchar(256) DEFAULT NULL,
  `access_token_validity` int(11) DEFAULT NULL COMMENT 'access_token有效期（单位秒）',
  `refresh_token_validity` int(11) DEFAULT NULL COMMENT 'refresh_token有效期（单位秒）',
  `additional_information` varchar(4096) DEFAULT NULL,
  `autoapprove` varchar(256) DEFAULT NULL,
  `createtime` datetime DEFAULT NULL,
  `updatetime` datetime DEFAULT NULL,
  PRIMARY KEY (`client_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='oauth2的client表';

-- ----------------------------
-- Records of oauth_client_details
-- ----------------------------
INSERT INTO `oauth_client_details` VALUES ('system', null, '$2a$10$cjYA9bD6u2QNkiqBu44YEOpM3UwqhT7rucN1IeGT7s.gBeJ7iNtNW', null, null, 'app', 'authorization_code,password,refresh_token,client_credentials', null, null, '28800', null, null, null, null, null);

-- ----------------------------
-- Table structure for oauth_user_credentials
-- ----------------------------
DROP TABLE IF EXISTS `oauth_user_credentials`;
CREATE TABLE `oauth_user_credentials` (
  `id` varchar(50) NOT NULL,
  `user_id` varchar(50) DEFAULT NULL,
  `user_code` varchar(50) DEFAULT NULL,
  `user_code_type` varchar(50) DEFAULT NULL,
  `system_type` varchar(50) DEFAULT NULL,
  `createtime` datetime DEFAULT NULL,
  `updatetime` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of oauth_user_credentials
-- ----------------------------
INSERT INTO `oauth_user_credentials` VALUES ('1234123', '123qwe1', 'admin', 'userCode', 'management', '2019-04-03 12:24:05', '2019-04-03 12:24:10');
