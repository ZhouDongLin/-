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

 Date: 24/12/2021 22:29:50
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for photos
-- ----------------------------
DROP TABLE IF EXISTS `photos`;
CREATE TABLE `photos` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `key` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `uploadTime` datetime DEFAULT NULL,
  `albumid` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `album_fk` (`albumid`),
  CONSTRAINT `album_fk` FOREIGN KEY (`albumid`) REFERENCES `albums` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

SET FOREIGN_KEY_CHECKS = 1;
