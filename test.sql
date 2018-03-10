/*
Navicat MySQL Data Transfer

Source Server         : test
Source Server Version : 50537
Source Host           : localhost:3306
Source Database       : test

Target Server Type    : MYSQL
Target Server Version : 50537
File Encoding         : 65001

Date: 2018-03-10 19:19:28
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for ball
-- ----------------------------
DROP TABLE IF EXISTS `ball`;
CREATE TABLE `ball` (
  `id` int(11) NOT NULL,
  `date` datetime DEFAULT NULL,
  `r1` int(255) DEFAULT NULL,
  `r2` int(255) DEFAULT NULL,
  `r3` int(255) DEFAULT NULL,
  `r4` int(255) DEFAULT NULL,
  `r5` int(255) DEFAULT NULL,
  `r6` int(255) DEFAULT NULL,
  `b` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of ball
-- ----------------------------
