/*
 Navicat Premium Data Transfer

 Source Server         : xuniji
 Source Server Type    : MySQL
 Source Server Version : 50728
 Source Host           : 192.168.211.128:3306
 Source Schema         : sharding-jdbc

 Target Server Type    : MySQL
 Target Server Version : 50728
 File Encoding         : 65001

 Date: 20/03/2020 19:24:52
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for t_dict
-- ----------------------------
DROP TABLE IF EXISTS `t_dict`;
CREATE TABLE `t_dict` (
  `id` bigint(20) NOT NULL,
  `name` varchar(255) NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Table structure for t_order0
-- ----------------------------
DROP TABLE IF EXISTS `t_order0`;
CREATE TABLE `t_order0` (
  `order_id` bigint(20) NOT NULL COMMENT '订单id',
  `user_id` bigint(20) NOT NULL COMMENT '购买的用户id',
  `product_name` varchar(255) NOT NULL COMMENT '购买的商品名称',
  PRIMARY KEY (`order_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Table structure for t_order1
-- ----------------------------
DROP TABLE IF EXISTS `t_order1`;
CREATE TABLE `t_order1` (
  `order_id` bigint(20) NOT NULL COMMENT '订单id',
  `user_id` bigint(20) NOT NULL COMMENT '购买的用户id',
  `product_name` varchar(255) NOT NULL COMMENT '购买的商品名称',
  PRIMARY KEY (`order_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Table structure for t_order_item0
-- ----------------------------
DROP TABLE IF EXISTS `t_order_item0`;
CREATE TABLE `t_order_item0` (
  `order_item_id` bigint(20) NOT NULL COMMENT '订单详情的id',
  `order_id` bigint(20) NOT NULL COMMENT '关联订单的id',
  `create_time` datetime NOT NULL COMMENT '订单创建的时间',
  `user_id` bigint(10) NOT NULL,
  PRIMARY KEY (`order_item_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Table structure for t_order_item1
-- ----------------------------
DROP TABLE IF EXISTS `t_order_item1`;
CREATE TABLE `t_order_item1` (
  `order_item_id` bigint(20) NOT NULL COMMENT '订单详情的id',
  `order_id` bigint(20) NOT NULL COMMENT '关联订单的id',
  `create_time` datetime NOT NULL COMMENT '订单创建的时间',
  `user_id` bigint(10) NOT NULL,
  PRIMARY KEY (`order_item_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

SET FOREIGN_KEY_CHECKS = 1;
