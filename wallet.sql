/*
 Navicat MySQL Data Transfer

 Source Server         : iot
 Source Server Type    : MySQL
 Source Server Version : 50719
 Source Host           : localhost:3306
 Source Schema         : wallet

 Target Server Type    : MySQL
 Target Server Version : 50719
 File Encoding         : 65001

 Date: 10/03/2023 10:36:58
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for user_balance_take
-- ----------------------------
DROP TABLE IF EXISTS `user_balance_take`;
CREATE TABLE `user_balance_take`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `take_date` datetime NULL DEFAULT NULL,
  `operate` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `surplus` double(255, 2) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `fk_wallet_take_username`(`username`) USING BTREE,
  CONSTRAINT `fk_wallet_take_username` FOREIGN KEY (`username`) REFERENCES `user_wallet` (`username`) ON DELETE RESTRICT ON UPDATE CASCADE
) ENGINE = InnoDB AUTO_INCREMENT = 23 CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user_balance_take
-- ----------------------------
INSERT INTO `user_balance_take` VALUES (9, 'wsz', '2023-03-10 00:47:12', '-100', 20.00);
INSERT INTO `user_balance_take` VALUES (10, 'wsz', '2023-03-10 00:47:13', '+20', 40.00);
INSERT INTO `user_balance_take` VALUES (11, 'wsz', '2023-03-10 00:50:16', '-100', 1020.00);
INSERT INTO `user_balance_take` VALUES (12, 'wsz', '2023-03-10 00:50:16', '+20', 1040.00);
INSERT INTO `user_balance_take` VALUES (13, 'wsz', '2023-03-10 00:50:46', '-100', 940.00);
INSERT INTO `user_balance_take` VALUES (14, 'wsz', '2023-03-10 00:50:46', '+20', 960.00);
INSERT INTO `user_balance_take` VALUES (15, 'wsz', '2023-03-10 00:51:38', '-100', 860.00);
INSERT INTO `user_balance_take` VALUES (16, 'wsz', '2023-03-10 00:51:38', '+20', 880.00);
INSERT INTO `user_balance_take` VALUES (17, 'wsz', '2023-03-10 00:52:18', '-100', 780.00);
INSERT INTO `user_balance_take` VALUES (18, 'wsz', '2023-03-10 00:52:19', '+20', 800.00);
INSERT INTO `user_balance_take` VALUES (19, 'wsz', '2023-03-10 00:54:05', '-100', 700.00);
INSERT INTO `user_balance_take` VALUES (20, 'wsz', '2023-03-10 00:54:05', '+20', 720.00);
INSERT INTO `user_balance_take` VALUES (21, 'wsz', '2023-03-10 00:54:27', '-100', 620.00);
INSERT INTO `user_balance_take` VALUES (22, 'wsz', '2023-03-10 00:54:27', '+20', 640.00);

-- ----------------------------
-- Table structure for user_wallet
-- ----------------------------
DROP TABLE IF EXISTS `user_wallet`;
CREATE TABLE `user_wallet`  (
  `username` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `balance` double(255, 2) NULL DEFAULT NULL,
  PRIMARY KEY (`username`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user_wallet
-- ----------------------------
INSERT INTO `user_wallet` VALUES ('wsz', 200.00);

SET FOREIGN_KEY_CHECKS = 1;
