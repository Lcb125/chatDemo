
-- 建表

CREATE TABLE `access_info` (
                               `id` int NOT NULL AUTO_INCREMENT COMMENT '主键',
                               `access_code` int NOT NULL COMMENT '用户编码',
                               `device_id` varchar(200) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT '' COMMENT '设备唯一标识',
                               `access_level` varchar(1) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '用户等级（用来区别用户每天可使用次数）',
                               `available_num` int DEFAULT NULL COMMENT '可用次数',
                               `access_type` varchar(1) COLLATE utf8_bin DEFAULT '' COMMENT '权限类型',
                               `create_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
                               `update_time` datetime DEFAULT NULL COMMENT '更新时间',
                               PRIMARY KEY (`id`),
                               KEY `user_index` (`access_code`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='此表用来储存匿名用户的信息'


-- 插入数据

INSERT INTO `access_info` (`id`, `access_code`, `device_id`, `access_level`, `available_num`, `access_type`, `create_time`, `update_time`) VALUES (1, 1111, '09403504d06a', 'a', 0, 'L', '2023-05-18 14:33:13', '2023-05-18 14:33:12');
INSERT INTO `access_info` (`id`, `access_code`, `access_level`, `access_type`, `create_time`) VALUES (4, 5018, 'c', '', '2023-05-17 20:18:39');
INSERT INTO `access_info` (`id`, `access_code`, `access_level`, `access_type`, `create_time`) VALUES (5, 7625, 'c', '', '2023-05-17 20:18:39');
INSERT INTO `access_info` (`id`, `access_code`, `access_level`, `access_type`, `create_time`) VALUES (6, 2374, 'c', '', '2023-05-17 20:18:39');
INSERT INTO `access_info` (`id`, `access_code`, `access_level`, `access_type`, `create_time`) VALUES (7, 1601, 'c', '', '2023-05-17 20:18:39');
INSERT INTO `access_info` (`id`, `access_code`, `access_level`, `access_type`, `create_time`) VALUES (8, 5097, 'c', '', '2023-05-17 20:18:39');
INSERT INTO `access_info` (`id`, `access_code`, `access_level`, `access_type`, `create_time`) VALUES (9, 6317, 'c', '', '2023-05-17 20:18:39');
INSERT INTO `access_info` (`id`, `access_code`, `access_level`, `access_type`, `create_time`) VALUES (10, 8128, 'c', '', '2023-05-17 20:18:39');
INSERT INTO `access_info` (`id`, `access_code`, `access_level`, `access_type`, `create_time`) VALUES (11, 8344, 'c', '', '2023-05-17 20:18:39');
INSERT INTO `access_info` (`id`, `access_code`, `access_level`, `access_type`, `create_time`) VALUES (12, 7063, 'c', '', '2023-05-17 20:18:39');
INSERT INTO `access_info` (`id`, `access_code`, `access_level`, `access_type`, `create_time`) VALUES (13, 6850, 'c', '', '2023-05-17 20:18:39');
INSERT INTO `access_info` (`id`, `access_code`, `access_level`, `access_type`, `create_time`) VALUES (14, 2553, 'c', '', '2023-05-17 20:18:39');
INSERT INTO `access_info` (`id`, `access_code`, `device_id`, `access_level`, `available_num`, `access_type`, `create_time`) VALUES (17, 1111, '0660a805800e', 'c', 0, 'M', '2023-05-18 14:28:25');