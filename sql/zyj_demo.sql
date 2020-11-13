/*
 Navicat Premium Data Transfer

 Source Server         : mysql8
 Source Server Type    : MySQL
 Source Server Version : 80011
 Source Host           : 192.168.193.135:3306
 Source Schema         : zyj_demo

 Target Server Type    : MySQL
 Target Server Version : 80011
 File Encoding         : 65001

 Date: 13/11/2020 17:41:08
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for zyj_menu
-- ----------------------------
DROP TABLE IF EXISTS `zyj_menu`;
CREATE TABLE `zyj_menu`  (
  `menu_id` bigint(64) NOT NULL AUTO_INCREMENT COMMENT '主键di',
  `menu_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '菜单名称',
  `menu_parent_id` int(64) NOT NULL COMMENT '菜单父级id',
  `menu_url` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '菜单路径',
  `menu_css` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '菜单样式',
  `menu_permissions` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'shiro权限标志',
  `sort_no` int(64) NULL DEFAULT NULL COMMENT '菜单排序',
  `is_show` int(255) NULL DEFAULT NULL COMMENT '是否显示 0：显示1：不显示',
  `created_by` int(64) NULL DEFAULT NULL COMMENT '创建人',
  `created_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `updated_by` int(64) NULL DEFAULT NULL COMMENT '修改人',
  `updated_time` datetime(0) NULL DEFAULT NULL COMMENT '修改时间',
  `del_flag` int(1) NULL DEFAULT NULL COMMENT '删除标识0:正常1：已删除',
  PRIMARY KEY (`menu_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for zyj_role
-- ----------------------------
DROP TABLE IF EXISTS `zyj_role`;
CREATE TABLE `zyj_role`  (
  `role_id` bigint(64) NOT NULL AUTO_INCREMENT COMMENT '角色id',
  `role_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '角色名称',
  `role_desc` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '角色描述',
  `role_type` int(64) NULL DEFAULT NULL COMMENT '角色类型',
  `role_order` int(64) NULL DEFAULT NULL COMMENT '角色排序',
  `created_by` int(64) NULL DEFAULT NULL COMMENT '创建人',
  `created_time` datetime(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `update_by` int(64) NULL DEFAULT NULL COMMENT '修改人',
  `update_time` datetime(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '修改时间',
  `del_flag` int(1) NULL DEFAULT NULL COMMENT '删除标记',
  PRIMARY KEY (`role_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of zyj_role
-- ----------------------------
INSERT INTO `zyj_role` VALUES (1, '超级管理员', '超级管理员', 1, 1, NULL, '2020-11-12 15:38:50', NULL, '2020-11-12 15:38:52', 0);

-- ----------------------------
-- Table structure for zyj_role_menu
-- ----------------------------
DROP TABLE IF EXISTS `zyj_role_menu`;
CREATE TABLE `zyj_role_menu`  (
  `role_menuid` bigint(64) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `role_id` int(64) NOT NULL COMMENT '角色id',
  `menu_id` int(64) NOT NULL COMMENT '菜单id',
  `created_by` int(64) NULL DEFAULT NULL COMMENT '添加人',
  `created_time` datetime(0) NULL DEFAULT NULL COMMENT '添加时间',
  `updated_by` int(64) NULL DEFAULT NULL COMMENT '修改人',
  `updated_time` datetime(0) NULL DEFAULT NULL COMMENT '修改时间',
  `del_flag` int(1) NULL DEFAULT NULL COMMENT '删除状态',
  PRIMARY KEY (`role_menuid`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for zyj_user
-- ----------------------------
DROP TABLE IF EXISTS `zyj_user`;
CREATE TABLE `zyj_user`  (
  `user_id` bigint(64) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `user_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户名',
  `login_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '登录名称',
  `user_password` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '登录密码',
  `user_salt` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '加盐',
  `user_sex` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '性别0：男 1：女',
  `user_mobile` int(11) NULL DEFAULT NULL COMMENT '手机号',
  `user_type` int(1) NULL DEFAULT NULL COMMENT '用户类型',
  `user_groupid` int(64) NULL DEFAULT NULL COMMENT '用户所属群组',
  `user_status` int(1) NOT NULL COMMENT '账号状态 0：正常 1：锁定',
  `created_by` int(64) NULL DEFAULT NULL COMMENT '创建人',
  `created_time` datetime(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `update_by` int(64) NULL DEFAULT NULL COMMENT '修改人',
  `update_time` datetime(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '修改时间',
  `del_flag` int(1) NULL DEFAULT NULL COMMENT '删除标记0:正常 1：删除',
  PRIMARY KEY (`user_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 8 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of zyj_user
-- ----------------------------
INSERT INTO `zyj_user` VALUES (1, '超级管理员', 'system', '94ac6e5dcb91387718e0b3f1fb1cf3dc72bc1c97e1c51d60dc189475ac28f12c', 'mg9tfQYDjp4uhj6K0S8B', '0', NULL, NULL, NULL, 0, 1, '2020-11-13 09:17:10', NULL, '2020-11-13 09:17:10', 0);
INSERT INTO `zyj_user` VALUES (2, '张三', 'zhangsan', 'aaaaaa', NULL, '0', NULL, NULL, NULL, 0, NULL, '2020-11-12 08:52:30', NULL, '2020-11-12 08:52:30', 0);
INSERT INTO `zyj_user` VALUES (3, '李四', 'lisi', 'aaaaaa', NULL, '0', NULL, NULL, NULL, 0, NULL, '2020-11-12 08:52:31', NULL, '2020-11-12 08:52:31', 0);
INSERT INTO `zyj_user` VALUES (4, '王五', 'wangwu', 'aaaaaa', NULL, '0', NULL, NULL, NULL, 0, NULL, '2020-11-12 08:52:31', NULL, '2020-11-12 08:52:31', 0);
INSERT INTO `zyj_user` VALUES (5, '麻六', 'maliu', 'aaaaaa', NULL, '0', NULL, NULL, NULL, 0, NULL, '2020-11-12 08:52:32', NULL, '2020-11-12 08:52:32', 0);
INSERT INTO `zyj_user` VALUES (6, '宋七', 'songqi', 'aaaaaa', NULL, '0', NULL, NULL, NULL, 0, NULL, '2020-11-12 08:52:35', NULL, '2020-11-12 08:52:35', 0);
INSERT INTO `zyj_user` VALUES (7, '丁建生', 'dingjiansheng', '0b4e7a0e5fe84ad35fb5f95b9ceeac79', NULL, '0', NULL, NULL, NULL, 0, NULL, '2020-11-12 08:52:34', NULL, '2020-11-12 08:52:34', 0);

-- ----------------------------
-- Table structure for zyj_user_role
-- ----------------------------
DROP TABLE IF EXISTS `zyj_user_role`;
CREATE TABLE `zyj_user_role`  (
  `user_role_id` bigint(64) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `user_id` int(64) NOT NULL COMMENT '用户id',
  `role_id` int(64) NULL DEFAULT NULL COMMENT '角色id',
  `created_by` int(64) NULL DEFAULT NULL COMMENT '创建人',
  `created_time` datetime(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `update_by` int(64) NULL DEFAULT NULL COMMENT '修改人',
  `update_time` datetime(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '修改时间',
  `del_flag` int(1) NULL DEFAULT NULL COMMENT '删除标识',
  PRIMARY KEY (`user_role_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of zyj_user_role
-- ----------------------------
INSERT INTO `zyj_user_role` VALUES (1, 1, 1, NULL, '2020-11-12 15:39:12', NULL, NULL, 0);

SET FOREIGN_KEY_CHECKS = 1;
