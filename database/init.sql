-- =============================================
-- 农产品跨境贸易云平台 - 数据库初始化脚本
-- 毕业设计版本（简化版）
-- =============================================

-- 创建数据库
DROP DATABASE IF EXISTS `agri_trade`;
CREATE DATABASE `agri_trade` CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
USE `agri_trade`;

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- =============================================
-- 用户管理相关表
-- =============================================

-- 用户表
DROP TABLE IF EXISTS `users`;
CREATE TABLE `users` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '用户ID',
  `username` varchar(50) NOT NULL COMMENT '用户名',
  `password` varchar(255) NOT NULL COMMENT '密码',
  `email` varchar(100) NOT NULL COMMENT '邮箱',
  `phone` varchar(20) DEFAULT NULL COMMENT '手机号',
  `real_name` varchar(50) DEFAULT NULL COMMENT '真实姓名',
  `user_type` varchar(20) NOT NULL COMMENT '用户类型：EXPORT_ENTERPRISE,IMPORT_ENTERPRISE,LOGISTICS_PROVIDER,REGULATORY_AUTHORITY',
  `status` varchar(20) DEFAULT 'ACTIVE' COMMENT '状态：ACTIVE,INACTIVE,LOCKED',
  `created_at` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updated_at` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_username` (`username`),
  UNIQUE KEY `uk_email` (`email`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='用户表';

-- 企业信息表
DROP TABLE IF EXISTS `companies`;
CREATE TABLE `companies` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '企业ID',
  `user_id` bigint(20) NOT NULL COMMENT '用户ID',
  `company_name` varchar(200) NOT NULL COMMENT '企业名称',
  `legal_person` varchar(50) DEFAULT NULL COMMENT '法人代表',
  `business_license` varchar(100) DEFAULT NULL COMMENT '营业执照号',
  `address` varchar(500) DEFAULT NULL COMMENT '企业地址',
  `contact_person` varchar(50) DEFAULT NULL COMMENT '联系人',
  `contact_phone` varchar(20) DEFAULT NULL COMMENT '联系电话',
  `status` varchar(20) DEFAULT 'PENDING' COMMENT '审核状态：PENDING,APPROVED,REJECTED',
  `created_at` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updated_at` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`),
  CONSTRAINT `fk_companies_user_id` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='企业信息表';

-- =============================================
-- 资质管理相关表
-- =============================================

-- 资质证书表
DROP TABLE IF EXISTS `qualifications`;
CREATE TABLE `qualifications` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '资质ID',
  `company_id` bigint(20) NOT NULL COMMENT '企业ID',
  `cert_type` varchar(50) NOT NULL COMMENT '证书类型',
  `cert_name` varchar(200) NOT NULL COMMENT '证书名称',
  `cert_number` varchar(100) NOT NULL COMMENT '证书编号',
  `issue_date` date NOT NULL COMMENT '颁发日期',
  `expire_date` date NOT NULL COMMENT '到期日期',
  `file_path` varchar(500) DEFAULT NULL COMMENT '证书文件路径',
  `status` varchar(20) DEFAULT 'PENDING' COMMENT '状态：PENDING,APPROVED,REJECTED,EXPIRED',
  `created_at` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updated_at` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_cert_number` (`cert_number`),
  CONSTRAINT `fk_qualifications_company_id` FOREIGN KEY (`company_id`) REFERENCES `companies` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='资质证书表';

-- =============================================
-- 报关核验相关表
-- =============================================

-- HS编码表
DROP TABLE IF EXISTS `hs_codes`;
CREATE TABLE `hs_codes` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `hs_code` varchar(20) NOT NULL COMMENT 'HS编码',
  `description_cn` varchar(500) NOT NULL COMMENT '中文描述',
  `description_en` varchar(500) DEFAULT NULL COMMENT '英文描述',
  `category` varchar(100) DEFAULT NULL COMMENT '商品类别',
  `tax_rate` decimal(5,2) DEFAULT NULL COMMENT '税率(%)',
  `unit` varchar(20) DEFAULT NULL COMMENT '计量单位',
  `is_restricted` tinyint(1) DEFAULT 0 COMMENT '是否限制出口：0-否，1-是',
  `created_at` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_hs_code` (`hs_code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='HS编码表';

-- 报关单表
DROP TABLE IF EXISTS `customs_declarations`;
CREATE TABLE `customs_declarations` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '报关单ID',
  `company_id` bigint(20) NOT NULL COMMENT '企业ID',
  `declaration_no` varchar(50) NOT NULL COMMENT '报关单号',
  `product_name` varchar(200) NOT NULL COMMENT '商品名称',
  `hs_code` varchar(20) NOT NULL COMMENT 'HS编码',
  `quantity` decimal(15,3) NOT NULL COMMENT '数量',
  `unit_price` decimal(15,2) NOT NULL COMMENT '单价',
  `total_value` decimal(15,2) NOT NULL COMMENT '总价值',
  `destination_country` varchar(50) NOT NULL COMMENT '目的国',
  `trade_terms` varchar(20) DEFAULT NULL COMMENT '贸易条款：FOB,CIF,EXW等',
  `status` varchar(20) DEFAULT 'DRAFT' COMMENT '状态：DRAFT,SUBMITTED,APPROVED,REJECTED',
  `created_at` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_declaration_no` (`declaration_no`),
  CONSTRAINT `fk_customs_declarations_company_id` FOREIGN KEY (`company_id`) REFERENCES `companies` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='报关单表';

-- =============================================
-- 合同管理相关表
-- =============================================

-- 合同表
DROP TABLE IF EXISTS `contracts`;
CREATE TABLE `contracts` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '合同ID',
  `contract_no` varchar(50) NOT NULL COMMENT '合同编号',
  `seller_company_id` bigint(20) NOT NULL COMMENT '卖方企业ID',
  `buyer_company_id` bigint(20) NOT NULL COMMENT '买方企业ID',
  `contract_title` varchar(200) NOT NULL COMMENT '合同标题',
  `product_description` text NOT NULL COMMENT '商品描述',
  `quantity` decimal(15,3) NOT NULL COMMENT '数量',
  `unit_price` decimal(15,2) NOT NULL COMMENT '单价',
  `total_amount` decimal(15,2) NOT NULL COMMENT '合同总金额',
  `trade_terms` varchar(20) NOT NULL COMMENT '贸易条款',
  `delivery_date` date NOT NULL COMMENT '交货日期',
  `status` varchar(20) DEFAULT 'DRAFT' COMMENT '状态：DRAFT,PENDING,SIGNED,CANCELLED',
  `created_at` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_contract_no` (`contract_no`),
  CONSTRAINT `fk_contracts_seller_company_id` FOREIGN KEY (`seller_company_id`) REFERENCES `companies` (`id`) ON DELETE CASCADE,
  CONSTRAINT `fk_contracts_buyer_company_id` FOREIGN KEY (`buyer_company_id`) REFERENCES `companies` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='合同表';

-- =============================================
-- 溯源管理相关表
-- =============================================

-- 溯源记录表
DROP TABLE IF EXISTS `traceability_records`;
CREATE TABLE `traceability_records` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '溯源记录ID',
  `company_id` bigint(20) NOT NULL COMMENT '企业ID',
  `product_batch_no` varchar(50) NOT NULL COMMENT '产品批次号',
  `trace_code` varchar(100) NOT NULL COMMENT '溯源码',
  `product_name` varchar(200) NOT NULL COMMENT '产品名称',
  `production_date` date NOT NULL COMMENT '生产日期',
  `origin_location` varchar(200) DEFAULT NULL COMMENT '产地',
  `production_process` text DEFAULT NULL COMMENT '生产过程记录',
  `quality_info` text DEFAULT NULL COMMENT '质量信息',
  `status` varchar(20) DEFAULT 'ACTIVE' COMMENT '状态：ACTIVE,INACTIVE',
  `created_at` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_trace_code` (`trace_code`),
  CONSTRAINT `fk_traceability_records_company_id` FOREIGN KEY (`company_id`) REFERENCES `companies` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='溯源记录表';

-- =============================================
-- 订单管理相关表
-- =============================================

-- 订单表
DROP TABLE IF EXISTS `orders`;
CREATE TABLE `orders` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '订单ID',
  `order_no` varchar(50) NOT NULL COMMENT '订单号',
  `seller_company_id` bigint(20) NOT NULL COMMENT '卖方企业ID',
  `buyer_company_id` bigint(20) NOT NULL COMMENT '买方企业ID',
  `product_name` varchar(200) NOT NULL COMMENT '商品名称',
  `quantity` decimal(15,3) NOT NULL COMMENT '数量',
  `unit_price` decimal(15,2) NOT NULL COMMENT '单价',
  `total_amount` decimal(15,2) NOT NULL COMMENT '订单总金额',
  `order_status` varchar(20) DEFAULT 'ACTIVE' COMMENT '订单状态：ACTIVE,CANCELLED,COMPLETED',
  `created_at` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_order_no` (`order_no`),
  CONSTRAINT `fk_orders_seller_company_id` FOREIGN KEY (`seller_company_id`) REFERENCES `companies` (`id`) ON DELETE CASCADE,
  CONSTRAINT `fk_orders_buyer_company_id` FOREIGN KEY (`buyer_company_id`) REFERENCES `companies` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='订单表';

-- =============================================
-- 初始化数据
-- =============================================

-- 插入常用HS编码数据
INSERT INTO `hs_codes` (`hs_code`, `description_cn`, `description_en`, `category`, `tax_rate`, `unit`) VALUES
('0701100000', '种用马铃薯', 'Seed potatoes', '蔬菜', 10.00, 'KG'),
('0702000000', '鲜或冷藏的番茄', 'Tomatoes, fresh or chilled', '蔬菜', 13.00, 'KG'),
('0703100000', '鲜或冷藏的洋葱及青葱', 'Onions and shallots, fresh or chilled', '蔬菜', 13.00, 'KG'),
('0801110000', '鲜椰子', 'Coconuts, fresh', '水果', 12.00, 'KG'),
('0803000000', '鲜或干的香蕉', 'Bananas, fresh or dried', '水果', 10.00, 'KG'),
('0805100000', '鲜或干的橙', 'Oranges, fresh or dried', '水果', 12.00, 'KG');

-- 创建默认管理员用户（密码：admin123）
INSERT INTO `users` (`username`, `password`, `email`, `real_name`, `user_type`, `status`) VALUES
('admin', '$2a$10$N.zmdr9k7uOCQb376NoUnuTJ8iAt6Z5EHsM8lE9lBOsl7iKTVEFDi', 'admin@agritrade.com', '系统管理员', 'REGULATORY_AUTHORITY', 'ACTIVE');

-- 创建测试企业用户
INSERT INTO `users` (`username`, `password`, `email`, `real_name`, `user_type`, `status`) VALUES
('test_export', '$2a$10$N.zmdr9k7uOCQb376NoUnuTJ8iAt6Z5EHsM8lE9lBOsl7iKTVEFDi', 'export@test.com', '张三', 'EXPORT_ENTERPRISE', 'ACTIVE'),
('test_import', '$2a$10$N.zmdr9k7uOCQb376NoUnuTJ8iAt6Z5EHsM8lE9lBOsl7iKTVEFDi', 'import@test.com', '李四', 'IMPORT_ENTERPRISE', 'ACTIVE');

-- 创建测试企业信息
INSERT INTO `companies` (`user_id`, `company_name`, `legal_person`, `business_license`, `address`, `contact_person`, `contact_phone`, `status`) VALUES
(2, '测试出口贸易有限公司', '张三', '91110000123456789X', '北京市朝阳区测试路123号', '张三', '13800138001', 'APPROVED'),
(3, '测试进口贸易有限公司', '李四', '91110000987654321Y', '上海市浦东新区测试街456号', '李四', '13800138002', 'APPROVED');

SET FOREIGN_KEY_CHECKS = 1;

-- 显示创建完成信息
SELECT '数据库初始化完成！默认管理员账号：admin，密码：admin123' as message;
