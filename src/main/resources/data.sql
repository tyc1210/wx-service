-- 创建 wx_service 数据库 mysql版本 8.0.24

-- 记录用户操作日志
CREATE TABLE `admin_operate_log` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `operatorName` varchar(50) DEFAULT NULL,
  `optIp` varchar(32) DEFAULT NULL,
  `optTime` datetime DEFAULT NULL,
  `optType` varchar(50) DEFAULT NULL,
  `optModule` varchar(50) DEFAULT NULL,
  `optContent` longtext,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1579980 DEFAULT CHARSET=utf8mb4;

-- 管理员表 角色功能暂未实现
CREATE TABLE `admin_user` (
  `id` bigint(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) NOT NULL,
  `psw` varchar(100) NOT NULL COMMENT 'MD5加密',
  `role` tinyint(3) NOT NULL DEFAULT '2' COMMENT '1超级管理员、2普通管理员、3预备管理员',
  `createdTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updatedTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8mb4;

INSERT INTO `admin_user`(`id`, `name`, `psw`, `role`, `createdTime`, `updatedTime`) VALUES (2, 'test', 'e10adc3949ba59abbe56e057f20f883e', 2, '2023-12-11 15:43:37', '2023-12-11 15:43:37');

