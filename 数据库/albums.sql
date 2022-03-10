/*
 Navicat Premium Data Transfer

 Source Server         : localhost_3306
 Source Server Type    : MySQL
 Source Server Version : 80011
 Source Host           : localhost:3306
 Source Schema         : album

 Target Server Type    : MySQL
 Target Server Version : 80011
 File Encoding         : 65001

 Date: 24/12/2021 14:03:48
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for albums
-- ----------------------------
DROP TABLE IF EXISTS `albums`;
CREATE TABLE `albums` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `createTime` datetime DEFAULT NULL,
  `username` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `username_fk` (`username`),
  CONSTRAINT `username_fk` FOREIGN KEY (`username`) REFERENCES `user` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

SET FOREIGN_KEY_CHECKS = 1;
