/*
 Navicat Premium Data Transfer

 Source Server         : asas
 Source Server Type    : MySQL
 Source Server Version : 50719
 Source Host           : 10.211.55.19:3306
 Source Schema         : test

 Target Server Type    : MySQL
 Target Server Version : 50719
 File Encoding         : 65001

 Date: 25/10/2017 20:04:46
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for dept
-- ----------------------------
DROP TABLE IF EXISTS `dept`;
CREATE TABLE `dept` (
  `deptno` int(11) NOT NULL AUTO_INCREMENT,
  `dname` varchar(100) DEFAULT NULL,
  `creatData` date DEFAULT NULL,
  `age` int(11) DEFAULT NULL,
  PRIMARY KEY (`deptno`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of dept
-- ----------------------------
BEGIN;
INSERT INTO `dept` VALUES (1, '大数据部', '2017-10-04', 14);
INSERT INTO `dept` VALUES (2, '人事部', '2017-10-06', 20);
INSERT INTO `dept` VALUES (3, '生产部', '2017-10-03', 45);
INSERT INTO `dept` VALUES (14, '研发部', '2017-10-04', 95);
COMMIT;

-- ----------------------------
-- Table structure for emp
-- ----------------------------
DROP TABLE IF EXISTS `emp`;
CREATE TABLE `emp` (
  `EMPNO` int(4) NOT NULL,
  `ENAME` varchar(10) COLLATE utf8_bin DEFAULT NULL,
  `JOB` varchar(9) COLLATE utf8_bin DEFAULT NULL,
  `MGR` int(4) DEFAULT NULL,
  `HIREDATE` date DEFAULT NULL,
  `SAL` double(7,2) DEFAULT NULL,
  `COMM` double(7,2) DEFAULT NULL,
  `DEPTNO` int(2) DEFAULT NULL,
  PRIMARY KEY (`EMPNO`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of emp
-- ----------------------------
BEGIN;
INSERT INTO `emp` VALUES (1111, 'æœç´¢', '2222', 7782, '2017-08-28', 1322.00, NULL, 10);
INSERT INTO `emp` VALUES (7369, 'SMITH', 'CLERK', 7902, '1980-12-17', 801.00, NULL, 20);
INSERT INTO `emp` VALUES (7499, 'ALLEN', 'SALESMAN', 7698, '1981-02-20', 1600.00, 300.00, 30);
INSERT INTO `emp` VALUES (7521, 'WARD', 'SALESMAN', 7698, '1981-02-22', 1250.00, 500.00, 30);
INSERT INTO `emp` VALUES (7566, 'JONES', 'MANAGER', 7839, '1981-04-02', 2975.00, NULL, 20);
INSERT INTO `emp` VALUES (7654, 'MARTIN', 'SALESMAN', 7698, '1981-09-28', 1250.00, 1400.00, 30);
INSERT INTO `emp` VALUES (7698, 'BLAKE', 'MANAGER', 7839, '1981-05-01', 2850.00, NULL, 30);
INSERT INTO `emp` VALUES (7782, 'CLARK', 'MANAGER', 7839, '1981-06-09', 2450.00, NULL, 10);
INSERT INTO `emp` VALUES (7788, 'SCOTT', 'ANALYST', 7566, '1987-04-19', 3000.00, NULL, 20);
INSERT INTO `emp` VALUES (7839, 'KING', 'PRESIDENT', NULL, '1981-11-17', 5000.00, NULL, 10);
INSERT INTO `emp` VALUES (7844, 'TURNER', 'SALESMAN', 7698, '1981-09-08', 1500.00, 0.00, 30);
INSERT INTO `emp` VALUES (7876, 'ADAMS', 'CLERK', 7788, '1987-05-23', 1100.00, NULL, 20);
INSERT INTO `emp` VALUES (7900, 'JAMES', 'CLERK', 7698, '1981-12-03', 950.00, NULL, 30);
INSERT INTO `emp` VALUES (7902, 'FORD', 'ANALYST', 7566, '1981-12-03', 3000.00, NULL, 20);
INSERT INTO `emp` VALUES (7934, 'MILLER', 'CLERK', 7782, '1982-01-23', 1301.00, NULL, 10);
COMMIT;

-- ----------------------------
-- Table structure for sys_resource
-- ----------------------------
DROP TABLE IF EXISTS `sys_resource`;
CREATE TABLE `sys_resource` (
  `id` int(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) DEFAULT NULL,
  `type` varchar(50) DEFAULT NULL,
  `url` varchar(200) DEFAULT NULL,
  `parent_id` int(20) DEFAULT NULL,
  `permission` varchar(100) DEFAULT NULL,
  `available` tinyint(1) DEFAULT '0',
  `icon` varchar(60) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `idx_sys_resource_parent_id` (`parent_id`)
) ENGINE=InnoDB AUTO_INCREMENT=90 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_resource
-- ----------------------------
BEGIN;
INSERT INTO `sys_resource` VALUES (1, '系统管理', 'menu', '#', 0, 'sys:menu', 1, 'menu-icon glyphicon glyphicon-cog');
INSERT INTO `sys_resource` VALUES (47, '资源管理', 'menu', 'resourceIndext', 1, 'menu:menu', 1, 'menu-icon glyphicon glyphicon-leaf');
INSERT INTO `sys_resource` VALUES (57, '角色管理', 'menu', 'roleIndext', 1, 'role:menu', 0, 'menu-icon glyphicon glyphicon-leaf');
INSERT INTO `sys_resource` VALUES (61, '用户管理', 'menu', 'userIndext', 1, 'user:menu', 0, 'menu-icon glyphicon glyphicon-leaf');
INSERT INTO `sys_resource` VALUES (62, '添加用户', 'button', '', 61, 'user:create', 0, 'menu-icon glyphicon glyphicon-leaf');
INSERT INTO `sys_resource` VALUES (63, '修改用户或密码', 'button', '', 61, 'user:update', 0, 'menu-icon glyphicon glyphicon-leaf');
INSERT INTO `sys_resource` VALUES (64, '删除用户', 'button', '', 61, 'user:delete', 0, 'menu-icon glyphicon glyphicon-leaf');
INSERT INTO `sys_resource` VALUES (80, '系统日志', 'menu', 'druid', 1, 'syslog:menu', 0, 'menu-icon glyphicon glyphicon-leaf');
INSERT INTO `sys_resource` VALUES (81, 'springMVC学习', 'menu', '#', 0, 'springMVC:menu', 0, 'menu-icon glyphicon glyphicon-leaf');
INSERT INTO `sys_resource` VALUES (82, 'RestUrlCRUD', 'menu', 'restUrlIndext', 81, 'restUrlCRUD:menu', 0, 'menu-icon glyphicon glyphicon-leaf');
INSERT INTO `sys_resource` VALUES (83, '数据校验', 'menu', 'dataValidationIndext', 81, 'dataValidation:menu', 0, 'menu-icon glyphicon glyphicon-leaf');
INSERT INTO `sys_resource` VALUES (84, 'zdy类型转换器', 'menu', 'conversionServiceIndext', 81, 'conversionService:menu', 0, 'menu-icon glyphicon glyphicon-leaf');
INSERT INTO `sys_resource` VALUES (85, '数据格式化', 'menu', 'formattingIndex', 81, 'formatting:menu', 0, 'menu-icon glyphicon glyphicon-leaf');
INSERT INTO `sys_resource` VALUES (86, 'zdy视图解析器与视图', 'menu', 'viewIndext', 81, 'view:menu', 0, 'menu-icon glyphicon glyphicon-leaf');
INSERT INTO `sys_resource` VALUES (87, 'zdy参数解析器', 'menu', 'argumentResolverIndext', 81, 'argumentResolver:menu', 0, 'menu-icon glyphicon glyphicon-leaf');
INSERT INTO `sys_resource` VALUES (88, '国际化', 'menu', 'i18nIndext', 81, 'i18n:menu', 0, 'menu-icon glyphicon glyphicon-leaf');
INSERT INTO `sys_resource` VALUES (89, '消息转换器', 'menu', 'messageConverterIndex', 81, 'messageConverter:menu', 0, 'menu-icon glyphicon glyphicon-leaf');
COMMIT;

-- ----------------------------
-- Table structure for sys_roles
-- ----------------------------
DROP TABLE IF EXISTS `sys_roles`;
CREATE TABLE `sys_roles` (
  `id` int(20) NOT NULL AUTO_INCREMENT,
  `role` varchar(100) DEFAULT NULL,
  `description` varchar(100) DEFAULT NULL,
  `available` tinyint(1) DEFAULT '0',
  PRIMARY KEY (`id`),
  UNIQUE KEY `idx_sys_roles_role` (`role`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_roles
-- ----------------------------
BEGIN;
INSERT INTO `sys_roles` VALUES (1, 'admin', '系统管理员', 0);
INSERT INTO `sys_roles` VALUES (2, 'del', '一级管理员', 0);
COMMIT;

-- ----------------------------
-- Table structure for sys_roles_resource
-- ----------------------------
DROP TABLE IF EXISTS `sys_roles_resource`;
CREATE TABLE `sys_roles_resource` (
  `role_id` int(20) NOT NULL,
  `resource_id` int(20) NOT NULL,
  PRIMARY KEY (`role_id`,`resource_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_roles_resource
-- ----------------------------
BEGIN;
INSERT INTO `sys_roles_resource` VALUES (1, 1);
INSERT INTO `sys_roles_resource` VALUES (1, 47);
INSERT INTO `sys_roles_resource` VALUES (1, 57);
INSERT INTO `sys_roles_resource` VALUES (1, 61);
INSERT INTO `sys_roles_resource` VALUES (1, 62);
INSERT INTO `sys_roles_resource` VALUES (1, 63);
INSERT INTO `sys_roles_resource` VALUES (1, 64);
INSERT INTO `sys_roles_resource` VALUES (1, 80);
INSERT INTO `sys_roles_resource` VALUES (1, 81);
INSERT INTO `sys_roles_resource` VALUES (1, 82);
INSERT INTO `sys_roles_resource` VALUES (1, 83);
INSERT INTO `sys_roles_resource` VALUES (1, 84);
INSERT INTO `sys_roles_resource` VALUES (1, 85);
INSERT INTO `sys_roles_resource` VALUES (1, 86);
INSERT INTO `sys_roles_resource` VALUES (1, 87);
INSERT INTO `sys_roles_resource` VALUES (1, 88);
INSERT INTO `sys_roles_resource` VALUES (1, 89);
INSERT INTO `sys_roles_resource` VALUES (2, 1);
INSERT INTO `sys_roles_resource` VALUES (2, 61);
COMMIT;

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user` (
  `id` int(20) NOT NULL AUTO_INCREMENT,
  `username` varchar(100) DEFAULT NULL,
  `password` varchar(100) DEFAULT NULL,
  `salt` varchar(100) DEFAULT NULL,
  `locked` tinyint(1) DEFAULT '0',
  PRIMARY KEY (`id`),
  UNIQUE KEY `idx_sys_users_username` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_user
-- ----------------------------
BEGIN;
INSERT INTO `sys_user` VALUES (1, 'xiaohei', '7e4e2dbc3e45c3e50e46983371ab1f7a', '08eea8ffe8d4d1716d7e426a1b0353b8', 0);
INSERT INTO `sys_user` VALUES (18, 'xiaobai', 'ccf25ef2eaf4456ddd9e9164789dccb0', 'd34a30fba39e74bc923069114c88b3f8', 0);
INSERT INTO `sys_user` VALUES (20, 'xiaolan', '8546873bacadbcdcb9fdf82cad99cb74', 'f461f2fc3acfa78f9b698fd39f08926a', 0);
INSERT INTO `sys_user` VALUES (21, 'xiaohong', 'c8ef0ca108ff1d2e47a75f51dc743348', '98aa2b4b6a3d6ac6c37f11ae29ad8e56', 0);
INSERT INTO `sys_user` VALUES (22, 'qwe', 'c6d13678ff236cb0fea8fc79c1603535', '046f50e935f3b31283196b5897ad13b3', 0);
COMMIT;

-- ----------------------------
-- Table structure for sys_users_roles
-- ----------------------------
DROP TABLE IF EXISTS `sys_users_roles`;
CREATE TABLE `sys_users_roles` (
  `user_id` bigint(20) NOT NULL,
  `role_id` bigint(20) NOT NULL,
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_users_roles
-- ----------------------------
BEGIN;
INSERT INTO `sys_users_roles` VALUES (1, 1);
INSERT INTO `sys_users_roles` VALUES (18, 2);
INSERT INTO `sys_users_roles` VALUES (20, 1);
INSERT INTO `sys_users_roles` VALUES (21, 1);
INSERT INTO `sys_users_roles` VALUES (22, 1);
COMMIT;

-- ----------------------------
-- Procedure structure for myproc1
-- ----------------------------
DROP PROCEDURE IF EXISTS `myproc1`;
delimiter ;;
CREATE DEFINER=`root`@`%` PROCEDURE `myproc1`(in did int, OUT s int)
BEGIN
      SELECT COUNT(*) INTO s FROM dept where dept.deptno=did;
	set s =2;
    END;
;;
delimiter ;

-- ----------------------------
-- Procedure structure for myproc3
-- ----------------------------
DROP PROCEDURE IF EXISTS `myproc3`;
delimiter ;;
CREATE DEFINER=`root`@`%` PROCEDURE `myproc3`(in did int, OUT s int)
BEGIN
	set s =did;
    END;
;;
delimiter ;

SET FOREIGN_KEY_CHECKS = 1;
