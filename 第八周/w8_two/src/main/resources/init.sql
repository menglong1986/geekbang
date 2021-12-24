
SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for t_config
-- ----------------------------
DROP TABLE IF EXISTS `t_config`;
CREATE TABLE `t_config` (
                            `id` int NOT NULL AUTO_INCREMENT,
                            `user_id` bigint DEFAULT NULL,
                            `config` varchar(100) COLLATE utf8mb4_bin DEFAULT NULL,
    PRIMARY KEY (`id`)
    ) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

-- ----------------------------
-- Table structure for t_order0
-- ----------------------------
DROP TABLE IF EXISTS `t_order0`;
CREATE TABLE `t_order0` (
                            `order_id` bigint NOT NULL AUTO_INCREMENT,
                            `user_id` bigint NOT NULL,
                            `name` varchar(100) COLLATE utf8mb4_bin DEFAULT NULL,
    PRIMARY KEY (`order_id`)
    ) ENGINE=InnoDB AUTO_INCREMENT=681156302403010561 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

-- ----------------------------
-- Table structure for t_order1
-- ----------------------------
DROP TABLE IF EXISTS `t_order1`;
CREATE TABLE `t_order1` (
                            `order_id` bigint NOT NULL AUTO_INCREMENT,
                            `user_id` bigint NOT NULL,
                            `name` varchar(100) COLLATE utf8mb4_bin DEFAULT NULL,
    PRIMARY KEY (`order_id`)
    ) ENGINE=InnoDB AUTO_INCREMENT=681157088906313730 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

SET FOREIGN_KEY_CHECKS = 1;


