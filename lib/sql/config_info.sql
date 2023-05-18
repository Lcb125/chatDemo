
-- 建表

CREATE TABLE `config_info` (
                               `id` int NOT NULL AUTO_INCREMENT COMMENT '主键',
                               `code_type` varchar(20) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT '' COMMENT '配置类型',
                               `code_key` varchar(10) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT '' COMMENT '配置key',
                               `code_value` varchar(20) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT '' COMMENT '配置value',
                               `code_desc` varchar(40) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT '' COMMENT '配置描述',
                               `create_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
                               `update_time` datetime DEFAULT NULL COMMENT '更新时间',
                               `create_by` varchar(20) COLLATE utf8_bin DEFAULT '' COMMENT '创建人',
                               `update_by` varchar(20) COLLATE utf8_bin DEFAULT '' COMMENT '更新人',
                               PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='配置信息表'

-- 插入数据

INSERT INTO `config_info` (`id`, `code_type`, `code_key`, `code_value`, `code_desc`, `create_time`, `create_by`, `update_by`) VALUES (1, 'switch', 'ifCheck', 'true', '判断页面是否需要校验', '2023-05-18 11:22:36', '', '');
INSERT INTO `config_info` (`id`, `code_type`, `code_key`, `code_value`, `code_desc`, `create_time`, `create_by`, `update_by`) VALUES (3, 'access_level', 'a', '100', 'a级别编号可使用100次', '2023-05-18 11:25:00', '', '');
INSERT INTO `config_info` (`id`, `code_type`, `code_key`, `code_value`, `code_desc`, `create_time`, `create_by`, `update_by`) VALUES (4, 'access_level', 'b', '20', 'b级别编号可使用20次', '2023-05-18 11:25:00', '', '');
INSERT INTO `config_info` (`id`, `code_type`, `code_key`, `code_value`, `code_desc`, `create_time`, `create_by`, `update_by`) VALUES (5, 'access_level', 'c', '5', 'c级别编号可使用5次', '2023-05-18 11:25:00', '', '');
