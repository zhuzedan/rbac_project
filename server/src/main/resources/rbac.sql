/*
 Navicat Premium Data Transfer

 Source Server         : localhost_3306
 Source Server Type    : MySQL
 Source Server Version : 80100 (8.1.0)
 Source Host           : localhost:3306
 Source Schema         : rbac

 Target Server Type    : MySQL
 Target Server Version : 80100 (8.1.0)
 File Encoding         : 65001

 Date: 10/05/2024 11:36:46
*/

SET NAMES utf8mb4;
SET
    FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for t_system_login_log
-- ----------------------------
DROP TABLE IF EXISTS `t_system_login_log`;
CREATE TABLE `t_system_login_log`
(
    `id`          bigint                                                        NOT NULL AUTO_INCREMENT COMMENT '访问ID',
    `username`    varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci  NULL DEFAULT '' COMMENT '用户账号',
    `ipaddr`      varchar(128) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT '' COMMENT '登录IP地址',
    `status`      tinyint(1)                                                    NULL DEFAULT 1 COMMENT '登录状态（1成功 0失败）',
    `msg`         longtext CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci     NULL COMMENT '提示信息',
    `create_time` timestamp                                                     NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    `update_time` timestamp                                                     NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    `is_deleted`  tinyint                                                       NULL DEFAULT 0 COMMENT '删除标记（0:可用 1:已删除）',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB
  AUTO_INCREMENT = 1788558172340879362
  CHARACTER SET = utf8mb3
  COLLATE = utf8mb3_general_ci COMMENT = '系统访问记录'
  ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_system_login_log
-- ----------------------------
INSERT INTO `t_system_login_log`
VALUES (1788558146613018625, 'admin', '192.168.28.1', 0, '密码不正确', '2024-05-09 21:14:27', '2024-05-09 21:14:27', 0);
INSERT INTO `t_system_login_log`
VALUES (1788558172340879361, 'admin', '192.168.28.1', 1, '操作成功', '2024-05-09 21:14:33', '2024-05-09 21:14:33', 0);

-- ----------------------------
-- Table structure for t_system_menu
-- ----------------------------
DROP TABLE IF EXISTS `t_system_menu`;
CREATE TABLE `t_system_menu`
(
    `id`          bigint                                                        NOT NULL AUTO_INCREMENT COMMENT '编号',
    `name`        varchar(20) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci  NOT NULL DEFAULT '' COMMENT '菜单名称',
    `parent_id`   bigint                                                        NOT NULL DEFAULT 0 COMMENT '所属上级',
    `perms`       varchar(100) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL     DEFAULT NULL COMMENT '权限标识',
    `type`        tinyint                                                       NOT NULL DEFAULT 0 COMMENT '类型(0:目录,1:菜单,2:按钮)',
    `path`        varchar(100) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL     DEFAULT NULL COMMENT '路由地址',
    `component`   varchar(100) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL     DEFAULT NULL COMMENT '组件路径',
    `icon`        varchar(100) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL     DEFAULT NULL COMMENT '图标',
    `url`         varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL     DEFAULT NULL COMMENT '授权路径',
    `sort_value`  int                                                           NULL     DEFAULT NULL COMMENT '排序',
    `status`      tinyint                                                       NULL     DEFAULT 1 COMMENT '状态(0:禁止,1:正常)',
    `create_time` timestamp                                                     NULL     DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_time` timestamp                                                     NULL     DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    `is_deleted`  tinyint                                                       NULL     DEFAULT 0 COMMENT '删除标记（0:可用 1:已删除）',
    PRIMARY KEY (`id`) USING BTREE,
    INDEX `idx_parent_id` (`parent_id` ASC) USING BTREE
) ENGINE = InnoDB
  AUTO_INCREMENT = 37
  CHARACTER SET = utf8mb3
  COLLATE = utf8mb3_general_ci COMMENT = '菜单表'
  ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_system_menu
-- ----------------------------
INSERT INTO `t_system_menu`
VALUES (2, '系统管理', 0, '', 0, 'system', 'Layout', 'el-icon-s-tools', NULL, 1, 1, '2021-05-31 18:05:37',
        '2023-03-14 15:25:26', 0);
INSERT INTO `t_system_menu`
VALUES (3, '用户管理', 2, '', 1, 'sysUser', 'system/sysUser/Index', 'el-icon-s-custom', NULL, 1, 1,
        '2021-05-31 18:05:37', '2023-03-14 15:25:29', 0);
INSERT INTO `t_system_menu`
VALUES (4, '角色管理', 2, '', 1, 'sysRole', 'system/sysRole/Index', 'el-icon-user-solid', NULL, 2, 1,
        '2021-05-31 18:05:37', '2023-03-14 15:25:29', 0);
INSERT INTO `t_system_menu`
VALUES (5, '菜单管理', 2, '', 1, 'sysMenu', 'system/sysMenu/Index', 'el-icon-s-unfold', NULL, 3, 1,
        '2021-05-31 18:05:37', '2023-03-14 15:25:31', 0);
INSERT INTO `t_system_menu`
VALUES (6, '查看', 3, 'bnt.sysUser.list', 2, NULL, NULL, NULL, NULL, 1, 1, '2021-05-31 18:05:37', '2023-03-13 14:22:20',
        0);
INSERT INTO `t_system_menu`
VALUES (7, '添加', 3, 'bnt.sysUser.add', 2, NULL, NULL, NULL, NULL, 1, 1, '2021-05-31 18:05:37', '2023-03-13 14:22:21',
        0);
INSERT INTO `t_system_menu`
VALUES (8, '修改', 3, 'bnt.sysUser.update', 2, NULL, NULL, NULL, NULL, 1, 1, '2021-05-31 18:05:37',
        '2023-03-13 14:22:22', 0);
INSERT INTO `t_system_menu`
VALUES (9, '删除', 3, 'bnt.sysUser.remove', 2, NULL, NULL, NULL, NULL, 1, 1, '2021-05-31 18:05:37',
        '2023-03-13 14:22:23', 0);
INSERT INTO `t_system_menu`
VALUES (10, '查看', 4, 'bnt.sysRole.list', 2, NULL, NULL, NULL, NULL, 1, 1, '2021-05-31 18:05:37',
        '2023-03-13 14:22:26', 0);
INSERT INTO `t_system_menu`
VALUES (11, '添加', 4, 'bnt.sysRole.add', 2, NULL, NULL, NULL, NULL, 1, 1, '2021-05-31 18:05:37', '2023-03-13 14:22:27',
        0);
INSERT INTO `t_system_menu`
VALUES (12, '修改', 4, 'bnt.sysRole.update', 2, NULL, NULL, NULL, NULL, 1, 1, '2021-05-31 18:05:37',
        '2023-03-13 14:22:28', 0);
INSERT INTO `t_system_menu`
VALUES (13, '删除', 4, 'bnt.sysRole.remove', 2, NULL, NULL, NULL, NULL, 1, 1, '2021-05-31 18:05:37',
        '2023-03-13 14:22:30', 0);
INSERT INTO `t_system_menu`
VALUES (14, '查看', 5, 'bnt.sysMenu.list', 2, NULL, NULL, NULL, NULL, 1, 1, '2021-05-31 18:05:37',
        '2023-03-13 14:22:33', 0);
INSERT INTO `t_system_menu`
VALUES (15, '添加', 5, 'bnt.sysMenu.add', 2, NULL, NULL, NULL, NULL, 1, 1, '2021-05-31 18:05:37', '2023-03-13 14:22:34',
        0);
INSERT INTO `t_system_menu`
VALUES (16, '修改', 5, 'bnt.sysMenu.update', 2, NULL, NULL, NULL, NULL, 1, 1, '2021-05-31 18:05:37',
        '2023-03-13 14:22:35', 0);
INSERT INTO `t_system_menu`
VALUES (17, '删除', 5, 'bnt.sysMenu.remove', 2, NULL, NULL, NULL, NULL, 1, 1, '2021-05-31 18:05:37',
        '2023-03-13 14:22:38', 0);
INSERT INTO `t_system_menu`
VALUES (18, '分配角色', 3, 'bnt.sysUser.assignRole', 2, NULL, NULL, NULL, NULL, 1, 1, '2022-05-23 17:14:32',
        '2023-03-13 14:22:43', 0);
INSERT INTO `t_system_menu`
VALUES (19, '分配权限', 4, 'bnt.sysRole.assignAuth', 2, 'assignAuth', 'system/sysRole/AssignAuth', NULL, NULL, 1, 1,
        '2022-05-23 17:18:14', '2023-03-13 14:22:48', 0);
INSERT INTO `t_system_menu`
VALUES (30, '操作日志', 34, '', 1, 'sysOperLog', 'system/sysOperLog/list', 'el-icon-document-remove', NULL, 7, 1,
        '2022-05-26 16:09:59', '2023-03-14 15:25:32', 0);
INSERT INTO `t_system_menu`
VALUES (31, '查看', 30, 'bnt.sysOperLog.list', 2, NULL, NULL, NULL, NULL, 1, 1, '2022-05-26 16:10:17',
        '2023-03-13 14:23:03', 0);
INSERT INTO `t_system_menu`
VALUES (32, '登录日志', 34, '', 1, 'sysLoginLog', 'system/sysLoginLog/list', 'el-icon-s-goods', NULL, 8, 1,
        '2022-05-26 16:36:13', '2023-03-14 15:25:33', 0);
INSERT INTO `t_system_menu`
VALUES (33, '查看', 32, 'bnt.sysLoginLog.list', 2, NULL, NULL, NULL, NULL, 1, 1, '2022-05-26 16:36:31',
        '2023-03-13 14:23:11', 0);
INSERT INTO `t_system_menu`
VALUES (34, '日志管理', 0, '', 0, 'log', 'ParentView', 'el-icon-tickets', NULL, 6, 1, '2022-05-31 13:23:07',
        '2023-03-14 15:25:34', 0);
INSERT INTO `t_system_menu`
VALUES (35, '商品管理', 0, '', 0, 'forms', 'form/Index', 'el-icon-tickets', NULL, NULL, 1, '2023-03-13 14:04:13',
        '2023-03-14 15:42:41', 0);
INSERT INTO `t_system_menu`
VALUES (36, 'cs', 35, 'bnt', 0, NULL, 'form/Index/', NULL, NULL, NULL, 1, '2023-03-13 14:04:49', '2023-03-13 14:23:13',
        0);

-- ----------------------------
-- Table structure for t_system_operation_log
-- ----------------------------
DROP TABLE IF EXISTS `t_system_operation_log`;
CREATE TABLE `t_system_operation_log`
(
    `id`              bigint                                                         NOT NULL AUTO_INCREMENT COMMENT '日志主键',
    `title`           varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci   NULL     DEFAULT '' COMMENT '模块标题',
    `business_type`   varchar(20) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci   NULL     DEFAULT '0' COMMENT '业务类型（0其它 1新增 2修改 3删除）',
    `method`          varchar(100) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci  NULL     DEFAULT '' COMMENT '方法名称',
    `request_method`  varchar(10) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci   NULL     DEFAULT '' COMMENT '请求方式',
    `operator_type`   varchar(20) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci   NULL     DEFAULT '0' COMMENT '操作类别（0其它 1后台用户 2手机端用户）',
    `operation_name`  varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci   NULL     DEFAULT '' COMMENT '操作人员',
    `operation_url`   varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci  NULL     DEFAULT '' COMMENT '请求URL',
    `operation_ip`    varchar(128) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci  NULL     DEFAULT '' COMMENT '主机地址',
    `operation_param` varchar(2000) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL     DEFAULT '' COMMENT '请求参数',
    `json_result`     varchar(2000) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL     DEFAULT '' COMMENT '返回参数',
    `status`          int                                                            NULL     DEFAULT 0 COMMENT '操作状态（0正常 1异常）',
    `error_msg`       varchar(2000) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL     DEFAULT '' COMMENT '错误消息',
    `operation_time`  varchar(200) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci  NULL     DEFAULT NULL COMMENT '操作时间',
    `create_time`     timestamp                                                      NULL     DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    `update_time`     timestamp                                                      NULL     DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
    `is_deleted`      tinyint                                                        NOT NULL DEFAULT 0 COMMENT '删除标记（0:可用 1:已删除）',
    `browser`         varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci  NULL     DEFAULT NULL COMMENT '浏览器',
    `address`         varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci  NULL     DEFAULT NULL COMMENT '地址',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB
  AUTO_INCREMENT = 1788560157496573954
  CHARACTER SET = utf8mb3
  COLLATE = utf8mb3_general_ci
  ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_system_operation_log
-- ----------------------------
INSERT INTO `t_system_operation_log`
VALUES (1788560157496573953, '获取当前登录用户信息', 'SELECT', 'org.zzd.controller.SystemUserController.getInfo()',
        'GET', 'MANAGE', '', '/api/systemUser/info', '192.168.28.1', '',
        '{\"code\":200,\"data\":{\"code\":200,\"data\":{\"avatar\":\"https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif?imageView2/1/w/80/h/80\",\"description\":\"123123\",\"id\":1,\"perms\":[\"bnt.sysUser.list\",\"bnt.sysUser.add\",\"bnt.sysUser.update\",\"bnt.sysUser.remove\",\"bnt.sysRole.list\",\"bnt.sysRole.add\",\"bnt.sysRole.update\",\"bnt.sysRole.remove\",\"bnt.sysMenu.list\",\"bnt.sysMenu.add\",\"bnt.sysMenu.update\",\"bnt.sysMenu.remove\",\"bnt.sysUser.assignRole\",\"bnt.sysRole.assignAuth\",\"bnt\"],\"realName\":\"管理员\"},\"message\":\"操作成功\",\"success\":true},\"message\":\"操作成功\",\"success\":true}',
        1, '', '1ms', '2024-05-09 21:22:26', '2024-05-09 21:22:26', 0, 'Chrome 124', '内网IP');

-- ----------------------------
-- Table structure for t_system_role
-- ----------------------------
DROP TABLE IF EXISTS `t_system_role`;
CREATE TABLE `t_system_role`
(
    `id`          bigint                                                        NOT NULL AUTO_INCREMENT COMMENT '角色id',
    `role_name`   varchar(20) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci  NOT NULL DEFAULT '' COMMENT '角色名称',
    `role_code`   varchar(20) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci  NULL     DEFAULT NULL COMMENT '角色编码',
    `description` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL     DEFAULT NULL COMMENT '描述',
    `create_time` timestamp                                                     NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_time` timestamp                                                     NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    `is_deleted`  tinyint                                                       NOT NULL DEFAULT 0 COMMENT '删除标记（0:可用 1:已删除）',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB
  AUTO_INCREMENT = 4
  CHARACTER SET = utf8mb3
  COLLATE = utf8mb3_general_ci COMMENT = '角色'
  ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_system_role
-- ----------------------------
INSERT INTO `t_system_role`
VALUES (1, '管理员', 'administrator', '管理员最高权限，有一切的操作功能.', '2023-01-15 10:01:47', '2023-02-15 11:25:04',
        0);
INSERT INTO `t_system_role`
VALUES (2, '商家', 'merchant', '商家可以上架商品，有订单可进行发货', '2022-11-24 10:02:06', '2023-01-16 11:09:35', 0);
INSERT INTO `t_system_role`
VALUES (3, '组织者', 'organizer', '组织者可以组织汉服圈子的活动', '2022-11-24 15:36:20', '2023-01-16 11:09:48', 0);

-- ----------------------------
-- Table structure for t_system_role_menu
-- ----------------------------
DROP TABLE IF EXISTS `t_system_role_menu`;
CREATE TABLE `t_system_role_menu`
(
    `id`          bigint    NOT NULL AUTO_INCREMENT,
    `role_id`     bigint    NOT NULL DEFAULT 0,
    `menu_id`     bigint    NOT NULL DEFAULT 0,
    `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    `is_deleted`  tinyint   NOT NULL DEFAULT 0 COMMENT '删除标记（0:可用 1:已删除）',
    PRIMARY KEY (`id`) USING BTREE,
    INDEX `idx_role_id` (`role_id` ASC) USING BTREE,
    INDEX `idx_menu_id` (`menu_id` ASC) USING BTREE
) ENGINE = InnoDB
  AUTO_INCREMENT = 35
  CHARACTER SET = utf8mb3
  COLLATE = utf8mb3_general_ci COMMENT = '角色菜单'
  ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_system_role_menu
-- ----------------------------
INSERT INTO `t_system_role_menu`
VALUES (1, 2, 2, '2022-06-02 16:11:27', '2022-06-02 16:16:10', 1);
INSERT INTO `t_system_role_menu`
VALUES (2, 2, 3, '2022-06-02 16:11:27', '2022-06-02 16:16:10', 1);
INSERT INTO `t_system_role_menu`
VALUES (3, 2, 6, '2022-06-02 16:11:27', '2022-06-02 16:16:10', 1);
INSERT INTO `t_system_role_menu`
VALUES (4, 2, 7, '2022-06-02 16:11:27', '2022-06-02 16:16:10', 1);
INSERT INTO `t_system_role_menu`
VALUES (5, 2, 8, '2022-06-02 16:11:27', '2022-06-02 16:16:10', 1);
INSERT INTO `t_system_role_menu`
VALUES (6, 2, 9, '2022-06-02 16:11:27', '2022-06-02 16:16:10', 1);
INSERT INTO `t_system_role_menu`
VALUES (7, 2, 18, '2022-06-02 16:11:27', '2022-06-02 16:16:10', 1);
INSERT INTO `t_system_role_menu`
VALUES (8, 2, 4, '2022-06-02 16:11:27', '2022-06-02 16:16:10', 1);
INSERT INTO `t_system_role_menu`
VALUES (9, 2, 10, '2022-06-02 16:11:27', '2022-06-02 16:16:10', 1);
INSERT INTO `t_system_role_menu`
VALUES (10, 2, 11, '2022-06-02 16:11:27', '2022-06-02 16:16:10', 1);
INSERT INTO `t_system_role_menu`
VALUES (11, 2, 12, '2022-06-02 16:11:27', '2022-06-02 16:16:10', 1);
INSERT INTO `t_system_role_menu`
VALUES (12, 2, 13, '2022-06-02 16:11:27', '2022-06-02 16:16:10', 1);
INSERT INTO `t_system_role_menu`
VALUES (13, 2, 19, '2022-06-02 16:11:27', '2022-06-02 16:16:10', 1);
INSERT INTO `t_system_role_menu`
VALUES (14, 2, 5, '2022-06-02 16:11:27', '2022-06-02 16:16:10', 1);
INSERT INTO `t_system_role_menu`
VALUES (15, 2, 14, '2022-06-02 16:11:27', '2022-06-02 16:16:10', 1);
INSERT INTO `t_system_role_menu`
VALUES (16, 2, 15, '2022-06-02 16:11:27', '2022-06-02 16:16:10', 1);
INSERT INTO `t_system_role_menu`
VALUES (17, 2, 16, '2022-06-02 16:11:27', '2022-06-02 16:16:10', 1);
INSERT INTO `t_system_role_menu`
VALUES (18, 2, 17, '2022-06-02 16:11:27', '2022-06-02 16:16:10', 1);
INSERT INTO `t_system_role_menu`
VALUES (19, 2, 2, '2022-06-02 16:16:10', '2022-06-09 09:26:34', 1);
INSERT INTO `t_system_role_menu`
VALUES (20, 2, 3, '2022-06-02 16:16:10', '2022-06-09 09:26:34', 1);
INSERT INTO `t_system_role_menu`
VALUES (21, 2, 6, '2022-06-02 16:16:10', '2022-06-09 09:26:34', 1);
INSERT INTO `t_system_role_menu`
VALUES (22, 2, 7, '2022-06-02 16:16:10', '2022-06-09 09:26:34', 1);
INSERT INTO `t_system_role_menu`
VALUES (23, 2, 8, '2022-06-02 16:16:10', '2022-06-09 09:26:34', 1);
INSERT INTO `t_system_role_menu`
VALUES (24, 2, 2, '2022-06-09 09:26:34', '2022-06-09 09:26:34', 0);
INSERT INTO `t_system_role_menu`
VALUES (25, 2, 3, '2022-06-09 09:26:34', '2022-06-09 09:26:34', 0);
INSERT INTO `t_system_role_menu`
VALUES (26, 2, 6, '2022-06-09 09:26:34', '2022-06-09 09:26:34', 0);
INSERT INTO `t_system_role_menu`
VALUES (27, 2, 7, '2022-06-09 09:26:34', '2022-06-09 09:26:34', 0);
INSERT INTO `t_system_role_menu`
VALUES (28, 2, 8, '2022-06-09 09:26:34', '2022-06-09 09:26:34', 0);
INSERT INTO `t_system_role_menu`
VALUES (29, 2, 5, '2022-06-09 09:26:34', '2022-06-09 09:26:34', 0);
INSERT INTO `t_system_role_menu`
VALUES (30, 2, 14, '2022-06-09 09:26:34', '2022-06-09 09:26:34', 0);
INSERT INTO `t_system_role_menu`
VALUES (31, 2, 20, '2022-06-09 09:26:34', '2022-06-09 09:26:34', 0);
INSERT INTO `t_system_role_menu`
VALUES (32, 2, 21, '2022-06-09 09:26:34', '2022-06-09 09:26:34', 0);
INSERT INTO `t_system_role_menu`
VALUES (33, 3, 35, '2023-03-13 14:12:18', '2023-03-13 14:12:18', 0);
INSERT INTO `t_system_role_menu`
VALUES (34, 3, 36, '2023-03-13 14:12:45', '2023-03-13 14:12:45', 0);

-- ----------------------------
-- Table structure for t_system_user
-- ----------------------------
DROP TABLE IF EXISTS `t_system_user`;
CREATE TABLE `t_system_user`
(
    `id`          bigint                                                        NOT NULL AUTO_INCREMENT COMMENT '用户id',
    `username`    varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci  NOT NULL DEFAULT '' COMMENT '用户名',
    `password`    varchar(100) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL DEFAULT '' COMMENT '密码',
    `real_name`   varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL     DEFAULT NULL COMMENT '真实姓名',
    `nickname`    varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL     DEFAULT NULL COMMENT '昵称',
    `email`       varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL     DEFAULT NULL COMMENT '邮箱',
    `phone`       varchar(11) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci  NULL     DEFAULT NULL COMMENT '手机',
    `avatar`      varchar(200) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL     DEFAULT NULL COMMENT '头像地址',
    `description` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL     DEFAULT NULL COMMENT '描述',
    `status`      tinyint                                                       NULL     DEFAULT 1 COMMENT '状态1启用0禁用',
    `gender`      char(1) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci      NULL     DEFAULT '3' COMMENT '性别;1=男,2=女,3=未知',
    `birthday`    date                                                          NULL     DEFAULT NULL COMMENT '生日',
    `login_ip`    varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci  NULL     DEFAULT NULL COMMENT '登录ip',
    `create_time` timestamp                                                     NULL     DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_time` timestamp                                                     NULL     DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    `is_deleted`  tinyint                                                       NULL     DEFAULT 0 COMMENT '删除标记（0:可用 1:已删除）',
    `user_type`   int                                                           NULL     DEFAULT NULL COMMENT '用户类型后台前台',
    `create_by`   varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL     DEFAULT NULL COMMENT '创建人',
    `update_by`   varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL     DEFAULT NULL COMMENT '更新人',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB
  AUTO_INCREMENT = 10
  CHARACTER SET = utf8mb3
  COLLATE = utf8mb3_general_ci COMMENT = '用户表'
  ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_system_user
-- ----------------------------
INSERT INTO `t_system_user`
VALUES (1, 'admin', '$2a$10$17e8vszA6drGoJDYMytHvOu8f8b3jsJISBbs.Sw0QIJaUdVIYjQc2', '管理员', NULL, '1031155817@qq.com',
        '15099909888', 'https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif?imageView2/1/w/80/h/80',
        '123123', 1, '3', '2023-01-21', NULL, '2023-01-05 21:58:48', '2024-03-17 21:17:15', 0, 0, NULL, NULL);
INSERT INTO `t_system_user`
VALUES (3, '12323', '$2a$10$vBqqHx141pXwhiUN8fVr.eojDQl/mD8pPIlP6zI6kDJJ2XiDOU4I.', 'ffee', NULL, '12323', '12323',
        NULL, '这是一个普通账号', 1, '3', NULL, NULL, '2023-01-15 23:14:06', '2023-02-01 11:43:14', 0, 1, NULL, NULL);
INSERT INTO `t_system_user`
VALUES (4, 'zhangsan', '$2a$10$TG0vliQ7F9PemlpCcVlxaucB54XfM2bARK/yJlFAFPNHLpYKUffzq', '张三', NULL,
        '13030302021@qq.com', '13432321212', NULL, 'des', 1, '0', '2020-09-18', '', '2023-01-21 11:59:02',
        '2023-01-26 11:36:27', 0, 0, NULL, NULL);
INSERT INTO `t_system_user`
VALUES (9, 'abc', '$2a$10$VU/qz3FZLHtYajMG5VZtNeHuZRfuuGrjaViyWLFVC4Kdkmd6BDy02', NULL, '是我', 'abc@123.com',
        '13432343234', '2023/02/0c77a995-2836-4ee1-82c7-a6b04f7c229689X7bs5HfdtA7ae1726c2accaa3049a53916175ebb9d.jpg',
        '说啥', 1, '3', NULL, NULL, '2023-02-25 19:07:18', '2023-03-15 10:43:36', 0, 1, NULL, NULL);

-- ----------------------------
-- Table structure for t_system_user_role
-- ----------------------------
DROP TABLE IF EXISTS `t_system_user_role`;
CREATE TABLE `t_system_user_role`
(
    `id`          bigint    NOT NULL AUTO_INCREMENT COMMENT '主键id',
    `role_id`     bigint    NOT NULL DEFAULT 0 COMMENT '角色id',
    `user_id`     bigint    NOT NULL DEFAULT 0 COMMENT '用户id',
    `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    `is_deleted`  tinyint   NOT NULL DEFAULT 0 COMMENT '删除标记（0:可用 1:已删除）',
    PRIMARY KEY (`id`) USING BTREE,
    INDEX `idx_role_id` (`role_id` ASC) USING BTREE,
    INDEX `idx_admin_id` (`user_id` ASC) USING BTREE
) ENGINE = InnoDB
  AUTO_INCREMENT = 6
  CHARACTER SET = utf8mb3
  COLLATE = utf8mb3_general_ci COMMENT = '用户角色'
  ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_system_user_role
-- ----------------------------
INSERT INTO `t_system_user_role`
VALUES (1, 1, 1, '2022-11-22 17:03:27', '2022-11-22 17:03:27', 0);
INSERT INTO `t_system_user_role`
VALUES (2, 2, 1, '2022-11-22 17:12:40', '2022-11-22 17:12:40', 0);
INSERT INTO `t_system_user_role`
VALUES (3, 3, 1, '2022-11-22 17:14:50', '2023-01-17 20:07:15', 0);
INSERT INTO `t_system_user_role`
VALUES (4, 3, 3, '2023-01-17 20:08:38', '2023-03-04 15:57:09', 0);
INSERT INTO `t_system_user_role`
VALUES (5, 3, 9, '2023-03-13 14:11:50', '2023-03-13 14:11:50', 0);

SET
    FOREIGN_KEY_CHECKS = 1;
